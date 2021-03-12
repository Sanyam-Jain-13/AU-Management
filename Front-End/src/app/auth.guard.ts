import { Injectable } from '@angular/core';
import { CanActivate, Router} from '@angular/router';
import { Observable,of as observableOf } from 'rxjs';
import {AuthService} from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private auth : AuthService,
    private router:Router){ }

    canActivate(): Observable<boolean>{
        if (!this.auth.isAuthenticated()) {
          this.router.navigate(['login']);
          return observableOf(false);
        }
        return observableOf(true);
      }
}
