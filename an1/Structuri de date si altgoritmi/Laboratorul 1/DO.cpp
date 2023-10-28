#include "Iterator.h"
#include "DO.h"
#include <iostream>

#include <exception>
using namespace std;


//o(n) la toate
void DO::redim()
{
	TElem *enou = new TElem[2 * cp];

	for (int i = 0; i < n; i++)
		enou[i] = elemente[i];
	cp = 2 * cp;
	delete[] elemente;
	elemente = enou;
}

DO::DO(Relatie r) {
	n = 0;
	this->cp = 2;
	elemente = new TElem[cp];
	prim = NULL;
	R = r;
}

//adauga o pereche (cheie, valoare) in dictionar
//daca exista deja cheia in dictionar, inlocuieste valoarea asociata cheii si returneaza vechea valoare
//daca nu exista cheia, adauga perechea si returneaza null
//best case: O(1)
//wosrt case: O(n)
//medium case: O(n)
//overall: O(n)
TValoare DO::adauga(TCheie c, TValoare v) {
	/* de adaugat */
	if (n == cp)
		redim();
	TElem el{ c,v };
	TValoare val = cauta(c);
	if (val == NULL_TVALOARE)
	{
		elemente[n++] = el;
		for(int i=0;i<n-1;i++)
			for (int j = i; j < n; j++)
			{
				if (R(elemente[j].first, elemente[i].first))
				{
					TElem e = elemente[i];
					elemente[i] = elemente[j];
					elemente[j] = e;
				}
			}
		return NULL_TVALOARE;
	}
	else{
		for (int i = 0; i < n; i++)
		{
			if (elemente[i].first == c)
			{
				elemente[i].second = v;
				return val;
			}
		}
	}
}

//cauta o cheie si returneaza valoarea asociata (daca dictionarul contine cheia) sau null
//O(n) - la toate
TValoare DO::cauta(TCheie c) const {
	
	for (int i = 0; i < n; i++)
	{
		if (elemente[i].first == c)
		{
			return elemente[i].second;
		}
	}
	return NULL_TVALOARE;
}

//sterge o cheie si returneaza valoarea asociata (daca exista) sau null
//best case: O(1)
//worst case: O(n^2)
//medium: O(n^2)
//overall: O(n^2)
TValoare DO::sterge(TCheie c) {
	TValoare val = cauta(c);
	if (val != NULL_TVALOARE)
	{
		for (int i = 0; i < n; i++)
		{
			if (elemente[i].first == c)
			{
				int ii = i;
				for (int j = ii; j < n-1; j++)
					elemente[j] = elemente[j + 1];
				n--;
				return val;
			}
		}
	}
	return NULL_TVALOARE;
}

//returneaza numarul de perechi (cheie, valoare) din dictionar
//O(1) - la toate
int DO::dim() const {
	/* de adaugat */
	return n;
}

//verifica daca dictionarul e vid
//O(1) - la toate
bool DO::vid() const {
	return n==0;
}

//O(1) - la toate
Iterator DO::iterator() const {
	return  Iterator(*this);
}

DO::~DO() {
	/* de adaugat */
	delete[] elemente;
}
