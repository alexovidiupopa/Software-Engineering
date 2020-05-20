export class UserDto{
  constructor(username: string, password: string, website: string, affiliation: string, firstname: string, lastname: string, phonenumber: string, email: string, academicrank: string, uid: number) {
    this.username = username;
    this.password = password;
    this.website = website;
    this.affiliation = affiliation;
    this.firstname = firstname;
    this.lastname = lastname;
    this.phonenumber = phonenumber;
    this.email = email;
    this.academicrank = academicrank;
    this.uid = uid;
  }
  username: string;
  password: string;
  website: string;
  affiliation: string;
  firstname: string;
  lastname: string;
  phonenumber: string;
  email: string;
  academicrank: string;
  uid: number;
}
