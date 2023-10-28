#pragma once
#include <iostream>
#include <string>
#include <assert.h>
using std::cout;
//#include "VectorDinamicTemplate.h"
#include "Medicament.h"
#include"Repository.h"
#include "Validare.h"
#include "Service.h"

//template<typename VectorDinamic>
class Teste {

public:
	void test_creaza_medicament();
	void test_adauga_un_medicament_in_lista();
	void test_cauta_un_medicament_in_lista();
	void test_sterge_un_element_din_lista();
	void test_modifica_medicament_din_lista();
	void test_valideaza_medicament();
	void test_service_adauga_il_lista();
	void test_service_cauta_medicament_in_lista();
	void test_service_sterge_medicament_din_lista();
	void test_service_modifca_medicament_din_lista();
	void test_service_sorteaza_lista_dupa_denumire();
	void test_service_sorteaza_lista_dupa_producator();
	void test_service_sorteaza_lista_dupa_substanta_activa_si_pret();
	void test_service_filtreaza_lista_dupa_pret();
	void test_service_filtreaza_lista_dupa_substanta_activa();

	void test_repo_reteta_adauga_medicamente_il_lista_de_reteta();
	void test_repo_reteta_goleste_toata_lista_de_retata();
	void test_repo_reteta_adauga_medicamente_random_in_reteta();

	void test_service_reteta_adauga_medicamente_in_retata();
	void test_service_reteta_goleste_reteta();
	void test_service_reteta_adauga_medicamente_random_in_reteta();
	void test_service_undo();
	void test_medicament_comun();
	void test_repo_reteta_fisier_adauga();
	void tets_repo_reteta_fisier_goleste_lista();
	void test_repo_reteta_fisier_adauga_random();
	void run_teste();
};
