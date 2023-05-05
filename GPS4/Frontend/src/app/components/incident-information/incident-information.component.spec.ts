import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IncidentInformationComponent } from './incident-information.component';

describe('IncidentInformationComponent', () => {
  let component: IncidentInformationComponent;
  let fixture: ComponentFixture<IncidentInformationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IncidentInformationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IncidentInformationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
