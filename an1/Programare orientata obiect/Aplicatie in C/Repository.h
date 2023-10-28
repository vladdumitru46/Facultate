#ifndef REPOSITORY_H
#define REPOSITORY_H
#pragma once
#define _CRTDBG_MAP_ALLOC
#include <stdlib.h>
#include <crtdbg.h>
#include <string.h>
#include "Creare_cheltuiala.h"

typedef Cheltuiala Tipul_de_elemente;

typedef struct {
	Tipul_de_elemente* elemete_cheltuiala;
	int lungime_lista_cheltuiala;
	int capacitate_lista_cheltuiala;
}Lista_cheltuiala;

Lista_cheltuiala creare_lista_cheltuiala();

void distruge_lista_de_cheltuiala(Lista_cheltuiala* lista);

Tipul_de_elemente get_element_din_lista_de_cheltuiala(Lista_cheltuiala* lista, int pozitia_cautata);

Tipul_de_elemente setaza_cheltuiala_noua(Lista_cheltuiala* lista_cheltuieli, int pozitie_element, Tipul_de_elemente cheltuiala_noua);

void adauga_cheltuiala_noua(Lista_cheltuiala* lista, Tipul_de_elemente cheltuiala);
/*
	functie care adauga o cheltuiala noua in lista de cheltuieli
	input:  lista: o lista de cheltuieli
			cheltuiala: elementul pe care doresc sa il adaug in lista de cheltuiala
			
	output: lungimea listei de cheltuieli
*/
int cauta_cheltuiala_lunara(Lista_cheltuiala* lista_cheltuielii, int numarul_apartamentului_cautat, char* tipul_de_cheltuiala_lunara_cautat);

void modifica_tipul_si_suma_unei_cheltuieli_lunare(Lista_cheltuiala* lista_cheltuieli, int numar_apartament_cautat, float suma_cheltuielii_lunare_modificate, char* tipul_de_cheltuiala_liunara_cautat, char* tipul_de_cheltuiala_lunara_modificat);
/*
	functie care modifica o cheltuiala din lista
	input: lista_cheltuieli: lista de cheltuieli
			lungime_lista_de_cheltuilei: lungimea listei de cheltuieli
			numar_apartament: numarul apartamentului cautat, pe care doresc sa il modific
			tipul_de_cheltuiala_lunara_cautat: tipul de cheltuiala dupa care caut in lista de cheltuieli
			tipul_de_cheltuiala_lunara_modifcat: tipul de cheltuiala modifcat
	output: nimic, daca a modificat cu succes
*/
Tipul_de_elemente sterge_cheltuiala_lunara(Lista_cheltuiala* lista_cheltuieli, int pozitia_unde_se_afla_elementul_pe_care_vreau_sa_il_sterg_din_vector);
// functie care sterge o cheltuiala
void sterge_cheltuiala_lunara_dupa_numarul_apartamentului_si_tipul_de_cheltuiala(Lista_cheltuiala* lista_cheltuieli, int numarul_apartamentului, char* tipul_de_cheluiala_lunara);
/*
	functie care sterge o cheltuiala dupa numarul apartamentului si tipul de cheltuiala
	input:	lista_de_cheltuieli: lista de chelutieli
			lungime_lista_de_cheltuieli: lungimea listei de chelutieli
			numarul_apartamentului: numarul aparta,entului cautat
			tipul_de_cheltuiala: tipul de cheltuiala cautat pentru stergere
	output: lungimea listei de cheltuieli
*/
Lista_cheltuiala creare_cpoie_pentru_lista(Lista_cheltuiala* lista_cheltuieli);
#endif
