export class Paperr {
  pid: number;
  aid: number;
  session: number;
  title: string;
  topic: string;
  accepted: string;
  abstracturl: string;
  contenturl: string;
  presentationurl: string;


  constructor(pid: number, session: number, title: string, topic: string, accepted: string, abstracturl: string, contenturl: string, presentationurl: string) {
    this.pid = pid;
    this.session = session;
    this.title = title;
    this.topic = topic;
    this.accepted = accepted;
    this.abstracturl = abstracturl;
    this.contenturl = contenturl;
    this.presentationurl = presentationurl;
  }

}
