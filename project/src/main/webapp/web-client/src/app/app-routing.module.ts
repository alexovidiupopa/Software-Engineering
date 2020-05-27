import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ManagePCComponent} from './components/manage-pc/manage-pc.component';
import {CreateConferenceComponent} from './components/create-conference/create-conference.component';
import {PcDetailComponent} from './components/pc-detail/pc-detail.component';
import {PcHomeComponent} from './components/pc-home/pc-home.component';
import {ChairHomeComponent} from './components/chair-home';
import {LoginComponent} from './components/login';
import {RegisterComponent} from './components/register/register.component';
import {AuthorRegisterComponent} from './components/author-register/author-register.component';
import {PcRegisterComponent} from './components/pc-register/pc-register.component';
import {UploadAbstractComponent} from './components/upload-abstract/upload-abstract.component';
import {AuthorPapersComponent} from './components/author-papers/author-papers.component';
import {PaperDetailComponent} from './components/paper-detail/paper-detail.component';
import {HomepageComponent} from './components/homepage/homepage.component';
import {AuthorHomeComponent} from './components/author-home/author-home.component';
import {AllReviewsComponent} from './components/all-reviews/all-reviews.component';
import {BiddingComponent} from './components/bidding/bidding.component';
import {BuyTicketComponent} from './components/buy-ticket/buy-ticket.component';
import {UpdateConferenceComponent} from './components/update-conference/update-conference.component';
import {StructureConferenceComponent} from './components/structure-conference/structure-conference.component';
import {PcReviewComponent} from './components/pc-review/pc-review.component';
import {AssignReviewerComponent} from './components/assign-reviewer/assign-reviewer.component';
import {PaperDetailDecisionComponent} from './components/paper-detail-decision/paper-detail-decision.component';
import {CheckoutComponent} from './components/checkout/checkout.component';
import {SuccessBuyComponent} from './components/success-buy/success-buy.component';
import {PaymentComponent} from './components/payment/payment.component';
import {ManageSessionComponent} from './components/manage-session/manage-session.component';
import {EditSessionComponent} from './components/edit-session/edit-session.component';
import {AuthGuard} from "./helper";
import {AuthorReviewsComponent} from "./components/author-reviews/author-reviews.component";


const routes: Routes = [
  {path: 'pc/detail/:id', component: PcDetailComponent},
  {path: 'create-conference', component: CreateConferenceComponent},
  {path: 'manage-pcs', component: ManagePCComponent},
  // {path: 'home', component: HomeComponent, canActivate: [AuthGuard]}, //fixme AuthGuard should be enabled
  {path: 'pc-home', component: PcHomeComponent},
  {path: 'author-home', component: AuthorHomeComponent},
  {path: 'reviews/all', component: AllReviewsComponent},
  {path: 'bid', component: BiddingComponent},
  {path: 'ticket', component: BuyTicketComponent},
  {path: 'checkout', component: CheckoutComponent},
  {path: 'pay/safe', component: PaymentComponent},
  {path: 'pay/end', component: SuccessBuyComponent},
  {path: 'conference/edit', component: UpdateConferenceComponent, canActivate: [AuthGuard]},
  {path: 'conference/structure', component: StructureConferenceComponent, canActivate: [AuthGuard]},
  {path: 'review/pc/:id', component: PcReviewComponent, canActivate: [AuthGuard]},
  {path: 'review/assign', component: AssignReviewerComponent, canActivate: [AuthGuard]},
  {path: '', component: HomepageComponent}, //HomeComponent
  {path: 'chair-home', component: ChairHomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'author-register', component: AuthorRegisterComponent},
  {path: 'pc-register', component: PcRegisterComponent},
  {path: 'paper/upload', component: UploadAbstractComponent},
  {path: 'paper/all', component: AuthorPapersComponent},
  {path: 'paper/detail/:id', component: PaperDetailComponent},
  {path: 'paper/decision/:id', component: PaperDetailDecisionComponent},
  {path: 'session/manage', component: ManageSessionComponent},
  {path: 'session/edit/:id', component: EditSessionComponent},
  {path: 'reviews/:id',component:AuthorReviewsComponent},
  // otherwise redirect to home
  {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
