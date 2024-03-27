import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ViewChild, ElementRef } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  username: string ="";
  email: string ="";
  password: string ="";
  confirmPassword: string="";
  passwordMismatch: boolean = false;
  

  constructor(private http: HttpClient,private router: Router,private elementRef: ElementRef)
  {

  }
  
  navigateTo(url : string) {
    this.router.navigate([url]);
  }
  save() {
    let bodyData = {
      "username": this.username,
      "email": this.email,
      "password": this.password
    };

    this.http.post("http://localhost:8080/api/v1/user/save", bodyData, { responseType: 'text' }).subscribe(
      (resultData: any) => {
        console.log(resultData);
        alert("User Registered Successfully");
        localStorage.setItem('loggedIn', 'true');
        this.router.navigateByUrl('/flats-pagination');
      },
      error => {
        console.error(error);
        alert("Kayıt işlemi başarısız oldu: Bu email kullanımda.");
      }
    );
  }

  checkPasswords() {
    this.passwordMismatch = this.confirmPassword !== this.password;
  }

  togglePasswordVisibility(inputType: string) {
    const inputElement = this.elementRef.nativeElement.querySelector(`#${inputType}`);
    if (inputElement.type === 'password') {
      inputElement.type = 'text';
    } else {
      inputElement.type = 'password';
    }
  }
  
}