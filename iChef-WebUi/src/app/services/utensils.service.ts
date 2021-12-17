import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { EMPTY, Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Utensil } from '../data-types/utensil';

@Injectable({
  providedIn: 'root'
})
export class UtensilsService {

  constructor(
    private http: HttpClient,
    @Inject('BASE_API_URL') private baseUrl: string
  ) { }

  getUtensils(): Observable<Utensil[]> {
    return this.http.get<Utensil[]>(this.baseUrl + '/utensils')
        .pipe(
            catchError((error: any) => {
                return this.handleHttpError(`The request to get all utensils failed with error code ${error.status}.`);
            }),
            map((response: any) => {
                return response as Utensil[];
            })
        );
  }

  private handleHttpError(errorMessage: string): Observable<never> {
    return EMPTY;
  }
}
