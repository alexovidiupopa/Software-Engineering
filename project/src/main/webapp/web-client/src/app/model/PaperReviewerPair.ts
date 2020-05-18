export class PaperReviewerPair {
  idPaper: number;
  idsReviewer: number[] = [];

  constructor(idPaper: number) {
    this.idPaper = idPaper;
  }

  addReviewer(id: number) {
    this.idsReviewer.push(id);
  }

  /// returns 1 if there are too many reviewers, returns -1 if there are too few reviewrs, and 0 otherwise
  canSend() {
    if (this.idsReviewer.length < 2) {
      return -1;
    }
    if (this.idsReviewer.length > 3) {
      return 1;
    }
    return 0;

  }

  removeReviewer(id: number) {
    const index: number = this.idsReviewer.indexOf(id);
    if (index !== -1) {
      this.idsReviewer.splice(index, 1);
    }
  }
}
