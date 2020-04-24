import {Component} from '@angular/core';
import {User} from '../../user';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../services/login';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  currentUser: User;
  title = 'loginSites';
  authorButton = 'none';
  pcButton = 'primary';
  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
  ) {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }

  goToPCRegister() {
    this.pcButton = 'none';
    this.authorButton = 'primary';
    this.router.navigate(['/pc-register']);
  }

  goToAuthorRegister() {
    this.pcButton = 'primary';
    this.authorButton = 'none';
    this.router.navigate(['/author-register']);
  }
  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }

  back_to_clients_page() {
    const user: User = this.authenticationService.get_current_user();
    // @ts-ignore

    this.router.navigate([user.get_url()]);
  }
}
