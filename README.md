# Description
OAuth2 flow connecting Authorization Server with an Angular SPA frontend through a backend for frontend OAuth2 client application. 

## Services
### Authorization Server (authorization) [deprecated in favor of Keycloak provider]
Authorization server with OAuth 2 Authorization server dependency. Server authenticates users trying to login via angular application and destributes/validates JWT tokens to allow access to protected resources.
### Keycloak docker authorization server
Docker container of keycloak with a premade realm and users in .json format
### OAuth2 client (bff) [deprecated in favor of less verbose springboot gateway oauth client]
Springboot application working as a BFF (backend for frontend) service managing JWTs and sending them to resource servers to access protected resources after redirecting users to authenticate themselves. 
### Angular SPA (frontend)
Frontend application allowing users to login, logout and access protected resources via backend OAuth2client without exposing Access tokens in cookies. 
### Angular ADMIN UI (adminUI)
Frontend application working as a public client with PKCE oauth2 authorization, allows to access protected resources with an access_token based on user's authorities.
### Springboot gateway (gateway) [deprecated in favor of less verbose springboot gateway oauth client]
Springboot application connecting OAuth2 client with angular frontend allowing for same origin communication.
### OAuth2 client as springboot gateway (bffGateway)
Springboot gateway application registered as an OAuth2 Client relaying obtained access tokens to resource servers via tokenRelay filters. 
### Resource Server (resourceServer)
Springboot application holding protected resources, allowing only authoried users to access them via JWTs and granted authorities. 
### Installation
#### To run authorization server (keycloak)
go into the keycloak folder ```cd .\keycloak``` and run command ``` docker compose up``` <br>
this will run a keycloak docker container on port 8080 and automatically import a realm with all users from the ./keycloak/import folder.
#### To run angular application (frontned)
```cd .\frontend\``` and ```npm install``` and then <br> ```npm start```
#### To run angular application (adminUI)
```cd .\adminUI\``` and ```npm install``` and then <br> ```npm start```
#### Accessing endpoints
Run authorization server, then all other backend applications and access the angular frontend with <br>
``` http://localhost:9050/angular/ ```. <br> 
or ```http://localhost:4200``` to access AdminUI frontend application.
#### Login-in
To login click ```LOGIN``` button and type ```user:12345``` when asked for credentials. <br> To access protected resources click ```Get resources``` button.

