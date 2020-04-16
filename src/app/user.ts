export class User{
    firstName:string;
    lastName:string;
    username:string;
    password:string;
    type:string = "pulamea"
    url:string;
    constructor(firstName:string, lastName:string,username:string,password:string,type:string,url:string){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.type = type;
        this.url = url;
    }

    get_url(){
        return this.url;
    }

    isChair(){
        debugger
        return this.type=="Chair";
    }
}