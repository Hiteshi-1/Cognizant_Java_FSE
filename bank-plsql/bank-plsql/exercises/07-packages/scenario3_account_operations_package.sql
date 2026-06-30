-- ============================================================
--  Exercise 7 – Packages
--  Scenario 3: AccountOperations – groups account-related
--              operations into a single package.
-- ============================================================

CREATE OR REPLACE PACKAGE AccountOperations AS

    PROCEDURE OpenAccount (
        p_account_id    IN Accounts.AccountID%TYPE,
        p_customer_id   IN Accounts.CustomerID%TYPE,
        p_account_type  IN Accounts.AccountType%TYPE,
        p_initial_balance IN Accounts.Balance%TYPE DEFAULT 0
    );

    PROCEDURE CloseAccount (
        p_account_id IN Accounts.AccountID%TYPE
    );

    FUNCTION GetTotalBalance (
        p_customer_id IN Customers.CustomerID%TYPE
    ) RETURN NUMBER;

END AccountOperations;
/

CREATE OR REPLACE PACKAGE BODY AccountOperations AS

    PROCEDURE OpenAccount (
        p_account_id    IN Accounts.AccountID%TYPE,
        p_customer_id   IN Accounts.CustomerID%TYPE,
        p_account_type  IN Accounts.AccountType%TYPE,
        p_initial_balance IN Accounts.Balance%TYPE DEFAULT 0
    ) AS
        v_customer_exists NUMBER;
    BEGIN
        SELECT COUNT(*) INTO v_customer_exists
          FROM Customers
         WHERE CustomerID = p_customer_id;

        IF v_customer_exists = 0 THEN
            RAISE_APPLICATION_ERROR(-20060, 'CustomerID ' || p_customer_id || ' does not exist.');
        END IF;

        INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
        VALUES (p_account_id, p_customer_id, p_account_type, p_initial_balance, SYSDATE);

        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Account ' || p_account_id || ' (' || p_account_type ||
                              ') opened for CustomerID ' || p_customer_id);

    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('Error: AccountID ' || p_account_id || ' already exists.');
        WHEN OTHERS THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('Error opening account: ' || SQLERRM);
    END OpenAccount;


    PROCEDURE CloseAccount (
        p_account_id IN Accounts.AccountID%TYPE
    ) AS
    BEGIN
        DELETE FROM Accounts WHERE AccountID = p_account_id;

        IF SQL%ROWCOUNT = 0 THEN
            RAISE_APPLICATION_ERROR(-20061, 'AccountID ' || p_account_id || ' not found.');
        END IF;

        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Account ' || p_account_id || ' closed successfully.');

    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('Error closing account: ' || SQLERRM);
    END CloseAccount;


    FUNCTION GetTotalBalance (
        p_customer_id IN Customers.CustomerID%TYPE
    ) RETURN NUMBER
    AS
        v_total NUMBER;
    BEGIN
        SELECT NVL(SUM(Balance), 0) INTO v_total
          FROM Accounts
         WHERE CustomerID = p_customer_id;

        RETURN v_total;

    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error getting total balance: ' || SQLERRM);
            RETURN NULL;
    END GetTotalBalance;

END AccountOperations;
/

-- Sample test calls:
-- EXEC AccountOperations.OpenAccount(5, 1, 'Savings', 1000);
-- EXEC AccountOperations.CloseAccount(5);
-- SELECT AccountOperations.GetTotalBalance(1) FROM DUAL;
