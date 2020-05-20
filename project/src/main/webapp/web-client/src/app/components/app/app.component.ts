import {Component} from '@angular/core';
import {User} from '../../model/user';

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
    // private router: Router,
    // private authenticationService: AuthenticationService,
    // private conferenceService: ConferenceService
  ) {}
    // this.authenticationService.currentUser.subscribe(x => {
      // this.currentUser = x;
      // this.homepage = this.authenticationService.getCurrentUser().getUrl();
    // });
  //
  // logout() {
  //   this.authenticationService.logout();
  // }
  //
  // back_to_clients_page() {
  //   const user: User = this.authenticationService.getCurrentUser();
  //   // @ts-ignore
  //
  //   this.router.navigate([user.get_url()]);
  // }
  //
  // navigateToHome() {
  //   this.homepage = this.authenticationService.getCurrentUser().getHomepageUrl();
  //   this.router.navigateByUrl(this.homepage);
  //
  // }
}
