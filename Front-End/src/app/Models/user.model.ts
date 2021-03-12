export class User {

  public userId:String;
  public firstName:String;
  public lastName:String;
  public email:String;
  public photoUrl:String;

  public constructor(userId:String,firstName:String,lastName:string,email:String,photoUrl:String) {
    this.userId = userId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.photoUrl = photoUrl;
  }

}
