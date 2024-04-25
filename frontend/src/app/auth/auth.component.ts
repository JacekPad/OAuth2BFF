import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

interface Resp {
  name: string
}


@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrl: './auth.component.css'
})
export class AuthComponent {
  user?: Resp = undefined;
  answer?: Resp = undefined;

  constructor(private http: HttpClient) {}

  public login() {
    window.location.href = '/bff/oauth2/authorization/client';
  }

  public resource() {
    this.http.get<Resp>('/bff/resources').subscribe(ans => {
      this.answer = ans;
    })
  }

  public getUser() {
    this.http.get<Resp>('/bff/info').subscribe(ans => {
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
