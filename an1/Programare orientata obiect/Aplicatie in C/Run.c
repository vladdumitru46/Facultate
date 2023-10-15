#include "Run.h"

void meniu()
{
	printf("1.Daca doriti sa daugati o cheltuiala noua, screti '1'\n");
	printf("2.Daca doriti sa modificati o cheltuiala deja existanta, scrieti '2'\n");
	printf("3. Daca doriti sa stergeti o cheltuiala dupa numarul apartamentului si tipul de cheltuiala lunara, scrieti '3'\n");
	printf("4. Daca doriti sa filtrati lista dupa numarul apartamentului, apasati '4'\n");
	printf("5. Daca doriti sa filtrati lista dupa suma lunara a cheltuielii, apasati '5'\n");
	printf("6. Daca doriti sa filtrati lista dupa tipul de cheltuiala lunara, apasati '6'\n");
	printf("7. Daca doriti sa sortati lista dupa tipul de cheltuiala lunara, apasati '7'\n");
	printf("8. Daca doriti sa sortati lista dupa suma lunara a cheltuielii, apasati '8\n");
	printf("9. Daca doriti sa afisati lista de cheltuieli, scrieti, '9'\n");
	printf("Daca doriti sa iesiti din aplicatie, scrieti 'exit'\n");
}

void print_lista_cheltuieli(Lista_cheltuiala* lista_cheltuieli)
{
	for (int i = 0; i < lista_cheltuieli->lungime_lista_cheltuiala; i++)
	{
		printf("Numarul aparatemntului este:%d ",lista_cheltuieli->elemete_cheltuiala[i].numarul_apartamentului);
		printf("Suma cheltuielii lunare a apartamentului este:%f ", lista_cheltuieli->elemete_cheltuiala[i].suma_lunara_a_cheltuielii);
		printf("Tipul de cheltuiala lunara este: %s\n", lista_cheltuieli->elemete_cheltuiala[i].tipul_de_cheltuiala_lunara);
	}
}

