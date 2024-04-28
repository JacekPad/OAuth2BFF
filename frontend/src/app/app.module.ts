import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthComponent } from './auth/auth.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { Interceptor403 } from './auth/Interceptor403';
import { LoginErrorComponent } from './login-error/login-error.component';

@NgModule({
  declarations: [
    AppComponent,
    AuthComponent,
    LoginErrorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    {
    provide: HTTP_INTERCEPTORS,
    useClass: Interceptor403,
    multi: true
  }
],
  bootstrap: [AppComponent]
})
export class AppModule { }
