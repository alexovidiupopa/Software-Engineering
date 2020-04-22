import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {MessageService} from '../message/message.service';
import {Observable, of} from 'rxjs';
import {catchError, map} from 'rxjs/operators';
import {Conference} from './conference';

@Injectable({
  providedIn: 'root'
})
@Injectable({providedIn: 'root'})
export class ConferenceService {


  //private conferenceUrl = 'http://demo4608640.mockable.io/api/conference';  // URL to web api
  private conferenceUrl = 'http://localhost:8080/api';  // URL to web api

  constructor(
    private http: HttpClient,
    private messageService: MessageService) {
  }
  addConference(conference: Conference): Observable<boolean> {
    //const url = `${this.conferenceUrl}/create`;
    const url = "http://localhost:8080/api/create";
    const conferenceFormatted = {
      conferenceName: conference.conferenceName,
      /*preliminaryPhaseDeadline: conference.preliminaryPhaseDeadline.format('MM/DD/YYYY HH:mm:ss'),
      firstPhaseDeadline: conference.firstPhaseDeadline.format('MM/DD/YYYY HH:mm:ss'),
      secondPhaseDeadline: conference.secondPhaseDeadline.format('MM/DD/YYYY HH:mm:ss'),
      thirdPhaseDeadline: conference.thirdPhaseDeadline.format('MM/DD/YYYY HH:mm:ss')*/
    };
    console.log(conferenceFormatted);

    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*'});
    let options = { headers: headers };

    return this.http.post<boolean>(url, conferenceFormatted, options).pipe(
      map(response => response['success']),
      // tap((newConference: Conference) => this.log(`added conference w/ name=${conference.conferenceName}`)),
      catchError(this.handleError<Conference>('addConference'))
    );
  }

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

}
