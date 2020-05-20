import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {PaperService} from '../../services/paper/paper.service';
import {Router} from '@angular/router';
import {AuthenticationService} from "../../services/login";

@Component({
  selector: 'app-upload-abstract',
  templateUrl: './upload-abstract.component.html',
  styleUrls: ['./upload-abstract.component.css']
})
export class UploadAbstractComponent implements OnInit {
  abstractFailed = false;
  paperTitle = '';
  paperKeywords = '';
  paperAuthors = '';
  @ViewChild('fileUpload', {static: false}) fileUpload: ElementRef;
  file: File = null;
  fileName: string;

  constructor(private paperService: PaperService, private router: Router, private authenticationService:AuthenticationService) {
  }

  ngOnInit(): void {
  }

  uploadAbstractButtonPressed() {
    const fileUpload = this.fileUpload.nativeElement;
    fileUpload.onchange = () => {
      this.file = fileUpload.files[0];
      this.fileName = this.file.name;
      this.fileUpload.nativeElement.value = '';
    };
    fileUpload.click();
  }

  validData() {
    return this.paperTitle.length > 0 && this.paperKeywords.length > 0 && this.file != null;
  }

  createAbstractButtonPressed() {
    if (this.validData()) {
      this.paperService
        .uploadAbstract(this.authenticationService.getCurrentUser().id, this.paperTitle, this.paperKeywords, this.file)
        .subscribe(result => {
          console.log(result);
          if (result === true) {
            this.abstractFailed = false;
            this.router.navigateByUrl('/author-home');
          } else {
            this.abstractFailed = true;
          }
        });
    } else {
      this.abstractFailed = true;
    }
  }

  paperTitleChanged(value: string) {
    this.paperTitle = value;
  }

  paperAuthorsChanged(value: string) {
    this.paperAuthors = value;
  }

  paperKeywordsChanged(value: string) {
    this.paperKeywords = value;
  }
}
