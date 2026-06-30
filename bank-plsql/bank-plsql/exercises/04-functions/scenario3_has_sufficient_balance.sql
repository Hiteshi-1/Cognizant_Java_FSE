-- ============================================================
--  Exercise 4 – Functions
--  Scenario 3: HasSufficientBalance – returns TRUE/FALSE indicating
--              whether an account has at least the specified amount.
--
--  NOTE: Returns a VARCHAR2('TRUE'/'FALSE') since plain SQL cannot
--        directly display a BOOLEAN. A native BOOLEAN-returning
--        overload is also provided for use from PL/SQL code.
-- ============================================================

CREATE OR REPLACE FUNCTION HasSufficientBalance (
    p_account_id IN Accounts.AccountID%TYPE,
    p_amount     IN NUMBER
) RETURN VARCHAR2
AS
    v_balance Accounts.Balance%TYPE;
BEGIN
    SELECT Balance INTO v_balance
      FROM Accounts
     WHERE AccountID = p_account_id;

    IF v_balance >= p_amount THEN
        RETURN 'TRUE';
    ELSE
        RETURN 'FALSE';
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Account ' || p_account_id || ' not found.');
        RETURN 'FALSE';
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error checking balance: ' || SQLERRM);
        RETURN 'FALSE';
END HasSufficientBalance;
/

-- Sample test calls (from SQL):
-- SELECT HasSufficientBalance(1, 500) FROM DUAL;
-- SELECT HasSufficientBalance(1, 999999) FROM DUAL;


-- ============================================================
--  Optional: native BOOLEAN-returning version for use from
--  PL/SQL blocks (PL/SQL supports BOOLEAN return types; pure
--  SQL does not, hence the VARCHAR2 version above).
-- ============================================================

CREATE OR REPLACE FUNCTION HasSufficientBalanceBool (
    p_account_id IN Accounts.AccountID%TYPE,
    p_amount     IN NUMBER
) RETURN BOOLEAN
AS
    v_balance Accounts.Balance%TYPE;
BEGIN
    SELECT Balance INTO v_balance
      FROM Accounts
     WHERE AccountID = p_account_id;

    RETURN v_balance >= p_amount;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN FALSE;
    WHEN OTHERS THEN
        RETURN FALSE;
END HasSufficientBalanceBool;
/

-- Sample usage from a PL/SQL block:
-- BEGIN
--     IF HasSufficientBalanceBool(1, 500) THEN
--         DBMS_OUTPUT.PUT_LINE('Sufficient balance.');
--     ELSE
--         DBMS_OUTPUT.PUT_LINE('Insufficient balance.');
--     END IF;
-- END;
-- /
