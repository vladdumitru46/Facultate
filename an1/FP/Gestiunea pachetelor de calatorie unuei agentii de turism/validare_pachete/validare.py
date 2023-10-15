from Adauga_pachete_de_calatorie.adauga_pachete import an_data_sfarsit,an_data_inceput,zi_data_sfarsit,zi_data_inceput,luna_data_sfarsit,luna_data_inceput,destinatie_calatorie,pret_calatorie

def valideaza_pachet_calatorie(pachet_calatorie):
    # functie care verifica daca ziua de incput si de sfarsit sunt mai mari decat 0 si mai mici decat 31, luna de incput si de sfarsit sunt mai mari decat 0 si mai mici decat 12, daca anul de incput este mai mare sau egal cu 2021, pretul este mai mare decat 0 si destinatia este nevida
    # input: pachet_calatorie- un pachet de calatorie
    # output: , daca pachetul este valid
    # raises: Exception cu textul
    # "zi de incput invalida\n", daca ziua de inceput este <1 sau >31
    # "luna de inceput invalida\n", daca luna de inceput este <1 sau >12
    # "an de inceput invalid\n", daca anul de inceput este <2021
    # "zi de sfarsit invalida\n", daca ziua de sfarsit este <1 sau >31
    # "luna de sfarsit invalida\n", daca luna de sfarsit este <1 sau >12
    # "an de sfarsit invalid\n", daca anul de sfarsit este <2021
    # "destinatie invalida\n", daca destinatia este vida
    # "pret invalid\n", daca pretu este <=0
    err = ""
    if an_data_inceput(pachet_calatorie) < 2021:
        err += "An de inceput invalid\n"
    if luna_data_inceput(pachet_calatorie) < 1 or luna_data_inceput(pachet_calatorie) > 12:
        err += "Luna de inceput invalida\n"
    if luna_data_inceput(pachet_calatorie) == 4 or luna_data_inceput(pachet_calatorie) == 6 or luna_data_inceput(
        pachet_calatorie) == 9 or luna_data_inceput(pachet_calatorie) == 11:
        if zi_data_inceput(pachet_calatorie) < 1 or zi_data_inceput(pachet_calatorie) > 30:
            err += "Zi de inceput invalida\n"
    elif luna_data_inceput(pachet_calatorie) == 2:
        if an_data_inceput(pachet_calatorie) % 4 == 0:
            if zi_data_inceput(pachet_calatorie) < 1 or zi_data_inceput(pachet_calatorie) > 29:
                err += "Zi de inceput invalida\n"
        else:
            if zi_data_inceput(pachet_calatorie) < 1 or zi_data_inceput(pachet_calatorie) > 28:
                err += "Zi de inceput invalida\n"
    else:
        if zi_data_inceput(pachet_calatorie) < 1 or zi_data_inceput(pachet_calatorie) > 31:
            err += "Zi de inceput invalida\n"
    if an_data_sfarsit(pachet_calatorie) < 2021 or an_data_sfarsit(pachet_calatorie) < an_data_inceput(pachet_calatorie):
        err += "An de sfarsit invalid\n"
    if luna_data_sfarsit(pachet_calatorie) < 1 or luna_data_sfarsit(pachet_calatorie) > 12:
        err += "Luna de sfarsit invalida\n"
    if an_data_sfarsit(pachet_calatorie) == an_data_inceput(pachet_calatorie) and luna_data_sfarsit(
        pachet_calatorie) < luna_data_inceput(pachet_calatorie):
        err += "Luna de sfarsit invalida\n"
    if an_data_sfarsit(pachet_calatorie) == an_data_inceput(pachet_calatorie) and luna_data_sfarsit(
        pachet_calatorie) == luna_data_inceput(pachet_calatorie) and zi_data_sfarsit(
        pachet_calatorie) < zi_data_inceput(pachet_calatorie):
        err += "Zi de sfarsit invalida\n"
    if luna_data_sfarsit(pachet_calatorie) == 4 or luna_data_sfarsit(pachet_calatorie) == 6 or luna_data_sfarsit(
        pachet_calatorie) == 9 or luna_data_sfarsit(pachet_calatorie) == 11:
        if zi_data_sfarsit(pachet_calatorie) < 1 or zi_data_sfarsit(pachet_calatorie) > 30:
            err += "Zi de sfarsit invalida\n"
    elif luna_data_sfarsit(pachet_calatorie) == 2:
        if an_data_sfarsit(pachet_calatorie) % 4 == 0:
            if zi_data_sfarsit(pachet_calatorie) < 1 or zi_data_sfarsit(pachet_calatorie) > 29:
                err += "Zi de sfarsit invalida\n"
        else:
            if zi_data_sfarsit(pachet_calatorie) < 1 or zi_data_sfarsit(pachet_calatorie) > 28:
                err += "Zi de sfarsit invalida\n"
    else:
        if zi_data_sfarsit(pachet_calatorie) < 1 or zi_data_sfarsit(pachet_calatorie) > 31:
            err += "Zi de sfarsit invalida\n"
    if pret_calatorie(pachet_calatorie) <= 0:
        err += "Pret invalid\n"
    if destinatie_calatorie(pachet_calatorie) == "":
        err += "Destinatie invalida\n"
    if len(err) > 0:
        raise Exception(err)


