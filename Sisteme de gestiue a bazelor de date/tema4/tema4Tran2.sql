use Club_de_fotbal

CREATE TABLE LogTableReads(
Lid INT IDENTITY PRIMARY KEY,
TypeOperation VARCHAR(50),
TableOperation VARCHAR(50),
ExecutionDate DATETIME)

--dirty reads
--problem
SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED
BEGIN TRAN
SELECT * FROM echipament
WAITFOR DELAY '00:00:15'
SELECT * FROM echipament
insert into LogTableReads(TypeOperation, TableOperation, ExecutionDate) values('Update', 'dirty reads problem',GETDATE())
COMMIT TRAN

select * from echipament

--solution
SET TRANSACTION ISOLATION LEVEL READ COMMITTED
BEGIN TRAN
SELECT * FROM echipament
WAITFOR DELAY '00:00:15'
SELECT * FROM echipament
insert into LogTableReads(TypeOperation, TableOperation, ExecutionDate) values('Update', 'dirty reads solution',GETDATE())
COMMIT TRAN


--non repeatabale reads
--problem
SET TRANSACTION ISOLATION LEVEL READ COMMITTED
BEGIN TRAN
SELECT * FROM echipament
WAITFOR DELAY '00:00:15'
SELECT * FROM echipament
insert into LogTableReads(TypeOperation, TableOperation, ExecutionDate) values('Update', 'non repeatabale reads problem',GETDATE())
COMMIT TRAN


--solution
SET TRANSACTION ISOLATION LEVEL REPEATABLE READ
BEGIN TRAN
SELECT * FROM echipament
WAITFOR DELAY '00:00:15'
SELECT * FROM echipament
insert into LogTableReads(TypeOperation, TableOperation, ExecutionDate) values('Update', 'non repeatabale reads solution',GETDATE())
COMMIT TRAN


select * from echipament


--phantom reads
--problem
SET TRANSACTION ISOLATION LEVEL REPEATABLE READ
begin tran
select * from echipament
waitfor delay '00:00:15'
select * from echipament
insert into LogTableReads(TypeOperation, TableOperation, ExecutionDate) values('Insert', 'phantom reads problem',GETDATE())
commit tran

--solution
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE
begin tran
select * from echipament
waitfor delay '00:00:15'
select * from echipament
insert into LogTableReads(TypeOperation, TableOperation, ExecutionDate) values('Insert', 'phantom reads solution',GETDATE())
commit tran

select * from echipament

--deadlock
--problem
begin tran
update sponsori set baga_bani=3900 where nume_sponsor='dfiuagsd'
waitfor delay '00:00:10'
update echipament set culoare_echimanet='albastru' where id_echipament=6
insert into LogTableReads(TypeOperation, TableOperation, ExecutionDate) values('Update', 'deadlock problem',GETDATE())
commit tran

--solution
set deadlock_priority high;
begin tran
update sponsori set baga_bani=3900 where nume_sponsor='dfiuagsd'
waitfor delay '00:00:10'
update echipament set culoare_echimanet='albastru' where id_echipament=6
insert into LogTableReads(TypeOperation, TableOperation, ExecutionDate) values('Update', 'deadlock solution',GETDATE())
commit tran

select * from LogTableReads