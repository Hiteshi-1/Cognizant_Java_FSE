-- ============================================================
--  run_all.sql – Master script to set up the entire schema and
--                deploy all PL/SQL objects in the correct order.
--
--  Usage (SQL*Plus / SQLcl):
--      SQL> @run_all.sql
-- ============================================================

SET SERVEROUTPUT ON;
SET ECHO ON;

PROMPT === Creating schema ===
@schema/01_create_tables.sql

PROMPT === Inserting sample data ===
@sample-data/02_insert_sample_data.sql

PROMPT === Exercise 2: Error-handling procedures ===
@exercises/02-error-handling/scenario1_safe_transfer_funds.sql
@exercises/02-error-handling/scenario2_update_salary.sql
@exercises/02-error-handling/scenario3_add_new_customer.sql

PROMPT === Exercise 3: Stored procedures ===
@exercises/03-stored-procedures/scenario1_process_monthly_interest.sql
@exercises/03-stored-procedures/scenario2_update_employee_bonus.sql
@exercises/03-stored-procedures/scenario3_transfer_funds.sql

PROMPT === Exercise 4: Functions ===
@exercises/04-functions/scenario1_calculate_age.sql
@exercises/04-functions/scenario2_calculate_monthly_installment.sql
@exercises/04-functions/scenario3_has_sufficient_balance.sql

PROMPT === Exercise 5: Triggers ===
@exercises/05-triggers/scenario1_update_customer_last_modified.sql
@exercises/05-triggers/scenario2_log_transaction.sql
@exercises/05-triggers/scenario3_check_transaction_rules.sql

PROMPT === Exercise 7: Packages ===
@exercises/07-packages/scenario1_customer_management_package.sql
@exercises/07-packages/scenario2_employee_management_package.sql
@exercises/07-packages/scenario3_account_operations_package.sql

PROMPT === Setup complete. ===
PROMPT === Run Exercise 1 and Exercise 6 scripts manually as anonymous PL/SQL blocks (see exercises/01-control-structures and exercises/06-cursors). ===
