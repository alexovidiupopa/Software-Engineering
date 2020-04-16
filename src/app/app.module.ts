import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
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
    ReactiveFormsModule
    
    
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
