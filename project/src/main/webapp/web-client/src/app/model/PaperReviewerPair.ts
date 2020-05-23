export class PaperReviewerPair {
  pid: number;
  reviewers: number[] = [];

  constructor(pid: number) {
    this.pid = pid;
  }

  addReviewer(id: number)  {
    this.reviewers.push(id);
  }

  /// returns 1 if there are too many reviewers, returns -1 if there are too few reviewrs, and 0 otherwise
  canSend() {
    if (this.reviewers.length < 2) {
      return -1;
    }
    if (this.reviewers.length > 3) {
      return 1;
    }
    return 0;

  }

  removeReviewer(id: number) {
    const index: number = this.reviewers.indexOf(id);
    if (index !== -1) {
      this.reviewers.splice(index, 1);
    }
  }
}
