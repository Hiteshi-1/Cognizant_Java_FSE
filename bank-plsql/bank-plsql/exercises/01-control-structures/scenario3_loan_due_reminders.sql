-- ============================================================
--  Exercise 1 – Control Structures
--  Scenario 3: Print reminders for loans due within the next 30 days
-- ============================================================

SET SERVEROUTPUT ON;

BEGIN
    FOR loan_rec IN (
        SELECT l.LoanID, l.CustomerID, c.Name, l.EndDate, l.LoanAmount
          FROM Loans l
          JOIN Customers c ON c.CustomerID = l.CustomerID
         WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30
    ) LOOP

        DBMS_OUTPUT.PUT_LINE(
            'Reminder: Loan #' || loan_rec.LoanID ||
            ' for ' || loan_rec.Name ||
            ' (Amount: ' || loan_rec.LoanAmount || ') is due on ' ||
            TO_CHAR(loan_rec.EndDate, 'DD-MON-YYYY')
        );

    END LOOP;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error generating loan reminders: ' || SQLERRM);
END;
/
