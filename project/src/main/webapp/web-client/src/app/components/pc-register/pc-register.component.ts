import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ProgramCommittee} from '../../model/program-committee';
import {Router} from '@angular/router';
import {SignUpService} from '../../services/sign-up/sign-up.service';

@Component({
  selector: 'app-pc-register',
  templateUrl: './pc-register.component.html',
  styleUrls: ['./pc-register.component.css']
})
export class PcRegisterComponent implements OnInit {

  myForm: FormGroup;
  success = false;

  constructor(private signUpService: SignUpService, private fb: FormBuilder, private router: Router) {
  }

  get email() {
    return this.myForm.get('email');
  }

  get password() {
    return this.myForm.get('password');
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

  goHome() {
    this.router.navigate(['']);
  }

  assignId(): number {
    return Math.random();
  }

  getUsername(): string {
    return this.myForm.value.username;
  }

  getPassword(): string {
    return this.myForm.value.password;
  }

  getAffiliation(): string {
    return this.myForm.value.affiliation;
  }

  getFirstName(): string {
    return this.myForm.value.firstName;
  }

  getLastName(): string {
    return this.myForm.value.lastName;
  }

  getPhoneNumber(): string {
    return this.myForm.value.phoneNumber;
  }

  getEmail(): string {
    return this.myForm.value.email;
  }

  getWebsite(): string {
    return this.myForm.value.website;
  }

  getAcademicRank(): string {
    return this.myForm.value.academicRank;
  }


  registerPCMember(): void {
    this.success = true;
    // tslint:disable-next-line:max-line-length
    const pcMember: ProgramCommittee = new ProgramCommittee(this.getUsername(), this.getPassword(), this.getWebsite(), this.getAffiliation(), this.getFirstName(), this.getLastName(), this.getPhoneNumber(), this.getEmail(), this.getAcademicRank(), this.assignId());
    this.signUpService.registerPcMember(pcMember)
      .subscribe(success => console.log(success));
  }
}
