import { TestBed } from '@angular/core/testing';

import { PaperService } from './paper.service';

describe('PaperService', () => {
  let service: PaperService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PaperService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
