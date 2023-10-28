#include "Colectie.h"
#include "IteratorColectie.h"
#include <exception>
#include <iostream>

using namespace std;


Nod::Nod(TElem e, PNod urm, PNod prec) {
	this->e = e;
	this->urm = urm;
	this->prec = prec; 
}

//O(1) - la toate
TElem Nod::element() {
	return e;
}


//O(1) - la toate
PNod Nod::urmator() {
	return urm;
}



//O(1) - la toate
PNod Nod::precedent()
{
	return prec;
}



Colectie::Colectie() {
	/* de adaugat */
	//fr = new int[100];
	this->prim = nullptr;
	this->ultim = nullptr;
}



//O(1) - la toate
void Colectie::adauga(TElem elem) {
	/* de adaugat */
	fr[elem]++;
	PNod q = new Nod(elem, nullptr, nullptr);
	if (prim == nullptr && ultim == nullptr )
	{
		prim = q;
		ultim = q;
	}
	else {
		ultim->urm = q;
		q->prec = ultim;
		ultim = q;
	}
}


// best case: Teta(n)
//worst case: Teta(n^2)
//medium: Teta(n^2)
//overall: Teta(n^2)
bool Colectie::sterge(TElem elem) {
	/* de adaugat */
	PNod p = prim;
	/*
	int ok = 0;

	while (p != nullptr)
	{
		if (p->element() == elem)
		{
			ok = 1;
			break;
		}
		p = p->urm;
	}
	*/
	if (int(fr[elem]) > 0)
	{
		fr[elem]--;
		if (p == prim && p->urm != nullptr)
		{
			prim = p->urm;
			p->prec = nullptr;
			p->urm = prim->urm;
			p->urm = nullptr;
			p = nullptr;
			prim->prec = nullptr;
			//prim = nullptr;
		}
		if (p == prim && p->urm == nullptr )
		{
			Colectie::~Colectie();
		}
		if (prim == nullptr)
		{
			ultim = nullptr;
			return true;
		}
		else
		{
			PNod q = prim;
			if (q->urm != nullptr)
			{
				while (q->urm != p)
				{
					q = q->urm;
				}
			}
			if (p == ultim  && q->prec != nullptr)
			{
				ultim = q->prec;
				q->urm = nullptr;
				q->prec = ultim->prec;
				q->prec = nullptr;
				q = nullptr;
				ultim = nullptr;
				//return true;
			}
			if ( p==prim && ultim == nullptr)
			{
				Colectie::~Colectie();
				//prim = prim->urm;
				//delete p;
			}
			if(p!=nullptr && q!=nullptr)
			{
				q->urm = p->urm;
				q->urm = p->urm;
				//return true;
			}
		}
		return true;
	}

	return false;


}


//best case: O(1)
//worst caase: O(n)
//avarage: O(n)
//overal: O(n)

bool Colectie::cauta(TElem elem) const {
	PNod q = prim;
	while (q != nullptr)
	{
		if (q->element() == elem)
			return true;
		q = q->urm;
	}
	return false;
}

//best case: O(1)
//worst caase: O(n)
//avarage: O(n)
//overal: O(n)
int Colectie::nrAparitii(TElem elem) const {
	/*
	int nr = 0;
	PNod q = prim;
	while (q != nullptr)
	{
		if (q->element() == elem)
			nr++;
		q = q->urm;
	}
	return nr;
	*/
	return int(fr[elem]);
}

//best case: teta(n)
//worst case: teta(n)
//medium: O(n)
//overal: O(n)
/*
eliminanraparitii(lsi,elem)
pre: lsi aparinte L, elem aprtine TElem
post: returneaza nr de aparitii eliminate ale elementului

nr=nrAparitii(elem)
q =lsi.prim
cat timp q != NULL executa
	daca q.element() == elem atunci
		q = q.precedent()
		q.urm=q.urm.urm
	sf daca
	q = q.urm
sf cat timp
nr
sfarsit functie

*/
int Colectie::eliminanraparitii(TElem elem) const
{
	int nr = int(fr[elem]);
	PNod q = prim;
	while (q != nullptr)
	{
		if (q->e == elem)
		{
			q = q->prec;
			q->urm = q->urm->urm;
		}
		q = q->urm;
	}
	return nr;
}


//best case - O(1)
//worst case - O(n)
//average case - O(n)
//overall O(n)

/*
AaritiiMultiple(l,nr,elem)
//pre: l - lista,nr - int, elem - TElem
post : exceptie, daca nr e negativ, altfel nimic

daca nr<=0 atunci
	fr[elem]<-fr[elem]+nr
	i<-0
	cat timp i<nr executa
		l.adauga(elem)
		i<-i+1
	sf cat timp
altfel 
	exceptie
sf daca
sf subaltgr

*/
void Colectie::AparitiiMultiple(int nr, TElem elem)
{
	if (nr >= 0)
	{
		fr[elem] += nr;
		int i = 0;
		while (i < nr)
		{
			adauga(elem);
			i++;
		}
	}
	else
		throw exception();
}


//best case: O(1)
//worst caase: O(n)
//avarage: O(n)
//overal: O(n)
int Colectie::dim() const {
	
	int nr = 0;
	PNod q = prim;
	while (q != nullptr)
	{
		nr++;
		q = q->urm;
	}
	return nr;
}

//O(1) - la toate
bool Colectie::vida() const {
	/* de adaugat */
	return prim == nullptr;
}

IteratorColectie Colectie::iterator() const {
	return  IteratorColectie(*this);
}

//O(n) - la toate
Colectie::~Colectie() {
	/* de adaugat */
	while (prim != nullptr)
	{
		PNod p = prim;
		prim = prim->urm;
		delete p;
	}
}


