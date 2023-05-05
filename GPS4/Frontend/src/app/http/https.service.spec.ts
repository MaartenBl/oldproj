import { TestBed } from '@angular/core/testing';

import { HttpsService } from './https.service';
import {
  HttpClientTestingModule
} from '@angular/common/http/testing';
describe('HttpsService', () => {
  let service: HttpsService;

  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientTestingModule],
    providers: [HttpsService]
  }));

  it('should be created', () => {
    const service: HttpsService = TestBed.get(HttpsService);
    expect(service).toBeTruthy();
  });
});