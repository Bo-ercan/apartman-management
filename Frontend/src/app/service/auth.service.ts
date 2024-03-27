import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  email: string ="";
  password: string ="";
  status: boolean =false;
  constructor(private httpClient: HttpClient) { }

  login(email: string, password: string): Observable<any> {
    const bodyData = { email, password };
    return this.httpClient.post("http://localhost:8080/api/v1/user/login", bodyData);
  }

  logout(): void {
    localStorage.setItem('loggedIn', 'false');
  }
}
