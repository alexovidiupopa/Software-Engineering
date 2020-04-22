import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DashboardComponent } from './components/dashboard/dashboard.component';
import { HeroesComponent } from './components/heroes/heroes.component';
import { HeroDetailComponent } from './components/hero-detail/hero-detail.component';
import {ManagePCComponent} from './components/manage-pc/manage-pc.component';
import {CreateConferenceComponent} from './components/create-conference/create-conference.component';
import {PcDetailComponent} from './components/pc-detail/pc-detail.component';
import {HomeComponent} from "./components/home";
import {AuthGuard} from "./_helper";
import {PcHomeComponent} from "./components/pc-home/pc-home.component";
import {ChairHomeComponent} from "./components/chair-home";
import {LoginComponent} from "./components/login";
import {RegisterComponent} from "./components/register";

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'detail/:id', component: HeroDetailComponent },
  { path: 'pc/detail/:id', component: PcDetailComponent },
  { path: 'heroes', component: HeroesComponent },
  { path: 'create-conference', component: CreateConferenceComponent },
  { path: 'manage-pcs', component: ManagePCComponent},
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'pc-home', component: PcHomeComponent},
  { path: 'chair-home', component: ChairHomeComponent},
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },

  // otherwise redirect to home
  { path: '**', redirectTo: 'home' }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
