import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadImageFormComponent } from './upload-image-form.component';

describe('UploadImageFormComponent', () => {
  let component: UploadImageFormComponent;
  let fixture: ComponentFixture<UploadImageFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UploadImageFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UploadImageFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
