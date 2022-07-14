#pragma once
#include "Teste.h"


void Teste::test_creaza_medicament()
{
	Medicament medicament1{ "paracetamol", 233, "Nasa", "C+" };
	assert(medicament1.get_denumire() == "paracetamol");
	assert(medicament1.get_pret() == 233);
	assert(medicament1.get_producator() == "Nasa");
	assert(medicament1.get_substanta_activa() == "C+");

	Medicament medicament2{ "paracetamol", 233, "Nasa", "C+" };

	medicament2.setDenumire("LALa");
	medicament2.setPret(333);
	medicament2.setProducator("AAA");
	medicament2.setSubstanta_activa("D+");

	assert(medicament2.get_denumire() == "LALa");
	assert(medicament2.get_pret() == 333);
	assert(medicament2.get_producator() == "AAA");
	assert(medicament2.get_substanta_activa() == "D+");
}


void Teste::test_adauga_un_medicament_in_lista()
{
	Medicament medicament_de_adaugat{ "paracetamol", 233, "NASA", "C+" };
	Repository lista_de_medicamente;
	assert(lista_de_medicamente.get_toate_medicamentele_din_lista().size() == 0);
	lista_de_medicamente.adauga_medicament_in_lista(medicament_de_adaugat);
	assert(lista_de_medicamente.get_toate_medicamentele_din_lista().size() == 1);
	try {
		lista_de_medicamente.adauga_medicament_in_lista(medicament_de_adaugat);
		assert(false);
	}
	catch(Exceptie_de_adaugare& exceptie) {
		assert(exceptie.get_mesaje_de_erori() == "Medicamentul exista deja in lista\n");
	}
}


void Teste::test_cauta_un_medicament_in_lista()
{
	Repository lista_de_medicamente;
	Medicament medicament_de_adaugat1{ "paracetamol", 233, "NASA", "C+" };
	Medicament medicament_de_adaugat2{ "norofen", 433, "lala", "C+" };
	lista_de_medicamente.adauga_medicament_in_lista(medicament_de_adaugat1);
	lista_de_medicamente.adauga_medicament_in_lista(medicament_de_adaugat2);

	Medicament medicament_gasit=lista_de_medicamente.cauta_un_medicament_in_lista_de_medicamente("paracetamol");
	assert(medicament_gasit.get_denumire() == "paracetamol");
	assert(medicament_gasit.get_pret() == 233);
	assert(medicament_gasit.get_producator() == "NASA");
	assert(medicament_gasit.get_substanta_activa() == "C+");

	try {
		Medicament medicament_care_nu_exista = lista_de_medicamente.cauta_un_medicament_in_lista_de_medicamente("shidgyasd");
		assert(false);
	}
	catch (Exceptie_de_cautare& erori) {
		assert(erori.get_mesaje_de_erori() == "Medicamentul nu exista in lista!\n");
	}
}


void Teste::test_sterge_un_element_din_lista()
{
	Repository lista_de_medicamente;
	Medicament medicament_de_adaugat1{ "paracetamol", 233, "NASA", "C+" };
	Medicament medicament_de_adaugat2{ "norofen", 433, "lala", "C+" };
	lista_de_medicamente.adauga_medicament_in_lista(medicament_de_adaugat1);
	lista_de_medicamente.adauga_medicament_in_lista(medicament_de_adaugat2);

	assert(lista_de_medicamente.get_toate_medicamentele_din_lista().size() == 2);
	lista_de_medicamente.sterge_medicament_din_lista("paracetamol");
	assert(lista_de_medicamente.get_toate_medicamentele_din_lista().size() == 1);
	try {
		lista_de_medicamente.sterge_medicament_din_lista("sdhg");
		assert(false);
	}
	catch (Exceptie_de_stergere& erori) {
		assert(erori.get_mesaje_de_erori() == "Medicamentul nu exista!\n");
	}
}


void Teste::test_modifica_medicament_din_lista()
{
	Repository lista_de_medicamente;
	Medicament medicament_de_adaugat1{ "paracetamol", 233, "NASA", "C+" };
	Medicament medicament_de_adaugat2{ "norofen", 433, "lala", "C+" };
	lista_de_medicamente.adauga_medicament_in_lista(medicament_de_adaugat1);
	lista_de_medicamente.adauga_medicament_in_lista(medicament_de_adaugat2);

	lista_de_medicamente.modidica_medicamentul_din_lista("paracetamol", "NASA", 444, "D+");

	Medicament medicament_modificat = lista_de_medicamente.cauta_un_medicament_in_lista_de_medicamente("paracetamol");
	assert(medicament_modificat.get_pret() == 444);
	assert(medicament_modificat.get_substanta_activa() == "D+");

	try {
		lista_de_medicamente.modidica_medicamentul_din_lista("shdgc", "jsdg", 12, "D+");
		assert(false);
	}
	catch (Exceptie_de_modificare& erori) {
		assert(erori.get_mesaje_de_erori() == "Medicamentul nu exista!\n");
	}
}


