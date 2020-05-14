import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {PaperDetailDecisionComponent} from './paper-detail-decision.component';

describe('PaperDetailDecisionComponent', () => {
  let component: PaperDetailDecisionComponent;
  let fixture: ComponentFixture<PaperDetailDecisionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [PaperDetailDecisionComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PaperDetailDecisionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
