import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {catchError, map} from 'rxjs/operators';
import {Conference} from '../../model/conference';

@Injectable({
  providedIn: 'root'
})
@Injectable({providedIn: 'root'})
export class ConferenceService {

  private url = 'http://localhost:8080/api/conference';  // URL to web api
  private httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})

  };

  constructor(
    private http: HttpClient) {
  }

  getCurrentPhase(): Observable<string> {
    return this.http.get(this.url + '/getCurrentPhase', this.httpOptions).pipe(
      map(response => response['message']),
      catchError(this.handleError<Conference>('getCurrentPhase'))
    );
  }


  check_if_conference_exists():Observable<Boolean>{
    return this.http.get<Boolean>(this.url + '/exists',this.httpOptions)
      .pipe(
        map(result=>Boolean(result['message']))
      );
  }

  addConference(conference: Conference): Observable<boolean> {
    const conferenceFormatted = {
      conferenceName: conference.conferenceName,
      preliminaryPhaseDeadline: conference.preliminaryPhaseDeadline.format('MM/DD/YYYY HH:mm:ss'),
      firstPhaseDeadline: conference.firstPhaseDeadline.format('MM/DD/YYYY HH:mm:ss'),
      secondPhaseDeadline: conference.secondPhaseDeadline.format('MM/DD/YYYY HH:mm:ss'),
      thirdPhaseDeadline: conference.thirdPhaseDeadline.format('MM/DD/YYYY HH:mm:ss')
    };
    console.log(conferenceFormatted);

    return this.http.post<boolean>(this.url + '/create', conferenceFormatted, this.httpOptions).pipe(
      map(response => Boolean(response['message'])),
      catchError(this.handleError<boolean>('addConference'))
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
