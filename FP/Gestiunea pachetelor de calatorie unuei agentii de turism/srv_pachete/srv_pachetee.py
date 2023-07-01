from Adauga_pachete_de_calatorie.adauga_pachete import adauga_pachet_de_calatorie
from validare_pachete.validare import valideaza_pachet_calatorie
from Adauga_pachete_de_calatorie_in_lista.adauga_lista import adauga_pachet_calatorie_in_lista

def srv_pachet_calatorie(l, zi_inceput, luna_inceput, an_inceput, zi_sfarsit, luna_sfarsit, an_sfarsit, destinatie,
                         pret):
    # functie care creeaza pachetul de calatorie
    # input: lista-l
    # ziua de inceput:zi_inceput
    # luna de inceput:luna_inceput
    # anul de inceput:an_inceput
    # ziua de sfarsit:zi_sfarsit
    # luna de sfarsit:luna_sfarsit
    # anul de sfarsit: an_sfarsit
    # destinatia: destinatie
    # pretul: pret
    # output: , daca totul resueste cu succes
    # raises: EXception cu textul
    # "zi de incput invalida\n", daca ziua de inceput este <1 sau >31
    # "luna de inceput invalida\n", daca luna de inceput este <1 sau >12
    # "an de inceput invalid\n", daca anul de inceput este <2021
    # "zi de sfarsit invalida\n", daca ziua de sfarsit este <1 sau >31
    # "luna de sfarsit invalida\n", daca luna de sfarsit este <1 sau >12
    # "an de sfarsit invalid\n", daca anul de sfarsit este <2021
    # "destinatie invalida\n", daca destinatia este vida
    # "pret invalid\n", daca pretu este <=0
    pachet_calatorie = adauga_pachet_de_calatorie(zi_inceput, luna_inceput, an_inceput, zi_sfarsit, luna_sfarsit,
                                              an_sfarsit, destinatie, pret)
    adauga_pachet_calatorie_in_lista(l, pachet_calatorie)
    valideaza_pachet_calatorie(pachet_calatorie)