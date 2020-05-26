export class UserDto{
  constructor( uid: number, username: string, password: string, website: string, affiliation: string, firstname: string, lastname: string, phonenumber: string, email: string, academicrank: string) {
    this.uid = uid;
    this.username = username;
    this.password = password;
    this.website = website;
    this.affiliation = affiliation;
    this.firstname = firstname;
    this.lastname = lastname;
    this.phonenumber = phonenumber;
    this.email = email;
    this.academicrank = academicrank;
  }
  uid: number;
  username: string;
  password: string;
  website: string;
  affiliation: string;
  firstname: string;
  lastname: string;
  phonenumber: string;
  email: string;
  academicrank: string;
}
