import { RecipeIngredient } from "./ingredient";
import { Utensil } from "./utensil";

export interface Recipe {
    recipeId?: string;
    title: string;
    utensils?: Utensil[];
    steps: string;
    rating?: number;
    difficulty: number;
    preparationTime: number;
    portions: number;
    notes?: string;
    numberOfViews?: number;
    imagePath?: string;
    userId: string;
    recipeIngredientList: RecipeIngredient[];
}
