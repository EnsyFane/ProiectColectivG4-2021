import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';
import { Recipe } from 'src/app/data-types/recipe';
import { RecipesService } from 'src/app/services/recipes.service';
import { BUTTON_STRINGS, PLACEHOLDERS_STRINGS, TITLES } from '../../constants/texts';
import { RecipeIngredient } from 'src/app/data-types/ingredient';
import { Utensil } from 'src/app/data-types/utensil';
import { IngredientsService } from 'src/app/services/ingredients.service';
import { UtensilsService } from 'src/app/services/utensils.service';
import { Filter } from 'src/app/data-types/filter';
import { FilterCriteria } from 'src/app/data-types/filter';
import { animations } from 'src/app/constants/animations';
import { SnackbarService } from 'src/app/services/snackbar/snackbar.service';

@Component({
    selector: 'app-recipes',
    templateUrl: './recipes.component.html',
    styleUrls: ['./recipes.component.scss'],
    animations: [
        animations.animeTrigger,
        animations.animeTrigger2,
        animations.animeTrigger3
    ]
})
export class RecipesComponent implements OnInit {

    readonly searchBtn = BUTTON_STRINGS.SEARCH;
    readonly searchPlaceHolder = PLACEHOLDERS_STRINGS.SEARCH;
    readonly createBtn = BUTTON_STRINGS.CREATE;
    readonly filterBtn = BUTTON_STRINGS.FILTER;
    readonly difficultyTitle = TITLES.DIFFICULTY;
    readonly timeTitle = TITLES.TIME;
    readonly portionsTitle = TITLES.PORTIONS;
    readonly ingredientsTitle = TITLES.INGREDIENTS;
    readonly utensilsTitle = TITLES.UTENSILS;
    readonly numberPlaceHolder = PLACEHOLDERS_STRINGS.NUMBER;
    readonly applyBtn = BUTTON_STRINGS.APPLY;

    recipes: Recipe[] = [];
    searchTextPrevPage: string = '';

    ingredients: RecipeIngredient[] = [];
    utensils: Utensil[] = [];

    checkBoxIngredients: boolean[] = [];
    checkBoxUtensils: boolean[] = [];

    constructor(
        private snackbarService: SnackbarService,
        private recipeService: RecipesService,
        private ingredientsService: IngredientsService,
        private utensilsService: UtensilsService,
        private router: Router) {
            if (this.router.getCurrentNavigation()?.extras.state?.text !== undefined) {
                this.searchTextPrevPage = this.router.getCurrentNavigation()?.extras.state?.text;
            }
        }

    searchText = new FormControl('');

    difficultyOperation = new FormControl('');
    difficultyNumber = new FormControl('');

    timeOperation = new FormControl('');
    timeNumber = new FormControl('');

    portionsOperation = new FormControl('');
    portionsNumber = new FormControl('');

    checkedIngredients: string[] = [];
    checkedUtensils: string[] = [];

    filtersCriteria: FilterCriteria = {filters: []};

    menuState: string = 'out';

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

        this.ingredientsService.getIngredients().pipe(
            tap(ingredients => {
                this.ingredients = ingredients;
                for (let i = 0; i < this.ingredients.length; i++) {
                    this.checkBoxIngredients[i] = false;
                }
            })
        ).subscribe();

