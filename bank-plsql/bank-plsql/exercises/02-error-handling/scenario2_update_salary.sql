-- ============================================================
--  Exercise 2 – Error Handling
--  Scenario 2: UpdateSalary – increase employee salary by a
--              percentage, logging an error if the employee
--              does not exist.
-- ============================================================

CREATE OR REPLACE PROCEDURE UpdateSalary (
    p_employee_id    IN Employees.EmployeeID%TYPE,
    p_increase_pct   IN NUMBER
) AS
    v_exists NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_exists
      FROM Employees
     WHERE EmployeeID = p_employee_id;

    IF v_exists = 0 THEN
        RAISE_APPLICATION_ERROR(-20010, 'Employee ID ' || p_employee_id || ' does not exist.');
    END IF;

    UPDATE Employees
       SET Salary = Salary + (Salary * p_increase_pct / 100)
     WHERE EmployeeID = p_employee_id;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Salary updated for Employee ID ' || p_employee_id ||
                          ' by ' || p_increase_pct || '%');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ProcName, ErrorMsg)
        VALUES ('UpdateSalary', SQLERRM);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Error updating salary: ' || SQLERRM);
END UpdateSalary;
/

-- Sample test calls:
-- EXEC UpdateSalary(1, 10);     -- successful 10% raise
-- EXEC UpdateSalary(999, 10);   -- triggers "Employee ID does not exist" error
