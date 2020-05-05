import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {UploadAbstractComponent} from './upload-abstract.component';

describe('UploadAbstractComponent', () => {
  let component: UploadAbstractComponent;
  let fixture: ComponentFixture<UploadAbstractComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [UploadAbstractComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UploadAbstractComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
