import { TestBed } from '@angular/core/testing';

import { P2kService } from './p2k.service';
import {
  HttpClientTestingModule
} from '@angular/common/http/testing';
describe('P2kService', () => {
  let service: P2kService;

  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientTestingModule],
    providers: [P2kService]
  }));

  it('should be created', () => {
    const service: P2kService = TestBed.get(P2kService);
    expect(service).toBeTruthy();
  });
});
