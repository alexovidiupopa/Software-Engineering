export class Paper {
  title: string;
  authors: string;
  authorId: number;
  keywords: string;
  abstract: File;
  paperContent: File;
  id: number;


  constructor(title: string, authors: string, authorId: number, keywords: string, abstract: File, paperContent: File, id: number) {
    this.title = title;
    this.authors = authors;
    this.authorId = authorId;
    this.keywords = keywords;
    this.abstract = abstract;
    this.paperContent = paperContent;
    this.id = id;
  }
}
