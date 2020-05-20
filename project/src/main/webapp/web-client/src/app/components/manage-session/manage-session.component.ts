import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {SessionService} from '../../services/session/session.service';
import {PaperService} from '../../services/paper/paper.service';
import {Sesssion} from 'src/app/model/sesssion';
import {Paper} from 'src/app/model/paper';

@Component({
  selector: 'app-manage-session',
  templateUrl: './manage-session.component.html',
  styleUrls: ['./manage-session.component.css']
})
export class ManageSessionComponent implements OnInit {


  sessions: Sesssion[];
  pair: any[]
  papers: Paper[];


  constructor(private route: ActivatedRoute, private sessionService: SessionService, private paperService: PaperService) {

  }

  ngOnInit(): void {
    this.getSession();
    this.getPapers();
  }


  getPapersForSessionId(id: number): string
  {

    let paperString = "";
    let sessionPapers: Paper[] = [];
    for( let paper of this.papers.values())
    {
      if (paper.sessionId == id)
        paperString += paper.title
      paperString += ", "
    }

    return paperString;
  }

  getSession()
  {
    this.sessionService.getSessions()
      .subscribe( session => this.sessions = session)
  }

  getPapers()
  {
    this.paperService.getAllPapers()
      .subscribe( papers => this.papers = papers)
  }

}
