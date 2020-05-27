import {Component, OnInit} from '@angular/core';
import {Paper} from '../../model/paper';
import {PaperService} from '../../services/paper/paper.service';
import {AuthenticationService} from '../../services/login';

@Component({
  selector: 'app-author-papers',
  templateUrl: './author-papers.component.html',
  styleUrls: ['./author-papers.component.css']
})
export class AuthorPapersComponent implements OnInit {
  papers: Paper[];
  loading: boolean = true;

  constructor(private paperService: PaperService, private userService: AuthenticationService) {
  }

  ngOnInit(): void {
    this.getPapers();
  }

  private getPapers() {
    this.paperService.getPapersForAuthor(this.userService.getCurrentUser().id).subscribe(
      result => {
        this.papers = result;
        this.loading = false;
        console.log(result);
      }
    );
  }
}
