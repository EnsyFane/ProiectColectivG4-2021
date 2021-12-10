import { TestBed } from '@angular/core/testing';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { ErrorInfoComponent } from 'src/app/components/error-info/error-info.component';
import { SnackbarService } from './snackbar.service';

describe('SnackbarService', () => {
    let dismissSpy: jasmine.SpyObj<any>;
    let matSnackBar: MatSnackBar;
    let openFromComponentSpy: jasmine.SpyObj<any>;
    let snackBarService: SnackbarService;

    beforeEach(async (): Promise<void> => {
        await TestBed.configureTestingModule({
            imports: [
                MatSnackBarModule,
                NoopAnimationsModule
            ]
        }).compileComponents();

        snackBarService = TestBed.inject(SnackbarService);

        matSnackBar = TestBed.inject(MatSnackBar);
        openFromComponentSpy = spyOn(matSnackBar, 'openFromComponent');
        dismissSpy = spyOn(matSnackBar, 'dismiss');
    });

    it('should be created', (): void => {
        expect(snackBarService).toBeDefined();
    });

    it('should call throgh to mat snackbar when displaying error snackbar', () => {
        snackBarService.displayErrorSnackbar('test');

        const openFromComponentSpyArgs = openFromComponentSpy.calls.mostRecent().args;
        expect(openFromComponentSpyArgs[0]).toBe(ErrorInfoComponent);
        expect(dismissSpy).not.toHaveBeenCalled();
    });

    it('should call through to mat snackbar open and dismiss', (): void => {
        snackBarService.displayErrorSnackbar('message');

        const openFromComponentSpyArgs = openFromComponentSpy.calls.mostRecent().args;
        expect(openFromComponentSpyArgs[0]).toBe(ErrorInfoComponent);
        expect(dismissSpy).not.toHaveBeenCalled();

        snackBarService.dismissSnackbar();
        expect(dismissSpy).toHaveBeenCalled();
    });
});
