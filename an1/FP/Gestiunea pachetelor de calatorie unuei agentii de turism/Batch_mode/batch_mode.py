from Adauga_pachete_de_calatorie.adauga_pachete import adauga_pachet_de_calatorie
from Adauga_pachete_de_calatorie_in_lista.adauga_lista import adauga_pachet_calatorie_in_lista
#from Run.runn import pra
from Calcul_nr_zile.nr_zile import nr_zile
from Stergere_pachete_calatorie_destinatie_data.stergere_pachete import stergere_pachete_destinatie_data
from Stergere_pachete_calatorie_pret_mai_mare_decat_o_suma_data.stergere_pachete_pret_mai_mare import stergere_pachete_pret_mai_mare_decat_o_suma_data
from Stergere_pachete_calatorie_durata_scurta_fata_de_zile_date.stergere_pachete_zile import stergere_pachete_duarata_mai_scurta
from Adauga_pachete_de_calatorie.adauga_pachete import *
from Undo.undo import undo
from srv_pachete.srv_pachetee import srv_pachet_calatorie


#sterge 1 Barcelona add 2021 2 19 2021 2 28 Barcelona 333 print add 2021 3 30 2021 4 15 Haway 22828282 print sterge 2 400 print undo add 2021 2 19 2021 2 28 Brasov 333 undo !

def batch_mode(l):
    text=input("Introduceti comeziile:")
    comand=text.split()
    i=0
    undo_l=[]
    while i<len(comand):
        if comand[i]=='add':
            srv_pachet_calatorie(l,int(comand[i+3]),int(comand[i+2]),int(comand[i+1]),int(comand[i+6]),int(comand[i+5]),int(comand[i+4]),comand[i+7],int(comand[i+8]))
            undo_l.append(undo(l))
            print("Pachetul de calatorie a fost adaugat cu succes")
        elif comand[i]=='print':
            x = 0
            while x < len(l):
                print("Din data de:", zi_data_inceput(l[x]), '/', luna_data_inceput(l[x]), '/',an_data_inceput(l[x]), "pana in data de:", zi_data_sfarsit(l[x]), '/',
                    luna_data_sfarsit(l[x]), '/', an_data_sfarsit(l[x]))
                print("In:", destinatie_calatorie(l[x]))
                print("Cu pretul de:", pret_calatorie(l[x]), '\n')
                x += 1
        elif comand[i]=='sterge':
            if comand[i+1]=='1':
                nr=0
                undo_l.append(undo(l))
                for x in l:
                    if destinatie_calatorie(x)!=comand[i+2]:
                        nr+=1
                if nr==len(l):
                    print("Nu avem ce pachete de calatorie sa stergem")
                else:
                    stergere_pachete_destinatie_data(l,comand[i+2])
            elif comand[i+1]=='2':
                undo_l.append(undo(l))
                nr = 0
                for x in l:
                    if pret_calatorie(x) - int(comand[i + 2]) <= 0:
                        nr += 1
                if nr == len(l):
                    print("Nu avem ce pachete de calatorie sa stergem")
                else:
                    stergere_pachete_pret_mai_mare_decat_o_suma_data(l,int(comand[i+2]))
            elif comand[i+1]=='3':
                undo_l.append(undo(l))
                nr = 0
                for x in l:
                    nrz = nr_zile(zi_data_inceput(x), luna_data_inceput(x), an_data_inceput(x),
                                  zi_data_sfarsit(x), an_data_sfarsit(x), luna_data_sfarsit(x))
                    if nrz >= int(comand[i + 2]):
                        nr += 1
                if nr == len(l):
                    print("Nu avem ce pachete de calatorie sa stergem")
                else:
                    stergere_pachete_duarata_mai_scurta(l,int(comand[i+2]))
            print("Stergerea a fost efectuata cu succes!")
        elif comand[i]== 'undo':
            if len(undo_l)==0:
               print("Nu poate face undo!")
            else:
                print("undo a fost efectuata")
                x = 0
                a=[]
                l=[]
                a=undo_l.pop()
                while x < len(a):
                    print("Din data de:", zi_data_inceput(a[x]), '/', luna_data_inceput(a[x]), '/',
                        an_data_inceput(a[x]), "pana in data de:", zi_data_sfarsit(a[x]), '/',
                        luna_data_sfarsit(a[x]), '/', an_data_sfarsit(a[x]))
                    print("In:", destinatie_calatorie(a[x]))
                    print("Cu pretul de:", pret_calatorie(a[x]), '\n')
                    x+=1
                l=a[:]

        elif comand[i]=='!':
            return
        i+=1