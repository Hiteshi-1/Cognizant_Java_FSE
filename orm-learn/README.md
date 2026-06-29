# orm-learn — Spring Data JPA & Hibernate

Cognizant Digital Nurture 5.0 · Spring Data JPA Upskilling Assignment

---

## Project Overview

This Spring Boot project demonstrates:

| Topic | Hands-on |
|---|---|
| Spring Data JPA setup, basic CRUD on `country` table | Day 1 HO-1, 5–9 |
| Query Methods — containing, sorted, startsWith | Day 2 HO-1 |
| Query Methods on `stock` table — between dates, greater than, top N | Day 2 HO-2 |
| O/R Mapping — `@ManyToOne`, `@OneToMany`, `@ManyToMany` | Day 2 HO-3–6 |
| HQL with `fetch`, aggregate functions | Day 3 HO-2, 3, 4 |
| Native Queries | Day 3 HO-5 |

---

## Tech Stack

- Java 11
- Spring Boot 2.7.x
- Spring Data JPA / Hibernate
- MySQL 8.x
- Maven

---

## Database Setup

### 1. Create schema
```sql
mysql -u root -p
mysql> CREATE SCHEMA ormlearn;
```

### 2. Country table
```
mysql> source src/main/resources/country-setup.sql
```

### 3. Payroll tables (employee, department, skill)
```
mysql> source src/main/resources/payroll.sql
```

### 4. Stock table
```
mysql> source src/main/resources/stock-setup.sql
-- Then load your stock-data.sql from Cognizant files
mysql> source D:\spring-data-jpa-files\stock-data.sql
```

---

## Project Structure

```
src/main/java/com/cognizant/ormlearn/
├── OrmLearnApplication.java          ← Main class with all test methods
├── model/
│   ├── Country.java
│   ├── Stock.java
│   ├── Employee.java
│   ├── Department.java
│   └── Skill.java
├── repository/
│   ├── CountryRepository.java        ← Query Methods for country
│   ├── StockRepository.java          ← Query Methods for stock
│   ├── EmployeeRepository.java       ← HQL & Native queries
│   ├── DepartmentRepository.java
│   └── SkillRepository.java
└── service/
    ├── CountryService.java
    ├── StockService.java
    ├── EmployeeService.java
    ├── DepartmentService.java
    ├── SkillService.java
    └── exception/
        └── CountryNotFoundException.java
```

---

## How to Run

1. Update `src/main/resources/application.properties` with your MySQL password.
2. Run the SQL setup scripts (see Database Setup above).
3. In `OrmLearnApplication.main()`, **uncomment** the test method you want to run.
4. Execute the main class.

---

## Key Concepts Demonstrated

### Query Methods (Spring Data JPA)
Method names are parsed by Spring and converted automatically to SQL:
```java
List<Country> findByNameContaining(String name);               // LIKE %name%
List<Country> findByNameContainingOrderByName(String name);    // + ORDER BY
List<Country> findByNameStartingWith(String prefix);           // LIKE prefix%
List<Stock>   findByCodeAndDateBetween(String code, ...);      // WHERE code=? AND date BETWEEN
List<Stock>   findTop3ByOrderByVolumeDesc();                   // TOP 3 by volume
```

### O/R Mapping Annotations
| Annotation | Purpose |
|---|---|
| `@ManyToOne` + `@JoinColumn` | Employee → Department (FK in employee table) |
| `@OneToMany(mappedBy=...)` | Department → List of Employees |
| `@ManyToMany` + `@JoinTable` | Employee ↔ Skill via employee_skill bridge table |
| `FetchType.EAGER` | Load related entity immediately with parent |
| `FetchType.LAZY` | Load related entity only when accessed (default for `@OneToMany`) |

### HQL vs Native Query
```java
// HQL — uses entity class names, not table names
@Query("SELECT e FROM Employee e LEFT JOIN FETCH e.department LEFT JOIN FETCH e.skillList WHERE e.permanent = true")

// Native — raw SQL
@Query(value = "SELECT * FROM employee", nativeQuery = true)
```

### @Transactional
Applied on all service methods. Spring manages the Hibernate session lifecycle — opens session before the method, commits/rolls back on completion, closes session after. This prevents `LazyInitializationException` for lazy associations accessed within the same transaction.
