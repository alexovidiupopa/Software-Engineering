import { Component, OnInit } from '@angular/core';
import { Hero } from '../../services/hero/hero';
import { HeroService } from '../../services/hero/hero.service';
import {MessageService} from '../../services/message/message.service';
import {HttpClient} from '@angular/common/http';
import {catchError, tap} from 'rxjs/operators';
import {Observable, of} from 'rxjs';
import {Todo} from '../../todo';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [ './dashboard.component.css' ]
})
export class DashboardComponent implements OnInit {
  heroes: Hero[] = [];
  todo: Todo;
  constructor(private heroService: HeroService) { }

  ngOnInit() {
    this.getHeroes();
  }

  getHeroes(): void {
    this.heroService.getHeroes()
      .subscribe(heroes => this.heroes = heroes.slice(1, 5));
  }

  httpRequest(id: string) {
    this.heroService.customCall(+id).subscribe(todo => this.todo = todo);
  }

}
