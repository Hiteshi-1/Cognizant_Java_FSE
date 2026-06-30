-- ============================================================
--  Exercise 6 – Cursors
--  Scenario 3: UpdateLoanInterestRates – explicit cursor that
--              fetches all loans and updates interest rates
--              based on a new bank policy.
--
--  Policy used here (illustrative): loans under 5 years duration
--  get a 0.5% rate decrease; loans 5 years or longer get a 0.25%
--  rate increase. Adjust thresholds as needed for your scenario.
-- ============================================================

SET SERVEROUTPUT ON;

DECLARE
    CURSOR c_loans IS
        SELECT LoanID, InterestRate, StartDate, EndDate
          FROM Loans
         FOR UPDATE OF InterestRate;

    v_duration_years NUMBER;
    v_new_rate       NUMBER;
BEGIN
    FOR loan_rec IN c_loans LOOP

        v_duration_years := MONTHS_BETWEEN(loan_rec.EndDate, loan_rec.StartDate) / 12;

        IF v_duration_years < 5 THEN
            v_new_rate := loan_rec.InterestRate - 0.5;
        ELSE
            v_new_rate := loan_rec.InterestRate + 0.25;
        END IF;

        -- Never allow interest rate to drop below 0
        IF v_new_rate < 0 THEN
            v_new_rate := 0;
        END IF;

        UPDATE Loans
           SET InterestRate = v_new_rate
         WHERE CURRENT OF c_loans;

        DBMS_OUTPUT.PUT_LINE(
            'Loan #' || loan_rec.LoanID ||
            ': rate changed from ' || loan_rec.InterestRate ||
            ' to ' || v_new_rate
        );

    END LOOP;

    COMMIT;

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error updating loan interest rates: ' || SQLERRM);
END;
/
