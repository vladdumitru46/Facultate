#pragma once
template <typename Tip_de_elemenet>
class IteratorVector;

template <typename Tip_de_element>
class VectorDinamic
{
private:
	Tip_de_element* element;
	int lungime;
	int capacitate;
	void redimensionare();
public:
	VectorDinamic() noexcept;

	VectorDinamic(const VectorDinamic& vectordinamic);

	~VectorDinamic();

	/*
	Operator assgnment
	elibereaza ce era in obiectul curent (this)
	aloca spatiu pentru elemente
	copieaza elementele din ot in this
	*/
	VectorDinamic& operator = (const VectorDinamic & vectordinamic);

	/*
	Move constructor
	Apelat daca construim un nou vector dintr-un r-value (ex temporary, expresie de la return)
	Obiectul ot nu se mai foloseste astfel se poate refolosi interiorul lui
	*/
	VectorDinamic(VectorDinamic&& vectordinamic);

	/*
	Move assignment
	Similar cu move constructor
	Folosit la assignment
	*/
	VectorDinamic& operator = ( VectorDinamic&& vectordinamic);

	void adauga(const Tip_de_element& elemente);
	
	Tip_de_element& get_elementul_din_vector_dupa_pozitie(int pozitie) const;

	void seteaza_un_anumit_element_din_vector_de_la_pozitiea_pozitie(int pozitie, const Tip_de_element& elemente_de_setat);

	int dimensiune() const noexcept;

	void sterge(const Tip_de_element& elemente);

	friend class IteratorVector<Tip_de_element>;

	IteratorVector<Tip_de_element> begin();
	IteratorVector<Tip_de_element> end();

};


template<typename Tip_de_element>
VectorDinamic<Tip_de_element>::VectorDinamic() noexcept{
	this->element = new Tip_de_element[5];
	this->lungime = 0;
	this->capacitate = 5; 
}

/*
Constructor de copiere
Obiectul current (this) acum se creaza
aloca spatiu pentru elemente
copieaza elementele din ot in this
*/
template<typename Tip_de_element>
VectorDinamic<Tip_de_element>::VectorDinamic(const VectorDinamic<Tip_de_element>& vectordinamic)
{
	element = new Tip_de_element[vectordinamic.capacitate];
	for (int i = 0; i < lungime; i++)
	{
		element[i] = vectordinamic.element[i];
	}
	lungime = vectordinamic.lungime;
	capacitate = vectordinamic.capacitate;
}

/*
Operator assgnment
elibereaza ce era in obiectul curent (this)
aloca spatiu pentru elemente
copieaza elementele din ot in this
*/
template <typename Tip_de_element>
VectorDinamic<Tip_de_element>& VectorDinamic<Tip_de_element>::operator=(const VectorDinamic<Tip_de_element>& vectordinamic)
{
	if (this == &vectordinamic)
	{
		return *this;
	}
	delete[] element;
	element = new Tip_de_element[vectordinamic.capacitate];
	for (int i = 0; i < vectordinamic.lungime; i++)
	{
		element[i] = vectordinamic.element[i];
	}
	lungime = vectordinamic.lungime;
	capacitate = vectordinamic.capacitate;
	return *this;
}



template<typename Tip_de_element>
VectorDinamic<Tip_de_element>::~VectorDinamic() 
{
	delete[] element;
}


/*
Move constructor
Apelat daca construim un nou vector dintr-un r-value (ex temporary)
Obiectul ot nu se mai foloseste astfel se poate refolosi interiorul lui
*/
template<typename Tip_de_element>
VectorDinamic<Tip_de_element>::VectorDinamic(VectorDinamic&& vectordinamic)
{
	element = vectordinamic.element;
	lungime = vectordinamic.lungime;
	capacitate = vectordinamic.capacitate;

	vectordinamic.element = nullptr;
	vectordinamic.lungime = 0;
	vectordinamic.capacitate = 0;
}

/*
Move assignment
Similar cu move constructor
Folosit la assignment
Elibereaza ce continea obiectul curent (this)
"fura" interiorul lui ot
pregateste ot pentru distrugere
*/

template<typename Tip_de_element>
VectorDinamic<Tip_de_element>& VectorDinamic<Tip_de_element>:: operator = (VectorDinamic<Tip_de_element>&& vectordinamic)
{
	if (this == &vectordinamic)
	{
		return *this;
	}
	delete[] element;

	element = vectordinamic.element;
	lungime = vectordinamic.lungime;
	capacitate = vectordinamic.capacitate;

	vectordinamic.element = 0;
	vectordinamic.lungime = 0;
	vectordinamic.capacitate = 0;

	return *this;
}

