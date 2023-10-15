'''
funtia cauta intr-un sir, cel mai mare cuvant dpdv alfabetic si il retruneaza
params: a - string, a este textul separat de spatii in care cauti cuvantul cel mai mare dpdv alfabetic
returns: maxi-string, cuvantul care e cel mai mare dpdv alfabetic in textul a
'''

def solution(a):
    l = a.split(" ")
    maxi = " "
    for a in l:
        if ord(a[0]) > ord(maxi[0]):
            maxi = a
    return maxi




