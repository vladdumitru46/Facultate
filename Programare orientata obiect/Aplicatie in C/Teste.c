#include "Teste.h"
#include <stdio.h>

void test_creare_cheltuiala()
{
	Cheltuiala cheltuiala_lunara = creare_cheltuiala(12, 222, "apa");
	assert(cheltuiala_lunara.numarul_apartamentului == 12);
	assert(cheltuiala_lunara.suma_lunara_a_cheltuielii == 222);
	assert(strcmp(cheltuiala_lunara.tipul_de_cheltuiala_lunara, "apa") == 0);

	destroy_cheltuiala(&cheltuiala_lunara);
}

void test_creare_lista_cheltuiala()
{
	Lista_cheltuiala lista = creare_lista_cheltuiala();
	assert(lista.lungime_lista_cheltuiala == 0);
	distruge_lista_de_cheltuiala(&lista);
}


void test_adauga_cheltuiala_noua_in_lista()
{
	Lista_cheltuiala lista_cheltuieli = creare_lista_cheltuiala();
	Cheltuiala cheltuiala_lunara_1 = creare_cheltuiala(12, 222, "apa");
	Cheltuiala cheltuiala_lunara_2 = creare_cheltuiala(13, 342343, "gaz");
	Cheltuiala cheltuiala_lunara_3 = creare_cheltuiala(14, 342342, "incalizre");
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_1);
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_2);
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_3);
	assert(lista_cheltuieli.lungime_lista_cheltuiala == 3);

	Cheltuiala cheltuiala_lunara = get_element_din_lista_de_cheltuiala(&lista_cheltuieli, 0);
	assert(cheltuiala_lunara.numarul_apartamentului == 12);
	assert(cheltuiala_lunara.suma_lunara_a_cheltuielii == 222);
	assert(strcmp(cheltuiala_lunara.tipul_de_cheltuiala_lunara, "apa") == 0);

	distruge_lista_de_cheltuiala(&lista_cheltuieli);
	assert(lista_cheltuieli.lungime_lista_cheltuiala == 0);

}
void test_cauta_element_in_lista()
{
	Lista_cheltuiala lista_cheltuieli = creare_lista_cheltuiala();
	Cheltuiala cheltuiala_lunara_1 = creare_cheltuiala(12, 222, "apa");
	Cheltuiala cheltuiala_lunara_2 = creare_cheltuiala(13, 342343, "gaz");
	Cheltuiala cheltuiala_lunara_3 = creare_cheltuiala(14, 342342, "incalizre");
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_1);
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_2);
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_3);
	assert(lista_cheltuieli.lungime_lista_cheltuiala == 3);

	int pozitie_cautata = cauta_cheltuiala_lunara(&lista_cheltuieli, 12, "apa");
	assert(pozitie_cautata == 0);
	distruge_lista_de_cheltuiala(&lista_cheltuieli);
}

void test_modificarea_unei_cheltuieli()
{
	Lista_cheltuiala lista_cheltuieli = creare_lista_cheltuiala();
	Cheltuiala cheltuiala_lunara_1 = creare_cheltuiala(12, 222, "apa");
	Cheltuiala cheltuiala_lunara_2 = creare_cheltuiala(15, 222, "gaz");
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_1);
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_2);

	modifica_tipul_si_suma_unei_cheltuieli_lunare(&lista_cheltuieli, 12, 333, "apa", "gaz");
	assert(lista_cheltuieli.elemete_cheltuiala[0].suma_lunara_a_cheltuielii == 333);
	assert(strcmp(lista_cheltuieli.elemete_cheltuiala[0].tipul_de_cheltuiala_lunara, "gaz") == 0);

	distruge_lista_de_cheltuiala(&lista_cheltuieli);
}

void test_stergerea_unei_cheluieli_din_vector_dupa_numarul_apartamentului_si_tipul_de_cheltuiala_lunara()
{
	Lista_cheltuiala lista_cheltuieli = creare_lista_cheltuiala();
	Cheltuiala cheltuiala_lunara_1 = creare_cheltuiala(12, 222, "apa");
	Cheltuiala cheltuiala_lunara_2 = creare_cheltuiala(13, 34, "gaz");
	//Cheltuiala cheltuiala_lunara_3 = creare_cheltuiala(14, 34.55, "incalizre");
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_1);
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_2);
	//adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_3);
	assert(cauta_cheltuiala_lunara(&lista_cheltuieli, 12, "apa") == 0);
	sterge_cheltuiala_lunara_dupa_numarul_apartamentului_si_tipul_de_cheltuiala(&lista_cheltuieli, 12, "apa");
	assert(lista_cheltuieli.lungime_lista_cheltuiala == 1);
	assert(cauta_cheltuiala_lunara(&lista_cheltuieli, 12, "apa") == -1);

	distruge_lista_de_cheltuiala(&lista_cheltuieli);
}

