import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router) {}

  canActivate(): boolean {
    const loggedIn = localStorage.getItem('loggedIn');
    if (loggedIn === 'true') {
      return true; // Kullanıcı oturumu devam ediyor
    } else {
      // Oturumu devam etmiyorsa giriş sayfasına yönlendir
      this.router.navigateByUrl('/login');
      return false;
    }
  }
}
