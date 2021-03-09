import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TrendsService {
  BASE_URL='http://localhost:8080/trends';
  GET_LOCATION_LABEL='/getLocation';
  GET_LOCATION_COUNT='/getLocationCount';
  GET_SKILL_LABEL='/getSkills';
  GET_SKILL_COUNT='/getSkillsCount';
  GET_YOY_LABEL='/getYoY';
  GET_YOY_COUNT='/getYoYCount';

  constructor(private http:HttpClient) { }

  public getLocationLabel() : Observable<any>{
    return this.http.get(this.BASE_URL+this.GET_LOCATION_LABEL);
  }
  public getLocationCount() : Observable<any>{
    return this.http.get(this.BASE_URL+this.GET_LOCATION_COUNT);
  }
  public getSkillLabel() : Observable<any>{
    return this.http.get(this.BASE_URL+this.GET_SKILL_LABEL);
  }
  public getSkillCount() : Observable<any>{
    return this.http.get(this.BASE_URL+this.GET_SKILL_COUNT);
  }
  public getYoYLabel() : Observable<any>{
    return this.http.get(this.BASE_URL+this.GET_YOY_LABEL);
  }
  public getYoYCount() : Observable<any>{
    return this.http.get(this.BASE_URL+this.GET_YOY_COUNT);
  }
  public getYoYLocationLabel(location:string) : Observable<any>{
    return this.http.get(this.BASE_URL+this.GET_YOY_LABEL+'/'+location);
  }
  public getYoYLocationCount(location:string) : Observable<any>{
    return this.http.get(this.BASE_URL+this.GET_YOY_COUNT+'/'+location);
  }
}
