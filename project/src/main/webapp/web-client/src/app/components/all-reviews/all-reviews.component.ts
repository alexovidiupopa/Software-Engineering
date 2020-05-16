import {Component, OnInit} from '@angular/core';
import {Paper} from "../../model/paper";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {PaperService} from "../../services/paper/paper.service";
import {Review} from "../../model/review";
import {Reviewer} from "../../model/Reviewer";

@Component({
  selector: 'app-all-reviews',
  templateUrl: './all-reviews.component.html',
  styleUrls: ['./all-reviews.component.css']
})
export class AllReviewsComponent implements OnInit {
  reviews: Review[] = [];

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  url:string = 'http://localhost:8080/api/paper';
  constructor(private paperService: PaperService , private http: HttpClient) {
  }

  private get_reviews(){
    const url = this.url + '/reviews';
    return this.http.get<Review[]>(url,this.httpOptions).subscribe(result=>
    this.reviews = result);
  }
  ngOnInit(): void {
  //this.get_reviews() fixme: uncomment this after the backend is implemented


    let review1 : Review = new Review(1);
    review1.qualifier = "strong";
    let review2 : Review = new Review(2);
    review2.qualifier = "stronger";
    let review3 : Review = new Review(3);
    review3.qualifier = "strongest";
      let review4 : Review = new Review(4);
    review4.qualifier = "strongerer";
      let review5 : Review = new Review(5);
    review5.qualifier = "Strongeststst";

    this.reviews.push(review1);
    this.reviews.push(review2);
    this.reviews.push(review3);
    this.reviews.push(review4);
    this.reviews.push(review5);

  }

}
