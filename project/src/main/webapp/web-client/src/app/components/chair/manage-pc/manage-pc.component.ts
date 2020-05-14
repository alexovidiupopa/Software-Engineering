import {Component, OnInit} from '@angular/core';
import {FormControl, Validators} from '@angular/forms';
import {ProgramCommittee} from '../../../model/program-committee';
import {PcDto, ProgramCommitteeService} from '../../../services/program-committee/program-committee.service';

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
  pcs: PcDto[];

  constructor(private pcService: ProgramCommitteeService) {
  }

  ngOnInit() {
    this.getPCs();
  }

  getPCs(): void {
    this.pcService.getProgramCommittees()
      .subscribe(pcs => this.pcs = pcs);
  }
}
