import {TestBed} from '@angular/core/testing';

import {ProgramCommitteeService} from './program-committee.service';

describe('ProgramCommitteeService', () => {
  let service: ProgramCommitteeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProgramCommitteeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
