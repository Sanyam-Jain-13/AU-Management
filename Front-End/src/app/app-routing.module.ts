import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { AuditComponent } from './Components/dashboard/audit/audit.component';
import { HomeComponent } from './Components/home/home.component';
import { OpportunityComponent } from './Components/dashboard/opportunity/opportunity.component';
import { SearchComponent } from './Components/dashboard/search/search.component';
import { TrendsComponent } from './Components/dashboard/trends/trends.component';
import { LoginComponent } from './Components/login/login.component';
import { DashboardComponent } from './Components/dashboard/dashboard.component';
import { HttpClientModule } from '@angular/common/http';
import { YoYChartComponent } from './Components/dashboard/trends/yoy-chart/yoy-chart.component';
import { LocationChartComponent } from './Components/dashboard/trends/location-chart/location-chart.component';
import { SkillChartComponent } from './Components/dashboard/trends/skill-chart/skill-chart.component';

const routes: Routes = [
  { path:'', component:HomeComponent},
  { path:'home',component:HomeComponent},

  { path:'dashboard',component:DashboardComponent,canActivate:[AuthGuard],children:[

    { path:'',redirectTo:'dashboard/opportunity',pathMatch:'full'},
    { path: 'opportunity', component: OpportunityComponent,canActivate:[AuthGuard]},
    { path: 'search', component: SearchComponent,canActivate:[AuthGuard] },
    { path: 'trends', component: TrendsComponent,canActivate:[AuthGuard], children:[

      {path:'YoY',component:YoYChartComponent,canActivate:[AuthGuard]},
      {path:'Location',component:LocationChartComponent,canActivate:[AuthGuard]},
      {path:'Skills',component:SkillChartComponent,canActivate:[AuthGuard]}
    ] },
    
    { path: 'audit', component: AuditComponent,canActivate:[AuthGuard]}

  ]},

  { path:'login',component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes),HttpClientModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
