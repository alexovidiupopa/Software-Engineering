import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {MessageService} from '../message/message.service';
import {Observable, of} from 'rxjs';
import {catchError, map, tap} from 'rxjs/operators';
import {ProgramCommittee} from './program-committee';

@Injectable({
  providedIn: 'root'
})
export class ProgramCommitteeService {
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  private tempPCs = [new ProgramCommittee('AvramPop', 'pass',
    'avrampop.io', 'Duke University', 'Dani', 'Pop',
    '0771131686', 'daniel.avram.pop@gamil.com', 'Visiting Professor', 0),
    new ProgramCommittee('AlexPopa', 'pass',
      'alex.popa.com', 'Gordon-Cromwell', 'Alex', 'Popa',
      '0758996859', 'alex.popa@gmail.com', 'Ph.D. student', 1),
    new ProgramCommittee('CristiPopBM', 'pass',
      'cristi.bm', 'Harvard', 'Cristi', 'Pop',
      '0745963698', 'cristibm@yahoo.com', 'Undergraduate', 2),
    new ProgramCommittee('CristiStanford', 'pass',
      'standford.edu/~cristi', 'Stanford', 'Cristi', 'Pop',
      '0758888635', 'cristi@stanford.edu', 'Professor', 3)];
  private programCommitteeUrl = 'http://demo4608640.mockable.io/api/pc';  // URL to web api

  constructor(
    private http: HttpClient,
    private messageService: MessageService) {
  }

  /** GET heroes from the server */
  getProgramCommittees(): Observable<ProgramCommittee[]> {
    // return of(this.tempPCs);
    return this.http.get<ProgramCommittee[]>(this.programCommitteeUrl)
      .pipe(
        map(result => result['pcs']),
        tap(res => this.log(res)),
        catchError(this.handleError<ProgramCommittee[]>('getProgramCommittees', []))
      );
  }

  updatePCToChair(id: number): Observable<boolean> {
    const url = `${this.programCommitteeUrl}/to-chair`;

    return this.http.put<boolean>(url, this.httpOptions).pipe(
      map(response => response['success']),
      tap(_ => this.log(`made pc w/ id=${id} to chair`)),
      catchError(this.handleError<ProgramCommittee>('makePCIntoChair'))
    );
  }

  getProgramCommittee(id: number): Observable<ProgramCommittee> {
    return this.http.get<ProgramCommittee[]>(this.programCommitteeUrl)
      .pipe(
        map(result => result['pcs'][id]),
        tap(res => this.log(res)),
        catchError(this.handleError<ProgramCommittee[]>('getProgramCommittees', []))
      );
  }

  //
  // /** POST: add a new hero to the server */
  // addProgramCommittee(pc: ProgramCommittee): Observable<ProgramCommittee> {
  //   return this.http.post<ProgramCommittee>(this.programCommitteeUrl, pc, this.httpOptions).pipe(
  //     tap((newPC: ProgramCommittee) => this.log(`added pc w/ id=${newPC.email}`)),
  //     catchError(this.handleError<ProgramCommittee>('addProgramCommittee'))
  //   );
  // }

  private log(message: string) {
    this.messageService.add(`ProgramCommitteeService: ${message}`);
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

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

}
