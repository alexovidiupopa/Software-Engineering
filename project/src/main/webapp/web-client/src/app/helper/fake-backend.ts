import {Injectable} from '@angular/core';
import {User} from '../model/user';
import {
  HTTP_INTERCEPTORS,
  HttpClient,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpResponse
} from '@angular/common/http';
import {Observable, of, throwError} from 'rxjs';
import {delay, dematerialize, materialize, mergeMap} from 'rxjs/operators';

const users = [{id: 1, firstName: 'Jason', lastName: 'Watmore', username: 'test', password: 'test'}];
const myUsers = [new User('fn', 'ln', 'test1', 'test1', 'Chair', 'chair-home'5,
),
new User('fn', 'ln', 'test2', 'test2', 'Pc', 'pc-home', 6);
]
;

@Injectable()
export class FakeBackendInterceptor implements HttpInterceptor {
  // httpOptions:any;
  response$: Observable<boolean>;

  constructor(private http: HttpClient) {
    this.http = http;
    mocupURL : 'https://demo4608640.mockable.io/api/login';
    //      this.httpOptions = {
    //   headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    // };
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const {url, method, headers, body} = request;


    // wrap in delayed observable to simulate server api call
    return of(null)
      .pipe(mergeMap(handleRoute))
      .pipe(materialize()) // call materialize and dematerialize to ensure delay even if an error is thrown (https://github.com/Reactive-Extensions/RxJS/issues/648)
      .pipe(delay(500))
      .pipe(dematerialize());

    function handleRoute() {
      switch (true) {
        case url.endsWith('/users/authenticate') && method === 'POST':

          return authenticate();
        default:
          // pass through any requests not handled above
          return next.handle(request);
      }
    }

    // route functions

    function authenticate() {

      const {username, password} = body;


      const user = myUsers.find(x => x.username === username && x.password === password);


      if (!user) {
        return error('Username or password is incorrect');
      }
      return ok(user);
    }

    // helper functions

    function ok(body?) {
      return of(new HttpResponse({status: 200, body}));
    }

    function error(message) {
      return throwError({error: {message}});
    }
  }
}

export const fakeBackendProvider = {
  // use fake backend in place of Http service for backend-less development
  provide: HTTP_INTERCEPTORS,
  useClass: FakeBackendInterceptor,
  multi: true
};
