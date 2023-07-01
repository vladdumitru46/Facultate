'''
Created on 20 Nov 2021

@author: vladb
'''
class StudentProblema(object):
    def __init__(self,id_stud,nr_lab,nr_pb,nota,nume):
        self.__id_stud = id_stud
        self.__stud=None
        self.__nr_lab = nr_lab
        self.__nr_pb = nr_pb
        self.__prob=None
        self.__nota = nota
        self.__nume=nume


    def get_id_stud(self):
        return self.__id_stud

    def get_stud(self):
        return self.__stud

    def get_nr_lab(self):
        return self.__nr_lab


    def get_nr_pb(self):
        return self.__nr_pb
    
    def get_prob(self):
        return self.__prob


    def get_nota_stud(self):
        return self.__nota

    def get_nume_st(self):
        return self.__nume
    
    def set_stud(self, value):
        self.__stud = value


    def set_prob(self, value):
        self.__prob = value

    def set_nota(self, value):
        self.__nota = value

    def __str__(self):
        return "In laboratorul:"+str(self.__nr_lab)+', la problema:'+str(self.__nr_pb)+", nota obtinuta este:"+str(self.__nota)
    nota = property(None, set_nota, None, None)
    
    
    
class ProblemaStudent():
    def __init__(self,nume_stud, lista_probleme, nota):
        self.__nume_stud = nume_stud
        self.__lista_probleme=lista_probleme
        self.__nota = nota
        
    def __str__(self):
        st=""
        st+=self.__nume_stud+":\n"
        for problema in self.__lista_probleme:
            st+='\t'+str(problema)+'\n'
        return st
    

class top3Labnote():
    def __init__(self,nr_lab,nr_pb,descriere,nr_note):
        self.__nr_lab = nr_lab
        self.__nr_pb = nr_pb
        self.__descriere = descriere
        self.nr_note = nr_note
    
    def __str__(self):
        st=""
        st+="Nr lab:"+self.__nr_lab+", nr problema:"+self.__nr_pb+",descriere:"+self.__descriere+",are numarul de:"+self.nr_note+"de note\n"
        
    
    
    
    
    
    
        