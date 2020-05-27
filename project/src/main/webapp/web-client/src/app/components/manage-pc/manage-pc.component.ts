import {Component, OnInit} from '@angular/core';
import {FormControl, Validators} from '@angular/forms';
import {ProgramCommitteeService} from '../../services/program-committee/program-committee.service';
import {PcDto} from "../../model/pcdto";
import {UserDto} from "../../model/userdto";

@Component({
  selector: 'app-manage-pc',
  templateUrl: './manage-pc.component.html',
  styleUrls: ['./manage-pc.component.css']
})
export class ManagePCComponent implements OnInit {
  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);
  email : string;
  pcs: PcDto[];
  pcMembersInfo: UserDto[] = [];
  validData = true;
  constructor(private pcService: ProgramCommitteeService) {
  }

  ngOnInit() {
    this.getPCs();
  }

  getPCs(): void {
    this.pcService.getProgramCommittees()
      .subscribe(pcs => {
        this.pcs = pcs
        for (const pc of pcs){
          this.pcService.getUserInfo(pc.uid)
            .subscribe(person=>this.pcMembersInfo.push(person));
        }
      });
  }

  invitePc() {
      alert("Invited pc with email: " + this.email)
      this.pcService.invitePc(this.email)
        .subscribe();
  }

  inputChange(email: string) {
    this.email = email;
  }
}
