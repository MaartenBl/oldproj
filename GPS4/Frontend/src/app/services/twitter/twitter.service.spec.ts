import { TestBed } from '@angular/core/testing';

import { TwitterService } from './twitter.service';
import {
  HttpClientTestingModule
} from '@angular/common/http/testing';

describe('TwitterService', () => {
  let service: TwitterService;
  
  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientTestingModule],
    providers: [TwitterService]
  }));

  it('should be created', () => {
    const service: TwitterService = TestBed.get(TwitterService);
    expect(service).toBeTruthy();
  });
});
