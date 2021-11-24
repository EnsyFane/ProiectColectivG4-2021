import { Component, OnInit } from '@angular/core';
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

    constructor(
        private recipeService: RecipesService,
        private router: Router) { }

    ngOnInit(): void {
        this.recipeService.getRecipes().pipe(
            tap(recipes => {
                this.recipes = recipes;
            })
        ).subscribe();
    }

    onCreateEvent(): void {
        this.router.navigate(['details']);
    }
}
