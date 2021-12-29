import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserProfilePageComponent } from './user-profile-page.component';
import { FakeHttpClient} from '../../testing/fake-http-client';
import { RouterTestingModule } from '@angular/router/testing';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { HttpClient } from '@angular/common/http';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

describe('UserProfilePageComponent', () => {
    let component: UserProfilePageComponent;
    let fixture: ComponentFixture<UserProfilePageComponent>;
    let fakeHttpClient: FakeHttpClient;

    beforeEach(async () => {
        fakeHttpClient = new FakeHttpClient();
        await TestBed.configureTestingModule({
            imports: [RouterTestingModule, MatSnackBarModule],
            declarations: [ UserProfilePageComponent ],
            providers: [
                { provide: 'BASE_API_URL', useValue: '' },
                { provide: HttpClient, useValue: fakeHttpClient }
            ],
            schemas: [CUSTOM_ELEMENTS_SCHEMA]
        }).compileComponents();
    });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserProfilePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