void Teste::test_valideaza_medicament()
{
	Validare_de_medicamente validare_de_medicament;
	Medicament medicament_valid{ "paracetamol", 222, "Nasa", "C+" };
	validare_de_medicament.valideaza_medicament(medicament_valid);

	Medicament medicament_invalid("", 0, "", "");
	try {
		validare_de_medicament.valideaza_medicament(medicament_invalid);
		assert(false);
	}
	catch (Validare) {
		assert(true);

	}
}



void Teste::test_service_adauga_il_lista()
{
	Repository lista_de_medicamente;
	Validare_de_medicamente validare_medicamente;
	RepoReteta reteta_de_medicamente{ lista_de_medicamente };
	ServiceMedicament lista_de_medicamente_service{ lista_de_medicamente,validare_medicamente, reteta_de_medicamente };
	assert(lista_de_medicamente_service.get_toate_medicamentele_din_lista().size() == 0);
	lista_de_medicamente_service.service_adauga_medicament_in_lista("paracetamol", 233, "NASA", "C+");
	lista_de_medicamente_service.service_adauga_medicament_in_lista("paracetl", 23, "SA", "C+");
	assert(lista_de_medicamente_service.get_toate_medicamentele_din_lista().size() == 2);
	try {
		lista_de_medicamente_service.service_adauga_medicament_in_lista("", 2, "dfaf", "faaf");
		assert(false);
	}
	catch (Validare& eroare_de_validare) {
		assert(eroare_de_validare.get_stringul_de_erori() == "Denumirea nu poate sa fie vida!\n");
	}
	try {
		lista_de_medicamente_service.service_adauga_medicament_in_lista("dfasdf", -2, "dfaf", "faaf");
		assert(false);
	}
	catch (Validare& eroare_de_validare) {
		assert(eroare_de_validare.get_stringul_de_erori() == "Pretul trebuie sa fie >0!\n");
	}

	try {
		lista_de_medicamente_service.service_adauga_medicament_in_lista("dfasdf", 22, "", "faaf");
		assert(false);
	}
	catch (Validare& eroare_de_validare) {
		assert(eroare_de_validare.get_stringul_de_erori() == "Producatorul nu poate sa fie vid!\n");
	}

	try {
		lista_de_medicamente_service.service_adauga_medicament_in_lista("dfasdf", 22, "dfaf", "");
		assert(false);
	}
	catch (Validare& eroare_de_validare) {
		assert(eroare_de_validare.get_stringul_de_erori() == "Substanta activa nu poate sa fie vida!\n");
	}

}

void Teste::test_service_cauta_medicament_in_lista()
{
	Repository lista_de_medicamente;
	Validare_de_medicamente validare_medicamente;
	RepoReteta reteta_de_medicamente{ lista_de_medicamente };
	ServiceMedicament lista_de_medicamente_service{ lista_de_medicamente,validare_medicamente, reteta_de_medicamente };
	assert(lista_de_medicamente_service.get_toate_medicamentele_din_lista().size() == 0);
	lista_de_medicamente_service.service_adauga_medicament_in_lista("paracetamol", 233, "NASA", "C+");
	lista_de_medicamente_service.service_adauga_medicament_in_lista("paracetl", 23, "SA", "C+");

	Medicament medicament_gasit = lista_de_medicamente_service.service_cauta_un_medicament_in_lista_de_medicamente("paracetamol");
	assert(medicament_gasit.get_denumire() == "paracetamol");
	assert(medicament_gasit.get_pret() == 233);
	assert(medicament_gasit.get_producator() == "NASA");
	assert(medicament_gasit.get_substanta_activa() == "C+");

	try {
		Medicament medicament_negasit = lista_de_medicamente_service.service_cauta_un_medicament_in_lista_de_medicamente("a");
		assert(false);
	}
	catch (Exceptie_de_cautare& erori) {
		assert(erori.get_mesaje_de_erori() == "Medicamentul nu exista in lista!\n");
	}
}

