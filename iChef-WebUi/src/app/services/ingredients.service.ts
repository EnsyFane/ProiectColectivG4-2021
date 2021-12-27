import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { EMPTY, Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { RecipeIngredient } from '../data-types/ingredient';

@Injectable({
  providedIn: 'root'
})
export class IngredientsService {

  constructor(
    private http: HttpClient,
    @Inject('BASE_API_URL') private baseUrl: string
  ) { }

  getIngredients(): Observable<RecipeIngredient[]> {
    return this.http.get<RecipeIngredient[]>(this.baseUrl + '/ingredients')
        .pipe(
            catchError((error: any) => {
                return this.handleHttpError(`The request to get all ingredients failed with error code ${error.status}.`);
            }),
            map((response: any) => {
                return response as RecipeIngredient[];
            })
        );
  }

  private handleHttpError(errorMessage: string): Observable<never> {
    return EMPTY;
  }
}