void test_copie_lista()
{
	Lista_cheltuiala lista_cheltuieli = creare_lista_cheltuiala();
	Cheltuiala cheltuiala_lunara_1 = creare_cheltuiala(12, 222, "apa");
	Cheltuiala cheltuiala_lunara_2 = creare_cheltuiala(13, 342343, "gaz");
	Cheltuiala cheltuiala_lunara_3 = creare_cheltuiala(14, 342342, "incalizre");
	Cheltuiala cheltuiala_lunara_4 = creare_cheltuiala(15, 222, "apa");
	Cheltuiala cheltuiala_lunara_5 = creare_cheltuiala(16, 342343, "apa");
	Cheltuiala cheltuiala_lunara_6 = creare_cheltuiala(17, 342342, "incalizre");
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_1);
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_2);
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_3);
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_4);
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_5);
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_6);
	Lista_cheltuiala copie_lista_cheltuiala = creare_cpoie_pentru_lista(&lista_cheltuieli);
	assert(copie_lista_cheltuiala.lungime_lista_cheltuiala == 6);
	
	
	distruge_lista_de_cheltuiala(&copie_lista_cheltuiala);
	distruge_lista_de_cheltuiala(&lista_cheltuieli);
}

void test_validarea_unei_cheltuieli()
{
	Cheltuiala cheltuiala_valida = creare_cheltuiala(12, 222, "apa");
	assert(validare_cheltuiala_noua(cheltuiala_valida) == 0);
	Cheltuiala cheltuiala_apartament_invalid = creare_cheltuiala(-99, 222, "apa");
	assert(validare_cheltuiala_noua(cheltuiala_apartament_invalid) == 1);
	Cheltuiala cheltuiala_suma_invalid = creare_cheltuiala(99, -222, "apa");
	assert(validare_cheltuiala_noua(cheltuiala_suma_invalid) == 2);
	Cheltuiala cheltuiala_tip_invalid = creare_cheltuiala(99, 222, "dsfuigsduf");
	assert(validare_cheltuiala_noua(cheltuiala_tip_invalid) == 3);
	destroy_cheltuiala(&cheltuiala_valida);
	destroy_cheltuiala(&cheltuiala_apartament_invalid);
	destroy_cheltuiala(&cheltuiala_suma_invalid);
	destroy_cheltuiala(&cheltuiala_tip_invalid);
}


void test_service_adauga_in_lista()
{
	Lista_cheltuiala lista_cheltuieli = creare_lista_cheltuiala();
	assert(service_adauga_cheltuiala_noua(&lista_cheltuieli, 12, 222, "apa") == 0);//adauga cheltuiala valida
	assert(lista_cheltuieli.lungime_lista_cheltuiala == 1);

	assert(service_adauga_cheltuiala_noua(&lista_cheltuieli, -12, 222, "apa") == 1);//chaltuiala apartament invalid
	assert(service_adauga_cheltuiala_noua(&lista_cheltuieli, 12, -222, "apa") == 1);//chaltuiala suma invalida
	assert(service_adauga_cheltuiala_noua(&lista_cheltuieli, 12, 222, "rtergapa") == 1);//chaltuiala tip invalid

	distruge_lista_de_cheltuiala(&lista_cheltuieli);
}
void test_servvice_modifica_cheltuiala()
{
	Lista_cheltuiala lista_cheltuieli = creare_lista_cheltuiala();
	service_adauga_cheltuiala_noua(&lista_cheltuieli, 12, 333, "apa");
	assert(service_modifica_tipul_si_suma_unei_cheltuieli_lunare(&lista_cheltuieli, 12, 324, "apa", "gaz") == 0);//modificare cheltuiala existenta
	assert(lista_cheltuieli.elemete_cheltuiala[0].suma_lunara_a_cheltuielii == 324);
	assert(strcmp(lista_cheltuieli.elemete_cheltuiala[0].tipul_de_cheltuiala_lunara, "gaz") == 0);
	assert(service_modifica_tipul_si_suma_unei_cheltuieli_lunare(&lista_cheltuieli, 3432, 324, "apa", "gaz") == 1);//modificare cheltuiala inexistenta
	distruge_lista_de_cheltuiala(&lista_cheltuieli);

}
void test_service_sterge_cheltuiala()
{
	Lista_cheltuiala lista_cheltuieli = creare_lista_cheltuiala();
	service_adauga_cheltuiala_noua(&lista_cheltuieli, 12, 333, "apa");
	service_adauga_cheltuiala_noua(&lista_cheltuieli, 14, 333, "gaz");
	assert(service_stergere_cheltuiala_dupa_numarul_apartamentului_si_dupa_tipul_de_cheltuiala_lunara(&lista_cheltuieli, 12, "apa") == 0);//steregre cheltuiala existeta
	assert(lista_cheltuieli.lungime_lista_cheltuiala == 1);
	assert(service_stergere_cheltuiala_dupa_numarul_apartamentului_si_dupa_tipul_de_cheltuiala_lunara(&lista_cheltuieli, 4535, "gaz") == 1);//sterege cheltuiala inexistanta
	
	distruge_lista_de_cheltuiala(&lista_cheltuieli);

}

