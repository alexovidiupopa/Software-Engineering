import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Sesssion} from '../../model/sesssion';
import {map} from 'rxjs/operators';
import {Paper} from "../../model/paper";


@Injectable({
  providedIn: 'root'
})
export class SessionService {

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  private url = 'http://localhost:8080/api/session';


  constructor(private http: HttpClient) {
  }

  getSessions(): Observable<Sesssion[]> {
    return this.http.get<Sesssion>(this.url + '/getAllSessions', this.httpOptions)
      .pipe(map(result => result['sessions']));
  }

  addSession(cid: number, rid: number, time: string, selectedPapers: Paper[]) {
    console.log(selectedPapers);
      return this.http.post<boolean>(this.url + "/addSession", {
        cid,
        rid,
        time,
        'papers':selectedPapers
      }
      ,this.httpOptions)
        .pipe(
          map(result=>Boolean(result['message']))
        );
  }
}
