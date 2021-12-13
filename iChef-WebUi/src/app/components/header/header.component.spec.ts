import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { Router } from '@angular/router';

import { HeaderComponent } from './header.component';
import { routes } from '../../app-routing.module';
import { HeaderPageObject } from './header.pageobject';
import { MatDialogModule } from '@angular/material/dialog';

describe('HeaderComponent', () => {
    let component: HeaderComponent;
    let fixture: ComponentFixture<HeaderComponent>;
    let router: Router;
    let headerPage: HeaderPageObject;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [
                RouterTestingModule.withRoutes(routes),
                MatDialogModule
            ],
            declarations: [HeaderComponent]
        }).compileComponents();
    });

    beforeEach(() => {
        router = TestBed.inject(Router);

        fixture = TestBed.createComponent(HeaderComponent);
        component = fixture.componentInstance;
        router.initialNavigation();
        headerPage = new HeaderPageObject(fixture.nativeElement);

        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

    describe('Link highlight', () => {
        async function delay(time: number): Promise<any> {
            return new Promise(resolve => setTimeout(resolve, time));
        }

        it('should highlight the home link by default', async () => {
            await delay(100);

            expect(headerPage.getHomeLinkElement().classList).toContain('active-tab');
        });

        it('should highlight the recipes link when navigating to the recipes grid', async () => {
            router.navigate(['/recipes']);
            await delay(100);

            expect(headerPage.getRecipesLinkElement().classList).toContain('active-tab');
        });

        it('should highlight the account link when navigating to the account page', async () => {
            router.navigate(['/my-account']);
            await delay(100);

            expect(headerPage.getAccountLinkElement().classList).toContain('active-tab');
        });
    });

    describe('Navigate by link buttons', () => {
        it('should navigate to the recipes page after clicking link', async () => {
            headerPage.getRecipesLinkElement().click();
            fixture.detectChanges();
            await fixture.whenStable();

            expect(router.url).toContain('/recipes');
        });

        it('should navigate to the account page after clicking link', async () => {
            headerPage.getAccountLinkElement().click();
            fixture.detectChanges();
            await fixture.whenStable();

            expect(router.url).toContain('/my-account');
        });

        it('should navigate to the recipes page after clicking link', async () => {
            router.navigate(['/recipes']);
            fixture.detectChanges();
            await fixture.whenStable();

            headerPage.getHomeLinkElement().click();
            fixture.detectChanges();
            await fixture.whenStable();

            expect(router.url).toContain('/home');
        });
    });
});
