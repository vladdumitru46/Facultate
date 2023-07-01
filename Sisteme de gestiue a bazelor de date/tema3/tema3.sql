use Club_de_fotbal
go

CREATE TABLE LogTable(
Lid INT IDENTITY PRIMARY KEY,
TypeOperation VARCHAR(50),
TableOperation VARCHAR(50),
ExecutionDate DATETIME)

create or alter function dbo.uf_validateNumeEchipa(@nume_echipa varchar(100))
RETURNS INT AS
Begin
declare @return INT
set @return = 1
if(@nume_echipa = '')
set @return=0
RETURN @return
end

create or alter function dbo.uf_validateAnInfintareEchipa(@an_infintare int)
RETURNS INT AS
Begin
declare @return INT
set @return = 1
if(@an_infintare < 1890)
set @return=0
RETURN @return
end

create or alter function dbo.uf_validateNumeSponsori(@nume_sponsor varchar(100))
RETURNS INT AS
Begin
declare @return int
set @return = 1
if(@nume_sponsor = '')
set @return=0
RETURN @return
end

create or alter function dbo.uf_validateBaniSponsori(@baga_bani int)
RETURNS INT AS
Begin
declare @return int
set @return = 1
if(@baga_bani < 1)
set @return=0
RETURN @return
end


create or alter procedure InsertInTables
@nume_echipa varchar(100),
@an_infintare int,
@nume_sponsor varchar(100),
@baga_bani int
as
begin
	begin TRAN
	begin TRY
		if(dbo.uf_validateNumeEchipa(@nume_echipa)=0)
		begin
			RAISERROR('Nume echipa must not be null',14,1)
		end
		if(dbo.uf_validateAnInfintareEchipa(@an_infintare)=0)
		begin
			RAISERROR('An_infintare must be >1890',15,1)
		end
		insert into echipe values(@nume_echipa, @an_infintare)
		if(dbo.uf_validateNumeSponsori(@nume_sponsor)=0)
		begin
			RAISERROR('Nume sponsor must not be null',16,1)
		end
		if(dbo.uf_validateBaniSponsori(@baga_bani)=0)
		begin
			RAISERROR('Banii_Sponsori must be > 1',17,1)
		end
		insert into Sponsori_fotbal values(@nume_sponsor, @baga_bani)

		declare @id_echipa int;
		declare @id_sponsor int

		SELECT @id_echipa = MAX(id_echipa) FROM echipe;
		SELECT @id_sponsor = MAX(sponsor_id) from Sponsori_fotbal
		insert into sponsori_ai_echipei_fotbal values(@id_echipa, @id_sponsor)
		COMMIT TRAN
		SELECT 'Transaction commited'
	END TRY
	BEGIN CATCH
	ROLLBACK TRAN
		select 'Transaction rollbacked'
		insert into LogTable(TypeOperation, TableOperation, ExecutionDate) values((select ERROR_MESSAGE()),'Insert',GETDATE())
		SELECT * FROM LogTable WHERE Lid=(SELECT MAX(Lid) FROM LogTable);
    
	end CATCH
end


exec InsertInTables 'Mancheter United',1899, 'KKK', 5000000
select * from echipe
select * from Sponsori_fotbal
select * from sponsori_ai_echipei_fotbal

exec InsertInTables 'FC Barcelona',1899, '', 700
select * from echipe
select * from Sponsori_fotbal
select * from sponsori_ai_echipei_fotbal



create or alter procedure InsertInTablesVar2
@nume_echipa varchar(100),
@an_infintare int,
@nume_sponsor varchar(100),
@baga_bani int
as
begin
	declare @ok int
	set @ok=1
	begin TRAN
	begin TRY
		if(dbo.uf_validateNumeEchipa(@nume_echipa)=0)
		begin
			RAISERROR('Nume echipa must not be null',14,1)
		end
		if(dbo.uf_validateAnInfintareEchipa(@an_infintare)=0)
		begin
			RAISERROR('An_infintare must be >1890',15,1)
		end
		insert into echipe values(@nume_echipa, @an_infintare)
				COMMIT TRAN
		SELECT 'Transaction commited'
	END TRY
	BEGIN CATCH
	ROLLBACK TRAN
		select 'Transaction rollbacked'
		set @ok=0
		insert into LogTable(TypeOperation, TableOperation, ExecutionDate) values((select ERROR_MESSAGE()),'Insert',GETDATE())
		SELECT * FROM LogTable WHERE Lid=(SELECT MAX(Lid) FROM LogTable);
	end CATCH
	begin TRAN
	begin TRY
		if(dbo.uf_validateNumeSponsori(@nume_sponsor)=0)
		begin
			RAISERROR('Nume sponsor must not be null',16,1)
		end
		if(dbo.uf_validateBaniSponsori(@baga_bani)=0)
		begin
			RAISERROR('Banii_Sponsori must be > 1',17,1)
		end
		insert into Sponsori_fotbal values(@nume_sponsor, @baga_bani)
		COMMIT TRAN
		SELECT 'Transaction commited'
	END TRY
	BEGIN CATCH
	ROLLBACK TRAN
	set @ok=0
		select 'Transaction rollbacked'
		insert into LogTable(TypeOperation, TableOperation, ExecutionDate) values((select ERROR_MESSAGE()),'Insert',GETDATE())
		SELECT * FROM LogTable WHERE Lid=(SELECT MAX(Lid) FROM LogTable);
    
	end CATCH
	begin tran
	begin try
		if(@ok=0)
		begin 
			RAISERROR('Cannot insert into Sponsori_ai_Echipei_fotbal because data is inavlid!',18,1)
		end
		declare @id_echipa int
		declare @id_sponsor int
		SELECT @id_echipa = MAX(id_echipa) FROM echipe;
		SELECT @id_sponsor = MAX(sponsor_id) from Sponsori_fotbal
		insert into sponsori_ai_echipei_fotbal values(@id_echipa, @id_sponsor)
		COMMIT TRAN
		SELECT 'Transaction commited'
	END TRY
	BEGIN CATCH
	ROLLBACK TRAN
		select 'Transaction rollbacked'
		insert into LogTable(TypeOperation, TableOperation, ExecutionDate) values((select ERROR_MESSAGE()),'Insert',GETDATE())
		SELECT * FROM LogTable WHERE Lid=(SELECT MAX(Lid) FROM LogTable);
	end CATCH
end

exec InsertInTablesVar2'Mancheter United',1899, 'KKK', 5000000
select * from echipe
select * from Sponsori_fotbal
select * from sponsori_ai_echipei_fotbal

exec InsertInTablesVar2 '',1800, 'fsgdf', 700
select * from echipe
select * from Sponsori_fotbal
select * from sponsori_ai_echipei_fotbal

exec InsertInTablesVar2 'Echipaaa',1899, '', -700
select * from echipe
select * from Sponsori_fotbal
select * from sponsori_ai_echipei_fotba11l