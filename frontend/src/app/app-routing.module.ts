import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginErrorComponent } from './login-error/login-error.component';
import { AuthComponent } from './auth/auth.component';

const routes: Routes = [
  {path: 'login', component: LoginErrorComponent},
  {path: '', component: AuthComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
