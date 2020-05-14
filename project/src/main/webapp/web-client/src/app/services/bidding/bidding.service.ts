import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Paper} from "../../model/paper";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class BiddingService {
  //private url = 'http://localhost:8080/api';  // URL to web api
  private url = 'http://demo5157520.mockable.io/api'
  private httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})

  };

  constructor(private http: HttpClient) {
  }

  getAllPapers(userId: number): Observable<Paper[]> {
    const url = `${this.url}/paper/getAllExcept/${userId}`;
    return this.http.get<Paper[]>(url, this.httpOptions).pipe(map(response => response["papers"]));
  }

  acceptPapers(userId: number, accepted: number[]) {
    for (let paper in accepted) {
      const url = `${this.url}/paper/bid/paper=${paper}-uid=${userId}`;
      this.http.post(url, this.httpOptions);
    }
  }
}
