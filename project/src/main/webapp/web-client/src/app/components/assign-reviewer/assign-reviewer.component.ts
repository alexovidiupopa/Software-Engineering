import {Component, OnInit} from '@angular/core';
import {PaperService} from '../../services/paper/paper.service';
import {Reviewer} from '../../model/Reviewer';
import {MatCheckbox} from '@angular/material/checkbox';
import {PaperReviewerPair} from '../../model/PaperReviewerPair';
import {Paper} from '../../model/paper';
import {Router} from '@angular/router';

@Component({
  selector: 'app-assign-reviewer',
  templateUrl: './assign-reviewer.component.html',
  styleUrls: ['./assign-reviewer.component.css']
})
export class AssignReviewerComponent implements OnInit {

  papers: Paper[] = [];
  reviewers: Reviewer[] = [];
  paperReviewerPair: PaperReviewerPair = null;
  badData = false;
  public breakpoint: number;
  canShow = false;

  constructor(private paperService: PaperService, private router: Router) {
  }

  addRemoveReviewer(event, checkbox: MatCheckbox) {

    if (checkbox.checked) {
      for (const reviewer of this.reviewers) {
        if (reviewer.id === Number(checkbox.value)) {
          this.paperReviewerPair.addReviewer(reviewer.id);
        }
      }

    } else if (!checkbox.checked) {
      this.paperReviewerPair.removeReviewer(Number(checkbox.value));
    }
  }

  assignReviewerToPaper() {

    if (this.paperReviewerPair.canSend() === 1) {
      alert('you can add at most 3 reviewers'); // todo should not use browser specific stuff, not complying to material design
    } else if (this.paperReviewerPair.canSend() === -1) {
      alert('you need to have at least 2 reviewers');
    } else {

      this.paperService.assignReviewersToPaper(this.paperReviewerPair)
        .subscribe(result => {
          if (result === true) {
            this.router.navigateByUrl('/chair-home');
            this.badData = false;
          } else {
            this.badData = true;
          }
        });


      console.log(this.paperReviewerPair);
      this.canShow = true;
    }
  }

  showReviewers(paper: Paper) {
    this.paperReviewerPair = new PaperReviewerPair(paper.pid);
    this.paperService.getAllReviewersForPaper(paper.pid)
      .subscribe(result => this.reviewers = result);
  }

  ngOnInit(): void {
    this.getPapers();
    this.breakpoint = (window.innerWidth <= 400) ? 1 : 3;


  }

  private getPapers() {
    this.paperService.getAllPapers().subscribe(
      result => {
        this.papers = result;
        console.log(result);
      }
    );
  }

}
