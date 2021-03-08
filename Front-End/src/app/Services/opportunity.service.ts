import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule,HttpHeaders } from '@angular/common/http';
import { Opportunity } from '../Models/opportunity.model';

@Injectable({
  providedIn: 'root'
})
export class OpportunityService {

  constructor(public http:HttpClient) { }
  url : string = "http://localhost:8080/opportunity/add";

  public addOpportunity(OpportunityObject : Opportunity) : any
  {
    let httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    let options = {
        headers: httpHeaders
    };

    return this.http.post(this.url,OpportunityObject,options);
  }
}
