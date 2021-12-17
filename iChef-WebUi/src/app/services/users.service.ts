import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { EMPTY } from 'rxjs';
import { Observable } from 'rxjs/internal/Observable';
import { map } from 'rxjs/internal/operators/map';
import { catchError } from 'rxjs/operators';
import { User } from '../data-types/user';
import { SnackbarService } from './snackbar/snackbar.service';

@Injectable({
    providedIn: 'root'
})
export class UsersService {

    constructor(
        private http: HttpClient,
        private snackbarService: SnackbarService,
        @Inject('BASE_API_URL') private baseUrl: string
    ) { }

    login(email: string, password: string): Observable<string> {
        const user: User = { 'email': email, 'password': password };
        return this.http.post(this.baseUrl + '/users/login', user)
            .pipe(
                catchError((error: any) => {
                    return this.handleHttpError(`The request to login failed with status ${error.status}.`);
                }),
                map((response: any) => {
                    return response as string;
                })
            );
    }

    private handleHttpError(errorMessage: string): Observable<never> {
        this.snackbarService.displayErrorSnackbar(errorMessage);
        return EMPTY;
    }
}
