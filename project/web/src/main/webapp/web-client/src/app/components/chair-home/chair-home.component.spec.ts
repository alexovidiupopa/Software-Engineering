import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ChairHomeComponent} from './chair-home.component';

describe('ChairHomeComponent', () => {
  let component: ChairHomeComponent;
  let fixture: ComponentFixture<ChairHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ChairHomeComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChairHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
