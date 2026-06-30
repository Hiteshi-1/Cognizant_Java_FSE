-- ============================================================
--  Exercise 3 – Stored Procedures
--  Scenario 2: UpdateEmployeeBonus – add a bonus percentage to
--              salaries of all employees in a given department.
-- ============================================================

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_department    IN Employees.Department%TYPE,
    p_bonus_pct     IN NUMBER
) AS
    v_count NUMBER := 0;
BEGIN
    UPDATE Employees
       SET Salary = Salary + (Salary * p_bonus_pct / 100)
     WHERE Department = p_department;

    v_count := SQL%ROWCOUNT;

    IF v_count = 0 THEN
        RAISE_APPLICATION_ERROR(-20020, 'No employees found in department: ' || p_department);
    END IF;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Bonus of ' || p_bonus_pct || '% applied to ' ||
                          v_count || ' employees in ' || p_department || ' department.');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ProcName, ErrorMsg)
        VALUES ('UpdateEmployeeBonus', SQLERRM);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Error updating employee bonus: ' || SQLERRM);
END UpdateEmployeeBonus;
/

-- Sample test calls:
-- EXEC UpdateEmployeeBonus('IT', 5);
-- EXEC UpdateEmployeeBonus('Marketing', 5); -- triggers "no employees found" error
