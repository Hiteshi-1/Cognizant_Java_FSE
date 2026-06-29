package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.model.Stock;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.DepartmentService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.SkillService;
import com.cognizant.ormlearn.service.StockService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Date;
import java.util.List;

/**
 * Main Spring Boot entry point.
 *
 * ┌──────────────────────────────────────────────────────────────┐
 * │  All test methods are defined below.                         │
 * │  To run a specific test, uncomment its call in main().       │
 * └──────────────────────────────────────────────────────────────┘
 *
 * Coverage:
 *   Day 1 HO-1  : testGetAllCountries()
 *   Day 1 HO-5  : testFindCountryByCode(), testAddCountry(),
 *                 testUpdateCountry(), testDeleteCountry()
 *   Day 2 HO-1  : testSearchByName(), testSearchByNameSorted(),
 *                 testSearchByStartingLetter()
 *   Day 2 HO-2  : testGetFBSeptemberStocks(), testGoogleAbove1250(),
 *                 testTop3Volume(), testTop3LowestNetflix()
 *   Day 2 HO-4  : testGetEmployee(), testAddEmployee(), testUpdateEmployee()
 *   Day 2 HO-5  : testGetDepartment()
 *   Day 2 HO-6  : testAddSkillToEmployee()
 *   Day 3 HO-2  : testGetAllPermanentEmployees()
 *   Day 3 HO-4  : testGetAverageSalary()
 *   Day 3 HO-5  : testGetAllEmployeesNative()
 */
