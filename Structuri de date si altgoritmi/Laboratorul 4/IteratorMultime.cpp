#include "IteratorMultime.h"
#include "Multime.h"
#include <iostream>
using namespace std;

IteratorMultime::IteratorMultime(const Multime& m) : multime(m){
	/* de adaugat */
	///theta(m)
	valoarea_curenta = new Nod* [m.m];
	for (int i = 0; i < multime.m; i++)
	{
		valoarea_curenta[i] = multime.tabela_de_dispersie[i];
	}
	deplasare();
}

/// theta(n)
void IteratorMultime::deplasare()
{
	Nod* nod = nullptr;
	int poz;
	for (int i = 0; i < multime.m; i++)
	{
		if (valoarea_curenta[i] == nullptr)
			continue;
		if (nod == nullptr)
		{
			nod = valoarea_curenta[i];
			poz = i;
		}
	}
	if (nod != nullptr)
	{
		elementul_curent = nod->element;
		valoarea_curenta[poz] = valoarea_curenta[poz]->urm;
	}
	else
	{
		elementul_curent = NULL_TELEM;
	}
}

//theta(m)
void IteratorMultime::prim() {
	/* de adaugat */
	for (int i = 0; i < multime.m; i++)
	{
		valoarea_curenta[i] = multime.tabela_de_dispersie[i];
	}
	deplasare();
}

//O(1)
void IteratorMultime::urmator() {
	/* de adaugat */
	//if (!valid())
		//throw std::exception();
	deplasare();
}

//o(1)
TElem IteratorMultime::element() const {
	if (!valid())
		throw std::exception();
	return elementul_curent;
}

//O(1+factorul_de_)
/*
	anterior(l)
		it<-l.multime.iterator()
		it.prim()
		daca elementul_curent == it.element() atunci
			elementul_curent<- NULL_TELEM
		altfel
			it<-l.multime
			it.prim()
			anterior<-it.element()
			cat timp it.element() != elementul_curent executa
				anterior <- it.element()
				it.urmator()
			sf cat timp
			elementul_curent<-anterior
		sf daca
		daca !l.valid() atunci
			anterior()<-exceptie()
		sf daca
	sf anterior()
*/
void IteratorMultime::anterior()
{
	IteratorMultime it = multime.iterator();
	it.prim();
	if (elementul_curent == it.element())
	{
		elementul_curent = NULL_TELEM;
	}
	else
	{
		IteratorMultime it= multime;
		it.prim();
		TElem anterior = it.element();
		while (it.element() != elementul_curent)
		{
		    anterior = it.element();
			it.urmator();
		}
		elementul_curent = anterior;
	}
	if (!valid())
	{
		throw std::exception();
	}
}


//O(1)
bool IteratorMultime::valid() const {
	return elementul_curent != NULL_TELEM;
}

