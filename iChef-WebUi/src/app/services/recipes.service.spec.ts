import { HttpClient } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { FakeHttpClient } from '../testing/fake-http-client';

import { RecipesService } from './recipes.service';

describe('RecipesService', () => {
    let service: RecipesService;
    let fakeHttpClient: FakeHttpClient;

    beforeEach(() => {
        fakeHttpClient = new FakeHttpClient();
        TestBed.configureTestingModule({
            providers: [
                { provide: 'BASE_API_URL', useValue: '' },
                { provide: HttpClient, useValue: fakeHttpClient }
            ]
        });
        service = TestBed.inject(RecipesService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});
