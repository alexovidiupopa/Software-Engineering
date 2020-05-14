import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {PcReviewComponent} from './pc-review.component';

describe('PcReviewComponent', () => {
  let component: PcReviewComponent;
  let fixture: ComponentFixture<PcReviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [PcReviewComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PcReviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
