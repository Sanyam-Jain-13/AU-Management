import { Component, OnInit } from '@angular/core';
import {SocialAuthService, SocialUser} from 'angularx-social-login';
import { AuthService } from 'src/app/auth.service';
import { Router} from '@angular/router'
import { User } from 'src/app/Models/user.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private aService : SocialAuthService,private auth:AuthService,private router:Router) { }

  user:SocialUser;
  userObj:User = new User( "105623594884336759836", "Sanyam","Jain",
  "sanyam.jain@accolitedigital.com",
  "https://lh6.googleusercontent.com/-LADP-mc4yq0/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucm1BIiB82gQWHphttkmcCX-Wsx5Aw/s96-c/photo.jpg"
  );

  ngOnInit(): void {

    this.aService.authState.subscribe((user)=>{
      this.user = user;
      //this.userObj = new User(this.user.id,this.user.firstName,this.user.lastName,this.user.email,this.user.photoUrl);

    });

    if(localStorage.getItem("APP_TOKEN")){
      this.router.navigate(['']);
    }
  }

  async signInWithGoogle() {

    await this.auth.signingInWithGoogle();
    console.log("after await");

    // this.auth.addUser(this.userObj).subscribe((data)=>{
    //   console.log("User Added!");
    // });
  }

  signOut() : any{
    this.auth.signingOut();
  }
}