void Teste::test_service_sterge_medicament_din_lista()
{
	Repository lista_de_medicamente;
	Validare_de_medicamente validare_medicamente;
	RepoReteta reteta_de_medicamente{lista_de_medicamente};
	ServiceMedicament lista_de_medicamente_service{ lista_de_medicamente,validare_medicamente, reteta_de_medicamente };
	assert(lista_de_medicamente_service.get_toate_medicamentele_din_lista().size() == 0);
	lista_de_medicamente_service.service_adauga_medicament_in_lista("paracetamol", 233, "NASA", "C+");
	lista_de_medicamente_service.service_adauga_medicament_in_lista("paracetl", 23, "SA", "C+");

	assert(lista_de_medicamente_service.get_toate_medicamentele_din_lista().size() == 2);
	lista_de_medicamente_service.service_setrge_medicament_din_lista("paracetamol");
	assert(lista_de_medicamente_service.get_toate_medicamentele_din_lista().size() == 1);
}


void Teste::test_service_modifca_medicament_din_lista()
{
	Repository lista_de_medicamente;
	Validare_de_medicamente validare_medicamente;
	RepoReteta reteta_de_medicamente{ lista_de_medicamente };
	ServiceMedicament lista_de_medicamente_service{ lista_de_medicamente,validare_medicamente, reteta_de_medicamente };
	assert(lista_de_medicamente_service.get_toate_medicamentele_din_lista().size() == 0);
	lista_de_medicamente_service.service_adauga_medicament_in_lista("paracetamol", 233, "NASA", "C+");
	lista_de_medicamente_service.service_adauga_medicament_in_lista("paracetl", 23, "SA", "C+");

	lista_de_medicamente_service.service_modifica_medicament_din_lista("paracetamol", "NASA", 444, "D+");

	Medicament medicament_modificat = lista_de_medicamente_service.service_cauta_un_medicament_in_lista_de_medicamente("paracetamol");
	assert(medicament_modificat.get_pret() == 444);
	assert(medicament_modificat.get_substanta_activa() == "D+");

}

void Teste::test_service_sorteaza_lista_dupa_denumire()
{
	Repository lista_de_medicamente;
	Validare_de_medicamente validare_medicamente;
	RepoReteta reteta_de_medicamente{ lista_de_medicamente };
	ServiceMedicament lista_de_medicamente_service{ lista_de_medicamente,validare_medicamente, reteta_de_medicamente };
	lista_de_medicamente_service.service_adauga_medicament_in_lista("bbb", 32423, "dhgf", "hdgvay");
	lista_de_medicamente_service.service_adauga_medicament_in_lista("ddd", 32423, "dhgf", "hdgvay");
	lista_de_medicamente_service.service_adauga_medicament_in_lista("aaa", 32423, "dhgf", "hdgvay");
	lista_de_medicamente_service.service_adauga_medicament_in_lista("ccc", 32423, "dhgf", "hdgvay");

	vector<Medicament> lista_sortata_dupa_denumire = lista_de_medicamente_service.sorteaza_lista_dupa_denumirea_medicamentului();

	assert(lista_sortata_dupa_denumire[0].get_denumire() == "aaa");
	assert(lista_sortata_dupa_denumire[1].get_denumire() == "bbb");
	assert(lista_sortata_dupa_denumire[2].get_denumire() == "ccc");
	assert(lista_sortata_dupa_denumire[3].get_denumire() == "ddd");
}

void Teste::test_service_sorteaza_lista_dupa_producator()
{
	Repository lista_de_medicamente;
	Validare_de_medicamente validare_medicamente;
	RepoReteta reteta_de_medicamente{ lista_de_medicamente };
	ServiceMedicament lista_de_medicamente_service{ lista_de_medicamente,validare_medicamente, reteta_de_medicamente };
	lista_de_medicamente_service.service_adauga_medicament_in_lista("bbb", 32423, "bbb", "hdgvay");
	lista_de_medicamente_service.service_adauga_medicament_in_lista("ddd", 32423, "ddd", "hdgvay");
	lista_de_medicamente_service.service_adauga_medicament_in_lista("aaa", 32423, "aaa", "hdgvay");
	lista_de_medicamente_service.service_adauga_medicament_in_lista("ccc", 32423, "ccc", "hdgvay");

	vector<Medicament> lista_sortata_dupa_producator = lista_de_medicamente_service.sorteaza_lista_dupa_producatorul_medicamentului();

	assert(lista_sortata_dupa_producator[0].get_producator() == "aaa");
	assert(lista_sortata_dupa_producator[1].get_producator() == "bbb");
	assert(lista_sortata_dupa_producator[2].get_producator() == "ccc");
	assert(lista_sortata_dupa_producator[3].get_producator() == "ddd");
}

