import {Component} from '@angular/core';
import {User} from '../../model/user';
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

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
  ) {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }


  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }

  back_to_clients_page() {
    const user: User = this.authenticationService.getCurrentUser();
    // @ts-ignore

    this.router.navigate([user.get_url()]);
  }
}
