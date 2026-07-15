# Student Course Portal — Digital Nurture 5.0 (Angular v20)

Single Angular application built incrementally across all 10 hands-on exercises
from the DN 5.0 Angular Hands-On Exercise Book (standalone components, Angular 20).

## Setup
```
npm install
json-server --watch db.json --port 3000   # mock backend (separate terminal)
ng serve                                    # http://localhost:4200
ng test                                     # Jasmine/Karma unit tests
```

## Where each hands-on lives
| Hands-On | Topic | Location |
|---|---|---|
| 1 | Setup, first components | `src/app/components/header`, `src/app/pages/home` |
| 2 | Binding, lifecycle, @Input/@Output | `home.component.ts`, `components/course-card` |
| 3 | Directives & pipes | `directives/highlight.directive.ts`, `pipes/credit-label.pipe.ts` |
| 4 | Template-driven forms | `features/enrollment/pages/enrollment-form` |
| 5 | Reactive forms, FormArray, custom validators | `features/enrollment/pages/reactive-enrollment-form` |
| 6 | Services & DI | `services/course.service.ts`, `services/enrollment.service.ts`, `components/notification` |
| 7 | Routing, guards, lazy loading | `app.routes.ts`, `guards/`, `features/enrollment/enrollment.routes.ts` |
| 8 | HttpClient, RxJS, interceptors | `services/course.service.ts`, `interceptors/` |
| 9 | NgRx store/effects/selectors | `store/course`, `store/enrollment` |
| 10 | Unit tests | every `*.spec.ts` file (Jasmine/Karma, TestBed, HttpClientTestingModule, MockStore) |

`notes.txt` at the project root answers Hands-On 1, Task 1 (file-by-file notes + budget thresholds).
`db.json` is the JSON Server mock backend used from Hands-On 8 onward.
