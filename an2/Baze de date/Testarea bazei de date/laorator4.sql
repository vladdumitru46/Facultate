use Club_de_fotbal_4
go


if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestRunTables_Tables]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [TestRunTables] DROP CONSTRAINT FK_TestRunTables_Tables
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestTables_Tables]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [TestTables] DROP CONSTRAINT FK_TestTables_Tables
GO
 
if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestRunTables_TestRuns]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [TestRunTables] DROP CONSTRAINT FK_TestRunTables_TestRuns
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestRunViews_TestRuns]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [TestRunViews] DROP CONSTRAINT FK_TestRunViews_TestRuns
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestTables_Tests]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [TestTables] DROP CONSTRAINT FK_TestTables_Tests
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestViews_Tests]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [TestViews] DROP CONSTRAINT FK_TestViews_Tests
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestRunViews_Views]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [TestRunViews] DROP CONSTRAINT FK_TestRunViews_Views
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestViews_Views]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [TestViews] DROP CONSTRAINT FK_TestViews_Views
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[Tables]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [Tables]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[TestRunTables]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [TestRunTables]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[TestRunViews]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [TestRunViews]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[TestRuns]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [TestRuns]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[TestTables]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [TestTables]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[TestViews]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [TestViews]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[Tests]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [Tests]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[Views]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [Views]
GO

