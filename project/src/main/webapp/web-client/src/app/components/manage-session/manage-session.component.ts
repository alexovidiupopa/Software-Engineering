import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {SessionService} from '../../services/session/session.service';
import { Paper } from 'src/app/model/paper';
import {PaperService} from '../../services/paper/paper.service';
import { Sesssion } from 'src/app/model/sesssion';
import { Paperr } from 'src/app/model/paperr';
import {DateAdapter} from '@angular/material/core';

@Component({
  selector: 'app-manage-session',
  templateUrl: './manage-session.component.html',
  styleUrls: ['./manage-session.component.css']
})
export class ManageSessionComponent implements OnInit {


  sessions: Sesssion[];

  papers: Paperr[];
  constructor(private route: ActivatedRoute, private sessionService: SessionService, private paperService: PaperService) {
    this.papers = [new Paperr(1,1,1,"a","a","A","a","a","a")]
    this.sessions = [new Sesssion(1, 1, 1,1), new Sesssion(2, 1, 1,1)]
  }

  ngOnInit(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    //this.getSession();
    //this.getPapers();
  }

  getSession()
  {
    this.sessionService.getSessions().subscribe( session => this.sessions = session)
  }

  getPapers()
  {
    //this.paperService.getAllPapers().subscribe( papers => this.papers = papers)
  }

}
