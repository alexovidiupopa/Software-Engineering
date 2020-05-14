import {Component, OnInit} from '@angular/core';
import {Paper} from '../../../model/paper';
import {PaperService} from '../../../services/paper/paper.service';
import {AuthenticationService} from '../../../services/login';

@Component({
  selector: 'app-author-papers',
  templateUrl: './author-papers.component.html',
  styleUrls: ['./author-papers.component.css']
})
export class AuthorPapersComponent implements OnInit {
  papers: Paper[];

  constructor(private paperService: PaperService, private userService: AuthenticationService) {
  }

  ngOnInit(): void {
    this.getPapers();
  }

  private getPapers() {
    this.paperService.getPapersForAuthor(/*this.userService.getCurrentUser().id*/1).subscribe( // fixme uncomment when login logic works
      result => {
        this.papers = result;
        console.log(result);
      }
    );
  }
}
