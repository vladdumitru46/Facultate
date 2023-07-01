use Club_de_fotbal
go


create procedure do_procedure_1
AS
Begin
	ALTER table competitii 
	alter column bani_primiti_de_catre_castigator float NOT NULL 
	print 'a fost modificata coloana bani_primti_de_catre_castigator, din int s-a facut float!\n'
 end

 create procedure do_procedure_2
 as
 begin
	Alter table mingi 
	add constraint df_dim_minge default 5 for dimensiune_minge 
	print'a fost adaugata constrangerea default la dimensiune minge!\n'
end

create procedure do_procedure_3
as
begin
	Create Table Adversare( 
		nume_adversara VARCHAR(100) Primary Key,
		an_infintare int NOT NULL);
		print'a fost adaugat tabelul nou Adevsare\n'
end

create procedure do_procedure_4
as
begin
	ALter Table mingi 
	Add an_folosire int 
	print' a fost adaugata coloana noua an_folosire in tabelul minge!\n'
end

create procedure do_procedure_5
as
begin
	Alter Table fani 
	add Constraint nume_echipa Foreign Key(nume_echipa) References echipa(nume_echipa) 
	print' a fost adugata constrangerea foreign key in tabelul Fani\m'
end

create procedure undo_procedure_1
AS 
Begin
	ALTER table competitii 
	alter column bani_primiti_de_catre_castigator int NOT NULL 
	print 'bani primiti de catre castigator s-a intors la int\n'
end

create procedure undo_procedure_2
as
begin
	Alter table mingi 
	drop CONSTRAINT df_dim_minge; 
	print 'constrngerea default din tabelul mingi a fost stresa!\n'
end

create procedure undo_procedure_3
as
begin
	drop table Adversare 
	print'tabelul adversare a fost sters\n'
end

create procedure undo_procedure_4
as
begin
	alter table mingi 
	drop column an_folosire 
	print 'colana noua an_folosire din tabelul mingi a fost stearsa\n'
end

create procedure undo_procedure_5
as
begin
	alter table Fani 
	Drop Constraint nume_echipa 
	print 'constrangerea de foreign key din tabelul fani a fost staresa!\n'
end


create table VersionProcedure(versionNo int)

insert into VersionProcedure(versionNo) values(1)

create procedure main
@new_version int
as 
begin 
	Declare @curent_version int
	set @curent_version = (select versionNo from VersionProcedure)
	print @curent_version
	if @new_version > 5
	begin
		print 'versiunea este prea mare'
		return 1;
	end
	if @curent_version<=@new_version
	begin
		while @curent_version<=@new_version
		begin
			if @curent_version=1
			begin
				exec do_procedure_1
			end
			if @curent_version=2
			begin
				exec do_procedure_2
			end
			if @curent_version=3
			begin
				exec do_procedure_3
			end
			if @curent_version=4
			begin
				exec do_procedure_4
			end
			if @curent_version=5
			begin
				exec do_procedure_5
			end
			set @curent_version = @curent_version+1
		end
		update VersionProcedure set versionNO = @curent_version
		return 1
	end
	if @curent_version>=@new_version
	begin
		while @curent_version>=@new_version
		begin
			if @curent_version=1
			begin
				exec undo_procedure_1
			end
			if @curent_version=2
			begin
				exec undo_procedure_2
			end
			if @curent_version=3
			begin
				exec undo_procedure_3
			end
			if @curent_version=4
			begin
				exec undo_procedure_4
			end
			if @curent_version=5
			begin
				exec undo_procedure_5
			end
			set @curent_version = @curent_version-1
		end
		update VersionProcedure set versionNo = @curent_version
		return 1
	end
end

exec main 20