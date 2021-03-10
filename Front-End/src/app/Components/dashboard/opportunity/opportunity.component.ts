import { Component, OnInit, ViewChild,TemplateRef, Output, Input } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import {MatSort} from '@angular/material/sort';
import{Opportunity} from '../../../Models/opportunity.model';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import {MatDialog, MatDialogConfig, MatDialogRef} from '@angular/material/dialog';
import {MatSnackBar, MatSnackBarConfig} from '@angular/material/snack-bar';
import { SocialUser } from 'angularx-social-login';
import {OpportunityService} from '../../../Services/opportunity.service';
import { UserService } from 'src/app/Services/user.service';
import { User } from 'src/app/Models/user.model';
import * as EventEmitter from 'node:events';

@Component({
  selector: 'app-opportunity',
  templateUrl: './opportunity.component.html',
  styleUrls: ['./opportunity.component.css']
})

export class OpportunityComponent implements OnInit {

  //ALL VARIABLES

  addOpportunityForm : FormGroup;
  minDate : Date;
  defaultOpportunity : Opportunity;
  snackBarConf = new MatSnackBarConfig();
  user:SocialUser;

  //Create Opportunity Form Values
  data:Opportunity=new Opportunity();

  //opportunities : any;
  dataSource = new MatTableDataSource < Opportunity > ();
  allUser : any;
  currentUser : any;
  displayedColumns: string[] = ['oppid', 'userName', 'userEmail', 'description', 'location', 'endDate', 'skills', 'minxp', 'demand','getdetails'];


  //-----------------------------------------------------------------------
  //ALL THE VIEW TEMPLATES
  @ViewChild('CreateOpportunityForm') addTemplate: TemplateRef < any > ;
  public addDialog: MatDialogRef < TemplateRef < any >> ;

  @ViewChild('EditOpportunityForm') editTemplate : TemplateRef< any >;
  public editDialog : MatDialogRef < TemplateRef < any>>;

  @ViewChild('snackBarTemplate')
  snackBarTemplate: TemplateRef < any > ;

  @ViewChild(MatPaginator, {
    static: true
  }) paginator: MatPaginator;

  @ViewChild(MatSort, {
    static: true
  }) sort: MatSort;

  //-----------------------------------------------------------------------

  constructor(
    private fb:FormBuilder,
    public dialog: MatDialog,
    public snackBar: MatSnackBar,
    private OppoService : OpportunityService,
    private UserService : UserService) { }

  //-----------------------------------------------------------------------

  ngOnInit(): void {

    this.defaultOpportunity = new Opportunity();
    this.minDate = new Date();
    this.user = JSON.parse(localStorage.getItem("userData"));

    this.addOpportunityForm  = this.fb.group({
      id: '0',
      userId:'0',
      description: new FormControl('', [Validators.required]),
      location: new FormControl('', [Validators.required]),
      date: new FormControl('', [Validators.required]),
      skills: new FormControl('', [Validators.required]),
      demand: new FormControl('', [Validators.required]),
      minExperience: new FormControl('', [Validators.required]),
    });

    this.UserService.getCurrentUser(this.user.id).subscribe((data : any)=>{
      //current signed in user
      this.currentUser = data;
      this.UserService.getAllUsers().subscribe((data : any) =>{
        //all users present
        this.allUser = data;
        console.log(this.allUser);
        this.OppoService.getAllOpportunities().subscribe((data : any)=>{
          //this.opportunities = data;
          this.dataSource.data = data;
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.sort;
        });
      })
    })

    this.snackBarConf.duration = 3 * 1000;
    this.snackBarConf.horizontalPosition = 'end';
    this.snackBarConf.verticalPosition = 'bottom';
    this.snackBarConf.announcementMessage = "Running";

  }
  //-----------------------------------------------------------------------

  //######################################################

