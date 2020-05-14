import {Component, OnInit} from '@angular/core';
import {User} from '../../model/user';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../services/login';
import {ConferenceService} from '../../services/conference/conference.service';
import {UserType} from '../../model/userType';
import {ConferencePhase} from '../../model/conferencePhase';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  currentUser: User;
  title = 'loginSites';
  homepage: string;
  private currentUserType: UserType;
  private currentPhase: ConferencePhase;

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
    private conferenceService: ConferenceService
  ) {
    this.currentUser = this.authenticationService.getCurrentUser();
    this.setUserType();
    this.setConferencePhase();
    this.homepage = this.authenticationService.getCurrentUser().getHomepageUrl();
  }

  private setUserType() {
    if (this.currentUser.type === 'chair') {
      this.currentUserType = UserType.CHAIR;
    } else if (this.currentUser.type === 'author') {
      this.currentUserType = UserType.AUTHOR;
    } else {
      this.currentUserType = UserType.PC;
    }
    // this.currentUserType = UserType.AUTHOR;
  }

  isAuthor() {
    return this.currentUserType === UserType.AUTHOR;
    return true;
  }

  isChair() {
    return this.currentUserType === UserType.CHAIR;
    return true;

  }

  isPC() {
    return this.currentUserType === UserType.PC;
    return true;

  }

  ngOnInit() {
  }

  logout() {
    this.authenticationService.logout();
  }

  navigateToHome() {
    this.homepage = this.authenticationService.getCurrentUser().getHomepageUrl();
    this.router.navigateByUrl(this.homepage);
  }

  private setConferencePhase() {
    this.conferenceService.getCurrentPhase().subscribe(result => {
      if (result === 'preliminary') {
        this.currentPhase = ConferencePhase.PRELIMINARY;
      } else if (result === 'first') {
        this.currentPhase = ConferencePhase.FIRST;
      } else if (result === 'second') {
        this.currentPhase = ConferencePhase.SECOND;
      } else {
        this.currentPhase = ConferencePhase.THIRD;
      }
    });
    // this.currentPhase = ConferencePhase.FIRST;
  }

  isPreliminaryPhase() {
    return this.currentPhase === ConferencePhase.PRELIMINARY;
  }

  isFirstPhase() {
    return this.currentPhase === ConferencePhase.FIRST;
    return true;

  }

  isSecondPhase() {
    return this.currentPhase === ConferencePhase.SECOND;
    return true;

  }

  isThirdPhase() {
    return this.currentPhase === ConferencePhase.THIRD;
    return true;

  }
}
