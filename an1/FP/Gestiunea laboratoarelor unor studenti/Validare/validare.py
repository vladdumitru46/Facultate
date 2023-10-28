from Creaza_student.student import student 
from Exceptii.exceptii import ValidationError
from Creaza_problema.probleme import problema


class valideaza_student(object):
    #clasa care valideaza un student
    def valideaza(self,Student):
        #functie caee valideaza un student
        #input student= un student
        #output , daca studentul este valid
        #raise Exception cu textul: "Id invalid!\n" daca id<=0
        #                           "Nume invalid!\n" daca numele este gol
        #                           "Grup invalid!\n" daca grupul<=0
        ve=""
        if Student.get_id_student()<=0:
            ve+="Id invalid!\n"
        if Student.get_nume()=="":
            ve+="Nume invalid!\n"
        if Student.get_grup()<=0:
            ve+="Grup invalid!\n"
        if len(ve):
            raise ValidationError(ve)
            
            
    def valideaza_id(self, id_stud):
        if id_stud<=0:
            raise ValidationError("Id invalid!\n")


class valideaza_problema(object):
    
    
    def validare_problema(self, prob):
        
        ve=""
        if prob.get_numar_lab()<=0:
            ve+="Numar de laborator invalid!\n"
        if prob.get_numar_pb()<=0:
            ve+="Numar de problema invalida!\n"
        if prob.get_descriere()=="":
            ve+="Descriere invalida!\n"
        if prob.get_dedline()=="":
            ve+="Dedline invalid!\n"
    
        if len(ve)>0:
            raise ValidationError(ve)

    
    def valideaza_nr_lab_si_nr_pb(self, nr_lab, nr_pb):
        ve=""
        if nr_lab<=0:
            ve+="Numar de laborator invalid!\n"
        if nr_pb<=0:
            ve+="Numar de problema invalida!\n"
            
        if len(ve)>0:
            raise ValidationError(ve)    


    
    



class valideaza_student_problema(object):
    def validare_stud_prob(self,stud_prob):
        err=""
        if stud_prob.get_id_stud()<=0:
            err+="Id-ul studentului este invalid!\n"
        if stud_prob.get_nr_lab()<=0:
            err+="Numarul de laborator este invalid!\n"
        if stud_prob.get_nr_pb()<=0:
            err+="Numarul de problema este invalid!\n"
        if stud_prob.get_nota_stud()<=0:
            err+="Nota este inavlida!\n"
        if len(err)>0:
            raise ValidationError(err)

    
    def valideaza_id_si_nr(self, id_stud, nr_lab, nr_pb):
        err=""
        if id_stud<=0:
            err+="Id-ul studentului este invalid!\n"
        if nr_lab<=0:
            err+="Numarul de laborator este invalid!\n"
        if nr_pb<=0:
            err+="Numarul de problema este invalid!\n"
        if len(err)>0:
            raise ValidationError(err)
    
    


