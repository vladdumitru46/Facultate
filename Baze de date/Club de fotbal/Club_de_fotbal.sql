create database Club_de_fotbal
go
use Club_de_fotbal
go

--tema 1
create table echipa(
		nume_echipa varchar(100) primary key,
		an_inafintare int)

create table Jucatori(
	nume_jucator varchar(100) primary key,
	varsta int,
	nume_echipa varchar(100) foreign key references echipa(nume_echipa))

create table Fani(
	numar_fani int,
	varsta_fani int,
	id_fan int primary key,
	nume_echipa varchar(100) foreign key references echipa(nume_echipa))


create table sponsori(
	nume_sponsor varchar(100) primary key,
	baga_bani int
	)

create table sponsori_ai_echipei(
	nume_echipa varchar(100) foreign key references echipa(nume_echipa),
	nume_sponsor varchar(100) foreign key references sponsori(nume_sponsor))

create table competitii(
	nume_competitie varchar(100) primary key,
	bani_primiti_de_catre_castigator int,
	nume_echipa varchar(100) foreign key references echipa(nume_echipa))

create table mingi(
	id_minge int primary key,
	culoare_minge varchar(100),
	dimnesiune_minge int,
	nume_competitie varchar(100) foreign key references competitii(nume_competitie))



create table echipament(
	culoare_echimanet varchar(100),
	id_echipament int primary key, 
	echipment_acasa_sau_deplasare varchar(100),
	nume_jucator varchar(100) foreign key references Jucatori(nume_jucator))


create table conducere(
	nume_sef varchar(100),
	numar_oameni_in_conducere int,
	varsta int,
	id_om int primary key,
	nume_echipa varchar(100) foreign key references echipa(nume_echipa))

create table stadion(
	nume_stadion varchar(100) primary key,
	capacitate int,
	an_constructie int,
	pret_bilet int,
	nume_echipa varchar(100) foreign key references echipa(nume_echipa)
	)



--tema 2
Insert into echipa(nume_echipa, an_inafintare) values ('FC Barcelona', 1899)
Insert into echipa(nume_echipa, an_inafintare) values ('Liverpool', 1899)


insert into Jucatori(nume_jucator, varsta, nume_echipa) values ('Lewandowski', 34, 'FC Barcelona')
insert into Jucatori(nume_jucator, varsta, nume_echipa) values ('Pedri', 19, 'FC Barcelona')
insert into Jucatori(nume_jucator, varsta, nume_echipa) values ('Gavi', 18, 'FC Barcelona')
insert into Jucatori(nume_jucator, varsta, nume_echipa) values ('De Jong', 25, 'FC Barcelona')
insert into Jucatori(nume_jucator, varsta, nume_echipa) values ('Araujo', 22, 'FC Barcelona')
insert into Jucatori(nume_jucator, varsta, nume_echipa) values ('Kounde', 23, 'FC Barcelona')

insert into Fani(id_fan,numar_fani, varsta_fani,nume_echipa) values(1,1323423423,33,'FC Barcelona') 
insert into Fani(id_fan,numar_fani, varsta_fani,nume_echipa) values(2,1323423423,77,'FC Barcelona') 
insert into Fani(id_fan,numar_fani, varsta_fani,nume_echipa) values(3,1323423423,23,'FC Barcelona') 
insert into Fani(id_fan,numar_fani, varsta_fani,nume_echipa) values(4,1323423423,55,'FC Barcelona') 
insert into Fani(id_fan,numar_fani, varsta_fani,nume_echipa) values(5,1323423423,19,'FC Barcelona') 
insert into Fani(id_fan,numar_fani, varsta_fani,nume_echipa) values(6,1323423423,14,'FC Barcelona') 

insert into sponsori(nume_sponsor, baga_bani) values ('Nike', 3300000)
insert into sponsori(nume_sponsor, baga_bani) values ('Adidas', 3300000)
insert into sponsori(nume_sponsor, baga_bani) values ('Puma', 3300000)
insert into sponsori(nume_sponsor, baga_bani) values ('Beko', 3300000)
insert into sponsori(nume_sponsor, baga_bani) values ('Sorare', 3300000)
insert into sponsori(nume_sponsor, baga_bani) values ('FIFA', 3300000)

