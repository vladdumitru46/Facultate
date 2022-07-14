from Adauga_pachete_de_calatorie.adauga_pachete import *
from validare_pachete.validare import valideaza_pachet_calatorie
from srv_pachete.srv_pachetee import srv_pachet_calatorie
from Adauga_pachete_de_calatorie_in_lista.adauga_lista import adauga_pachet_calatorie_in_lista
from Eliminare_oferte_pret_mai_mare_decat_cel_dat.eliminare_oferte import eliminare_oferte_pret_mai_mare
from pachete_de_calatorie.pachete import pachte_de_calatorie
from Elimina_oferte_luna_selectata.elimina_of_luna import elimina_oferte_luna_selectata
from Medie_pret_destinatie_data.medie_pret import medie_pret_dest_data
from Pachete_perioada_data_pret_crescator.pachete_pret_crescator import pachete_perioada_data_pret_crescator
from Stergere_pachete_calatorie_destinatie_data.stergere_pachete import stergere_pachete_destinatie_data
from Stergere_pachete_calatorie_durata_scurta_fata_de_zile_date.stergere_pachete_zile import stergere_pachete_duarata_mai_scurta
from Calcul_nr_zile.nr_zile import nr_zile
from Stergere_pachete_calatorie_pret_mai_mare_decat_o_suma_data.stergere_pachete_pret_mai_mare import stergere_pachete_pret_mai_mare_decat_o_suma_data


def test_srv_pachet_calatorie():
    l = []
    assert (len(l) == 0)
    srv_pachet_calatorie(l, 12, 12, 2021, 3, 1, 2022, "Haway", 20000.32)
    assert (len(l) == 1)
    try:
        srv_pachet_calatorie(l, 35, 12, 2021, 3, 15, 2022, "HAway", 20000.32)
        assert False
    except Exception as err:
        assert (str(err) == "Zi de inceput invalida\nLuna de sfarsit invalida\n")


def test_valideaza_pachet_calatorie():
    pachet_calatorie = adauga_pachet_de_calatorie(12, 2, 2021, 13, 3, 2021, "Caraibe", 20000.53)
    valideaza_pachet_calatorie(pachet_calatorie)
    invalid_zi_inceput = adauga_pachet_de_calatorie(0, 2, 2021, 13, 3, 2021, "Caraibe", 20000.53)
    try:
        valideaza_pachet_calatorie(invalid_zi_inceput)
        assert False
    except Exception as err:
        assert(str(err) == "Zi de inceput invalida\n")
        invalid_pachet_calatorie = adauga_pachet_de_calatorie(32, 13, 2000, 233, 13, 1999, "", -233333)
    try:
        valideaza_pachet_calatorie(invalid_pachet_calatorie)
        assert (False)
    except Exception as err:
        assert (str(err) == "An de inceput invalid\nLuna de inceput invalida\nZi de inceput invalida\nAn de sfarsit invalid\nLuna de sfarsit invalida\nZi de sfarsit invalida\nPret invalid\nDestinatie invalida\n")


def test_adauga_pachet_calatorie_in_lista():
    l = []
    assert (len(l) == 0)
    pachet_calatorie = adauga_pachet_de_calatorie(2, 12, 2021, 15, 1, 2022, "Bermude", 2000.53)
    adauga_pachet_calatorie_in_lista(l, pachet_calatorie)
    assert (len(l) == 1)
    assert (zi_data_inceput(pachet_calatorie) == zi_data_inceput(l[0]))
    assert (luna_data_inceput(pachet_calatorie) == luna_data_inceput(l[0]))
    assert (an_data_inceput(pachet_calatorie) == an_data_inceput(l[0]))
    assert (zi_data_sfarsit(pachet_calatorie) == zi_data_sfarsit(l[0]))
    assert (luna_data_sfarsit(pachet_calatorie) == luna_data_sfarsit(l[0]))
    assert (an_data_sfarsit(pachet_calatorie) == an_data_sfarsit(l[0]))
    assert (destinatie_calatorie(pachet_calatorie) == destinatie_calatorie(l[0]))
    assert (abs(pret_calatorie(pachet_calatorie) - pret_calatorie(l[0])) < 0.000001)


def test_adauga_pachetul_de_calatorie():
    zi_inceput = 23
    luna_inceput = 2
    an_inceput = 2021
    zi_sfarsit = 2
    luna_sfarsit = 3
    an_sfarsit = 2021
    pret = 200.2
    destinatie = "Caraibe"
    pachet_calatorie = adauga_pachet_de_calatorie(zi_inceput, luna_inceput, an_inceput, zi_sfarsit, luna_sfarsit,
                                              an_sfarsit, destinatie, pret)
    assert (zi_data_inceput(pachet_calatorie) == zi_inceput)
    assert (luna_data_inceput(pachet_calatorie) == luna_inceput)
    assert (an_data_inceput(pachet_calatorie) == an_inceput)
    assert (zi_data_sfarsit(pachet_calatorie) == zi_sfarsit)
    assert (luna_data_sfarsit(pachet_calatorie) == luna_sfarsit)
    assert (an_data_sfarsit(pachet_calatorie) == an_sfarsit)
    assert (abs(pret_calatorie(pachet_calatorie) - pret) < 0.00001)
    assert (destinatie_calatorie(pachet_calatorie) == destinatie)



def test_elimina_pachet_calatorie_pret_mai_mare():
    l = 400
    destinatie="Barcelona"
    p = pachte_de_calatorie()
    a = []
    assert(len(a)==0)
    eliminare_oferte_pret_mai_mare(p,l,destinatie,a)
    assert(len(a)>0)
    a=[]
    l=1
    destinatie="lala"
    try:
        eliminare_oferte_pret_mai_mare(p,l,destinatie,a)
        assert False
    except Exception as ex:
        assert(str(ex)=="Nu avem ce oferte sa eliminam")


