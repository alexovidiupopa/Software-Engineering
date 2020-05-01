import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {User} from '../../model/user';


// tslint:disable-next-line:class-name
class request {
  success: boolean;
  type: string;

  constructor() {
  }
}


@Injectable({providedIn: 'root'})
export class AuthenticationService {
  public currentUser: Observable<any>;
  public user: User;
  private response: request = new request();
  private currentUserSubject: BehaviorSubject<any>;
  private url = 'http://localhost:8080/api';
  private httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})

  };

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<any>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue() {
    return this.currentUserSubject.value;
  }

  public getCurrentUser() {
    console.log(this.user);
    return this.user;
  }

  public assignAnIdentificationTokenToUser(user: User) {
    let token: string = user.makeid();
    while (localStorage.getItem(token)) {
      token = user.makeid();
    }
    user.setToken(token);
  }

  login(username, password) {
    return this.http.post<request>(this.url + '/login', {username, password}).pipe(
      map(user => {
        this.response.success = user.success;
        if (this.response.success === true) {
          this.response.type = user.type;
          this.user = new User('firstname', 'lastname', 'username', 'password', this.response.type, 'idk', 1);
          if (this.user.type === 'chair') {
            this.user.url = 'chair-home';
          } else if (this.user.type === 'pc') {
            this.user.url = 'pc-home';
          } else if (this.user.type === 'author') {
            this.user.url = 'author-home';
          }
          this.assignAnIdentificationTokenToUser(this.user);
          localStorage.setItem(this.user.getToken(), JSON.stringify(this.user));
          this.currentUserSubject.next(user);
          return this.user;
        } else {
          return Error('username or password incorrect');
        }
      })
    );
  }

  logout() {
    localStorage.removeItem(this.user.getToken());
    this.currentUserSubject.next(null);
  }
}








