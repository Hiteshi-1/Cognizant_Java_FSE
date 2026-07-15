import { CanDeactivateFn } from '@angular/router';

export interface HasUnsavedChanges {
  isDirty(): boolean;
}

// Hands-On 7, Task 2: CanDeactivate guard warning about unsaved reactive-form data.
export const unsavedChangesGuard: CanDeactivateFn<HasUnsavedChanges> = (component) => {
  if (component.isDirty()) {
    return window.confirm('You have unsaved changes. Leave?');
  }
  return true;
};
