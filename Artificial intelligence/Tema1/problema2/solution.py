import math

"""
functia calculeaza suma euclidiana a 2 pucte
param:  a- list, primul punct
        b- list, al doilea punct
returns: sum- integer, suma euclidiana a celor 2 puncte
"""
def solution(a, b):
    sum = math.sqrt(pow((a[0] - b[0]), 2) + pow((a[1] - b[1]), 2))
    return sum
