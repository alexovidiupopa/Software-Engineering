import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Chair} from "../../model/chair";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class ChairService {
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  private url = 'http://localhost:8080/api/chair';

  constructor(private http: HttpClient) {}

  getAllChairs(): Observable<Chair[]>
  {
    return this.http.get<Chair[]>(this.url + '/getAllChairs', this.httpOptions).pipe(map(result => result["chairs"]) );
  }
}
