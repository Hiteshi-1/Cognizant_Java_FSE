-- ============================================================
--  Exercise 1 – Control Structures
--  Scenario 1: Apply 1% loan interest discount to customers above 60
-- ============================================================

SET SERVEROUTPUT ON;

DECLARE
    v_age NUMBER;
BEGIN
    FOR cust_rec IN (SELECT CustomerID, Name, DOB FROM Customers) LOOP

        v_age := TRUNC(MONTHS_BETWEEN(SYSDATE, cust_rec.DOB) / 12);

        IF v_age > 60 THEN
            UPDATE Loans
               SET InterestRate = InterestRate - (InterestRate * 0.01)
             WHERE CustomerID = cust_rec.CustomerID;

            DBMS_OUTPUT.PUT_LINE(
                'Discount applied for ' || cust_rec.Name ||
                ' (Age: ' || v_age || ') - Rows updated: ' || SQL%ROWCOUNT
            );
        END IF;

    END LOOP;

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error applying loan discounts: ' || SQLERRM);
END;
/
