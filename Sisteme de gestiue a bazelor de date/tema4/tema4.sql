use Club_de_fotbal
go


--dirty reads
select * from echipament
BEGIN TRANSACTION 
UPDATE echipament SET
culoare_echimanet = 'rosu albastru' WHERE id_echipament=1
WAITFOR DELAY '00:00:10'
ROLLBACK TRANSACTION



--non repeatable reads
insert into echipament values('rosu', 4, 'acasa', 'Araujo')
begin tran
WAITFOR DELAY '00:00:10'
UPDATE echipament set culoare_echimanet='alb' where id_echipament=4
commit tran


select * from echipament
delete from echipament where id_echipament=4
select * from echipament


--phantom reads
begin tran
waitfor delay '00:00:10'
insert into echipament values('mov', 5,'deplasare', 'Gavi')
commit tran

select * from echipament
delete from echipament where id_echipament=5
select * from echipament


insert into echipament values('mov', 6,'deplasare', 'Pedri')
select * from echipament
insert into sponsori values('dfiuagsd', 1999)
select * from sponsori
--deadlock
begin tran
update echipament set culoare_echimanet='portocaliu' where id_echipament=6
waitfor delay '00:00:10'
update sponsori set baga_bani=3000 where nume_sponsor='dfiuagsd'
commit tran
select * from echipament
select * from sponsori


delete from echipament where id_echipament = 6
delete from sponsori where nume_sponsor='dfiuagsd'