def test_elimina_pachet_calatorie_luna_egala():
    luna=2
    p = pachte_de_calatorie()
    a = []
    assert (len(a) == 0)
    elimina_oferte_luna_selectata(p, luna, a)
    assert (len(a) > 0)
    a = []
    l = 13
    try:
        elimina_oferte_luna_selectata(p, l, a)
        assert False
    except Exception as ex:
        assert (str(ex) == "Luna invalida!")

def test_medie_pret():
    destinatie="LALLLALALALA"
    p=pachte_de_calatorie()
    try:
        medie_pret_dest_data(p,destinatie)
        assert False
    except Exception as edd:
        assert(str(edd)=="Nu avem o medie de pret pentru destinatia introdusa")

def test_perioada_pret_crescator():
    zi_inceput=19
    luna_inceput=2
    an_inceput=2021
    zi_sfarsit=28
    luna_sfasrit=2
    an_sfasrit=2021
    p=pachte_de_calatorie()
    a=[]
    assert(len(a)==0)
    pachete_perioada_data_pret_crescator(p,a,zi_inceput, luna_inceput, an_inceput, zi_sfarsit, luna_sfasrit, an_sfasrit)
    assert(len(a)>0)
    zi_inceput = 28
    luna_inceput = 2
    an_inceput = 2021
    zi_sfarsit = 28
    luna_sfasrit = 2
    an_sfasrit = 2021
    p = pachte_de_calatorie()
    a = []
    try:
        pachete_perioada_data_pret_crescator(p, a, zi_inceput, luna_inceput, an_inceput, zi_sfarsit, luna_sfasrit,an_sfasrit)
        assert False
    except Exception as epc:
        assert(str(epc)=="Nu avem oferte din aceasata perioada")
    zi_inceput = 28
    luna_inceput = 2
    an_inceput = 2021
    zi_sfarsit = 28
    luna_sfasrit = 2
    an_sfasrit = 2021
    p = pachte_de_calatorie()
    a = []
    try:
        pachete_perioada_data_pret_crescator(p, a, zi_inceput, luna_inceput, an_inceput, zi_sfarsit, luna_sfasrit,
                                             an_sfasrit)
        assert False
    except Exception as epc:
        assert (str(epc) == "Nu avem oferte din aceasata perioada")



def test_stergere_pachete_calatorie_disponibile_destinatie_data():
    destinatie = "Barcelona"
    p = pachte_de_calatorie()
    stergere_pachete_destinatie_data(p,destinatie)
    x=0
    while x<len(p):
        assert(destinatie_calatorie(p[x])!=destinatie)
        x+=1
    destinatie="LALALA"
    p=pachte_de_calatorie()
    try:
        stergere_pachete_destinatie_data(p,destinatie)
        assert False
    except Exception as esd:
        assert(str(esd)=="Nu avem pachete de calatorie in destinatia selectata!")


def test_sterege_pachete_calatorie_duarata_mai_scurta_decat_un_numar_de_zile_dat():
    nrzile=9
    assert 9 ==8
    p=pachte_de_calatorie()
    stergere_pachete_duarata_mai_scurta(p,nrzile)
    x=0
    while x<len(p):
        nrz=nr_zile(zi_data_inceput(p[x]),luna_data_inceput(p[x]),an_data_inceput(p[x]),zi_data_sfarsit(p[x]),an_data_sfarsit(p[x]),luna_data_sfarsit(p[x]))
        assert(nrz>=nrzile)
        x+=1
    nrzile=0
    p=pachte_de_calatorie()
    try:
        stergere_pachete_duarata_mai_scurta(p,nrzile)
        assert False
    except Exception as enrs:
        assert(str(enrs)== "Numar de zile invalid!")
    nrzile = 1
    p = pachte_de_calatorie()
    try:
        stergere_pachete_duarata_mai_scurta(p, nrzile)
        assert False
    except Exception as enrs:
        assert (str(enrs) == "Nu avem ce pachete de calatorie sa stergem!")


def test_stergere_pachete_calatorie_pret_mai_mare_decat_o_suma_data():
    suma=600
    p=pachte_de_calatorie()
    stergere_pachete_pret_mai_mare_decat_o_suma_data(p,suma)
    x=0
    while x<len(p):
        assert(pret_calatorie(p[x])<suma)
        x+=1
    suma=0
    p=pachte_de_calatorie()
    try:
        stergere_pachete_pret_mai_mare_decat_o_suma_data(p,suma)
        assert False
    except Exception as err:
        assert(str(err)=="Pret invalid!")
    suma = 10000000000000000000000000000000000000000
    p = pachte_de_calatorie()
    try:
        stergere_pachete_pret_mai_mare_decat_o_suma_data(p, suma)
        assert False
    except Exception as ers:
        assert (str(ers) == "Nu avem ce pachete de calatrie sa stergem")


def run_teste():
    test_adauga_pachetul_de_calatorie()
    test_valideaza_pachet_calatorie()
    test_adauga_pachet_calatorie_in_lista()
    test_srv_pachet_calatorie()
    test_elimina_pachet_calatorie_pret_mai_mare()
    test_elimina_pachet_calatorie_luna_egala()
    test_medie_pret()
    test_perioada_pret_crescator()
    test_stergere_pachete_calatorie_disponibile_destinatie_data()
    test_sterege_pachete_calatorie_duarata_mai_scurta_decat_un_numar_de_zile_dat()
    test_stergere_pachete_calatorie_pret_mai_mare_decat_o_suma_data()