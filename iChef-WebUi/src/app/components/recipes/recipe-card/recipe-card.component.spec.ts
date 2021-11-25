import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecipeCardComponent } from './recipe-card.component';

describe('RecipeCardComponent', () => {
    let component: RecipeCardComponent;
    let fixture: ComponentFixture<RecipeCardComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [RecipeCardComponent],
            schemas: [CUSTOM_ELEMENTS_SCHEMA]
        })
            .compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(RecipeCardComponent);
        component = fixture.componentInstance;
        component.recipe = {
            title: '',
            difficulty: 1,
            portions: 1,
            preparationTime: 1,
            recipeIngredientList: [],
            steps: '',
            userId: ''
        };
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
