import { Component, ElementRef, Renderer2, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { TITLES, PLACEHOLDERS_STRINGS, BUTTON_STRINGS } from 'src/app/constants/texts';
import { RecipeIngredient } from 'src/app/data-types/ingredient';
import { Recipe } from 'src/app/data-types/recipe';
import { Utensil } from 'src/app/data-types/utensil';
import { RecipesService } from 'src/app/services/recipes.service';

/**
 * @title Dynamic grid-list
 */
@Component({
    selector: 'app-recipe-page',
    templateUrl: './recipe-page.component.html',
    styleUrls: ['./recipe-page.component.scss']

})
export class RecipePageComponent {

    readonly create = TITLES.CREATE;
    readonly titlePlaceHolder = PLACEHOLDERS_STRINGS.TITLE;
    readonly ingredient = TITLES.INGREDIENT;
    readonly utensil = TITLES.UTENSIL;
    readonly timeTitle = TITLES.TIME;
    readonly difficultyTitle = TITLES.DIFFICULTY;
    readonly instructionsTitle = TITLES.INSTRUCTIONS;
    readonly notesTitle = TITLES.NOTES;
    readonly ingredientsUtensilsTitle = TITLES.INGREDIENTSUTENSILS;
    readonly ingredientsTitle = TITLES.INGREDIENTS;
    readonly utensilsTitle = TITLES.UTENSILS;
    readonly saveBtn = BUTTON_STRINGS.SAVE;
    readonly g = TITLES.G;
    readonly kg = TITLES.KG;
    readonly ml = TITLES.ML;
    readonly l = TITLES.L;
    readonly easy = TITLES.EASY;
    readonly medium = TITLES.MEDIUM;
    readonly hard = TITLES.HARD;

    constructor(
        private renderer: Renderer2,
        private recipeService: RecipesService,
        private router: Router
    ) { }

    @ViewChild('ingredientsContainer') ingredientsContainer!: ElementRef;
    @ViewChild('utensilsContainer') utensilsContainer!: ElementRef;

    utensilName = new FormControl('');

    ingredientName = new FormControl('');
    amount = new FormControl('');
    quantity = new FormControl('');

    ingredients: string[] = [];
    utensils: string[] = [];

    title = new FormControl('');
    time = new FormControl('');
    difficulty = new FormControl('');
    instructions = new FormControl('');
    notes = new FormControl('');

    addIngredient(): void {
        if (this.ingredientName.value === '' || this.amount.value === '' || this.quantity.value === '') {
            window.alert('Insert name, amount and quantity for ingredient!');
        } else {
            const div: HTMLDivElement = this.renderer.createElement('div');
            const span: HTMLSpanElement = this.renderer.createElement('span');
            const button: HTMLButtonElement = this.renderer.createElement('button');

            span.innerText = this.ingredientName.value + ' ' + this.amount.value + ' ' + this.quantity.value;
            span.className = 'ingredient-name';
            button.innerText = 'x';
            button.className = 'mat-raised-button delete-button';
            button.onclick = () => {
                this.renderer.removeChild(this.utensilsContainer.nativeElement, div);
                const index: number = this.ingredients.indexOf(span.innerText);
                if (index !== -1) {
                    this.ingredients.splice(index, 1);
                }
            };

            this.ingredients.push(span.innerText);
            div.appendChild(span);
            div.appendChild(button);
            this.renderer.appendChild(this.ingredientsContainer.nativeElement, div);

            this.ingredientName.setValue('');
            this.amount.setValue('');
            this.quantity.setValue('');
        }
    }

    addUtensil(): void {
        if (this.utensilName.value === '') {
            window.alert('Insert name for utensil!');
        } else {
            const div: HTMLDivElement = this.renderer.createElement('div');
            const span: HTMLSpanElement = this.renderer.createElement('span');
            const button: HTMLButtonElement = this.renderer.createElement('button');

            div.className = 'element';
            span.innerText = this.utensilName.value;
            span.className = 'ingredient-name';
            button.innerText = 'x';
            button.className = 'mat-raised-button delete-button';
            button.onclick = () => {
                this.renderer.removeChild(this.utensilsContainer.nativeElement, div);
                const index: number = this.utensils.indexOf(span.innerText);
                if (index !== -1) {
                    this.utensils.splice(index, 1);
                }
            };

            this.utensils.push(span.innerText);

            div.appendChild(span);
            div.appendChild(button);
            this.renderer.appendChild(this.utensilsContainer.nativeElement, div);

            this.utensilName.setValue('');
        }
    }

    saveRecipe(): void {
        if (!this.title.value || !this.ingredients.length || !this.utensils.length || !this.time.value || !this.difficulty.value || !this.instructions.value || !this.notes.value) {
            window.alert('Insert title, ingredients, utensils, time to prepare, difficulty, instructions and extra notes for recipe!');
        } else {
            const ingredientObjects: RecipeIngredient[] = [];
            const utensilObjects: Utensil[] = [];

            this.ingredients.forEach(element => {
                const elements = element.split(' ');
                ingredientObjects.push({
                    ingredientName: elements[0],
                    amount: parseInt(elements[1], 10),
                    measurementUnit: elements[2]
                });
            });

            this.utensils.forEach(element => {
                utensilObjects.push({ utensilName: element });
            });

            const recipe: Recipe = {
                difficulty: this.difficulty.value,
                // TODO: Remove hardcoding once image uploading is supported.
                imagePath: 'https://images.unsplash.com/photo-1567620905732-2d1ec7ab7445?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8&w=1000&q=80',
                notes: this.notes.value,
                portions: 1,
                preparationTime: this.time.value,
                recipeIngredientList: ingredientObjects,
                recipeUtensilList: utensilObjects,
                steps: this.instructions.value,
                title: this.title.value,
                userId: '7ba38ead-8fd1-4fce-91ae-eeb3beafd05c'
            };

            this.recipeService.createRecipe(recipe).subscribe(() => {
                this.router.navigate(['recipes']);
            });
        }
    }

}
