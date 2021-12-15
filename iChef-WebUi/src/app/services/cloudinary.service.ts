import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { EMPTY, Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { SnackbarService } from './snackbar/snackbar.service';
import shajs from 'sha.js';

@Injectable({
    providedIn: 'root'
})
export class CloudinaryService {

    constructor(
        private http: HttpClient,
        private snackbarService: SnackbarService,
        @Inject('CLOUDINARY_CLOUD_NAME') private cloudinaryCloudName: string,
        @Inject('CLOUDINARY_API_KEY') private cloudinaryApiKey: string,
        @Inject('CLOUDINARY_API_SECRET') private cloudinaryApiSecret: string
    ) { }

    uploadImage(image: File): Observable<any> {
        const formData = new FormData();
        const timestamp = Date.now();
        const signature = this.hash(`timestamp=${timestamp}${this.cloudinaryApiSecret}`);
        formData.append('file', image);
        formData.append('api_key', this.cloudinaryApiKey);
        formData.append('timestamp', `${timestamp}`);
        formData.append('signature', signature);

        return this.http.post(`https://api.cloudinary.com/v1_1/${this.cloudinaryCloudName}/image/upload`, formData, {
            reportProgress: true,
            observe: 'events'
        }).pipe(
            catchError((error: HttpErrorResponse) => {
                this.handleHttpError(`Could not upload to IMGUR. Error code: ${error.status}.`);
                return of(error);
            })
        );
    }

    private hash(input: string): string {
        return shajs('sha256').update(input).digest('hex');
    }

    private handleHttpError(errorMessage: string): Observable<never> {
        this.snackbarService.displayErrorSnackbar(errorMessage);
        return EMPTY;
    }
}
