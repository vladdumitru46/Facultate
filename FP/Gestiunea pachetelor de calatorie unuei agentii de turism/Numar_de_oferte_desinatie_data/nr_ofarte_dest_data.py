from Adauga_pachete_de_calatorie.adauga_pachete import destinatie_calatorie
from validare_pachete.validare import valideaza_destinatie
def nr_oferte_destinatie(p, destinatie):
    # Tipărirea pachetelor de călătorie cu o destinație dată și cu preț mai mic decât o sumă dată
    nr = 0
    x = 0
    erd=""
    valideaza_destinatie(destinatie)
    while x < len(p):
        if destinatie_calatorie(p[x]) == destinatie:
                nr += 1
        x += 1
    if nr == 0:
        erd+="Nu avem oferte pentru destinatia selectata"
    else:
        print("Pentru destinatia",destinatie,"avem ", nr, " oferte")
    if len(erd)>0:
        raise Exception(erd)