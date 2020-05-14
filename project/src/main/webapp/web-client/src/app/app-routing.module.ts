import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ManagePCComponent} from './components/chair/manage-pc/manage-pc.component';
import {CreateConferenceComponent} from './components/chair/create-conference/create-conference.component';
import {PcDetailComponent} from './components/chair/pc-detail/pc-detail.component';
import {PcHomeComponent} from './components/pc/pc-home/pc-home.component';
import {ChairHomeComponent} from './components/chair/chair-home';
import {LoginComponent} from './components/general/login';
import {RegisterComponent} from './components/general/register/register.component';
import {AuthorRegisterComponent} from './components/author/author-register/author-register.component';
import {PcRegisterComponent} from './components/pc/pc-register/pc-register.component';
import {UploadAbstractComponent} from './components/author/upload-abstract/upload-abstract.component';
import {AuthorPapersComponent} from './components/author/author-papers/author-papers.component';
import {PaperDetailComponent} from './components/author/paper-detail/paper-detail.component';
import {HomepageComponent} from './components/general/homepage/homepage.component';
import {AuthorHomeComponent} from './components/author/author-home/author-home.component';
import {AllReviewsComponent} from './components/chair/all-reviews/all-reviews.component';
import {BiddingComponent} from './components/pc/bidding/bidding.component';
import {BuyTicketComponent} from './components/ticket/buy-ticket/buy-ticket.component';
import {UpdateConferenceComponent} from './components/chair/update-conference/update-conference.component';
import {StructureConferenceComponent} from './components/chair/structure-conference/structure-conference.component';
import {PcReviewComponent} from './components/pc/pc-review/pc-review.component';
import {RoomsComponent} from './components/chair/rooms/rooms.component';
import {AssignReviewerComponent} from './components/chair/assign-reviewer/assign-reviewer.component';
import {CheckoutComponent} from './components/ticket/checkout/checkout.component';
import {PaymentComponent} from './components/ticket/payment/payment.component';
import {SuccessBuyComponent} from './components/ticket/success-buy/success-buy.component';
import {PaperDetailDecisionComponent} from "./components/chair/paper-detail-decision/paper-detail-decision.component";


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
  {path: 'conference/edit', component: UpdateConferenceComponent},
  {path: 'conference/structure', component: StructureConferenceComponent},
  {path: 'review/assign', component: AssignReviewerComponent},
  {path: 'review/pc/:id', component: PcReviewComponent},
  {path: 'rooms', component: RoomsComponent},
  {path: '', component: HomepageComponent},
  {path: 'chair-home', component: ChairHomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'author-register', component: AuthorRegisterComponent},
  {path: 'pc-register', component: PcRegisterComponent},
  {path: 'paper/upload', component: UploadAbstractComponent},
  {path: 'paper/all', component: AuthorPapersComponent},
  {path: 'paper/detail/:id', component: PaperDetailComponent},
  {path: 'paper/decision/:id', component:PaperDetailDecisionComponent},
  // otherwise redirect to home
  {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
