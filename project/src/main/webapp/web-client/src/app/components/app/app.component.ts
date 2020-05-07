import {Component} from '@angular/core';
import {User} from '../../model/user';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../services/login';
import {ConferenceService} from '../../services/conference/conference.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  currentUser: User;
  title = 'loginSites';
  homepage: string;

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
    private conferenceService: ConferenceService
  ) {
    this.authenticationService.currentUser.subscribe(x => {
      this.currentUser = x;
      // this.homepage = this.authenticationService.getCurrentUser().getUrl();
    });
  }

  logout() {
    this.authenticationService.logout();
  }

  navigateToHome() {
    this.homepage = this.authenticationService.getCurrentUser().getHomepageUrl();
    this.router.navigateByUrl(this.homepage);
  }
}
