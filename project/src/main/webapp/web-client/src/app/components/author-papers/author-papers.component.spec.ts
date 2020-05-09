import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {AuthorPapersComponent} from './author-papers.component';

describe('AuthorPapersComponent', () => {
  let component: AuthorPapersComponent;
  let fixture: ComponentFixture<AuthorPapersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [AuthorPapersComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AuthorPapersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
