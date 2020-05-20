import { Component, OnInit } from '@angular/core';
import {MatDatepickerInputEvent} from "@angular/material/datepicker";
import {Room} from "../../model/room";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Chair} from "../../model/chair";
import {PaperService} from "../../services/paper/paper.service";
import {RoomService} from "../../services/room/room.service";
import {ActivatedRoute} from "@angular/router";
import {ChairService} from "../../services/chair/chair.service";
import {Paper} from "../../model/paper";
import * as $ from 'jquery';
import {SessionService} from "../../services/session/session.service";
import {AmazingTimePickerService} from "amazing-time-picker";
import * as moment from "moment";
import {Session} from "../../model/session";
import {MatOption} from "@angular/material/core";
import {MatSelectChange} from "@angular/material/select";

@Component({
  selector: 'app-edit-session',
  templateUrl: './edit-session.component.html',
  styleUrls: ['./edit-session.component.css']
})
export class EditSessionComponent implements OnInit {

  rooms: Room[] = [];
  chairs: Chair[] = [];
  id = parseInt(this.route.snapshot.paramMap.get('id'));
  papers: Paper[] = [];
  date: Date
  sessionTime: string;
  selectedOptions: Paper[] = [];
  selectedPapers : Paper[] = [];
  cid: number;
  rid: number;

  constructor( private atp: AmazingTimePickerService,private sessionService: SessionService, private paperService: PaperService, private roomService: RoomService, private chairService: ChairService, private route: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.getAllRooms();
    this.getPapers()
    this.getAllChairs();
  }

  getAllRooms()
  {
    this.roomService.getAllRooms().subscribe( room => this.rooms = room);
  }

  getAllChairs()
  {

    this.chairService.getAllChairs().subscribe( chairs => this.chairs = chairs);
  }

  getPapers()
  {
    this.paperService.getAllPapers().subscribe( papers => this.papers = papers);
  }



  openTime() {
    const amazingTimePicker = this.atp.open({
      theme: 'material-blue'
    });
    amazingTimePicker.afterClose().subscribe(time => {
      this.sessionTime = time;
    });
  }

  selectionChanged($event: any) {
    this.selectedOptions=$event;
    let newPapers = [];
    for (const paper of this.selectedOptions) {
      newPapers.push(paper);
    }
    this.selectedPapers = newPapers;
    console.log(this.selectedPapers);
  }

  createSession() {
    this.sessionService.addSession(this.cid, this.rid, this.sessionTime, this.selectedPapers)
      .subscribe();

  }

  selectChair(event: MatSelectChange) {
    const selectedData = {
      text: (event.source.selected as MatOption).viewValue,
      value: event.source.value
    };
    this.cid = Number(selectedData.value);
    console.log(this.cid);
  }

  selectRoom(event: MatSelectChange) {
    const selectedData = {
      text: (event.source.selected as MatOption).viewValue,
      value: event.source.value
    };
    this.rid = Number(selectedData.value);
    console.log(this.rid);
  }
}