void run()
{
	Lista_cheltuiala lista_cheltuieli = creare_lista_cheltuiala();
	char* adaugare_element_in_lista = "1";
	char* modificare_element_din_lista = "2";
	char* stergere_element_din_lista = "3";
	char* filtreaza_lista_dupa_numarul_apartamentului = "4";
	char* filtreaza_lista_dupa_suma_lunara_a_cheltuielii = "5";
	char* filtreaza_lista_dupa_tipul_de_cheltuiala = "6";
	char* sorteaza_lista_dupa_tip = "7";
	char* sorteaza_lista_dupa_suma = "8";
	char* print_lista = "9";
	int ok = 1;
	while (ok)
	{
		meniu();
		char comanda[256];
		printf(">>>");
		scanf_s("%s", comanda, 256);
		if (strcmp(comanda, adaugare_element_in_lista) == 0)
		{
			int numar_apartament = 0;
			float suma_cheltuiala_lunara = 0;
			char tipul_de_cheltuiala_lunara[20]="";
			printf("Introduceti numarul apartamentului pe care doriti sa il adaugati:");
			scanf_s("%d", &numar_apartament);
			printf("Introduceti suma cheltuielii lunare:");
			scanf_s("%f", &suma_cheltuiala_lunara);
			printf("Introduceti tipul de cheltuiala(apa, gaz, incalizre, canal):");
			scanf_s("%s", tipul_de_cheltuiala_lunara, 20);
			if (service_adauga_cheltuiala_noua(&lista_cheltuieli, numar_apartament, suma_cheltuiala_lunara, tipul_de_cheltuiala_lunara) == 0)
				printf("Cheltuiala adaugata cu succes\n");
			else
				printf("Cheltuiala invalida!\n");
		}
		if (strcmp(comanda, modificare_element_din_lista) == 0)
		{
			int numar_apartament = 0;
			float suma_cheltuiala_lunara = 0;
			char tipul_de_cheltuiala_lunara_cautat[20]="", tipul_de_cheltuiala_lunara_modificat[20]="";
			printf("Introduceti numarul apartamentului pe care doriti sa il cautati pentru modificare:");
			scanf_s("%d", &numar_apartament);
			printf("Introduceti tipul de cheltuiala dupa care doriti sa il modificati(apa, gaz, incalizre, canal):");
			scanf_s("%s", &tipul_de_cheltuiala_lunara_cautat, 20);
			printf("Introduceti suma cheltuielii lunare modificata:");
			scanf_s("%f", &suma_cheltuiala_lunara);
			printf("Introduceti tipul de cheltuiala modificata(apa, gaz, incalizre, canal):");
			scanf_s("%s", &tipul_de_cheltuiala_lunara_modificat, 20);
			if (service_modifica_tipul_si_suma_unei_cheltuieli_lunare(&lista_cheltuieli, numar_apartament, suma_cheltuiala_lunara, tipul_de_cheltuiala_lunara_cautat, tipul_de_cheltuiala_lunara_modificat)==0)
				printf("Cheltuiala modificata cu succes\n");
			else
				printf("Cheltuiala inexistenta!\n");
		}
		if (strcmp(comanda, stergere_element_din_lista) == 0)
		{
			int numar_apartament = 0;
			char tipul_de_cheltiala_lunara[20]="";
			printf("Introducti numarul apartamentului pe care doriti sa il stergeti:");
			scanf_s("%d", &numar_apartament);
			printf("Introduceti tipul de cheltuiala lunara dupa care doriti sa steregti:");
			scanf_s("%s", tipul_de_cheltiala_lunara, 20);
			if (service_stergere_cheltuiala_dupa_numarul_apartamentului_si_dupa_tipul_de_cheltuiala_lunara(&lista_cheltuieli, numar_apartament, tipul_de_cheltiala_lunara) == 0)
				printf("Cheltuiala stearsa cu succes");
			else printf("Cheltuiala inexistenta!\n");
		}
		if (strcmp(comanda, filtreaza_lista_dupa_numarul_apartamentului) == 0)
		{
			int numar_apartament = 0;
			printf("Numarul apartamentului dupa care doriti sa filtrati:");
			scanf_s("%d", &numar_apartament);
			Lista_cheltuiala lista_cheltuiala_filtrata = filtrare_lista_dupa_apartament(&lista_cheltuieli, numar_apartament);
			print_lista_cheltuieli(&lista_cheltuiala_filtrata);
			distruge_lista_de_cheltuiala(&lista_cheltuiala_filtrata);
		}
		if (strcmp(comanda, filtreaza_lista_dupa_suma_lunara_a_cheltuielii) == 0)
		{
			float suma_lunara_a_cheltuielii = 0;
			printf("Suma lunara a cheltuielii dupa care doriti sa filtrati:");
			scanf_s("%f", &suma_lunara_a_cheltuielii);
			Lista_cheltuiala lista_cheltuiala_filtrata = filtrare_lista_dupa_suma_lunara(&lista_cheltuieli, suma_lunara_a_cheltuielii);
			print_lista_cheltuieli(&lista_cheltuiala_filtrata);
			distruge_lista_de_cheltuiala(&lista_cheltuiala_filtrata);
		}
		if(strcmp(comanda,filtreaza_lista_dupa_tipul_de_cheltuiala)==0)
		{
			char tip_de_cheltuiala[20] = "";
			printf("Numarul apartamentului dupa care doriti sa filtrati:");
			scanf_s("%s", tip_de_cheltuiala, 20);
			Lista_cheltuiala lista_cheltuiala_filtrata = filtrare_lista_dupa_tipul_de_cheeltuiala(&lista_cheltuieli, tip_de_cheltuiala);
			print_lista_cheltuieli(&lista_cheltuiala_filtrata);
			distruge_lista_de_cheltuiala(&lista_cheltuiala_filtrata);
		}
		if (strcmp(comanda, sorteaza_lista_dupa_tip) == 0)
		{
			Lista_cheltuiala lista_sortata_dupa_tip = sortare_lista_dupa_tip(&lista_cheltuieli);
			print_lista_cheltuieli(&lista_sortata_dupa_tip);
			distruge_lista_de_cheltuiala(&lista_sortata_dupa_tip);
		}
		if (strcmp(comanda, sorteaza_lista_dupa_suma) == 0)
		{
			int reversed = 0;
			printf("sortare crescaotoare(0) sau descrescatoare(1)");
			scanf_s("%d", &reversed);
			Lista_cheltuiala lista_sortata_dupa_suma = sortare_lista_dupa_suma(&lista_cheltuieli,reversed, comparare_pentru_sortare);
			print_lista_cheltuieli(&lista_sortata_dupa_suma);
			distruge_lista_de_cheltuiala(&lista_sortata_dupa_suma);
		}
		if (strcmp(comanda, print_lista) == 0)
			print_lista_cheltuieli(&lista_cheltuieli);
		if (strcmp(comanda, "exit") == 0)
		{
			distruge_lista_de_cheltuiala(&lista_cheltuieli);
			break;
		}
		else
			continue;
	}
}

