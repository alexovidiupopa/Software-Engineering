import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {catchError, map, tap} from 'rxjs/operators';
import {Paper} from '../../model/paper';

@Injectable({
  providedIn: 'root'
})
export class PaperService {
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  private url = 'http://localhost:8080/api/paper';

  constructor(
    private http: HttpClient) {
  }

  uploadAbstract(authorId: number, paperName: string, paperAuthors: string,
                 paperKeywords: string, abstract: File): Observable<boolean> {
    return this.http.post<boolean>(this.url + '/upload-abstract', {
      authorId, paperName, paperAuthors, paperKeywords, abstract
    }, this.httpOptions)
      .pipe(
        map(result => result['success']),
        catchError(this.handleError<boolean>('uploadAbstract'))
      );
  }

  uploadCompletePaper(paperId: number, paper: File): Observable<boolean> {
    return this.http.put<boolean>(this.url + '/upload-paper', {
      paperId, paper
    }, this.httpOptions)
      .pipe(
        map(result => result['success']),
        catchError(this.handleError<boolean>('uploadCompletePaper'))
      );
  }

  getPapersForAuthor(authorId: number): Observable<Paper[]> {
    const tempUrl = "http://demo4608640.mockable.io/api/paper/getPapers/1";
    return this.http.get<Paper[]>(tempUrl, this.httpOptions) // fixme temp url
      .pipe(
        map(result => result['papers']),
        catchError(this.handleError<Paper[]>('getPapersForAuthor', []))
      );
  }

  updatePaper(paperId: number, paperName: string, paperAuthors: string,
              paperKeywords: string, abstract: File, paper: File): Observable<boolean> {

    console.log(paperId, paperName, paperAuthors, paperKeywords, abstract, paper);

    return this.http.put<boolean>(this.url + '/update', {
      paperId, paperName, paperAuthors, paperKeywords, abstract, paper
    }, this.httpOptions)
      .pipe(
        map(result => result['success']),
        catchError(this.handleError<boolean>('updatePaper'))
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

  getPaperById(id: number): Observable<Paper> {
    const tempUrl = "http://demo4608640.mockable.io/api/paper/:id";
    return this.http.get<Paper>(tempUrl, this.httpOptions) // fixme this is a temp mocking link
      .pipe(
        tap(result => console.log(result)),
        catchError(this.handleError<Paper>('getPapersForAuthor'))
      );
  }

  getAbstract(paperId: number): Observable<File> {
    // return this.http.get<File>(this.url + '/abstract/' + paperId, this.httpOptions)
    //   .pipe(
    //     catchError(this.handleError<File>('getPapersForAuthor'))
    //   );
    return of(new File(['abstract blob'], 'abstractTestFile'));
  }

  getPaperContent(paperId: number): Observable<File> {
    // return this.http.get<File>(this.url + '/content/' + paperId, this.httpOptions)
    //   .pipe(
    //     catchError(this.handleError<File>('getPapersForAuthor'))
    //   );
    return of(new File(['paper blob'], 'paperTestFile'));
  }
}
