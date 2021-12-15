import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EMPTY, Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { SnackbarService } from './snackbar/snackbar.service';

@Injectable({
    providedIn: 'root'
})
export class ImgurService {

    constructor(
        private http: HttpClient,
        private snackbarService: SnackbarService
    ) { }

    uploadImage(image: File): Observable<any> {
        const formData = new FormData();
        formData.append('image', image);

        const headerDict = {
            'Authorization': 'Client-ID 0340cb196a9cc55'
        };

        return this.http.post('https://api.imgur.com/3/upload', formData, {
            headers: new HttpHeaders(headerDict),
            reportProgress: true,
            observe: 'events'
        }).pipe(
            catchError((error: HttpErrorResponse) => {
                this.handleHttpError(`Could not upload to IMGUR. Error code: ${error.status}.`);
                return of(error);
            })
        );
    }

    private handleHttpError(errorMessage: string): Observable<never> {
        this.snackbarService.displayErrorSnackbar(errorMessage);
        return EMPTY;
    }
}
