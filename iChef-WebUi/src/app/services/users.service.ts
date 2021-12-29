import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { EMPTY, forkJoin, of } from 'rxjs';
import { Observable } from 'rxjs/internal/Observable';
import { catchError, switchMap } from 'rxjs/operators';
import { User } from '../data-types/user';
import { SnackbarService } from './snackbar/snackbar.service';
import { LoggedUser } from '../data-types/logged-user';
import { SharedService } from './shared.service';

@Injectable({
    providedIn: 'root'
})
export class UsersService {
    user!: LoggedUser;

    constructor(
        private http: HttpClient,
        private snackbarService: SnackbarService,
        private sharedService: SharedService,
        @Inject('BASE_API_URL') private baseUrl: string
    ) { }

    login(email: string, password: string): Observable<LoggedUser> {
        const user: User = { 'email': email, 'password': password };
        return this.http.post(this.baseUrl + '/users/login', user)
            .pipe(
                catchError((error: any) => {
                    return this.handleHttpError(`The request to login failed with status ${error.status}.`);
                }),
                switchMap((response: any) => {
                    this.sharedService.setIsUserLogged(true);
                    const getUserRequest = this.getUserById(response.userId);
                    return forkJoin([of(response.userId), getUserRequest]);
                }),
                switchMap(([userId, requestedUser]) => {
                    this.user = requestedUser;
                    this.user.userId = userId;
                    return of(this.user);
                })
            );
    }

    getUserById(userId: string): Observable<LoggedUser> | Observable<any> {
        return this.http.get(this.baseUrl + '/users/' + userId)
            .pipe(
                catchError((error: any) => {
                    this.handleHttpError(`The request to get a user by id failed with status ${error.status}.`);
                    return of(error);
                })
            );
    }

    logout(): void {
        this.sharedService.setIsUserLogged(false);
    }

    private handleHttpError(errorMessage: string): Observable<never> {
        this.snackbarService.displayErrorSnackbar(errorMessage);
        return EMPTY;
    }

    getLoggedUser(): LoggedUser {
        return this.user;
    }
}
