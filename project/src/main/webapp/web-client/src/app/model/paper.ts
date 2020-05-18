export class Paper {
  id: number;
  authorId: number;
  sessionId: number;
  title: string;
  topic: string;
  accepted: string;
  abstractUrl: string;
  contentUrl: string;
  presentationUrl: string; // fixme probably should delete this


  constructor(pid: number, aid: number, session: number, title: string, topic: string, accepted: string, abstractUrl: string, contentUrl: string, presentationUrl: string) {
    this.id = pid;
    this.authorId = aid;
    this.sessionId = session;
    this.title = title;
    this.topic = topic;
    this.accepted = accepted;
    this.abstractUrl = abstractUrl;
    this.contentUrl = contentUrl;
    this.presentationUrl = presentationUrl;
  }

}
