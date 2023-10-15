#include "Medicament.h"

string Medicament::get_denumire() const {
	return this->denumire;
}


double Medicament::get_pret() const noexcept {
	return this->pret;
}

string Medicament::get_producator() const {
	return this->producator;
}


string Medicament::get_substanta_activa() const {
	return this->substanta_activa;
}


void Medicament::setDenumire(string denumireNoua)
{
	this->denumire = denumireNoua;
}


void Medicament::setPret(double pretNou) noexcept {
	this->pret = pretNou;
}


void Medicament::setProducator(string producatorNou)
{
	this->producator = producatorNou;
}



void Medicament::setSubstanta_activa(string substanta_activa_noua)
{
	this->substanta_activa = substanta_activa_noua;
}