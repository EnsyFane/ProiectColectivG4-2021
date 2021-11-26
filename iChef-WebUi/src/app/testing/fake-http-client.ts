import { HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Recipe } from '../data-types/recipe';

export class FakeHttpClient {
    private recipesResponse!: Recipe[];
    private recipeDetailsResponse!: Recipe;

    constructor() {
        this.resetDefaultValues();
    }

    resetDefaultValues(): void {
        this.recipesResponse = this.getDefaultRecipesResponse();
        this.recipeDetailsResponse = this.getDefaulRecipeResponse();
    }

    setRecipeDetailsResponse(recipe: Recipe) {
        this.recipeDetailsResponse = recipe;
    }

    // ------- PUBLIC SETUP API -------
    // Public HTTP API

    get(url: string): Observable<any> {
        if (this.matches(/recipes$/i, url)) {
            return of(this.recipesResponse);
        }

        if (this.matches(/recipes\/.*$/i, url)) {
            return of(this.recipeDetailsResponse);
        }

        return of(this.getNotFoundResponse());
    }

    post(url: string, requestBody: any): Observable<any> {
        return of(this.getNotFoundResponse());
    }

    put(url: string, requestBody: any): Observable<any> {
        return of(this.getNotFoundResponse());
    }

    delete(url: string): Observable<any> {
        return of(this.getNotFoundResponse());
    }

    // Private Methods
    private matches(regex: RegExp, url: string): false | RegExpMatchArray {
        const match = url.match(regex);
        if (!match) {
            return false;
        }
        return match;
    }

    private getNotFoundResponse(): HttpErrorResponse {
        return new HttpErrorResponse({
            status: 404
        });
    }

    private getDefaultRecipesResponse(): Recipe[] {
        return [
            this.getDefaulRecipeResponse()
        ];
    }

    private getDefaulRecipeResponse(): Recipe {
        return {
            difficulty: 1,
            portions: 1,
            preparationTime: 60,
            recipeIngredientList: [],
            steps: 'All steps',
            title: 'Title',
            userId: 'user-id',
            recipeUtensilList: [{
                utensilName: 'Utensils'
            }]
        };
    }
}
