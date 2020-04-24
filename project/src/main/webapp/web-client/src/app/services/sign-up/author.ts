export class Author {
  username: string;
  password: string;
  affiliation: string;
  firstName: string;
  lastName: string;
  phoneNumber: string;
  email: string;
  id: number;

  // tslint:disable-next-line:max-line-length
  constructor(username: string, password: string, affiliation: string, firstName: string, lastName: string, phoneNumber: string, email: string, id: number) {
    this.username = username;
    this.password = password;
    this.affiliation = affiliation;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.id = id;
  }
}
