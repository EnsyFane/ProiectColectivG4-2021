import { HttpClient } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { FakeHttpClient } from '../testing/fake-http-client';
import { SnackbarService } from './snackbar/snackbar.service';

import { UsersService } from './users.service';

describe('UsersService', () => {
    let service: UsersService;
    let fakeHttpClient: FakeHttpClient;

    beforeEach(() => {
        fakeHttpClient = new FakeHttpClient();
        const fakeSnackbarService = jasmine.createSpyObj(['displaySnackbar', 'displayErrorSnackbar']);

        TestBed.configureTestingModule({
            providers: [
                { provide: 'BASE_API_URL', useValue: '' },
                { provide: SnackbarService, useValue: fakeSnackbarService },
                { provide: HttpClient, useValue: fakeHttpClient }
            ]
        });
        service = TestBed.inject(UsersService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});
