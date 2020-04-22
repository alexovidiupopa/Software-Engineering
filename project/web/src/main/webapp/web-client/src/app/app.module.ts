import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatInputModule} from '@angular/material/input';
import {MAT_DATE_FORMATS, MAT_DATE_LOCALE, MatNativeDateModule} from '@angular/material/core';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatCardModule} from '@angular/material/card';
import {NgxMaterialTimepickerModule} from 'ngx-material-timepicker';
import {MatButtonModule} from '@angular/material/button';
import { AppComponent } from './components/app/app.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { HeroDetailComponent } from './components/hero-detail/hero-detail.component';
import { HeroesComponent } from './components/heroes/heroes.component';
import { MessagesComponent } from './components/messages/messages.component';
import { HttpClientModule } from '@angular/common/http';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { AppRoutingModule } from './app-routing.module';
import { HeroSearchComponent } from './components/hero-search/hero-search.component';
import { CreateConferenceComponent } from './components/create-conference/create-conference.component';
import { AmazingTimePickerModule } from 'amazing-time-picker'; // this line you need
import {MatListModule} from '@angular/material/list';
import {MAT_FORM_FIELD_DEFAULT_OPTIONS} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import { ManagePCComponent } from './components/manage-pc/manage-pc.component';
import { PcDetailComponent } from './components/pc-detail/pc-detail.component';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    MatButtonModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatDatepickerModule,
    MatIconModule,
    MatListModule,
    MatSnackBarModule,
    AmazingTimePickerModule,
    ReactiveFormsModule,
    MatCardModule,
    MatNativeDateModule,
    NgxMaterialTimepickerModule
  ],
  providers: [
    { provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, useValue: { appearance: 'fill' } },
    {provide: MAT_DATE_LOCALE, useValue: 'ro-RO'}
  ],
  declarations: [
    AppComponent,
    DashboardComponent,
    HeroesComponent,
    HeroDetailComponent,
    MessagesComponent,
    HeroSearchComponent,
    CreateConferenceComponent,
    ManagePCComponent,
    PcDetailComponent
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
