-- ============================================================
--  Bank Management System – Schema DDL
-- ============================================================

CREATE TABLE Customers (
    CustomerID   NUMBER PRIMARY KEY,
    Name         VARCHAR2(100),
    DOB          DATE,
    Balance      NUMBER,
    LastModified DATE,
    IsVIP        VARCHAR2(1) DEFAULT 'N'   -- Y/N flag, used in Exercise 1 Scenario 2
);

CREATE TABLE Accounts (
    AccountID    NUMBER PRIMARY KEY,
    CustomerID   NUMBER,
    AccountType  VARCHAR2(20),
    Balance      NUMBER,
    LastModified DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Transactions (
    TransactionID    NUMBER PRIMARY KEY,
    AccountID        NUMBER,
    TransactionDate  DATE,
    Amount           NUMBER,
    TransactionType  VARCHAR2(10),
    FOREIGN KEY (AccountID) REFERENCES Accounts(AccountID)
);

CREATE TABLE Loans (
    LoanID       NUMBER PRIMARY KEY,
    CustomerID   NUMBER,
    LoanAmount   NUMBER,
    InterestRate NUMBER,
    StartDate    DATE,
    EndDate      DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Employees (
    EmployeeID NUMBER PRIMARY KEY,
    Name       VARCHAR2(100),
    Position   VARCHAR2(50),
    Salary     NUMBER,
    Department VARCHAR2(50),
    HireDate   DATE
);

-- ============================================================
--  Supporting table for Exercise 5 Scenario 2 (audit log trigger)
-- ============================================================
CREATE TABLE AuditLog (
    AuditID         NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    TransactionID   NUMBER,
    AccountID       NUMBER,
    Amount          NUMBER,
    TransactionType VARCHAR2(10),
    LoggedAt        DATE DEFAULT SYSDATE
);

-- ============================================================
--  Supporting table for Exercise 2 (generic error logging)
-- ============================================================
CREATE TABLE ErrorLog (
    ErrorID     NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    ProcName    VARCHAR2(100),
    ErrorMsg    VARCHAR2(4000),
    LoggedAt    DATE DEFAULT SYSDATE
);
