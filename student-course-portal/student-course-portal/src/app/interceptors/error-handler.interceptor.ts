import { HttpInterceptorFn, HttpErrorResponse } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';

// Hands-On 8, Task 3: global HTTP error handling — 401 redirects to login/home,
// 500 logs a global notification, all other errors are re-thrown.
export const errorHandlerInterceptor: HttpInterceptorFn = (req, next) => {
  const router = inject(Router);

  return next(req).pipe(
    catchError((err: HttpErrorResponse) => {
      if (err.status === 401) {
        router.navigate(['/']);
      } else if (err.status === 500) {
        console.error('Global error notification: server error occurred.');
      }
      return throwError(() => err);
    })
  );
};
