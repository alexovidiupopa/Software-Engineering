import {Time} from '@angular/common';
import * as moment from 'moment';

export class Room {
  id: number;
  capacity: number;

  constructor(id: number, capacity: number) {
    this.id = id;
    this.capacity = capacity;
  }
}
