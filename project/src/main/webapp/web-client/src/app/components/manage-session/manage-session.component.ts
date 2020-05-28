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
  loading: boolean = true;


  constructor(private route: ActivatedRoute, private sessionService: SessionService, private paperService: PaperService) {
    this.getSession();
    this.getPapers();
  }

  ngOnInit(): void {

  }


  getPapersForSessionId(id: number): string
  {

    let paperString = "";
    for( let index = 0; index < this.papers.length; index++)
    {
      if (this.papers[index].session == id) {
        paperString += this.papers[index].title
        paperString += ", "
      }
    }
    return paperString.slice(0,-2);
  }

  getSession()
  {
    this.sessionService.getSessions()
      .subscribe( session => this.sessions = session)
  }

  getPapers()
  {
    this.paperService.getAllPapers()
      .subscribe( papers =>
      {
        this.papers = papers
        this.loading = false;
      })
  }

}
