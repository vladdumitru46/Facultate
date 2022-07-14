from Batch_mode.batch_mode import batch_mode
from UI_pachete_de_calatorie.Ui_adauga_in_lista import ui_adauga_pachet_de_calatorie,ui_batch_mode
from pachete_de_calatorie.pachete import pachte_de_calatorie
from Pachete_calatorie_data_de_sfarsit_data.P_data_de_sfarsit import tiparire_pachete_data_sfarsot
from pachete_cu_interval_de_calatorie_dat.Aceeasi_data import aceeasi_data
from Pachete_destinatie_data_pret_mai_mic.p_destinatie_pret import destinatie_pret_mic
from Numar_de_oferte_desinatie_data.nr_ofarte_dest_data import nr_oferte_destinatie
from Eliminare_oferte_pret_mai_mare_decat_cel_dat.eliminare_oferte import eliminare_oferte_pret_mai_mare
from Adauga_pachete_de_calatorie.adauga_pachete import pret_calatorie,zi_data_inceput,zi_data_sfarsit,luna_data_inceput,luna_data_sfarsit,an_data_inceput,an_data_sfarsit,destinatie_calatorie
from Elimina_oferte_luna_selectata.elimina_of_luna import elimina_oferte_luna_selectata
from Medie_pret_destinatie_data.medie_pret import medie_pret_dest_data
from Pachete_perioada_data_pret_crescator.pachete_pret_crescator import pachete_perioada_data_pret_crescator
from Stergere_pachete_calatorie_destinatie_data.stergere_pachete import stergere_pachete_destinatie_data
from Stergere_pachete_calatorie_durata_scurta_fata_de_zile_date.stergere_pachete_zile import stergere_pachete_duarata_mai_scurta
from Stergere_pachete_calatorie_pret_mai_mare_decat_o_suma_data.stergere_pachete_pret_mai_mare import stergere_pachete_pret_mai_mare_decat_o_suma_data
from Undo.undo import undo
from srv_pachete.srv_pachetee import srv_pachet_calatorie
from Adauga_pachete_de_calatorie_in_lista.adauga_lista import adauga_pachet_calatorie_in_lista


def pra(a):
    x=0
    while x<len(a):
        print("Din data de:", zi_data_inceput(a[x]), '/', luna_data_inceput(a[x]), '/',
              an_data_inceput(a[x]), "pana in data de:", zi_data_sfarsit(a[x]), '/',
              luna_data_sfarsit(a[x]), '/', an_data_sfarsit(a[x]))
        print("In:", destinatie_calatorie(a[x]))
        print("Cu pretul de:", pret_calatorie(a[x]), '\n')
        x += 1

def meniu():
    print("Agentia de Tursim Dumitru")


    print("1. Pentru a alege data, destinatia si pretul pe care il doriti, va rugam apasati '1'")
    print("2. Pentru a modifica datele introduse, va rugam apsati '2'")
    print("3. daca doriti sa stergeti toate ofertele din destinatia selectata, va rugam apasati '3'")
    print("4. Daca doriti sa stergeti toare ofertele, ale caror durata este mai scurta decat un numar de zile dat, va rugam apasati '4'")
    print("5. Daca doriti sa stergeti toate pachetele de calatorie care au pretul mai mare decat o suma dorita de catre dumneavoastra, va rugam apasati '5'")
    print("6. Daca doriti sa vedeti ofertele din perioada pe care o doriti, scrieti '6'")
    print("7. Daca doriti sa vedeti ofertele din destinatia selectata, scrieti '7'")
    print("8. Daca doriti sa vedeti toate ofertele cu o anumita data de final, scrieti '8'")
    print("9. Daca doriti sa vedeti mumarul de oferte din destinatia selectata, va rugam scrieti '9'")
    print("10. Daca doriti sa veedeti toate pachetele de calatorie dintr-o perioada selectata, cu pretul ordonat crescator, apasa '10'")
    print("11. Daca doriti sa vedeti media de pret pentru destinatia selectata, va rugam apasati '11'")
    print("12. Daca doriti sa eliminati ofertele cu pretul mai mare decat cel dorit de catre dumneavoastra apasati '12'")
    print("13. Daca doriti sa eliminati ofertele din luna selectata de catre dumneavoastra, apasati '13'")
    print("14. Daca doriti sa refaceti ultima operatie care a modificat pachetele de calatorie, va rugam sa scrieti 'undo'")
    print("15. Pentru iesirea din aplicatie, va rugam sa fiti siguri ca asta doriti. Daca sunteti 100% siguri ca doriti sa ne parasiti, scrieti 'exit'")

