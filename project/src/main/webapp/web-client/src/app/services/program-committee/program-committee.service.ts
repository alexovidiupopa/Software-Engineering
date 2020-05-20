import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {catchError, map} from 'rxjs/operators';
import {ProgramCommittee} from '../../model/program-committee';
import {PcDto} from "../../model/pcdto";
import {UserDto} from "../../model/userdto";

@Injectable({
  providedIn: 'root'
})
export class ProgramCommitteeService {
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  private url = 'http://localhost:8080/api/pc';

  constructor(
    private http: HttpClient) {
  }

  getProgramCommittees(): Observable<PcDto[]> {
    return this.http.get<PcDto[]>(this.url + '/getAllPcMembers', this.httpOptions)
      .pipe(
        map(result => result['pcMember']), // todo fix plural typo
        catchError(this.handleError<PcDto[]>('getProgramCommittees', []))
      );
  }

  updatePCToChair(id: number): Observable<boolean> {
    return this.http.put<boolean>(this.url + '/pcToChair/' + id, {}, this.httpOptions).pipe(
      map(response => Boolean(response['message'])),
      catchError(this.handleError<boolean>('makePCIntoChair'))
    );
  }

  getUserInfo(id: number) : Observable<UserDto>{
    return this.http.get<UserDto>('http://localhost:8080/api/user/'+id, this.httpOptions);
  }

  getProgramCommittee(id: number): Observable<PcDto> {
    return this.http.get<PcDto>(this.url + '/getPcMemberById/' + id, this.httpOptions)
      .pipe(
        map(result => result['pcMember']),
        catchError(this.handleError<ProgramCommittee[]>('getProgramCommittees', []))
      );
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  invitePc(email: string): Observable<any> {
    console.log(email);
    return this.http.post("http://localhost:8080/api/chair/invitePc",
      {"message":email},
      this.httpOptions);
  }
}
