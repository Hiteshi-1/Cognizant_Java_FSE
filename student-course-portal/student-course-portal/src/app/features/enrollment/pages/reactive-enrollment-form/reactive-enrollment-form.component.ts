import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import {
  AbstractControl, FormArray, FormBuilder, FormGroup, ReactiveFormsModule, ValidationErrors, Validators
} from '@angular/forms';
import { HasUnsavedChanges } from '../../../../guards/unsaved-changes.guard';

// Hands-On 5, Task 2: custom synchronous validator rejecting course codes
// starting with 'XX'.
function noCourseCode(control: AbstractControl): ValidationErrors | null {
  const value = control.value as string | null;
  return value && value.startsWith('XX') ? { noCourseCode: true } : null;
}

// Hands-On 5, Task 2: custom async validator simulating a server-side email check.
function simulateEmailCheck(control: AbstractControl): Promise<ValidationErrors | null> {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve(String(control.value).includes('test@') ? { emailTaken: true } : null);
    }, 800);
  });
}

@Component({
  selector: 'app-reactive-enrollment-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './reactive-enrollment-form.component.html',
  styleUrl: './reactive-enrollment-form.component.css'
})
export class ReactiveEnrollmentFormComponent implements OnInit, HasUnsavedChanges {
  enrollForm!: FormGroup;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.enrollForm = this.fb.group({
      studentName: ['', [Validators.required, Validators.minLength(3)]],
      studentEmail: this.fb.control('', [Validators.required, Validators.email], [simulateEmailCheck]),
      courseId: [null, [Validators.required, noCourseCode]],
      preferredSemester: ['Odd', Validators.required],
      agreeToTerms: [false, Validators.requiredTrue],
      additionalCourses: this.fb.array([])
    });
  }

  get additionalCourses(): FormArray {
    // A typed getter avoids repeated `as FormArray` casts scattered through the template.
    return this.enrollForm.get('additionalCourses') as FormArray;
  }

  addCourseControl(): void {
    this.additionalCourses.push(this.fb.control('', Validators.required));
  }

  removeCourse(index: number): void {
    this.additionalCourses.removeAt(index);
  }

  onSubmit(): void {
    // enrollForm.value excludes disabled controls; getRawValue() includes all controls.
    console.log(this.enrollForm.value);
    console.log(this.enrollForm.getRawValue());
  }

  isDirty(): boolean {
    return this.enrollForm.dirty;
  }
}
