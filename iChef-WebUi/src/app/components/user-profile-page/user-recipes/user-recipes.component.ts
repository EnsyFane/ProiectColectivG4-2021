import { Component, Input, OnInit } from '@angular/core';
import { BUTTON_STRINGS, PLACEHOLDERS_STRINGS } from '../../../constants/texts';
import { Recipe } from '../../../data-types/recipe';
import { Router } from '@angular/router';
import { RecipesService } from '../../../services/recipes.service';
import { tap } from 'rxjs/operators';

@Component({
    selector: 'app-user-recipes',
    templateUrl: './user-recipes.component.html',
    styleUrls: ['./user-recipes.component.scss']
})
export class UserRecipesComponent implements OnInit {

    @Input() userId!: string;

    readonly searchBtn = BUTTON_STRINGS.SEARCH;
    readonly searchPlaceHolder = PLACEHOLDERS_STRINGS.SEARCH;
    readonly createBtn = BUTTON_STRINGS.CREATE;

    recipes: Recipe[] = [];

    constructor(
        private recipeService: RecipesService,
        private router: Router) { }

    ngOnInit(): void {
        this.userId = 'f975d0e4-c71d-4d0e-9f77-4309082cd53a'; // This is preventive (it will be changed when we will have a user Service)
        this.refrehRecipes();
    }

    onSelectRecipe(recipe: Recipe): void {
        this.router.navigate(['/recipes/details/' + recipe.recipeId]);
    }

    goToRecipeEdit(recipe: Recipe): void {
        // TODO change the following line so it takes the user to the update page
        this.router.navigate(['/recipes/details/' + recipe.recipeId]);
    }

    deleteRecipe(recipe: Recipe): void {
        if (recipe.recipeId) {
            this.recipeService.deleteRecipe(recipe.recipeId).subscribe(() => this.refrehRecipes());
        }
    }

    private refrehRecipes(): void {
        this.recipeService.getRecipesByUserId(this.userId).pipe(
            tap(recipes => {
                this.recipes = recipes;
            })
        ).subscribe();
    }
}
