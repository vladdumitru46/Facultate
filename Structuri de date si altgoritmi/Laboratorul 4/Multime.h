#pragma once

#define NULL_TELEM -1
typedef int TElem;

class IteratorMultime;

struct Nod {
	TElem element;
	Nod* urm;
};

class Multime {
	friend class IteratorMultime;

    private:
		/* aici e reprezentarea */
		Nod** tabela_de_dispersie;
		int m;
		int hash_code(TElem e) const;
		int d(TElem e);
		void redimensionare();
		int dimensiunea_tabelei;
		double factorul_de_incarcare;
		double get_factorul_de_incarcare() const;
    public:
 		//constructorul implicit
		Multime();

		//adauga un element in multime
		//returneaza adevarat daca elementul a fost adaugat (nu exista deja in multime)
		bool adauga(TElem e);

		//sterge un element din multime
		//returneaza adevarat daca elementul a existat si a fost sters
		bool sterge(TElem e);

		//verifica daca un element se afla in multime
		bool cauta(TElem elem) const;


		//intoarce numarul de elemente din multime;
		int dim() const;

		//verifica daca multimea e vida;
		bool vida() const;

		//returneaza un iterator pe multime
		IteratorMultime iterator() const;

		// destructorul multimii
		~Multime();
};




