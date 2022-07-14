#pragma once
#include "Medicament.h"
#include "Exceptii.h"
#include "Observer.h"
#include <vector>
#include <algorithm>
#include <random>
#include <chrono>
#include <fstream>
using std::vector;
using std::find_if;


class Repository {

protected:
	vector<Medicament> lista_de_medicamente;

public:
	Repository() = default;
	
	Repository(const Repository& repo_medicament) = delete;
	
	virtual void adauga_medicament_in_lista(const Medicament& medicament_de_adaugat);
	
	int cauta_element_dupa_pozitie_in_vector(string denumire_de_cautat);
	
	const Medicament& cauta_un_medicament_in_lista_de_medicamente(string denumire_de_cautat);
	
	virtual void sterge_medicament_din_lista(string denumire_de_sters);
	
	virtual void modidica_medicamentul_din_lista(string denumire_de_cautat, string producator_de_cautat, double pret_nou_modifcat, string substanta_activa_de_modificat);
	
	virtual const vector<Medicament>& get_toate_medicamentele_din_lista() noexcept;
	
	virtual ~Repository() = default;
};

class RepositoryFisier : public Repository {
private:
	void citim_din_fisier();
	void scriem_in_fisier();
	string nume_fisier;
public:
	RepositoryFisier(string nume_fisier) : nume_fisier{ nume_fisier } {
		citim_din_fisier();
	};
	void adauga_medicament_in_lista(const Medicament& medicament_de_adaugat) override;

	void sterge_medicament_din_lista(string denumire_de_sters) override;

	void modidica_medicamentul_din_lista(string denumire_de_cautat, string producator_de_cautat, double pret_nou_modifcat, string substanta_activa_de_modificat) override;
	
	const vector<Medicament>& get_toate_medicamentele_din_lista() noexcept override;
};

class RepoReteta : public Repository, public Observable{
private: 
	vector<Medicament> lista_de_retete;
	Repository& lista_de_medicamente;
public:
	RepoReteta(Repository& lista_de_medicamente) noexcept:lista_de_medicamente{ lista_de_medicamente } {};
	
	RepoReteta(const Repository& repo_reteta) = delete;
	
	const vector<Medicament>& get_toate_medicamentele_din_lista() noexcept override;
	
	virtual void adauga_medicament_in_reteta(const string denumire_medicament_pe_care_vrei_sa_il_adaugi_in_reteta);
	
	virtual void golseste_reteta_de_medicamente();
	
	virtual void adauga_medicamente_random_in_reteta(vector<Medicament> medicamente,int numarul_de_medicamente_pe_care_vreau_sa_le_adaug_random_in_reteta);
};

class RepoRetetaFisier : public RepoReteta {

private:
	string nume_fisier;
	void luam_medicamete_din_fisier();
	void scriem_medicamentele_in_fisier();

public:
	RepoRetetaFisier(Repository& lista_de_medicamente, string nume_de_fisier) : RepoReteta(lista_de_medicamente), nume_fisier{ nume_de_fisier }{
		//luam_medicamete_din_fisier();
	};
	virtual void adauga_medicament_in_reteta(const string denumire_medicament_pe_care_vrei_sa_il_adaugi_in_reteta) override;

	virtual void golseste_reteta_de_medicamente() override;

	virtual void adauga_medicamente_random_in_reteta(vector<Medicament> medicamente, int numarul_de_medicamente_pe_care_vreau_sa_le_adaug_random_in_reteta)override;

	const vector<Medicament>& get_toate_medicamentele_din_fisier();
};