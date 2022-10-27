from validare_pachete.validare import valideaza_luna
from Adauga_pachete_de_calatorie.adauga_pachete import pret_calatorie, zi_data_inceput, zi_data_sfarsit, luna_data_inceput, luna_data_sfarsit, an_data_inceput, an_data_sfarsit, destinatie_calatorie


def elimina_oferte_luna_selectata(p, luna, a):
    #Eliminarea ofertelor în care sejurul presupune zile dintr-o anumitălună (citită de la tastatură sub forma unui număr natural între 1 și 12)
    # input: pachetele de calatorie, p
    #        datele adaugate de la tasatura de catre utilizator, memorate in lista l
    #        o lista goala,a , in care se vor memora elementele care nu se elimina din p
    # output: lista a, cu toate elementele din lista p, care nu sunt eliminate
    # Exceptiom: daca nu exista pachete de calatorie din luna selectata, se afiseaza mesajul:
    #  "Nu avem ce oferte sa eliminam, din luna selectata"
    valideaza_luna(luna)
    x = 0
    err=""
    while x < len(p):
        if luna_data_inceput(p[x]) != luna:
            a.append(p[x])
        x += 1
    if len(a)==0:
        err+="Nu avem ce oferte sa eliminam, din luna selectata"
    if len(err)>0:
        raise Exception(err)

