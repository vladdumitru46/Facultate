"""
functia calcueleaza produsul cartezian a 2 vectori rari
pram:  l1 - list, primul vector
       l2 - list, al doilea vector
return: produs - int, produsul cartezian a lui l1 si l2
"""

def solution(l1, l2):
    produs = 0
    for i in range(0, len(l1)):
        produs += l1[i]*l2[i]
    return produs
