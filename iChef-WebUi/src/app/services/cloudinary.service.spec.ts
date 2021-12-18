import { HttpClient } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { FakeHttpClient } from '../testing/fake-http-client';

import { CloudinaryService } from './cloudinary.service';
import { SnackbarService } from './snackbar/snackbar.service';

describe('ImgurService', () => {
    let service: CloudinaryService;
    let fakeHttpClient: FakeHttpClient;
    const fakeSnackbarService = jasmine.createSpyObj(['displaySnackbar', 'displayErrorSnackbar']);

    beforeEach(() => {
        fakeHttpClient = new FakeHttpClient();
        TestBed.configureTestingModule({
            providers: [
                { provide: 'CLOUDINARY_CLOUD_NAME', useValue: '' },
                { provide: 'CLOUDINARY_API_KEY', useValue: '' },
                { provide: 'CLOUDINARY_API_SECRET', useValue: '' },
                { provide: SnackbarService, useValue: fakeSnackbarService },
                { provide: HttpClient, useValue: fakeHttpClient }
            ]
        });
        service = TestBed.inject(CloudinaryService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});
