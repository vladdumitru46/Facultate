use Club_de_fotbal_4
go

create or alter procedure Insert_Echipa
@nume_echipa varchar(50),
@an_infiintare int,
@noOfRows int
as begin
	declare @n int = 1
	while @n<=@noOfRows
	begin
		set @nume_echipa = @nume_echipa + CONVERT(varchar(5), @n)
		insert into echipa(nume_echipa, an_inafintare) values (@nume_echipa, @an_infiintare)
		set @n = @n+1 
	end
end


create or alter procedure Read_Echipa
as
begin
	select * from echipa
end

create or alter procedure Update_Echipa
@nume_echipa varchar(50),
@an_infiintare int
as begin
	update echipa set an_inafintare=@an_infiintare where nume_echipa=@nume_echipa
end

create or alter procedure Delete_Echipa
@nume_echipa varchar(50)
as begin
	delete from echipa where nume_echipa=@nume_echipa
end

create or alter procedure MainEchipa
@nume_echipa varchar(50),
@an_infintare int,
@noOfRows int
as begin
	exec Insert_Echipa @nume_echipa, @an_infintare, @noOfRows
	exec Read_Echipa
	exec Update_Echipa @nume_echipa, @an_infintare
	exec Delete_Echipa @nume_echipa
end

create or alter procedure Insert_Sponsori
@nume_sponsor varchar(50),
@bagabani int,
@noOfRows int
as begin
	declare @n int = 1
	while @n<=@noOfRows
	begin
		set @nume_sponsor = @nume_sponsor + CONVERT(varchar(5), @n)
		insert into sponsori(nume_sponsor, baga_bani) values (@nume_sponsor, @bagabani)
		set @n = @n+1 
	end
end


create or alter procedure Read_Sponsor
as
begin
	select * from sponsori
end


create or alter procedure Update_Sponsor
@nume_sponsor varchar(50),
@bagabani int
as begin
	update sponsori set baga_bani=@bagabani where nume_sponsor=@nume_sponsor
end

create or alter procedure Delete_Sponsor
@nume_sponsor varchar(50)
as begin
	delete from sponsori where nume_sponsor=@nume_sponsor
end


create or alter procedure MainSponsor
@nume_sponsor varchar(50),
@bagabani int,
@noOfRows int
as begin
	exec Insert_Sponsori @nume_sponsor, @bagabani, @noOfRows
	exec Read_Sponsor
	exec Update_Sponsor @nume_sponsor, @bagabani
	exec Delete_Sponsor @nume_sponsor
end

create or alter procedure Insert_Sponsori_ai_echipei
@nume_echipa varchar(50),
@nume_sponsor varchar(50),
@noOfRows int
as begin
	declare @n int = 1
	while @n<=@noOfRows
	begin
		insert into sponsori_ai_echipei(nume_echipa, nume_sponsor) values (@nume_echipa, @nume_sponsor)
		set @n = @n+1 
	end
end


create or alter procedure Read_Sponsori_ai_Echipei
as
begin
	select * from sponsori_ai_echipei
end

create or alter procedure Update_Sponsori_ai_Echipei
@nume_echipa varchar(50),
@nume_sponsor varchar(50)
as begin
	update sponsori_ai_echipei set nume_sponsor=@nume_sponsor where nume_echipa=@nume_echipa
end

create or alter procedure Delete_Sponsori_ai_Echipei
@nume_echipa varchar(50),
@nume_sponsor varchar(50)
as begin
	delete from sponsori_ai_echipei where nume_echipa=@nume_echipa and nume_sponsor=@nume_sponsor
end


create or alter procedure MainSponsoriAiEchipei
@nume_echipa varchar(50),
@nume_sponsor varchar(50),
@noOfRows int
as begin
	exec Insert_Sponsori_ai_echipei @nume_echipa, @nume_sponsor, @noOfRows
	exec Read_Sponsori_ai_Echipei
	exec Update_Sponsori_ai_Echipei @nume_echipa, @nume_sponsor
	exec Delete_Sponsori_ai_Echipei @nume_echipa, @nume_sponsor
end


create or alter procedure Jucatori_Insert
@nume_jucator varchar(50),
@varsta int,
@nume_echipa varchar(50),
@noOfRows int
as begin
	declare @n int = 1
	while @n<=@noOfRows
	begin
		set @nume_jucator = @nume_jucator + CONVERT(varchar(5), @n)
		insert into Jucatori(nume_jucator, varsta, nume_echipa) values (@nume_jucator, @varsta, @nume_echipa)
		set @n = @n+1 
	end
end



create or alter procedure Read_Jucatori
as begin
		select * from Jucatori
end

create or alter procedure Update_Jucatori
@nume_jucator varchar(50),
@varsta int
as begin
	update Jucatori set varsta=@varsta where nume_jucator=@nume_jucator
