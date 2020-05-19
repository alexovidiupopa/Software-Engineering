import {Time} from '@angular/common';
import * as moment from 'moment';

export class Sesssion {
  id: number;
  time: moment.Moment;
  room: number;
  supervisor: number;
  constructor(id: number, time: moment.Moment, room: number, supervisor: number) {
    this.time = time;
    this.room = room;
    this.id = id;
    this.supervisor = supervisor;
  }

}
