import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { NotificationService } from '../../services/notification.service';

// Hands-On 6, Task 2: providers: [NotificationService] here creates a NEW,
// separate NotificationService instance scoped to this component and its
// children — distinct from the app-wide singleton pattern used by
// providedIn: 'root' services like CourseService.
@Component({
  selector: 'app-notification',
  standalone: true,
  imports: [CommonModule],
  providers: [NotificationService],
  template: `
    <div class="notifications">
      <p *ngFor="let m of service.getAll()">{{ m }}</p>
    </div>
  `
})
export class NotificationComponent {
  constructor(public service: NotificationService) {}
}
