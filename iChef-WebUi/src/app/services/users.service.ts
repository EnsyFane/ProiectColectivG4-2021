import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { map } from 'rxjs/internal/operators/map';
import { User } from '../data-types/user';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  constructor(
    private http: HttpClient,
    @Inject('BASE_API_URL') private baseUrl: string
  ) { }

  login(user: User) : Observable<string> {
    return this.http.post(this.baseUrl + '/users/login', user).pipe(
      map((response: any) => {
          return response as string;
      })
    );
  }
}
