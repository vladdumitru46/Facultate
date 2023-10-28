#pragma once
#include "Repository.h"
#define NULL_TVALOARE -1
#include <sstream>
using std::endl;
using std::shuffle;
using std::stringstream;

const vector<Medicament>& Repository::get_toate_medicamentele_din_lista() noexcept {
	return this->lista_de_medicamente;
}

void Repository::adauga_medicament_in_lista(const Medicament& medicament_de_adaugat)
{
	if (cauta_element_dupa_pozitie_in_vector(medicament_de_adaugat.get_denumire()) != -1)
		throw Exceptie_de_adaugare("Medicamentul exista deja in lista\n");
	else {
		this->lista_de_medicamente.push_back(medicament_de_adaugat);
	}
}



int Repository::cauta_element_dupa_pozitie_in_vector(string denumire_de_cautat)
{
	int pozitia_pe_care_se_afla_medicamentul_in_lista = -1;
	for (int i = 0; i < lista_de_medicamente.size(); i++)
	{
		if (lista_de_medicamente[i].get_denumire() == denumire_de_cautat)
		{
			pozitia_pe_care_se_afla_medicamentul_in_lista = i;
			break;
		}
	}
	return pozitia_pe_care_se_afla_medicamentul_in_lista;
}




const Medicament& Repository::cauta_un_medicament_in_lista_de_medicamente(string denumire_de_cautat)
{
	vector<Medicament>::iterator iterator_medicament = std::find_if(this->lista_de_medicamente.begin(), this->lista_de_medicamente.end(), [denumire_de_cautat](const Medicament& medicament_cautat) {
		return medicament_cautat.get_denumire() == denumire_de_cautat;
		});
	if (iterator_medicament != this->lista_de_medicamente.end())
		return (*iterator_medicament);
	else throw Exceptie_de_cautare("Medicamentul nu exista in lista!\n");
}


void Repository::sterge_medicament_din_lista(string denumire_de_sters )
{	
	const int pozitia_pe_care_se_afla_medciamentul_in_lista = cauta_element_dupa_pozitie_in_vector(denumire_de_sters);
	if (pozitia_pe_care_se_afla_medciamentul_in_lista != -1)
		lista_de_medicamente.erase(lista_de_medicamente.begin() + pozitia_pe_care_se_afla_medciamentul_in_lista);
	else {
		throw Exceptie_de_stergere("Medicamentul nu exista!\n");
	}

}


void Repository::modidica_medicamentul_din_lista(string denumire_de_cautat, string producator_de_cautat, double pret_nou_modifcat, string substanta_activa_de_modificat)
{
	const int pozitia_pe_care_se_afla_medciamentul_in_lista = cauta_element_dupa_pozitie_in_vector(denumire_de_cautat);
	if (pozitia_pe_care_se_afla_medciamentul_in_lista != -1)
	{
		sterge_medicament_din_lista(denumire_de_cautat);
		Medicament medicament_nou_modificat{ denumire_de_cautat, pret_nou_modifcat, producator_de_cautat, substanta_activa_de_modificat };
		adauga_medicament_in_lista(medicament_nou_modificat);
	}
	else
	{
		throw Exceptie_de_modificare("Medicamentul nu exista!\n");
	}
}








const vector<Medicament>& RepoReteta::get_toate_medicamentele_din_lista() noexcept
{
	return this->lista_de_retete;
}

void RepoReteta::adauga_medicament_in_reteta(const string denumire_medicament_pe_care_vrei_sa_il_adaugi_in_reteta)
{
	const int verificare_daca_medicamentul_pe_care_vreau_sa_il_adaug_exista_in_lista_de_medicamente = lista_de_medicamente.cauta_element_dupa_pozitie_in_vector(denumire_medicament_pe_care_vrei_sa_il_adaugi_in_reteta);
	if (verificare_daca_medicamentul_pe_care_vreau_sa_il_adaug_exista_in_lista_de_medicamente != -1)
	{
		Medicament medicamentul_pe_care_vreau_sa_il_il_adaug_in_reteta = lista_de_medicamente.cauta_un_medicament_in_lista_de_medicamente(denumire_medicament_pe_care_vrei_sa_il_adaugi_in_reteta);
		lista_de_retete.push_back(medicamentul_pe_care_vreau_sa_il_il_adaug_in_reteta);
		notify_observer();
	}
	else
	{
		throw Exceptie_de_adaugare("Nu exista medicamenul pe care doresti sa il adaugi in reteta!\n");
	}
}

void RepoReteta::golseste_reteta_de_medicamente()
{
	if (lista_de_medicamente.get_toate_medicamentele_din_lista().size() == 0)
		throw Exceptie_de_stergere("Reteta nu are medicamente!\n");
	while (lista_de_retete.size() > 0)
	{
		for (int i = 0; i < lista_de_retete.size(); i++)
			lista_de_retete.erase(lista_de_retete.begin() + i);
	}
	notify_observer();
}

void RepoReteta::adauga_medicamente_random_in_reteta(vector<Medicament> lista_de_medicamentee, int numarul_de_medicamente_pe_care_vreau_sa_le_adaug_random_in_reteta)
{
	shuffle(lista_de_medicamentee.begin(), lista_de_medicamentee.end(), std::default_random_engine(std::random_device{}()));
	while (lista_de_retete.size() < numarul_de_medicamente_pe_care_vreau_sa_le_adaug_random_in_reteta && lista_de_medicamentee.size() > 0)
	{
		lista_de_retete.push_back(lista_de_medicamentee.back());
		lista_de_medicamentee.pop_back();
	}
	notify_observer();
}



