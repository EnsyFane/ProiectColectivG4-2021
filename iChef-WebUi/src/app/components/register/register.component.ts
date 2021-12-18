import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { BUTTON_STRINGS, PLACEHOLDERS_STRINGS } from 'src/app/constants/texts';
import { UserRegister } from 'src/app/data-types/user-register';
import { UsersService } from 'src/app/services/users.service';

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
    readonly registerBtn = BUTTON_STRINGS.REGISTER;
    readonly closeBtn = BUTTON_STRINGS.CLOSE;
    readonly firstname = PLACEHOLDERS_STRINGS.FIRSTNAME;
    readonly lastname = PLACEHOLDERS_STRINGS.LASTNAME;
    readonly email = PLACEHOLDERS_STRINGS.EMAIL;
    readonly username = PLACEHOLDERS_STRINGS.USERNAME;
    readonly password = PLACEHOLDERS_STRINGS.PASSWORD;
    readonly emailRequired = PLACEHOLDERS_STRINGS.EMAIL_REQUIRED;
    readonly passwordRequired = PLACEHOLDERS_STRINGS.PASSWORD_REQUIRED;

    form = this.formBuilder.group({
        email: ['', Validators.required],
        password: ['', Validators.required],
        firstname: ['', Validators.required],
        lastname: ['', Validators.required],
        username: ['', Validators.required]
    });

    constructor(
        private formBuilder: FormBuilder,
        private dialogRef: MatDialogRef<RegisterComponent>,
        private userService: UsersService) { }

    hide = true;

    register(): void {
        const user: UserRegister = {
            email: this.form.get('email')?.value ?? '',
            firstName: this.form.get('firstname')?.value ?? '',
            lastName: this.form.get('lastname')?.value ?? '',
            password: this.form.get('password')?.value ?? '',
            username: this.form.get('username')?.value ?? ''
        };
        this.userService.register(user).subscribe(_ => {
            this.close();
        });
    }

    close(): void {
        this.dialogRef.close();
    }
}
