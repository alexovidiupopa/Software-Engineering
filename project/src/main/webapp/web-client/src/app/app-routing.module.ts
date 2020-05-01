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

const routes: Routes = [
  {path: 'pc/detail/:id', component: PcDetailComponent},
  {path: 'create-conference', component: CreateConferenceComponent},
  {path: 'manage-pcs', component: ManagePCComponent},
  {path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
  {path: 'pc-home', component: PcHomeComponent},
  {path: 'chair-home', component: ChairHomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'author-register', component: AuthorRegisterComponent},
  {path: 'pc-register', component: PcRegisterComponent},
  {path: 'paper/upload', component: UploadAbstractComponent},
  {path: 'paper/all', component: AuthorPapersComponent},
  {path: 'paper/detail/:id', component: PaperDetailComponent},
  // otherwise redirect to home
  {path: '**', redirectTo: 'home'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
