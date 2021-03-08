import { Component, OnInit, ViewChild,TemplateRef } from '@angular/core';
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

  //-----------------------------------------------------------------------
  //ALL THE VIEW TEMPLATES
  @ViewChild('CreateOpportunityForm') addTemplate: TemplateRef < any > ;
  public addDialog: MatDialogRef < TemplateRef < any >> ;

  @ViewChild('snackBarTemplate')
  snackBarTemplate: TemplateRef < any > ;
  //-----------------------------------------------------------------------

  constructor(
    private fb:FormBuilder,
    public dialog: MatDialog,
    public snackBar: MatSnackBar,
    private OppoService : OpportunityService) { }

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

    this.snackBarConf.duration = 3 * 1000;
    this.snackBarConf.horizontalPosition = 'end';
    this.snackBarConf.verticalPosition = 'bottom';
    this.snackBarConf.announcementMessage = "Running";

  }
  //-----------------------------------------------------------------------

  displayedColumns: string[] = ['oppid', 'userName', 'userEmail', 'description', 'location', 'endDate', 'skills', 'minxp', 'demand','getdetails'];
  dataSource = new MatTableDataSource < Opportunity > ();

  //-----------------------------------------------------------------------

  //Create opportunity dialog box
  public openCreateOpportunity() : void{
      this.defaultOpportunity.default();
      this.addOpportunityForm  = this.fb.group(this.defaultOpportunity);
      const dialogConfig = new MatDialogConfig();
      dialogConfig.restoreFocus = false;
      dialogConfig.autoFocus = false;
      dialogConfig.role = 'dialog';
      dialogConfig.disableClose = true;
      this.addDialog = this.dialog.open(this.addTemplate, dialogConfig);
  }

  //-----------------------------------------------------------------------

  //on clicking cancel button in Create and Edit Opportunity
  public onCancel(data : any) {
    let msg : any;
    if (data == 'Add') {
        this.addDialog.close();
        msg = "Creating Opportunity aborted !!";
    }
    this.snackBarConf.panelClass = 'red-snackbar';
    this.snackBar.open(msg,'', this.snackBarConf);
  }

  public onSubmit(data : any)
  {
    // date: ""
    // demand: 0
    // description: ""
    // endDate: "NaN-aN-aN"
    // id: 0
    // location: ""
    // minExperience: 0
    // skills: ""
    // userId: "105623594884336759836"

    //console.log(data);
    const EndingDate = new Date(data.date);
    const day = ("0" + EndingDate.getDate()).slice(-2);
    const month = ("0" + (EndingDate.getMonth() + 1)).slice(-2);
    const year = EndingDate.getFullYear();
    const FinalEndingDate = year+"-"+month+"-"+day;

    data.date = FinalEndingDate;
    data.userId = this.user.id;

    this.OppoService.addOpportunity(data).subscribe((data)=>{
      this.snackBarConf.panelClass = 'green-snackbar';
      this.snackBar.open("Opportunity Created Successfully",'', this.snackBarConf);
      this.addDialog.close();
    });

    console.log(data);
  }
}
