-- ============================================================
--  Exercise 7 – Packages
--  Scenario 1: CustomerManagement – groups customer-related
--              procedures and functions into a single package.
-- ============================================================

CREATE OR REPLACE PACKAGE CustomerManagement AS

    PROCEDURE AddCustomer (
        p_customer_id IN Customers.CustomerID%TYPE,
        p_name        IN Customers.Name%TYPE,
        p_dob         IN Customers.DOB%TYPE,
        p_balance     IN Customers.Balance%TYPE
    );

    PROCEDURE UpdateCustomerDetails (
        p_customer_id IN Customers.CustomerID%TYPE,
        p_name        IN Customers.Name%TYPE DEFAULT NULL,
        p_balance     IN Customers.Balance%TYPE DEFAULT NULL
    );

    FUNCTION GetCustomerBalance (
        p_customer_id IN Customers.CustomerID%TYPE
    ) RETURN NUMBER;

END CustomerManagement;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement AS

    PROCEDURE AddCustomer (
        p_customer_id IN Customers.CustomerID%TYPE,
        p_name        IN Customers.Name%TYPE,
        p_dob         IN Customers.DOB%TYPE,
        p_balance     IN Customers.Balance%TYPE
    ) AS
    BEGIN
        INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
        VALUES (p_customer_id, p_name, p_dob, p_balance, SYSDATE);

        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Customer added: ' || p_name);

    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('Error: CustomerID ' || p_customer_id || ' already exists.');
        WHEN OTHERS THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('Error adding customer: ' || SQLERRM);
    END AddCustomer;


    PROCEDURE UpdateCustomerDetails (
        p_customer_id IN Customers.CustomerID%TYPE,
        p_name        IN Customers.Name%TYPE DEFAULT NULL,
        p_balance     IN Customers.Balance%TYPE DEFAULT NULL
    ) AS
    BEGIN
        UPDATE Customers
           SET Name    = NVL(p_name, Name),
               Balance = NVL(p_balance, Balance)
         WHERE CustomerID = p_customer_id;

        IF SQL%ROWCOUNT = 0 THEN
            RAISE_APPLICATION_ERROR(-20040, 'CustomerID ' || p_customer_id || ' not found.');
        END IF;

        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Customer ' || p_customer_id || ' updated successfully.');

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('Error updating customer: ' || SQLERRM);
    END UpdateCustomerDetails;


    FUNCTION GetCustomerBalance (
        p_customer_id IN Customers.CustomerID%TYPE
    ) RETURN NUMBER
    AS
        v_balance Customers.Balance%TYPE;
    BEGIN
        SELECT Balance INTO v_balance
          FROM Customers
         WHERE CustomerID = p_customer_id;

        RETURN v_balance;

    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('CustomerID ' || p_customer_id || ' not found.');
            RETURN NULL;
    END GetCustomerBalance;

END CustomerManagement;
/

-- Sample test calls:
-- EXEC CustomerManagement.AddCustomer(6, 'Test Customer', TO_DATE('2000-01-01','YYYY-MM-DD'), 500);
-- EXEC CustomerManagement.UpdateCustomerDetails(6, p_balance => 750);
-- SELECT CustomerManagement.GetCustomerBalance(6) FROM DUAL;
