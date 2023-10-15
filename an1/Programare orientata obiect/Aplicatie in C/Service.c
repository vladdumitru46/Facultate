#include "Service.h"

int service_adauga_cheltuiala_noua(Lista_cheltuiala* lista_cheltuieli, int numar_apartament, float suma_lunara_a_cheluielii, char* tipul_de_cheltuiala_lunara)
{
	int cheltuiala_lunara_invalida = 1;
	int cheltuiala_adaugata_cu_succes = 0;
	Cheltuiala chaltuiala_noua = creare_cheltuiala(numar_apartament, suma_lunara_a_cheluielii, tipul_de_cheltuiala_lunara);
	if (validare_cheltuiala_noua(chaltuiala_noua) == 0)
	{
		adauga_cheltuiala_noua(lista_cheltuieli, chaltuiala_noua);
		return cheltuiala_adaugata_cu_succes;
	}
	else
	{
		destroy_cheltuiala(&chaltuiala_noua);
		return cheltuiala_lunara_invalida;
	}
}

int service_modifica_tipul_si_suma_unei_cheltuieli_lunare(Lista_cheltuiala* lista_cheltuieli, int numar_apartament, float suma_lunara_a_cheluielii, char* tipul_de_cheltuiala_lunara_cautat, char* tipul_cheltuiala_lunara_mdoficat)
{
	int cheltuiala_lunara_inexistenta = 1;
	int cheltuiala_modificata_cu_succes = 0;
	if (cauta_cheltuiala_lunara(lista_cheltuieli, numar_apartament, tipul_de_cheltuiala_lunara_cautat) != -1)
	{
		modifica_tipul_si_suma_unei_cheltuieli_lunare(lista_cheltuieli, numar_apartament, suma_lunara_a_cheluielii, tipul_de_cheltuiala_lunara_cautat, tipul_cheltuiala_lunara_mdoficat);
		return cheltuiala_modificata_cu_succes;
	}
	else
		return cheltuiala_lunara_inexistenta;
		
}

int service_stergere_cheltuiala_dupa_numarul_apartamentului_si_dupa_tipul_de_cheltuiala_lunara(Lista_cheltuiala* lista_cheltuieli, int numarul_apartamentului, char* tipul_de_cheluiala_lunara)
{
	int cheltuiala_lunara_inexistenta = 1;
	int cheltuiala_stearsa_cu_succes = 0;
	if (cauta_cheltuiala_lunara(lista_cheltuieli, numarul_apartamentului, tipul_de_cheluiala_lunara) != -1)
	{
		sterge_cheltuiala_lunara_dupa_numarul_apartamentului_si_tipul_de_cheltuiala(lista_cheltuieli, numarul_apartamentului, tipul_de_cheluiala_lunara);
		return cheltuiala_stearsa_cu_succes;
	}
	else
		return cheltuiala_lunara_inexistenta;
}



Lista_cheltuiala filtrare_lista_dupa_apartament(Lista_cheltuiala* lista_cheltuieli, int numarul_apartamentului)
{
	if (numarul_apartamentului > 0)
	{
		Lista_cheltuiala lista_filtrata = creare_lista_cheltuiala();
		for (int i = 0; i < lista_cheltuieli->lungime_lista_cheltuiala; i++)
		{
			Cheltuiala cheltuiala_cautata = get_element_din_lista_de_cheltuiala(lista_cheltuieli, i);
			if (cheltuiala_cautata.numarul_apartamentului == numarul_apartamentului)
				adauga_cheltuiala_noua(&lista_filtrata, creare_cheltuiala(cheltuiala_cautata.numarul_apartamentului,cheltuiala_cautata.suma_lunara_a_cheltuielii,cheltuiala_cautata.tipul_de_cheltuiala_lunara));
		}
		return lista_filtrata;
	}
	else
		return creare_cpoie_pentru_lista(lista_cheltuieli);
}

Lista_cheltuiala filtrare_lista_dupa_suma_lunara(Lista_cheltuiala* lista_cheltuieli, float suma_lunara_a_cheltuielii)
{
	if (suma_lunara_a_cheltuielii > 0)
	{
		Lista_cheltuiala lista_filtrata = creare_lista_cheltuiala();
		for (int i = 0; i < lista_cheltuieli->lungime_lista_cheltuiala; i++)
		{
			Cheltuiala cheltuiala_cautata = get_element_din_lista_de_cheltuiala(lista_cheltuieli, i);
			if (cheltuiala_cautata.suma_lunara_a_cheltuielii == suma_lunara_a_cheltuielii)
				adauga_cheltuiala_noua(&lista_filtrata, creare_cheltuiala(cheltuiala_cautata.numarul_apartamentului,cheltuiala_cautata.suma_lunara_a_cheltuielii,cheltuiala_cautata.tipul_de_cheltuiala_lunara));
		}
		return lista_filtrata;
	}
	else
		return creare_cpoie_pentru_lista(lista_cheltuieli);
}

