from Adauga_pachete_de_calatorie.adauga_pachete import an_data_sfarsit,an_data_inceput,zi_data_sfarsit,zi_data_inceput,luna_data_sfarsit,luna_data_inceput,destinatie_calatorie,pret_calatorie


def destinatie_pret_mic(p, l):
    # Tipărirea pachetelor de călătorie cu o destinație dată și cu preț mai mic decât o sumă dată
    #input: lista l, cu datele introduse de la tastatura
    #       lista p, continad pachetek de calatorie ale firmei
    #output: pachetele de calatorie, din lista p, care au o destinație dată și cu preț mai mic decât o sumă dată
    nr = 0
    x = 0
    print("In destinatia selectata", destinatie_calatorie(l[len(l) - 1]), "avem urmatoarele oferte:")
    while x < len(p):
        if destinatie_calatorie(p[x]) == destinatie_calatorie(l[len(l) - 1]):
            if pret_calatorie(p[x]) < pret_calatorie(l[len(l) - 1]):
                print("in data de:", zi_data_inceput(p[x]), '/', luna_data_inceput(p[x]), '/', an_data_inceput(p[x]), "Pana in data de:",zi_data_sfarsit(p[x]), '/', luna_data_sfarsit(p[x]), '/', an_data_sfarsit(p[x]), "Cu pretul de:",pret_calatorie(p[x]))
                nr += 1
        x += 1
    if nr == 0:
        print("Nu avem oferte in ", destinatie_calatorie(l[len(l) - 1]))