void test_filtrare_lista_dupa_numarul_apartamentului()
{
	Lista_cheltuiala lista_cheltuieli = creare_lista_cheltuiala();
	Cheltuiala cheltuiala_lunara_1 = creare_cheltuiala(12, 222, "apa");
	Cheltuiala cheltuiala_lunara_2 = creare_cheltuiala(12, 342343, "gaz");
	Cheltuiala cheltuiala_lunara_3 = creare_cheltuiala(12, 342342, "incalizre");
	Cheltuiala cheltuiala_lunara_4 = creare_cheltuiala(15, 222, "apa");
	Cheltuiala cheltuiala_lunara_5 = creare_cheltuiala(16, 342343, "apa");
	Cheltuiala cheltuiala_lunara_6 = creare_cheltuiala(17, 342342, "incalizre");
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_1);
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_2);
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_3);
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_4);
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_5);
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_6);
	
	Lista_cheltuiala lista_cheltuiala_filtrata = filtrare_lista_dupa_apartament(&lista_cheltuieli, 12);//filtram lista dupa numarul apartamentului
	assert(lista_cheltuiala_filtrata.lungime_lista_cheltuiala == 3);
	distruge_lista_de_cheltuiala(&lista_cheltuiala_filtrata);

	lista_cheltuiala_filtrata = filtrare_lista_dupa_apartament(&lista_cheltuieli, 44);//listram lista dupa un apartament inexistent
	assert(lista_cheltuiala_filtrata.lungime_lista_cheltuiala == 0);
	distruge_lista_de_cheltuiala(&lista_cheltuiala_filtrata);

	lista_cheltuiala_filtrata = filtrare_lista_dupa_apartament(&lista_cheltuieli, -44);//listram lista dupa un apartament invalid
	assert(lista_cheltuiala_filtrata.lungime_lista_cheltuiala == 6);
	distruge_lista_de_cheltuiala(&lista_cheltuiala_filtrata);

	distruge_lista_de_cheltuiala(&lista_cheltuieli);
}

void test_filtrare_lista_dupa_suma_lunara_a_cheltuielii()
{
	Lista_cheltuiala lista_cheltuieli = creare_lista_cheltuiala();
	Cheltuiala cheltuiala_lunara_1 = creare_cheltuiala(12, 222, "apa");
	Cheltuiala cheltuiala_lunara_2 = creare_cheltuiala(12, 342343, "gaz");
	Cheltuiala cheltuiala_lunara_3 = creare_cheltuiala(12, 222 , "incalizre");
	Cheltuiala cheltuiala_lunara_4 = creare_cheltuiala(15, 222, "apa");
	Cheltuiala cheltuiala_lunara_5 = creare_cheltuiala(16, 342343, "apa");
	Cheltuiala cheltuiala_lunara_6 = creare_cheltuiala(17, 342342, "incalizre");
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_1);
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_2);
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_3);
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_4);
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_5);
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_6);

	Lista_cheltuiala lista_cheltuiala_filtrata = filtrare_lista_dupa_suma_lunara(&lista_cheltuieli, 222);//filtram lista dupa suma lunara a cheltuielii
	assert(lista_cheltuiala_filtrata.lungime_lista_cheltuiala == 3);
	distruge_lista_de_cheltuiala(&lista_cheltuiala_filtrata);
	
	lista_cheltuiala_filtrata = filtrare_lista_dupa_suma_lunara(&lista_cheltuieli, 35976);//listram lista dupa o suma lunara inexistenta
	assert(lista_cheltuiala_filtrata.lungime_lista_cheltuiala == 0);
	distruge_lista_de_cheltuiala(&lista_cheltuiala_filtrata);

	lista_cheltuiala_filtrata = filtrare_lista_dupa_suma_lunara(&lista_cheltuieli, -35976);//listram lista dupa o suma lunara invalida
	assert(lista_cheltuiala_filtrata.lungime_lista_cheltuiala == 6);
	distruge_lista_de_cheltuiala(&lista_cheltuiala_filtrata);

	distruge_lista_de_cheltuiala(&lista_cheltuieli);
}

