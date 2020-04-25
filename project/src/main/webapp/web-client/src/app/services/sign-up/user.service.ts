import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError, map, retry, tap} from 'rxjs/operators';
import {ProgramCommittee} from './program-comittee';
import {Author} from './author';


@Injectable({ providedIn: 'root' })
export class UserService {

  private url = 'https://demo4608640.mockable.io/api/signup/author';
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient) { }

  registerPcMember(pcMember: ProgramCommittee): Observable<boolean>
  {
    return this.http.post<boolean>(this.url, pcMember, this.httpOptions).pipe(
      map(response => response['success']));
  }

  registerAuthor(author: Author): Observable<boolean> {
    return this.http.post<boolean>(this.url, author, this.httpOptions).pipe(
      map(response => response['success']));
  }

}