insert into sponsori_ai_echipei(nume_echipa, nume_sponsor) values ('FC Barcelona', 'Nike')
insert into sponsori_ai_echipei(nume_echipa, nume_sponsor) values ('Liverpool', 'Nike')
insert into sponsori_ai_echipei(nume_echipa, nume_sponsor) values ('FC Barcelona', 'Beko')
insert into sponsori_ai_echipei(nume_echipa, nume_sponsor) values ('FC Barcelona', 'Sorare')
insert into sponsori_ai_echipei(nume_echipa, nume_sponsor) values ('FC Barcelona', 'FIFA')

insert into mingi(id_minge, culoare_minge, dimnesiune_minge, nume_competitie) values (1,'albastru',5,'Champions League')
insert into mingi(id_minge, culoare_minge, dimnesiune_minge, nume_competitie) values (2,'alb cu verde',5,'La liga')
insert into mingi(id_minge, culoare_minge, dimnesiune_minge, nume_competitie) values (1,'alb cu rosu',5,'Copa del Rey')

insert into competitii(nume_competitie, bani_primiti_de_catre_castigator, nume_echipa) values ('Chamions League', 120000000,'FC Barcelona')
insert into competitii(nume_competitie, bani_primiti_de_catre_castigator, nume_echipa) values ('La liga', 1200000,'FC Barcelona')
insert into competitii(nume_competitie, bani_primiti_de_catre_castigator, nume_echipa) values ('Copa del Rey', 10000,'FC Barcelona')

insert into echipament(id_echipament, culoare_echimanet, echipment_acasa_sau_deplasare, nume_jucator) values (1,'rosu si albastru', 'acasa', 'Lewandowski')
insert into echipament(id_echipament, culoare_echimanet, echipment_acasa_sau_deplasare, nume_jucator) values (2,'rosu si albastru', 'acasa', 'Pedri')
insert into echipament(id_echipament, culoare_echimanet, echipment_acasa_sau_deplasare, nume_jucator) values (3,'galben', 'deplasare', 'Araujo')

insert into conducere(nume_sef, numar_oameni_in_conducere,varsta, id_om, nume_echipa) values ('Laporta', 7, 64, 1, 'FC Barcelona')
insert into conducere(nume_sef, numar_oameni_in_conducere, varsta, id_om,nume_echipa) values ('Cruyff', 7, 33, 2,'FC Barcelona')
insert into conducere(nume_sef, numar_oameni_in_conducere, varsta, id_om,nume_echipa) values ('Alemany', 7, 44, 3, 'FC Barcelona')
insert into conducere(nume_sef, numar_oameni_in_conducere, varsta, id_om,nume_echipa) values ('Alem', 13, 44, 4, 'Liverpool')
insert into conducere(nume_sef, numar_oameni_in_conducere, varsta, id_om,nume_echipa) values ('Alemny', 13, 44, 5, 'Liverpool')

insert into stadion(nume_stadion,capacitate, an_constructie,pret_bilet, nume_echipa) values ('CampNou', 99000,1919,33, 'FC Barcelona')
insert into stadion(nume_stadion,capacitate, an_constructie,pret_bilet, nume_echipa) values ('Anfield', 55000,1900,23, 'Liverpool')


Select * from echipa
Select * from Jucatori
Select * from Fani
Select * from sponsori
Select * from sponsori_ai_echipei
Select * from mingi
Select * from competitii
Select * from conducere
Select * from stadion
Select * from echipament

/*
interogarea arata jucatorii care au vasrta mai mica de 25 de ani, competitia CHampions League si unde id_ul faniilor este mai mic decat 2
*/
Select distinct j.nume_jucator, c.nume_competitie, f.id_fan
from Jucatori j INNER JOIN competitii c on j.nume_echipa = c.nume_echipa INNER JOIN Fani f on f.nume_echipa=j.nume_echipa
where nume_competitie = 'Chamions League' and j.varsta<25 and f.id_fan<2


