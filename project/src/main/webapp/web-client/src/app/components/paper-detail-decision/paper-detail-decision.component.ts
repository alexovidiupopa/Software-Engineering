import { Component, OnInit } from '@angular/core';
import {PaperService} from "../../services/paper/paper.service";
import {Paper} from "../../model/paper";
import {AuthenticationService} from "../../services/login";
import {User} from "../../model/user";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {DomSanitizer} from "@angular/platform-browser";
import {ProgramCommitteeService} from "../../services/program-committee/program-committee.service";
import {ProgramCommittee} from "../../model/program-committee";

@Component({
  selector: 'app-paper-detail-decision',
  templateUrl: './paper-detail-decision.component.html',
  styleUrls: ['./paper-detail-decision.component.css']
})
export class PaperDetailDecisionComponent implements OnInit {

  user: User;
  paper: Paper;
  id = +this.route.snapshot.paramMap.get('id');
  pcMembers: ProgramCommittee[] = [];
  reviewersMap: Map<number, boolean>;
  constructor(
    private route: ActivatedRoute,
    private paperService: PaperService,
    private location: Location,
    private router: Router,
    private sanitizer: DomSanitizer,
    private authenticationService: AuthenticationService,
    private pcService: ProgramCommitteeService
  ) {
    //this.user = authenticationService.getCurrentUser();
    this.reviewersMap = new Map<number, boolean>();
  }

  ngOnInit(): void {
    this.getPaper();
    this.getPcs();
  }


  private getPaper() {
   //this.paper = new Paper("name1","authors",1,"keywords",null,null,1);
     this.paperService.getPaperById(this.id)
      .subscribe(paper => {
        this.paper = paper;
      });
  }

  private getPcs(){
    //this.pcMembers.push(new ProgramCommittee(null, null, null, "UBB", "alex","popa",null, null, null, 1));
    this.pcService.getProgramCommittees()
      .subscribe(pcs=>{
        this.pcMembers = pcs;
      });
    this.pcMembers.forEach(pc=>this.reviewersMap.set(pc.id,false));
  }

  acceptPaper() {
    this.paperService.accept(this.paper.id);
  }

  rejectPaper() {
    this.paperService.reject(this.paper.id);
  }

  sendPaperBack() {
    let reviewers = [];
    this.reviewersMap.forEach((value,key)=>{
      if (value==true){
        reviewers.push(key);
      }
    });
    if (reviewers.length!=2 && reviewers.length!=3){
      window.alert("please choose 2 or 3 reviewers!");
      return;
    }
    this.paperService.reassign(this.paper.id, reviewers);
  }

  selectReviewer(id: number) {
    this.reviewersMap.set(id,!this.reviewersMap.get(id));
  }
}
