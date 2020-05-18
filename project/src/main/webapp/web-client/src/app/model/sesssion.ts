import {Time} from '@angular/common';

export class Sesssion {
  id: number;
  time: number;
  room: number;
  supervisor: number;
  constructor(id: number, time: number, room: number, supervisor: number) {
    this.time = time;
    this.room = room;
    this.id = id;
    this.supervisor = supervisor;
  }

}
