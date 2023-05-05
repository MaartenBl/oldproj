import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WeatherMeasurementInformationComponent } from './weather-measurement-information.component';

describe('WeatherMeasurementInformationComponent', () => {
  let component: WeatherMeasurementInformationComponent;
  let fixture: ComponentFixture<WeatherMeasurementInformationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WeatherMeasurementInformationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WeatherMeasurementInformationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
