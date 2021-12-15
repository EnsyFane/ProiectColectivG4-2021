import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { EMPTY, Observable } from 'rxjs';
import { Recipe } from '../data-types/recipe';
import { catchError, map } from 'rxjs/operators';
import { FilterCriteria } from '../data-types/filter-criteria';

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

    getRecipesByUserId(userId: string): Observable<Recipe[]> {
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
        return this.http.get<Recipe>(this.baseUrl + `/recipes/${recipeId}`)
            .pipe(
                catchError((error: any) => {
                    return this.handleHttpError(`The request to get recipe with id ${recipeId} failed with error code ${error.status}.`);
                }),
                map((response: any) => {
                    return response as Recipe;
                })
            );
    }

    // TODO: Create model for recipe creation
    createRecipe(recipe: any): Observable<Recipe> {
        return this.http.post<Recipe>(this.baseUrl + '/recipes', recipe)
            .pipe(
                catchError((error: any) => {
                    return this.handleHttpError(`The request to get create recipe failed with error code ${error.status}.`);
                }),
                map((response: any) => {
                    return response as Recipe;
                })
            );
    }

    updateRecipe(recipe: any, recipeId: string): Observable<Recipe> {
        return this.http.put<Recipe>(this.baseUrl + `/recipes/${recipeId}`, recipe)
            .pipe(
                catchError((error: any) => {
                    return this.handleHttpError(`The request to get update recipe with id ${recipeId} failed with error code ${error.status}.`);
                }),
                map((response: any) => {
                    return response as Recipe;
                })
            );
    }

    deleteRecipe(recipeId: string): Observable<Recipe> {
        return this.http.delete<Recipe>(this.baseUrl + `/recipes/${recipeId}`)
            .pipe(
                catchError((error: any) => {
                    return this.handleHttpError(`The request to get update recipe with id ${recipeId} failed with error code ${error.status}.`);
                }),
                map((response: any) => {
                    return response as Recipe;
                })
            );
    }

    searchRecipes(title: string): Observable<Recipe[]> {
        return this.http.get<Recipe[]>(this.baseUrl + `/recipes/filter/${title}`)
            .pipe(
                catchError((error: any) => {
                    return this.handleHttpError(`The request to search recipes failed with error code ${error.status}.`);
                }),
                map((response: any) => {
                    return response as Recipe[];
                })
            );
    }

    filterRecipes(filters: FilterCriteria): Observable<Recipe[]> {
        return this.http.post<Recipe[]>(this.baseUrl + `/recipes/complex_filter`, filters)
            .pipe(
                catchError((error: any) => {
                    return this.handleHttpError(`The request to filter recipes failed with error code ${error.status}.`);
                }),
                map((response: any) => {
                    return response as Recipe[];
                })
            );
    }

    // TODO: Add error snackbar
    private handleHttpError(errorMessage: string): Observable<never> {
        return EMPTY;
    }
}
