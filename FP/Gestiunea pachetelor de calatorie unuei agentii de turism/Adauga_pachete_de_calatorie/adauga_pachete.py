def adauga_pachet_de_calatorie(zi_inceput, luna_inceput, an_inceput, zi_sfarsit, luna_sfarsit, an_sfarsit, destinatia,pret):
    '''
    in aceasta fuctie se vor afla datele despre un pachet de calatorie
    input
    :param data_inceput: data la care incepe calatoria
    :param data_sfarsit: data la care se termina calatoria
    :param destinatia: locul in care se doreste sa se calatoreasca
    :param pret: pretul calatoriei
    :return: pachetul de calatorie, continand data de incput: zi_incput, luna_incput, an_incput, data de sfarsit: zi_incput,luna_incput, an_incput, data_sfarsit, destinataia, destinatia si pretul, pret
    '''
    return [zi_inceput,luna_inceput,an_inceput,zi_sfarsit,luna_sfarsit,an_sfarsit,destinatia,pret]

def zi_data_inceput(pachet_calatorie):
    # functie care returneaza ziua de inceput al calatoriei
    # input: pachet_calatorie, un pachet de calatorie
    # output: zi_inceput- intreg>0 si <31
    return pachet_calatorie[0]


def luna_data_inceput(pachet_calatorie):
    # functie care returneaza luna de inceput al calatoriei
    # input: pachet_calatorie, un pachet de calatorie
    # output: luna_inceput- intreg>0 si <12
    return pachet_calatorie[1]


def an_data_inceput(pachet_calatorie):
    # functie care returneaza anul de inceput al calatoriei
    # input: pachet_calatorie, un pachet de calatorie
    # output: an_inceput- intreg>0
    return pachet_calatorie[2]


def zi_data_sfarsit(pachet_calatorie):
    # functie care returneaza ziua de sfasrit a calatoriei
    # input: pachet_calatorie, un pachet de calatorie
    # output: zi_sfarsit- intreg>0 si <31
    return pachet_calatorie[3]


def luna_data_sfarsit(pachet_calatorie):
    # functie care returneaza luna de sfarsit a calatoriei
    # input: pachet_calatorie, un pachet de calatorie
    # output: luna sfarsit- intreg>0 si <12
    return pachet_calatorie[4]


def an_data_sfarsit(pachet_calatorie):
    # functie care returneaza anul de sfarsit al calatoriei
    # input: pachet_calatorie, un pachet de calatorie
    # output: an_sfarsit- intreg>0
    return pachet_calatorie[5]


def pret_calatorie(pachet_calatorie):
    # functie care returneaza pretul calatoriei
    # input: pachet_calatorie,un pachet de calatorie
    # output: pretul calatoriei float>0
    return pachet_calatorie[7]


def destinatie_calatorie(pachet_calatorie):
    # functie care returenaza destinatie calatoriei
    # input: pachet_calatorie,un pachet de calatorie
    # output: destinatia calatoriei, sir de caractere
    return pachet_calatorie[6]
