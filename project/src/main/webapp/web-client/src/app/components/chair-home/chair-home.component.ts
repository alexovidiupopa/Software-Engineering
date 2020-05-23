import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../services/login';
import {ConferenceService} from "../../services/conference/conference.service";

@Component({
  selector: 'app-chair-home',
  templateUrl: './chair-home.component.html',
  styleUrls: ['./chair-home.component.css']
})
export class ChairHomeComponent implements OnInit {
  existsConference:boolean = false;
  constructor(private router: Router,
              private authenticationService: AuthenticationService,
              private conferenceService: ConferenceService
  ) {
  }

  ngOnInit() {
    this.conferenceService.check_if_conference_exists().subscribe((data:boolean)=>{
      this.existsConference = data;
      }
    );
  }



  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }


  create_conference() {
    this.router.navigate(['/create-conference']);
  }
}
