import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {SuccessBuyComponent} from './success-buy.component';

describe('SuccessBuyComponent', () => {
  let component: SuccessBuyComponent;
  let fixture: ComponentFixture<SuccessBuyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [SuccessBuyComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SuccessBuyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
