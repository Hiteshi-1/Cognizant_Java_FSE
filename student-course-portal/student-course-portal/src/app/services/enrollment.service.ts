import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { CourseService } from './course.service';
import { Course } from '../models/course.model';

// Hands-On 6, Task 2: service-to-service injection + shared enrollment state.
@Injectable({ providedIn: 'root' })
export class EnrollmentService {
  private enrolledCourseIds: number[] = [];

  constructor(private courseService: CourseService) {}

  enroll(courseId: number): void {
    if (!this.isEnrolled(courseId)) {
      this.enrolledCourseIds.push(courseId);
    }
  }

  unenroll(courseId: number): void {
    this.enrolledCourseIds = this.enrolledCourseIds.filter(id => id !== courseId);
  }

  isEnrolled(courseId: number): boolean {
    return this.enrolledCourseIds.includes(courseId);
  }

  getEnrolledCourses(): Observable<Course[]> {
    return this.courseService.getCourses().pipe(
      map(courses => courses.filter(c => this.enrolledCourseIds.includes(c.id)))
    );
  }

  getStudentsByCourse(courseId: number): Observable<{ id: number; name: string }[]> {
    // Simulated dependent HTTP call demonstrating switchMap usage in components.
    return this.courseService.getCourseById(courseId).pipe(
      map(() => [{ id: 1, name: 'Hiteshi' }])
    );
  }
}
