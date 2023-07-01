#pragma once
#include "Medicament.h"
#include "VectorDinamicTemplate.h"
#include <vector>
using std::vector;
using std::string;

//template<typename VectorDinamic>
class Validare {
private:
	vector<string> mesaj_de_erori;
public:
	Validare(vector<string> mesaje_de_erori) :mesaj_de_erori{ mesaje_de_erori } {};

	string get_stringul_de_erori()
	{
		string mesajul_complet = "";
		for (const string erori : mesaj_de_erori)
		{
			mesajul_complet += erori + '\n';
		}
		return mesajul_complet;
	}
};

//template<typename VectorDinamic>
class Validare_de_medicamente {
public:
	void valideaza_medicament(const Medicament& medicament_de_validat)
	{
		vector<string> erori;
		if (medicament_de_validat.get_denumire() == "")
			erori.push_back("Denumirea nu poate sa fie vida!");
		if (medicament_de_validat.get_pret() <= 0)
			erori.push_back("Pretul trebuie sa fie >0!");
		if (medicament_de_validat.get_producator() == "")
			erori.push_back("Producatorul nu poate sa fie vid!");
		if (medicament_de_validat.get_substanta_activa() == "")
			erori.push_back("Substanta activa nu poate sa fie vida!");

		if (erori.size() > 0)
			throw Validare(erori);
	}
};