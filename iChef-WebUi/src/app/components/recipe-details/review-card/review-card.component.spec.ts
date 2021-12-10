import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewCardComponent } from './review-card.component';
import { ReviewCardPageObject } from './review-card.pageobject';

describe('ReviewCardComponent', () => {
    let component: ReviewCardComponent;
    let fixture: ComponentFixture<ReviewCardComponent>;

    let reviewCardPage: ReviewCardPageObject;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [ReviewCardComponent],
            schemas: [CUSTOM_ELEMENTS_SCHEMA]
        }).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(ReviewCardComponent);
        component = fixture.componentInstance;
        component.review = {
            username: 'username',
            content: 'content',
            rating: 2.2
        };
        reviewCardPage = new ReviewCardPageObject(fixture.nativeElement);

        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

    it('should contain correct information in fields', () => {
        expect(reviewCardPage.getUsername()).toEqual('username');
        expect(reviewCardPage.getRating()).toContain('2.2');
        expect(reviewCardPage.getContent()).toEqual('content');
    });
});
