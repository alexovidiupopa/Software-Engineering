import {Component, OnInit} from '@angular/core';
import {AmazingTimePickerService} from 'amazing-time-picker';
import {MatDatepickerInputEvent} from '@angular/material/datepicker';
import * as moment from 'moment';
import {ConferenceService} from '../../services/conference/conference.service';
import {Conference} from '../../model/conference';
import {Router} from '@angular/router';

@Component({
  selector: 'app-create-conference',
  templateUrl: './create-conference.component.html',
  styleUrls: ['./create-conference.component.css']
})
export class CreateConferenceComponent implements OnInit {
  minDate: Date;
  preliminaryPhaseDate: Date;
  firstPhaseDate: Date;
  secondPhaseDate: Date;
  thirdPhaseDate: Date;
  preliminarySelectedTime: string;
  firstPhaseSelectedTime: string;
  secondPhaseSelectedTime: string;
  thirdPhaseSelectedTime: string;
  preliminaryPhaseDeadline: moment.Moment;
  firstPhaseDeadline: moment.Moment;
  secondPhaseDeadline: moment.Moment;
  thirdPhaseDeadline: moment.Moment;
  validData: boolean;
  conferenceFailed = false;
  private conferenceName: string;

  constructor(private atp: AmazingTimePickerService, private conferenceService: ConferenceService, private router: Router) {
    // Set the minimum to January 1st 20 years in the past and December 31st a year in the future. // fixme who's this?
    const currentYear = new Date().getFullYear();
    this.minDate = moment().toDate();
    this.validData = true;
  }

  ngOnInit(): void {
  }

  preliminaryPhaseDateAdded(event: MatDatepickerInputEvent<Date>) {
    this.preliminaryPhaseDate = event.value;
  }

  firstPhaseDateAdded(event: MatDatepickerInputEvent<Date>) {
    this.firstPhaseDate = event.value;
  }

  secondPhaseDateAdded(event: MatDatepickerInputEvent<Date>) {
    this.secondPhaseDate = event.value;
  }

  thirdPhaseDateAdded(event: MatDatepickerInputEvent<Date>) {
    this.thirdPhaseDate = event.value;
  }

  openTimePreliminary() {
    const amazingTimePicker = this.atp.open({
      theme: 'material-blue'
    });
    amazingTimePicker.afterClose().subscribe(time => {
      this.preliminarySelectedTime = time;
    });
  }

  openTimeFirstPhase() {
    const amazingTimePicker = this.atp.open({
      theme: 'material-blue'
    });
    amazingTimePicker.afterClose().subscribe(time => {
      this.firstPhaseSelectedTime = time;
    });
  }

  openTimeSecondPhase() {
    const amazingTimePicker = this.atp.open({
      theme: 'material-blue'
    });
    amazingTimePicker.afterClose().subscribe(time => {
      this.secondPhaseSelectedTime = time;
    });
  }

  openTimeThirdPhase() {
    const amazingTimePicker = this.atp.open({
      theme: 'material-blue'
    });
    amazingTimePicker.afterClose().subscribe(time => {
      this.thirdPhaseSelectedTime = time;
    });

  }

  createConferenceButtonPressed() {
    this.formatConferenceData();
    this.printCreateConference();
    this.validateConferenceData();
    if (this.validData) {
      this.conferenceService.addConference({
        conferenceName: this.conferenceName,
        preliminaryPhaseDeadline: this.preliminaryPhaseDeadline,
        firstPhaseDeadline: this.firstPhaseDeadline,
        secondPhaseDeadline: this.secondPhaseDeadline,
        thirdPhaseDeadline: this.thirdPhaseDeadline
      } as Conference)
        .subscribe(success => {
          if (success) {
            this.conferenceFailed = false;
            this.router.navigateByUrl('/chair-home');
          } else {
            this.conferenceFailed = true;
          }
        });
    }
  }

  conferenceNameChanged(conferenceName: string) {
    this.conferenceName = conferenceName;
  }

  private validateConferenceData(): void {
    if (!this.conferenceName ||
      !this.preliminaryPhaseDate || !this.preliminarySelectedTime ||
      !this.firstPhaseDate || !this.firstPhaseDate ||
      !this.secondPhaseDate || !this.secondPhaseDate ||
      !this.thirdPhaseDate || !this.thirdPhaseDate) {
      this.validData = false;
      return;
    }

    if (!this.preliminaryPhaseDeadline || !this.firstPhaseDeadline || !this.secondPhaseDeadline || !this.thirdPhaseDeadline) {
      this.validData = false;
      return;
    }

    console.log(this.preliminaryPhaseDeadline);
    console.log(this.firstPhaseDeadline);
    console.log(this.secondPhaseDeadline);
    console.log(this.thirdPhaseDeadline);

    console.log(this.firstPhaseDeadline.isAfter(this.preliminaryPhaseDeadline));
    console.log(this.secondPhaseDeadline.isAfter(this.firstPhaseDeadline));
    console.log(this.thirdPhaseDeadline.isAfter(this.secondPhaseDeadline));

    this.validData = this.firstPhaseDeadline.isAfter(this.preliminaryPhaseDeadline) &&
      this.secondPhaseDeadline.isAfter(this.firstPhaseDeadline) &&
      this.thirdPhaseDeadline.isAfter(this.secondPhaseDeadline);
  }

  private printCreateConference() {
    console.log('valid data:' + this.validData);

    console.log('conference name:');
    console.log(this.conferenceName);

    console.log('preliminary phase');
    console.log(this.preliminaryPhaseDate);
    console.log(this.preliminarySelectedTime);
    console.log(this.preliminaryPhaseDeadline);

    console.log('first phase');
    console.log(this.firstPhaseDate);
    console.log(this.firstPhaseSelectedTime);
    console.log(this.firstPhaseDeadline);

    console.log('second phase');
    console.log(this.secondPhaseDate);
    console.log(this.secondPhaseSelectedTime);
    console.log(this.secondPhaseDeadline);

    console.log('third phase');
    console.log(this.thirdPhaseDate);
    console.log(this.thirdPhaseSelectedTime);
    console.log(this.thirdPhaseDeadline);
  }

  private formatConferenceData() {
    if (this.conferenceName) {
      this.conferenceName = this.conferenceName.trim();
    }
    if (this.preliminaryPhaseDate && this.preliminarySelectedTime) {
      this.preliminaryPhaseDeadline = this.formatDeadline(this.preliminaryPhaseDate, this.preliminarySelectedTime);
    }
    if (this.firstPhaseDate && this.firstPhaseSelectedTime) {
      this.firstPhaseDeadline = this.formatDeadline(this.firstPhaseDate, this.firstPhaseSelectedTime);
    }
    if (this.secondPhaseDate && this.secondPhaseSelectedTime) {
      this.secondPhaseDeadline = this.formatDeadline(this.secondPhaseDate, this.secondPhaseSelectedTime);
    }
    if (this.thirdPhaseDate && this.thirdPhaseSelectedTime) {
      this.thirdPhaseDeadline = this.formatDeadline(this.thirdPhaseDate, this.thirdPhaseSelectedTime);
    }

  }

  private formatDeadline(date: Date, time: string) {
    return moment({
      year: date.getFullYear(),
      month: date.getMonth() + 1,
      day: date.getDate(),
      hour: parseInt(time.split(':')[0], 10),
      minute: parseInt(time.split(':')[1], 10),
      second: 0,
      millisecond: 0
    });
  }
}
