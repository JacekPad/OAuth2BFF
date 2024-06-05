# Description
OAuth2 flow connecting Authorization Server with an Angular SPA (frontend) frontend through a backend for frontend OAuth2 client application and a SPA application (adminUI) obtaining tokens directly from authorization server, with session storage for token storage.

## Services
### Keycloak docker authorization server (keycloak)
Docker container of keycloak with a premade realm and users in .json format
### Angular SPA (frontend)
Frontend application allowing users to login, logout and access protected resources via backend OAuth2client without exposing Access tokens in cookies. 
### Angular ADMIN UI (adminUI)
Frontend application working as a public client with PKCE oauth2 authorization, allows to access protected resources with an access_token based on user's authorities.
### OAuth2 client as springboot gateway (bffGateway)
Springboot gateway application registered as an OAuth2 Client relaying obtained access tokens to resource servers via tokenRelay filters. 
### Resource Server (resourceServer)
Springboot application holding protected resources, allowing only authoried users to access them via JWTs and granted authorities. 
### Installation
#### To run authorization server (keycloak)
go into the keycloak folder ```cd .\keycloak``` and run command ``` docker compose up``` <br>
this will run a keycloak docker container on port 8080 and automatically import a realm with all users from the ./keycloak/import folder.
#### To run angular application (frontned) [connected via spring gateway]
```cd .\frontend\``` and ```npm install``` and then <br> ```npm start```
#### To run angular application (adminUI) [public client]
```cd .\adminUI\``` and ```npm install``` and then <br> ```npm start```
#### Accessing endpoints
Run keycloak authorization server container, then the backend applications (bffGateway and resourceServer) and access the angular frontend with <br>
``` http://localhost:9090/angular/ ``` for frontend connected via gateway backend. <br> 
```http://localhost:4100``` for frontend connected directly to authorzation server via a public client setting.
#### Login-in
To login click ```LOGIN``` button and type ```user:12345``` when asked for credentials. <br> To access protected resources click ```Get resources``` button.

