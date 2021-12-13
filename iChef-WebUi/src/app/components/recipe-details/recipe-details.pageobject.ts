export class RecipeDetailsPageObject {
    element: HTMLElement;

    constructor(element: HTMLElement) {
        this.element = element;
    }

    getImageSrc(): string {
        const elem = this.element.querySelector('#image') as HTMLImageElement;
        return elem.src;
    }

    getTitle(): string {
        const elem = this.element.querySelector('.title') as HTMLElement;
        return elem.innerText;
    }

    getDifficulty(): string {
        const elem = this.element.querySelector('.difficulty') as HTMLElement;
        return elem.innerText;
    }

    getPreparationTime(): string {
        const elem = this.element.querySelector('.preparation-time') as HTMLElement;
        return elem.innerText;
    }

    getRating(): string {
        const elem = this.element.querySelector('.rating') as HTMLElement;
        return elem.innerText;
    }

    getIngredients(): string {
        const elem = this.element.querySelector('.ingredients') as HTMLElement;
        return elem.innerText;
    }

    getUtensils(): string {
        const elem = this.element.querySelector('.utensils') as HTMLElement;
        return elem.innerText;
    }

    getSteps(): string {
        const elem = this.element.querySelector('#steps') as HTMLElement;
        return elem.innerText;
    }

    getNotes(): string {
        const elem = this.element.querySelector('#notes') as HTMLElement;
        return elem.innerText;
    }
}
