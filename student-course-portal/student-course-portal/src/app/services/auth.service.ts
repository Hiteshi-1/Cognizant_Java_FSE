import { Injectable } from '@angular/core';

// Hands-On 7, Task 2: minimal auth service backing the route guard.
@Injectable({ providedIn: 'root' })
export class AuthService {
  isLoggedIn = true; // hardcoded for the exercise
}
