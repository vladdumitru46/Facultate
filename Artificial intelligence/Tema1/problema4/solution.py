"""
functia cauta toate cuvintele dintr-un text care apar o singura data
param: a - string, textul in care cautam cuvintele
return: rez - list, lista rezultata care cotine doar cuvintele care apar o singura data
"""

def solution(a):
    fr = {}
    l = a.split(" ")
    for i in l:
        fr[i] = 0
    for i in l:
        fr[i] = fr[i] + 1
    rez = []
    for i in fr:
        if fr[i] == 1:
            rez.append(i)
    return rez
