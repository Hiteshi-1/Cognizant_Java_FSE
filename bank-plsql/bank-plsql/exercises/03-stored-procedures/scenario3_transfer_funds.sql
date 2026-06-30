-- ============================================================
--  Exercise 3 – Stored Procedures
--  Scenario 3: TransferFunds – transfer a specified amount between
--              two accounts, checking sufficient balance first.
--
--  NOTE: This is the "plain" version requested in Exercise 3.
--        See Exercise 2 Scenario 1 (SafeTransferFunds) for the
--        version with full error handling and rollback/logging.
-- ============================================================

CREATE OR REPLACE PROCEDURE TransferFunds (
    p_from_account IN Accounts.AccountID%TYPE,
    p_to_account   IN Accounts.AccountID%TYPE,
    p_amount       IN NUMBER
) AS
    v_from_balance Accounts.Balance%TYPE;
BEGIN
    SELECT Balance INTO v_from_balance
      FROM Accounts
     WHERE AccountID = p_from_account;

    IF v_from_balance < p_amount THEN
        DBMS_OUTPUT.PUT_LINE('Transfer failed: Insufficient balance in account ' || p_from_account);
        RETURN;
    END IF;

    UPDATE Accounts
       SET Balance = Balance - p_amount,
           LastModified = SYSDATE
     WHERE AccountID = p_from_account;

    UPDATE Accounts
       SET Balance = Balance + p_amount,
           LastModified = SYSDATE
     WHERE AccountID = p_to_account;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transferred ' || p_amount || ' from account ' ||
                          p_from_account || ' to account ' || p_to_account);

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Transfer failed: Account ' || p_from_account || ' not found.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Transfer failed: ' || SQLERRM);
END TransferFunds;
/

-- Sample test calls:
-- EXEC TransferFunds(1, 2, 100);
-- EXEC TransferFunds(1, 2, 999999); -- insufficient balance
