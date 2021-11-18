export class Recipe {
    id?: string;
    title?: string;
    difficulty?: string;
    image?: string;
    rating?: number;
    ingredients?: string[];
    utensils?: string[];
    time?: string;
    preparationInstructions?: string;
    extraNotes?: string;

    constructor(id: string,
        title: string,
        difficulty: string,
        image: string,
        rating: number,
        ingredients: string[],
        utensils: string[],
        time: string,
        preparationInstructions: string,
        extraNotes: string) {
        this.id = id;
        this.title = title;
        this.difficulty = difficulty;
        this.image = image;
        this.rating = rating;
        this.ingredients = ingredients;
        this.utensils = utensils;
        this.time = time;
        this.preparationInstructions = preparationInstructions;
        this.extraNotes = extraNotes;
    }

}