end

create or alter procedure Delete_Jucatori
@nume_jucator varchar(50)
as begin
	delete from Jucatori where nume_jucator=@nume_jucator
end

create or alter procedure Main_Jucatori
@nume_jucator varchar(50),
@varsta int,
@nume_echipa varchar(50),
@noOfRows int
as begin
	exec Jucatori_Insert @nume_jucator, @varsta, @nume_echipa, @noOfRows
	exec Read_Jucatori
	exec Update_Jucatori @nume_jucator, @varsta
	exec Delete_Jucatori @nume_jucator
end

create or alter procedure CRUD_Stadion
@nume_stadion varchar(50),
@capacitate int,
@an_constructie int,
@pret int,
@nume_echipa varchar(50),
@noOfRows int
as begin
	declare @n int = 1
	while @n<=@noOfRows
	begin
		set @nume_stadion = @nume_stadion + CONVERT(varchar(5), @n)
		insert into stadion(nume_stadion, capacitate, an_constructie, pret_bilet, nume_echipa) values (@nume_stadion, @an_constructie, @capacitate, @pret, @nume_echipa)
		set @n = @n+1 
	end
	select * from stadion
	update stadion set capacitate = 70000 where nume_stadion=@nume_stadion
	delete from stadion where capacitate<50000
end

create or alter function dbo.TestAnEchipa(@an_infintare int)
returns int
as
	begin
		if @an_infintare > 1850
			return @an_infintare
		else set @an_infintare=1
		return @an_infintare
end	

create or alter procedure doEchipa
@nume_echipa varchar(50),
@an_infintare int,
@noOfRows int
as begin
if dbo.TestAnEchipa(@an_infintare)!=1
	exec MainEchipa @nume_echipa , @an_infintare, @noOfRows 
else
	print 'error'
end


create or alter function dbo.TestBagaBaniSponsor(@bagabani int)
returns int
as
	begin
		if @bagabani > 100000
			return @bagabani
		else set @bagabani=1
		return @bagabani
end	

create or alter procedure doSponsori
@nume_sponsor varchar(50),
@bagabani int,
@noOfRows int
as begin
	if dbo.TestBagaBaniSponsor(@bagabani)!=1
		exec MainSponsor @nume_sponsor, @bagabani, @noOfRows
	else
		print 'error'
end


create or alter function dbo.TestNumeEchipaSiSponsor(@nume_echipa varchar(50), @nume_sponosr varchar(50))
returns varchar
as
	begin
		if @nume_echipa like 'Echipa%' and @nume_sponosr like 'Sponsor%'
			return @nume_echipa + @nume_sponosr
		else set @nume_echipa='NU'
		return @nume_echipa
end	

create or alter procedure doSponsoriAiEchipei
@nume_echipa varchar(50),
@nume_sponosr varchar(50),
@noOfRows int
as begin
	if dbo.TestNumeEchipaSiSponsor(@nume_echipa, @nume_sponosr)!='NU'
		exec MainSponsoriAiEchipei @nume_echipa, @nume_sponosr, @noOfRows
	else
		print 'error'
end


---view

create or alter view View_1
as 
	select e.nume_echipa, s.nume_sponsor, s.baga_bani 
	from echipa e, sponsori s
go


create or alter view View_2
as
	select j.nume_jucator, s.nume_stadion, j.varsta, s.capacitate
	from Jucatori j INNER JOIN stadion s on j.nume_echipa=s.nume_echipa
go

select * from echipa order by nume_echipa
select * from sponsori order by nume_sponsor
select * from Jucatori order by nume_jucator
select * from stadion order by nume_stadion

SELECT OBJECT_NAME (A.[OBJECT_ID]) AS [OBJECT NAME] ,
	I.[NAME] AS [INDEX NAME],
	A.LEAF_INSERT_COUNT,
	A.LEAF_UPDATE_COUNT,
	A.LEAF_DELETE_COUNT 
	FROM SYS.DM_DB_INDEX_OPERATIONAL_STATS(NULL,NULL,NULL,NULL) A INNER JOIN SYS.INDEXES AS I 
	ON I.[OBJECT_ID] =A.[OBJECT_ID] AND I.INDEX_ID = A.INDEX_ID 
	WHERE OBJECTPROPERTY(A.[OBJECT_ID],'IsUserTable')=1


exec doEchipa 'EchipaRaa', 1902, 20
exec doSponsori 'sposnori', 40000000, 30
exec doSponsoriAiEchipei 'Echipa1000','Sposnorii123',30
exec Main_Jucatori 'jucator' , 30, 'Echipa10', 20
exec CRUD_Stadion 'stadion', 60000, 1990, 30, 'Echipa10', 20

select * from View_2
