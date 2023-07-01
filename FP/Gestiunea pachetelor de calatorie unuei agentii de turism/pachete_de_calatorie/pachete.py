from Adauga_pachete_de_calatorie.adauga_pachete import adauga_pachet_de_calatorie

def pachte_de_calatorie():
    #Functie care adauga pachete de calatorie intr-o lista
    #input: ,
    #output: lista p care detine informatiile tutror pachetelor de calatorie
    p = []
    a = adauga_pachet_de_calatorie(12, 12, 2021, 16, 12, 2021, "Paris", 200.50)
    p.append(a)
    a = adauga_pachet_de_calatorie(3, 12, 2021, 13, 12, 2021, "Viena", 250.00)
    p.append(a)
    a = adauga_pachet_de_calatorie(19, 2, 2021, 28, 2, 2021, "Barcelona", 300.00)
    p.append(a)
    a = adauga_pachet_de_calatorie(30, 5, 2021, 7, 6, 2021, "Barcelona", 350.00)
    p.append(a)
    a = adauga_pachet_de_calatorie(5, 7, 2021, 20, 7,2021, "Barcelona", 750.00)
    p.append(a)
    a = adauga_pachet_de_calatorie(19, 2, 2021, 28, 2, 2021, "Buenos Aires", 7500.35)
    p.append(a)
    a = adauga_pachet_de_calatorie(25, 2, 2021, 28, 2, 2021, "Maldive", 7500.35)
    p.append(a)
    a = adauga_pachet_de_calatorie(20, 2, 2021, 7, 3, 2021, "Los Angeles", 7500.35)
    p.append(a)
    a = adauga_pachet_de_calatorie(22, 12, 2022, 27, 12, 2022, "Amsterdam", 26561245612476.30)
    p.append(a)
    a = adauga_pachet_de_calatorie(19, 2, 2021, 28, 2, 2021, "Paris", 500.00)
    p.append(a)
    return p
