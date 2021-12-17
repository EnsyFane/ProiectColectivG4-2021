import { Component, Inject } from '@angular/core';
import { MatSnackBarRef, MAT_SNACK_BAR_DATA } from '@angular/material/snack-bar';
import { ErrorInfoDetails } from 'src/app/data-types/error-info-details';

@Component({
    selector: 'app-error-info',
    templateUrl: './error-info.component.html',
    styleUrls: ['./error-info.component.scss']
})
export class ErrorInfoComponent {
    isError: boolean = true;
    mainMessage: string;
    headerText: string;

    panelOpenState = false;

    constructor(
        private snackbarRef: MatSnackBarRef<ErrorInfoComponent>,
        @Inject(MAT_SNACK_BAR_DATA) data: any) {

        const message = data as ErrorInfoDetails;

        this.mainMessage = message.message;
        this.headerText = message.header;
        this.isError = message.isError;
    }

    dismiss(): void {
        this.snackbarRef.dismiss();
    }
}