void test_filtrare_lista_dupa_tipul_de_cheltuiala_lunara()
{
	Lista_cheltuiala lista_cheltuieli = creare_lista_cheltuiala();
	Cheltuiala cheltuiala_lunara_1 = creare_cheltuiala(12, 222, "apa");
	Cheltuiala cheltuiala_lunara_2 = creare_cheltuiala(12, 342343, "gaz");
	Cheltuiala cheltuiala_lunara_3 = creare_cheltuiala(12, 342342, "gaz");
	Cheltuiala cheltuiala_lunara_4 = creare_cheltuiala(15, 222, "apa");
	Cheltuiala cheltuiala_lunara_5 = creare_cheltuiala(16, 342343, "apa");
	Cheltuiala cheltuiala_lunara_6 = creare_cheltuiala(17, 342342, "incalizre");
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_1);
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_2);
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_3);
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_4);
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_5);
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_6);

	Lista_cheltuiala lista_cheltuiala_filtrata = filtrare_lista_dupa_tipul_de_cheeltuiala(&lista_cheltuieli, "apa");//filtram lista dupa tipul de cheltuiala lunara
	assert(lista_cheltuiala_filtrata.lungime_lista_cheltuiala == 3);
	distruge_lista_de_cheltuiala(&lista_cheltuiala_filtrata);

	lista_cheltuiala_filtrata= filtrare_lista_dupa_tipul_de_cheeltuiala(&lista_cheltuieli, "canal");//filtram lista dupa un tip de cheltuiala lunara inexistent
	assert(lista_cheltuiala_filtrata.lungime_lista_cheltuiala == 0);
	distruge_lista_de_cheltuiala(&lista_cheltuiala_filtrata);

	lista_cheltuiala_filtrata = filtrare_lista_dupa_tipul_de_cheeltuiala(&lista_cheltuieli, "ghdyf");//listram lista dupa un tip de cheltuiala lunara invalid
	assert(lista_cheltuiala_filtrata.lungime_lista_cheltuiala == 6);
	distruge_lista_de_cheltuiala(&lista_cheltuiala_filtrata);


	distruge_lista_de_cheltuiala(&lista_cheltuieli);
}

void test_sortare_lista_dupa_tip()
{
	Lista_cheltuiala lista_cheltuieli = creare_lista_cheltuiala();
	Cheltuiala cheltuiala_lunara_1 = creare_cheltuiala(12, 222, "apa");
	Cheltuiala cheltuiala_lunara_2 = creare_cheltuiala(12, 342343, "gaz");
	Cheltuiala cheltuiala_lunara_3 = creare_cheltuiala(12, 342342, "gaz");
	Cheltuiala cheltuiala_lunara_4 = creare_cheltuiala(15, 222, "apa");
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_1);
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_2);
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_3);
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_4);

	Lista_cheltuiala lista_cheltuiala_sortata = sortare_lista_dupa_tip(&lista_cheltuieli);
	Cheltuiala cheltuiala_1 = get_element_din_lista_de_cheltuiala(&lista_cheltuiala_sortata, 0);
	Cheltuiala cheltuiala_2 = get_element_din_lista_de_cheltuiala(&lista_cheltuiala_sortata, 1);
	Cheltuiala cheltuiala_3 = get_element_din_lista_de_cheltuiala(&lista_cheltuiala_sortata, 2);
	Cheltuiala cheltuiala_4 = get_element_din_lista_de_cheltuiala(&lista_cheltuiala_sortata, 3);
	assert(strcmp(cheltuiala_1.tipul_de_cheltuiala_lunara, "apa") == 0);
	assert(strcmp(cheltuiala_2.tipul_de_cheltuiala_lunara, "apa") == 0);
	assert(strcmp(cheltuiala_3.tipul_de_cheltuiala_lunara, "gaz") == 0);
	assert(strcmp(cheltuiala_4.tipul_de_cheltuiala_lunara, "gaz") == 0);

	distruge_lista_de_cheltuiala(&lista_cheltuiala_sortata);
	distruge_lista_de_cheltuiala(&lista_cheltuieli);
}

