from srv_pachete.srv_pachetee import srv_pachet_calatorie
from Adauga_pachete_de_calatorie.adauga_pachete import adauga_pachet_de_calatorie

def ui_adauga_pachet_de_calatorie(l):
    try:
        an_inceput = int(input("Introduceti anul in care doriti sa calatoriti:"))
    except ValueError:
        print("An invalid! Va rugam sa introduceti un an valid")
        return
    try:
        luna_inceput = int(input("Introduceti luna in care doriti sa calatoriti:"))
    except ValueError:
        print("Luna invalida! Va rugam sa introduceti o luna valida!")
        return
    try:
        zi_inceput = int(input("Introduceti ziua in care doriti sa calatoriti:"))
    except ValueError:
        print("Zi invalida! Va rugam sa introduceti o zi valida!")
        return
    try:
        an_sfarsit = int(input("Introduceti anul la care doriti sa incheiati calatoria:"))
    except ValueError:
        print("An invalid! Va rugam sa introduceti un an valid!")
        return
    try:
        luna_sfarsit = int(input("Introduceti luna in care doriti sa incheiati calatoria:"))
    except ValueError:
        print("Luna invalida! Va rugam sa introduceti o luna valida!")
        return
    try:
        zi_sfarsit = int(input("Introduceti ziua in care doriti sa va incheiati calatoria:"))
    except ValueError:
        print("Zi invalida! Va rugam sa introduceti o zi valida!")
        return
    destinatie = input("Introduceti destinatia in care doriti sa calatoriti:")
    try:
        pret = float(input("Va rugam sa introduceti pretul pe care il doriti pentru calatoria dumneavoastra:$"))
    except ValueError:
        print("Pret invalid! Va rugam sa introduceti un nou pret")
        return
    srv_pachet_calatorie(l, zi_inceput, luna_inceput, an_inceput, zi_sfarsit, luna_sfarsit, an_sfarsit, destinatie, pret)


def ui_batch_mode(l):

    pass