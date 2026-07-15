import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CourseService } from './course.service';
import { Course } from '../models/course.model';

describe('CourseService', () => {
  let service: CourseService;
  let httpMock: HttpTestingController;

  const mockCourses: Course[] = [
    { id: 1, name: 'Data Structures', code: 'CS101', credits: 4, gradeStatus: 'passed' },
    { id: 2, name: 'Operating Systems', code: 'CS205', credits: 3, gradeStatus: 'pending' }
  ];

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [CourseService]
    });
    service = TestBed.inject(CourseService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => httpMock.verify());

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should GET courses from the correct URL', () => {
    service.getCourses().subscribe(courses => {
      expect(courses.length).toBe(2);
    });
    const req = httpMock.expectOne('http://localhost:3000/courses');
    expect(req.request.method).toBe('GET');
    req.flush(mockCourses);
  });

  it('should propagate an error on a failed request', () => {
    service.getCourses().subscribe({
      next: () => fail('expected an error'),
      error: (err) => expect(err.message).toContain('Failed to load courses')
    });
    const requests = httpMock.match('http://localhost:3000/courses');
    // last retry attempt gets the final flush
    requests[requests.length - 1].flush('server error', { status: 500, statusText: 'Server Error' });
  });
});
