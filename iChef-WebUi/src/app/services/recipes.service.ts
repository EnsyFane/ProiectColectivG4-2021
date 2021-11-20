import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { EMPTY, Observable, of } from 'rxjs';
import { Recipe } from '../data-types/recipe';
import { catchError, map } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class RecipesService {

    constructor(
        private http: HttpClient,
        @Inject('BASE_API_URL') private baseUrl: string
    ) { }

    getRecipes(): Observable<Recipe[]> {
        return this.http.get<Recipe[]>(this.baseUrl + '/recipes')
            .pipe(
                catchError((error: any) => {
                    return this.handleHttpError(`The request to get all recipes failed with error code ${error.status}.`);
                }),
                map((response: any) => {
                    return response as Recipe[];
                })
            );
    }

    getRecipesByUserId(userId: string | undefined): Observable<Recipe[]> {
        return this.http.get<Recipe[]>(this.baseUrl + '/recipes')
            .pipe(
                catchError((error: any) => {
                    return this.handleHttpError(`The request to get all recipes for user with id ${userId} failed with error code ${error.status}.`);
                }),
                map((response: any) => {
                    const recipes = response as Recipe[];
                    return recipes.filter(recipe => recipe.userId === userId);
                })
            );
    }

    getRecipe(recipeId: string): Observable<Recipe> {
        const recipes = this.getRecipes();
        for (const recipe of recipes) {
            if (recipe.id === recipeId) {
                return of(recipe);
            }
        }
        return of();
    }

    createRecipe(recipe: Recipe): Promise<Recipe> {
        return new Promise((resolve, reject) => {
            // TODO Here should be the BE call but for now we use local data
            this.recipes.push(recipe);
            resolve(recipe);
        });
    }

    updateRecipe(recipe: Recipe): Promise<Recipe> {
        return new Promise((resolve, reject) => {
            // TODO Here should be the BE call but for now we use local data
            resolve(recipe);
        });
    }

    deleteRecipe(recipe: Recipe): Promise<Recipe> {
        return new Promise((resolve, reject) => {
            // TODO Here should be the BE call but for now we use local data
            for (let i = 0; i < this.userRecipes.length; i++) {
                if (this.userRecipes[i].id === recipe.id) {
                    this.userRecipes.splice(i, 1);
                }
            }
            resolve(recipe);
        });
    }

    private handleHttpError(errorMessage: string): Observable<never> {
        return EMPTY;
    }
}
