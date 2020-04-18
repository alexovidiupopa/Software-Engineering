export class User{
    firstName:string;
    lastName:string;
    username:string;
    password:string;
    type:string = "pulamea"
    url:string;
    token:string;
    tokenLength : number;
    constructor(firstName:string, lastName:string,username:string,password:string,type:string,url:string){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.type = type;
        this.url = url;
        this.tokenLength = 10;
    }

    public get_token(){
      return this.token;
    }

   public set_token(token:string){
      this.token = token;
    }


    makeid() {
    var result           = '';
    var characters       = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    var charactersLength = characters.length;
    for ( var i = 0; i < this.tokenLength; i++ ) {
      result += characters.charAt(Math.floor(Math.random() * charactersLength));
    }
    return result;
  }

    get_url(){
        return this.url;
    }

    isChair(){
        debugger
        return this.type=="Chair";
    }
}
