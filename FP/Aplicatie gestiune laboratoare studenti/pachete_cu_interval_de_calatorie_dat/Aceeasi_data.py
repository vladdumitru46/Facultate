from Adauga_pachete_de_calatorie.adauga_pachete import an_data_sfarsit,an_data_inceput,zi_data_sfarsit,zi_data_inceput,luna_data_sfarsit,luna_data_inceput,destinatie_calatorie,pret_calatorie


def aceeasi_data(p, l):
# Tipărirea pachetelor de călătorie care presupun un sejur într-un interval dat (e.g. 10/08/2018 - 24/08/2018 - un pachet valid este cel a cărui dată de început este aceeași sau după de data de început citită și data de sfârșit este înainte sau aceeași cu data de sfârșit introdusă de la tastatură)
    x = 0
    nr = 0
    while x < len(p):
        if zi_data_inceput(p[x]) >= zi_data_inceput(l[len(l) - 1]) and luna_data_inceput(p[x]) >= luna_data_inceput(
            l[len(l) - 1]) and an_data_inceput(p[x]) >= an_data_inceput(l[len(l) - 1]):
            if zi_data_sfarsit(p[x]) <= zi_data_sfarsit(l[len(l) - 1]) and luna_data_sfarsit(p[x]) <= luna_data_sfarsit(
                l[len(l) - 1]) and an_data_sfarsit(p[x]) <= an_data_sfarsit(l[len(l) - 1]):
                print("In perioada pe care a-ti ales-o, avem urmatoarele oferte:")
                print("in data de:", zi_data_inceput(p[x]), '/', luna_data_inceput(p[x]), '/', an_data_inceput(p[x]), "Pana in data de", zi_data_sfarsit(p[x]), '/', luna_data_sfarsit(p[x]), '/', an_data_sfarsit(p[x]), " Destinatia:", destinatie_calatorie(p[x]), "cu pretul de:", pret_calatorie(p[x]), '\n')
                nr += 1
        x += 1
    if nr == 0:
        print("Din pacate nu avem oferte din perioada pe care a-ti selectat-o")
