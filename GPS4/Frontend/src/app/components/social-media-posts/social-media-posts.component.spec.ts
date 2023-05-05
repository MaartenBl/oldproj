import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SocialMediaPostsComponent } from './social-media-posts.component';
import {
  HttpClientTestingModule
} from '@angular/common/http/testing';

describe('SocialMediaPostsComponent', () => {
  let component: SocialMediaPostsComponent;
  let fixture: ComponentFixture<SocialMediaPostsComponent>;

  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientTestingModule],
    providers: [SocialMediaPostsComponent]
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SocialMediaPostsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
