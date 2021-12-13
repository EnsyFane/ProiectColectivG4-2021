export class HeaderPageObject {
    element: HTMLElement;

    constructor(element: HTMLElement) {
        this.element = element;
    }

    getHomeLinkElement(): HTMLElement {
        return this.element.querySelector('#home-link') as HTMLElement;
    }

    getRecipesLinkElement(): HTMLElement {
        return this.element.querySelector('#recipes-link') as HTMLElement;
    }

    getAccountLinkElement(): HTMLElement {
        return this.element.querySelector('#account-link') as HTMLElement;
    }
}
