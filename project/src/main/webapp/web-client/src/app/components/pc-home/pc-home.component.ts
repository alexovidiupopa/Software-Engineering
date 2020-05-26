import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/login";

@Component({
  selector: 'app-pc-home',
  templateUrl: './pc-home.component.html',
  styleUrls: ['./pc-home.component.css']
})
export class PcHomeComponent implements OnInit {

  constructor(private router: Router,
              private authenticationService: AuthenticationService) {
  }

  ngOnInit() {
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }
}
