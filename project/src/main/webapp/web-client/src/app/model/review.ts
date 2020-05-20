export class Review {
  paperId: number;
  review: File;
  qualifier: number;
  id: number;


  constructor(paperId: number, review?: File, qualifier?: number) {
    this.paperId = paperId;
    this.review = review;
    this.qualifier = qualifier;
  }
}
