#include "UI.h"
#include <iostream>
using namespace std;


void ConsoleUI::meniu()
{
	cout << "Farmacie"<<endl<<endl;
	cout << "1. Actiuni pentru lista de medicamente\n";
	cout << "2. Actiuni pentru reteta de medicamente\n";
	cout << "Daca doriti sa inchideti aplicatia, scrieti 'exit'\n";
}

void ConsoleUI::meniu_medicament()
{
	cout << "1. Daca doriti sa adaugati un medicament in lista, scroeti '1'\n";
	cout << "2. Daca doriti sa cautati un medicament in lista, scrieti '2'\n";
	cout << "3. Daca doriti sa stergeti un medicament din lista, scrieti '3'\n";
	cout << "4. Daca doriti sa modificati un medicament din lisra, scrieti '4'\n";
	cout << "5. Daca doriti sa filtrati lista dupa pret, scrieti '5'\n";
	cout << "6. Daca doriti sa filtrati lista dupa substnta activa, sctieti  '6'\n";
	cout << "7. Daca doriti sa sortati lista dupa denumire, scroeti '7'\n";
	cout << "8. Daca doriti sa sortati lista dupa producator, scrieti '8'\n";
	cout << "9. Daca doriti sa scortati lista dupa substanta activa si pret, scrieti '9'\n";
	cout << "10. Daca doriti sa afisati lista de medicamente, scrieti '10'\n";
	cout << "11. Daca doriti sa iesiti din meniul de medicamente, scrieti '11'\n";
}

void ConsoleUI::meniu_reteta()
{
	cout << "1. Daca doriti sa creeati o reteta cu medicamente din lista de medicamente, scrieti '1'\n";
	cout << "2. Daca doriti sa goliti reteta de medicamente, scrieti '2'\n";
	cout << "3. Daca doriti sa adaugati medicamente random in reteta, scrieti '3'\n";
	cout << "4. Daca doriti sa afisati reteta, scrieti '4'\n";
	cout << "5. Daca doriti sa iesiti din meniul pentru reteta, scrieti '5'\n";
	
}


void ConsoleUI::afiseaza_toate_medicamentele(vector<Medicament> toate_medicamentele)
{
	for (const auto& medicament : toate_medicamentele)
	{
		cout << "Denumire: " << medicament.get_denumire() << " Pret: " << medicament.get_pret() << " Producator: " << medicament.get_producator() << " Substanta activa: " << medicament.get_substanta_activa() << endl;
	}
	cout << endl;
}



