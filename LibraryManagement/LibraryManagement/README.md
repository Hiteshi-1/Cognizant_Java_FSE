# LibraryManagement – Spring Framework Exercises 1–9

A complete Maven + Spring Boot project covering all 9 exercises from the
**Cognizant Digital Nurture 5.0 – Spring Core** module.

---

## Project Structure

```
LibraryManagement/
├── pom.xml
└── src/
    └── main/
        ├── java/com/library/
        │   ├── LibraryManagementApplication.java   ← Main class (Ex 9 Boot + Ex 1–8 runner)
        │   ├── aspect/
        │   │   └── LoggingAspect.java              ← Ex 3, 8
        │   ├── controller/
        │   │   └── BookController.java             ← Ex 9 REST CRUD
        │   ├── model/
        │   │   └── Book.java                       ← Ex 9 JPA Entity
        │   ├── repository/
        │   │   ├── BookRepository.java             ← Ex 1, 2, 5, 6
        │   │   └── BookJpaRepository.java          ← Ex 9 Spring Data JPA
        │   └── service/
        │       └── BookService.java                ← Ex 2, 6, 7
        └── resources/
            ├── applicationContext.xml              ← Ex 1–8 XML config
            └── application.properties             ← Ex 9 Spring Boot config
```

---

## Exercise Reference

| Exercise | Topic | Key Files |
|----------|-------|-----------|
| 1 | Configure basic Spring application | `applicationContext.xml`, `BookService`, `BookRepository` |
| 2 | Setter Dependency Injection | `applicationContext.xml` (`<property>`), `BookService.setBookRepository()` |
| 3 | AOP – Execution Time Logging | `LoggingAspect` (`@Around`), `applicationContext.xml` |
| 4 | Maven project setup | `pom.xml` (dependencies + compiler plugin) |
| 5 | Spring IoC container config | `applicationContext.xml`, `BookService`, `BookRepository` |
| 6 | Annotation-based beans | `@Service`, `@Repository`, `<context:component-scan>` |
| 7 | Constructor + Setter Injection | `BookService` constructor, `<constructor-arg>` in XML |
| 8 | Basic AOP (`@Before`, `@After`) | `LoggingAspect` (all advice methods) |
| 9 | Spring Boot application | `LibraryManagementApplication`, `BookController`, `Book`, `BookJpaRepository` |

---

## How to Run

### Prerequisites
- Java 17+
- Maven 3.6+

### Exercise 9 – Spring Boot REST API
```bash
mvn spring-boot:run
```

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/api/books` | GET | List all books |
| `/api/books` | POST | Create a book (JSON body) |
| `/api/books/{id}` | GET | Get book by ID |
| `/api/books/{id}` | PUT | Update book |
| `/api/books/{id}` | DELETE | Delete book |
| `/api/books/search?keyword=` | GET | Search by title |
| `/h2-console` | — | H2 in-memory DB console |

**Sample POST body:**
```json
{
  "title": "Clean Code",
  "author": "Robert C. Martin",
  "isbn": "978-0132350884"
}
```

### Exercises 1–8 – Classic Spring XML Context
In `LibraryManagementApplication.java`:
1. Comment out `SpringApplication.run(...)`
2. Uncomment `runClassicSpringContext()`
3. Run:
```bash
mvn compile exec:java -Dexec.mainClass="com.library.LibraryManagementApplication"
```
Observe the console output for:
- DI success messages (Ex 2, 7)
- AOP `[AOP - BEFORE]`, `[AOP - AFTER]`, `[AOP - AROUND]` logs (Ex 3, 8)

---

## Dependencies Summary (`pom.xml`)
- `spring-boot-starter-parent` 3.2.0
- `spring-context` – IoC container
- `spring-aspects` + `aspectjweaver` – AOP
- `spring-webmvc` – Web layer
- `spring-boot-starter-web` – REST API
- `spring-boot-starter-data-jpa` – JPA/Hibernate
- `h2` – In-memory database (runtime)
