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
import {AppComponent} from './components/general/app/app.component';
import {HttpClientModule} from '@angular/common/http';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {AppRoutingModule} from './app-routing.module';
import {CreateConferenceComponent} from './components/chair/create-conference/create-conference.component';
import {AmazingTimePickerModule} from 'amazing-time-picker'; // this line you need
import {MatListModule} from '@angular/material/list';
import {MAT_FORM_FIELD_DEFAULT_OPTIONS, MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {ManagePCComponent} from './components/chair/manage-pc/manage-pc.component';
import {PcDetailComponent} from './components/chair/pc-detail/pc-detail.component';
import {MatSliderModule} from '@angular/material/slider';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {PcHomeComponent} from './components/pc/pc-home/pc-home.component';
import {ChairHomeComponent} from './components/chair/chair-home';
import {RegisterComponent} from './components/general/register/register.component';
import {LoginComponent} from './components/general/login';
import {MatSelectModule} from '@angular/material/select';
import {AuthorRegisterComponent} from './components/author/author-register/author-register.component';
import {PcRegisterComponent} from './components/pc/pc-register/pc-register.component';
import {UploadAbstractComponent} from './components/author/upload-abstract/upload-abstract.component';
import {AuthorPapersComponent} from './components/author/author-papers/author-papers.component';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {PaperDetailComponent} from './components/author/paper-detail/paper-detail.component';
import {AuthorHomeComponent} from './components/author/author-home/author-home.component';
import {HomepageComponent} from './components/general/homepage/homepage.component';
import {HeaderComponent} from './components/general/header/header.component';
import {AllReviewsComponent} from './components/chair/all-reviews/all-reviews.component';
import {BiddingComponent} from './components/pc/bidding/bidding.component';
import {BuyTicketComponent} from './components/ticket/buy-ticket/buy-ticket.component';
import {UpdateConferenceComponent} from './components/chair/update-conference/update-conference.component';
import {StructureConferenceComponent} from './components/chair/structure-conference/structure-conference.component';
import {PcReviewComponent} from './components/pc/pc-review/pc-review.component';
import {RoomsComponent} from './components/chair/rooms/rooms.component';
import {AssignReviewerComponent} from './components/chair/assign-reviewer/assign-reviewer.component';
import {CheckoutComponent} from './components/ticket/checkout/checkout.component';
import {SuccessBuyComponent} from './components/ticket/success-buy/success-buy.component';
import {PaymentComponent} from './components/ticket/payment/payment.component';
import {MatRadioModule} from '@angular/material/radio';
import { PaperDetailDecisionComponent } from './components/chair/paper-detail-decision/paper-detail-decision.component';

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
    MatProgressBarModule,
    MatRadioModule
  ],
  providers: [
    {provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, useValue: {appearance: 'fill'}},
    {provide: MAT_DATE_LOCALE, useValue: 'ro-RO'}
  ],
  declarations: [
    AppComponent,
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
    PaperDetailComponent,
    AuthorHomeComponent,
    HomepageComponent,
    HeaderComponent,
    AllReviewsComponent,
    BiddingComponent,
    BuyTicketComponent,
    UpdateConferenceComponent,
    StructureConferenceComponent,
    PcReviewComponent,
    RoomsComponent,
    AssignReviewerComponent,
    CheckoutComponent,
    SuccessBuyComponent,
    PaymentComponent,
    PaperDetailDecisionComponent,
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
