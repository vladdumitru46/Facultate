#include "Validare.h"

int validare_cheltuiala_noua(Cheltuiala cheltuiala_lunara)
{
	int numar_apartament_invalid = 1;
	int suma_lunara_invalida = 2;
	int tip_de_cheltuiala_lunara_invalida = 3;
	if (cheltuiala_lunara.numarul_apartamentului <= 0)
		return numar_apartament_invalid;
	if (cheltuiala_lunara.suma_lunara_a_cheltuielii <= 0)
		return suma_lunara_invalida;
	if (strcmp(cheltuiala_lunara.tipul_de_cheltuiala_lunara, "apa") != 0)
		if(strcmp(cheltuiala_lunara.tipul_de_cheltuiala_lunara,"gaz") != 0)
			if (strcmp(cheltuiala_lunara.tipul_de_cheltuiala_lunara, "canal") != 0)
				if(strcmp(cheltuiala_lunara.tipul_de_cheltuiala_lunara, "incalzire") != 0)
					return tip_de_cheltuiala_lunara_invalida;
	return 0;
}