void Teste::test_service_sorteaza_lista_dupa_substanta_activa_si_pret()
{
	Repository lista_de_medicamente;
	Validare_de_medicamente validare_medicamente;
	RepoReteta reteta_de_medicamente{ lista_de_medicamente };
	ServiceMedicament lista_de_medicamente_service{ lista_de_medicamente,validare_medicamente, reteta_de_medicamente };
	lista_de_medicamente_service.service_adauga_medicament_in_lista("bbb", 2, "bbb", "bbb");
	lista_de_medicamente_service.service_adauga_medicament_in_lista("ddd", 4, "ddd", "ddd");
	lista_de_medicamente_service.service_adauga_medicament_in_lista("aaa", 1, "aaa", "aaa");
	lista_de_medicamente_service.service_adauga_medicament_in_lista("ccc", 3.02, "ccc", "ccc");

	vector<Medicament> lista_sortata_dupa_substanta_activa_si_pret = lista_de_medicamente_service.sorteaza_lista_dupa_substanta_activa_si_pretul_medicamentului();
	assert(lista_sortata_dupa_substanta_activa_si_pret[0].get_substanta_activa() == "aaa");
	assert(lista_sortata_dupa_substanta_activa_si_pret[0].get_pret() == 1);
	assert(lista_sortata_dupa_substanta_activa_si_pret[1].get_substanta_activa() == "bbb");
	assert(lista_sortata_dupa_substanta_activa_si_pret[1].get_pret() == 2);
	assert(lista_sortata_dupa_substanta_activa_si_pret[2].get_substanta_activa() == "ccc");
	assert(lista_sortata_dupa_substanta_activa_si_pret[2].get_pret() == 3.02);
	assert(lista_sortata_dupa_substanta_activa_si_pret[3].get_substanta_activa() == "ddd");
	assert(lista_sortata_dupa_substanta_activa_si_pret[3].get_pret() == 4);

}

void Teste::test_service_filtreaza_lista_dupa_pret()
{
	Repository lista_de_medicamente;
	Validare_de_medicamente validare_medicamente;
	RepoReteta reteta_de_medicamente{ lista_de_medicamente };
	ServiceMedicament lista_de_medicamente_service{ lista_de_medicamente,validare_medicamente, reteta_de_medicamente };

	lista_de_medicamente_service.service_adauga_medicament_in_lista("bbb", 2, "bbb", "bbb");
	lista_de_medicamente_service.service_adauga_medicament_in_lista("ddd", 4, "ddd", "ddd");
	lista_de_medicamente_service.service_adauga_medicament_in_lista("aaa", 2, "aaa", "aaa");
	lista_de_medicamente_service.service_adauga_medicament_in_lista("ccc", 2, "ccc", "ccc");
	vector<Medicament> lista_filtrata_dupa_pret = lista_de_medicamente_service.filtreaza_lista_dupa_pret(2);

	assert(lista_filtrata_dupa_pret[0].get_denumire() == "bbb");
	assert(lista_filtrata_dupa_pret[1].get_denumire() == "aaa");
	assert(lista_filtrata_dupa_pret[2].get_denumire() == "ccc");
}

void Teste::test_service_filtreaza_lista_dupa_substanta_activa()
{
	Repository lista_de_medicamente;
	Validare_de_medicamente validare_medicamente;
	RepoReteta reteta_de_medicamente{ lista_de_medicamente };
	ServiceMedicament lista_de_medicamente_service{ lista_de_medicamente,validare_medicamente, reteta_de_medicamente };
	lista_de_medicamente_service.service_adauga_medicament_in_lista("bbb", 2, "bbb", "bbb");
	lista_de_medicamente_service.service_adauga_medicament_in_lista("ddd", 4, "ddd", "ddd");
	lista_de_medicamente_service.service_adauga_medicament_in_lista("aaa", 2, "aaa", "bbb");
	lista_de_medicamente_service.service_adauga_medicament_in_lista("ccc", 2, "ccc", "bbb");
	vector<Medicament> lista_filtrata_dupa_substanta_activa = lista_de_medicamente_service.filtreaza_lista_dupa_substanta_activa("bbb");

	assert(lista_filtrata_dupa_substanta_activa[0].get_denumire() == "bbb");
	assert(lista_filtrata_dupa_substanta_activa[1].get_denumire() == "aaa");
	assert(lista_filtrata_dupa_substanta_activa[2].get_denumire() == "ccc");
}


