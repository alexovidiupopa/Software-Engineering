import {Component, OnInit} from '@angular/core';
import {Paper} from '../../model/paper';
import {BiddingService} from '../../services/bidding/bidding.service';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../services/login';
import {PaperService} from '../../services/paper/paper.service';
import {DomSanitizer, SafeResourceUrl} from '@angular/platform-browser';

@Component({
  selector: 'app-bidding',
  templateUrl: './bidding.component.html',
  styleUrls: ['./bidding.component.css']
})
export class BiddingComponent implements OnInit {

  papers: Paper[];
  accepted: number[];
  rejected: number[];
  userId: number;
  papersMap: Map<number, string>;
  paperContentUrl: SafeResourceUrl;
  urls = [];
  paperNames: string[] = [];

  constructor(private biddingService: BiddingService,
              private router: Router,
              private authenticationService: AuthenticationService,
              private paperService: PaperService,
              private sanitizer: DomSanitizer) {
    this.papersMap = new Map<number, string>();
    this.accepted = [];
    this.userId = this.authenticationService.getCurrentUser().id;
  }

  ngOnInit(): void {
    this.populatePapers();
  }


  populatePapers(): void {
    this.biddingService.getAllPapers(this.userId)
      .subscribe(papers => {
        this.papers = papers;
        for (let i = 0; i < papers.length; i++) {
          this.urls.push(this.sanitizer.bypassSecurityTrustResourceUrl('http://localhost:8080/api/paper/content/' + papers[i].pid));
          this.paperNames.push(papers[i].title);
        }
      });
  }

  submitChoices(): void {
    this.papersMap.forEach((value: string, key: number) => {
      if (value === 'accept') {
        this.accepted.push(key);
      }
    });

    this.biddingService.acceptPapers(this.userId, this.accepted)
      .subscribe(
        result=> {
          if (result === true)
            this.router.navigateByUrl('/pc-home');
        }
      );
  }

  setPaperStatus(id: number, accept: string) {
    this.papersMap.set(id, accept);
  }
}
