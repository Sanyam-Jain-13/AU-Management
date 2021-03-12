import { Injectable } from '@angular/core';
import { HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  public getAllUsers()
  {
    return this.http.get("http://localhost:8080/user/getAllUsers");
  }

  public getCurrentUser(userId : String)
  {
    console.log(userId);
    return this.http.get("http://localhost:8080/user/getUser/"+userId);
  }

}
