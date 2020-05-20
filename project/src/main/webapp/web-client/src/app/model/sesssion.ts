import * as moment from 'moment';

export class Sesssion {
  sid: number;
  time: moment.Moment;
  price: number = Math.random() * (50 - 5) + 5 ;
  room: number;
  supervisor: number;
  constructor(id: number, time: moment.Moment, room: number, supervisor: number) {
    this.time = time;
    this.room = room;
    this.sid = id;
    this.supervisor = supervisor;
  }

}