  //Create opportunity dialog box
  public openCreateOpportunity() : void{
      this.defaultOpportunity.default();
      this.addOpportunityForm  = this.fb.group(this.defaultOpportunity);
      const dialogConfig = new MatDialogConfig();
      dialogConfig.restoreFocus = false;
      dialogConfig.autoFocus = false;
      dialogConfig.role = 'dialog';
      dialogConfig.disableClose = true;
      dialogConfig.panelClass = "add-dialog-box";
      this.addDialog = this.dialog.open(this.addTemplate, dialogConfig);
  }

  //######################################################

  public openEditOpportunityDialog(data : any)
  {
    this.addOpportunityForm = this.fb.group(data);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.restoreFocus = false;
    dialogConfig.autoFocus = false;
    dialogConfig.role = 'dialog';
    dialogConfig.disableClose = true;
    this.editDialog = this.dialog.open(this.editTemplate, dialogConfig);
  }

  //######################################################

  //on clicking cancel button in Create and Edit Opportunity
  public onCancel(data : any) {
    let msg : any;
    if (data == 'Add') {
        this.addDialog.close();
        msg = "Creating Opportunity aborted !";
    }
    if (data == 'Edit') {
      this.editDialog.close();
      msg = "Editing Opportunity Cancelled !";
  }
    this.snackBarConf.panelClass = 'red-snackbar';
    this.snackBar.open(msg,'', this.snackBarConf);
  }

  //######################################################

  //on submitting new opportunity object in Db
  public onSubmit()
  {

    this.data=this.addOpportunityForm.value;
    this.data.userId = String(this.user.id);

    var event = new Date(this.data.date);
    let date1 = JSON.stringify(event);

    date1 = date1.slice(1,11);

    this.data.date=date1;
    this.data.location=this.data.location.charAt(0).toUpperCase() + this.data.location.slice(1);


    const words = this.data.skills.split(",");
    this.data.skills=words.map((word) => {
      return word[0].toUpperCase() + word.substring(1);
    }).join(",");


    this.OppoService.addOpportunity(this.data).subscribe((data)=>{
      this.OppoService.getAllOpportunities().subscribe((res : any[])=>{
        this.dataSource.data = res;
        this.dataSource.paginator = this.paginator;
      })
      this.snackBarConf.panelClass = 'green-snackbar';
      this.snackBar.open("Opportunity Created Successfully",'', this.snackBarConf);
      this.addDialog.close();
    });

  }
  //######################################################

  //On clicking edit button in editing opportunity
  public onEdit()
  {
    //console.log(data);
    this.data=this.addOpportunityForm.value;
    this.data.userId = String(this.user.id);

    var event = new Date(this.data.date);
    let date1 = JSON.stringify(event)
    date1 = date1.slice(1,11)

    this.data.date=date1;
    this.data.location=this.data.location.charAt(0).toUpperCase() + this.data.location.slice(1);

    //console.log(this.data.location);

    const words = this.data.skills.split(",");
    this.data.skills=words.map((word) => {
      return word[0].toUpperCase() + word.substring(1);
    }).join(",");


    this.OppoService.editOpportunity(this.data).subscribe((data)=>{
      this.OppoService.getAllOpportunities().subscribe((res : any[])=>{
        this.dataSource.data = res;
        this.dataSource.paginator = this.paginator;
      })
      this.snackBarConf.panelClass = 'green-snackbar';
      this.snackBar.open("Opportunity Updated Successfully",'', this.snackBarConf);
      this.editDialog.close();
    });

  }

  //######################################################

  //on deleting particular row from Db
  public deleteRow(id : any){
    console.log(id);
    this.OppoService.deleteOpportunity(id).subscribe((data)=>{
      this.OppoService.getAllOpportunities().subscribe((data : any)=>{
        this.dataSource.data = data;
        this.dataSource.paginator = data;
      });

      this.snackBarConf.panelClass = "green-snackbar";
      this.snackBar.open("Opportunity Deleted!",'',this.snackBarConf);
    })
  }

  //######################################################

  public applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.dataSource.filter = filterValue;
    // this.dataSource.filterPredicate = (data: any, filterValue) => {
    //   const dataStr =JSON.stringify(data).toLowerCase();
    //   console.log(dataStr);
    //   return dataStr.indexOf(filterValue) != -1;
    // }
  }


}
