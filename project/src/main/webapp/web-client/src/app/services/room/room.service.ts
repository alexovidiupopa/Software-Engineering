import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Sesssion} from '../../model/sesssion';
import {map} from 'rxjs/operators';
import { Room } from '../../model/room';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  private url = 'http://localhost:8080/api/room';

  constructor(private http: HttpClient) {}

  getAllRooms(): Observable<Room[]>
  {
    return this.http.get<Room[]>(this.url + '/getAllRooms', this.httpOptions).pipe( map(result => result["rooms"]) );
  }

}