void ConsoleUI::run()
{
	string comanda;

	while (1)
	{
		meniu();
		cout << ">>>";
		cin >> comanda;
		if (comanda == "1")
		{
			while (1)
			{
				meniu_medicament();
				string comanda_medicmanet;
				cout << ">>>";
				cin >> comanda_medicmanet;
				if (comanda_medicmanet == "1")
				{
					string denumire = "", producator = "", substanta_activa = "";
					double pret;
					try {
						cout << "Denumire medicamentului: ";
						cin >> denumire;
						cout << "Pretul medicamentului: ";
						cin >> pret;
						cout << "Producatorul medicamentului: ";
						cin >> producator;
						cout << "Substanta activa a medicamentului:";
						cin >> substanta_activa;
						service_medicament.service_adauga_medicament_in_lista(denumire, pret, producator, substanta_activa);
					}
					catch (Validare& exceptii) {
						cout << exceptii.get_stringul_de_erori() << endl;
					}
					catch (Exceptie_de_adaugare& exceptie)
					{
						cout << exceptie.get_mesaje_de_erori() << endl;
					}
				}
				else if (comanda_medicmanet == "2")
				{
					string denumire, producator;
					cout << "Denumire medicamentului pe care doriti sa il cautati: ";
					cin >> denumire;
					try {
						Medicament medicament_gasit = service_medicament.service_cauta_un_medicament_in_lista_de_medicamente(denumire);
						cout << "Denumire: " << medicament_gasit.get_denumire() << " Pret: " << medicament_gasit.get_pret() << " Producator: " << medicament_gasit.get_producator() << " Substanta activa: " << medicament_gasit.get_substanta_activa() << endl << endl;
					}
					catch (Exceptie_de_cautare& erori) {
						cout << erori.get_mesaje_de_erori();
					}
				}
				else if (comanda_medicmanet == "3")
				{
					string denumire, producator;
					cout << "Denumire medicamentului pe care doriti sa il stergeti: ";
					cin >> denumire;
					try {
						service_medicament.service_setrge_medicament_din_lista(denumire);
					}
					catch (Exceptie_de_stergere& exceptie) {
						cout << exceptie.get_mesaje_de_erori() << endl;
					}
				}
				else if (comanda_medicmanet == "4")
				{
					string denumire, producator, substanta_activa;
					double pret;
					cout << "Denumire medicamentului pe care doriti sa il modificati: ";
					cin >> denumire;
					cout << "Producatorul medicamentului pe care doriti sa il modificati: ";
					cin >> producator;
					cout << "Pretul medicamentului modificat: ";
					cin >> pret;
					cout << "Substanta activa a medicamenului midificata: ";
					cin >> substanta_activa;
					try {
						service_medicament.service_modifica_medicament_din_lista(denumire, producator, pret, substanta_activa);
					}
					catch (Exceptie_de_modificare& exceptie)
					{
						cout << exceptie.get_mesaje_de_erori() << endl;
					}
				}
				else if (comanda_medicmanet == "5")
				{
					double pret;
					cout << "Pretul dupa care vrei sa filtrezi:";
					cin >> pret;
					vector<Medicament> lista_filtrata_dupa_pret = service_medicament.filtreaza_lista_dupa_pret(pret);
					afiseaza_toate_medicamentele(lista_filtrata_dupa_pret);
				}
				else if (comanda_medicmanet == "6")
				{
					string substanta_activa;
					cout << "Substanta activa dupa care vrei sa filtrezi lista: ";
					cin >> substanta_activa;
					vector<Medicament> lista_filtrata_dupa_substanta_activa = service_medicament.filtreaza_lista_dupa_substanta_activa(substanta_activa);
					afiseaza_toate_medicamentele(lista_filtrata_dupa_substanta_activa);
				}
				else if (comanda_medicmanet == "7")
				{
					vector<Medicament> lista_sortata_dupa_denumire = service_medicament.sorteaza_lista_dupa_denumirea_medicamentului();
					afiseaza_toate_medicamentele(lista_sortata_dupa_denumire);
				}
				else if (comanda_medicmanet == "8")
				{
					vector<Medicament> lista_sortata_dupa_producator = service_medicament.sorteaza_lista_dupa_producatorul_medicamentului();
					afiseaza_toate_medicamentele(lista_sortata_dupa_producator);
				}
				else if (comanda_medicmanet == "9")
				{
					vector<Medicament> lista_sortata_dupa_substanta_activa_si_pret = service_medicament.sorteaza_lista_dupa_substanta_activa_si_pretul_medicamentului();
					afiseaza_toate_medicamentele(lista_sortata_dupa_substanta_activa_si_pret);
				}
				else if (comanda_medicmanet == "10")
				{
					afiseaza_toate_medicamentele(service_medicament.get_toate_medicamentele_din_lista());
				}
				else if (comanda_medicmanet == "11")
				{
					break;
				}
				else if (comanda_medicmanet == "undo")
				{
					try {
						service_medicament.undo();
						afiseaza_toate_medicamentele(service_medicament.get_toate_medicamentele_din_lista());
					}
					catch (Exceptii& exceptie) {
						cout << exceptie.get_mesaje_de_erori() << endl;
					}
				}
				else continue;
			}
		}
		else if (comanda == "2")
		{
			while (1) {

				meniu_reteta();
				string comanda_reteta;
				cout << ">>>";
				cin >> comanda_reteta;
				if (comanda_reteta == "1")
				{
					string nume_medicament_pe_care_vreau_sa_il_adaug_in_retata;
					cout << "Numele medicamentului pe care vreau sa il adaug in reteta: ";
					cin >> nume_medicament_pe_care_vreau_sa_il_adaug_in_retata;
					try {
						service_medicament.adauga_medicament_in_reteta(nume_medicament_pe_care_vreau_sa_il_adaug_in_retata);
					}
					catch (Exceptie_de_adaugare& exceptie) {
						cout << exceptie.get_mesaje_de_erori() << endl;
					}
				}
				else if (comanda_reteta == "2")
				{
					try {
						service_medicament.golseste_reteta_de_medicamente();
						cout << "Lista a fost golita cu succes\n";
					}
					catch (Exceptie_de_stergere& exceptie) {
						cout << exceptie.get_mesaje_de_erori() << endl;
					}
				}
				else if (comanda_reteta == "3")
				{
					int numarul_de_elemente_pe_care_vreau_sa_le_adaug_radom_in_reteta;
					cout << "Numarul de elemente pe care vreau sa le adaug radom in reteta: ";
					cin >> numarul_de_elemente_pe_care_vreau_sa_le_adaug_radom_in_reteta;
					service_medicament.adauga_medicamente_random_in_cos(numarul_de_elemente_pe_care_vreau_sa_le_adaug_radom_in_reteta);
					cout << "Au fost adaugate cu succes " << numarul_de_elemente_pe_care_vreau_sa_le_adaug_radom_in_reteta << " medicamente\n";
				}
				else if (comanda_reteta == "4")
				{
					afiseaza_toate_medicamentele(service_medicament.get_toate_medicamentele_din_reteta());
				}
				else if (comanda_reteta == "5")
				{
					break;
				}
				else continue;
			}
		}
		else if (comanda == "exit")
		{
			break;
		}
		else continue;
	}
}
