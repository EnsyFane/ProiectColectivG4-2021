import { Injectable } from "@angular/core";
import { MatSnackBar, MatSnackBarRef } from "@angular/material/snack-bar";
import { Observable } from "rxjs";
import { takeUntil } from "rxjs/operators";
import { ErrorInfoComponent } from "src/app/components/error-info/error-info.component";
import { ErrorInfoDetails } from "src/app/data-types/error-info-details";

export interface DisplayErrorConfig {
    message: ErrorInfoDetails;
    keepActive?: boolean;
    destroy$?: Observable<void>;
    durationInMs?: number;
}

@Injectable({
    providedIn: 'root'
})
export class SnackbarService {
    readonly shortMessageDuration: number = 5000;
    readonly errorMessageDuration: number = 10000;
    readonly longMessageDuration: number = 20000;

    readonly defaultMessageDuration: number = this.shortMessageDuration;

    constructor(private snackBar: MatSnackBar) { }

    displayErrorSnackbar(message: string, header: string = 'Error'): MatSnackBarRef<ErrorInfoComponent> {
        return this.displaySnackbar({
            message: {
                header: header,
                isError: true,
                message: message
            }
        });
    }

    displayInfoSnackbar(message: string, header: string = 'Info'): MatSnackBarRef<ErrorInfoComponent> {
        return this.displaySnackbar({
            message: {
                header: header,
                isError: false,
                message: message
            }
        });
    }

    displaySnackbar(message: DisplayErrorConfig): MatSnackBarRef<ErrorInfoComponent> {
        let destroy$: Observable<void> | null = null;
        let detailedMessage: ErrorInfoDetails;
        let duration: number | undefined;

        detailedMessage = message.message;
        if (message.keepActive === true) {
            duration = undefined;
        } else if (Number.isInteger(message.durationInMs)) {
            duration = message.durationInMs;
        } else {
            duration = this.defaultMessageDuration;
        }

        if (message.destroy$) {
            destroy$ = message.destroy$;
        }

        const snackBarConfig = {
            data: detailedMessage,
            panelClass: ['slat-snackbar-detailed-error'],
            duration
        };

        const ref = this.snackBar.openFromComponent(ErrorInfoComponent, snackBarConfig);
        if (destroy$) {
            destroy$.pipe(takeUntil(ref.afterDismissed())).subscribe(() => ref.dismiss());
        }

        return ref;
    }
}
