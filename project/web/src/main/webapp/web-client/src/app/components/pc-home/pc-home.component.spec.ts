import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {PcHomeComponent} from './pc-home.component';

describe('PcHomeComponent', () => {
  let component: PcHomeComponent;
  let fixture: ComponentFixture<PcHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [PcHomeComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PcHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