        this.utensilsService.getUtensils().pipe(
            tap(utensils => {
                this.utensils = utensils;
                for (let i = 0; i < this.utensils.length; i++) {
                    this.checkBoxUtensils[i] = false;
                }
            })
        ).subscribe();
    }

    onCreateEvent(): void {
        this.router.navigate(['details']);
    }

    search(): void {
        if (this.searchText.value === '') {
            this.snackbarService.displayErrorSnackbar('Insert a recipe title for search!');
        } else {
            this.recipeService.searchRecipes(this.searchText.value).pipe(
                tap(recipes => {
                    this.recipes = recipes;
                })
            ).subscribe();
            this.searchText.setValue('');
        }
    }

    openMenu(): void {
        this.menuState = 'in';
    }

    closeMenu(): void {
        this.menuState = 'out';
    }

    clearFields(): void {
        this.difficultyOperation.setValue('');
        this.difficultyNumber.setValue('');

        this.timeOperation.setValue('');
        this.timeNumber.setValue('');

        this.portionsOperation.setValue('');
        this.portionsNumber.setValue('');

        this.filtersCriteria.filters = [];
        for (let i = 0; i < this.ingredients.length; i++) {
            this.checkBoxIngredients[i] = false;
        }
        for (let i = 0; i < this.utensils.length; i++) {
            this.checkBoxUtensils[i] = false;
        }
        this.checkedIngredients = [];
        this.checkedUtensils = [];
    }

    getOperation(operation: string): string {
        if (operation === '=') {
            return 'equal';
        }

        if (operation === '<=') {
            return 'lessThanOrEqualTo';
        }

        if (operation === '>=') {
            return 'greaterThanOrEqualTo';
        }

        if (operation === '<') {
            return 'lessThan';
        }

        if (operation === '>') {
            return 'greaterThan';
        }

        return '';
    }

    applyFilters(): void {
        let errors: string = '';

        if (this.difficultyOperation.value !== '' && this.difficultyNumber.value !== '') {
            const filter: Filter = {field: 'difficulty', operation: this.getOperation(this.difficultyOperation.value), text: this.difficultyNumber.value.toString()};
            this.filtersCriteria.filters.push(filter);
        }

        if (this.difficultyOperation.value === '' && this.difficultyNumber.value !== '' || this.difficultyOperation.value !== '' && this.difficultyNumber.value === '') {
            errors += 'Insert operation and number for difficulty!\n';
        }

        if (this.timeOperation.value !== '' && this.timeNumber.value !== '') {
            const filter: Filter = {field: 'preparationTime', operation: this.getOperation(this.timeOperation.value), text: this.timeNumber.value.toString()};
            this.filtersCriteria.filters.push(filter);
        }

        if (this.timeOperation.value === '' && this.timeNumber.value !== '' || this.timeOperation.value !== '' && this.timeNumber.value === '') {
            errors += 'Insert operation and number for preparation time!\n';
        }

        if (this.portionsOperation.value !== '' && this.portionsNumber.value !== '') {
            const filter: Filter = {field: 'portions', operation: this.getOperation(this.portionsOperation.value), text: this.portionsNumber.value.toString()};
            this.filtersCriteria.filters.push(filter);
        }

        if (this.portionsOperation.value === '' && this.portionsNumber.value !== '' || this.portionsOperation.value !== '' && this.portionsNumber.value === '') {
            errors += 'Insert operation and number for portions!\n';
        }

        if (errors !== '') {
            this.snackbarService.displayErrorSnackbar(errors);
            this.clearFields();
        } else {
            if (this.filtersCriteria.filters.length === 0) {
                this.snackbarService.displayErrorSnackbar('Insert a filter!');
                this.clearFields();
            } else {
                this.recipeService.filterRecipes(this.filtersCriteria).pipe(
                    tap(recipes => {
                        this.recipes = recipes;
                    })
                ).subscribe( () => {
                    this.closeMenu();
                    this.clearFields();
                });
            }
        }
    }

    updateCheckedIngredient(ingredient: string, event: any): void {
        if (event.target.checked) {
            this.checkedIngredients.push(ingredient);
        } else {
            this.checkedIngredients.forEach((value, index) => {
                if (value === ingredient) this.checkedIngredients.splice(index, 1);
            });
        }
    }

    updateCheckedUtensil(utensil: string, event: any): void {
        if (event.target.checked) {
            this.checkedUtensils.push(utensil);
        } else {
            this.checkedUtensils.forEach((value, index) => {
                if (value === utensil) this.checkedUtensils.splice(index, 1);
            });
        }
    }
}
