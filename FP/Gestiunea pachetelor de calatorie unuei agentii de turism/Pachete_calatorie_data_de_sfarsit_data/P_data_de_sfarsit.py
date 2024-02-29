from Adauga_pachete_de_calatorie.adauga_pachete import an_data_sfarsit,an_data_inceput,zi_data_sfarsit,zi_data_inceput,luna_data_sfarsit,luna_data_inceput,destinatie_calatorie,pret_calatorie


def tiparire_pachete_data_sfarsot(p, l):
    x = 0
    nr = 0
    while x < len(p):
        if zi_data_sfarsit(p[x]) == zi_data_sfarsit(l[len(l) - 1]) and luna_data_sfarsit(p[x]) == luna_data_sfarsit(l[len(l) - 1]) and an_data_sfarsit(p[x]) == an_data_sfarsit(l[len(l) - 1]):
            print("in data de:", zi_data_inceput(p[x]), '/', luna_data_inceput(p[x]), '/', an_data_inceput(p[x]),"Pana in data de", zi_data_sfarsit(p[x]), '/', luna_data_sfarsit(p[x]), '/', an_data_sfarsit(p[x])," Destinatia:", destinatie_calatorie(p[x]), "cu pretul de:", pret_calatorie(p[x]), '\n')
            nr += 1
        x += 1
    if nr == 0:
        print("Nu avem oferte cu aceasta data de sfarsit", zi_data_sfarsit(l[len(l) - 1]), '/',luna_data_sfarsit(l[len(l) - 1]), '/', an_data_sfarsit(l[len(l) - 1]))
