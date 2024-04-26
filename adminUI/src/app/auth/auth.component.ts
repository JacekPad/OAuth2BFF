import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { OAuthService } from 'angular-oauth2-oidc';

interface Answer {
  name: string
}

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrl: './auth.component.css'
})
export class AuthComponent {


  answerPublic?: Answer = undefined;
  answerPrivate?: Answer = undefined;


  constructor(private oauthService: OAuthService, private http: HttpClient) {
  }


  getPrivateResource() {
    this.http.get<Answer>('http://localhost:8090/resources').subscribe(ans => {
      this.answerPrivate = ans;
    })
  }


  getPublicResource() {
    this.http.get<Answer>('http://localhost:8090/public').subscribe(ans => {
      this.answerPublic = ans;
    })
  }

  logout() {
    console.log('loggin out');

    this.oauthService.logOut();
  }

  login() {
    console.log('login in');

  }

  refresh() {
    console.log("refreshing");
     this.oauthService.refreshToken();
    }


}
