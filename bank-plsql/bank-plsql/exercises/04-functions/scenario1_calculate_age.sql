-- ============================================================
--  Exercise 4 – Functions
--  Scenario 1: CalculateAge – returns a customer's age in years
--              given their date of birth.
-- ============================================================

CREATE OR REPLACE FUNCTION CalculateAge (
    p_dob IN DATE
) RETURN NUMBER
AS
    v_age NUMBER;
BEGIN
    v_age := TRUNC(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
    RETURN v_age;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error calculating age: ' || SQLERRM);
        RETURN NULL;
END CalculateAge;
/

-- Sample test calls:
-- SELECT CustomerID, Name, CalculateAge(DOB) AS Age FROM Customers;
-- SELECT CalculateAge(TO_DATE('1990-07-20','YYYY-MM-DD')) FROM DUAL;
