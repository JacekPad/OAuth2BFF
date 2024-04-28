import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

interface Resp {
  name: string
  authenticated: boolean;
}


@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrl: './auth.component.css'
})
export class AuthComponent implements OnInit {
  user: Resp | undefined;
  answer?: Resp = undefined;
  publicAnswer?: Resp = undefined;

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    this.getUser();
  }

  public login() {
    window.location.href = '/oauth2/authorization/keycloak';
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

  public isUserAuthenticated(): boolean {
    return this.user ? this.user?.authenticated : false;
  }

}
