from validare_pachete.validare import valideaza_pret
from Adauga_pachete_de_calatorie.adauga_pachete import pret_calatorie

def stergere_pachete_pret_mai_mare_decat_o_suma_data(p,suma):
    #Functie care sterge toate pachetele de calatorie care au pretul mai mare decat o suma data de catre utilizator
    #incput: p- toate pachetele de calatorie ale firemei
    #        suma- o suma data de catre utilizator
    #output: "Toate pachetele de calatorie care au pretul mai mare decat", suma,"au fost sterse"
    #raise EXception: "Nu avem ce pachete de calatrie sa stergem"
    valideaza_pret(suma)
    nr = 0
    err = ""
    x = 0
    while x < len(p):
        if pret_calatorie(p[x]) >= suma:
            del p[x]
            nr += 1
            x = x - 1
        x += 1

    if nr == 0:
        err += "Nu avem ce pachete de calatrie sa stergem"
    if len(err) > 0:
        raise Exception(err)