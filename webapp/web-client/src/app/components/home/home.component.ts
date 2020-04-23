import {Component, OnInit} from '@angular/core';
import {MatSliderChange} from '@angular/material/slider';
import {animate, state, style, transition, trigger} from '@angular/animations';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  animations: [trigger('fade', [
    state('void', style({opacity: 0})),
    transition('void <=> *', [animate('0.5s ease-in-out')])
  ])]
})


export class HomeComponent implements OnInit {
  foods: Food[] = [
    {value: 'steak-0', viewValue: 'Steak'},
    {value: 'pizza-1', viewValue: 'Pizza'},
    {value: 'tacos-2', viewValue: 'Tacos'}
  ];

  counter = 0;
  slideItems = [
    {src: 'https://placeimg.com/600/600/any', title: 'Title 1'},
    {src: 'https://placeimg.com/600/600/nature', title: 'Title 2'},
    {src: 'https://placeimg.com/600/600/sepia', title: 'Title 3'},
    {src: 'https://placeimg.com/600/600/people', title: 'Title 4'},
    {src: 'https://placeimg.com/600/600/tech', title: 'Title 5'}
  ];

  sliderValue: number;

  constructor() {
  }

  showNextImage() {

    this.counter = (this.counter + 1) % this.slideItems.length;

  }

  showPreviousImage() {
    if (this.counter == 0) {
      this.counter = this.slideItems.length - 1;
    } else {
      this.counter = (this.counter - 1);
    }
  }

  ngOnInit() {
  }

  onInputChange($event: MatSliderChange) {
    console.log($event.value);
    this.sliderValue = $event.value;

  }
}

interface Food {
  value: string;
  viewValue: string;
}
