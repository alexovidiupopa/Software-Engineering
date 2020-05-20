import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Paper} from '../../model/paper';
import {PaperService} from '../../services/paper/paper.service';
import {ActivatedRoute} from '@angular/router';
import {ReviewMark} from '../../model/review-mark';
import {Observable} from 'rxjs';
import {DomSanitizer} from '@angular/platform-browser';
import {AuthenticationService} from '../../services/login';
import {Review} from '../../model/review';
import {MatRadioChange} from '@angular/material/radio';
import {AuthorService} from '../../services/author/author.service';

@Component({
  selector: 'app-pc-review',
  templateUrl: './pc-review.component.html',
  styleUrls: ['./pc-review.component.css']
})
export class PcReviewComponent implements OnInit {
  papers: Observable<Paper[]>;
  markValues = [{name: 'Strong accept', value: ReviewMark.STRONG_ACCEPT},
    {name: 'Accept', value: ReviewMark.ACCEPT},
    {name: 'Weak accept', value: ReviewMark.WEAK_ACCEPT},
    {name: 'Borderline paper', value: ReviewMark.BORDERLINE_PAPER},
    {name: 'Weak reject', value: ReviewMark.WEAK_REJECT},
    {name: 'Reject', value: ReviewMark.REJECT},
    {name: 'Strong reject', value: ReviewMark.STRONG_REJECT}];
  marks: Mark[] = [];
  urls = [];
  isLoaded = false;
  paperNames = [];
  badData: boolean[] = [];
  @ViewChild('reviewUpload', {static: false}) reviewUpload: ElementRef;
  reviews: ReviewData[] = [];
  abstractUrl: any;
  contentUrl: any;
  authors: string[] = [];
  constructor(private paperService: PaperService, private route: ActivatedRoute, private authorService: AuthorService,
              private sanitizer: DomSanitizer, private authenticationService: AuthenticationService
  ) {
    this.papers = this.paperService.getAllPapersForReviewer(+this.route.snapshot.paramMap.get('id'));
    this.papers.subscribe(result => {
      for (let i = 0; i < result.length; i++) {
        this.urls.push(this.sanitizer.bypassSecurityTrustResourceUrl('http://localhost:8080/api/paper/content/' + result[i].pid));
        this.paperNames.push(result[i].title);
        this.marks.push(null);
        this.badData[i] = false;
        this.authorService.getAuthorById(result[i].authorId).subscribe(response => this.authors.push(response.firstname + ' ' + response.lastname));
      }
    });
    console.log('pc-review constructor');
    console.log(this.contentUrl);
    this.isLoaded = true;
  }

  ngOnInit(): void {
  }

  getAuthor(authorId: number) {
    let name: string = null;
    this.authorService.getAuthorById(authorId).subscribe(response => name = response.firstname + ' ' + response.lastname);
    return name;
  }

  uploadReview(id: number) {
    const fileUpload = this.reviewUpload.nativeElement;
    fileUpload.onchange = () => {
      this.reviews.push({id, review: fileUpload.files[0], name: fileUpload.files[0].name});
      this.reviewUpload.nativeElement.value = '';
    };
    fileUpload.click();
  }

  submitReview(id: number, index: number) {
    let file = this.fileOfReviewWithPaperId(id);
    let qualifier = this.getQualifierForPaper(index);
    if (this.validData(file, qualifier)) {
      let userId = this.authenticationService.getCurrentUser().id;
      this.paperService.submitReview(userId, new Review(id, file, qualifier))
        .subscribe(response => {
          if (response === true) {
            location.reload(true);
            this.badData[index] = false;
          } else {
            this.badData[index] = true;
          }
        });
    } else {
      this.badData[index] = true;
    }
  }

  nameOfReviewWithId(id: number): string {
    for (const reviewData of this.reviews) {
      if (reviewData.id === id) {
        return reviewData.name;
      }
    }
    return null;
  }

  updateSelectedMark($event: MatRadioChange, i: number) {
    this.marks[i] = new Mark(ReviewMark[$event.value], ReviewMark[ReviewMark[$event.value]]);
  }

  private fileOfReviewWithPaperId(id: number): File {
    for (const reviewData of this.reviews) {
      if (reviewData.id === id) {
        //    console.log(reviewData.review);
        return reviewData.review;
      }
    }
    return null;
  }

  private validData(file: File, qualifier: number) {
    return file !== null && qualifier >= 0 ;
  }

  private getQualifierForPaper(id: number): number {
    if (this.marks[id] !== null) {
      return this.marks[id].value;
    } else {
      return null;
    }
  }
}

class Mark {
  name: string;
  value: ReviewMark;


  constructor(name: string, value: ReviewMark) {
    this.name = name;
    this.value = value;
  }
}

class ReviewData {
  id: number;
  review: File;
  name: string;
}
