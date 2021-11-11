export class Review {
    username?: string;
    content?: string;
    rating?: number;

    constructor(username: string,
        content: string,
        rating: number) {

        this.username = username;    
        this.content = content;
        this.rating = rating;
    }

}
