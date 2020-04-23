import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {map} from 'rxjs/operators';
// import { environment } from 'src/environments/environment';
import {User} from '../../user';


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
  private succes: boolean;
  private response: request = new request();
  private currentUserSubject: BehaviorSubject<any>;
  private url = 'https://demo4608640.mockable.io/api/login';
  private httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})

  };

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<any>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
    this.user = new User('firstname', 'lastname', 'username', 'password', this.response.type, 'idk');

  }

  public get currentUserValue() {
    return this.currentUserSubject.value;
  }

  public get_current_user() {
    return this.user;
  }


  public assign_an_identification_token_to_user(user: User) {
    let token: string = user.makeid();
    while (localStorage.getItem(token)) {
      token = user.makeid();
    }
    user.set_token(token);
  }

  login(username, password) {

    return this.http.post<request>(this.url, {username, password}).pipe(
      map(user => {

        this.response.success = user.success;
        // tslint:disable-next-line:triple-equals
        if (this.response.success == true) {
          this.response.type = user.type;
          const loggedUser: User = new User('firstname', 'lastname', 'username', 'password', this.response.type, 'idk');
          // tslint:disable-next-line:triple-equals
          if (loggedUser.type == 'chair') {
            loggedUser.url = 'chair-home';
          } else if (loggedUser.type == 'pc') {
            loggedUser.url = 'pc-home';
          } else if (loggedUser.type == 'author') {
            loggedUser.url = 'author-home';
          }
          this.assign_an_identification_token_to_user(loggedUser);
          this.user = loggedUser;
          localStorage.setItem(this.user.get_token(), JSON.stringify(this.user));
          this.currentUserSubject.next(user);

          return this.user;

        } else {
          return Error('username or passowrd incorrect');
        }
      })
    );


    return this.http.post<any>(this.url, {username, password})
      .pipe(map(user => {

        // store user details and jwt token in local storage to keep user logged in between page refreshes
        localStorage.setItem('currentUser', JSON.stringify(user));
        this.currentUserSubject.next(user);
        return user;
      }));


    /*return this.http.post<any>(`${environment.apiUrl}/users/authenticate`, { username, password })
        .pipe(map(user => {

            // store user details and jwt token in local storage to keep user logged in between page refreshes
            localStorage.setItem('currentUser', JSON.stringify(user));
            this.currentUserSubject.next(user);
            return user;
        }));*/
  }

  logout() {
    // remove user from local storage and set current user to null
    localStorage.removeItem(this.user.get_token());
    this.currentUserSubject.next(null);
  }
}








