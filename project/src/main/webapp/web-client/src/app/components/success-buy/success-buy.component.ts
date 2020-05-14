import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-success-buy',
  templateUrl: './success-buy.component.html',
  styleUrls: ['./success-buy.component.css']
})
export class SuccessBuyComponent implements OnInit {

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  home() {
    this.router.navigateByUrl('/');
  }
}
