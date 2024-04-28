import { Component } from '@angular/core';

@Component({
  selector: 'app-login-error',
  templateUrl: './login-error.component.html',
  styleUrl: './login-error.component.css'
})
export class LoginErrorComponent {

  constructor() {}

  public login() {
    window.location.href = '/oauth2/authorization/keycloak';
  }
}
