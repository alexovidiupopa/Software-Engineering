export class Paper {
  pid: number;
  authorId: number;
  session: number;
  title: string;
  topic: string;
  accepted: string;
  abstractUrl: string;
  contentUrl: string;


  constructor(pid: number, aid: number, session: number, title: string, topic: string, accepted: string, abstractUrl: string, contentUrl: string) {
    this.pid = pid;
    this.authorId = aid;
    this.session = session;
    this.title = title;
    this.topic = topic;
    this.accepted = accepted;
    this.abstractUrl = abstractUrl;
    this.contentUrl = contentUrl;
  }

}
