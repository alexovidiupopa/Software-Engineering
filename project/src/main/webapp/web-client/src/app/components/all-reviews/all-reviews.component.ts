import {Component, OnInit} from '@angular/core';
import {PaperService} from '../../services/paper/paper.service';
import {Review} from '../../model/review';

@Component({
  selector: 'app-all-reviews',
  templateUrl: './all-reviews.component.html',
  styleUrls: ['./all-reviews.component.css']
})
export class AllReviewsComponent implements OnInit {
  reviews: Review[] = [];

  constructor(private paperService: PaperService) {
  }

  ngOnInit(): void {
    // fixme: uncomment this after the backend is implemented
    // todo filter pcs that uploaded paper
    /*
    * this.paperService.getAllReviews().subscribe(result => this.reviews = result);
    * */

    // let review1: Review = new Review(1); // fixme remove all below
    // review1.qualifier = 'strong';
    // let review2: Review = new Review(2);
    // review2.qualifier = 'stronger';
    // let review3: Review = new Review(3);
    // review3.qualifier = 'strongest';
    // let review4: Review = new Review(4);
    // review4.qualifier = 'strongerer';
    // let review5: Review = new Review(5);
    // review5.qualifier = 'Strongeststst';
    //
    // this.reviews.push(review1);
    // this.reviews.push(review2);
    // this.reviews.push(review3);
    // this.reviews.push(review4);
    // this.reviews.push(review5);

  }

}
