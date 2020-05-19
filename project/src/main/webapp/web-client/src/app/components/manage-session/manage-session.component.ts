import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {SessionService} from '../../services/session/session.service';
import { Paper } from 'src/app/model/paper';
import {PaperService} from '../../services/paper/paper.service';
import { Sesssion } from 'src/app/model/sesssion';
import { Paperr } from 'src/app/model/paperr';
import {DateAdapter} from '@angular/material/core';
// @ts-ignore
import moment = require('moment');

@Component({
  selector: 'app-manage-session',
  templateUrl: './manage-session.component.html',
  styleUrls: ['./manage-session.component.css']
})
export class ManageSessionComponent implements OnInit {


  sessions: Sesssion[];
  pair: any[]
  papers: Paperr[];


  constructor(private route: ActivatedRoute, private sessionService: SessionService, private paperService: PaperService) {

  }

  ngOnInit(): void {
    this.getSession();
    this.getPapers();
  }


  getPapersForSessionId(id: number): string
  {

    let paperString = "";
    let sessionPapers: Paperr[] = [];
    for( let paper of this.papers.values())
    {
      if (paper.session == id)
        paperString += paper.title
        paperString += ", "
    }

    return paperString;
  }

  getSession()
  {
    this.sessionService.getSessions().subscribe( session => this.sessions = session)
  }

  getPapers()
  {
    this.paperService.getAllPapers().subscribe( papers => this.papers = papers)
  }

}
