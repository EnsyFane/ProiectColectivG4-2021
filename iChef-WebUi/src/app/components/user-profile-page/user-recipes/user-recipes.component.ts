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

    @Input() userId?: string;

    readonly searchBtn = BUTTON_STRINGS.SEARCH;
    readonly searchPlaceHolder = PLACEHOLDERS_STRINGS.SEARCH;
    readonly createBtn = BUTTON_STRINGS.CREATE;

    recipes: Recipe[] = [];

    constructor(
        private recipeService: RecipesService,
        private router: Router) { }

    ngOnInit(): void {
        this.userId = 'f7a520f8-32de-41e3-ab24-c6e5c7645051'; // This is preventive (it will be changed when we will have a user Service)
        this.recipeService.getRecipesByUserId(this.userId).pipe(
            tap(recipes => {
                this.recipes = recipes;
            })
        ).subscribe();
    }

    onSelectRecipe(recipe: Recipe): void {
        this.router.navigate(['/recipes/details/' + recipe.id]);
    }

    goToRecipeEdit(recipe: Recipe): void {
        // TODO change the following line so it takes the user to the update page
        this.router.navigate(['/recipes/details/' + recipe.id]);
    }

    deleteRecipe(recipe: Recipe): void {
        this.recipeService.deleteRecipe(recipe.id);
        this.ngOnInit();
    }
}
