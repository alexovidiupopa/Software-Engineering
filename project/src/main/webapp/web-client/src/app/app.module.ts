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
import {MatSelectModule} from '@angular/material/select';
import {AuthorRegisterComponent} from './components/author-register/author-register.component';
import {PcRegisterComponent} from './components/pc-register/pc-register.component';
import {UploadAbstractComponent} from './components/upload-abstract/upload-abstract.component';
import {AuthorPapersComponent} from './components/author-papers/author-papers.component';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {PaperDetailComponent} from './components/paper-detail/paper-detail.component';
import {AuthorHomeComponent} from './components/author-home/author-home.component';
import {HomepageComponent} from './components/homepage/homepage.component';
import {HeaderComponent} from './components/header/header.component';
import {AllReviewsComponent} from './components/all-reviews/all-reviews.component';
import {BiddingComponent} from './components/bidding/bidding.component';
import {BuyTicketComponent} from './components/buy-ticket/buy-ticket.component';
import {UpdateConferenceComponent} from './components/update-conference/update-conference.component';
import {StructureConferenceComponent} from './components/structure-conference/structure-conference.component';
import {PcReviewComponent} from './components/pc-review/pc-review.component';
import {AssignReviewerComponent} from './components/assign-reviewer/assign-reviewer.component';
import {MatRadioModule} from '@angular/material/radio';
import {PaperDetailDecisionComponent} from './components/paper-detail-decision/paper-detail-decision.component';
import {CheckoutComponent} from './components/checkout/checkout.component';
import {SuccessBuyComponent} from './components/success-buy/success-buy.component';
import {PaymentComponent} from './components/payment/payment.component';
import {ManageSessionComponent} from './components/manage-session/manage-session.component';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatTabsModule} from '@angular/material/tabs';
import {EditSessionComponent} from './components/edit-session/edit-session.component';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { AuthorReviewsComponent } from './components/author-reviews/author-reviews.component';

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
    MatRadioModule,
    MatFormFieldModule,
    MatSelectModule,
    MatProgressBarModule,
    MatGridListModule,
    MatTabsModule,
    MatProgressSpinnerModule,
    // MatTabsModule,
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
    AssignReviewerComponent,
    PaperDetailDecisionComponent,
    CheckoutComponent,
    SuccessBuyComponent,
    PaymentComponent,
    ManageSessionComponent,
    EditSessionComponent,
    AuthorReviewsComponent,
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
