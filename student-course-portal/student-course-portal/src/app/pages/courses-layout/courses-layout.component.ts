import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

// Hands-On 7, Task 1: nested-route layout hosting course-list / course-detail.
@Component({
  selector: 'app-courses-layout',
  standalone: true,
  imports: [RouterOutlet],
  template: `<router-outlet></router-outlet>`
})
export class CoursesLayoutComponent {}
