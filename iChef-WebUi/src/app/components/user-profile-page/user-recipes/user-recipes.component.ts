import { Component, Input, OnInit } from '@angular/core';
import { BUTTON_STRINGS, PLACEHOLDERS_STRINGS } from '../../../constants/texts';
import { Recipe } from '../../../data-types/recipe';
import { Router } from '@angular/router';
import { RecipesService } from '../../../services/recipes.service';
import { tap } from 'rxjs/operators';
import { FormControl } from '@angular/forms';
import { SharedService } from '../../../services/shared.service';

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
    searchText = new FormControl('');

    constructor(
        private recipeService: RecipesService,
        private sharedService: SharedService,
        private router: Router) { }

    ngOnInit(): void {
        this.refrehRecipes();
    }

    onSelectRecipe(recipe: Recipe): void {
        this.router.navigate(['/recipes/details/' + recipe.recipeId]);
    }

    goToRecipeEdit(recipe: Recipe): void {
        // TODO change the following line so it takes the user to the update page
        if (recipe.recipeId) {
            this.sharedService.setId(recipe.recipeId);
            this.sharedService.setRecipeEditMode(true);
        }
        this.router.navigate(['/recipes/update/' + recipe.recipeId]);
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

    search(): void {
        if (this.searchText.value === '') {
            alert('Insert a recipe title for search!');
        } else {
            this.recipeService.searchRecipes(this.searchText.value).pipe(
                tap(recipes => {
                    this.recipes = recipes;
                })
            ).subscribe();
            this.searchText.setValue('');
        }
    }

    createRecipe(): void {
        this.router.navigate(['recipes/create']);
    }
}
