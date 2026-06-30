-- ============================================================
--  Exercise 6 – Cursors
--  Scenario 2: ApplyAnnualFee – explicit cursor that deducts an
--              annual maintenance fee from every account balance.
-- ============================================================

SET SERVEROUTPUT ON;

DECLARE
    CURSOR c_accounts IS
        SELECT AccountID, Balance FROM Accounts FOR UPDATE OF Balance;

    v_annual_fee CONSTANT NUMBER := 25;  -- flat annual maintenance fee
    v_count NUMBER := 0;
BEGIN
    FOR acct_rec IN c_accounts LOOP

        IF acct_rec.Balance >= v_annual_fee THEN
            UPDATE Accounts
               SET Balance = Balance - v_annual_fee,
                   LastModified = SYSDATE
             WHERE CURRENT OF c_accounts;

            v_count := v_count + 1;
        ELSE
            DBMS_OUTPUT.PUT_LINE(
                'Skipped AccountID ' || acct_rec.AccountID ||
                ': balance too low to deduct annual fee.'
            );
        END IF;

    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Annual fee of ' || v_annual_fee || ' applied to ' || v_count || ' accounts.');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error applying annual fee: ' || SQLERRM);
END;
/
