import {Component, OnInit} from '@angular/core';
import {Ticket} from '../../model/ticket';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.scss']
})
export class HomepageComponent implements OnInit {

  constructor() {
  }

  ngOnInit(): void {
    localStorage.setItem('cart', JSON.stringify(new Array<Ticket>()));
  }

}
