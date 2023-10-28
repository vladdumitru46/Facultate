from validare_pachete.validare import valideaza_destinatie
from Adauga_pachete_de_calatorie.adauga_pachete import destinatie_calatorie

def stergere_pachete_destinatie_data(p,destinatie):
    #Functie care sterge toate pachetele de calatorie dintr-o destinatie selectata
    #input: p, toate pachetele de calatorie ale firmei
    #       destinatie, o destinatie selectata de catre utilizator
    #output: "Toate pachetele de calatorie din" destinatie "au fost sterse"
    #raises exception: "Nu avem pachete de calatorie in destinatia selectata!"
    x=0
    nr=0
    valideaza_destinatie(destinatie)
    esp=""
    while x<len(p):
        if destinatie_calatorie(p[x])==destinatie:
            nr+=1
            del p[x]
            x=x-1
        x+=1
    if nr<=0:
        esp+="Nu avem pachete de calatorie in destinatia selectata!"
    if len(esp)>0:
        raise Exception(esp)