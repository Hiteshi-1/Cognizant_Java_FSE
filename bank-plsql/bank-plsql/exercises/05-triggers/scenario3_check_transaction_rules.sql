-- ============================================================
--  Exercise 5 – Triggers
--  Scenario 3: CheckTransactionRules – enforce that withdrawals
--              do not exceed the account balance and that
--              deposits are positive, before the transaction
--              row is inserted.
-- ============================================================

CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    v_balance Accounts.Balance%TYPE;
BEGIN
    SELECT Balance INTO v_balance
      FROM Accounts
     WHERE AccountID = :NEW.AccountID;

    IF :NEW.TransactionType = 'Withdrawal' THEN
        IF :NEW.Amount > v_balance THEN
            RAISE_APPLICATION_ERROR(-20030,
                'Withdrawal amount exceeds account balance for AccountID ' || :NEW.AccountID);
        END IF;

    ELSIF :NEW.TransactionType = 'Deposit' THEN
        IF :NEW.Amount <= 0 THEN
            RAISE_APPLICATION_ERROR(-20031, 'Deposit amount must be positive.');
        END IF;
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20032, 'AccountID ' || :NEW.AccountID || ' not found.');
END CheckTransactionRules;
/

-- Sample tests:
-- Valid deposit:
-- INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
-- VALUES (4, 1, SYSDATE, 100, 'Deposit');
--
-- Invalid withdrawal (exceeds balance) -> raises ORA-20030:
-- INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
-- VALUES (5, 1, SYSDATE, 999999, 'Withdrawal');
--
-- Invalid deposit (non-positive amount) -> raises ORA-20031:
-- INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
-- VALUES (6, 1, SYSDATE, -50, 'Deposit');
