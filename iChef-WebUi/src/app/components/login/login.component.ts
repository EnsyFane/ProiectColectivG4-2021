import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { of } from 'rxjs';
import { tap } from 'rxjs/internal/operators/tap';
import { catchError } from 'rxjs/operators';
import { BUTTON_STRINGS, PLACEHOLDERS_STRINGS } from 'src/app/constants/texts';
import { User } from 'src/app/data-types/user';
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
      private formBuilder: FormBuilder,
      private dialogRef: MatDialogRef<LoginComponent>,
      private userService: UsersService) {
  }

  login() : void {
    const user = new User();
    user.email = this.form.value.email;
    user.password = this.form.value.password;
    this.userService.login(user).pipe(
      tap(() => {
        this.dialogRef.close();
      }),
      catchError(error => {
        alert('Invalid credentials!');
        this.form.reset();
        return of(0);
      })
    ).subscribe();
  }

  close() : void {
      this.dialogRef.close();
  }

}
