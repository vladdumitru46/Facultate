from Adauga_pachete_de_calatorie.adauga_pachete import destinatie_calatorie,pret_calatorie
from validare_pachete.validare import valideaza_destinatie

def medie_pret_dest_data(p,destinatie):
    s=0
    nr=0
    x=0
    ed=""
    valideaza_destinatie(destinatie)
    while x<len(p):
        if destinatie_calatorie(p[x])==destinatie:
            s+=pret_calatorie(p[x])
            nr+=1
        x+=1
    if nr==0:
        ed+="Nu avem o medie de pret pentru destinatia introdusa"
    if len(ed)>0:
        raise Exception(ed)
    if nr>0:
        print("Media de pret pentru", destinatie, "este", float(s / nr))