import { Component, OnInit } from '@angular/core';

import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserService} from '../../services/sign-up/user.service';
import {Author} from '../../services/sign-up/author';

@Component({
  selector: 'app-author-register',
  templateUrl: './author-register.component.html',
  styleUrls: ['./author-register.component.css']
})
export class AuthorRegisterComponent implements OnInit {

  myForm: FormGroup;
  success = false;
  constructor(private userService: UserService, private fb: FormBuilder) {}

  ngOnInit(): void {
      this.myForm = this.fb.group({
        username: '',
        password: ['', [
          Validators.required,
          Validators.pattern('^(?=.*[a-zA-Z])(?=.*[0-9])([a-zA-Z0-9]+)$'),
    Validators.minLength(7),
          Validators.maxLength(17)
        ]],
        affiliation: ['', [
          Validators.required,
        ]],
        firstName: ['', [
          Validators.required,
        ]],
        lastName: ['', [
          Validators.required,
        ]],
        phoneNumber: ['', [
          Validators.required,
          Validators.pattern('(?=.*[0-9])([0-9]+)$'),
        ]],
        email: ['', [
          Validators.required,
          Validators.email
        ]]
      });
  }

  get email()
  {
    return this.myForm.get('email');
  }

  get password()
  {
    return this.myForm.get('password');
  }
  assignId(): number
  {
    return Math.random();
  }
  getUsername(): string
  {
    return this.myForm.value.username;
  }
  getPassword(): string
  {
    return this.myForm.value.password;
  }
  getAffiliation(): string
  {
    return this.myForm.value.affiliation;
  }
  getFirstName(): string
  {
    return this.myForm.value.firstName;
  }
  getLastName(): string
  {
    return this.myForm.value.lastName;
  }
  getPhoneNumber(): string
  {
    return this.myForm.value.phoneNumber;
  }
  getEmail(): string
  {
    return this.myForm.value.email;
  }
  registerAuthor(): void {
    this.success = true;
    // tslint:disable-next-line:max-line-length
    const author: Author = new Author(this.getUsername(), this.getPassword(), this.getAffiliation(), this.getFirstName(), this.getLastName(), this.getPhoneNumber(), this.getEmail(), this.assignId());
    this.userService.registerAuthor(author)
      .subscribe(success => console.log(success));
  }
}
