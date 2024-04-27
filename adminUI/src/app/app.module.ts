import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { AuthConfig, OAuthModule, OAuthService } from 'angular-oauth2-oidc';
import { AuthComponent } from './auth/auth.component';

export const authCodeFlowConfig: AuthConfig = {
  issuer: 'http://localhost:8080/realms/test',
  tokenEndpoint: 'http://localhost:8080/realms/test/protocol/openid-connect/token',
  redirectUri: window.location.origin,
  clientId: 'myweb',
  responseType: 'code',
  scope: 'openid profile',
  requireHttps: false,
  useSilentRefresh: true
}

// initialize oauth
function initializeOAuth(oauthService: OAuthService): Promise<void> {
  return new Promise((resolve) => {
    oauthService.configure(authCodeFlowConfig);
    oauthService.setupAutomaticSilentRefresh();
    oauthService.loadDiscoveryDocumentAndLogin()
    .then(() => resolve());
  });
}


@NgModule({
  declarations: [
    AppComponent,
    AuthComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    OAuthModule.forRoot({
      resourceServer:{
        allowedUrls: ['http://localhost:8090/resources'],
        sendAccessToken: true
      }
    })
  ],
  providers: [
    {provide: APP_INITIALIZER,
      useFactory: (oauthService: OAuthService) => {
        return () => {
          initializeOAuth(oauthService);
        }
      },
      multi: true,
      deps: [
        OAuthService
      ]
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
