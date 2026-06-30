-- ============================================================
--  Exercise 6 – Cursors
--  Scenario 1: GenerateMonthlyStatements – explicit cursor that
--              retrieves all transactions for the current month
--              and prints a statement line for each.
-- ============================================================

SET SERVEROUTPUT ON;

DECLARE
    CURSOR c_statements IS
        SELECT c.Name,
               a.AccountID,
               t.TransactionID,
               t.TransactionDate,
               t.Amount,
               t.TransactionType
          FROM Transactions t
          JOIN Accounts   a ON a.AccountID  = t.AccountID
          JOIN Customers  c ON c.CustomerID = a.CustomerID
         WHERE TRUNC(t.TransactionDate, 'MM') = TRUNC(SYSDATE, 'MM')
         ORDER BY c.Name, t.TransactionDate;

    v_current_customer Customers.Name%TYPE := NULL;
BEGIN
    FOR stmt_rec IN c_statements LOOP

        IF v_current_customer IS NULL OR v_current_customer != stmt_rec.Name THEN
            DBMS_OUTPUT.PUT_LINE('---------------------------------------------');
            DBMS_OUTPUT.PUT_LINE('Monthly Statement for: ' || stmt_rec.Name);
            DBMS_OUTPUT.PUT_LINE('---------------------------------------------');
            v_current_customer := stmt_rec.Name;
        END IF;

        DBMS_OUTPUT.PUT_LINE(
            TO_CHAR(stmt_rec.TransactionDate, 'DD-MON-YYYY') ||
            ' | Account ' || stmt_rec.AccountID ||
            ' | ' || stmt_rec.TransactionType ||
            ' | Amount: ' || stmt_rec.Amount
        );

    END LOOP;

EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error generating monthly statements: ' || SQLERRM);
END;
/
