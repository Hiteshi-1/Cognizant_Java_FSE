# spring-learn

Cognizant Digital Nurture 5.0 — **Spring Core + RESTful Web Services**
(Sessions 1–3 combined hands-on solutions)

Group: `com.cognizant` · Artifact: `spring-learn`

---

## Project Structure

```
spring-learn/
├── pom.xml
└── src/
    ├── main/
    │   ├── java/com/cognizant/springlearn/
    │   │   ├── SpringLearnApplication.java     ← main(), HO 2/4/5/6 demo methods
    │   │   ├── GlobalExceptionHandler.java      ← @ControllerAdvice (Session 3)
    │   │   ├── controller/
    │   │   │   ├── HelloController.java         ← GET /hello
    │   │   │   ├── CountryController.java       ← Country CRUD + validation
    │   │   │   ├── EmployeeController.java      ← Employee CRUD
    │   │   │   └── DepartmentController.java    ← GET /departments
    │   │   ├── service/
    │   │   │   ├── CountryService.java
    │   │   │   ├── EmployeeService.java
    │   │   │   ├── DepartmentService.java
    │   │   │   └── exception/
    │   │   │       ├── CountryNotFoundException.java
    │   │   │       └── EmployeeNotFoundException.java
    │   │   ├── dao/
    │   │   │   ├── EmployeeDao.java
    │   │   │   └── DepartmentDao.java
    │   │   └── model/
    │   │       ├── Country.java
    │   │       ├── Employee.java
    │   │       ├── Department.java
    │   │       └── Skill.java
    │   └── resources/
    │       ├── application.properties           ← logging + server.port=8083
    │       ├── date-format.xml                   ← HO 2
    │       ├── country.xml                       ← HO 4, 5, 6
    │       └── employee.xml                      ← Employee/Department static data
    └── test/java/com/cognizant/springlearn/
        └── SpringLearnApplicationTests.java      ← MockMVC tests
```

---

## Hands-on Reference (Session 1: Spring Core)

| Hands-on | Topic | Key Files |
|---|---|---|
| 1 | Create Spring Boot project via Spring Initializr | `pom.xml`, `SpringLearnApplication.java` |
| 2 | Load `SimpleDateFormat` from XML | `date-format.xml`, `displayDate()` |
| 3 | Incorporate logging | `application.properties`, `LOGGER` usage everywhere |
| 4 | Load `Country` bean from XML | `country.xml`, `Country.java`, `displayCountry()` |
| 5 | Singleton vs Prototype scope | `country.xml` (`scope="singleton/prototype"`) |
| 6 | Load list of countries from XML | `country.xml` (`countryList` bean), `displayCountries()` |

## Hands-on Reference (Session 2: REST GET + MockMVC)

| Feature | Endpoint | Controller |
|---|---|---|
| Hello World | `GET /hello` | `HelloController` |
| Get India | `GET /country` | `CountryController` |
| Get all countries | `GET /countries` | `CountryController` |
| Get by code (case-insensitive) | `GET /country/{code}` or `GET /countries/{code}` | `CountryController` |
| 404 on bad code | — | `CountryNotFoundException` |
| MockMVC tests | — | `SpringLearnApplicationTests.java` |

## Hands-on Reference (Session 3: POST/PUT/DELETE + Validation)

| Feature | Endpoint | Notes |
|---|---|---|
| Create country | `POST /countries` | `@Valid @RequestBody Country` |
| Update country | `PUT /countries` | RESTful naming (no ID in URL — code is part of payload) |
| Delete country | `DELETE /countries/{code}` | |
| Validation | — | `@NotNull`, `@Size` on `Country.code` |
| Global exception handling | — | `GlobalExceptionHandler` (`@ControllerAdvice`) |
| Update employee | `PUT /employees` | Full bean validation on `Employee`, `Department`, `Skill` |
| Delete employee | `DELETE /employees/{id}` | Throws `EmployeeNotFoundException` if not found |
| Number format errors | — | `handleHttpMessageNotReadable()` in `GlobalExceptionHandler` |

## Hands-on Reference (Employee/Department REST — Angular integration session)

| Feature | Endpoint | Notes |
|---|---|---|
| Get all employees | `GET /employees` | Data loaded from `employee.xml` via `EmployeeDao` |
| Get employee by id | `GET /employees/{id}` | |
| Get all departments | `GET /departments` | Data loaded from `employee.xml` via `DepartmentDao` |

---

## How to Run

### Prerequisites
- Java 17+
- Maven 3.6+

### Run the Spring Boot REST application
```bash
mvn spring-boot:run
```
App runs on **http://localhost:8083** (configured in `application.properties`).

### Try it out

```bash
# Hello World
curl http://localhost:8083/hello

# Get India
curl http://localhost:8083/country

# Get all countries
curl http://localhost:8083/countries

# Get by code (case-insensitive)
curl http://localhost:8083/country/in

# Exceptional scenario (404)
curl -i http://localhost:8083/country/az

# Create a country (valid)
curl -i -H 'Content-Type: application/json' -X POST -d '{"code":"IN","name":"India"}' http://localhost:8083/countries

# Create a country (invalid code -> 400 with global handler response)
curl -i -H 'Content-Type: application/json' -X POST -d '{"code":"I","name":"India"}' http://localhost:8083/countries

# Get all employees
curl http://localhost:8083/employees

# Get employee by id
curl http://localhost:8083/employees/1

# Get all departments
curl http://localhost:8083/departments
```

### Run the classic XML hands-on demos (2, 4, 5, 6)
In `SpringLearnApplication.java`, uncomment the relevant call(s) inside `main()`:
```java
displayDate();
displayCountry();
displayCountries();
```
Then run:
```bash
mvn compile exec:java -Dexec.mainClass="com.cognizant.springlearn.SpringLearnApplication"
```
Check the console (debug logs) for constructor invocation counts to verify
singleton vs. prototype scope behavior (Hands-on 5).

### Run tests
```bash
mvn clean test
```
Covers: context loading, `GET /country` happy path, 404 exceptional scenario,
and `GET /countries` array size.

---

## Notes
- All methods use SLF4J logging (`LOGGER.info("Start")` / `LOGGER.debug(...)` / `LOGGER.info("End")`) — no `System.out.println()`, per the "going forward" convention from Hands-on 3.
- Both the **legacy singular URLs** (`/country`, `/country/{code}`) from the original hands-on text and the **RESTful plural URLs** (`/countries`, `/countries/{code}`) are implemented in `CountryController` so the project satisfies both the literal instructions and the later "naming convention" guidance.
- `javax.validation` references in the original doc map to `jakarta.validation` in Spring Boot 3.x (used throughout).
