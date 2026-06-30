# bank-plsql

Oracle PL/SQL hands-on solutions for a **Bank Management System** — covering
control structures, error handling, stored procedures, functions, triggers,
cursors, and packages.

---

## Project Structure

```
bank-plsql/
├── run_all.sql                          ← master setup script
├── schema/
│   └── 01_create_tables.sql             ← DDL: Customers, Accounts, Transactions, Loans, Employees, AuditLog, ErrorLog
├── sample-data/
│   └── 02_insert_sample_data.sql        ← sample rows (incl. extra rows for senior/VIP/due-loan scenarios)
└── exercises/
    ├── 01-control-structures/
    │   ├── scenario1_loan_discount_seniors.sql
    │   ├── scenario2_vip_status.sql
    │   └── scenario3_loan_due_reminders.sql
    ├── 02-error-handling/
    │   ├── scenario1_safe_transfer_funds.sql
    │   ├── scenario2_update_salary.sql
    │   └── scenario3_add_new_customer.sql
    ├── 03-stored-procedures/
    │   ├── scenario1_process_monthly_interest.sql
    │   ├── scenario2_update_employee_bonus.sql
    │   └── scenario3_transfer_funds.sql
    ├── 04-functions/
    │   ├── scenario1_calculate_age.sql
    │   ├── scenario2_calculate_monthly_installment.sql
    │   └── scenario3_has_sufficient_balance.sql
    ├── 05-triggers/
    │   ├── scenario1_update_customer_last_modified.sql
    │   ├── scenario2_log_transaction.sql
    │   └── scenario3_check_transaction_rules.sql
    ├── 06-cursors/
    │   ├── scenario1_generate_monthly_statements.sql
    │   ├── scenario2_apply_annual_fee.sql
    │   └── scenario3_update_loan_interest_rates.sql
    └── 07-packages/
        ├── scenario1_customer_management_package.sql
        ├── scenario2_employee_management_package.sql
        └── scenario3_account_operations_package.sql
```

---

## Exercise Reference

| Exercise | Object | Name |
|---|---|---|
| 1.1 | Anonymous block | Loan discount for customers > 60 |
| 1.2 | Anonymous block | VIP flag for balance > $10,000 |
| 1.3 | Anonymous block | Loan due reminders (next 30 days) |
| 2.1 | Procedure | `SafeTransferFunds` |
| 2.2 | Procedure | `UpdateSalary` |
| 2.3 | Procedure | `AddNewCustomer` |
| 3.1 | Procedure | `ProcessMonthlyInterest` |
| 3.2 | Procedure | `UpdateEmployeeBonus` |
| 3.3 | Procedure | `TransferFunds` |
| 4.1 | Function | `CalculateAge` |
| 4.2 | Function | `CalculateMonthlyInstallment` |
| 4.3 | Function | `HasSufficientBalance` (+ `HasSufficientBalanceBool`) |
| 5.1 | Trigger | `UpdateCustomerLastModified` |
| 5.2 | Trigger | `LogTransaction` |
| 5.3 | Trigger | `CheckTransactionRules` |
| 6.1 | Cursor block | `GenerateMonthlyStatements` |
| 6.2 | Cursor block | `ApplyAnnualFee` |
| 6.3 | Cursor block | `UpdateLoanInterestRates` |
| 7.1 | Package | `CustomerManagement` |
| 7.2 | Package | `EmployeeManagement` |
| 7.3 | Package | `AccountOperations` |

---

## How to Run

### Prerequisites
- Oracle Database (or Oracle Live SQL / Oracle XE)
- SQL*Plus or SQLcl, or any SQL client connected to your schema

### Quick start
```sql
SQL> @run_all.sql
```
This creates the schema, loads sample data, and compiles all stored
procedures, functions, triggers, and packages. Exercise 1 and Exercise 6
scripts are anonymous PL/SQL blocks meant to be run individually (since they
print results rather than being reusable named objects) — run them directly:

```sql
SQL> @exercises/01-control-structures/scenario1_loan_discount_seniors.sql
SQL> @exercises/06-cursors/scenario1_generate_monthly_statements.sql
```

### Calling the procedures/functions
Each script includes commented sample test calls at the bottom, e.g.:
```sql
EXEC SafeTransferFunds(1, 2, 100);
SELECT CalculateAge(DOB) FROM Customers;
EXEC CustomerManagement.AddCustomer(6, 'Test', SYSDATE, 500);
```

---

## Design Notes

- **`IsVIP` column**: Added to the `Customers` table (not in the original
  schema doc) to support Exercise 1 Scenario 2, since the scenario requires
  persisting a VIP flag.
- **`AuditLog` / `ErrorLog` tables**: Added as supporting tables for
  Exercise 5 Scenario 2 (transaction audit trail) and Exercise 2 (centralized
  error logging across all error-handling procedures).
- **Trigger interaction**: Both `LogTransaction` (Ex 5.2) and
  `CheckTransactionRules` (Ex 5.3) fire on `Transactions` inserts.
  `CheckTransactionRules` is `BEFORE INSERT` (validates/blocks bad data) and
  `LogTransaction` is `AFTER INSERT` (only logs transactions that passed
  validation) — this ordering is intentional so invalid transactions are
  never audited as if they succeeded.
- **`TransferFunds` vs `SafeTransferFunds`**: Exercise 3 Scenario 3 asks for
  a plain transfer procedure with a balance check; Exercise 2 Scenario 1 asks
  for the same operation but with full exception handling, rollback, and
  error logging. Both are implemented separately so each exercise's solution
  is self-contained and directly matches what was asked.
- **`HasSufficientBalance`**: Provided as a `VARCHAR2`-returning function
  (callable from plain SQL `SELECT`) plus a `BOOLEAN`-returning overload
  (`HasSufficientBalanceBool`) for use inside PL/SQL blocks, since Oracle SQL
  cannot directly evaluate a `BOOLEAN` return type in a `SELECT` list.
