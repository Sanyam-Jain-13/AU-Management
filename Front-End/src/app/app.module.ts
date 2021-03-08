import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './Components/login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { SocialAuthServiceConfig} from 'angularx-social-login';
import { SocialLoginModule, GoogleLoginProvider} from 'angularx-social-login';
import { HttpClientModule} from '@angular/common/http';
import { HomeComponent } from './Components/home/home.component';
import { TrendsComponent } from './Components/dashboard/trends/trends.component';
import { AuditComponent } from './Components/dashboard/audit/audit.component';
import { SearchComponent } from './Components/dashboard/search/search.component'
import{ AuthService } from './auth.service';
import{ AuthGuard } from './auth.guard';
import { DashboardComponent } from './Components/dashboard/dashboard.component';
import { OpportunityComponent } from './Components/dashboard/opportunity/opportunity.component';

import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { MatCardModule} from '@angular/material/card';
import { MatToolbarModule} from '@angular/material/toolbar';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import {MatDatepickerModule } from '@angular/material/datepicker';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import {MatIconModule} from '@angular/material/icon';
import { MatSortModule} from '@angular/material/sort';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    TrendsComponent,
    AuditComponent,
    SearchComponent,
    DashboardComponent,
    OpportunityComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    SocialLoginModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,

    MatCardModule,MatToolbarModule,MatTableModule,MatPaginatorModule,MatSortModule,MatIconModule,
    MatFormFieldModule,MatInputModule,MatDatepickerModule,MatNativeDateModule,MatDialogModule,MatButtonModule,
    MatSnackBarModule

  ],
  providers: [
    AuthGuard,
    AuthService,
    {
      provide: 'SocialAuthServiceConfig',
      useValue: {
        autoLogin: false,
        providers: [
          {
            id: GoogleLoginProvider.PROVIDER_ID,
            provider: new GoogleLoginProvider(
              '407910661247-pjugmolkjfusdq2cn6brhguk5m83jtgs.apps.googleusercontent.com'
            )
          }
        ]
      } as SocialAuthServiceConfig,
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
