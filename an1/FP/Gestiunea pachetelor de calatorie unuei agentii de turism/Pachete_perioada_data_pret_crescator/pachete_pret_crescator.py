from validare_pachete.validare import valideaza_data
from Adauga_pachete_de_calatorie.adauga_pachete import *
from Sortare.sortare import sortare_pret

def pachete_perioada_data_pret_crescator(p,a,zi_inceput, luna_inceput, an_inceput, zi_sfarsit, luna_sfarsit, an_sfarsit):
    #functie care afiseaza pachetele de calatorie dintr-o perioada data, cu pretul ordonat crescator
    '''
    :param p: lista care contine toate pachetele de calatorie ale firmei
    :param a:   lista in care se vor salva pachetele din perioada data cu pretul ordonat crescator
    :param zi_inceput: ziua de inceput
    :param luna_inceput: luna de inceout
    :param an_inceput: anul de inceput
    :param zi_sfarsit: ziua de sfarsit
    :param luna_sfarsit: luna de sfarsit
    :param an_sfarsit: anul de sfarsit
    :return: lista a cu toate ofertele din perioada selectata cu pretl ordonat crescator
    raise Exception:"Nu avem oferte din aceasata perioada", daca nu exista ofrte din perioada selectata
    '''
    x=0
    epc=""
    valideaza_data(zi_inceput,luna_inceput,an_inceput,zi_sfarsit,luna_sfarsit, an_sfarsit)
    while x<len(p):
        if zi_data_inceput(p[x])>=zi_inceput and luna_data_inceput(p[x])>=luna_inceput and an_data_inceput(p[x])>=an_inceput:
            if zi_data_sfarsit(p[x])<=zi_sfarsit and luna_data_sfarsit(p[x])<=luna_sfarsit and an_data_sfarsit(p[x])<=an_sfarsit:
                a.append(p[x])
        x+=1
    if len(a)==0:
        epc+="Nu avem oferte din aceasata perioada"
    sortare_pret(a)
    if len(epc)>0:
        raise Exception(epc)