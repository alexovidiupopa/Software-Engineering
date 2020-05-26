import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/login";

@Component({
  selector: 'app-pc-home',
  templateUrl: './pc-home.component.html',
  styleUrls: ['./pc-home.component.css']
})
export class PcHomeComponent implements OnInit {
  id: number;

  constructor(private router: Router,
              private authenticationService: AuthenticationService) {
    this.id = authenticationService.getCurrentUser().id;
  }

  ngOnInit() {
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }
}
