import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Flat } from "../class/flat";
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class FlatService {
  loggedIn: boolean = false;

  private baseURL= "http://localhost:8080/api/v1/flats";
  constructor(private httpClient: HttpClient, private router: Router) { }

  getFlatsByPage(page: number): Observable<Flat[]>{
    return this.httpClient.get<Flat[]>(`${this.baseURL}-pagination/${page}`);
  }

  getTotalPageNumber(): Observable<number> {
    console.log("getTotalPageNumber fonksiyonu çağrıldı.");
    return this.httpClient.get<any>(`${this.baseURL}-pagination/1`, { observe: 'response' }).pipe(
      map((response: HttpResponse<any>) => {
        console.log("HTTP isteği başarılı bir şekilde yapıldı. Yanıt alındı.");
        const totalPages = parseInt(response.headers.get('X-Total-Pages') || '0');
        console.log("Toplam sayfa sayısı:", totalPages);
        return totalPages;
      })
    );
  }

  getFlatsList(): Observable<Flat[]>{
    return this.httpClient.get<Flat[]>(`${this.baseURL}`);
  }

  createFlat(flat:Flat):Observable<any>{
    return this.httpClient.post(`${this.baseURL}`,flat);
  }

  getFlatById(id: number): Observable<Flat>{
    return this.httpClient.get<Flat>(`${this.baseURL}/${id}`)
  }

  updateFlat(id: number, flat: Flat): Observable<any>{
    return this.httpClient.put(`${this.baseURL}/${id}`, flat);
  }

  deleteFlat(id:number):Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`)
  }
  

logout() {
  localStorage.setItem('loggedIn', 'false');
  this.checkLoggedInStatus(); // Çıkış yapıldığında loggedIn değerini kontrol et
}
checkLoggedInStatus() {
  const loggedInValue = localStorage.getItem('loggedIn');
  this.loggedIn = loggedInValue === 'true';
}

navigateTo(url : string) {
  this.router.navigate([url]);
}
}
