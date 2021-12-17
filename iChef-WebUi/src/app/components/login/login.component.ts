import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { BUTTON_STRINGS, PLACEHOLDERS_STRINGS } from 'src/app/constants/texts';
import { SnackbarService } from 'src/app/services/snackbar/snackbar.service';
import { UsersService } from 'src/app/services/users.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent {

    readonly loginBtn = BUTTON_STRINGS.LOGIN;
    readonly closeBtn = BUTTON_STRINGS.CLOSE;
    readonly email = PLACEHOLDERS_STRINGS.EMAIL;
    readonly password = PLACEHOLDERS_STRINGS.PASSWORD;
    readonly emailRequired = PLACEHOLDERS_STRINGS.EMAIL_REQUIRED;
    readonly passwordRequired = PLACEHOLDERS_STRINGS.PASSWORD_REQUIRED;

    form = this.formBuilder.group({
        email: ['', Validators.required],
        password: ['', Validators.required]
    });

    hide = true;

    constructor(
        private snackbarService: SnackbarService,
        private formBuilder: FormBuilder,
        private dialogRef: MatDialogRef<LoginComponent>,
        private userService: UsersService) { }

    login(): void {
        this.userService.login(this.form.value.email, this.form.value.password).pipe(
            catchError(error => {
                this.snackbarService.displayErrorSnackbar('Invalid credentials!');
                this.form.reset();
                return of(0);
            })
        ).subscribe(_ => {
            this.dialogRef.close();
        });
    }

    close(): void {
        this.dialogRef.close();
    }
}
