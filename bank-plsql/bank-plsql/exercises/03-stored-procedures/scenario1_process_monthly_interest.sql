-- ============================================================
--  Exercise 3 – Stored Procedures
--  Scenario 1: ProcessMonthlyInterest – apply 1% interest to
--              all savings account balances.
-- ============================================================

CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
    v_rate CONSTANT NUMBER := 0.01;  -- 1% monthly interest
    v_count NUMBER := 0;
BEGIN
    FOR acct_rec IN (
        SELECT AccountID, Balance
          FROM Accounts
         WHERE AccountType = 'Savings'
    ) LOOP

        UPDATE Accounts
           SET Balance = Balance + (Balance * v_rate),
               LastModified = SYSDATE
         WHERE AccountID = acct_rec.AccountID;

        v_count := v_count + 1;

    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Monthly interest processed for ' || v_count || ' savings accounts.');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ProcName, ErrorMsg)
        VALUES ('ProcessMonthlyInterest', SQLERRM);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Error processing monthly interest: ' || SQLERRM);
END ProcessMonthlyInterest;
/

-- Sample test call:
-- EXEC ProcessMonthlyInterest;
