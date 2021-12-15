export class ErrorInfoPageObject {
    private element: HTMLElement;

    constructor(element: HTMLElement) {
        this.element = element;
    }

    clickDismiss(): void {
        const dismissButton = this.element.querySelector('.header-dismiss') as HTMLElement;
        dismissButton.click();
    }

    getMainMessageTextContent(): string {
        const mainMessage = this.element.querySelector('.main-message') as HTMLElement;
        return mainMessage.textContent ?? '';
    }

    getHeaderTextContent(): string {
        const headerText = this.element.querySelector('.header-text') as HTMLElement;
        return headerText.textContent ?? '';
    }

    getHederElement(): HTMLElement {
        return this.element.querySelector('.header') as HTMLElement;
    }
}
