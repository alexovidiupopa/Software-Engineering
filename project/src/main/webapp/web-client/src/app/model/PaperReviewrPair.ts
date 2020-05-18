export class PaperReviewrPair{
  idPaper:number;
  idsReviewer: number[] = [];
  constructor(idPaper: number) {
    this.idPaper = idPaper;
  }

  add_reviwer(id:number){
    this.idsReviewer.push(id);
  }
  ///returns 1 if there are too many reviewers, returns -1 if there are too few reviewrs, and 0 otherwise
  can_send(){
    if(this.idsReviewer.length < 2){
      return -1;
    }
    if(this.idsReviewer.length > 3){
      return 1;
    }
    return 0;

  }

  remove_reviewer(id:number){
    const index: number = this.idsReviewer.indexOf(id);
    if (index !== -1) {
      this.idsReviewer.splice(index, 1);
    }
  }
}