void Teste::test_repo_reteta_adauga_medicamente_il_lista_de_reteta()
{
	Repository lista_de_medicamente;
	RepoReteta reteta_de_medicamente{ lista_de_medicamente };
	Medicament medicament1{ "p", 233, "producator", "C+" };
	Medicament medicament2{ "n", 233, "producator", "C+" };
	Medicament medicament3{ "c", 233, "producator", "C+" };
	Medicament medicament4{ "s", 233, "producator", "C+" };
	Medicament medicament5{ "a", 233, "producator", "C+" };

	lista_de_medicamente.adauga_medicament_in_lista(medicament1);
	lista_de_medicamente.adauga_medicament_in_lista(medicament2);
	lista_de_medicamente.adauga_medicament_in_lista(medicament3);
	lista_de_medicamente.adauga_medicament_in_lista(medicament4);
	lista_de_medicamente.adauga_medicament_in_lista(medicament5);
	assert(reteta_de_medicamente.get_toate_medicamentele_din_lista().size() == 0);
	reteta_de_medicamente.adauga_medicament_in_reteta("p");
	reteta_de_medicamente.adauga_medicament_in_reteta("n");
	reteta_de_medicamente.adauga_medicament_in_reteta("a");
	assert(reteta_de_medicamente.get_toate_medicamentele_din_lista().size() == 3);

	try {
		reteta_de_medicamente.adauga_medicament_in_reteta("sdajifgyusdgfyisgdyifusidfuod");
		assert(false);
	}
	catch (Exceptie_de_adaugare& exceptii) {
		assert(exceptii.get_mesaje_de_erori() == "Nu exista medicamenul pe care doresti sa il adaugi in reteta!\n");
	}

}

void Teste::test_repo_reteta_goleste_toata_lista_de_retata()
{
	Repository lista_de_medicamente;
	RepoReteta reteta_de_medicamente{ lista_de_medicamente };
	Medicament medicament1{ "p", 233, "producator", "C+" };
	Medicament medicament2{ "n", 233, "producator", "C+" };
	Medicament medicament3{ "c", 233, "producator", "C+" };
	Medicament medicament4{ "s", 233, "producator", "C+" };
	Medicament medicament5{ "a", 233, "producator", "C+" };

	try {
		reteta_de_medicamente.golseste_reteta_de_medicamente();
		assert(false);
	}
	catch (Exceptie_de_stergere& exceptie) {
		assert(exceptie.get_mesaje_de_erori() == "Reteta nu are medicamente!\n");
	}

	lista_de_medicamente.adauga_medicament_in_lista(medicament1);
	lista_de_medicamente.adauga_medicament_in_lista(medicament2);
	lista_de_medicamente.adauga_medicament_in_lista(medicament3);
	lista_de_medicamente.adauga_medicament_in_lista(medicament4);
	lista_de_medicamente.adauga_medicament_in_lista(medicament5);
	assert(reteta_de_medicamente.get_toate_medicamentele_din_lista().size() == 0);
	reteta_de_medicamente.adauga_medicament_in_reteta("p");
	reteta_de_medicamente.adauga_medicament_in_reteta("n");
	reteta_de_medicamente.adauga_medicament_in_reteta("a");
	assert(reteta_de_medicamente.get_toate_medicamentele_din_lista().size() == 3);

	reteta_de_medicamente.golseste_reteta_de_medicamente();
	assert(reteta_de_medicamente.get_toate_medicamentele_din_lista().size() == 0);

}

void Teste::test_repo_reteta_adauga_medicamente_random_in_reteta()
{
	Repository lista_de_medicamente;
	RepoReteta reteta_de_medicamente{ lista_de_medicamente };
	Medicament medicament1{ "p", 233, "producator", "C+" };
	Medicament medicament2{ "n", 233, "producator", "C+" };
	Medicament medicament3{ "c", 233, "producator", "C+" };
	Medicament medicament4{ "s", 233, "producator", "C+" };
	Medicament medicament5{ "a", 233, "producator", "C+" };

	lista_de_medicamente.adauga_medicament_in_lista(medicament1);
	lista_de_medicamente.adauga_medicament_in_lista(medicament2);
	lista_de_medicamente.adauga_medicament_in_lista(medicament3);
	lista_de_medicamente.adauga_medicament_in_lista(medicament4);
	lista_de_medicamente.adauga_medicament_in_lista(medicament5);
	assert(reteta_de_medicamente.get_toate_medicamentele_din_lista().size() == 0);

	reteta_de_medicamente.adauga_medicamente_random_in_reteta(lista_de_medicamente.get_toate_medicamentele_din_lista(), 3);
	assert(reteta_de_medicamente.get_toate_medicamentele_din_lista().size() == 3);
}

