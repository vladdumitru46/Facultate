#include "Multime.h"
#include "IteratorMultime.h"
#include <iostream>

using namespace std;

//o posibila relatie
bool rel(TElem e1, TElem e2) {
    if (e1 <= e2) {
        return true;
    }
    else {
        return false;
    }
}

Multime::Multime() {
    multime.cp = 4;
    multime.noduri = new Nod[4];
    multime.prim = -1;
    init_spatiu_liber();
    multime.n = 0;
    multime.lungimea_reala = 0;
}

int Multime::aloca()
{
    int element_nou = this->multime.primliber;
    if (element_nou != -1)
    {
        this->multime.primliber = this->multime.noduri[this->multime.primliber].urm;
        this->multime.noduri[element_nou].urm = -1;
    }
    return element_nou;
}

void Multime::dealoca(int poz)
{
    this->multime.noduri[poz].urm = this->multime.primliber;
    this->multime.primliber = poz;
}

void Multime::redimensionare()
{
    Nod* noduri_noi = new Nod[this->multime.cp + 20];
    for (int i = 0; i < this->multime.cp; i++)
    {
        noduri_noi[i] = this->multime.noduri[i];
    }

    for (int i = this->multime.cp; i < 20+ this->multime.cp - 1; i++)
    {
        noduri_noi[i].urm = i + 1;
    }
    noduri_noi[this->multime.cp + 20 - 1].urm = -1;
    this->multime.primliber = this->multime.cp;
    this->multime.cp = multime.cp + 20;
    delete[] this->multime.noduri;
    this->multime.noduri = noduri_noi;
}

void Multime::init_spatiu_liber()
{
    for (int i = 0; i < this->multime.cp - 1; i++)
    {
        multime.noduri[i].urm = i + 1;
    }
    multime.noduri[multime.cp - 1].urm = -1;
    multime.primliber = 0;
}

int Multime::creaza_nd(TElem e) const
{
    int curent = multime.prim;
    while (curent != -1 && this->multime.noduri[curent].element != e)
    {
        curent = this->multime.noduri[curent].urm;
    }
    return curent;
}

void Multime::insereaza_element_pozitie(TElem e, int poz)
{
    int element_nou = this->aloca();
    if (element_nou == -1)
    {
        this->redimensionare();
        element_nou = this->aloca();
    }
    this->multime.noduri[element_nou].element = e;
    if (poz == 0)
    {
        if (this->multime.prim == -1)
        {
            this->multime.prim = element_nou;
        }
        else
        {
            this->multime.noduri[element_nou].urm = this->multime.prim;
            this->multime.prim = element_nou;
        }
    }
    else {
        int nod = this->multime.prim;
        int poz2 = 0;
        while (nod != -1 && poz2 < poz - 1)
        {
            nod = this->multime.noduri[nod].urm;
            poz2++;
        }
        int nod_urm;
        if (nod != -1)
        {
            nod_urm = this->multime.noduri[nod].urm;
            this->multime.noduri[element_nou].urm = nod_urm;
            //this->multime.noduri[element_nou] = multime.noduri[nod];
            multime.noduri[nod].urm = element_nou;
            if (nod_urm == -1)
            {
                //cout << "ULTIMUL" << endl;
                this->multime.noduri[multime.primliber] = this->multime.noduri[element_nou];
                this->multime.primliber = this->multime.noduri[multime.primliber].urm;
            }
        }
    }
}





bool Multime::adauga(TElem elem) {
    if (cauta(elem) == true)
        return false;
    int i = creaza_nd(elem);
    this->multime.n++;
    if (i == -1)
    {
        i = this->multime.prim;
        if (i != -1)
        {

            int index = 0;
            while (i != -1 && rel(this->multime.noduri[i].element, elem))
            {
                i = this->multime.noduri[i].urm;
                index++;
            }
            if (i == -1)
            {
                this->insereaza_element_pozitie(elem, this->multime.lungimea_reala);
            }
            else
            {
                this->insereaza_element_pozitie(elem, index);
            }
        }
        else {
            i = 0;
            this->insereaza_element_pozitie(elem, i);
        }
        this->multime.lungimea_reala++;
    }
    else
        return false;
    return true;
}



bool Multime::sterge(TElem elem) {
    int i = creaza_nd(elem);
    if (i == -1)
        return false;
    else {
        multime.n--;
        multime.lungimea_reala--;
        if (i == multime.prim)
            multime.prim = multime.noduri[i].urm;
        else {
            int q = multime.prim;
            while (multime.noduri[q].urm != i)
            {
                q = multime.noduri[q].urm;
            }
            multime.noduri[q].urm = multime.noduri[i].urm;
        }
        dealoca(i);
        return true;
    }
}



bool Multime::cauta(TElem elem) const {
    int i = multime.prim;
    while (i != -1 && this->multime.noduri[i].element != elem)
        i = this->multime.noduri[i].urm;
    if (i != -1)
        return true;
    return false;
}


int Multime::dim() const {
    /* de adaugat */
    return this->multime.n;
}



bool Multime::vida() const {
    /* de adaugat */
    return this->multime.n == 0;
}

IteratorMultime Multime::iterator() const {
    return IteratorMultime(*this);
}


Multime::~Multime() {
    delete[] this->multime.noduri;
}






