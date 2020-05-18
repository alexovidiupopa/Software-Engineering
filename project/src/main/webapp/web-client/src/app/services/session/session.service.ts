import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Sesssion} from '../../model/sesssion';
import {map} from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class SessionService {

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  private url = 'http://localhost:8080/api/session';


  constructor(private http: HttpClient) {}

  getSessions(): Observable<Sesssion[]> {
      return this.http.get<any>(this.url, this.httpOptions).pipe( map(result => result["papers"]) );
  }
}
