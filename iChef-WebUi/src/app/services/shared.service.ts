import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SharedService {

    recipeId!: string;
    recipeEditMode: boolean = false;

    getId(): string {
        return this.recipeId;
    }

    setId(recipeId: string): void {
        this.recipeId = recipeId;
    }

    getRecipeEditMode(): boolean {
        return this.recipeEditMode;
    }

    setRecipeEditMode(recipeEditMode: boolean): void {
        this.recipeEditMode = recipeEditMode;
    }
}
