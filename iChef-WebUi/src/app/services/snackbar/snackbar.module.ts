import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBarModule } from '@angular/material/snack-bar';

import { ErrorInfoComponent } from 'src/app/components/error-info/error-info.component';
import { SnackbarService } from './snackbar.service';

@NgModule({
    declarations: [ErrorInfoComponent],
    imports: [
        CommonModule,
        MatButtonModule,
        MatIconModule,
        MatSnackBarModule
    ],
    providers: [SnackbarService]
})
export class SnackbarModule { }
