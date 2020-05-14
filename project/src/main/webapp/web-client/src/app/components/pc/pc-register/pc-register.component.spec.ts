import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {PcRegisterComponent} from './pc-register.component';

describe('PcRegisterComponent', () => {
  let component: PcRegisterComponent;
  let fixture: ComponentFixture<PcRegisterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [PcRegisterComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PcRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
