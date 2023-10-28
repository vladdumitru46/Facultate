#ifndef CREARE_CHELTUIALA_H_
#define CREARE_CHELTUIALA_H_
#pragma once
#define _CRTDBG_MAP_ALLOC
#include <stdlib.h>
#include <crtdbg.h>
#include <string.h>

typedef struct {
	int numarul_apartamentului;
	float suma_lunara_a_cheltuielii;
	char* tipul_de_cheltuiala_lunara;
}Cheltuiala;

Cheltuiala creare_cheltuiala(int nuamrul_apartamentului, float suma_lunara_a_cheltuielii, char* tipul_de_cheltuiala_lunara);

void destroy_cheltuiala(Cheltuiala* cheltuiala_lunara);

Cheltuiala copie_cheltuiala(Cheltuiala cheltuiala_lunara);
#endif