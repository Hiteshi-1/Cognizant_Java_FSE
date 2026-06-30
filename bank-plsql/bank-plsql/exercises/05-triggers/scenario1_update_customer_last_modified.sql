-- ============================================================
--  Exercise 5 – Triggers
--  Scenario 1: UpdateCustomerLastModified – automatically set
--              LastModified to SYSDATE whenever a customer row
--              is updated.
-- ============================================================

CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    :NEW.LastModified := SYSDATE;
END UpdateCustomerLastModified;
/

-- Sample test:
-- UPDATE Customers SET Balance = Balance + 100 WHERE CustomerID = 1;
-- SELECT CustomerID, Balance, LastModified FROM Customers WHERE CustomerID = 1;
