import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../services/login';

@Component({
  selector: 'app-chair-home',
  templateUrl: './chair-home.component.html',
  styleUrls: ['./chair-home.component.css']
})
export class ChairHomeComponent implements OnInit {

  constructor(private router: Router,
              private authenticationService: AuthenticationService
  ) {
  }

  ngOnInit() {
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }


  create_conference() {
    this.router.navigate(['/create-conference']);
  }
}
