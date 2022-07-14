#include "Creare_cheltuiala.h"

Cheltuiala creare_cheltuiala(int nuamrul_apartamentului, float suma_lunara_a_cheltuielii, char* tipul_de_cheltuiala_lunara)
{
    Cheltuiala cheltuiala_lunara;
    cheltuiala_lunara.numarul_apartamentului = nuamrul_apartamentului;
    cheltuiala_lunara.suma_lunara_a_cheltuielii = suma_lunara_a_cheltuielii;
    int contor_alocare_memorie = (int)strlen(tipul_de_cheltuiala_lunara) + 1;
    cheltuiala_lunara.tipul_de_cheltuiala_lunara = malloc(contor_alocare_memorie * sizeof(char));
    strcpy(cheltuiala_lunara.tipul_de_cheltuiala_lunara, tipul_de_cheltuiala_lunara);
    return cheltuiala_lunara;
}

void destroy_cheltuiala(Cheltuiala* cheltuiala_lunara)
{
    cheltuiala_lunara->numarul_apartamentului = -1;
    cheltuiala_lunara->suma_lunara_a_cheltuielii = -1;
    free(cheltuiala_lunara->tipul_de_cheltuiala_lunara);
    //cheltuiala_lunara->tipul_de_cheltuiala_lunara[0] = '\0';
}

Cheltuiala copie_cheltuiala(Cheltuiala cheltuiala_lunara)
{
    return creare_cheltuiala(cheltuiala_lunara.numarul_apartamentului,cheltuiala_lunara.suma_lunara_a_cheltuielii,cheltuiala_lunara.tipul_de_cheltuiala_lunara);
}
