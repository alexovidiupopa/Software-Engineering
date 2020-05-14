export class LoginUserBody {

  username: string;
  password: string;

  constructor(Username: string, Password: string) {
    this.username = Username;
    this.password = Password;
  }

  public getUsername() {
    return this.username;
  }

  public getPassword() {
    return this.password;
  }
}
