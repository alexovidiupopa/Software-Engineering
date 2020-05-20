import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {Author} from '../../model/author';
import {catchError} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AuthorService {

  private url = 'http://localhost:8080/api/author';  // URL to web api
  private httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})

  };

  constructor(
    private http: HttpClient) {
  }

  getAuthorById(authorId: number): Observable<Author> {
    return this.http.get<Author>(this.url + '/' + authorId, this.httpOptions)
      .pipe(
      catchError(this.handleError<Author>('getAuthorById'))
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

      // TODO: better job of transforming error for user consumption
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
