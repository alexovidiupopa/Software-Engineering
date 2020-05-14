import {TestBed} from '@angular/core/testing';

import {BiddingService} from './bidding.service';

describe('BiddingService', () => {
  let service: BiddingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BiddingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