Lista_cheltuiala filtrare_lista_dupa_tipul_de_cheeltuiala(Lista_cheltuiala* lista_cheltuieli, char* tipul_de_cheltuiala_lunara)
{
	if (strcmp(tipul_de_cheltuiala_lunara, "apa") == 0 || strcmp(tipul_de_cheltuiala_lunara, "gaz") == 0 || strcmp(tipul_de_cheltuiala_lunara, "incalzire") == 0 || strcmp(tipul_de_cheltuiala_lunara, "canal") == 0)
	{
		Lista_cheltuiala lista_filtrata = creare_lista_cheltuiala();
		for (int i = 0; i < lista_cheltuieli->lungime_lista_cheltuiala; i++)
		{
			Cheltuiala cheltuiala_cautata = get_element_din_lista_de_cheltuiala(lista_cheltuieli, i);
			if (strcmp(cheltuiala_cautata.tipul_de_cheltuiala_lunara, tipul_de_cheltuiala_lunara) == 0)
				adauga_cheltuiala_noua(&lista_filtrata, creare_cheltuiala(cheltuiala_cautata.numarul_apartamentului,cheltuiala_cautata.suma_lunara_a_cheltuielii,cheltuiala_cautata.tipul_de_cheltuiala_lunara));
		}
		return lista_filtrata;
	}
	else
		return creare_cpoie_pentru_lista(lista_cheltuieli);
}


Lista_cheltuiala sortare_lista_dupa_tip(Lista_cheltuiala* lista_cheltuieli)
{
	for (int i = 0; i < lista_cheltuieli->lungime_lista_cheltuiala - 1; i++)
	{
		for (int j = 0; j < lista_cheltuieli->lungime_lista_cheltuiala-i-1; j++)
		{
			Cheltuiala cheltuiala_1 = get_element_din_lista_de_cheltuiala(lista_cheltuieli, j);
			Cheltuiala cheltuiala_2 = get_element_din_lista_de_cheltuiala(lista_cheltuieli, j + 1);
			if (strcmp(cheltuiala_1.tipul_de_cheltuiala_lunara, cheltuiala_2.tipul_de_cheltuiala_lunara) > 0)
			{
				Cheltuiala aux = lista_cheltuieli->elemete_cheltuiala[j];
				lista_cheltuieli->elemete_cheltuiala[j] = lista_cheltuieli->elemete_cheltuiala[j + 1];
				lista_cheltuieli->elemete_cheltuiala[j + 1] = aux;
			}
		}
	}
	return creare_cpoie_pentru_lista(lista_cheltuieli);
}
int comparare_pentru_sortare(Cheltuiala cheltuiala_1, Cheltuiala cheltuiala_2)
{
	if (cheltuiala_1.suma_lunara_a_cheltuielii >= cheltuiala_2.suma_lunara_a_cheltuielii)
		return 1;
	return 0;
}

Lista_cheltuiala sortare_lista_dupa_suma(Lista_cheltuiala* lista_cheltuieli, int reversed, int (*comparare_pentru_sortare)(Cheltuiala , Cheltuiala ))
{
	for (int i = 0; i < lista_cheltuieli->lungime_lista_cheltuiala - 1; i++)
	{
		for (int j = 0; j < lista_cheltuieli->lungime_lista_cheltuiala - i - 1; j++)
		{
			Cheltuiala cheltuiala_1 = get_element_din_lista_de_cheltuiala(lista_cheltuieli, j);
			Cheltuiala cheltuiala_2 = get_element_din_lista_de_cheltuiala(lista_cheltuieli, j + 1);
			if (reversed == 0)
			{
				if (comparare_pentru_sortare(cheltuiala_1,cheltuiala_2)==1)
				{
					Cheltuiala aux = lista_cheltuieli->elemete_cheltuiala[j];
					lista_cheltuieli->elemete_cheltuiala[j] = lista_cheltuieli->elemete_cheltuiala[j + 1];
					lista_cheltuieli->elemete_cheltuiala[j + 1] = aux;
				}
			}
			else {
				if (comparare_pentru_sortare(cheltuiala_1, cheltuiala_2) == 0)
				{
					Cheltuiala aux = lista_cheltuieli->elemete_cheltuiala[j];
					lista_cheltuieli->elemete_cheltuiala[j] = lista_cheltuieli->elemete_cheltuiala[j + 1];
					lista_cheltuieli->elemete_cheltuiala[j + 1] = aux;
				}
			}
		}
	}
	return creare_cpoie_pentru_lista(lista_cheltuieli);
}
