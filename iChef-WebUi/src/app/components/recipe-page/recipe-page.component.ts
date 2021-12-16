import { Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { TITLES, PLACEHOLDERS_STRINGS, BUTTON_STRINGS } from 'src/app/constants/texts';
import { RecipeIngredient } from 'src/app/data-types/ingredient';
import { Recipe } from 'src/app/data-types/recipe';
import { Utensil } from 'src/app/data-types/utensil';
import { RecipesService } from 'src/app/services/recipes.service';
import { tap } from 'rxjs/operators';
import { SharedService } from '../../services/shared.service';
import { CloudinaryService } from 'src/app/services/cloudinary.service';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';

/**
 * @title Dynamic grid-list
 */
@Component({
    selector: 'app-recipe-page',
    templateUrl: './recipe-page.component.html',
    styleUrls: ['./recipe-page.component.scss']
})
export class RecipePageComponent implements OnInit, OnDestroy {

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
    readonly g = TITLES.G;
    readonly kg = TITLES.KG;
    readonly ml = TITLES.ML;
    readonly l = TITLES.L;
    readonly easy = TITLES.EASY;
    readonly medium = TITLES.MEDIUM;
    readonly hard = TITLES.HARD;

    pageTitle: string = TITLES.CREATE;
    saveBtn: string = BUTTON_STRINGS.SAVE;
    editMode!: boolean;
    selectedRecipe: Recipe | null = null;

    private subscription: Subscription = new Subscription();

    constructor(
        private imgurService: CloudinaryService,
        private recipeService: RecipesService,
        private sharedService: SharedService,
        private router: Router
    ) { }

    ngOnDestroy(): void {
        this.subscription.unsubscribe();
    }

    @ViewChild('ingredientsContainer') ingredientsContainer!: ElementRef;
    @ViewChild('utensilsContainer') utensilsContainer!: ElementRef;

    utensilName = new FormControl('');

    ingredientName = new FormControl('');
    amount = new FormControl('');
    quantity = new FormControl('');

    ingredientsList: RecipeIngredient[] = [];
    utensilsList: Utensil[] = [];

    recipeIngredient: RecipeIngredient = { amount: 0, ingredientName: '', measurementUnit: '' };
    recipeUtensil: Utensil = {};

    title = new FormControl('');
    time = new FormControl('');
    difficulty = new FormControl('');
    instructions = new FormControl('');
    notes = new FormControl('');
    imageUrl = '';
    imageIsUploading = false;

    ngOnInit(): void {
        this.editMode = this.sharedService.getRecipeEditMode();
        if (this.editMode) {
            this.saveBtn = BUTTON_STRINGS.UPDATE;
            this.pageTitle = TITLES.UPDATE;
            const recipeId = this.sharedService.getId();
            this.recipeService.getRecipe(recipeId).pipe(
                tap(recipe => {
                    this.selectedRecipe = recipe;
                    this.fillFields();
                })
            ).subscribe();
        }
    }

    fillFields(): void {
        this.title.setValue(this.selectedRecipe?.title);
        this.time.setValue(this.selectedRecipe?.preparationTime);
        this.difficulty.setValue(this.selectedRecipe?.difficulty);
        this.instructions.setValue(this.selectedRecipe?.steps);
        this.notes.setValue(this.selectedRecipe?.notes);

        if (this.selectedRecipe?.recipeIngredientList) {
            this.ingredientsList = this.selectedRecipe?.recipeIngredientList;
        }

        if (this.selectedRecipe?.recipeUtensilList) {
            this.utensilsList = this.selectedRecipe?.recipeUtensilList;
        }
    }

    clearIngredientFields(): void {
        this.ingredientName.setValue('');
        this.amount.setValue('');
        this.quantity.setValue('');
    }

    clearUtensilField(): void {
        this.utensilName.setValue('');
    }

    addIngredient(): void {
        if (this.ingredientName.value === '' || this.amount.value === '') {
            window.alert('Insert name and amount for ingredient!');
        } else {
            this.recipeIngredient = { amount: 0, ingredientName: '' };
            this.recipeIngredient.ingredientName = this.ingredientName.value;
            this.recipeIngredient.amount = this.amount.value;
            if (this.quantity.value !== '') {
                this.recipeIngredient.measurementUnit = this.quantity.value;
            }
            this.ingredientsList.push(this.recipeIngredient);
            this.clearIngredientFields();
        }
    }

    removeIngredient(ingredient: RecipeIngredient): void {
        const index: number = this.ingredientsList.indexOf(ingredient);
        if (index !== -1) {
            this.ingredientsList.splice(index, 1);
        }
    }

    addUtensil(): void {
        if (this.utensilName.value === '') {
            window.alert('Insert name for utensil!');
        } else {
            this.recipeUtensil = {};
            this.recipeUtensil.utensilName = this.utensilName.value;
            this.utensilsList.push(this.recipeUtensil);
            this.clearUtensilField();
        }
    }

    removeUtensil(utensil: Utensil): void {
        const index: number = this.utensilsList.indexOf(utensil);
        if (index !== -1) {
            this.utensilsList.splice(index, 1);
        }
    }

    onImageLoaded(image: File): void {
        if (!image) {
            return;
        }
        const subscription = this.imgurService.uploadImage(image).subscribe(event => {
            if (event.type === HttpEventType.UploadProgress) {
                this.imageIsUploading = true;
            }
            if (event instanceof HttpResponse) {
                this.imageUrl = event.body.url;
                this.imageIsUploading = false;
            }
        });

        this.subscription.add(subscription);
    }

    saveRecipe(): void {
        if (!this.title.value || !this.ingredientsList.length || !this.utensilsList.length || !this.time.value || !this.difficulty.value || !this.instructions.value || !this.notes.value) {
            window.alert('Insert title, ingredients, utensils, time to prepare, difficulty, instructions and extra notes for recipe!');
        } else {
            const ingredientObjects: RecipeIngredient[] = [];
            const utensilObjects: Utensil[] = [];

            this.ingredientsList.forEach(element => {
                ingredientObjects.push(element);
            });

            this.utensilsList.forEach(element => {
                utensilObjects.push(element);
            });

            const recipe: Recipe = {
                difficulty: this.difficulty.value,
                // TODO: Remove hardcoding once image uploading is supported.
                imagePath: this.imageUrl,
                notes: this.notes.value,
                portions: 1,
                preparationTime: this.time.value,
                recipeIngredientList: ingredientObjects,
                recipeUtensilList: utensilObjects,
                steps: this.instructions.value,
                title: this.title.value,
                userId: 'f975d0e4-c71d-4d0e-9f77-4309082cd53a'
            };

            if (this.editMode && this.selectedRecipe?.recipeId) {
                this.recipeService.updateRecipe(recipe, this.selectedRecipe.recipeId).subscribe(() => {
                    this.router.navigate(['recipes']);
                });
            } else {
                this.recipeService.createRecipe(recipe).subscribe(() => {
                    this.router.navigate(['recipes']);
                });
            }
        }
    }

}