CREATE TABLE [Tables] (
	[TableID] [int] IDENTITY (1, 1) NOT NULL ,
	[Name] [nvarchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [TestRunTables] (
	[TestRunID] [int] NOT NULL ,
	[TableID] [int] NOT NULL ,
	[StartAt] [datetime] NOT NULL ,
	[EndAt] [datetime] NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [TestRunViews] (
	[TestRunID] [int] NOT NULL ,
	[ViewID] [int] NOT NULL ,
	[StartAt] [datetime] NOT NULL ,
	[EndAt] [datetime] NOT NULL 
) ON [PRIMARY]
GO

drop table TestRuns
drop table TestRunTables
drop table TestRunViews

CREATE TABLE [TestRuns] (
	[TestRunID] [int] IDENTITY (1, 1) NOT NULL ,
	[Description] [nvarchar] (2000) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[StartAt] [datetime] NULL ,
	[EndAt] [datetime] NULL 
) ON [PRIMARY]
GO

CREATE TABLE [TestTables] (
	[TestID] [int] NOT NULL ,
	[TableID] [int] NOT NULL ,
	[NoOfRows] [int] NOT NULL ,
	[Position] [int] NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [TestViews] (
	[TestID] [int] NOT NULL ,
	[ViewID] [int] NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [Tests] (
	[TestID] [int] IDENTITY (1, 1) NOT NULL ,
	[Name] [nvarchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [Views] (
	[ViewID] [int] IDENTITY (1, 1) NOT NULL ,
	[Name] [nvarchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL 
) ON [PRIMARY]
GO

ALTER TABLE [Tables] WITH NOCHECK ADD 
	CONSTRAINT [PK_Tables] PRIMARY KEY  CLUSTERED 
	(
		[TableID]
	)  ON [PRIMARY] 
GO

ALTER TABLE [TestRunTables] WITH NOCHECK ADD 
	CONSTRAINT [PK_TestRunTables] PRIMARY KEY  CLUSTERED 
	(
		[TestRunID],
		[TableID]
	)  ON [PRIMARY] 
GO

ALTER TABLE [TestRunViews] WITH NOCHECK ADD 
	CONSTRAINT [PK_TestRunViews] PRIMARY KEY  CLUSTERED 
	(
		[TestRunID],
		[ViewID]
	)  ON [PRIMARY] 
GO

ALTER TABLE [TestRuns] WITH NOCHECK ADD 
	CONSTRAINT [PK_TestRuns] PRIMARY KEY  CLUSTERED 
	(
		[TestRunID]
	)  ON [PRIMARY] 
GO

ALTER TABLE [TestTables] WITH NOCHECK ADD 
	CONSTRAINT [PK_TestTables] PRIMARY KEY  CLUSTERED 
	(
		[TestID],
		[TableID]
	)  ON [PRIMARY] 
GO

ALTER TABLE [TestViews] WITH NOCHECK ADD 
	CONSTRAINT [PK_TestViews] PRIMARY KEY  CLUSTERED 
	(
		[TestID],
		[ViewID]
	)  ON [PRIMARY] 
GO

ALTER TABLE [Tests] WITH NOCHECK ADD 
	CONSTRAINT [PK_Tests] PRIMARY KEY  CLUSTERED 
	(
		[TestID]
	)  ON [PRIMARY] 
GO

ALTER TABLE [Views] WITH NOCHECK ADD 
	CONSTRAINT [PK_Views] PRIMARY KEY  CLUSTERED 
	(
		[ViewID]
	)  ON [PRIMARY] 
GO

ALTER TABLE [TestRunTables] ADD 
	CONSTRAINT [FK_TestRunTables_Tables] FOREIGN KEY 
	(
		[TableID]
	) REFERENCES [Tables] (
		[TableID]
	) ON DELETE CASCADE  ON UPDATE CASCADE ,
	CONSTRAINT [FK_TestRunTables_TestRuns] FOREIGN KEY 
	(
		[TestRunID]
	) REFERENCES [TestRuns] (
		[TestRunID]
	) ON DELETE CASCADE  ON UPDATE CASCADE 
GO

ALTER TABLE [TestRunViews] ADD 
	CONSTRAINT [FK_TestRunViews_TestRuns] FOREIGN KEY 
	(
		[TestRunID]
	) REFERENCES [TestRuns] (
		[TestRunID]
	) ON DELETE CASCADE  ON UPDATE CASCADE ,
	CONSTRAINT [FK_TestRunViews_Views] FOREIGN KEY 
	(
		[ViewID]
	) REFERENCES [Views] (
		[ViewID]
	) ON DELETE CASCADE  ON UPDATE CASCADE 
GO

ALTER TABLE [TestTables] ADD 
	CONSTRAINT [FK_TestTables_Tables] FOREIGN KEY 
	(
		[TableID]
	) REFERENCES [Tables] (
		[TableID]
	) ON DELETE CASCADE  ON UPDATE CASCADE ,
	CONSTRAINT [FK_TestTables_Tests] FOREIGN KEY 
	(
		[TestID]
	) REFERENCES [Tests] (
		[TestID]
	) ON DELETE CASCADE  ON UPDATE CASCADE 
GO

ALTER TABLE [TestViews] ADD 
	CONSTRAINT [FK_TestViews_Tests] FOREIGN KEY 
	(
		[TestID]
	) REFERENCES [Tests] (
		[TestID]
	),
	CONSTRAINT [FK_TestViews_Views] FOREIGN KEY 
	(
		[ViewID]
	) REFERENCES [Views] (
		[ViewID]
	)
GO


create View View_1 
as
	select * from echipa
go

create View View_2
as
	select e.nume_echipa, j.nume_jucator
	from echipa e INNER JOIN Jucatori j on e.nume_echipa = j.nume_echipa
go

drop View View_2
create View View_3
as
	select e.nume_echipa, c.id_om
	from conducere c INNER JOIN echipa e on c.nume_echipa = e.nume_echipa
	group by e.nume_echipa,c.id_om
go


insert into Tables(Name) values ('echipa'), ('Jucatori'), ('conducere')

select * from Tables

insert into Views(Name) values ('View_1'),('View_2'),('View_3')

select * from Views


insert into Tests(Name) values ('delete_table'), ('insert_table'), ('select_view')
select * from Tests

insert into TestViews(TestID, ViewID) values (3, 1), (3, 2), (3, 3)
select * from TestViews

insert into TestTables(TestID, TableID, NoOfRows, Position) values  (1, 1, 1000, 2), (1, 2, 1000, 1), (1, 3, 1000, 3),
																	(2, 1, 1000, 4), (2, 2, 1000, 3), (2, 3, 1000, 6)




create or alter procedure insert_echipa
as
begin
	declare @NoOfRows int 
	declare @n int
	declare @t varchar(50)

	select top 1 @NoOfRows = NoOfRows from dbo.TestTables where TestID = 2 and TableID = 1
	set @n =1 
	declare @nr int
	set @nr = (select COUNT(*) FROM echipa)
	while @n<@NoOfRows
	begin
		set @t = 'Echipa'+convert (varchar(5), @n+@nr)
		declare @an int
		set @an = 1900+@n
		insert into echipa(nume_echipa, an_inafintare) values (@t, @an)
		set @n=@n+1
	end
end

drop procedure insert_jucatori
select * from echipa
create or alter procedure insert_jucatori
as
begin
	declare @NoOfRows int 
	declare @n int
	declare @t varchar(50)
	declare @fk varchar(50)
	declare @fk1 varchar(50)

	select @fk=nume_echipa from echipa where @fk='FC Barcelona'

	select top 1 @fk1 = nume_jucator from Jucatori

	select top 1 @NoOfRows = NoOfRows from dbo.TestTables where TestID = 2 and TableID = 2
	set @n =1
	declare @nr int
	set @nr = (select COUNT(*) FROM Jucatori)
	while @n<@NoOfRows
	begin 
		set @t = 'Jucator' + convert (varchar(5),@n+@nr)
		declare @varsta int
		set @varsta = 15+@n
		insert into Jucatori(nume_echipa, nume_jucator, varsta) values ('FC Barcelona', @t, @varsta)
		set @n=@n+1
	end
end
select * from Jucatori

create or alter procedure insert_conducere
as
begin
		declare @NoOfRows int 
		declare @n int
		declare @t varchar(50)
		declare @fk varchar(50)
		declare @fk1 varchar(50)
		declare @id int
		delete from conducere where id_om>0
		select @fk=nume_echipa from echipa where @fk='FC Barcelona'

		select top 1 @NoOfRows = NoOfRows from dbo.TestTables where TestID = 2 and TableID = 2
		declare @nr int
		set @nr = (select COUNT(*) as nr FROM conducere)
		set @n =1
		while @n<@NoOfRows
		begin 
			set @t = 'Sef' +CONVERT(varchar(5), @n+@nr)
			insert into conducere(id_om, nume_sef, nume_echipa) values (@n+@nr, @t, 'FC Barcelona')
			set @n=@n+1
		end
end

select * from conducere


create procedure delete_echipa
as
begin
	declare @NoOfRows int
	select top 1 @NoOfRows=NoOfRows from TestTables where TestID=1 and TableID=1
	delete top(@NoOfRows)
	from echipa
	where nume_echipa LIKE 'Echipa%'
end

create procedure delete_jucator
as
begin
	declare @NoOfRows int
	select top 1 @NoOfRows=NoOfRows from TestTables where TestID=1 and TableID=2
	delete top(@NoOfRows)
	from Jucatori
	where nume_jucator LIKE 'Jucator%'
end

create procedure delete_conducere
as
begin
	declare @NoOfRows int
	select top 1 @NoOfRows=NoOfRows from TestTables where TestID=1 and TableID=1
	delete top(@NoOfRows)
	from conducere
	where nume_sef LIKE 'Sef%'
end


create or alter procedure test_1
as
begin
	exec insert_echipa
	DECLARE @ds DATETIME
	declare @di Datetime
	declare @df datetime
	set @ds = getdate()
	exec delete_echipa
	exec insert_echipa
	set @di = getdate()
	select * from View_1
	set @df= getdate()

	insert into TestRuns(Description, StartAt, EndAt) values ('testez echipe(sterg, adaug, le afisez)', @ds, @df)
	declare @idmax int
	select @idmax = MAX(TestRunID) from TestRuns
	insert into TestRunTables(TestRunID, TableID, StartAt, EndAt) values (@idmax, 1, @ds, @di)
	insert into TestRunViews(TestRunID, ViewID, StartAt, EndAt) values (@idmax, 1, @di, @df)
end

create or alter procedure test_2
as
begin
	exec insert_jucatori
	DECLARE @ds DATETIME
	declare @di Datetime
	declare @df datetime
	set @ds = getdate()
	exec delete_jucator
	exec insert_jucatori
	set @di = getdate()
	select * from View_2
	set @df= getdate()

	insert into TestRuns(Description, StartAt, EndAt) values ('testez jucatori(sterg, adaug, le afisez)', @ds, @df)
	declare @idmax int
	select @idmax = MAX(TestRunID) from TestRuns
	insert into TestRunTables(TestRunID, TableID, StartAt, EndAt) values (@idmax, 2, @ds, @di)
	insert into TestRunViews(TestRunID, ViewID, StartAt, EndAt) values (@idmax, 2, @di, @df)
end
create or alter procedure test_3
as
begin
	exec insert_conducere
	DECLARE @ds DATETIME
	declare @di Datetime
	declare @df datetime
	set @ds = getdate()
	exec delete_conducere
	exec insert_conducere
	set @di = getdate()
	select * from View_3
	set @df= getdate()

	insert into TestRuns(Description, StartAt, EndAt) values ('testez conducerea(sterg, adaug, le afisez)', @ds, @df)
	declare @idmax int
	select @idmax = MAX(TestRunID) from TestRuns
	insert into TestRunTables(TestRunID, TableID, StartAt, EndAt) values (@idmax, 3, @ds, @di)
	insert into TestRunViews(TestRunID, ViewID, StartAt, EndAt) values (@idmax, 3, @di, @df)
end
exec test_1
exec test_2
exec test_3
delete from conducere where nume_sef like 'Sef%'
select * from TestRuns
select * from TestRunTables
select * from TestRunViews




delete from echipa where nume_echipa like 'Echipa%'
delete from Jucatori where nume_jucator like 'Jucator%'
delete from conducere where nume_sef like 'Sef%'
