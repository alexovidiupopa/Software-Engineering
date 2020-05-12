import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {catchError, map} from 'rxjs/operators';
import {Ticket} from '../../model/ticket';
import {PaymentData} from '../../model/payment-data';
import {Session} from '../../model/session';

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
    console.log(tickets);
    console.log(paymentData);
    return this.http.post<boolean>('http://demo4608640.mockable.io/api/ticket/buy', {tickets, paymentData}, this.httpOptions)
      .pipe(
        map(result => result['success']),
        catchError(this.handleError<boolean>('buyTickets'))
      );
  }

  getSessionsWithAvailableSeats(): Observable<Session[]> {
    return this.http.get<Session[]>('http://demo4608640.mockable.io/api/ticket/available', this.httpOptions).pipe(
      map(response => response['sessions']),
      catchError(this.handleError<Session[]>('getSessionsWithAvailableSeats'))
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
