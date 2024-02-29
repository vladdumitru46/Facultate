#include "Repository.h"
#include <stdlib.h>



Lista_cheltuiala creare_lista_cheltuiala()
{
	Lista_cheltuiala lista_cheltuieli;
	lista_cheltuieli.capacitate_lista_cheltuiala = 2;
	lista_cheltuieli.lungime_lista_cheltuiala = 0;
	lista_cheltuieli.elemete_cheltuiala = malloc(sizeof(Tipul_de_elemente) * lista_cheltuieli.capacitate_lista_cheltuiala);
	return lista_cheltuieli;
}

void distruge_lista_de_cheltuiala(Lista_cheltuiala* lista)
{
	for (int i = 0; i < lista->lungime_lista_cheltuiala; i++)
	{
		Tipul_de_elemente element_lista_cheltuiala = get_element_din_lista_de_cheltuiala(lista, i);
		destroy_cheltuiala(&element_lista_cheltuiala);
	}
	lista->lungime_lista_cheltuiala = 0;
	free(lista->elemete_cheltuiala);
}

Tipul_de_elemente get_element_din_lista_de_cheltuiala(Lista_cheltuiala* lista, int pozitia_cautata)
{
	return lista->elemete_cheltuiala[pozitia_cautata];
}

Tipul_de_elemente setaza_cheltuiala_noua(Lista_cheltuiala* lista_cheltuieli, int pozitie_element, Tipul_de_elemente cheltuiala_noua)
{
	Tipul_de_elemente cheltuiala_noua_adaugata = lista_cheltuieli->elemete_cheltuiala[pozitie_element];
	lista_cheltuieli->elemete_cheltuiala[pozitie_element] = cheltuiala_noua;
	return cheltuiala_noua_adaugata;
}

void adauga_cheltuiala_noua(Lista_cheltuiala* lista, Tipul_de_elemente cheltuiala)
{
	if (lista->lungime_lista_cheltuiala == lista->capacitate_lista_cheltuiala)
	{
		int capacitate_noua = lista->capacitate_lista_cheltuiala * 2;
		Tipul_de_elemente* elemente_auxiliare_noi = malloc(sizeof(Tipul_de_elemente) * capacitate_noua);
		for (int i = 0; i < lista->lungime_lista_cheltuiala; i++)
		{
			elemente_auxiliare_noi[i] = lista->elemete_cheltuiala[i];
		}
		free(lista->elemete_cheltuiala);
		lista->elemete_cheltuiala = elemente_auxiliare_noi;
		lista->capacitate_lista_cheltuiala = capacitate_noua;
	}

	lista->elemete_cheltuiala[lista->lungime_lista_cheltuiala] = cheltuiala;
	lista->lungime_lista_cheltuiala += 1;
}



int cauta_cheltuiala_lunara(Lista_cheltuiala* lista_cheltuielii, int numarul_apartamentului_cautat, char* tipul_de_cheltuiala_lunara_cautat)
{
	int pozitia_pe_care_vreau_sa_o_aflu = -1;
	for (int i = 0; i < lista_cheltuielii->lungime_lista_cheltuiala; i++)
	{
		if (lista_cheltuielii->elemete_cheltuiala[i].numarul_apartamentului == numarul_apartamentului_cautat)
			if (strcmp(lista_cheltuielii->elemete_cheltuiala[i].tipul_de_cheltuiala_lunara, tipul_de_cheltuiala_lunara_cautat) == 0)
			{
				pozitia_pe_care_vreau_sa_o_aflu = i;
				break;
			}
	}
	return pozitia_pe_care_vreau_sa_o_aflu;
}

void modifica_tipul_si_suma_unei_cheltuieli_lunare(Lista_cheltuiala* lista_cheltuieli, int numar_apartament_cautat, float suma_cheltuielii_lunare_modificate, char* tipul_de_cheltuiala_liunara_cautat, char* tipul_de_cheltuiala_lunara_modificat)
{
	int pozitita_pe_care_se_afla_elementul_pe_care_vreau_sa_il_modific = cauta_cheltuiala_lunara(lista_cheltuieli, numar_apartament_cautat, tipul_de_cheltuiala_liunara_cautat);
	if (pozitita_pe_care_se_afla_elementul_pe_care_vreau_sa_il_modific != -1)
	{
		Cheltuiala cheltuiala_lunara = creare_cheltuiala(numar_apartament_cautat, suma_cheltuielii_lunare_modificate, tipul_de_cheltuiala_lunara_modificat);
		Cheltuiala cheltuiala_lunara_originala = get_element_din_lista_de_cheltuiala(lista_cheltuieli, pozitita_pe_care_se_afla_elementul_pe_care_vreau_sa_il_modific);
		destroy_cheltuiala(&cheltuiala_lunara_originala);
		setaza_cheltuiala_noua(lista_cheltuieli, pozitita_pe_care_se_afla_elementul_pe_care_vreau_sa_il_modific, cheltuiala_lunara);
	}
	
}

Tipul_de_elemente sterge_cheltuiala_lunara(Lista_cheltuiala* lista_cheltuieli, int pozitia_unde_se_afla_elementul_pe_care_vreau_sa_il_sterg_din_vector)
{
	Cheltuiala cheltuiala_stearsa = get_element_din_lista_de_cheltuiala(lista_cheltuieli, pozitia_unde_se_afla_elementul_pe_care_vreau_sa_il_sterg_din_vector);
	for (int i = pozitia_unde_se_afla_elementul_pe_care_vreau_sa_il_sterg_din_vector; i < lista_cheltuieli->lungime_lista_cheltuiala - 1; i++)
	{
		lista_cheltuieli->elemete_cheltuiala[i] = lista_cheltuieli->elemete_cheltuiala[i + 1];
	}
	//lista_cheltuieli->lungime_lista_cheltuiala = lista_cheltuieli->lungime_lista_cheltuiala - 1;
	return cheltuiala_stearsa;
}

void sterge_cheltuiala_lunara_dupa_numarul_apartamentului_si_tipul_de_cheltuiala(Lista_cheltuiala* lista_cheltuieli, int numarul_apartamentului, char* tipul_de_cheluiala_lunara)
{
	int pozitie_cautata_pentru_elementul_pe_care_vreau_sa_il_sterg = cauta_cheltuiala_lunara(lista_cheltuieli, numarul_apartamentului, tipul_de_cheluiala_lunara);
	if (pozitie_cautata_pentru_elementul_pe_care_vreau_sa_il_sterg != -1)
	{
		Cheltuiala cheltuiala_stearsa=sterge_cheltuiala_lunara(lista_cheltuieli, pozitie_cautata_pentru_elementul_pe_care_vreau_sa_il_sterg);
		lista_cheltuieli->lungime_lista_cheltuiala--;
		destroy_cheltuiala(&cheltuiala_stearsa);

	}
}

Lista_cheltuiala creare_cpoie_pentru_lista(Lista_cheltuiala* lista_cheltuieli)
{
	Lista_cheltuiala copie_lista = creare_lista_cheltuiala();
	for (int i = 0; i < lista_cheltuieli->lungime_lista_cheltuiala; i++)
	{
		Cheltuiala cheltuiala_noua = get_element_din_lista_de_cheltuiala(lista_cheltuieli, i);
		adauga_cheltuiala_noua(&copie_lista, copie_cheltuiala(cheltuiala_noua));
	}
	return copie_lista;
}




