import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {catchError, map, tap} from 'rxjs/operators';
import {Paper} from '../../model/paper';
import {Review} from '../../model/review';
import {Reviewer} from '../../model/Reviewer';
import {PaperReviewerPair} from '../../model/PaperReviewerPair';

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


  private getAllReviews(): Observable<Review[]> {
    const url = this.url + '/all-reviews';
    return this.http.get<Review[]>(url, this.httpOptions)
      .pipe(
        map(result => result['reviews']),
        catchError(this.handleError<Review[]>('getAllReviews', []))
      );
  }

  uploadAbstractMetadata(authorId: number, paperName: string,
                         keywords: string, filename: string): Observable<boolean> {
    return this.http.post<boolean>(this.url + '/upload-abstract/meta', {
      authorId, paperName, keywords, filename
    }, this.httpOptions)
      .pipe(
        map(result => Boolean(result['message'])),
        catchError(this.handleError<boolean>('uploadAbstractMetadata'))
      );
  }

  uploadAbstract(authorId: number, paperName: string,
                 paperKeywords: string, abstract: File): Observable<boolean> {
    console.log(authorId, paperName, paperKeywords, abstract);
    var success = true;
    this.uploadAbstractMetadata(authorId, paperName, paperKeywords, abstract.name)
      .subscribe(metaResult => {
        console.log(metaResult);
        if (metaResult === true) {
          this.uploadAbstractProper(abstract)
            .subscribe(abstractResult => {
              console.log(abstractResult);
              success = abstractResult;
            });
        } else {
          success=false;
        }
      });
    return of(success);
  }

  getPapersForAuthor(authorId: number): Observable<Paper[]> {
    return this.http.get<Paper[]>(this.url + '/getPapersForAuthor/' + authorId, this.httpOptions)
      .pipe(
        map(result => result['papers']),
        tap(result => console.log(result)),
        catchError(this.handleError<Paper[]>('getPapersForAuthor', []))
      );
  }

  updatePaperMetadata(paperId: number, authorId:number, paperName: string,
                      paperKeywords: string, fileName: string): Observable<boolean> {
    return this.http.put<boolean>(this.url + '/update/meta', {
      paperId, authorId, paperName, 'keywords':paperKeywords, fileName
    }, this.httpOptions)
      .pipe(
        map(result => Boolean(result['message'])),
        catchError(this.handleError<boolean>('updatePaperMetadata'))
      );
  }

  // todo test me potentially dangerous
  updatePaper(paperId: number, authorId: number, paperName: string,
              paperKeywords: string, abstract: File, paper: File): Observable<boolean> {

    this.updatePaperMetadata(paperId, authorId, paperName, paperKeywords, paper.name)
      .subscribe(metaResult => {
        if (metaResult === true) {
            var success = true;
            if (abstract!==null) {
              this.uploadAbstractProper(abstract)
                .subscribe(result=>success=success && result);
            }
            if (paper!==null) {
              this.updatePaperContent(paper)
                .subscribe(result=>success = success && result);
            }
            return of(true);
        } else {
          return of(false);
        }

      });

    return of(false);
  }

  getPaperById(id: number): Observable<Paper> {
    return this.http.get<Paper>(this.url + '/getPaper/' + id, this.httpOptions)
      .pipe(
        tap(result => console.log(result)),
        catchError(this.handleError<Paper>('getPapersForAuthor'))
      );
  }

  getAbstract(paperId: number): Observable<any> {
    return this.http.get<any>(this.url + '/abstract/' + paperId, this.httpOptions)
      .pipe(
        catchError(this.handleError<File>('getPapersForAuthor'))
      );
  }


  getPaperContent(paperId: number): Observable<any> {
    return this.http.get<any>(this.url + '/content/' + paperId, this.httpOptions)
      .pipe(
        catchError(this.handleError<any>('getPaperContent '))
      );
  }

  getAllPapers(): Observable<Paper[]> {
    const url = this.url + '/papers';
    return this.http.get<Paper[]>(url, this.httpOptions).pipe(
      map(result => result['papers']),
      catchError(this.handleError<Paper[]>('getAllPapers', []))
    );

  }

  getAllReviewersForPaper(paperId: number): Observable<Reviewer[]> {
    const url = this.url + '/for-paper/' + paperId;
    return this.http.get<Reviewer[]>(url, this.httpFileOptions).pipe(
      map(result => result['reviewers']),
      catchError(this.handleError<Reviewer[]>('getAllReviewersForPaper', []))
    );

  }

  assignReviewersToPaper(paperReviewerPair: PaperReviewerPair): Observable<boolean> {
    // fixme URL potentially unsafe. maybe change to other, could be overwritten
    return this.http.post(this.url + '/review', paperReviewerPair, this.httpOptions).pipe(
      map(result => result['success']),
      catchError(this.handleError<boolean>('assignReviewersToPaper'))
    );
  }

  getAllPapersForReviewer(pcId: number): Observable<Paper[]> {
    const url = this.url + '/for-review/' + pcId;
    return this.http.get<Paper[]>(url, this.httpOptions)
      .pipe(
        map(result => {
          let papers: Paper[] = result['papers'];
          // todo check if there is any leaking point, which needs the files no longer given here
          // for (let paper of papers) {
          //   this.getAbstract(paper.pid).subscribe(
          //     result => paper.abstract = result
          //   );
          //   this.getPaperContent(paper.pid).subscribe(
          //     result => paper.paperContent = result
          //   );
          // }
          return papers;
        }),
        catchError(this.handleError<Paper[]>('getAllPapersForReviewer', []))
      );
  }

  submitReview(pcId: number, review: Review): Observable<boolean> {
    const url = this.url + '/review/submit/' + pcId + '/' + review.paperId + '/5';
    const formData = new FormData();
    formData.append('file', review.review);
    return this.http.post<boolean>(url, formData)
      .pipe(
        map(response => Boolean(response['message'])),
        catchError(this.handleError<boolean>('submitReview'))
      );
  }

  updatePaperContent(content: File): Observable<boolean> {
    const formData = new FormData();
    formData.append('file', content);
    return this.http.put<boolean>(this.url + '/upload-content/content',
      formData
    )
      .pipe(
        map(result => Boolean(result['message'])),
        catchError(this.handleError<boolean>('updatePaperContent'))
      );
  }

  uploadAbstractProper(abstract: File): Observable<boolean> {
    const formData = new FormData();
    formData.append('file', abstract);
    return this.http.put<boolean>(this.url + '/upload-abstract/abstract', formData)
      .pipe(
        map(result => Boolean(result['message'])),
        catchError(this.handleError<boolean>('uploadAbstractProper'))
      );
  }

  acceptPaper(id: number) {
    const url = `${this.url}/accept/${id}`;
    console.log(id);
    this.http.put<boolean>(url, this.httpOptions);
  }

  rejectPaper(id: number) {
    const url = `${this.url}/reject/${id}`;
    console.log(id);
    this.http.put<boolean>(url, this.httpOptions);
  }

  reassignPaper(id: number, reviewers: number[]) {
    const url = `${this.url}/reassign/paper=${id}`;
    this.http.put<boolean>(url, {reviewers}, this.httpOptions);
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }



  paperHasContentUploaded(id: number): Observable<boolean> {
    return this.http.get(this.url + '/has-content/' + id, this.httpOptions)
      .pipe(
        tap(result=>console.log(result)),
        map(result => {
          if (result['message']==='true')
            return true;
          return false;
        })
      );
  }
}
