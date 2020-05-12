import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {PaperDetailComponent} from './paper-detail.component';

describe('PaperDetailComponent', () => {
  let component: PaperDetailComponent;
  let fixture: ComponentFixture<PaperDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [PaperDetailComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PaperDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
