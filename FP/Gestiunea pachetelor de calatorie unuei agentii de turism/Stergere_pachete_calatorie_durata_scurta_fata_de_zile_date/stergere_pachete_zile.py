from Calcul_nr_zile.nr_zile import nr_zile
from Adauga_pachete_de_calatorie.adauga_pachete import zi_data_sfarsit,zi_data_inceput,luna_data_sfarsit,luna_data_inceput,an_data_sfarsit,an_data_inceput
from validare_pachete.validare import validare_nrzile
def stergere_pachete_duarata_mai_scurta(p,nrzile):
    #funtie care sterge toate pachetele de calarttorie cu o durata mai scurta decat un numar de zile dat
    #input: p- toate pachetele de calatorie ale firmei
    #       nrzile: numarul de zile dorit de catre utilizator
    #output: "Toate pachetele de calatorie care au o duarat mai scurta de" nrzile "au fost sterse"
    #raise Exception: "Nu avem ce pachete de calatorie sa stergem!"
    validare_nrzile(nrzile)
    x=0
    nrz = 0
    nr=0
    enrs=""
    while x<len(p):
        nrz=nr_zile(zi_data_inceput(p[x]),luna_data_inceput(p[x]),an_data_inceput(p[x]),zi_data_sfarsit(p[x]),an_data_sfarsit(p[x]),luna_data_sfarsit(p[x]))
        if nrz<nrzile:
            del p[x]
            nr+=1
            x-=1
        x+=1
    if nr==0:
        enrs+="Nu avem ce pachete de calatorie sa stergem!"
    if len(enrs)>0:
        raise Exception(enrs)