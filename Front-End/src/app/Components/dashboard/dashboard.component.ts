import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/auth.service';
import {Router} from '@angular/router';
import { SocialAuthService, SocialUser } from 'angularx-social-login';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})

export class DashboardComponent implements OnInit {

  constructor(private auth:AuthService,private route:Router) { }

  user:SocialUser;

  ngOnInit(): void {
    //this.user = this.auth.user;
    this.user = JSON.parse(localStorage.getItem("userData"));
  }

  signOut() : any{
    this.auth.signingOut();
  }
}
