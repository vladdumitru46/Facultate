#include "IteratorColectie.h"
#include "Colectie.h"

//O(1)
IteratorColectie::IteratorColectie(const Colectie& c) : col(c) {
	/* de adaugat */
	this->curent = c.prim;
}

//O(1)
void IteratorColectie::prim() {
	/* de adaugat */
	this->curent = col.prim;
}

//O(1)
void IteratorColectie::urmator() {
	/* de adaugat */
	this->curent = this->curent->urmator();
}

//O(1)
bool IteratorColectie::valid() const {
	/* de adaugat */
	return (curent != nullptr);
}


//O(1)
TElem IteratorColectie::element() const {
	/* de adaugat */
	return curent->element();
}
