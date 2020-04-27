import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {catchError, map} from 'rxjs/operators';
import {ProgramCommittee} from '../../model/program-committee';

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

  /** GET heroes from the server */
  getProgramCommittees(): Observable<ProgramCommittee[]> {
    return this.http.get<ProgramCommittee[]>(this.url + '/getAllPcMembers', this.httpOptions)
      .pipe(
        map(result => result['pcMember']), // todo fix plural typo
        catchError(this.handleError<ProgramCommittee[]>('getProgramCommittees', []))
      );
  }

  updatePCToChair(id: number): Observable<boolean> {
    return this.http.put<boolean>(this.url + '/to-chair', {pcid: id}, this.httpOptions).pipe(
      map(response => response['message']),
      catchError(this.handleError<ProgramCommittee>('makePCIntoChair'))
    );
  }

  getProgramCommittee(id: number): Observable<ProgramCommittee> {
    return this.http.post<ProgramCommittee>(this.url + '/getPcMemberById', {pcid: id}, this.httpOptions)
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

}