void Teste::test_service_reteta_adauga_medicamente_in_retata()
{
	Repository lista_de_medicamente;
	Validare_de_medicamente validare_medicamente;
	RepoReteta reteta_de_medicamente{lista_de_medicamente};
	ServiceMedicament service_lista_de_medicamente{ lista_de_medicamente,validare_medicamente, reteta_de_medicamente };

	service_lista_de_medicamente.service_adauga_medicament_in_lista("1", 1, "1", "1");
	service_lista_de_medicamente.service_adauga_medicament_in_lista("2", 2, "2", "2");
	service_lista_de_medicamente.service_adauga_medicament_in_lista("3", 3, "3", "3");
	service_lista_de_medicamente.service_adauga_medicament_in_lista("4", 4, "4", "4");
	service_lista_de_medicamente.service_adauga_medicament_in_lista("5", 5, "5", "5");
	service_lista_de_medicamente.service_adauga_medicament_in_lista("6", 6, "6", "6");
	service_lista_de_medicamente.service_adauga_medicament_in_lista("7", 7, "7", "7");
	service_lista_de_medicamente.service_adauga_medicament_in_lista("8", 8, "8", "8");

	service_lista_de_medicamente.adauga_medicament_in_reteta("1");
	service_lista_de_medicamente.adauga_medicament_in_reteta("2");
	service_lista_de_medicamente.adauga_medicament_in_reteta("3");
	assert(service_lista_de_medicamente.get_toate_medicamentele_din_reteta().size() == 3);

}

void Teste::test_service_reteta_goleste_reteta()
{
	Repository lista_de_medicamente;
	Validare_de_medicamente validare_medicamente;
	RepoReteta reteta_de_medicamente{ lista_de_medicamente };
	ServiceMedicament service_lista_de_medicamente{ lista_de_medicamente,validare_medicamente, reteta_de_medicamente };

	service_lista_de_medicamente.service_adauga_medicament_in_lista("1", 1, "1", "1");
	service_lista_de_medicamente.service_adauga_medicament_in_lista("2", 2, "2", "2");
	service_lista_de_medicamente.service_adauga_medicament_in_lista("3", 3, "3", "3");
	service_lista_de_medicamente.service_adauga_medicament_in_lista("4", 4, "4", "4");
	service_lista_de_medicamente.service_adauga_medicament_in_lista("5", 5, "5", "5");
	service_lista_de_medicamente.service_adauga_medicament_in_lista("6", 6, "6", "6");
	service_lista_de_medicamente.service_adauga_medicament_in_lista("7", 7, "7", "7");
	service_lista_de_medicamente.service_adauga_medicament_in_lista("8", 8, "8", "8");

	service_lista_de_medicamente.adauga_medicament_in_reteta("1");
	service_lista_de_medicamente.adauga_medicament_in_reteta("2");
	service_lista_de_medicamente.adauga_medicament_in_reteta("3");
	assert(service_lista_de_medicamente.get_toate_medicamentele_din_reteta().size() == 3);

	service_lista_de_medicamente.golseste_reteta_de_medicamente();
	assert(service_lista_de_medicamente.get_toate_medicamentele_din_reteta().size() == 0);

}

void Teste::test_service_reteta_adauga_medicamente_random_in_reteta()
{
	Repository lista_de_medicamente;
	Validare_de_medicamente validare_medicamente;
	RepoReteta reteta_de_medicamente{ lista_de_medicamente };
	ServiceMedicament service_lista_de_medicamente{ lista_de_medicamente,validare_medicamente, reteta_de_medicamente };

	service_lista_de_medicamente.service_adauga_medicament_in_lista("1", 1, "1", "1");
	service_lista_de_medicamente.service_adauga_medicament_in_lista("2", 2, "2", "2");
	service_lista_de_medicamente.service_adauga_medicament_in_lista("3", 3, "3", "3");
	service_lista_de_medicamente.service_adauga_medicament_in_lista("4", 4, "4", "4");
	service_lista_de_medicamente.service_adauga_medicament_in_lista("5", 5, "5", "5");
	service_lista_de_medicamente.service_adauga_medicament_in_lista("6", 6, "6", "6");
	service_lista_de_medicamente.service_adauga_medicament_in_lista("7", 7, "7", "7");
	service_lista_de_medicamente.service_adauga_medicament_in_lista("8", 8, "8", "8");

	service_lista_de_medicamente.adauga_medicamente_random_in_cos(4);
	assert(service_lista_de_medicamente.get_toate_medicamentele_din_reteta().size() == 4);
}

