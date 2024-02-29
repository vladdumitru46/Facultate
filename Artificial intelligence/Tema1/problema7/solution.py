"""
functia cauta al k-lea cel mai mare numer din multimea data
:param colection - list, multimea in care cauti al k-lea cel mai mare numar
       k-int, al k-lea numar cautat
:returns al k-lea cel mai mare numar
"""

def solution(collection, k):
    maxim = 0
    collection.sort(reverse=True)
    return collection[k - 1]
