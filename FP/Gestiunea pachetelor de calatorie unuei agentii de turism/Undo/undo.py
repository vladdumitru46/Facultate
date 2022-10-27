from Adauga_pachete_de_calatorie.adauga_pachete import *

def undo(p):
    #Functie care reface ultima operatie
    #input: lista undo
    undo=[]
    for x in p:
        nx = x[:]
        undo.append(nx)
    return undo