import { HttpClient } from '@angular/common/http';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RecipeIngredient } from 'src/app/data-types/ingredient';
import { Recipe } from 'src/app/data-types/recipe';
import { Utensil } from 'src/app/data-types/utensil';
import { FakeHttpClient } from 'src/app/testing/fake-http-client';

import { RecipeDetailsComponent } from './recipe-details.component';
import { RecipeDetailsPageObject } from './recipe-details.pageobject';

describe('RecipeDetailsComponent', () => {
    let component: RecipeDetailsComponent;
    let fixture: ComponentFixture<RecipeDetailsComponent>;
    let fakeHttpClient: FakeHttpClient;

    let recipeDetailsPage: RecipeDetailsPageObject;

    beforeEach(async () => {
        fakeHttpClient = new FakeHttpClient();
        await TestBed.configureTestingModule({
            declarations: [RecipeDetailsComponent],
            providers: [
                { provide: 'BASE_API_URL', useValue: '' },
                { provide: HttpClient, useValue: fakeHttpClient }
            ],
            schemas: [CUSTOM_ELEMENTS_SCHEMA]
        }).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(RecipeDetailsComponent);
        component = fixture.componentInstance;

        recipeDetailsPage = new RecipeDetailsPageObject(fixture.nativeElement);
    });

    it('should create', () => {
        fixture.detectChanges();
        expect(component).toBeTruthy();
    });

    it('should populate fields correctly', async () => {
        const ingredient: RecipeIngredient = {
            ingredientName: 'name',
            amount: 2,
            measurementUnit: 'unit'
        };
        const utensil: Utensil = {
            utensilName: 'name'
        }
        const recipe: Recipe = {
            title: 'title',
            difficulty: 2,
            portions: 2,
            preparationTime: 26,
            recipeIngredientList: [ingredient],
            steps: 'steps',
            userId: 'user-id',
            imagePath: 'image-path',
            notes: 'notes',
            numberOfViews: 1006,
            rating: 3.3,
            recipeId: 'recipe-id',
            recipeUtensilList: [utensil]
        };
        fakeHttpClient.setRecipeDetailsResponse(recipe);
        fixture.detectChanges();

        expect(recipeDetailsPage.getImageSrc()).toContain(`${recipe.imagePath}`);
        expect(recipeDetailsPage.getTitle()).toEqual(recipe.title);
        expect(recipeDetailsPage.getDifficulty()).toContain(`${recipe.difficulty} / 5`);
        expect(recipeDetailsPage.getPreparationTime()).toContain(`${recipe.preparationTime} minutes`);
        expect(recipeDetailsPage.getRating()).toContain(`${recipe.rating}`);
        expect(recipeDetailsPage.getIngredients()).toContain(`${ingredient.ingredientName} x ${ingredient.amount} ${ingredient.measurementUnit}`);
        expect(recipeDetailsPage.getUtensils()).toContain(`${utensil.utensilName}`);
        expect(recipeDetailsPage.getSteps()).toContain(`${recipe.steps}`);
        expect(recipeDetailsPage.getNotes()).toContain(`${recipe.notes}`);
    });
});
