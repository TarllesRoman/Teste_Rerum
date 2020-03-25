import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BovinoComponent } from './bovino.component';

describe('BovinoComponent', () => {
  let component: BovinoComponent;
  let fixture: ComponentFixture<BovinoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BovinoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BovinoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
