import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ManageSessionComponent} from './manage-session.component';

describe('ManageSessionComponent', () => {
  let component: ManageSessionComponent;
  let fixture: ComponentFixture<ManageSessionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ManageSessionComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageSessionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
