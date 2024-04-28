import { HttpErrorResponse, HttpEvent, HttpEventType, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { Observable, catchError, of, throwError } from "rxjs";

@Injectable()
export class Interceptor403 implements HttpInterceptor {

    constructor(private router: Router){}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const authRequ = req.clone();
        return next.handle(authRequ).pipe(catchError(x=> this.handleAuthError(x)));
    }
    
    private handleAuthError(err: HttpErrorResponse): Observable<any> {
        console.log("interceptor catching stuff");
        if (err.status === 401 || err.status === 403) {
            this.router.navigateByUrl(`/login`);
            return of(err.message);
        }
        return throwError(err);
    }
}