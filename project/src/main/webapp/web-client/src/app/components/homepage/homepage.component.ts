import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../../services/login';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.scss']
})
export class HomepageComponent implements OnInit {
  userLoggedIn = false;
  userUrl: string;

  constructor(private authenticationService: AuthenticationService) {
    if(authenticationService.getCurrentUser() == null){
      this.userLoggedIn = false;
    } else {
      this.userLoggedIn = true;
      this.userUrl = authenticationService.getCurrentUser().getHomepageUrl();
    }
  }

  ngOnInit(): void {
  }

}
