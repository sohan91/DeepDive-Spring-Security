import { Component, OnInit } from '@angular/core';
import { User } from "src/app/model/user.model";
import { NgForm } from '@angular/forms';
import { LoginService } from 'src/app/services/login/login.service';
import { Router } from '@angular/router';
import { getCookie } from 'typescript-cookie';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  authStatus: string = "";
  model = new User();

  constructor(private loginService: LoginService, private router: Router) {}

  ngOnInit(): void {}

  validateUser(loginForm: NgForm) {
    this.loginService.validateLoginDetails(this.model).subscribe({
      next: responseData => {
        // ✅ store user details
        this.model = <any>responseData.body;
        this.model.authStatus = 'AUTH';
        window.sessionStorage.setItem("userdetails", JSON.stringify(this.model));

        // ✅ store CSRF token from cookie
        const csrf = getCookie("XSRF-TOKEN");
        if (csrf) {
          window.sessionStorage.setItem("X-XSRF-TOKEN", csrf);
        }

        // ✅ navigate to dashboard
        this.router.navigate(['dashboard']);
      },
      error: (error: HttpErrorResponse) => {
        if (error.status === 401) {
          this.authStatus = "Invalid username or password. Please try again.";
        } else {
          this.authStatus = "Login failed. Server error.";
        }
        console.error("Login error:", error);
      }
    });
  }
}
