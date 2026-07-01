# testing-mockito-junit

Cognizant Digital Nurture 5.0 — **JUnit 5, Mockito, Spring Testing & SLF4J Logging**

Group: `com.cognizant` · Artifact: `testing-mockito-junit`

---

## Project Structure

```
testing-mockito-junit/
├── pom.xml
└── src/
    ├── main/java/com/cognizant/testing/
    │   ├── TestingMockitoJunitApplication.java
    │   ├── controller/    UserController.java
    │   ├── exception/     GlobalExceptionHandler.java
    │   ├── logging/       LoggingExample.java, AppenderDemo.java
    │   ├── model/         User.java
    │   ├── repository/    UserRepository.java, Repository.java
    │   ├── service/       UserService.java, CalculatorService.java,
    │   │                  Service.java, ApiService.java, FileService.java,
    │   │                  NetworkService.java, MyService.java,
    │   │                  RestClient.java, ExternalApi.java, NetworkClient.java
    │   └── util/          Calculator.java, EvenChecker.java,
    │                      ExceptionThrower.java, PerformanceTester.java,
    │                      FileReader.java, FileWriter.java
    └── test/java/com/cognizant/testing/
        ├── junit/         AssertionsTest, CalculatorTest, EvenCheckerTest,
        │                  OrderedTests, ExceptionThrowerTest, PerformanceTesterTest
        ├── suite/         AllTests.java
        ├── mockito/       MyServiceTest.java, AdvancedMockitoTest.java
        ├── service/       SpringServiceTest.java, UserServiceTest.java
        ├── controller/    SpringControllerTest.java, UserControllerTest.java
        └── integration/   UserIntegrationTest.java, UserIntegrationMockTest.java
```

---

## Exercise Reference

### JUnit Testing Exercises

| Exercise | Topic | Test Class |
|---|---|---|
| 1 | Setting up JUnit (pom.xml) | — (`pom.xml`) |
| 2 | Writing Basic JUnit Tests | `CalculatorTest` |
| 3 | Assertions in JUnit | `AssertionsTest` |
| 4 | AAA Pattern + Setup/Teardown | `CalculatorTest` (`@BeforeEach`/`@AfterEach`) |

### Advanced JUnit Testing Exercises

| Exercise | Topic | Test Class |
|---|---|---|
| 1 | Parameterized Tests | `EvenCheckerTest` (`@ParameterizedTest`, `@ValueSource`, `@CsvSource`) |
| 2 | Test Suites | `AllTests` (`@Suite`, `@SelectClasses`) |
| 3 | Test Execution Order | `OrderedTests` (`@TestMethodOrder`, `@Order`) |
| 4 | Exception Testing | `ExceptionThrowerTest` (`assertThrows`) |
| 5 | Timeout Testing | `PerformanceTesterTest` (`@Timeout`, `assertTimeout`) |

### Mockito Hands-On Exercises

| Exercise | Topic | Test Class |
|---|---|---|
| 1 | Mocking and Stubbing | `MyServiceTest#testExternalApi` |
| 2 | Verifying Interactions | `MyServiceTest#testVerifyInteraction` |
| 3 | Argument Matching | `MyServiceTest#testArgumentMatching` |
| 4 | Handling Void Methods | `MyServiceTest#testHandlingVoidMethod` |
| 5 | Multiple Return Values | `MyServiceTest#testMultipleReturnValues` |
| 6 | Verifying Interaction Order | `MyServiceTest#testInteractionOrder` |
| 7 | Void Methods with Exceptions | `MyServiceTest#testVoidMethodThrowsException` |

### Advanced Mockito Exercises

| Exercise | Topic | Test Class |
|---|---|---|
| 1 | Mocking Databases/Repositories | `AdvancedMockitoTest#testServiceWithMockRepository` |
| 2 | Mocking External REST APIs | `AdvancedMockitoTest#testServiceWithMockRestClient` |
| 3 | Mocking File I/O | `AdvancedMockitoTest#testServiceWithMockFileIO` |
| 4 | Mocking Network Interactions | `AdvancedMockitoTest#testServiceWithMockNetworkClient` |
| 5 | Multiple Return Values | `AdvancedMockitoTest#testServiceWithMultipleReturnValues` |

### Spring Testing Exercises

| Exercise | Topic | Test Class |
|---|---|---|
| 1 | Basic Unit Test for Service Method | `SpringServiceTest#testCalculatorServiceAdd` |
| 2 | Mock Repository in Service Test | `SpringServiceTest#testGetUserById_*` |
| 3 | Test REST Controller with MockMvc | `SpringControllerTest#testGetUser_returnsUser` |
| 4 | Integration Test (full flow) | `UserIntegrationTest` |
| 5 | Test POST Endpoint | `SpringControllerTest#testCreateUser_returnsCreatedUser` |
| 6 | Test Service Exception Handling | `SpringServiceTest#testGetUserByIdOrThrow_*` |
| 7 | Test Custom Repository Query | `SpringServiceTest#testGetUsersByName` |
| 8 | Test ControllerAdvice Exception Handling | `SpringControllerTest#testGetUserStrict_notFound_returns404` |
| 9 | Parameterized Test with JUnit | `SpringServiceTest#testGetUserById_parameterized` |

### Mocking Dependencies in Spring Tests using Mockito

| Exercise | Topic | Test Class |
|---|---|---|
| 1 | Mock Service in Controller Test | `UserControllerTest` (`@WebMvcTest`, `@MockBean`) |
| 2 | Mock Repository in Service Test | `UserServiceTest` (`@Mock`, `@InjectMocks`) |
| 3 | Mock Service in Integration Test | `UserIntegrationMockTest` (`@SpringBootTest`, `@MockBean`) |

### SLF4J Logging Exercises

| Exercise | Topic | Class |
|---|---|---|
| 1 | Error and Warning level logging | `LoggingExample` |
| 2 | Parameterized Logging | `LoggingExample#main` |
| 3 | Using Different Appenders | `AppenderDemo` + `logback.xml` |

---

## How to Run

### Prerequisites
- Java 17+
- Maven 3.6+

### Run all tests
```bash
mvn clean test
```

### Run the Spring Boot application
```bash
mvn spring-boot:run
```
REST endpoints on **http://localhost:8084/users**

### Run logging demos
```bash
# After compiling:
mvn compile exec:java -Dexec.mainClass="com.cognizant.testing.logging.LoggingExample"
mvn compile exec:java -Dexec.mainClass="com.cognizant.testing.logging.AppenderDemo"
```
`AppenderDemo` writes to `logs/app.log` in addition to the console.
