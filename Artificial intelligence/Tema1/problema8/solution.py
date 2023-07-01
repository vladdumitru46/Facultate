"""
functia transforma toate numerele intre 1 si n in numere binare
:param n - int, este captul intrevalului [1, n] pentru care se transforma numerele in reprezentarea lor binara
:returns list - list, lista de numere intre [1, n] in reprezentarea lor binara
"""

def solution(n):
    i = 1
    list = []
    while i <= n:
        list.append(bin(i)[2:])
        i += 1
    return list
