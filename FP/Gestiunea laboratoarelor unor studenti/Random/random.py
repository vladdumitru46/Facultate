from random import seed
from random import randint
import random
from Creaza_student.student import student
from Exceptii.exceptii import AddError

class generate_random(object):
    
    def __init__(self):
        self.__letters="ABCDEFGHIJKLMNOPQRSTUVXYZabcdefghijklmnopqrstuvxyz"
    def numae_genertor(self,n, __letters):
        return "".join(random.choice(self.__letters) for x in range(n))
        
    def id_radom(self,l,n):
        seed(2)
        for x in range(n):
            id=randint(10,100)
            nume=self.numae_genertor(13, self.__letters)
            grupa=randint(100,1000)
            #self.__srv.adauga_studenti(id, nume, grupa)
            ok=1
            for x in l:
                if x.get_id_student()==id:
                    ok=0
            if ok==1:
                l.append(student(id,nume,grupa))
            
