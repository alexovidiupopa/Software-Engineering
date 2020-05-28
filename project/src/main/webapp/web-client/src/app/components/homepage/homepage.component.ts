import {Component, OnInit} from '@angular/core';
import {Ticket} from "../../model/ticket";
import {Router} from '@angular/router';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.scss']
})
export class HomepageComponent implements OnInit {

  constructor(private router: Router) {
  }

  ngOnInit(): void {

    localStorage.setItem('cart', JSON.stringify(new Array<Ticket>()));
  }

  goToAuthorRegister()
  {
    this.router.navigate(["author-register"]);
  }

}
