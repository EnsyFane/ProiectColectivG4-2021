import { Component, Input, OnInit } from '@angular/core';
import { tap } from 'rxjs/operators';
import { BUTTON_STRINGS, TITLES } from 'src/app/constants/texts';
import { Recipe } from 'src/app/data-types/recipe';
import { Review } from 'src/app/data-types/review';
import { RecipesService } from 'src/app/services/recipes.service';

@Component({
    selector: 'app-recipe-details',
    templateUrl: './recipe-details.component.html',
    styleUrls: ['./recipe-details.component.scss']
})
export class RecipeDetailsComponent implements OnInit {

    readonly reviews = TITLES.REVIEWS;
    readonly ingredients = TITLES.INGREDIENTS;
    readonly utensils = TITLES.UTENSILS;
    readonly instructions = TITLES.INSTRUCTIONS;
    readonly notes = TITLES.NOTES;
    readonly addReviewBtn = BUTTON_STRINGS.REVIEW;

    recipe: Recipe | null = null;

    review1: Review = new Review('Lorem ipsum', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In consequat egestas mi tempor condimentum. Pellentesque eu arcu eu libero maximus luctus id ut lorem. Proin et justo sed turpis ', 4.7);
    review2: Review = new Review('Lorem ipsum 2', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. ', 4);

    constructor(
        private recipeService: RecipesService
    ) { }

    ngOnInit(): void {
        const recipeId = window.location.href.split('/').pop() || '';

        this.recipeService.getRecipe(recipeId).pipe(
            tap(recipe => {
                this.recipe = recipe;
            })
        ).subscribe();
    }
}
