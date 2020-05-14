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
  httpFileOptions = {headers: new HttpHeaders({'Content-Type': 'multipart/form-data'})};
  private url = 'http://localhost:8080/api/paper';

  constructor(
    private http: HttpClient) {
  }

  uploadAbstractProper(abstract: File): Observable<boolean> {
    const formData = new FormData();
    formData.append('abstract', abstract);
    return this.http.put<boolean>(this.url + '/upload-abstract/abstract', {
      formData
    }, this.httpFileOptions)
      .pipe(
        map(result => result['success']),
        catchError(this.handleError<boolean>('uploadAbstractProper'))
      );
  }

  uploadAbstractMetadata(authorId: number, paperName: string, paperAuthors: string,
                         paperKeywords: string): Observable<boolean> {
    return this.http.post<boolean>(this.url + '/upload-abstract/meta', {
      authorId, paperName, paperAuthors, paperKeywords
    }, this.httpOptions)
      .pipe(
        map(result => result['success']),
        catchError(this.handleError<boolean>('uploadAbstractMetadata'))
      );
  }

  uploadAbstract(authorId: number, paperName: string, paperAuthors: string,
                 paperKeywords: string, abstract: File): Observable<boolean> {
    console.log(authorId, paperName, paperAuthors, paperKeywords, abstract);

    this.uploadAbstractMetadata(authorId, paperName, paperAuthors, paperKeywords)
      .subscribe(metaResult => {
        if (metaResult === true) {
          this.uploadAbstractProper(abstract)
            .subscribe(abstractResult => {
              if (abstractResult) {
                return of(true);
              } else {
                return of(false);
              }
            });
        } else {
          return of(false);
        }
      });
    return of(false);
    // return this.http.post<boolean>(this.url + '/upload-abstract', {
    //   authorId, paperName, paperAuthors, paperKeywords, abstract
    // }, this.httpOptions)
    //   .pipe(
    //     map(result => result['success']),
    //     catchError(this.handleError<boolean>('uploadAbstract'))
    //   );
  }


  // uploadCompletePaper(paperId: number, paper: File): Observable<boolean> {
  //   return this.http.put<boolean>(this.url + '/upload-paper', {
  //     paperId, paper
  //   }, this.httpOptions)
  //     .pipe(
  //       map(result => result['success']),
  //       catchError(this.handleError<boolean>('uploadCompletePaper'))
  //     );
  // }

  getPapersForAuthor(authorId: number): Observable<Paper[]> {
    const tempUrl = 'http://demo4608640.mockable.io/api/paper/getPapers/1';
    return this.http.get<Paper[]>(tempUrl, this.httpOptions) // fixme temp url
      .pipe(
        map(result => result['papers']),
        tap(result => console.log(result)),
        catchError(this.handleError<Paper[]>('getPapersForAuthor', []))
      );
  }

  updatePaperMetadata(paperId: number, paperName: string, paperAuthors: string,
                      paperKeywords: string): Observable<boolean> {
    return this.http.post<boolean>(this.url + '/update/meta', {
      paperId, paperName, paperAuthors, paperKeywords
    }, this.httpOptions)
      .pipe(
        map(result => result['success']),
        catchError(this.handleError<boolean>('updatePaperMetadata'))
      );
  }

  updatePaperAbstract(abstract: File): Observable<boolean> {
    const formData = new FormData();
    formData.append('abstract', abstract);
    return this.http.put<boolean>(this.url + '/update/abstract', {
      formData
    }, this.httpFileOptions)
      .pipe(
        map(result => result['success']),
        catchError(this.handleError<boolean>('updatePaperAbstract'))
      );
  }

  updatePaperContent(content: File): Observable<boolean> {
    const formData = new FormData();
    formData.append('content', content);
    return this.http.put<boolean>(this.url + '/update/content', {
      formData
    }, this.httpFileOptions)
      .pipe(
        map(result => result['success']),
        catchError(this.handleError<boolean>('updatePaperContent'))
      );
  }

  updatePaper(paperId: number, paperName: string, paperAuthors: string,
              paperKeywords: string, abstract: File, paper: File): Observable<boolean> {

    console.log(paperId, paperName, paperAuthors, paperKeywords, abstract, paper);
    this.updatePaperMetadata(paperId, paperName, paperAuthors, paperKeywords)
      .subscribe(metaResult => {
        if (metaResult === true) {
          this.updatePaperAbstract(abstract)
            .subscribe(abstractResult => {
              if (abstractResult === true) {
                this.updatePaperContent(paper)
                  .subscribe(contentResult => {
                    if (contentResult === true) {
                      return of(true);
                    } else {
                      return of(false);
                    }
                  });
              } else {
                return of(false);
              }
            });
        } else {
          return of(false);
        }
      });
    return of(false);
    // return this.http.put<boolean>(this.url + '/update', {
    //   paperId, paperName, paperAuthors, paperKeywords, abstract, paper
    // }, this.httpOptions)
    //   .pipe(
    //     map(result => result['success']),
    //     catchError(this.handleError<boolean>('updatePaper'))
    //   );
  }

  getPaperById(id: number): Observable<Paper> {
    const tempUrl = 'http://demo4608640.mockable.io/api/paper/:id';
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

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  accept(id: number) {
    const url = `${this.url}/accept/${id}`;
    console.log(id);
    this.http.put<boolean>(url,this.httpOptions);
  }

  reject(id: number) {
    const url = `${this.url}/reject/${id}`;
    console.log(id);
    this.http.put<boolean>(url,this.httpOptions);
  }

  reassign(id: number, reviewers: number[]) {
    const url=`${this.url}/reassign/paper=${id}`;
    this.http.put<boolean>(url,{"reviewers":reviewers},this.httpOptions);
  }
}
