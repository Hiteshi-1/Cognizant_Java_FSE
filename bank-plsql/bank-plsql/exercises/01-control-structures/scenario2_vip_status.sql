-- ============================================================
--  Exercise 1 – Control Structures
--  Scenario 2: Set IsVIP = 'Y' for customers with Balance > $10,000
-- ============================================================

SET SERVEROUTPUT ON;

BEGIN
    FOR cust_rec IN (SELECT CustomerID, Name, Balance FROM Customers) LOOP

        IF cust_rec.Balance > 10000 THEN
            UPDATE Customers
               SET IsVIP = 'Y'
             WHERE CustomerID = cust_rec.CustomerID;

            DBMS_OUTPUT.PUT_LINE(
                cust_rec.Name || ' (Balance: ' || cust_rec.Balance || ') promoted to VIP.'
            );
        ELSE
            UPDATE Customers
               SET IsVIP = 'N'
             WHERE CustomerID = cust_rec.CustomerID;
        END IF;

    END LOOP;

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error updating VIP status: ' || SQLERRM);
END;
/
