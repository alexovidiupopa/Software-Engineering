export class User {
  firstName: string;
  lastName: string;
  username: string;
  password: string;
  type = 'lll';
  url: string;
  token: string;
  tokenLength: number;

  constructor(firstName: string, lastName: string, username: string, password: string, type: string, url: string) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.password = password;
    this.type = type;
    this.url = url;
    this.tokenLength = 10;
  }

  public get_token() {
    return this.token;
  }

  public set_token(token: string) {
    this.token = token;
  }


  makeid() {
    let result = '';
    const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    const charactersLength = characters.length;
    for (let i = 0; i < this.tokenLength; i++) {
      result += characters.charAt(Math.floor(Math.random() * charactersLength));
    }
    return result;
  }

  get_url() {
    return this.url;
  }

  isChair() {
    // tslint:disable-next-line:triple-equals
    return this.type == 'Chair';
  }
}