def run():
    l = []
    p = pachte_de_calatorie()
    undo_l=[]
    undo_ll=[]
    meniu()
    while True:
        cmd = input(">>>")
        if cmd == "exit":
            return
        elif cmd == "":
            continue
        elif cmd=='p':
            pra(p)
        elif cmd == "1":
            undo_ll.append(undo(l))
            try:
                ui_adauga_pachet_de_calatorie(l)
            except Exception as err:
                print(err)
        elif cmd == "2":
            l.pop()
            try:
                ui_adauga_pachet_de_calatorie(l)
            except Exception as err:
                print(err)
        elif cmd=='3':
            try:
                undo_l.append(undo(p))
                destinatie=input("Introduceti destinatia dorita:")
                try:
                    stergere_pachete_destinatie_data(p,destinatie)
                    print("Toate pachetele de calatorie din", destinatie, "au fost sterse")
                except Exception as esp:
                    print(esp)
            except Exception as gool:
                print(gool)
        elif cmd=='4':
            undo_l.append(undo(p))
            try:
                nrzile=int(input("Introduceti ce numar de zile doriti sa aiba calatoria dumneavoastra:"))
                try:
                    stergere_pachete_duarata_mai_scurta(p,nrzile)
                    print("Toate pachetele de calatorie care au o duarat mai scurta de", nrzile, "zile, au fost sterse")
                except Exception as enrs:
                    print(enrs)
            except Exception as gaga:
                print(gaga)
        elif cmd=='5':
            try:
                undo_l.append(undo(p))
                suma=int(input("introduceti ce pret doriti"))
                try:
                    stergere_pachete_pret_mai_mare_decat_o_suma_data(p,suma)
                    print("Toate pachetele de calatorie care au pretul mai mare decat", suma,"au fost sterse")
                except Exception as esp:
                    print(esp)
            except Exception as mak:
                print(mak)
        elif cmd == "6":
            aceeasi_data(p, l)
        elif cmd == "7":
            destinatie_pret_mic(p, l)
        elif cmd == "8":
            tiparire_pachete_data_sfarsot(p, l)
        elif cmd=="9":
            try:
                destinatie=input("Introduceti destinatia dorita:")
                try:
                    nr_oferte_destinatie(p,destinatie)
                except Exception as erd:
                    print(erd)
            except Exception as gigi:
                print(gigi)
        elif cmd=="10":
            a=[]
            try:
                an_inceput=int(input("Introduceti anul de inceput:"))
                luna_inceput = int(input("Introduceti luna de inceput:"))
                zi_inceput=int(input("Intrpduceti ziua de inceput:"))
                an_sfarsit = int(input("Introduceti anul de sfarsit:"))
                luna_sfarsit = int(input("Introduceti luna de sfarsit:"))
                zi_sfarsit = int(input("Intrpduceti ziua de sfarsit:"))
                try:
                    pachete_perioada_data_pret_crescator(p,a,zi_inceput,luna_inceput,an_inceput,zi_sfarsit,luna_sfarsit,an_sfarsit)
                    x=0
                    while x<len(a):
                        print("In perioada pe care a-ti ales-o, avem urmatoarele oferte:")
                        print("in data de:", zi_data_inceput(a[x]), '/', luna_data_inceput(a[x]), '/', an_data_inceput(a[x]),
                            "Pana in data de", zi_data_sfarsit(a[x]), '/', luna_data_sfarsit(a[x]), '/', an_data_sfarsit(a[x]),
                            " Destinatia:", destinatie_calatorie(a[x]), "cu pretul de:", pret_calatorie(a[x]), '\n')
                        x+=1
                except Exception as epc:
                    print(epc)
            except Exception as epp:
                print(epp)

        elif cmd=="11":
            try:
                destinatie=input("Introduceti o destinatie dorita:")
                try:
                    medie_pret_dest_data(p,destinatie)
                except Exception as ed:
                    print(ed)
            except Exception as edd:
                print(edd)

        elif cmd=="12":
            a=[]
            try:
                destinatie=input("Introduceti destinatia dorita:")
                try:
                    pret=float(input("Introduceti ce pret doriti:"))
                    try:
                        eliminare_oferte_pret_mai_mare(p,pret,destinatie,a)
                        print(
                            "Dupa eliminarea ofretelor, care au pretul mai mare decat cel dorit de dumneavastra, si cu destinatia diferita de cea pe care a-ti introdus-o, aceste oferte au mai ramas:")
                        x = 0
                        while x < len(a):
                            print("Din data de(Zi,Luna,An):", zi_data_inceput(a[x]), '/', luna_data_inceput(a[x]), '/',
                                an_data_inceput(a[x]), "->", zi_data_sfarsit(a[x]), '/', luna_data_sfarsit(a[x]), '/',
                                an_data_sfarsit(a[x]))
                            print("Destinatia:", destinatie_calatorie(a[x]))
                            print("Cu pretul de:", pret_calatorie(a[x]), '\n')
                            x += 1
                    except Exception as ex:
                        print(ex)
                except Exception as ep:
                    print(ep)
            except Exception as lala:
                print(lala)
        elif cmd == "13":
            a=[]
            try:
                luna=int(input("Introduceti ce luna doriti:"))
                try:
                    elimina_oferte_luna_selectata(p,luna,a)
                    print("Dupa eliminarea ofretelor, din luna aleasa de catre dumneavastra, aceste oferte au mai ramas:")
                    x = 0
                    while x < len(a):
                        print("Din data de(Zi,Luna,An):", zi_data_inceput(a[x]), '/', luna_data_inceput(a[x]), '/',
                            an_data_inceput(a[x]), "->", zi_data_sfarsit(a[x]), '/', luna_data_sfarsit(a[x]), '/',
                            an_data_sfarsit(a[x]))
                        print("Destinatia:", destinatie_calatorie(a[x]))
                        print("Cu pretul de:", pret_calatorie(a[x]), '\n')
                        x += 1
                except Exception as err:
                    print(err)
            except Exception as el:
                print(el)
        elif cmd=="undo":
            if len(undo_ll)>0:
                x = 0
                a = []
                p = []
                a = undo_ll.pop()
                while x < len(a):
                    print("Din data de:", zi_data_inceput(a[x]), '/', luna_data_inceput(a[x]), '/',
                        an_data_inceput(a[x]), "pana in data de:", zi_data_sfarsit(a[x]), '/',
                        luna_data_sfarsit(a[x]), '/', an_data_sfarsit(a[x]))
                    print("In:", destinatie_calatorie(a[x]))
                    print("Cu pretul de:", pret_calatorie(a[x]), '\n')
                    x += 1
                p = a[:]
            elif len(undo_l)==0:
               print("Nu se mai poate face undo!")
            else:
                x = 0
                a=[]
                p=[]
                a=undo_l.pop()
                while x < len(a):
                    print("Din data de:", zi_data_inceput(a[x]), '/', luna_data_inceput(a[x]), '/',
                        an_data_inceput(a[x]), "pana in data de:", zi_data_sfarsit(a[x]), '/',
                        luna_data_sfarsit(a[x]), '/', an_data_sfarsit(a[x]))
                    print("In:", destinatie_calatorie(a[x]))
                    print("Cu pretul de:", pret_calatorie(a[x]), '\n')
                    x+=1
                p=a[:]
        elif cmd=="batch_mode":
            a=[]
           # a[0]=pachte_de_calatorie()
            batch_mode(a)
        else:
            print("Comanda invalida! Va rugam introduceti o comanda din lista de meniu")

