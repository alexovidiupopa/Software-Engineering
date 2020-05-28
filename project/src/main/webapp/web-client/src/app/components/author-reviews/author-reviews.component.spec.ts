import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthorReviewsComponent } from './author-reviews.component';

describe('AuthorReviewsComponent', () => {
  let component: AuthorReviewsComponent;
  let fixture: ComponentFixture<AuthorReviewsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AuthorReviewsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AuthorReviewsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
