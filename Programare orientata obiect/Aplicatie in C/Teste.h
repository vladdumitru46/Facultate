
#ifndef TESTE_H
#define TESTE_H

#include "Creare_cheltuiala.h"
#include "Repository.h"
#include "Validare.h"
#include "Service.h"
#include <assert.h>
#include <string.h>


void test_creare_cheltuiala();
void test_creare_lista_cheltuiala();
void test_adauga_cheltuiala_noua_in_lista();
void test_cauta_element_in_lista();
void test_modificarea_unei_cheltuieli();
void test_stergerea_unei_cheluieli_din_vector_dupa_numarul_apartamentului_si_tipul_de_cheltuiala_lunara();
void test_copie_lista();
void test_validarea_unei_cheltuieli();
void test_service_adauga_in_lista();
void test_servvice_modifica_cheltuiala();
void test_service_sterge_cheltuiala();
void test_filtrare_lista_dupa_numarul_apartamentului();
void test_filtrare_lista_dupa_suma_lunara_a_cheltuielii();
void test_filtrare_lista_dupa_tipul_de_cheltuiala_lunara();
void test_sortare_lista_dupa_tip();
void test_sortare_dupa_suma();
void run_teste();

#endif 
