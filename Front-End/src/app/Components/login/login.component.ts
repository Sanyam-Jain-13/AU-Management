import { Component, OnInit } from '@angular/core';
import {SocialAuthService, SocialUser} from 'angularx-social-login';
import { AuthService } from 'src/app/auth.service';
import { Router} from '@angular/router'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private aService : SocialAuthService,private auth:AuthService,private router:Router) { }

  user:SocialUser;

  ngOnInit(): void {
    
    this.aService.authState.subscribe((user)=>{
      this.user = user;
    });

    if(localStorage.getItem("APP_TOKEN")){
      this.router.navigate(['']);
    }

  }

  signInWithGoogle() : any{
    this.auth.signingInWithGoogle();
  }

  signOut() : any{
    this.auth.signingOut();
  }
}
