-- ============================================================
--  Exercise 7 – Packages
--  Scenario 2: EmployeeManagement – groups employee-related
--              procedures and functions into a single package.
-- ============================================================

CREATE OR REPLACE PACKAGE EmployeeManagement AS

    PROCEDURE HireEmployee (
        p_employee_id IN Employees.EmployeeID%TYPE,
        p_name        IN Employees.Name%TYPE,
        p_position    IN Employees.Position%TYPE,
        p_salary      IN Employees.Salary%TYPE,
        p_department  IN Employees.Department%TYPE
    );

    PROCEDURE UpdateEmployeeDetails (
        p_employee_id IN Employees.EmployeeID%TYPE,
        p_position    IN Employees.Position%TYPE DEFAULT NULL,
        p_salary      IN Employees.Salary%TYPE DEFAULT NULL,
        p_department  IN Employees.Department%TYPE DEFAULT NULL
    );

    FUNCTION CalculateAnnualSalary (
        p_employee_id IN Employees.EmployeeID%TYPE
    ) RETURN NUMBER;

END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS

    PROCEDURE HireEmployee (
        p_employee_id IN Employees.EmployeeID%TYPE,
        p_name        IN Employees.Name%TYPE,
        p_position    IN Employees.Position%TYPE,
        p_salary      IN Employees.Salary%TYPE,
        p_department  IN Employees.Department%TYPE
    ) AS
    BEGIN
        INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
        VALUES (p_employee_id, p_name, p_position, p_salary, p_department, SYSDATE);

        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Employee hired: ' || p_name || ' (' || p_position || ')');

    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('Error: EmployeeID ' || p_employee_id || ' already exists.');
        WHEN OTHERS THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('Error hiring employee: ' || SQLERRM);
    END HireEmployee;


    PROCEDURE UpdateEmployeeDetails (
        p_employee_id IN Employees.EmployeeID%TYPE,
        p_position    IN Employees.Position%TYPE DEFAULT NULL,
        p_salary      IN Employees.Salary%TYPE DEFAULT NULL,
        p_department  IN Employees.Department%TYPE DEFAULT NULL
    ) AS
    BEGIN
        UPDATE Employees
           SET Position   = NVL(p_position, Position),
               Salary     = NVL(p_salary, Salary),
               Department = NVL(p_department, Department)
         WHERE EmployeeID = p_employee_id;

        IF SQL%ROWCOUNT = 0 THEN
            RAISE_APPLICATION_ERROR(-20050, 'EmployeeID ' || p_employee_id || ' not found.');
        END IF;

        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Employee ' || p_employee_id || ' updated successfully.');

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('Error updating employee: ' || SQLERRM);
    END UpdateEmployeeDetails;


    FUNCTION CalculateAnnualSalary (
        p_employee_id IN Employees.EmployeeID%TYPE
    ) RETURN NUMBER
    AS
        v_monthly_salary Employees.Salary%TYPE;
    BEGIN
        SELECT Salary INTO v_monthly_salary
          FROM Employees
         WHERE EmployeeID = p_employee_id;

        RETURN v_monthly_salary * 12;

    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('EmployeeID ' || p_employee_id || ' not found.');
            RETURN NULL;
    END CalculateAnnualSalary;

END EmployeeManagement;
/

-- Sample test calls:
-- EXEC EmployeeManagement.HireEmployee(3, 'New Hire', 'Analyst', 55000, 'Finance');
-- EXEC EmployeeManagement.UpdateEmployeeDetails(3, p_salary => 58000);
-- SELECT EmployeeManagement.CalculateAnnualSalary(3) FROM DUAL;
