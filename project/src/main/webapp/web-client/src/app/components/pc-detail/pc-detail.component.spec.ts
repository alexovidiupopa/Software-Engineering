import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {PcDetailComponent} from './pc-detail.component';

describe('PcDetailComponent', () => {
  let component: PcDetailComponent;
  let fixture: ComponentFixture<PcDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [PcDetailComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PcDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
