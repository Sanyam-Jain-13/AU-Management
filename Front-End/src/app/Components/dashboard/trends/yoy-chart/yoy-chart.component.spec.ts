import { ComponentFixture, TestBed } from '@angular/core/testing';

import { YoyChartComponent } from './yoy-chart.component';

describe('YoyChartComponent', () => {
  let component: YoyChartComponent;
  let fixture: ComponentFixture<YoyChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ YoyChartComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(YoyChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
