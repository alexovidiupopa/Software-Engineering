import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {catchError, map} from 'rxjs/operators';
import {Ticket} from '../../model/ticket';
import {PaymentData} from '../../model/payment-data';
import * as moment from 'moment';
import {Sesssion} from "../../model/sesssion";

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  private url = 'http://localhost:8080/api/ticket';

  constructor(
    private http: HttpClient) {
  }

  buyTickets(tickets: Ticket[], paymentData: PaymentData): Observable<boolean> {
    let ticketsDto = [];
    for (const ticket of tickets) {
      const sessions = ticket.sessions.map(session => session.sid);
      ticketsDto.push({
        name: ticket.firstName + ' ' + ticket.lastName,
        datePurchased: moment().format('MM/DD/YYYY HH:mm:ss'),
        sessions,
        price: ticket.sessions.reduce((a, b) => a + b.price, 0)
      });
    }
    return this.http.post<boolean>(this.url + '/buy', {
      'tickets':ticketsDto,
      paymentData,
      email: tickets[0].email
    }, this.httpOptions)
      .pipe(
        map(result => Boolean(result['message'])),
        catchError(this.handleError<boolean>('buyTickets'))
      );
  }

  getSessionsWithAvailableSeats(): Observable<Sesssion[]> {
    return this.http.get<Sesssion[]>('http://localhost:8080/api/session/available', this.httpOptions).pipe(
      map(response => response['sessions']),
      catchError(this.handleError<Sesssion[]>('getSessionsWithAvailableSeats'))
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
