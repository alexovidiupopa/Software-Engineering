import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {MessageService} from '../message/message.service';
import {Observable, of} from 'rxjs';
import {Hero} from '../hero/hero';
import {catchError, tap} from 'rxjs/operators';
import {Todo} from '../../todo';
import {ProgramCommittee} from './program-committee';

@Injectable({
  providedIn: 'root'
})
export class ProgramCommitteeService {
  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }
  private programCommitteeUrl = 'api/pc';  // URL to web api
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  /** GET heroes from the server */
  getProgramCommittees(): Observable<ProgramCommittee[]> {
    const tempPCs = [new ProgramCommittee('username', 'pass',
      'website', 'aaa', 'Dani', 'Pop',
      '00', 'dani@pop.avram', 'ee', 1)];
    return of(tempPCs);
    // return this.http.get<ProgramCommittee[]>(this.programCommitteeUrl)
    //   .pipe(
    //     tap(_ => this.log('fetched PCs')),
    //     catchError(this.handleError<ProgramCommittee[]>('getProgramCommittees', []))
    //   );
  }

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

  /** POST: add a new hero to the server */
  addProgramCommittee(pc: ProgramCommittee): Observable<ProgramCommittee> {
    return this.http.post<ProgramCommittee>(this.programCommitteeUrl, pc, this.httpOptions).pipe(
      tap((newPC: ProgramCommittee) => this.log(`added pc w/ id=${newPC.email}`)),
      catchError(this.handleError<ProgramCommittee>('addProgramCommittee'))
    );
  }

  updatePCToChair(id: number): Observable<ProgramCommittee> {
    const url = `${this.programCommitteeUrl}/to-chair/${id}`;

    return this.http.put<ProgramCommittee>(url, id, this.httpOptions).pipe(
      tap((updatedPC: ProgramCommittee) => this.log(`made pc w/ name=${updatedPC.firstName + updatedPC.lastName} to chair`)),
      catchError(this.handleError<ProgramCommittee>('makePCIntoChair'))
    );
  }
}
