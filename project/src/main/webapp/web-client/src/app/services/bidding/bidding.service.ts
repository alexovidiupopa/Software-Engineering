import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {Paper} from '../../model/paper';
import {catchError, map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class BiddingService {
  private url = 'http://localhost:8080/api';  // URL to web api
  private httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})

  };

  constructor(private http: HttpClient) {
  }

  getAllPapers(userId: number): Observable<Paper[]> {
    const url = `${this.url}/paper/getAllExcept/${userId}`;
    return this.http.get<Paper[]>(url, this.httpOptions)
      .pipe(map(response => response['papers']));
  }

  acceptPapers(userId: number, accepted: number[]): Observable<boolean> {
    return this.http.post<boolean>(this.url + '/paper/bid', {userId, accepted}, this.httpOptions)
      .pipe(
        map(result => Boolean(result['message'])),
        catchError(this.handleError<boolean>('acceptPapers'))
      );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
