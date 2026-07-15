import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-not-found',
  standalone: true,
  imports: [RouterLink],
  template: `<h2>404 - Page Not Found</h2><a routerLink="/">Go back home</a>`
})
export class NotFoundComponent {}
