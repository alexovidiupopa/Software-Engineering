export class LoginUserBody {

  Username: string;
  Password: string;

  constructor(Username: string, Password: string){
    this.Username = Username;
    this.Password = Password;
  }

  public getUsername(){
    return this.Username;
  }
  public getPassword(){
    return this.Password;
  }
}
