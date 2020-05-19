import {Component, OnInit} from '@angular/core';
import {PaperService} from '../../services/paper/paper.service';
import {Reviewer} from '../../model/Reviewer';
import {MatCheckbox} from '@angular/material/checkbox';
import {PaperReviewerPair} from '../../model/PaperReviewerPair';
import {HttpClient, HttpHeaders} from '@angular/common/http';
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
    this.paperService.getAllReviewersForPaper(paper.pid).subscribe(result => this.reviewers = result);
  }

  ngOnInit(): void {
    this.getPapers();
    this.breakpoint = (window.innerWidth <= 400) ? 1 : 3;
    // // todo down here is an implementation to test the page. To be  deleted when the backend is implemented
    // const paper1: Paper = new Paper('title1', 'author1', 1, 'keyword1');
    // paper1.id = 1;
    // const paper2: Paper = new Paper('title2', 'author2', 2, 'keyword2');
    // paper2.id = 2;
    // const paper3: Paper = new Paper('title3', 'author3', 3, 'keyword3');
    // paper3.id = 3;
    // const paper4: Paper = new Paper('title4', 'author4', 4, 'keyword4');
    // paper4.id = 4;
    // this.papers.push(paper1);
    // this.papers.push(paper2);
    // this.papers.push(paper3);
    // this.papers.push(paper4);
    //
    // const reviewer1: Reviewer = new Reviewer(1, 'Pop', 'Cristian');
    // const reviewer2: Reviewer = new Reviewer(2, 'Pop2', 'Cristian2');
    // const reviewer3: Reviewer = new Reviewer(3, 'Popa', 'Alex');
    // const reviewer4: Reviewer = new Reviewer(4, 'Pop', 'Vlad');
    // const reviewer5: Reviewer = new Reviewer(5, 'Corpodean', 'Darius');
    // const reviewer6: Reviewer = new Reviewer(6, 'Mermezan', 'Mihai');
    // const reviewer7: Reviewer = new Reviewer(7, 'Dragus', 'Alexandru');
    // const reviewer8: Reviewer = new Reviewer(8, 'Pop', 'Daniel');
    //
    // this.pcs.set(1, [reviewer1, reviewer2, reviewer3, reviewer4]);
    // this.pcs.set(2, [reviewer3, reviewer1, reviewer5, reviewer6]);
    // this.pcs.set(3, [reviewer2, reviewer1, reviewer7, reviewer4]);
    // this.pcs.set(4, [reviewer8, reviewer2, reviewer5, reviewer7]);


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
