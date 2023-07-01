'''
Created on 6 Nov 2021

@author: vladb
'''

from User_interface.ui import Consola
from Validare.validare import valideaza_student,valideaza_problema,valideaza_student_problema
from Repository.repo import repo_student_problema, file_repo_problema, file_repo_studenti
from Service.srv_lista import srv_student,srv_problema,srv_student_problema



if __name__ == '__main__':
    
    Valid_student=valideaza_student()
    Valid_problema=valideaza_problema()
    Valid_student_problema=valideaza_student_problema()
    
    p=file_repo_problema("Probleme.txt")
    l=file_repo_studenti("Studenti.txt")
    q=repo_student_problema("StudentiProbleme.txt")
    
    srv=srv_student(l,Valid_student)
    srv_p=srv_problema(p,Valid_problema)
    srv_sp=srv_student_problema(q,Valid_student_problema,l,p)
    
       
    ui=Consola(srv,srv_p,srv_sp)
    ui.run()
