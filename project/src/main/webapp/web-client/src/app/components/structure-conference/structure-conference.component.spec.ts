import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {StructureConferenceComponent} from './structure-conference.component';

describe('StructureConferenceComponent', () => {
  let component: StructureConferenceComponent;
  let fixture: ComponentFixture<StructureConferenceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [StructureConferenceComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StructureConferenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
