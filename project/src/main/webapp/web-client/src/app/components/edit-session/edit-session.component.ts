import { Component, OnInit } from '@angular/core';
import {FormGroup} from '@angular/forms';
import {RoomService} from '../../services/room/room.service';
import { Room } from 'src/app/model/room';
import {Chair} from '../../model/chair';
import {ChairService} from '../../services/chair/chair.service';
import {ActivatedRoute, Route} from '@angular/router';
import {Paperr} from '../../model/paperr';
import {PaperService} from '../../services/paper/paper.service';
import {Moment} from 'moment';
import * as moment from 'moment';
import {MatDatepickerInputEvent} from '@angular/material/datepicker';



@Component({
  selector: 'app-edit-session',
  templateUrl: './edit-session.component.html',
  styleUrls: ['./edit-session.component.css']
})
export class EditSessionComponent implements OnInit {

  myForm: FormGroup;
  rooms: Room[] = [];
  chairs: Chair[] = [];
  id = parseInt(this.route.snapshot.paramMap.get('id'));
  papers: Paperr[] = [];
  date: Date

  constructor( private paperService: PaperService, private roomService: RoomService, private chairService: ChairService, private route: ActivatedRoute) {

  }

  ngOnInit(): void {
    //this.getAllRooms();
    // this.getPapers()
    // this.getAllChairs();
    this.papers.push(new Paperr(1, 1, "a","a","a","a","a","a"), new Paperr(2, 1, "b","a","a","a","a","a"), new Paperr(2, 2, "b","a","a","a","a","a"))
    this.chairs.push(new Chair(1,1), new Chair(2,1), new Chair(3,1));
    this.rooms.push(new Room(1,1), new Room(2,1), new Room(3,1), new Room(4,1), new Room(5,1))
  }

  getAllRooms()
  {
    this.roomService.getAllRooms().subscribe( room => this.rooms = room)
  }

  getAllChairs()
  {

    this.chairService.getAllChairs().subscribe( chairs => this.chairs = chairs)
  }

  getPapers()
  {
    this.paperService.getAllPapers().subscribe( papers => this.papers = papers)
  }

  getPapersForSessionId(): Paperr[]
  {
    let sessionPapers: Paperr[] = [];
    for( let paper of this.papers.values())
    {
      if (paper.session == this.id)
        sessionPapers.push(paper)
    }
    return sessionPapers;
  }

  removePaper(id: number)
  {
    for ( let index = 0; index < this.papers.length; index += 1)
    {
      let paper: Paperr = this.papers[index];
      console.log(paper)
      if ( paper.pid === id) {
        this.papers.splice(index, 1)
        index -= 1;
      }
    }
    //this.paperService.deletePaperById(id)
  }

  getAvailablePapers()
  {
    //fixme: doesn `t work
    let availablePapers: Paperr[] = [new Paperr(1, 1, "a","a","a","a","a","a")];
    let sessionPapers = this.getPapersForSessionId();
    for ( let paper of sessionPapers )
    {
      let ok = 0;
      for (let paper2 of this.papers)
          if (paper.pid == paper2.pid)
            ok = 1
      if (ok == 0)
      {
        availablePapers.push(paper)
      }
    }

    return availablePapers
  }

  addPaper()
  {
    let paper = this.myForm.get("paperAdd").value
    this.papers.push(paper)
    this.paperService.addPaper(paper);
  }


  setDate(event: MatDatepickerInputEvent<Date>) {
    this.date = event.value;
  }

  submit()
  {
      let cid = this.myForm.get("chairs").value
      let rid = this.myForm.get("rooms").value
    console.log(cid,rid,this.date)
  }

}
