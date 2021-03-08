import { Injectable } from '@angular/core';
import {SocialAuthService} from 'angularx-social-login';
import {SocialUser, GoogleLoginProvider} from 'angularx-social-login';
import { Router} from '@angular/router'
import {User} from './Models/user.model';
import { HttpClient, HttpClientModule,HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  user : SocialUser;
  userObj : User;
  addUserUrl : string = "http://localhost:8080/user/add";

  constructor(private aService : SocialAuthService,private router:Router,public http:HttpClient) { }

  public signingInWithGoogle():any{

    this.aService.signIn(GoogleLoginProvider.PROVIDER_ID).then((user)=>{

      this.user = user;
      this.userObj = new User(this.user.id,this.user.firstName,this.user.lastName,this.user.email,this.user.photoUrl);

      localStorage.setItem("APP_TOKEN", this.user.id);
      localStorage.setItem("userData",JSON.stringify(this.user));

      this.addUser(this.userObj).subscribe((data)=>{
        console.log("User Added!");
      });

      console.log(this.userObj);
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
    if (!localStorage.getItem("APP_TOKEN")) {
      return false;
    }
    return true;
  }

  //Adding user details in database
  public addUser(user : User) : any
  {
    let httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Methods':'GET,POST,OPTIONS,DELETE,PUT'
    });

    let options = {
        headers: httpHeaders
    };

    return this.http.post(this.addUserUrl,user,options);
  }

}
