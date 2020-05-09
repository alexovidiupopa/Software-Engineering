import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ManagePCComponent} from './components/manage-pc/manage-pc.component';
import {CreateConferenceComponent} from './components/create-conference/create-conference.component';
import {PcDetailComponent} from './components/pc-detail/pc-detail.component';
import {AuthGuard} from './helper';
import {HomeComponent} from './components/home';
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
import {RoomsComponent} from './components/rooms/rooms.component';
import {AssignReviewerComponent} from './components/assign-reviewer/assign-reviewer.component';


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
  {path: 'conference/edit', component: UpdateConferenceComponent},
  {path: 'conference/structure', component: StructureConferenceComponent},
  {path: 'review/assign', component: AssignReviewerComponent},
  {path: 'review/pc', component: PcReviewComponent},
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
  // otherwise redirect to home
  {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
