import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MatSliderModule } from '@angular/material/slider';
// used to create fake backend
import { fakeBackendProvider } from './_helper';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { appRoutingModule} from './app.routing'
import { ReactiveFormsModule } from '@angular/forms';

import { JwtInterceptor, ErrorInterceptor } from './_helper';
import { HttpClientModule } from '@angular/common/http';
import { ChairHomeComponent } from './chair-home/chair-home.component';
import { PcHomeComponent } from './pc-home/pc-home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatCheckboxModule, MatFormFieldModule, MatIconModule, MatSlideToggleModule} from '@angular/material';
import {MatCheckbox} from '@angular/material';
import {MatSelectModule} from '@angular/material';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    ChairHomeComponent,
    PcHomeComponent,

  ],
  imports: [
    BrowserModule,
    appRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatSliderModule,
    MatSlideToggleModule,
    MatCheckboxModule,
    MatFormFieldModule,
    MatSelectModule,
    MatIconModule

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
