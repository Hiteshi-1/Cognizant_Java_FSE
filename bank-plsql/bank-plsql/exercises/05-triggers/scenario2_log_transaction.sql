-- ============================================================
--  Exercise 5 – Triggers
--  Scenario 2: LogTransaction – inserts an audit record into
--              AuditLog whenever a new transaction is inserted.
-- ============================================================

CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (TransactionID, AccountID, Amount, TransactionType, LoggedAt)
    VALUES (:NEW.TransactionID, :NEW.AccountID, :NEW.Amount, :NEW.TransactionType, SYSDATE);
END LogTransaction;
/

-- Sample test:
-- INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
-- VALUES (3, 1, SYSDATE, 250, 'Deposit');
-- SELECT * FROM AuditLog;
