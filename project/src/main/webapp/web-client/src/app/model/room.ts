import {Time} from '@angular/common';
import * as moment from 'moment';

export class Room {
  rid: number;
  capacity: number;

  constructor(rid: number, capacity: number) {
    this.rid = rid;
    this.capacity = capacity;
  }
}
