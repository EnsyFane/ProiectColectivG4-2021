export class ReviewCardPageObject {
    element: HTMLElement;

    constructor(element: HTMLElement) {
        this.element = element;
    }

    getUsername(): string {
        const elem = this.element.querySelector('.username') as HTMLElement;
        return elem.innerText;
    }

    getRating(): string {
        const elem = this.element.querySelector('.rating') as HTMLElement;
        return elem.innerText;
    }

    getContent(): string {
        const elem = this.element.querySelector('.inside') as HTMLElement;
        return elem.innerText;
    }
}
