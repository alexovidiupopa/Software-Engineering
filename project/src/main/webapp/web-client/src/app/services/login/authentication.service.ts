import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {BehaviorSubject, Observable, of} from 'rxjs';
import {map} from 'rxjs/operators';
import {User} from '../../model/user';
import {LoginUserBody} from '../../model/LoginUserBody';
import * as jwt_decode from 'jwt-decode';
import {ProgramCommitteeService} from "../program-committee/program-committee.service";

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

  constructor(private http: HttpClient, private pcService : ProgramCommitteeService) {
    //this.currentUserSubject = new BehaviorSubject<any>(JSON.parse(localStorage.getItem('currentUser')));
   // this.currentUser = of(this.getCurrentUser());
  }

  public get currentUserValue() {
    return this.currentUserSubject.value;
  }

  public getCurrentUser()  {
    console.log(this.user);
    //return this.currentUserSubject.value;
    const user = JSON.parse(localStorage.getItem('currentUser'));
    if (user===null){
      return null;
    }
   // return user;
    return new User(user['firstName'],user['lastName'], user['username'], user['password'], user['type'],user['url'],<number>user['id']);
    //return JSON.parse(localStorage.getItem('currentUser')) as User;
  }

  public assignAnIdentificationTokenToUser(user: User) {
    let token: string = user.makeId();
    while (localStorage.getItem(token)) {
      token = user.makeId();
    }
    user.setToken(token);
  }

  public getUser(): User{
    console.log(this.user);
    return this.user;
  }

  login(username, password) : Observable<User>  {
    return this.http.post<request>(this.url + '/login', JSON.stringify(new LoginUserBody(username, password)), {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    }).pipe(
      map(response => {
        const decodedToken = this.decode_JWT_token(response['message']);
        console.log(decodedToken);
        this.response.success = decodedToken['success'];
        //this.urlCopy = decodedToken['url'];
        if (this.response.success === true) {
          this.response.type = decodedToken['type'];
          this.user = new User(decodedToken['firstname'],
                               decodedToken['lastname'],
            decodedToken['username'],
            null,
            this.response.type,
            decodedToken['url'],
            Number(decodedToken['uid'])
                              );
          localStorage.setItem('currentUser', JSON.stringify(this.user));

           return (this.user);
        } else {
          throw new Error('username or password incorrect');
        }
      })
    );
  }

  logout() {
    localStorage.removeItem('currentUser');
    //this.currentUserSubject.next(null);
    //this.currentUserSubject = JSON.parse(localStorage.getItem('currentUser'));
  }

  private decode_JWT_token(token: any) {
    return jwt_decode(token);
  }
}








