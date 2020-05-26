/* tslint:disable:no-string-literal */
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthenticationService} from '../../services/login';

@Component({templateUrl: 'login.component.html', styleUrls: ['./login.component.css']})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  error: string = '';

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService
  ) {
    // redirect to home if already logged in
    this.authenticationService.logout();
    if (this.authenticationService.getCurrentUser()!==null) {
      this.router.navigate(['/']);
    }
  }

  // convenience getter for easy access to form fields
  get formFields() {
    return this.loginForm.controls;
  }

  get f() {
    return this.loginForm.controls;
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
        username: ['', Validators.required],
        password: ['', Validators.required]
      }
    );
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';

  }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.loginForm.invalid) {
      return;
    }

    // this.error = ''
    this.loading = true;
    this.authenticationService.login(this.formFields.username.value, this.formFields.password.value)
      //.pipe(first())
      .subscribe(
        data => {
          console.log(data);
          console.log("here3");
          this.returnUrl = data['url'];
          this.router.navigate([this.returnUrl]);
        },
        error => {
          this.error = error;
          console.log(error);
          this.loading = false
          this.submitted=false;
          alert("Invalid credentials");
        });
  }
}
