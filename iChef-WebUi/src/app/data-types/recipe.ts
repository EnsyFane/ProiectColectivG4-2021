export interface RecipeIngredient {
    ingredientName: string;
    amount: number;
}

export interface Recipe {
    id: string;
    title: string;
    utensils?: string[];
    steps: string;
    rating?: number;
    difficulty: number;
    preparationTime: number;
    portions: number;
    notes?: string;
    numberOfViews?: number;
    imagePath?: string;
    userId: string;
    recipeIngredientsList: RecipeIngredient[];
}