@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    // ── Static service references ──────────────────────────────────────────
    private static CountryService    countryService;
    private static StockService      stockService;
    private static EmployeeService   employeeService;
    private static DepartmentService departmentService;
    private static SkillService      skillService;

    // ══════════════════════════════════════════════════════════════════════
    //  MAIN
    // ══════════════════════════════════════════════════════════════════════

    public static void main(String[] args) throws CountryNotFoundException {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);

        // Resolve services from context
        countryService    = context.getBean(CountryService.class);
        stockService      = context.getBean(StockService.class);
        employeeService   = context.getBean(EmployeeService.class);
        departmentService = context.getBean(DepartmentService.class);
        skillService      = context.getBean(SkillService.class);

        LOGGER.info("Inside main");

        // ── Uncomment the test you want to run ────────────────────────────

        // Day 1 – Country CRUD
        // testGetAllCountries();
        // testFindCountryByCode();
        // testAddCountry();
        // testUpdateCountry();
        // testDeleteCountry();

        // Day 2 HO-1 – Country Query Methods
        // testSearchByName();
        // testSearchByNameSorted();
        // testSearchByStartingLetter();

        // Day 2 HO-2 – Stock Query Methods
        // testGetFBSeptemberStocks();
        // testGoogleAbove1250();
        // testTop3Volume();
        // testTop3LowestNetflix();

        // Day 2 HO-4 – Employee ManyToOne
        // testGetEmployee();
        // testAddEmployee();
        // testUpdateEmployee();

        // Day 2 HO-5 – Department OneToMany
        // testGetDepartment();

        // Day 2 HO-6 – ManyToMany Employee<->Skill
        // testAddSkillToEmployee();

        // Day 3 – HQL & Native Queries
        // testGetAllPermanentEmployees();
        // testGetAverageSalary();
        // testGetAllEmployeesNative();
    }

    // ══════════════════════════════════════════════════════════════════════
    //  DAY 1 – COUNTRY CRUD
    // ══════════════════════════════════════════════════════════════════════

    /** HO-1: Get all countries */
    private static void testGetAllCountries() {
        LOGGER.info("Start testGetAllCountries");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.debug("countries={}", countries);
        LOGGER.info("End testGetAllCountries");
    }

    /** HO-6: Find country by code */
    private static void testFindCountryByCode() throws CountryNotFoundException {
        LOGGER.info("Start testFindCountryByCode");
        Country country = countryService.findCountryByCode("IN");
        LOGGER.debug("Country: {}", country);
        LOGGER.info("End testFindCountryByCode");
    }

    /** HO-7: Add a new country */
    private static void testAddCountry() throws CountryNotFoundException {
        LOGGER.info("Start testAddCountry");
        Country country = new Country();
        country.setCode("XX");
        country.setName("Test Country");
        countryService.addCountry(country);
        // Verify it was added
        Country fetched = countryService.findCountryByCode("XX");
        LOGGER.debug("Added country: {}", fetched);
        LOGGER.info("End testAddCountry");
    }

    /** HO-8: Update a country's name */
    private static void testUpdateCountry() throws CountryNotFoundException {
        LOGGER.info("Start testUpdateCountry");
        countryService.updateCountry("XX", "Test Country Updated");
        Country updated = countryService.findCountryByCode("XX");
        LOGGER.debug("Updated country: {}", updated);
        LOGGER.info("End testUpdateCountry");
    }

    /** HO-9: Delete a country */
    private static void testDeleteCountry() {
        LOGGER.info("Start testDeleteCountry");
        countryService.deleteCountry("XX");
        LOGGER.debug("Country XX deleted");
        LOGGER.info("End testDeleteCountry");
    }

    // ══════════════════════════════════════════════════════════════════════
    //  DAY 2 HO-1 – COUNTRY QUERY METHODS
    // ══════════════════════════════════════════════════════════════════════

    /** Search countries containing 'ou' */
    private static void testSearchByName() {
        LOGGER.info("Start testSearchByName");
        List<Country> countries = countryService.searchByName("ou");
        LOGGER.debug("Countries containing 'ou': {}", countries);
        LOGGER.info("End testSearchByName");
    }

    /** Search countries containing 'ou', sorted A-Z */
    private static void testSearchByNameSorted() {
        LOGGER.info("Start testSearchByNameSorted");
        List<Country> countries = countryService.searchByNameSorted("ou");
        LOGGER.debug("Countries containing 'ou' (sorted): {}", countries);
        LOGGER.info("End testSearchByNameSorted");
    }

    /** Countries starting with 'Z' */
    private static void testSearchByStartingLetter() {
        LOGGER.info("Start testSearchByStartingLetter");
        List<Country> countries = countryService.searchByStartingLetter("Z");
        LOGGER.debug("Countries starting with 'Z': {}", countries);
        LOGGER.info("End testSearchByStartingLetter");
    }

    // ══════════════════════════════════════════════════════════════════════
    //  DAY 2 HO-2 – STOCK QUERY METHODS
    // ══════════════════════════════════════════════════════════════════════

    /** Facebook stocks in September 2019 */
    private static void testGetFBSeptemberStocks() {
        LOGGER.info("Start testGetFBSeptemberStocks");
        List<Stock> stocks = stockService.getFacebookSeptemberStocks();
        stocks.forEach(s -> LOGGER.debug("{}", s));
        LOGGER.info("End testGetFBSeptemberStocks");
    }

    /** Google stocks where close > 1250 */
    private static void testGoogleAbove1250() {
        LOGGER.info("Start testGoogleAbove1250");
        List<Stock> stocks = stockService.getGoogleStocksAbove1250();
        stocks.forEach(s -> LOGGER.debug("{}", s));
        LOGGER.info("End testGoogleAbove1250");
    }

    /** Top 3 stocks by highest volume */
    private static void testTop3Volume() {
        LOGGER.info("Start testTop3Volume");
        List<Stock> stocks = stockService.getTop3HighestVolumeStocks();
        stocks.forEach(s -> LOGGER.debug("{}", s));
        LOGGER.info("End testTop3Volume");
    }

    /** 3 lowest Netflix closing prices */
    private static void testTop3LowestNetflix() {
        LOGGER.info("Start testTop3LowestNetflix");
        List<Stock> stocks = stockService.getTop3LowestNetflixStocks();
        stocks.forEach(s -> LOGGER.debug("{}", s));
        LOGGER.info("End testTop3LowestNetflix");
    }

    // ══════════════════════════════════════════════════════════════════════
    //  DAY 2 HO-4 – EMPLOYEE MANY-TO-ONE
    // ══════════════════════════════════════════════════════════════════════

    /** Get employee with department (ManyToOne – EAGER by default) */
    private static void testGetEmployee() {
        LOGGER.info("Start testGetEmployee");
        Employee employee = employeeService.get(1);
        LOGGER.debug("Employee: {}", employee);
        LOGGER.debug("Department: {}", employee.getDepartment());
        LOGGER.debug("Skills: {}", employee.getSkillList());
        LOGGER.info("End testGetEmployee");
    }

    /** Add a new employee linked to department id=1 */
    private static void testAddEmployee() {
        LOGGER.info("Start testAddEmployee");
        Employee employee = new Employee();
        employee.setName("New Employee");
        employee.setSalary(50000.0);
        employee.setPermanent(true);
        employee.setDateOfBirth(new Date());

        Department department = departmentService.get(1);
        employee.setDepartment(department);

        employeeService.save(employee);
        LOGGER.debug("Saved Employee: {}", employee);
        LOGGER.info("End testAddEmployee");
    }

    /** Update employee's department */
    private static void testUpdateEmployee() {
        LOGGER.info("Start testUpdateEmployee");
        Employee employee = employeeService.get(1);
        Department newDept = departmentService.get(2);   // switch to different dept
        employee.setDepartment(newDept);
        employeeService.save(employee);
        LOGGER.debug("Updated Employee: {}", employee);
        LOGGER.info("End testUpdateEmployee");
    }

    // ══════════════════════════════════════════════════════════════════════
    //  DAY 2 HO-5 – DEPARTMENT ONE-TO-MANY
    // ══════════════════════════════════════════════════════════════════════

    /**
     * Get department with employee list (OneToMany – EAGER configured in Department.java).
     * NOTE: Without FetchType.EAGER in @OneToMany, this throws LazyInitializationException
     *       when accessed outside the session. The fix is already applied in Department.java.
     */
    private static void testGetDepartment() {
        LOGGER.info("Start testGetDepartment");
        Department department = departmentService.get(1);
        LOGGER.debug("Department: {}", department);
        LOGGER.debug("Employees: {}", department.getEmployeeList());
        LOGGER.info("End testGetDepartment");
    }

    // ══════════════════════════════════════════════════════════════════════
    //  DAY 2 HO-6 – EMPLOYEE <-> SKILL MANY-TO-MANY
    // ══════════════════════════════════════════════════════════════════════

    /**
     * Add a skill to an employee.
     * Change employeeId / skillId to a pair that doesn't already exist in employee_skill.
     */
    private static void testAddSkillToEmployee() {
        LOGGER.info("Start testAddSkillToEmployee");

        int employeeId = 1;
        int skillId    = 3;   // use a skill not yet linked to this employee

        Employee employee = employeeService.get(employeeId);
        Skill    skill    = skillService.get(skillId);

        employee.getSkillList().add(skill);
        employeeService.save(employee);

        LOGGER.debug("Employee after skill add: {}", employee);
        LOGGER.debug("Skills: {}", employee.getSkillList());
        LOGGER.info("End testAddSkillToEmployee");
    }

    // ══════════════════════════════════════════════════════════════════════
    //  DAY 3 HO-2 – HQL: ALL PERMANENT EMPLOYEES
    // ══════════════════════════════════════════════════════════════════════

    /**
     * Uses HQL with LEFT JOIN FETCH to load employees, departments, and skills
     * in a single optimised query instead of multiple round-trips.
     */
    private static void testGetAllPermanentEmployees() {
        LOGGER.info("Start testGetAllPermanentEmployees");
        List<Employee> employees = employeeService.getAllPermanentEmployees();
        LOGGER.debug("Permanent Employees: {}", employees);
        employees.forEach(e -> LOGGER.debug("Skills: {}", e.getSkillList()));
        LOGGER.info("End testGetAllPermanentEmployees");
    }

    // ══════════════════════════════════════════════════════════════════════
    //  DAY 3 HO-4 – HQL: AVERAGE SALARY
    // ══════════════════════════════════════════════════════════════════════

    private static void testGetAverageSalary() {
        LOGGER.info("Start testGetAverageSalary");
        double avgAll  = employeeService.getAverageSalary();
        double avgDept = employeeService.getAverageSalaryByDepartment(1);
        LOGGER.debug("Average salary (all):          {}", avgAll);
        LOGGER.debug("Average salary (department 1): {}", avgDept);
        LOGGER.info("End testGetAverageSalary");
    }

    // ══════════════════════════════════════════════════════════════════════
    //  DAY 3 HO-5 – NATIVE QUERY: GET ALL EMPLOYEES
    // ══════════════════════════════════════════════════════════════════════

    private static void testGetAllEmployeesNative() {
        LOGGER.info("Start testGetAllEmployeesNative");
        List<Employee> employees = employeeService.getAllEmployeesNative();
        LOGGER.debug("Employees (native query): {}", employees);
        LOGGER.info("End testGetAllEmployeesNative");
    }
}
