import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

interface User {
  name: string
}


@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrl: './auth.component.css'
})
export class AuthComponent {
  user?: User = undefined;
  answer: string = 'none'

  constructor(private http: HttpClient) {}

  public login() {
    window.location.href = '/bff/oauth2/authorization/client';
  }

  public resource() {
    this.http.get<string>('/bff/resources').subscribe(ans => {
      this.answer = ans;
    })
  }

  public getUser() {
    this.http.get<User>('/bff/info').subscribe(ans => {
      this.user = ans;
    })
  }

  public logout() {
    window.location.href = '/bff/logout';
  }

  public post() {
    this.http.post('/bff/post', null).subscribe(resp => {
      console.log(resp)
    })
  }
}
