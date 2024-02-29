#pragma once

#define NULL_TELEM -1
typedef int TElem;

class IteratorColectie;

//referire a clasei Nod

class Nod;



//se defineste tipul PNod ca fiind adresa unui Nod

typedef Nod* PNod;



class Nod{

public:
	friend class Colectie;
	//constructor
	Nod(TElem e, PNod urm, PNod prec);
	TElem element();
	PNod urmator();
	PNod precedent();

private:
	TElem e;
	PNod urm;
	PNod prec;

};

class Colectie
{
	friend class IteratorColectie;

private:
	/* aici e reprezentarea */
	PNod prim;
	PNod ultim;
	PNod fr[];
public:
	//constructorul implicit
	Colectie();

	//adauga un element in colectie
	void adauga(TElem e);


	//sterge o aparitie a unui element din colectie
	//returneaza adevarat daca s-a putut sterge
	bool sterge(TElem e);

	//verifica daca un element se afla in colectie
	bool cauta(TElem elem) const;

	//returneaza numar de aparitii ale unui element in colectie
	int nrAparitii(TElem elem) const;

	int eliminanraparitii(TElem elem) const;

	void AparitiiMultiple(int nr, TElem elem);

	//intoarce numarul de elemente din colectie;
	int dim() const;

	//verifica daca colectia e vida;
	bool vida() const;

	//returneaza un iterator pe colectie
	IteratorColectie iterator() const;

	// destructorul colectiei
	~Colectie();

};

