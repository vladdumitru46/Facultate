#include "Multime.h"
#include "IteratorMultime.h"

#include <iostream>

Multime::Multime() {
	/* de adaugat */
	m = 127;
	dimensiunea_tabelei = 0;
	factorul_de_incarcare = 0.7;
	tabela_de_dispersie = new Nod * [m];
	for (int i = 0; i < m; i++)
	{
		tabela_de_dispersie[i] = nullptr;
	}
}


//O(1)
int Multime::hash_code(TElem e) const
{
	return abs(e) % m;
}
//tehta(n*2)
void Multime::redimensionare()
{
	int* toate_valorile = new int[this->m];
	int numara = 0;
	IteratorMultime it(*this);
	it.prim();
	while (it.valid())
	{
		toate_valorile[numara++] = it.element();
		it.urmator();
	}
	Nod** vechea_tabela_de_dispersie = this->tabela_de_dispersie;
	int m_vechi = m;
	m *= 2;
	m++;
	tabela_de_dispersie = new Nod * [m];
	for (int i = 0; i < m; i++)
	{
		tabela_de_dispersie[i] = nullptr;
	}
	dimensiunea_tabelei = 0;
	for (int i = 0; i < m_vechi; i++)
	{
		Nod* nod = vechea_tabela_de_dispersie[i];
		while (nod != nullptr)
		{
			Nod* urmator = nod->urm;
			delete nod;
			nod = urmator;
		}
	}
	delete[] vechea_tabela_de_dispersie;
}

//O(1)
double Multime::get_factorul_de_incarcare() const
{
	return double(dimensiunea_tabelei) / m;
}


//O(1)
bool Multime::adauga(TElem elem) {
	if (cauta(elem))
		return false;
	int poz = hash_code(elem);
	dimensiunea_tabelei++;
	Nod* nod_nou = new Nod;
	nod_nou->element = elem;
	//if (this->tabela_de_dispersie == nullptr)
	//{
	nod_nou->urm = tabela_de_dispersie[poz];
	tabela_de_dispersie[poz] = nod_nou;
	//}
	return true;
}

//BC: O(1)
//WX:theta(1+factorul_de_incarcare)
//AC:O(1+factorul_de_incarcare)
bool Multime::sterge(TElem elem) {
	if (this->dimensiunea_tabelei == 0)
		return false;
	int poz = hash_code(elem);
	Nod* curent = tabela_de_dispersie[poz];
	Nod* prev = nullptr;
	if (tabela_de_dispersie[poz] == nullptr)
		return false;
	if (tabela_de_dispersie[poz]->element == elem)
	{
		Nod* nod_vechi = tabela_de_dispersie[poz];
		tabela_de_dispersie[poz] = tabela_de_dispersie[poz]->urm;
		delete nod_vechi;
		dimensiunea_tabelei--;
		return true;
	}

	while (curent != nullptr)
	{
		if (curent->element == elem)
		{
			Nod* nod_urm = curent->urm;
			delete nod_urm;
			prev->urm = nod_urm;
			dimensiunea_tabelei--;
			return true;
		}
		prev = curent;
		curent=curent->urm;
	}
}

//BC:O(1)
//WC: theta(1+factorul_de_incarcare)
//AC:O(1+factorul_de_incarcare)
bool Multime::cauta(TElem elem) const {
	int poz = hash_code(elem);
	Nod* curent = tabela_de_dispersie[poz];
	while (curent != nullptr)
	{
		if (curent->element == elem)
			return true;
		curent = curent->urm;
	}
	return false;
}

//O(1)
int Multime::dim() const {
	/* de adaugat */
	return dimensiunea_tabelei;
}

//O(1)
bool Multime::vida() const {
	/* de adaugat */
	return dimensiunea_tabelei==0;
}

//theta(n+numarul de elemente)
Multime::~Multime() {
	for (int i = 0; i < m; i++)
	{
		Nod* nod = tabela_de_dispersie[i];
		while (nod != nullptr)
		{
			Nod* urmator = nod->urm;
			delete nod;
			nod = urmator;
		}
	}
}

//O(1)
IteratorMultime Multime::iterator() const {
	return IteratorMultime(*this);
}

