from Adauga_pachete_de_calatorie.adauga_pachete import *
from validare_pachete.validare import valideaza_pret,valideaza_destinatie

def eliminare_oferte_pret_mai_mare(p,pret,destinatie,a):
    #Eliminarea ofertelor care au un preț mai mare decât cel dat și o destinație diferită de cea citită de la tastatură
    #input: pachetele de calatorie, p
    #       datele adaugate de la tasatura de catre utilizator, memorate in lista l
    #       o lista goala,a , in care se vor memora elementele care nu se elimina din p
    #output: lista a, cu toate eelementele din lista p, care nu sunt eliminate
    # Exceptiom: daca nu exista pachete de calatorie cu pretul mai mic decat cel dorit de catre utilizator, se printeaza mesajul:
    #  "Nu avem ce oferte sa eliminam"
    x = 0
    valideaza_destinatie(destinatie)
    valideaza_pret(pret)

    ex = ""
    while x < len(p):
        if pret-pret_calatorie(p[x])>=0 and destinatie_calatorie(p[x])!=destinatie:
            a.append(p[x])
        x += 1
    if len(a)==0:
        ex+="Nu avem ce oferte sa eliminam"
    if len(ex)>0:
        raise Exception(ex)