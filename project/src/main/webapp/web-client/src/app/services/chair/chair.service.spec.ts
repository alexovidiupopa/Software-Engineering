import {TestBed} from '@angular/core/testing';

import {ChairService} from './chair.service';

describe('ChairService', () => {
  let service: ChairService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ChairService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
