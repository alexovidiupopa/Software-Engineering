export class Review {
  paperId: number;
  review: File;
  qualifier: string;
  id: number;


  constructor(paperId: number, review?: File, qualifier?: string) {
    this.paperId = paperId;
    this.review = review;
    this.qualifier = qualifier;
  }
}