/*
interogarea aarata unde id ul echipamentului este egal cu id-ul mingii si banii primiti de catre castigator la o competitie sa fie mai mare de 12000
*/
select e.nume_echipa,m.id_minge, m.culoare_minge, c.nume_competitie
from echipa e INNER JOIN competitii c on e.nume_echipa=c.nume_echipa, mingi m
where m.nume_competitie=c.nume_competitie

/*
interogarea arata unde numele sponsoorului este nike si numele competitiei este LA liga
*/
select s.nume_echipa, s.nume_sponsor, c.nume_competitie, f.id_fan
from sponsori_ai_echipei s INNER JOIN competitii c on s.nume_echipa=c.nume_echipa INNER JOIN Fani f on c.nume_echipa=f.nume_echipa
where nume_sponsor='Nike' and c.nume_competitie='La liga' and f.id_fan<2

/*
selecteaza unde numele echipei din tabelul m-n este FC Barcelona, sponsorul Nik
*/
select se.nume_sponsor, e.nume_echipa, s.baga_bani
from echipa e inner join sponsori_ai_echipei se on e.nume_echipa=se.nume_echipa inner join sponsori s on s.nume_sponsor = se.nume_sponsor
where e.nume_echipa='FC Barcelona' and s.nume_sponsor='Nike'

/*
	selecteaza unde numele echipei este FC Barcelona si id-ul omului din conducere este < 3 si numele stadionului este CampNou
*/
select distinct e.nume_echipa, c.id_om, c.nume_sef, s.nume_stadion
from echipa e INNER JOIN conducere c on e.nume_echipa = c.nume_echipa INNER JOIN stadion s on c.nume_echipa=s.nume_echipa
where c.id_om<3 and s.nume_stadion='CampNou'

/*
	selecteaza unde numele echipei este LIverpool si numele competitiei este CHampions league si id minge este < 3
*/
select e.nume_echipa, c.nume_competitie, m.id_minge
from echipa e INNER JOIN competitii c on e.nume_echipa = c.nume_echipa ,mingi m 
where c.nume_competitie='Chamions League' and m.id_minge<3

/*
	selecteaza unde numele echipei este FC Barcelona
*/
select e.nume_echipa, j.nume_jucator, ec.id_echipament
from echipament ec inner join  Jucatori j on ec.nume_jucator=j.nume_jucator, echipa e
where e.nume_echipa='FC Barcelona'

/*
	selecteaza unde capacitatea stadionului esre > 60000
*/
select f.id_fan, f.nume_echipa,c.id_om, s.capacitate
from Fani f inner join conducere c on f.nume_echipa=c.nume_echipa INNER JOIN stadion s on f.nume_echipa=s.nume_echipa
where s.capacitate>60000

/*
	selecteaza unde id-ul omului din conducere este < 2
*/
select c.nume_competitie, con.id_om, con.nume_echipa, con.nume_sef
from competitii c inner join conducere con on c.nume_echipa=con.nume_echipa
where con.id_om<2

/*
	arata unde media pretului biletelor de la stadion este >23
*/
select nume_stadion, AVG(pret_bilet) as Pret_mediu
from stadion
group by nume_stadion
having AVG(pret_bilet)>23

/*
selecteaza jucatorii unde numele echipei este FC Barcelona
*/
select nume_jucator, AVG(varsta) as Varsta_medie, nume_echipa
from Jucatori
group by nume_jucator, nume_echipa
having AVG(varsta)>23


--tema 3
/*
	1. modificare tip coloana
	2. default
	3. creare tabel/stergere tabel
	4. adaug camp nou
	5. cheie straina

	un tabel nou
	Versiune = 0(initiala) 1 2 3 4 5
	veriunea 3 inseamna ca s-au executat primele 3 (modif, default si creare tabel)
	versiunea 4 inseamna ca s-au executat primele 4
	etc


	main 4 se duce la versiunea 4
	0<4 -> V1 V2, V3, V4
	main 2
	4>2 -> V44, V33
	main 3 se duce la versiunea 3

	main n>5 || n nu e int
	mesaj ca nu se poate si se termina

	Declare @n INT - declararea variabilei n
	SET @n = 12 - n = 12;
*/