void Teste::test_service_undo()
{
	Repository lista_de_medicamente;
	Validare_de_medicamente validare_medicamente;
	RepoReteta reteta_de_medicamente{ lista_de_medicamente };
	ServiceMedicament service_lista_de_medicamente{ lista_de_medicamente,validare_medicamente, reteta_de_medicamente };

	service_lista_de_medicamente.service_adauga_medicament_in_lista("1", 1, "1", "1");
	assert(service_lista_de_medicamente.get_toate_medicamentele_din_lista().size() == 1);
	service_lista_de_medicamente.undo();
	assert(service_lista_de_medicamente.get_toate_medicamentele_din_lista().size() == 0);
	try {
		service_lista_de_medicamente.undo();
		assert(false);
	}
	catch (Exceptie_undo)
	{
		assert(true);
	}

}

void Teste::test_medicament_comun()
{
	Repository lista_de_medicamente;
	Validare_de_medicamente validare_medicamente;
	RepoReteta reteta_de_medicamente{ lista_de_medicamente };
	ServiceMedicament service_lista_de_medicamente{ lista_de_medicamente,validare_medicamente, reteta_de_medicamente };

	service_lista_de_medicamente.service_adauga_medicament_in_lista("1", 1, "1", "1");
	service_lista_de_medicamente.service_adauga_medicament_in_lista("2", 2, "1", "2");
	service_lista_de_medicamente.service_adauga_medicament_in_lista("3", 3, "1", "3");
	service_lista_de_medicamente.service_adauga_medicament_in_lista("4", 4, "2", "4");
	service_lista_de_medicamente.service_adauga_medicament_in_lista("5", 5, "2", "5");
	service_lista_de_medicamente.service_adauga_medicament_in_lista("6", 6, "3", "6");
	service_lista_de_medicamente.service_adauga_medicament_in_lista("7", 7, "4", "7");
	service_lista_de_medicamente.service_adauga_medicament_in_lista("8", 8, "3", "8");

	vector<string> vector_producatir_comun = service_lista_de_medicamente.medicamente_tip_comun();
	assert(vector_producatir_comun[0] == "1");
	assert(vector_producatir_comun[1] == "2");
	assert(vector_producatir_comun[2] == "3");
	assert(vector_producatir_comun[3] == "4");

	int numar = service_lista_de_medicamente.numarul_medicamentuelor_cu_acelsi_producator(vector_producatir_comun[0]);
	assert(numar == 3);
}

void Teste::test_repo_reteta_fisier_adauga()
{
	Repository lista_de_medicamente;
	RepoReteta reteta_de_medicamente{ lista_de_medicamente };
	string nume_fisier = "Test_repo_fisier.txt";

	RepoRetetaFisier reteta_de_medicamente_fisier{ lista_de_medicamente, nume_fisier };
	Medicament medicament1{ "p", 233, "producator", "C+" };
	Medicament medicament2{ "n", 233, "producator", "C+" };
	Medicament medicament3{ "c", 233, "producator", "C+" };
	Medicament medicament4{ "s", 233, "producator", "C+" };
	Medicament medicament5{ "a", 233, "producator", "C+" };

	lista_de_medicamente.adauga_medicament_in_lista(medicament1);
	lista_de_medicamente.adauga_medicament_in_lista(medicament2);
	lista_de_medicamente.adauga_medicament_in_lista(medicament3);
	lista_de_medicamente.adauga_medicament_in_lista(medicament4);
	lista_de_medicamente.adauga_medicament_in_lista(medicament5);

	assert(reteta_de_medicamente_fisier.get_toate_medicamentele_din_fisier().size() == 0);
	reteta_de_medicamente_fisier.adauga_medicament_in_reteta("p");
	reteta_de_medicamente_fisier.adauga_medicament_in_reteta("n");
	reteta_de_medicamente_fisier.adauga_medicament_in_reteta("c");
	assert(reteta_de_medicamente_fisier.get_toate_medicamentele_din_fisier().size() == 3);


}

