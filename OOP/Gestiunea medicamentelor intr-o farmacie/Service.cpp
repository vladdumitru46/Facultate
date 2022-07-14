#pragma once
#include "Service.h"

void ServiceMedicament::service_adauga_medicament_in_lista(string denumire, double pret, string producator, string substanta_activa)
{
	Medicament medicament_de_adaugat{ denumire, pret, producator, substanta_activa };
	validare_medicamente.valideaza_medicament(medicament_de_adaugat);
	repo_medicament.adauga_medicament_in_lista(medicament_de_adaugat);
	actiuni_de_undo.push_back(make_unique<UndoAdauga>(repo_medicament, medicament_de_adaugat));
}


const Medicament& ServiceMedicament::service_cauta_un_medicament_in_lista_de_medicamente(string denumire_de_cautat)
{
	return repo_medicament.cauta_un_medicament_in_lista_de_medicamente(denumire_de_cautat);
}


void ServiceMedicament::service_setrge_medicament_din_lista(string denumire_de_sters)
{
	Medicament medicament_undo = service_cauta_un_medicament_in_lista_de_medicamente(denumire_de_sters);
	repo_medicament.sterge_medicament_din_lista(denumire_de_sters);
	actiuni_de_undo.push_back(make_unique<UndoSterge>(repo_medicament, medicament_undo));
}


void ServiceMedicament::service_modifica_medicament_din_lista(string denumire_de_cautat, string prodicator_de_cautat, double pret_de_modificat, string substanta_activa_de_mdoficat)
{
	Medicament medicament_undo = service_cauta_un_medicament_in_lista_de_medicamente(denumire_de_cautat);
	repo_medicament.modidica_medicamentul_din_lista(denumire_de_cautat, prodicator_de_cautat, pret_de_modificat, substanta_activa_de_mdoficat);
	actiuni_de_undo.push_back(make_unique<UndoModifica>(repo_medicament, medicament_undo));
}



const vector<Medicament>& ServiceMedicament::get_toate_medicamentele_din_lista() noexcept {
	return this->repo_medicament.get_toate_medicamentele_din_lista();
}

vector<Medicament> ServiceMedicament::sorteaza_lista_dupa_denumirea_medicamentului()
{
	auto copie_petru_lista_initiala = repo_medicament.get_toate_medicamentele_din_lista();
	sort(copie_petru_lista_initiala.begin(), copie_petru_lista_initiala.end(), [](const Medicament& medicament1, const Medicament& medicament2)
		{
			return medicament1.get_denumire() < medicament2.get_denumire();
		});
	return copie_petru_lista_initiala;
}

vector<Medicament> ServiceMedicament::sorteaza_lista_dupa_producatorul_medicamentului()
{
	auto copie_petru_lista_initiala = repo_medicament.get_toate_medicamentele_din_lista();
	sort(copie_petru_lista_initiala.begin(), copie_petru_lista_initiala.end(), [](const Medicament& medicament1, const Medicament& medicament2)
		{
			return medicament1.get_producator() < medicament2.get_producator();
		});
	return copie_petru_lista_initiala;
}

vector<Medicament> ServiceMedicament::sorteaza_lista_dupa_substanta_activa_si_pretul_medicamentului()
{
	auto copie_petru_lista_initiala = repo_medicament.get_toate_medicamentele_din_lista();
	sort(copie_petru_lista_initiala.begin(), copie_petru_lista_initiala.end(), [](const Medicament& medicament1, const Medicament& medicament2)
		{
			return medicament1.get_denumire() < medicament2.get_denumire() && medicament1.get_pret() < medicament2.get_pret();
		});
	return copie_petru_lista_initiala;
}

vector<Medicament> ServiceMedicament::filtreaza_lista_dupa_pret(double pret_pentru_filtrare)
{
	vector<Medicament> copie_lista_medicamente = repo_medicament.get_toate_medicamentele_din_lista();
	vector<Medicament> lista_filtrata_dup_pret;
	copy_if(copie_lista_medicamente.begin(), copie_lista_medicamente.end(), back_inserter(lista_filtrata_dup_pret), [pret_pentru_filtrare](const Medicament& medicament1) {
			return (medicament1.get_pret() == pret_pentru_filtrare);
		});
	return lista_filtrata_dup_pret;
}

vector<Medicament> ServiceMedicament::filtreaza_lista_dupa_substanta_activa(string substana_activa_pentru_filtrare)
{
	vector<Medicament> copie_lista_medicamente = repo_medicament.get_toate_medicamentele_din_lista();
	vector<Medicament> lista_filtrata_dupa_substana_activa;
	copy_if(copie_lista_medicamente.begin(), copie_lista_medicamente.end(), back_inserter(lista_filtrata_dupa_substana_activa), [substana_activa_pentru_filtrare](const Medicament& medicament1) {
		return (medicament1.get_substanta_activa() == substana_activa_pentru_filtrare);
		});
	return lista_filtrata_dupa_substana_activa;
}


void ServiceMedicament::undo()
{
	if (actiuni_de_undo.empty())
		throw Exceptie_undo("Nu se mai poate face undo!\n");
	else
	{
		actiuni_de_undo.back()->doUndo();
		actiuni_de_undo.pop_back();
	}
}

vector<string>& ServiceMedicament::medicamente_tip_comun()
{
	for (const Medicament& medicament_cautat : repo_medicament.get_toate_medicamentele_din_lista())
	{
		int verificare_daca_producatorul_exista_deja_in_vectorul_cu_producatori = 1;
		for (auto& denumire_producator : vector_cu_producatorii_medicamentului)
		{
			if (medicament_cautat.get_producator() == denumire_producator)
				verificare_daca_producatorul_exista_deja_in_vectorul_cu_producatori = 0;
		}
		if (verificare_daca_producatorul_exista_deja_in_vectorul_cu_producatori == 1)
			vector_cu_producatorii_medicamentului.push_back(medicament_cautat.get_producator());
	}
	return vector_cu_producatorii_medicamentului;
}

int ServiceMedicament::numarul_medicamentuelor_cu_acelsi_producator(string producator_comun)
{
	int numar_medicamente_cu_alcelasi_producator = 0;
	for (const Medicament& medicament_de_cautat : get_toate_medicamentele_din_lista())
		if (medicament_de_cautat.get_producator() == producator_comun)
			numar_medicamente_cu_alcelasi_producator++;
	return numar_medicamente_cu_alcelasi_producator;
}

void ServiceMedicament::adauga_medicament_in_reteta(const string denumire_medicament_pe_care_vrei_sa_il_adaugi_in_reteta)
{
	reteta_de_medicamente.adauga_medicament_in_reteta(denumire_medicament_pe_care_vrei_sa_il_adaugi_in_reteta);
}

void ServiceMedicament::golseste_reteta_de_medicamente()
{
	reteta_de_medicamente.golseste_reteta_de_medicamente();
}

void ServiceMedicament::adauga_medicamente_random_in_cos(int numarul_de_medicamente_pe_care_vreau_sa_le_adaug_random_in_reteta)
{
	reteta_de_medicamente.adauga_medicamente_random_in_reteta(this->get_toate_medicamentele_din_lista(), numarul_de_medicamente_pe_care_vreau_sa_le_adaug_random_in_reteta);

}

const vector<Medicament>& ServiceMedicament::get_toate_medicamentele_din_reteta() noexcept
{
	return reteta_de_medicamente.get_toate_medicamentele_din_lista();
}
