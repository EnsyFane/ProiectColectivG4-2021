import { CommonModule } from '@angular/common';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBarModule, MatSnackBarRef, MAT_SNACK_BAR_DATA } from '@angular/material/snack-bar';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { ErrorInfoDetails } from 'src/app/data-types/error-info-details';

import { ErrorInfoComponent } from './error-info.component';
import { ErrorInfoPageObject } from './error-info.component.pageobject';

const message: ErrorInfoDetails = {
    header: 'test',
    message: 'test',
    isError: true
}

describe('ErrorInfoComponent', () => {
    let component: ErrorInfoComponent;
    let fixture: ComponentFixture<ErrorInfoComponent>;
    let mockMatSnackbarRef: jasmine.SpyObj<MatSnackBarRef<ErrorInfoComponent>>
    let page: ErrorInfoPageObject;

    beforeEach(async () => {
        mockMatSnackbarRef = jasmine.createSpyObj(['dismiss']);

        const testBedStatic = TestBed.configureTestingModule({
            declarations: [ErrorInfoComponent],
            providers: [
                { provide: MatSnackBarRef, useValue: mockMatSnackbarRef },
                { provide: MAT_SNACK_BAR_DATA, useValue: message }
            ],
            imports: [
                CommonModule,
                MatButtonModule,
                MatIconModule,
                MatSnackBarModule,
                NoopAnimationsModule
            ]
        })

        await testBedStatic.compileComponents();

        fixture = TestBed.createComponent(ErrorInfoComponent);
        fixture.detectChanges();
        component = fixture.componentInstance;
        page = new ErrorInfoPageObject(fixture.nativeElement);
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

    it('should have the correct main message', () => {
        expect(page.getMainMessageTextContent().trim()).toBe(message.message);
    });

    it('should have the correct header message', () => {
        expect(page.getHeaderTextContent().trim()).toBe(message.header);
    });

    it('should call dismiss on close', () => {
        page.clickDismiss();
        expect(mockMatSnackbarRef.dismiss).toHaveBeenCalledTimes(1);
    });

    it('should have error class on header element when message is error', () => {
        expect(page.getHederElement().classList).toContain('error');
        expect(page.getHederElement().classList).not.toContain('info');
    });

    it('should have info class on header element when message is not error', () => {
        component.isError = false;
        fixture.detectChanges();

        expect(page.getHederElement().classList).toContain('info');
        expect(page.getHederElement().classList).not.toContain('error');
    });
});