void Teste::tets_repo_reteta_fisier_goleste_lista()
{
	Repository lista_de_medicamente;
	RepoReteta reteta_de_medicamente{ lista_de_medicamente };
	string nume_fisier = "Test_repo_fisier.txt";

	RepoRetetaFisier reteta_de_medicamente_fisier{ lista_de_medicamente, nume_fisier };
	Medicament medicament1{ "p", 233, "producator", "C+" };
	Medicament medicament2{ "n", 233, "producator", "C+" };
	Medicament medicament3{ "c", 233, "producator", "C+" };
	Medicament medicament4{ "s", 233, "producator", "C+" };
	Medicament medicament5{ "a", 233, "producator", "C+" };

	lista_de_medicamente.adauga_medicament_in_lista(medicament1);
	lista_de_medicamente.adauga_medicament_in_lista(medicament2);
	lista_de_medicamente.adauga_medicament_in_lista(medicament3);
	lista_de_medicamente.adauga_medicament_in_lista(medicament4);
	lista_de_medicamente.adauga_medicament_in_lista(medicament5);

	assert(reteta_de_medicamente_fisier.get_toate_medicamentele_din_fisier().size() == 0);
	reteta_de_medicamente_fisier.adauga_medicament_in_reteta("p");
	reteta_de_medicamente_fisier.adauga_medicament_in_reteta("n");
	reteta_de_medicamente_fisier.adauga_medicament_in_reteta("c");
	assert(reteta_de_medicamente_fisier.get_toate_medicamentele_din_fisier().size() == 3);

	reteta_de_medicamente_fisier.golseste_reteta_de_medicamente();
	assert(reteta_de_medicamente_fisier.get_toate_medicamentele_din_fisier().size() == 0);
}

void Teste::test_repo_reteta_fisier_adauga_random()
{
	Repository lista_de_medicamente;
	RepoReteta reteta_de_medicamente{ lista_de_medicamente };
	string nume_fisier = "Test_repo_fisier.txt";

	RepoRetetaFisier reteta_de_medicamente_fisier{ lista_de_medicamente, nume_fisier };
	Medicament medicament1{ "p", 233, "producator", "C+" };
	Medicament medicament2{ "n", 233, "producator", "C+" };
	Medicament medicament3{ "c", 233, "producator", "C+" };
	Medicament medicament4{ "s", 233, "producator", "C+" };
	Medicament medicament5{ "a", 233, "producator", "C+" };

	lista_de_medicamente.adauga_medicament_in_lista(medicament1);
	lista_de_medicamente.adauga_medicament_in_lista(medicament2);
	lista_de_medicamente.adauga_medicament_in_lista(medicament3);
	lista_de_medicamente.adauga_medicament_in_lista(medicament4);
	lista_de_medicamente.adauga_medicament_in_lista(medicament5);

	assert(reteta_de_medicamente_fisier.get_toate_medicamentele_din_fisier().size() == 0);
	reteta_de_medicamente_fisier.adauga_medicamente_random_in_reteta(lista_de_medicamente.get_toate_medicamentele_din_lista(), 3);
	assert(reteta_de_medicamente_fisier.get_toate_medicamentele_din_fisier().size() == 3);
}

void Teste::run_teste()
{
	//cout << "Start teste\n";
	test_creaza_medicament();
	test_adauga_un_medicament_in_lista();
	test_cauta_un_medicament_in_lista();
	test_sterge_un_element_din_lista();
	test_modifica_medicament_din_lista();
	test_valideaza_medicament();
	test_service_adauga_il_lista();
	test_service_cauta_medicament_in_lista();
	test_service_sterge_medicament_din_lista();
	test_service_modifca_medicament_din_lista();
	test_service_sorteaza_lista_dupa_denumire();
	test_service_sorteaza_lista_dupa_producator();
	test_service_sorteaza_lista_dupa_substanta_activa_si_pret();
	test_service_filtreaza_lista_dupa_pret();
	test_service_filtreaza_lista_dupa_substanta_activa();
	test_repo_reteta_adauga_medicamente_il_lista_de_reteta();
	test_repo_reteta_goleste_toata_lista_de_retata();
	test_repo_reteta_adauga_medicamente_random_in_reteta();
	test_service_reteta_adauga_medicamente_in_retata();
	test_service_reteta_goleste_reteta();
	test_service_reteta_adauga_medicamente_random_in_reteta();
	test_repo_reteta_fisier_adauga();
	tets_repo_reteta_fisier_goleste_lista();
	test_repo_reteta_fisier_adauga_random();
	test_service_undo();
	test_medicament_comun();
	cout << "Finish teste\n";
}
