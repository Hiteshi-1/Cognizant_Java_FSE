import { Injectable } from '@angular/core';

// Hands-On 6, Task 2: provided at COMPONENT level (see NotificationComponent),
// not root, so a fresh instance is created per component subtree instead of
// being a global singleton.
@Injectable()
export class NotificationService {
  private messages: string[] = [];

  push(message: string): void {
    this.messages.push(message);
  }

  getAll(): string[] {
    return this.messages;
  }
}
