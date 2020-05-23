import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';
import {ProgramCommitteeService} from '../../services/program-committee/program-committee.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {UserDto} from "../../model/userdto";

@Component({
  selector: 'app-pc-detail',
  templateUrl: './pc-detail.component.html',
  styleUrls: ['./pc-detail.component.css']
})
export class PcDetailComponent implements OnInit {
  //pc: ProgramCommittee;
  successfulUpdate = true;
  pc: UserDto;
  constructor(
    private route: ActivatedRoute,
    private pcService: ProgramCommitteeService,
    private location: Location,
    private snackBar: MatSnackBar
  ) {
  }

  ngOnInit(): void {
    this.getPC();
  }

  getPC(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.pcService.getUserInfo(id)
      .subscribe(pc => this.pc = pc);
  }

  goBack(): void {
    this.location.back();
  }

  makeChair(id: number) {
    this.pcService.updatePCToChair(id).subscribe(success => {
      this.successfulUpdate = success;
      if (this.successfulUpdate === true) {
        this.openSnackBar('Made chair :)', null);
      }
    });
    console.log(this.successfulUpdate);
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 1000,
    });
  }
}
