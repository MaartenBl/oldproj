import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GamereportComponent } from './gamereport.component';

describe('GamereportComponent', () => {
  let component: GamereportComponent;
  let fixture: ComponentFixture<GamereportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GamereportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GamereportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
