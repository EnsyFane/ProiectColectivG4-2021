import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';
import { Recipe } from 'src/app/data-types/recipe';
import { RecipesService } from 'src/app/services/recipes.service';
import { BUTTON_STRINGS, PLACEHOLDERS_STRINGS } from '../../constants/texts';

@Component({
    selector: 'app-recipes',
    templateUrl: './recipes.component.html',
    styleUrls: ['./recipes.component.scss']
})
export class RecipesComponent implements OnInit {

    readonly searchBtn = BUTTON_STRINGS.SEARCH;
    readonly searchPlaceHolder = PLACEHOLDERS_STRINGS.SEARCH;
    readonly createBtn = BUTTON_STRINGS.CREATE;
    readonly filterBtn = BUTTON_STRINGS.FILTER;

    recipes: Recipe[] = [];
    searchTextPrevPage: string = '';

    constructor(
        private recipeService: RecipesService,
        private router: Router) {
            if (this.router.getCurrentNavigation()?.extras.state?.text !== undefined) {
                this.searchTextPrevPage = this.router.getCurrentNavigation()?.extras.state?.text;
            }
        }

    searchText = new FormControl('');

    ngOnInit(): void {

        if (this.searchTextPrevPage === '') {
            this.recipeService.getRecipes().pipe(
                tap(recipes => {
                    this.recipes = recipes;
                })
            ).subscribe();
        } else {
            this.recipeService.searchRecipes(this.searchTextPrevPage).pipe(
                tap(recipes => {
                    this.recipes = recipes;
                })
            ).subscribe();
        }
    }

    onCreateEvent(): void {
        this.router.navigate(['details']);
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
}
