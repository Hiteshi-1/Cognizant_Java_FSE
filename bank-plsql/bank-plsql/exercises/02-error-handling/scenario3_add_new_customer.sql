-- ============================================================
--  Exercise 2 – Error Handling
--  Scenario 3: AddNewCustomer – insert a new customer, handling
--              duplicate-ID errors gracefully.
-- ============================================================

CREATE OR REPLACE PROCEDURE AddNewCustomer (
    p_customer_id IN Customers.CustomerID%TYPE,
    p_name        IN Customers.Name%TYPE,
    p_dob         IN Customers.DOB%TYPE,
    p_balance     IN Customers.Balance%TYPE
) AS
    e_duplicate_customer EXCEPTION;
    PRAGMA EXCEPTION_INIT(e_duplicate_customer, -1); -- ORA-00001: unique constraint violated
BEGIN
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
    VALUES (p_customer_id, p_name, p_dob, p_balance, SYSDATE);

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Customer added successfully: ' || p_name);

EXCEPTION
    WHEN e_duplicate_customer THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ProcName, ErrorMsg)
        VALUES ('AddNewCustomer', 'Duplicate CustomerID: ' || p_customer_id);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Error: Customer with ID ' || p_customer_id || ' already exists.');

    WHEN OTHERS THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ProcName, ErrorMsg)
        VALUES ('AddNewCustomer', SQLERRM);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Error adding customer: ' || SQLERRM);
END AddNewCustomer;
/

-- Sample test calls:
-- EXEC AddNewCustomer(5, 'New Customer', TO_DATE('1999-01-01','YYYY-MM-DD'), 500);
-- EXEC AddNewCustomer(1, 'Duplicate John', TO_DATE('1985-05-15','YYYY-MM-DD'), 1000); -- triggers duplicate error
