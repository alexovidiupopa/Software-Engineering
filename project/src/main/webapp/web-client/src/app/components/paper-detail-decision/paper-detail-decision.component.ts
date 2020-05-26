import {Component, OnInit} from '@angular/core';
import {PaperService} from '../../services/paper/paper.service';
import {Paper} from '../../model/paper';
import {ActivatedRoute, Router} from '@angular/router';
import {Location} from '@angular/common';
import {DomSanitizer} from '@angular/platform-browser';
import {ProgramCommitteeService} from '../../services/program-committee/program-committee.service';
import {PcDto} from "../../model/pcdto";
import {UserDto} from "../../model/userdto";

@Component({
  selector: 'app-paper-detail-decision',
  templateUrl: './paper-detail-decision.component.html',
  styleUrls: ['./paper-detail-decision.component.css']
})
export class PaperDetailDecisionComponent implements OnInit {

  paper: Paper;
  id = +this.route.snapshot.paramMap.get('id');
  pcMembers: PcDto[] = [];
  pcMembersInfo: UserDto[] = [];
  reviewersMap: Map<number, boolean>;

  constructor(
    private route: ActivatedRoute,
    private paperService: PaperService,
    private location: Location,
    private router: Router,
    private sanitizer: DomSanitizer,
    private pcService: ProgramCommitteeService
  ) {
    this.reviewersMap = new Map<number, boolean>();
  }

  ngOnInit(): void {
    this.getPaper();
    this.getPcs();
  }

  acceptPaper() {
    this.paperService.acceptPaper(this.paper.pid)
      .subscribe();
  }

  rejectPaper() {
    this.paperService.rejectPaper(this.paper.pid)
      .subscribe();
  }

  sendPaperBack() {
    let reviewers = [];
    this.reviewersMap.forEach((value, key) => {
      if (value === true) {
        reviewers.push(key);
      }
    });
    if (reviewers.length !== 2 && reviewers.length !== 3) {
      window.alert('please choose 2 or 3 reviewers!');
      return;
    }
    this.paperService.reassignPaper(this.paper.pid, reviewers)
      .subscribe();
  }

  selectReviewer(id: number) {
    this.reviewersMap.set(id, !this.reviewersMap.get(id));
  }

  private getPaper() {
    this.paperService.getPaperById(this.id)
      .subscribe(paper => {
        this.paper = paper;
      });
  }

  private getPcs() {
    this.pcService.getProgramCommittees()
      .subscribe(pcs => {
        this.pcMembers = pcs;
        for (const pc of pcs){
          this.pcService.getUserInfo(pc.uid)
            .subscribe(person=>this.pcMembersInfo.push(person));
        }
      });
    this.pcMembers.forEach(pc => this.reviewersMap.set(pc.pcid, false));
  }
}
