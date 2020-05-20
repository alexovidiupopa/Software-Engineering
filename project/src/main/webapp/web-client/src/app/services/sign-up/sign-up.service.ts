import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {ProgramCommittee} from '../../model/program-committee';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {Author} from '../../model/author';

@Injectable({
  providedIn: 'root'
})
export class SignUpService {

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  private url = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {
  }

  registerPcMember(pcMember: ProgramCommittee): Observable<boolean> {
    return this.http.post<boolean>(this.url + '/pc/signup', pcMember, this.httpOptions).pipe(
      map(response => Boolean(response['message'])));
  }

  registerAuthor(author: Author): Observable<boolean> {
    return this.http.post<boolean>(this.url + '/author/signup', author, this.httpOptions).pipe(
      map(response => Boolean(response['message'])));
  }
}
