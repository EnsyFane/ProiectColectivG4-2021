export class Recipe {
    title?: string;
    difficulty?: string;
    image?: string;
    rating?: number;

    constructor(title: string,
        difficulty: string,
        image: string,
        rating: number) {

        this.title = title;
        this.difficulty = difficulty;
        this.image = image;
        this.rating = rating;
    }

}
