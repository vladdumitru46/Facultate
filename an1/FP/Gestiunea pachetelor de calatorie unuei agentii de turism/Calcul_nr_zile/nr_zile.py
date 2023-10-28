from validare_pachete.validare import valideaza_data



def nr_zile(zi_inceput, luna_inceput, an_inceput, zi_sfarsit, an_sfarsit, luna_sfarsit):
    valideaza_data(zi_inceput, luna_inceput, an_inceput, zi_sfarsit, luna_sfarsit, an_sfarsit)
    nrzile = 0
    if zi_inceput < zi_sfarsit:
        nrzile = zi_sfarsit - zi_inceput
    elif luna_inceput < luna_sfarsit:
            if luna_inceput == 4 or luna_inceput == 6 or luna_inceput == 9 or luna_inceput == 11:
                nrzile=(30-zi_inceput+1)+zi_sfarsit
            elif luna_inceput == 2:
                if an_inceput % 4 == 0:
                    nrzile=(28-zi_inceput+1)+zi_sfarsit
                else:
                    nrzile=(29-zi_inceput+1)+zi_sfarsit
            else:
                nrzile = (31 - zi_inceput + 1) + zi_sfarsit
    else:
        if an_inceput<an_sfarsit:
            if luna_inceput == 4 or luna_inceput == 6 or luna_inceput == 9 or luna_inceput == 11:
                nrzile=(30-zi_inceput+1)+zi_sfarsit
            elif luna_inceput == 2:
                if an_inceput % 4 == 0:
                    nrzile=(28-zi_inceput+1)+zi_sfarsit
                else:
                    nrzile=(29-zi_inceput+1)+zi_sfarsit
            else:
                nrzile = (31 - zi_inceput + 1) + zi_sfarsit
    return nrzile