import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserService} from '../../services/sign-up/user.service';
import {ProgramCommittee} from '../../services/sign-up/program-comittee';

@Component({
  selector: 'app-pc-register',
  templateUrl: './pc-register.component.html',
  styleUrls: ['./pc-register.component.css']
})
export class PcRegisterComponent implements OnInit {

  myForm: FormGroup;
  success = false;
  constructor(private userService: UserService, private fb: FormBuilder) {
  }

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
      ]],
      academicRank: ['', [
        Validators.required,
      ]],
      website: ['', [
        Validators.required,
      ]],
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
  getWesbite(): string
  {
    return this.myForm.value.website;
  }
  getAcademicRank(): string
  {
    return this.myForm.value.academicRank;
  }

  registerPCMember(): void {
    this.success =  true;
    // tslint:disable-next-line:max-line-length
    const pcMember: ProgramCommittee = new ProgramCommittee(this.getUsername(), this.getPassword(), this.getWesbite(), this.getAffiliation(), this.getFirstName(), this.getLastName(), this.getPhoneNumber(), this.getEmail(), this.getAcademicRank(), this.assignId());
    this.userService.registerPcMember(pcMember)
      .subscribe(success => console.log(success));
  }
}
