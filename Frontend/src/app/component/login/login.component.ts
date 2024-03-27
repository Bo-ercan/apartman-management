import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ViewChild, ElementRef } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
 
  email: string ="";
  password: string ="";
  status: boolean =false;

  constructor(private router: Router,private http: HttpClient,private elementRef: ElementRef) {}
 
  login() {
    console.log(this.email);
    console.log(this.password);
 
    let bodyData = {
      email: this.email,
      password: this.password,
    };
 
        this.http.post("http://localhost:8080/api/v1/user/login", bodyData).subscribe( (resultData: any) => {
        console.log(resultData);
 
        if (resultData.message == "Email not exits")
        {
          alert("Email not exits");
        }
        else if(resultData.message == "Login Success")
         {
          this.status=true;
          this.router.navigateByUrl('/flats-pagination');
          localStorage.setItem('loggedIn', 'true');

        }
        else
        {
          alert("Incorrect Email and Password not match");
        }

      });
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