void RepoRetetaFisier::luam_medicamete_din_fisier()
{
	std::ifstream in(nume_fisier);
	if (!in.is_open())
	{
		throw Exceptii("FIsierul nu s-a putut deschide!\n");
	}
	string linia;
	while (getline(in, linia))
	{
		string denumire, producator, substanta_activa;
		double pret=0;
		stringstream liniile_din_fisier(linia);
		string medicamentul_curent;
		int numarul_medicamentului=0;
		while (getline(liniile_din_fisier, medicamentul_curent, ' '))
		{
			if (numarul_medicamentului == 0)
				denumire = medicamentul_curent;
			if (numarul_medicamentului == 1)
				pret = stod(medicamentul_curent);
			if (numarul_medicamentului == 2)
				producator = medicamentul_curent;
			if (numarul_medicamentului == 3)
				substanta_activa = medicamentul_curent;
			numarul_medicamentului++;
		}
		const Medicament medicament{ denumire, pret, producator, substanta_activa };
		Repository::adauga_medicament_in_lista(medicament);
		//RepoReteta::adauga_medicament_in_reteta(medicament.get_denumire());
	}
	in.close();
}

void RepoRetetaFisier::scriem_medicamentele_in_fisier()
{
	std::ofstream out(nume_fisier);
	if (!out.is_open())
	{
		throw Exceptii("Fisierul nu s-a putut deschide!\n");
	}
	for (auto& medicament : get_toate_medicamentele_din_lista())
	{
		out << medicament.get_denumire()<<" ";
		out << medicament.get_pret() << " ";
		out << medicament.get_producator() << " "; 
		out << medicament.get_substanta_activa() << " ";
		out << endl;
	}
	out.close();
}

void RepoRetetaFisier::adauga_medicament_in_reteta(const string denumire_medicament_pe_care_vrei_sa_il_adaugi_in_reteta)
{
	RepoReteta::adauga_medicament_in_reteta(denumire_medicament_pe_care_vrei_sa_il_adaugi_in_reteta);
	scriem_medicamentele_in_fisier();
}

void RepoRetetaFisier::golseste_reteta_de_medicamente()
{
	RepoReteta::golseste_reteta_de_medicamente();
	scriem_medicamentele_in_fisier();
}

void RepoRetetaFisier::adauga_medicamente_random_in_reteta(vector<Medicament> medicamente, int numarul_de_medicamente_pe_care_vreau_sa_le_adaug_random_in_reteta)
{
	RepoReteta::adauga_medicamente_random_in_reteta(medicamente, numarul_de_medicamente_pe_care_vreau_sa_le_adaug_random_in_reteta);
	scriem_medicamentele_in_fisier();
}

const vector<Medicament>& RepoRetetaFisier::get_toate_medicamentele_din_fisier()
{
	//RepoReteta::golseste_reteta_de_medicamente();m_medicamete_din_fisier();
	return RepoReteta::get_toate_medicamentele_din_lista();
}

void RepositoryFisier::citim_din_fisier()
{
	std::ifstream fisier_medicamente(nume_fisier);
	if (!fisier_medicamente.is_open())
	{
		throw Exceptii("fisierul nu s-a putut deschide!\n");
	}
	string linia_fisierului;
	while (getline(fisier_medicamente, linia_fisierului))
	{
		string denumire, producator, substanta_activa;
		double pret = 0;
		stringstream liniile_fisierului(linia_fisierului);
		string medicamentul_curent;
		int numarul_medicamentului_curent = 0;
		while (getline(liniile_fisierului, medicamentul_curent, ','))
		{
			if (numarul_medicamentului_curent == 0)
				denumire = medicamentul_curent;
			if (numarul_medicamentului_curent == 1)
				pret = stod(medicamentul_curent);
			if (numarul_medicamentului_curent == 2)
				producator = medicamentul_curent;
			if (numarul_medicamentului_curent == 3)
				substanta_activa = medicamentul_curent;
			numarul_medicamentului_curent++;
		}
		Medicament medicament_din_fisier{ denumire, pret, producator, substanta_activa };
		Repository::adauga_medicament_in_lista(medicament_din_fisier);
	}
	fisier_medicamente.close();
}

void RepositoryFisier::scriem_in_fisier()
{
	std::ofstream fisier_medicaemnte(nume_fisier);
	if (!fisier_medicaemnte.is_open())
		throw Exceptii("fisierul nu s-a putut dechide!\n");
	for (auto medicamente_din_fisier : get_toate_medicamentele_din_lista())
	{
		fisier_medicaemnte << medicamente_din_fisier.get_denumire() << "," << medicamente_din_fisier.get_pret() << ',' << medicamente_din_fisier.get_producator() << ',' << medicamente_din_fisier.get_substanta_activa() << endl;
	}
	fisier_medicaemnte.close();
}

void RepositoryFisier::adauga_medicament_in_lista(const Medicament& medicament_de_adaugat)
{
	Repository::adauga_medicament_in_lista(medicament_de_adaugat);
	scriem_in_fisier();
}


void RepositoryFisier::sterge_medicament_din_lista(string denumire_de_sters)
{
	Repository::sterge_medicament_din_lista(denumire_de_sters);
	scriem_in_fisier();
}

void RepositoryFisier::modidica_medicamentul_din_lista(string denumire_de_cautat, string producator_de_cautat, double pret_nou_modifcat, string substanta_activa_de_modificat)
{
	Repository::modidica_medicamentul_din_lista(denumire_de_cautat, producator_de_cautat, pret_nou_modifcat, substanta_activa_de_modificat);
	scriem_in_fisier();
}

const vector<Medicament>& RepositoryFisier::get_toate_medicamentele_din_lista() noexcept
{
	return Repository::get_toate_medicamentele_din_lista();
}
