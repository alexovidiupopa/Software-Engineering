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
  // tslint:disable-next-line:variable-name
  author_register = false;
  // tslint:disable-next-line:variable-name
  pc_register = false;

  constructor(private router: Router) {
  }

  ngOnInit() {
  }

  goHome() {
    this.router.navigate(['']);
  }

  goToPCRegister() {
    this.author_register = false;
    this.pc_register = true;
    document.getElementById('loginButton2').style.backgroundColor = 'white';
    document.getElementById('loginButton1').style.backgroundColor = '#324e85';
    document.getElementById('loginButton2').style.color = 'black';
    document.getElementById('loginButton1').style.color = 'white';
    // this.pcButton = 'none';ik0
    // this.authorButton = 'primary';
    // this.router.navigate(['/pc-register']);
  }

  goToAuthorRegister() {
    this.author_register = true;
    this.pc_register = false;
    document.getElementById('loginButton1').style.backgroundColor = 'white';
    document.getElementById('loginButton2').style.backgroundColor = '#324e85';
    document.getElementById('loginButton1').style.color = 'black';
    document.getElementById('loginButton2').style.color = 'white';
    // this.pcButton = 'primary';
    // this.authorButton = 'none';
    // this.router.navigate(['/author-register']);
  }

}