template<typename Tip_de_element>
void VectorDinamic<Tip_de_element>::adauga(const Tip_de_element& elemente)
{
	redimensionare();
	element[lungime++] = elemente;
}

template <typename Tip_de_element>
Tip_de_element& VectorDinamic<Tip_de_element>::get_elementul_din_vector_dupa_pozitie(int pozitie) const {
	return element[pozitie];
}

template <typename Tip_de_element>
void VectorDinamic<Tip_de_element>::seteaza_un_anumit_element_din_vector_de_la_pozitiea_pozitie(int pozitie, const Tip_de_element& elemente_de_setat)
{
	element[pozitie] = elemente_de_setat;
}


template <typename Tip_de_element>
int VectorDinamic<Tip_de_element>::dimensiune() const noexcept
{
	return lungime;
}

template<typename Tip_de_element>
inline void VectorDinamic<Tip_de_element>::sterge(const Tip_de_element& elemente)
{
	int ii = -1;
	for (int i = 0; i < lungime; i++)
	{
		if (element[i] == elemente)
		{
			ii = i;
			for (int j = ii; j < lungime; j++)
				element[j] = element[j + 1];
			lungime--;
			break;
		}
	}
}


template <typename Tip_de_element>
void VectorDinamic<Tip_de_element>::redimensionare()
{
	if (lungime < capacitate)
		return;
	capacitate = capacitate * 2;
	Tip_de_element* aux = new Tip_de_element[capacitate];
	for (int i = 0; i < lungime; i++)
	{
		aux[i] = element[i];
	}
	delete[] element;
	element = aux;
}

template<typename Tip_de_element>
IteratorVector<Tip_de_element> VectorDinamic<Tip_de_element>::begin()
{
	return IteratorVector<Tip_de_element>(*this);
}

template<typename Tip_de_element>
IteratorVector<Tip_de_element> VectorDinamic<Tip_de_element>::end()
{
	return IteratorVector<Tip_de_element>(*this, this->lungime);
}


template<typename Tip_de_elment>
class IteratorVector {
private:
	const VectorDinamic<Tip_de_elment>& vectordinamic;
	int pozitie = 0;
public:
	IteratorVector(const VectorDinamic<Tip_de_elment>& v) noexcept;
	IteratorVector(const VectorDinamic<Tip_de_elment>& v, int poz)noexcept;
	bool valid() const;
	Tip_de_elment& element() const;
	void next() noexcept;
	Tip_de_elment& operator*();
	IteratorVector& operator++() noexcept;

	bool operator==(const IteratorVector& ot) noexcept;
	bool operator!=(const IteratorVector& ot) noexcept;

};


template <typename Tip_de_element>
IteratorVector<Tip_de_element>::IteratorVector(const VectorDinamic<Tip_de_element>& vectordinamic) noexcept : vectordinamic{ vectordinamic } {}


template <typename Tip_de_element>
IteratorVector<Tip_de_element>::IteratorVector(const VectorDinamic<Tip_de_element>& vectordinamic, int pozitie) noexcept : vectordinamic{ vectordinamic }, pozitie{ pozitie } {}


template <typename Tip_de_element>
bool IteratorVector<Tip_de_element>::valid() const
{
	return pozitie < vectordinamic.lungime;
}

template <typename Tip_de_element>
Tip_de_element& IteratorVector<Tip_de_element>::element() const {
	return vectordinamic.element[pozitie];
}

template <typename Tip_de_element>
void IteratorVector<Tip_de_element>::next() noexcept
{
	pozitie++;
}

template <typename Tip_de_element>
Tip_de_element& IteratorVector<Tip_de_element>::operator*() {
	return element();
}

template <typename Tip_de_element>
IteratorVector<Tip_de_element>& IteratorVector<Tip_de_element>::operator++() noexcept {
	next();
	return *this;
}

template <typename Tip_de_element>
bool IteratorVector<Tip_de_element>::operator==(const IteratorVector<Tip_de_element>& iteratorvectordinamic) noexcept {
	return pozitie == iteratorvectordinamic.pozitie;
}

template <typename Tip_de_element>
bool IteratorVector<Tip_de_element>::operator!=(const IteratorVector<Tip_de_element>& iteratorvectordinamic) noexcept {
	return !(*this == iteratorvectordinamic);
}