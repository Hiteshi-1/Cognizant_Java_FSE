-- ============================================================
--  Exercise 2 – Error Handling
--  Scenario 1: SafeTransferFunds – transfer funds between accounts
--              with rollback and error logging on failure.
-- ============================================================

CREATE OR REPLACE PROCEDURE SafeTransferFunds (
    p_from_account IN Accounts.AccountID%TYPE,
    p_to_account   IN Accounts.AccountID%TYPE,
    p_amount       IN NUMBER
) AS
    v_from_balance Accounts.Balance%TYPE;
    e_insufficient_funds EXCEPTION;
BEGIN
    -- Lock and check source account balance
    SELECT Balance INTO v_from_balance
      FROM Accounts
     WHERE AccountID = p_from_account
       FOR UPDATE;

    IF v_from_balance < p_amount THEN
        RAISE e_insufficient_funds;
    END IF;

    UPDATE Accounts
       SET Balance = Balance - p_amount,
           LastModified = SYSDATE
     WHERE AccountID = p_from_account;

    UPDATE Accounts
       SET Balance = Balance + p_amount,
           LastModified = SYSDATE
     WHERE AccountID = p_to_account;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Destination account not found: ' || p_to_account);
    END IF;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transfer successful: ' || p_amount ||
                          ' from account ' || p_from_account ||
                          ' to account ' || p_to_account);

EXCEPTION
    WHEN e_insufficient_funds THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ProcName, ErrorMsg)
        VALUES ('SafeTransferFunds', 'Insufficient funds in account ' || p_from_account);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Error: Insufficient funds in account ' || p_from_account);

    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ProcName, ErrorMsg)
        VALUES ('SafeTransferFunds', 'Source account not found: ' || p_from_account);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Error: Source account not found: ' || p_from_account);

    WHEN OTHERS THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ProcName, ErrorMsg)
        VALUES ('SafeTransferFunds', SQLERRM);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Error during fund transfer: ' || SQLERRM);
END SafeTransferFunds;
/

-- Sample test calls:
-- EXEC SafeTransferFunds(1, 2, 100);          -- successful transfer
-- EXEC SafeTransferFunds(1, 2, 999999999);    -- triggers insufficient funds
-- EXEC SafeTransferFunds(999, 2, 100);        -- triggers NO_DATA_FOUND
