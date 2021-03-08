import { Injectable } from '@angular/core';
import {SocialAuthService} from 'angularx-social-login';
import {SocialUser, GoogleLoginProvider} from 'angularx-social-login';
import { Router} from '@angular/router'

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  user : SocialUser;

  constructor(private aService : SocialAuthService,private router:Router) { }

  public signingInWithGoogle():any{
    this.aService.signIn(GoogleLoginProvider.PROVIDER_ID).then((user)=>{

      this.user = user;
      localStorage.setItem("APP_TOKEN", this.user.idToken);
      localStorage.setItem("userData",JSON.stringify(this.user));
      this.router.navigate(['dashboard']);

    },(error)=>{
      console.log(error);
    });
  }

  public signingOut():any{
    this.aService.signOut();
    localStorage.removeItem("APP_TOKEN");
    localStorage.removeItem("userData");
    this.router.navigate(['home']);
  }

  public isAuthenticated(): boolean {
    console.log(this.user);
    if (!localStorage.getItem("APP_TOKEN")) {
      return false;
    }
    return true;
  }

}
