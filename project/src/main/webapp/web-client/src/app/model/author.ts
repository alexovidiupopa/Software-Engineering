export class Author {
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

  // tslint:disable-next-line:max-line-length
  constructor(username: string, password: string, website: string, affiliation: string,
              firstName: string, lastName: string, phoneNumber: string, email: string,
              academicRank: string, id: number) {
    this.username = username;
    this.password = password;
    this.website = website;
    this.affiliation = affiliation;
    this.firstname = firstName;
    this.lastname = lastName;
    this.phonenumber = phoneNumber;
    this.email = email;
    this.academicrank = academicRank;
    this.uid = id;
  }
}
