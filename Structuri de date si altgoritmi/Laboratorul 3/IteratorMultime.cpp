#include "IteratorMultime.h"
#include "Multime.h"
#include <exception>

IteratorMultime::IteratorMultime(const Multime& m) : mult(m) {
	this->elementul_curent = m.multime.prim;
	//this->curent = 0;
}

TElem IteratorMultime::element() const {
	if (this->valid())
	{
		return this->mult.multime.noduri[this->elementul_curent].element;
	}
	return -1;
}

bool IteratorMultime::valid() const {
	/* de adaugat */
	return this->elementul_curent != -1;
}

void IteratorMultime::urmator() {
	if (!this->valid())
		throw std::exception();
	else {
		this->elementul_curent = this->mult.multime.noduri[this->elementul_curent].urm;
	}
}

void IteratorMultime::prim() {
	this->elementul_curent = this->mult.multime.prim;
}

