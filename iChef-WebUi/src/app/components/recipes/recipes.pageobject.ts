export class RecipesPageObject {
    element: HTMLElement;

    constructor(element: HTMLElement) {
        this.element = element;
    }

    getRecipeCardsCount(): number {
        return this.element.querySelectorAll('app-recipe-card').length;
    }
}
