import { Component, OnInit } from '@angular/core';
import { Chart } from 'chart.js';
import { TrendsService } from 'src/app/Services/trends.service';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-location-chart',
  templateUrl: './location-chart.component.html',
  styleUrls: ['./location-chart.component.css']
})
export class LocationChartComponent implements OnInit {

  location:Array<string>=[];
  count:Array<number>=[];
  locationchart = [];
  doughnutchart=[];

  constructor(private trendsService:TrendsService) {
    this.trendsService.getLocationLabel().subscribe(labels=>{
      this.location= labels;

      // console.log("from back"+labels);
      console.log(this.location);

      this.trendsService.getLocationCount().subscribe(counts=>{
        this.count=counts;

        this.locationchart = new Chart('canvas', {
          type: 'doughnut',
          data: {
            labels: this.location,
            datasets: [
              {
                data: this.count,
                borderColor: '#3cba9f',
                backgroundColor: [
                  "#3cb371",
                  "#0000FF",
                  "#9966FF",
                  "#4C4CFF",
                  "#00FFFF",
                  "#f990a7",
                  "#aad2ed",
                  "#FF00FF",
                  "Blue",
                  "Red",
                  "Green",
                  "Yellow",
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
            }
          }
        });
        console.log(this.count);
    });
  });
  }

  ngOnInit(): void {
  }

}


