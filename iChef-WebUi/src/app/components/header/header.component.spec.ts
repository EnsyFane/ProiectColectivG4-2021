import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { Router } from '@angular/router';

import { HeaderComponent } from './header.component';
import { routes } from '../../app-routing.module';
import { HeaderPageObject } from './header.pageobject';
import { MatDialogModule } from '@angular/material/dialog';
import { HttpClient } from '@angular/common/http';
import { FakeHttpClient } from 'src/app/testing/fake-http-client';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { SharedService } from 'src/app/services/shared.service';

describe('HeaderComponent', () => {
    let component: HeaderComponent;
    let fixture: ComponentFixture<HeaderComponent>;
    let router: Router;
    let headerPage: HeaderPageObject;
    let fakeHttpClient: FakeHttpClient;
    let sharedService: SharedService;

    beforeEach(async () => {
        fakeHttpClient = new FakeHttpClient();
        await TestBed.configureTestingModule({
            imports: [
                RouterTestingModule.withRoutes(routes),
                MatDialogModule,
                MatSnackBarModule,
                NoopAnimationsModule
            ],
            declarations: [HeaderComponent],
            providers: [
                { provide: 'BASE_API_URL', useValue: '' },
                { provide: HttpClient, useValue: fakeHttpClient }
            ]
        }).compileComponents();
    });

    beforeEach(() => {
        router = TestBed.inject(Router);
        sharedService = TestBed.inject(SharedService);
        sharedService.isUserLogged = false;

        fixture = TestBed.createComponent(HeaderComponent);
        component = fixture.componentInstance;
        router.initialNavigation();
        headerPage = new HeaderPageObject(fixture.nativeElement);
    });

    it('should create', () => {
        fixture.detectChanges();
        expect(component).toBeTruthy();
    });

    describe('Link highlight', () => {
        async function delay(time: number): Promise<any> {
            return new Promise(resolve => setTimeout(resolve, time));
        }

        it('should highlight the home link by default', async () => {
            sharedService.isUserLogged = false;
            fixture.detectChanges();

            await delay(100);

            expect(headerPage.getHomeLinkElement().classList).toContain('active-tab');
        });

        it('should highlight the recipes link when navigating to the recipes grid', async () => {
            sharedService.isUserLogged = false;
            fixture.detectChanges();

            router.navigate(['/recipes']);
            await delay(100);

            expect(headerPage.getRecipesLinkElement().classList).toContain('active-tab');
        });

        it('should highlight the account link when navigating to the account page', async () => {
            sharedService.isUserLogged = true;
            fixture.detectChanges();

            router.navigate(['/my-account']);
            await delay(100);

            expect(headerPage.getAccountLinkElement().classList).toContain('active-tab');
        });
    });

    describe('Navigate by link buttons', () => {
        it('should navigate to the recipes page after clicking link', async () => {
            sharedService.isUserLogged = false;
            fixture.detectChanges();

            headerPage.getRecipesLinkElement().click();
            fixture.detectChanges();
            await fixture.whenStable();

            expect(router.url).toContain('/recipes');
        });

        it('should navigate to the account page after clicking link', async () => {
            sharedService.isUserLogged = true;
            fixture.detectChanges();

            headerPage.getAccountLinkElement().click();
            fixture.detectChanges();
            await fixture.whenStable();

            expect(router.url).toContain('/my-account');
        });

        it('should navigate to the recipes page after clicking link', async () => {
            sharedService.isUserLogged = false;
            fixture.detectChanges();

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
