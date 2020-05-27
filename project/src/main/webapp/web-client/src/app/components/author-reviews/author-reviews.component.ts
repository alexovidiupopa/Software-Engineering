import { Component, OnInit } from '@angular/core';
import {Paper} from "../../model/paper";
import {PaperService} from "../../services/paper/paper.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-author-reviews',
  templateUrl: './author-reviews.component.html',
  styleUrls: ['./author-reviews.component.css']
})
export class AuthorReviewsComponent implements OnInit {

  papers: Paper[] = [];
  id = +this.route.snapshot.paramMap.get('id');

  constructor(private paperService: PaperService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.paperService.getReviewsForAuthor(this.id).subscribe(result => this.papers = result);
  }
}