def valideaza_luna(luna):
   # Functie care valideaza luna
   #input: luna, o luna
   #output: , daca luna este valida
   #raise Exception: "Luna invalida!, daca luna<1sau luna>12
    el=""
    if luna<1 or luna>12:
        el+="Luna invalida!"
    if len(el)>0:
        raise Exception(el)

def valideaza_pret(pret):
    #Functie care valideaza pretul
    #input:pret, un pret de calatorie
    #output: , daca pretul este valid
    #raise Exception: "Pret invalid!", daca pretul este <=0
    ep=""
    if pret<=0:
        ep+="Pret invalid!"
    if len(ep)>0:
        raise Exception(ep)


def valideaza_destinatie(destinatie):
    #Functie care valideaza destinatia
    #input: destinatie, o destinatie
    #output: ,daac destinatia este valida
    #raise Exeception: "Destinatie invalida!", daca destinatie=""

    edd=""
    if destinatie=="":
        edd+="Destinatie inavlida!"
    if len(edd)>0:
        raise Exception(edd)


def valideaza_data(zi_inceput, luna_inceput, an_inceput,zi_sfarsit, luna_sfarsit, an_sfarsit):
    #Functie care valideaza data
    #input: zi_inceput: ziua de inceput
    #       luna_inceput:luna de inceput
    #       an_inceput:anul de inceput
    #       zi_sfarsit: ziua de sfarsit
    #       luna_sfasrit: luna de sfarsit
    #       an_sfarsit: anul de sfarsit
    #output: , daca data este valida
    #raise Exception:
    #"zi de incput invalida\n", daca ziua de inceputeste < 1 sau > 31
    # "luna de inceput invalida\n", daca luna de inceput este <1 sau >12
    # "an de inceput invalid\n", daca anul de inceput este <2021
    # "zi de sfarsit invalida\n", daca ziua de sfarsit este <1 sau >31
    # "luna de sfarsit invalida\n", daca luna de sfarsit este <1 sau >12
    # "an de sfarsit invalid\n", daca anul de sfarsit este <2021
    err = ""
    if an_inceput < 2021:
        err += "An de inceput invalid\n"
    if luna_inceput < 1 or luna_inceput > 12:
        err += "Luna de inceput invalida\n"
    if luna_inceput == 4 or luna_inceput == 6 or luna_inceput == 9 or luna_inceput == 11:
        if zi_inceput < 1 or zi_inceput > 30:
            err += "Zi de inceput invalida\n"
    elif luna_inceput == 2:
        if an_inceput % 4 == 0:
            if zi_inceput < 1 or zi_inceput > 29:
                err += "Zi de inceput invalida\n"
        else:
            if zi_inceput < 1 or zi_inceput > 28:
                err += "Zi de inceput invalida\n"
    else:
        if zi_inceput < 1 or zi_inceput > 31:
            err += "Zi de inceput invalida\n"
    if an_sfarsit < 2021 or an_sfarsit < an_inceput:
        err += "An de sfarsit invalid\n"
    if luna_sfarsit < 1 or luna_sfarsit > 12:
        err += "Luna de sfarsit invalida\n"
    if an_sfarsit == an_inceput and luna_sfarsit < luna_inceput:
        err += "Luna de sfarsit invalida\n"
    if an_sfarsit == an_inceput and luna_sfarsit == luna_inceput and zi_sfarsit < zi_inceput:
        err += "Zi de sfarsit invalida\n"
    if luna_sfarsit == 4 or luna_sfarsit == 6 or luna_sfarsit == 9 or luna_sfarsit == 11:
        if zi_sfarsit < 1 or zi_sfarsit > 30:
            err += "Zi de sfarsit invalida\n"
    elif luna_sfarsit == 2:
        if an_sfarsit % 4 == 0:
            if zi_sfarsit < 1 or zi_sfarsit > 29:
                err += "Zi de sfarsit invalida\n"
        else:
            if zi_sfarsit < 1 or zi_sfarsit > 28:
                err += "Zi de sfarsit invalida\n"
    else:
        if zi_sfarsit < 1 or zi_sfarsit > 31:
            err += "Zi de sfarsit invalida\n"
    if len(err)>0:
        raise Exception(err)


def validare_nrzile(nrzile):
    #functie care valideaza un numar de zile citit de la tastatura
    #input nrile, un numar de zile
    #output , daca nrzile este >0
    #raise Exception: "Numar de zile invaid!"
    err=""
    if nrzile<=0:
        err+="Numar de zile invalid!"
    if len(err)>0:
        raise Exception(err)