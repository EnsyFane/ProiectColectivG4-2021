import { Ingredient } from "./ingredient";
import { Utensil } from "./utensil";

export class Recipe {
    id?: string;
    title?: string;
    difficulty?: string;
    image?: string;
    rating?: number;
    ingredients?: Ingredient[];
    utensils?: Utensil[];
    time?: string;
    preparationInstructions?: string;
    extraNotes?: string;

    constructor(title: string,
        difficulty: string,
        image: string,
        ingredients: Ingredient[],
        utensils: Utensil[],
        time: string,
        preparationInstructions: string,
        extraNotes: string) {
        this.title = title;
        this.difficulty = difficulty;
        this.image = image;
        this.ingredients = ingredients;
        this.utensils = utensils;
        this.time = time;
        this.preparationInstructions = preparationInstructions;
        this.extraNotes = extraNotes;
    }

}