void test_sortare_dupa_suma()
{
	Lista_cheltuiala lista_cheltuieli = creare_lista_cheltuiala();
	Cheltuiala cheltuiala_lunara_1 = creare_cheltuiala(12, 222, "apa");
	Cheltuiala cheltuiala_lunara_2 = creare_cheltuiala(12, 342343, "gaz");
	Cheltuiala cheltuiala_lunara_3 = creare_cheltuiala(12, 342342, "gaz");
	Cheltuiala cheltuiala_lunara_4 = creare_cheltuiala(15, 222, "apa");
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_1);
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_2);
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_3);
	adauga_cheltuiala_noua(&lista_cheltuieli, cheltuiala_lunara_4);

	Lista_cheltuiala lista_cheltuiala_sortata = sortare_lista_dupa_suma(&lista_cheltuieli,0,comparare_pentru_sortare );
	Cheltuiala cheltuiala_1 = get_element_din_lista_de_cheltuiala(&lista_cheltuiala_sortata, 0);
	Cheltuiala cheltuiala_2 = get_element_din_lista_de_cheltuiala(&lista_cheltuiala_sortata, 1);
	Cheltuiala cheltuiala_3 = get_element_din_lista_de_cheltuiala(&lista_cheltuiala_sortata, 2);
	Cheltuiala cheltuiala_4 = get_element_din_lista_de_cheltuiala(&lista_cheltuiala_sortata, 3);
	assert(cheltuiala_1.suma_lunara_a_cheltuielii == 222);
	assert(cheltuiala_2.suma_lunara_a_cheltuielii == 222);
	assert(cheltuiala_3.suma_lunara_a_cheltuielii == 342342);
	assert(cheltuiala_4.suma_lunara_a_cheltuielii == 342343);

	Lista_cheltuiala lista_cheltuiala_sortata_r =sortare_lista_dupa_suma(&lista_cheltuieli, 1, comparare_pentru_sortare);
	Cheltuiala cheltuiala_1_1 = get_element_din_lista_de_cheltuiala(&lista_cheltuiala_sortata_r, 0);
	Cheltuiala cheltuiala_2_2 = get_element_din_lista_de_cheltuiala(&lista_cheltuiala_sortata_r, 1);
	Cheltuiala cheltuiala_3_3 = get_element_din_lista_de_cheltuiala(&lista_cheltuiala_sortata_r, 2);
	Cheltuiala cheltuiala_4_4 = get_element_din_lista_de_cheltuiala(&lista_cheltuiala_sortata_r, 3);
	assert(cheltuiala_4_4.suma_lunara_a_cheltuielii == 222);
	assert(cheltuiala_3_3.suma_lunara_a_cheltuielii == 222);
	assert(cheltuiala_2_2.suma_lunara_a_cheltuielii == 342342);
	assert(cheltuiala_1_1.suma_lunara_a_cheltuielii == 342343);


	distruge_lista_de_cheltuiala(&lista_cheltuiala_sortata);
	distruge_lista_de_cheltuiala(&lista_cheltuiala_sortata_r);
	distruge_lista_de_cheltuiala(&lista_cheltuieli);
}


void run_teste()
{
	printf("Start teste...\n");
	test_creare_cheltuiala();
	test_creare_lista_cheltuiala();
	test_adauga_cheltuiala_noua_in_lista();
	test_cauta_element_in_lista();
	test_modificarea_unei_cheltuieli();
	test_stergerea_unei_cheluieli_din_vector_dupa_numarul_apartamentului_si_tipul_de_cheltuiala_lunara();
	test_copie_lista();
	test_validarea_unei_cheltuieli();
	test_service_adauga_in_lista();
	test_servvice_modifica_cheltuiala();
	test_service_sterge_cheltuiala();
	test_filtrare_lista_dupa_numarul_apartamentului();
	test_filtrare_lista_dupa_suma_lunara_a_cheltuielii();
	test_filtrare_lista_dupa_tipul_de_cheltuiala_lunara();
	test_sortare_lista_dupa_tip();
	test_sortare_dupa_suma();
	printf("Finish teste...\n");
}

