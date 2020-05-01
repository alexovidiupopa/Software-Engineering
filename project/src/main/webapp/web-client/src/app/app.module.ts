import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatInputModule} from '@angular/material/input';
import {MAT_DATE_LOCALE, MatNativeDateModule} from '@angular/material/core';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatCardModule} from '@angular/material/card';
import {NgxMaterialTimepickerModule} from 'ngx-material-timepicker';
import {MatButtonModule} from '@angular/material/button';
import {AppComponent} from './components/app/app.component';
import {HttpClientModule} from '@angular/common/http';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {AppRoutingModule} from './app-routing.module';
import {CreateConferenceComponent} from './components/create-conference/create-conference.component';
import {AmazingTimePickerModule} from 'amazing-time-picker'; // this line you need
import {MatListModule} from '@angular/material/list';
import {MAT_FORM_FIELD_DEFAULT_OPTIONS, MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {ManagePCComponent} from './components/manage-pc/manage-pc.component';
import {PcDetailComponent} from './components/pc-detail/pc-detail.component';
import {MatSliderModule} from '@angular/material/slider';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {PcHomeComponent} from './components/pc-home/pc-home.component';
import {ChairHomeComponent} from './components/chair-home';
import {RegisterComponent} from './components/register/register.component';
import {LoginComponent} from './components/login';
import {HomeComponent} from './components/home';
import {MatSelectModule} from '@angular/material/select';
import {AuthorRegisterComponent} from './components/author-register/author-register.component';
import {PcRegisterComponent} from './components/pc-register/pc-register.component';
import { UploadAbstractComponent } from './components/upload-abstract/upload-abstract.component';
import { AuthorPapersComponent } from './components/author-papers/author-papers.component';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { PaperDetailComponent } from './components/paper-detail/paper-detail.component';

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
        NgxMaterialTimepickerModule,
        MatSliderModule,
        MatSlideToggleModule,
        MatCheckboxModule,

        MatFormFieldModule,
        MatSelectModule,
        MatProgressBarModule
    ],
  providers: [
    {provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, useValue: {appearance: 'fill'}},
    {provide: MAT_DATE_LOCALE, useValue: 'ro-RO'}
  ],
  declarations: [
    AppComponent,
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    ChairHomeComponent,
    PcHomeComponent,
    CreateConferenceComponent,
    ManagePCComponent,
    PcDetailComponent,
    AuthorRegisterComponent,
    PcRegisterComponent,
    UploadAbstractComponent,
    AuthorPapersComponent,
    PaperDetailComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
