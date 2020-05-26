import {Sesssion} from "./sesssion";

export class Ticket {
  firstName: string;
  email: string;
  lastName: string;
  sessions: Sesssion[];


  constructor(firstName: string, lastName: string, email: string, sessions: Sesssion[]) {
    this.firstName = firstName;
    this.email = email;
    this.lastName = lastName;
    this.sessions = sessions;
  }

}
