import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ManagePCComponent} from './manage-pc.component';

describe('ManagePCComponent', () => {
  let component: ManagePCComponent;
  let fixture: ComponentFixture<ManagePCComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ManagePCComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagePCComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
