import { Component, OnInit } from '@angular/core';
import { Chart } from 'chart.js';
import { TrendsService } from 'src/app/Services/trends.service';
import { MatSelectModule } from '@angular/material/select';
@Component({
  selector: 'app-skill-chart',
  templateUrl: './skill-chart.component.html',
  styleUrls: ['./skill-chart.component.css']
})
export class SkillChartComponent implements OnInit {

  skills:Array<string>=[];
  count:Array<number>=[];
  skillchart = [];

  constructor(private trendsService:TrendsService) {
    console.log("hello");
    this.trendsService.getSkillLabel().subscribe(labels=>{
      this.skills= labels;
      // console.log("from back"+labels);
      console.log(this.skills);
      this.trendsService.getSkillCount().subscribe(counts=>{
        this.count=counts;
        this.skillchart = new Chart('canvas', {
          type: 'doughnut',
          data: {
            labels: this.skills,
            datasets: [
              {
                data: this.count,
                borderColor: '#3cba9f',
                backgroundColor: [
                  "#3cb371",
                  "#0000FF",
                  "#9966FF",
                  "#f990a7",
                  "#aad2ed",
                  "#FF00FF",
                  "#4C4CFF",
                  "#00FFFF",
                  "Blue",
                  "Red",
                  "Green",
                  "Yellow" ,
                  "DarkBlue",
                  "#3cb371",
                  "#0000FF",
                  "#9966FF",
                  "#f990a7",
                  "#aad2ed",
                  "#FF00FF",
                  "#4C4CFF",
                  "#00FFFF",
                  "Blue",
                  "Red",
                  "Green",
                  "Yellow" ,
                  "DarkBlue"
                ],
                fill: true
              }
            ]
          },
          options: {
            legend: {
              display: true
            },
            scales: {
              xAxes: [{
                display: true
              }],
              yAxes: [{
                display: true
              }],
            },
          }
        });
        console.log(this.count);
    });
  });
  }

  ngOnInit(): void {
  }

}


