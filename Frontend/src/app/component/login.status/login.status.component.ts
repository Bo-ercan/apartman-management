import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../service/auth.service';

@Component({
  selector: 'app-login-status',
  templateUrl: './login.status.component.html',
  styleUrls: ['./login.status.component.css']
})
export class LoginStatusComponent implements OnInit {
  loggedIn: boolean = false;
  email: string = "";
  password: string = "";

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit() {
    this.checkLoggedInStatus();
  }

  login() {
    this.authService.login(this.email, this.password).subscribe((resultData: any) => {
      if (resultData.message == "Login Success") {
        this.loggedIn = true;
        this.router.navigateByUrl('/flats');
        localStorage.setItem('loggedIn', 'true');
      } else {
        alert("Incorrect Email and Password not match");
      }
    });
  }

  logout() {
    this.authService.logout();
    this.loggedIn = false;
  }

  checkLoggedInStatus() {
    const loggedInValue = localStorage.getItem('loggedIn');
    this.loggedIn = loggedInValue === 'true';
  }
}
