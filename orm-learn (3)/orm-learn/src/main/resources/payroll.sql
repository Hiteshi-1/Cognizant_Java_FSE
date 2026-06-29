-- ============================================================
--  payroll.sql
--  Creates employee, department, skill and employee_skill tables
--  with sample data for Hands-on 3-6 (Day 2) and Day 3.
--  mysql> source /path/to/payroll.sql
-- ============================================================

USE ormlearn;

-- ── Department ────────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS department (
    dp_id   INT          NOT NULL AUTO_INCREMENT,
    dp_name VARCHAR(100) NOT NULL,
    PRIMARY KEY (dp_id)
);

-- ── Skill ─────────────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS skill (
    sk_id   INT          NOT NULL AUTO_INCREMENT,
    sk_name VARCHAR(100) NOT NULL,
    PRIMARY KEY (sk_id)
);

-- ── Employee ──────────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS employee (
    em_id            INT            NOT NULL AUTO_INCREMENT,
    em_name          VARCHAR(100)   NOT NULL,
    em_salary        DECIMAL(12, 2) NOT NULL,
    em_permanent     TINYINT(1)     NOT NULL DEFAULT 0,
    em_date_of_birth DATE,
    em_dp_id         INT,
    PRIMARY KEY (em_id),
    FOREIGN KEY (em_dp_id) REFERENCES department(dp_id)
);

-- ── Employee-Skill join table (ManyToMany) ────────────────────
CREATE TABLE IF NOT EXISTS employee_skill (
    es_em_id INT NOT NULL,
    es_sk_id INT NOT NULL,
    PRIMARY KEY (es_em_id, es_sk_id),
    FOREIGN KEY (es_em_id) REFERENCES employee(em_id),
    FOREIGN KEY (es_sk_id) REFERENCES skill(sk_id)
);

-- ── Sample data ───────────────────────────────────────────────
INSERT IGNORE INTO department (dp_id, dp_name) VALUES (1, 'Engineering');
INSERT IGNORE INTO department (dp_id, dp_name) VALUES (2, 'HR');
INSERT IGNORE INTO department (dp_id, dp_name) VALUES (3, 'Finance');

INSERT IGNORE INTO skill (sk_id, sk_name) VALUES (1, 'Java');
INSERT IGNORE INTO skill (sk_id, sk_name) VALUES (2, 'Spring Boot');
INSERT IGNORE INTO skill (sk_id, sk_name) VALUES (3, 'MySQL');
INSERT IGNORE INTO skill (sk_id, sk_name) VALUES (4, 'Angular');
INSERT IGNORE INTO skill (sk_id, sk_name) VALUES (5, 'Python');

INSERT IGNORE INTO employee (em_id, em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id)
VALUES (1, 'Alice Johnson',  75000.00, 1, '1992-03-15', 1);
INSERT IGNORE INTO employee (em_id, em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id)
VALUES (2, 'Bob Smith',      62000.00, 1, '1988-07-22', 1);
INSERT IGNORE INTO employee (em_id, em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id)
VALUES (3, 'Carol White',    55000.00, 0, '1995-11-05', 2);
INSERT IGNORE INTO employee (em_id, em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id)
VALUES (4, 'David Brown',    80000.00, 1, '1985-01-30', 3);
INSERT IGNORE INTO employee (em_id, em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id)
VALUES (5, 'Eva Martinez',   47000.00, 0, '1997-09-18', 2);

INSERT IGNORE INTO employee_skill (es_em_id, es_sk_id) VALUES (1, 1);
INSERT IGNORE INTO employee_skill (es_em_id, es_sk_id) VALUES (1, 2);
INSERT IGNORE INTO employee_skill (es_em_id, es_sk_id) VALUES (2, 1);
INSERT IGNORE INTO employee_skill (es_em_id, es_sk_id) VALUES (2, 3);
INSERT IGNORE INTO employee_skill (es_em_id, es_sk_id) VALUES (3, 4);
INSERT IGNORE INTO employee_skill (es_em_id, es_sk_id) VALUES (4, 2);
INSERT IGNORE INTO employee_skill (es_em_id, es_sk_id) VALUES (4, 5);
INSERT IGNORE INTO employee_skill (es_em_id, es_sk_id) VALUES (5, 4);
