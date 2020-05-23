import {Component, OnInit} from '@angular/core';
import {Room} from "../../model/room";
import {Chair} from "../../model/chair";
import {PaperService} from "../../services/paper/paper.service";
import {RoomService} from "../../services/room/room.service";
import {ActivatedRoute} from "@angular/router";
import {ChairService} from "../../services/chair/chair.service";
import {Paper} from "../../model/paper";
import {SessionService} from "../../services/session/session.service";
import {AmazingTimePickerService} from "amazing-time-picker";
import {MatOption} from "@angular/material/core";
import {MatSelectChange} from "@angular/material/select";
import {UserDto} from '../../model/userdto';

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
  chairUsers: UserDto[] = [];

  constructor( private atp: AmazingTimePickerService,private sessionService: SessionService, private paperService: PaperService, private roomService: RoomService, private chairService: ChairService, private route: ActivatedRoute) {
    this.getAllRooms();
    this.getPapers()
    this.getAllChairs();
  }

  ngOnInit(): void {

  }

  getAllRooms()
  {
    this.roomService.getAllRooms().subscribe( room => this.rooms = room);
  }

  getAllChairs()
  {
    this.chairService.getAllChairs().subscribe( chairs => {
      this.chairs = chairs;
      this.getNameForChairId();
    });
  }

  getPapers()
  {
    this.paperService.getAllPapers().subscribe( papers => this.papers = papers);
  }

  getNameForChairId()
  {
    for (let chairr of this.chairs)
    {
      let chair: UserDto;
      this.chairService.getNameForChairId(chairr.uid).subscribe(n => chair = n)
      console.log(chair.lastname)
      this.chairUsers.push(chair)
    }
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
