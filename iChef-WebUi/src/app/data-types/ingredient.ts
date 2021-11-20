export class Ingredient {
    name?: string;
    amount?: string;
    quantityType?: string;

    constructor(name: string, amount: string, quantityType: string) {
        this.name = name;
        this.amount = amount;
        this.quantityType = quantityType;
    }

}