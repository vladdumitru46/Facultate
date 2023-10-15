#include "Repository.h"
#include "Validare.h"


int service_adauga_cheltuiala_noua(Lista_cheltuiala* lista_cheltuieli, int numar_apartament, float suma_lunara_a_cheluielii, char* tipul_de_cheltuiala_lunara);
/*
	functie care adauga o cheltuiala noua in lista de cheltuieli
	input: lista_cheltuieli: o lista de cheltuieli
			lungime_lista_decheltieli: lungimea llistei de cheltuilei
			numar apartament: numarul apartamentului
			suma_lunara_a_cheltuielii: pretul cheltuielii lunare
			tipul_de_cheltuiala_lunara: tipul de cheltuiala(apa, gaz, canal, incalizre)
	output: lungimea listei de cheltuieli
*/
int service_modifica_tipul_si_suma_unei_cheltuieli_lunare(Lista_cheltuiala* lista_cheltuieli, int numar_apartament, float suma_lunara_a_cheluielii, char* tipul_de_cheltuiala_lunara_cautat, char* tipul_cheltuiala_lunara_mdoficat);
/*
	functie care modifica o cheltuiala din lista
	input: lista_cheltuieli: lista de cheltuieli
			lungime_lista_de_cheltuilei: lungimea listei de cheltuieli
			numar_apartament: numarul apartamentului cautat, pe care doresc sa il modific
			suma_lunara_a_cheltuielii: suma noua, modificata a apartemtntului
			tipul_de_cheltuiala_lunara_cautat: tipul de cheltuiala dupa care caut in lista de cheltuieli
			tipul_de_cheltuiala_lunara_modifcat: tipul de cheltuiala modifcat
	output: nimic, daca a modificat cu succes
*/
int service_stergere_cheltuiala_dupa_numarul_apartamentului_si_dupa_tipul_de_cheltuiala_lunara(Lista_cheltuiala* lista_cheltuieli, int numarul_apartamentului, char* tipul_de_cheluiala_lunara );
/*
	functie care sterge o cheltuiala dupa numarul apartamentului si tipul de cheltuiala
	input:	lista_de_cheltuieli: lista de chelutieli
			lungime_lista_de_cheltuieli: lungimea listei de chelutieli
			numarul_apartamentului: numarul aparta,entului cautat
			tipul_de_cheltuiala: tipul de cheltuiala cautat pentru stergere
	output: lungimea listei de cheltuieli
*/


Lista_cheltuiala filtrare_lista_dupa_apartament(Lista_cheltuiala* lista_cheltuieli, int numarul_apartamentului);
Lista_cheltuiala filtrare_lista_dupa_suma_lunara(Lista_cheltuiala* lista_cheltuieli, float suma_lunara_a_cheltuielii);
Lista_cheltuiala filtrare_lista_dupa_tipul_de_cheeltuiala(Lista_cheltuiala* lista_cheltuieli, char* tipul_de_cheltuiala_lunara);
Lista_cheltuiala sortare_lista_dupa_tip(Lista_cheltuiala* lista_cheltuieli);
int comparare_pentru_sortare(Cheltuiala cheltuiala_1, Cheltuiala cheltuiala_2);
Lista_cheltuiala sortare_lista_dupa_suma(Lista_cheltuiala* lista_cheltuieli, int reversed, int (comparare_pentru_sortare)(Cheltuiala, Cheltuiala));
