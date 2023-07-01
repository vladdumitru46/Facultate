#include "Iterator.h"
#include "DO.h"

using namespace std;

Iterator::Iterator(const DO& d) : dict(d){
	curent = d.elemente;
}


void Iterator::prim() {
	curent = dict.elemente;
}

void Iterator::urmator() {
	curent++;
}

bool Iterator::valid() const {
	
	return curent - dict.elemente < dict.dim();
}

TElem Iterator::element() const {
	return *curent;
	return pair <TCheie, TValoare>(-1, -1);
}


