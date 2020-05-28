import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Location} from '@angular/common';
import {Paper} from '../../model/paper';
import {PaperService} from '../../services/paper/paper.service';
import {DomSanitizer} from '@angular/platform-browser';
import * as $ from 'jquery';
import {AuthenticationService} from '../../services/login';

@Component({
  selector: 'app-paper-detail',
  templateUrl: './paper-detail.component.html',
  styleUrls: ['./paper-detail.component.css']
})
export class PaperDetailComponent implements OnInit {
  paper: Paper;
  paperUploaded: boolean = false;
  uploadPaperButtonText: string;
  paperTitle: string;
  paperAuthors: string;
  paperKeywords: string;
  @ViewChild('paperFileUpload', {static: false}) paperFileUpload: ElementRef;
  @ViewChild('abstractFileUpload', {static: false}) abstractFileUpload: ElementRef;
  paperFile: File = null;
  paperFilename: string;
  abstractFile: File = null;
  abstractFilename: string;
  id = +this.route.snapshot.paramMap.get('id');
  abstractUrl;
  paperContentUrl;
  updateFailed = false;


  constructor(
    private route: ActivatedRoute,
    private paperService: PaperService,
    private location: Location,
    private router: Router,
    private sanitizer: DomSanitizer,
    private authenticationService: AuthenticationService
  ) {

  }

  ngOnInit() {
    this.getPaper();
  }

  getPaper(): void {
    this.paperService.getPaperById(this.id)
      .subscribe(paper => {
        this.paper = paper;
        this.paperService.paperHasContentUploaded(paper.pid).subscribe(result => {
            this.paperUploaded = result;
            if (this.paperUploaded===true) {
              this.paperContentUrl = this.sanitizer.bypassSecurityTrustResourceUrl('http://localhost:8080/api/paper/content/' + paper.pid);
              this.uploadPaperButtonText = 'Update paper content';
              this.paperFilename = this.paper.contentUrl.substring(this.paper.contentUrl.lastIndexOf("/")+1);
            } else {
              this.uploadPaperButtonText = 'Upload paper';
            }
          }
        );
        this.abstractUrl = this.sanitizer.bypassSecurityTrustResourceUrl('http://localhost:8080/api/paper/abstract/' + paper.pid);
        this.abstractFilename = this.paper.abstractUrl.substring(this.paper.abstractUrl.lastIndexOf("/")+1);
      });
  }

  goBack(): void {
    this.location.back();
  }

  updatePaper() {
    if (!this.paperUploaded && this.paperFile == null) {
      this.updateFailed = true;
    } else {
      this.paperTitle = $('#paper-title').val();
      this.paperAuthors = $('#paper-authors').val();
      this.paperKeywords = $('#paper-keywords').val();

      if (this.abstractFile!==null) {
        const newName = this.paper.abstractUrl.substring(this.paper.abstractUrl.lastIndexOf("/") + 1);
        this.abstractFile = new File([this.abstractFile], newName, {type: this.abstractFile.type});
        console.log(newName);
      }

      if (this.paper.contentUrl===null){
        this.paper.contentUrl = "/" + this.paperFile.name;
      }
      if (this.paperFile!==null){
        const newNameContent = this.paper.contentUrl.substring(this.paper.contentUrl.lastIndexOf("/")+1);
        console.log(newNameContent);
        this.paperFile = new File([this.paperFile],newNameContent,{type:this.paperFile.type});
      }

      console.log(this.abstractFile);
      // fixme this would probably crash due to lack of null-checking on files
      this.paperService.updatePaper(this.id, this.authenticationService.getCurrentUser().id, this.paperTitle, this.paperKeywords, this.abstractFile, this.paperFile, this.paper.contentUrl.substring(this.paper.contentUrl.lastIndexOf("/")+1))
        .subscribe(response => {
          if (response === true) {
            this.router.navigateByUrl(this.authenticationService.getCurrentUser().getHomepageUrl());
            this.updateFailed = false;
          } else {
            this.updateFailed = true;
          }
        });
    }
  }

  paperTitleUpdated(value: string) {
    this.paperTitle = value;
  }

  paperAuthorsUpdated(value: string) {
    this.paperAuthors = value;
  }

  paperKeywordsUpdated(value: string) {
    this.paperKeywords = value;
  }

  uploadAbstract() {
    const fileUpload = this.abstractFileUpload.nativeElement;
    fileUpload.onchange = () => {
      this.abstractFile = fileUpload.files[0];
      this.abstractFilename = this.abstractFile.name;
      this.abstractFileUpload.nativeElement.value = '';
    };
    fileUpload.click();
  }

  uploadPaper() {
    const fileUpload = this.paperFileUpload.nativeElement;
    fileUpload.onchange = () => {
      this.paperFile = fileUpload.files[0];
      this.paperFilename = this.paperFile.name;
      this.paperFileUpload.nativeElement.value = '';
    };
    fileUpload.click();
  }
}
