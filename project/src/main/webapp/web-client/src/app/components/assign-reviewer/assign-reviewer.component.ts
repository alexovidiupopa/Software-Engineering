import {Component, OnInit} from '@angular/core';
import {Paper} from "../../model/paper";
import {PaperService} from "../../services/paper/paper.service";
import {Reviewer} from "../../model/Reviewer";
import {MatCheckbox} from "@angular/material/checkbox";
import {PaperReviewrPair} from "../../model/PaperReviewrPair";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Component({
  selector: 'app-assign-reviewer',
  templateUrl: './assign-reviewer.component.html',
  styleUrls: ['./assign-reviewer.component.css']
})
export class AssignReviewerComponent implements OnInit {

  papers: Paper[] = [];
  reviewrs: Reviewer[] = [];
  pcs = new Map<number, Reviewer[]>(); //this is just to test the page
  paperReviewerPair :PaperReviewrPair = null;
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  url:string = 'http://localhost:8080/api/paper';
  public breakpoint: number;
  constructor(private paperService: PaperService , private http: HttpClient ) {
  }
  private getPapers() {
    this.paperService.getAllPapers().subscribe(
      result => {
        this.papers = result; // fixme: not sure but i think i have to subscribe each paper individually from the result array
        console.log(result);
      }
    );
  }

  add_remove_reviewer(event, checkbox: MatCheckbox){

    if(checkbox.checked) {
      for(const reviewer of this.reviewrs){
        if (reviewer.id === Number(checkbox.value)){
          this.paperReviewerPair.add_reviwer(reviewer.id);
        }
      }

    }

    else if(!checkbox.checked){
      this.paperReviewerPair.remove_reviewer(Number(checkbox.value));
    }
  }
  // send the paper and its reviewers to backend
  canShow: boolean = false;
  assign_reviwers_to_paper(){

    if(this.paperReviewerPair.can_send() == 1){
      alert("you can add at most 3 reviwers");
    }
    else if(this.paperReviewerPair.can_send() == -1){
      alert("you need to have at least 2 reviwers");
    }
    else {
      const url = this.url + '/assignReviewers';
     // this.http.post(url, JSON.stringify(this.paperReviewerPair), this.httpOptions); fixme : to be uncommented when backend is implemented
      console.log(this.paperReviewerPair);
      this.canShow = true;
    }
  }

  showReviewrs(paper:Paper){
    this.paperReviewerPair = new PaperReviewrPair(paper.id);
    //this.paperService.getAllReviewersForPaper(paper.id).subscribe(
    // result=>this.reviewrs = result["reviewers"]); fixme: to be uncommented when the backend call is implemented

    //down here is an implementation to test the page. To be  deleted when the backend is implemented
    this.canShow = false;
     this.reviewrs = this.pcs.get(paper.id);
  }

  ngOnInit(): void {
  // this.getPapers(); fixme : to be uncommented when the backend call is implemented
    this.breakpoint = (window.innerWidth <= 400) ? 1 : 3;
    //down here is an implementation to test the page. To be  deleted when the backend is implemented
    console.log("aici");
    const paper1 : Paper = new Paper("title1","author1", 1,"keyword1");
    paper1.id = 1;
    const paper2 : Paper = new Paper("title2","author2", 2,"keyword2");
    paper2.id = 2;
    const paper3 : Paper = new Paper("title3","author3", 3,"keyword3");
    paper3.id = 3;
    const paper4 : Paper = new Paper("title4","author4", 4,"keyword4");
    paper4.id = 4;
    this.papers.push(paper1);
    this.papers.push(paper2);
    this.papers.push(paper3);
    this.papers.push(paper4);

    const reviewer1 : Reviewer = new Reviewer(1,"Pop","Cristian");
    const reviewer2 : Reviewer = new Reviewer(2,"Pop2","Cristian2");
    const reviewer3 : Reviewer = new Reviewer(3,"Popa","Alex");
    const reviewer4 : Reviewer = new Reviewer(4,"Pop","Vlad");
    const reviewer5 : Reviewer = new Reviewer(5,"Corpodean","Darius");
    const reviewer6 : Reviewer = new Reviewer(6,"Mermezan","Mihai");
    const reviewer7 : Reviewer = new Reviewer(7,"Dragus","Alexandru");
    const reviewer8 : Reviewer = new Reviewer(8,"Pop","Daniel");

    this.pcs.set(1, [reviewer1, reviewer2, reviewer3, reviewer4]);
    this.pcs.set(2, [reviewer3, reviewer1, reviewer5, reviewer6]);
    this.pcs.set(3, [reviewer2, reviewer1, reviewer7, reviewer4]);
    this.pcs.set(4, [reviewer8, reviewer2, reviewer5, reviewer7]);





  }

}
