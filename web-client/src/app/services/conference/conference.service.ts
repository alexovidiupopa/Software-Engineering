import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {MessageService} from '../message/message.service';
import {Observable, of} from 'rxjs';
import {Hero} from '../hero/hero';
import {catchError, tap} from 'rxjs/operators';
import {Todo} from '../../todo';
import {Conference} from './conference';

@Injectable({
  providedIn: 'root'
})
@Injectable({ providedIn: 'root' })
export class ConferenceService {
  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }
  private conferenceUrl = 'api/conference';  // URL to web api
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  private log(message: string) {
    this.messageService.add(`ConferenceService: ${message}`);
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
  addConference(conference: Conference): Observable<Conference> {
    const url = `${this.conferenceUrl}/create`;

    return this.http.post<Conference>(url, conference, this.httpOptions).pipe(
      tap((newConference: Conference) => this.log(`added conference w/ name=${conference.conferenceName}`)),
      catchError(this.handleError<Conference>('addConference'))
    );
  }

}
