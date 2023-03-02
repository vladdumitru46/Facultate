"""
functia cauta elementul majoritar in multime
:param collection - list, multimea initiala in care cautam numarul majoritar
:returns i - int, numerul care apare de len(collection)/2 in multimea initiala
"""

def solution(collection):
    dictionary = {}
    for i in collection:
        dictionary[i] = 0
    for i in collection:
        dictionary[i] += 1
    for i in dictionary:
        if dictionary[i] > (len(collection) / 2):
            return i
