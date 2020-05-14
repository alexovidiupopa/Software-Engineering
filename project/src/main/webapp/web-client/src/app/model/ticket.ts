import {Session} from './session';

export class Ticket {
  firstName: string;
  email: string;
  lastName: string;
  sessions: Session[];


  constructor(firstName: string, lastName: string, email: string, sessions: Session[]) {
    this.firstName = firstName;
    this.email = email;
    this.lastName = lastName;
    this.sessions = sessions;
  }

}
