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
  publicAnswer?: Resp = undefined;

  constructor(private http: HttpClient) {}

  public login() {
    window.location.href = '/oauth2/authorization/client';
  }

  public resource() {
    this.http.get<Resp>('/api/resources').subscribe(ans => {
      this.answer = ans;
    })
  }

  public publicResource() {
    this.http.get<Resp>('/api/public').subscribe(ans => {
      this.publicAnswer = ans;
    })
  }
  public getUser() {
    this.http.get<Resp>('/info').subscribe(ans => {
      this.user = ans;
    })
  }

  public logout() {
    window.location.href = '/logout';
  }

  public post() {
    this.http.post('/post', null).subscribe(resp => {
      console.log(resp)
    })
  }
}
