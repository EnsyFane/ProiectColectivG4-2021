import { HttpClient } from '@angular/common/http';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { Recipe } from 'src/app/data-types/recipe';
import { FakeHttpClient } from 'src/app/testing/fake-http-client';

import { RecipesComponent } from './recipes.component';
import { RecipesPageObject } from './recipes.pageobject';

describe('RecipesComponent', () => {
    let component: RecipesComponent;
    let fixture: ComponentFixture<RecipesComponent>;
    let fakeHttpClient: FakeHttpClient;

    let recipesPage: RecipesPageObject;

    beforeEach(async () => {
        fakeHttpClient = new FakeHttpClient();
        await TestBed.configureTestingModule({
            imports: [RouterTestingModule],
            declarations: [RecipesComponent],
            providers: [
                { provide: 'BASE_API_URL', useValue: '' },
                { provide: HttpClient, useValue: fakeHttpClient }
            ],
            schemas: [CUSTOM_ELEMENTS_SCHEMA]
        }).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(RecipesComponent);
        component = fixture.componentInstance;

        recipesPage = new RecipesPageObject(fixture.nativeElement);
    });

    it('should create', () => {
        fixture.detectChanges();
        expect(component).toBeTruthy();
    });

    it('should display the correct amount of recipe-cards', () => {
        const recipes: Recipe[] = [
            {
                difficulty: 2,
                portions: 1,
                preparationTime: 60,
                recipeIngredientList: [],
                steps: 'steps',
                title: 'title',
                userId: 'user-id'
            },
            {
                difficulty: 2,
                portions: 1,
                preparationTime: 60,
                recipeIngredientList: [],
                steps: 'steps',
                title: 'title2',
                userId: 'user-id'
            }
        ];

        fakeHttpClient.setRecipesResponse(recipes);

        fixture.detectChanges();

        expect(recipesPage.getRecipeCardsCount()).toEqual(recipes.length);
    });
});
