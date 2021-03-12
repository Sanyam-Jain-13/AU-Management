import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule,HttpHeaders } from '@angular/common/http';
import { Opportunity } from '../Models/opportunity.model';

@Injectable({
  providedIn: 'root'
})
export class OpportunityService {

  constructor(public http:HttpClient) { }
  addUrl : string = "http://localhost:8080/opportunity/add";

  public addOpportunity(OpportunityObject : Opportunity) : any
  {
    let httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Methods':'GET,POST,OPTIONS,DELETE,PUT'
    });

    let options = {
        headers: httpHeaders
    };

    return this.http.post(this.addUrl,OpportunityObject,options);
  }

  public getAllOpportunities()
  {
    return this.http.get("http://localhost:8080/opportunity/getAll");
  }

  public deleteOpportunity(id) {
    return this.http.get("http://localhost:8080/opportunity/delete/"+id);
  }

  public editOpportunity(Opportunity)
  {
    return this.http.post("http://localhost:8080/opportunity/update/"+Opportunity.id,Opportunity);
  }
}
