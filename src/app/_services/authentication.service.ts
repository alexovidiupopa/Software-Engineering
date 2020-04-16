import { Injectable, SystemJsNgModuleLoader } from '@angular/core';
import { HttpClient , HttpHeaders} from '@angular/common/http';
import { BehaviorSubject, Observable, Subscription } from 'rxjs';
import { map , tap} from 'rxjs/operators';
//import { environment } from 'src/environments/environment';
import { stringify } from 'querystring';
import { first } from 'rxjs/operators';
import { User } from '../user';


  


  




@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    private succes : boolean;
    private response:request = new request();
    private currentUserSubject: BehaviorSubject<any>;
    public currentUser: Observable<any>;
    public user:User = null;
    private url : string = 'https://demo4608640.mockable.io/api/login';
    private   httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })

  };
   
    constructor(private http: HttpClient) {
        this.currentUserSubject = new BehaviorSubject<any>(JSON.parse(localStorage.getItem('currentUser')));
        this.currentUser = this.currentUserSubject.asObservable();
       
    }

    public get currentUserValue() {
        return this.currentUserSubject.value;
    }

    public get_current_user(){  
        return this.currentUserSubject.value;
    }
    


    getProgramCommittee() {
    this.http.post<boolean>('https://demo4608640.mockable.io/api/login', this.httpOptions).pipe(
      map(response => response["success"]),
      tap(response => console.log(response + "dawd")),

    ).subscribe(res=>console.log(res));
  }

    login(username, password) {
        
      
     

      this.getProgramCommittee();

      return this.http.post<request>(this.url,{username, password}).pipe(
        map(user=>{
          debugger
          this.response.succes = user["success"];
          if(this.response.succes == true){
            this.response.type = user["type"];
            var loggedUser:User = new User("firstname","lastname","username","password",this.response.type,"idk");
            if(loggedUser.type == 'chair'){
              loggedUser.url = 'chair-home';
            }
            else if(loggedUser.type == 'pc'){
              loggedUser.url = 'pc-home';
            }
            else if(loggedUser.type == 'author'){
              loggedUser.url = 'author-home';
            }
            this.user = loggedUser;
            localStorage.setItem('currentUser', JSON.stringify(user));
            this.currentUserSubject.next(user);

            return this.user;

          }
          else{
            return Error("username or passowrd incorrect");
          }
        })
      )
      

       return this.http.post<any>(this.url, { username, password })
            .pipe(map(user => {
                debugger
                // store user details and jwt token in local storage to keep user logged in between page refreshes
                localStorage.setItem('currentUser', JSON.stringify(user));
                this.currentUserSubject.next(user);
                return user;
            }));
       
      
       
        
       

            
        /*return this.http.post<any>(`${environment.apiUrl}/users/authenticate`, { username, password })
            .pipe(map(user => {
                
                // store user details and jwt token in local storage to keep user logged in between page refreshes
                localStorage.setItem('currentUser', JSON.stringify(user));
                this.currentUserSubject.next(user);
                return user;
            }));*/
    }

    logout() {
        // remove user from local storage and set current user to null
        localStorage.removeItem('currentUser');
        this.currentUserSubject.next(null);
    }
}


 class request{
    succes:boolean;
    type:string;

    constructor(){}
}





