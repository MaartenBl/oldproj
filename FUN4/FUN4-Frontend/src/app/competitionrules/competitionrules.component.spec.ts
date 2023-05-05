import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompetitionrulesComponent } from './competitionrules.component';

describe('CompetitionrulesComponent', () => {
  let component: CompetitionrulesComponent;
  let fixture: ComponentFixture<CompetitionrulesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompetitionrulesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompetitionrulesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
