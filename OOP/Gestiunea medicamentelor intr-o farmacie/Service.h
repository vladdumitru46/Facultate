#pragma once
#include "VectorDinamicTemplate.h"
#include "Repository.h"
#include "Validare.h"
#include "Undo.h"
#include<vector>
#include <algorithm>
using std::vector;
using std::sort;
using std::back_inserter;
using std::unique_ptr;
using std::make_unique;

//template<typename VectorDinamic>
class ServiceMedicament {

private:
	Repository& repo_medicament;
	Validare_de_medicamente& validare_medicamente;
	RepoReteta& reteta_de_medicamente;
	vector<unique_ptr<ActiuneUndo>> actiuni_de_undo;
	vector<string> vector_cu_producatorii_medicamentului;

public:
	ServiceMedicament() = default;
	ServiceMedicament(Repository& repo_medicamente, Validare_de_medicamente& validare_medicamente, RepoReteta& reteta_de_medicamnte) noexcept: repo_medicament{ repo_medicamente }, validare_medicamente{ validare_medicamente }, reteta_de_medicamente{reteta_de_medicamnte}{};

	ServiceMedicament(const ServiceMedicament& ) = delete;

	void service_adauga_medicament_in_lista(string denumire, double pret, string producator, string substanta_activa);
	const Medicament& service_cauta_un_medicament_in_lista_de_medicamente(string denumire_de_cautat);
	void service_setrge_medicament_din_lista(string denumire_de_sters);
	void service_modifica_medicament_din_lista(string denumire_de_cautat, string prodicator_de_cautat, double pret_de_modificat, string substanta_activa_de_mdoficat);
	const vector<Medicament>& get_toate_medicamentele_din_lista() noexcept;
	vector<Medicament> sorteaza_lista_dupa_denumirea_medicamentului();
	vector<Medicament> sorteaza_lista_dupa_producatorul_medicamentului();
	vector<Medicament> sorteaza_lista_dupa_substanta_activa_si_pretul_medicamentului();

	vector<Medicament> filtreaza_lista_dupa_pret(double pret_pentru_filtrare);
	vector<Medicament> filtreaza_lista_dupa_substanta_activa(string substana_activa_pentru_filtrare);

	void undo();

	vector<string>& medicamente_tip_comun();
	int numarul_medicamentuelor_cu_acelsi_producator(string producator_comun);

	void adauga_medicament_in_reteta(const string denumire_medicament_pe_care_vrei_sa_il_adaugi_in_reteta);
	void golseste_reteta_de_medicamente();
	void adauga_medicamente_random_in_cos(int numarul_de_medicamente_pe_care_vreau_sa_le_adaug_random_in_reteta);

	const vector<Medicament>& get_toate_medicamentele_din_reteta() noexcept;
};
