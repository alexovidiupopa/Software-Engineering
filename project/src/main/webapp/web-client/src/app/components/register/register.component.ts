import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  authorButton = 'primary';
  pcButton = 'primary';
  constructor(private router: Router) {
  }

  ngOnInit() {
  }

  goHome()
  {
    this.router.navigate(['/login']);
  }

  goToPCRegister() {
    this.pcButton = 'none';
    this.authorButton = 'primary';
    this.router.navigate(['/pc-register']);
  }

  goToAuthorRegister() {
    this.pcButton = 'primary';
    this.authorButton = 'none';
    this.router.navigate(['/author-register']);
  }

}
