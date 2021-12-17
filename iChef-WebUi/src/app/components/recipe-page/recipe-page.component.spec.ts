import { HttpClient } from '@angular/common/http';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { RouterTestingModule } from '@angular/router/testing';
import { FakeHttpClient } from 'src/app/testing/fake-http-client';

import { RecipePageComponent } from './recipe-page.component';

// TODO: Add tests after update recipe is implemented
describe('RecipePageComponent', () => {
    let component: RecipePageComponent;
    let fixture: ComponentFixture<RecipePageComponent>;
    let fakeHttpClient: FakeHttpClient;

    beforeEach(async () => {
        fakeHttpClient = new FakeHttpClient();
        await TestBed.configureTestingModule({
            imports: [
                RouterTestingModule,
                MatSnackBarModule,
                NoopAnimationsModule
            ],
            declarations: [RecipePageComponent],
            providers: [
                { provide: 'BASE_API_URL', useValue: '' },
                { provide: HttpClient, useValue: fakeHttpClient }
            ],
            schemas: [CUSTOM_ELEMENTS_SCHEMA]
        }).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(RecipePageComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
