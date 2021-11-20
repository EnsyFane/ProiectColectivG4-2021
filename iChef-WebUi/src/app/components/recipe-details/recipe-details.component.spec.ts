import { HttpClient } from '@angular/common/http';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FakeHttpClient } from 'src/app/testing/fake-http-client';

import { RecipeDetailsComponent } from './recipe-details.component';

describe('RecipeDetailsComponent', () => {
    let component: RecipeDetailsComponent;
    let fixture: ComponentFixture<RecipeDetailsComponent>;
    let fakeHttpClient: FakeHttpClient;

    beforeEach(async () => {
        fakeHttpClient = new FakeHttpClient();
        await TestBed.configureTestingModule({
            declarations: [RecipeDetailsComponent],
            providers: [
                { provide: 'BASE_API_URL', useValue: '' },
                { provide: HttpClient, useValue: fakeHttpClient }
            ],
            schemas: [CUSTOM_ELEMENTS_SCHEMA]
        })
            .compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(RecipeDetailsComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
