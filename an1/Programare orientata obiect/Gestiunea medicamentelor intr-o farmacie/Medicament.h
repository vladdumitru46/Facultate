#pragma once


#include <iostream>
#include <string>

using std::string;
using std::cout;

//template<typename VectorDinamic>
class Medicament {
	
private:
	string denumire;
	double pret;
	string producator;
	string substanta_activa;

public:

	Medicament(string denumire, double pret, string producator, string substanta_activa) : denumire{ denumire }, pret{ pret }, producator{ producator }, substanta_activa{ substanta_activa }{};
	Medicament(const Medicament& medicament) : denumire{ medicament.denumire }, pret{ medicament.pret }, producator{ medicament.producator }, substanta_activa{ medicament.substanta_activa }{
		//cout << "S-a chemat copia de constructor\n";
	}


	string get_denumire() const;
	double get_pret() const noexcept;
	string get_producator() const;
	string get_substanta_activa() const;

	void setDenumire(string denumireNoua);
	void setPret(double pretNou) noexcept;
	void setProducator(string producatorNou);
	void setSubstanta_activa(string substanta_activa_noua);

	~Medicament() = default;
};