-- ============================================================
--  Exercise 4 – Functions
--  Scenario 2: CalculateMonthlyInstallment – returns the EMI for
--              a given loan amount, annual interest rate (%),
--              and loan duration in years.
--
--  Formula (standard amortizing loan EMI):
--    EMI = P * r * (1+r)^n / ((1+r)^n - 1)
--    where P = principal, r = monthly interest rate, n = number of months
-- ============================================================

CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment (
    p_loan_amount     IN NUMBER,
    p_interest_rate   IN NUMBER,   -- annual rate, e.g. 5 for 5%
    p_duration_years  IN NUMBER
) RETURN NUMBER
AS
    v_monthly_rate NUMBER;
    v_num_payments NUMBER;
    v_emi          NUMBER;
BEGIN
    v_monthly_rate := (p_interest_rate / 100) / 12;
    v_num_payments := p_duration_years * 12;

    IF v_monthly_rate = 0 THEN
        -- Zero-interest loan: simple division
        v_emi := p_loan_amount / v_num_payments;
    ELSE
        v_emi := p_loan_amount * v_monthly_rate * POWER(1 + v_monthly_rate, v_num_payments)
                 / (POWER(1 + v_monthly_rate, v_num_payments) - 1);
    END IF;

    RETURN ROUND(v_emi, 2);

EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error calculating monthly installment: ' || SQLERRM);
        RETURN NULL;
END CalculateMonthlyInstallment;
/

-- Sample test calls:
-- SELECT CalculateMonthlyInstallment(5000, 5, 5) FROM DUAL;
-- SELECT LoanID, CalculateMonthlyInstallment(LoanAmount, InterestRate, 5) AS EMI FROM Loans;
