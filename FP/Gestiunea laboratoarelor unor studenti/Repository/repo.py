from Exceptii.exceptii import ShearchingError, DeleteError, UpdateError,\
    AddError
from Creaza_student.student import student
from Validare.validare import valideaza_student, valideaza_problema,\
    valideaza_student_problema
from Creaza_problema.probleme import problema
from Random.random import generate_random
from DTOS.dtos import StudentProblema

class repo_student(object):
    
    
    def __init__(self):
        self._studenti=[]
    
    def __len__(self):
        return len(self._studenti)


    def adauga_student_lista(self,i, Student):
        '''
        funstie careadauga student in lista
        input: studet: un student
        output: nimic
        '''
        # for _stud in self._studenti:
        #     if _stud.get_id_student()==Student.get_id_student():
        #         raise AddError("Id deja existent!\n")
        # self._studenti.append(Student)
        if i< len(self._studenti):
            if self._studenti[i].get_id_student()==Student.get_id_student():
                raise AddError("Id deja existent!\n")
            self._studenti.append(Student)
            i+=1
            return self.adauga_student_lista(i, Student)
            

    
    def cauta_dupa_id(self,id_stud):
        '''
        functie care cauta un student dupa id
        input: id_stud, id-ul studentului pe care il cauti
        output: studentul cu id-ul cautat
        raise ShearchingError cu textul: "Id inexistent!\n"
        '''
        nr=0
        for _stud in self._studenti:
            if _stud.get_id_student()==id_stud:
                return _stud
                nr+=1
        if nr==0:
            raise ShearchingError("Id inexistent!\n")  
        
            
    
    
    def sterge_student_dupa_id(self,id_stud):
        ''' functie care sterge un student
        input: id_studnet
        ouput: , daca studentul a fost sters cu suuces
        raise DeleteError cu textul: "Nu avem nici-un student cu id-ul selectat!\n"
        '''
        g=valideaza_student()
        g.valideaza_id(id_stud)
        nr=0
        for x in self._studenti:
            if x.get_id_student()==id_stud:
                self._studenti.remove(x)
                nr+=1
        if nr==0:
            raise DeleteError("Nu avem nici-un student cu id-ul selectat!\n")
        
        
    def modifica_student(self,id_stud):
        '''
        functie care modifcica un student dupa id
        input: id stud
        ouput: , daca studentul a fost midificat cu suuces
        rause exception: Nu avem nici-un student cu id-ul selectat, daca id-u; de stud nu exitsa
        '''
        nr=0
        g=valideaza_student()
        g.valideaza_id(id_stud)
        for x in self._studenti:
            if x.get_id_student()==id_stud:
                self._studenti.remove(x)
                nr+=1
        if nr==0:
            raise UpdateError("Nu avem nici-un student cu id-ul selectat")
        
    
    
    def get_all_students(self):
        #funcie care reuneaza toti studentii
        return self._studenti
    
    
    def aleatoriu_id(self,l,n):
        r=generate_random()
        id=r.id_radom(l,n)
    
    
    
class repo_problema(object):
    
    def __init__(self):
        self._probleme = []
     
        
    def adauga_problema_in_lista(self, Problema):
        '''
        funstie careadauga o porblema in lista
        input: problema: o problema
        output: nimic
        '''
        
        for x in self._probleme:
            if x.get_numar_lab()==Problema.get_numar_lab() and x.get_numar_pb()==Problema.get_numar_pb():
                raise AddError("Problema deja existenta!\n")
        self._probleme.append(Problema)
    
    
    
    def __len__(self):
        return len(self._probleme)

    
    def cauta_problema(self,nr, nr_lab, nr_pb):
        ''' 
        functie care cauta o problema dupa nr_lab si nr_pb
        input: Nr_lab: numarul de laborator al unei probleme
            nr_pb: numarul de problema a unei probleme
        outpu: problema cautata
        '''
        # nr=0
        # for x in self._probleme:
        #     if x.get_numar_lab()==nr_lab and x.get_numar_pb()==nr_pb:
        #         return x
        #         nr+=1
        # if nr==0:
        #     raise ShearchingError("Nu avem nici-o probmea cu acest numar de laborator si de problema!\n")
        if nr==len(self._probleme):
            raise ShearchingError("Nu avem nici-o probmea cu acest numar de laborator si de problema!\n")
        else: 
            if self._probleme[nr].get_numar_lab()==nr_lab and self._probleme[nr].get_numar_pb()==nr_pb:
                return self._probleme[nr]
            else:
                self.cauta_problema(nr+1,nr_lab, nr_pb)

    
    

    def get_all_problems(self):
        return self._probleme

    
    def sterge_problema(self, nr_lab, nr_pb):
        ''' 
         functie care sterge o problema dupa id
         input: Nr_lab: numarul de laborator al unei probleme
            nr_pb: numarul de problema a unei probleme
         outpu: problema cautata
        '''
        nr=0
        for x in self._probleme:
            if x.get_numar_lab()==nr_lab and x.get_numar_pb()==nr_pb:
                self._probleme.remove(x)
                nr+=1
        if nr==0:
            raise DeleteError("Nu avem nici-o problema cu numarul de laborator si de problema selectat!\n")
    
    
    def modifica_problema(self,nr_lab,nr_pb):
        ''' 
        functie care modifica o problema dupa nr+lab si nr_pb
        input: Nr_lab: numarul de laborator al unei probleme
            nr_pb: numarul de problema a unei probleme
        output: , daca problema a fost modificata cu succes
        '''
        g=valideaza_problema()
        g.valideaza_nr_lab_si_nr_pb(nr_lab, nr_pb)
        nr=0
        for x in self._probleme:
            if x.get_numar_lab()==nr_lab and x.get_numar_pb()==nr_pb:
                self._probleme.remove(x)
                nr+=1
        if nr==0:
            raise UpdateError("Nu avem nici-o problema cu nuamrul de laborator si numarul de problema selectate")
            
    
    
class file_repo_studenti(repo_student):
    def __init__(self,file_path):
        repo_student.__init__(self)
        self.__file_path=file_path
    
 
    def __read_all_from_file(self):
        self._studenti=[]
        with open(self.__file_path,'r') as f:
            lines=f.readlines()
            for line in lines:
                line=line.strip()
                if len(line)>0:
                    parts=line.split(',')
                    id_student=int(parts[0])
                    nume_student=parts[1]
                    grupa_student=int(parts[2])
                    Student=student(id_student,nume_student,grupa_student)
                    self._studenti.append(Student)
    
    
    def __write_all_to_file(self,Student):
        with open(self.__file_path,'a') as f:
            f.write(str(Student.get_id_student())+','+Student.get_nume()+','+str(Student.get_grup())+'\n')
    
    
    def adauga_student_lista(self,i, Student):
        self.__read_all_from_file()
        repo_student.adauga_student_lista(self,i, Student)
        self.__write_all_to_file(Student)


    def cauta_dupa_id(self, id_student):
        self.__read_all_from_file()
        return repo_student.cauta_dupa_id(self ,id_student)

        
    def get_all_students(self):
        self.__read_all_from_file()
        return repo_student.get_all_students(self)
    
    def sterge_student_dupa_id_file(self, id_stud):
        #self.__read_all_from_file()
        g=valideaza_student()
        g.valideaza_id(id_stud)
        de=""
        with open(self.__file_path,'r') as f:
            lines=f.readlines()
            b=len(lines)
        nr=0
        with open(self.__file_path,'w') as f:
            for line in lines:
                if len(line)>0:
                    parts=line.split(',')
                    if int(parts[0])!=id_stud:
                        f.write(line)
                        nr+=1
        if nr==b:
            de="Nu avem nici-un student cu id-ul selectat!\n"
        if len(de)>0:
            raise DeleteError(de)
    
    def modifica_student(self, id_stud):
        self.sterge_student_dupa_id_file(id_stud)
    
    def __len__(self):
        self.__read_all_from_file()
        return repo_student.__len__(self)
    
    
    def aleatoriu_id(self, l, n):
        self.__read_all_from_file()
        repo_student.aleatoriu_id(self, l, n)
    
    
    
class file_repo_problema(repo_problema):
    def __init__(self,file_path):
        repo_problema.__init__(self)
        self.__file_path = file_path
    
    
    def __read_problema_from_file(self):
        self._probleme=[]
        with open(self.__file_path,'r') as f:
            lines=f.readlines()
            for line in lines:
                line=line.strip()
                if len(line)>0:
                    parts=line.split(',')
                    nr_lab=int(parts[0])
                    nr_pb=int(parts[1])
                    descriere=parts[2]
                    dedline=parts[3]
                    prob=problema(nr_lab,nr_pb,descriere,dedline)
                    self._probleme.append(prob)
            
    
    def __write_all_to_problema_file(self, Problema):
        with open(self.__file_path,'a') as f:
            f.write(str(Problema.get_numar_lab())+','+str(Problema.get_numar_pb())+','+Problema.get_descriere()+','+Problema.get_dedline()+'\n')
    
    
    def adauga_problema_in_lista(self, Problema):
        self.__read_problema_from_file()
        repo_problema.adauga_problema_in_lista(self, Problema)
        self.__write_all_to_problema_file(Problema)
    
    def cauta_problema(self,nr, nr_lab, nr_pb):
        self.__read_problema_from_file()
        return repo_problema.cauta_problema(self, nr,nr_lab, nr_pb)
    
    def sterge_problema_file(self, nr_lab, nr_pb):
        g=valideaza_problema()
        g.valideaza_nr_lab_si_nr_pb(nr_lab,nr_pb)
        with open(self.__file_path,'r') as f:
            lines=f.readlines()
            b=len(lines)
        nr=0
        de=""
        with open(self.__file_path,'w') as f:
            for line in lines:
                if len(line)>0:
                    parts=line.split(',')
                    if int(parts[0])!=nr_lab or int(parts[1])!=nr_pb:
                        f.write(line)
                        nr+=1
        if nr==b:
            de="Nu avem nici-o problema cu numarul de laborator si de problema selectat!\n"
        if len(de)>0:
            raise DeleteError(de)
    
    def modifica_problema(self, nr_lab, nr_pb):
        self.sterge_problema_file(nr_lab, nr_pb)
     
    def get_all_problems(self):
        self.__read_problema_from_file()
        return repo_problema.get_all_problems(self)   
        
    def __len__(self):
        self.__read_problema_from_file()
        return repo_problema.__len__(self)
  
  
  
class repo_student_problema(object):
    def __init__(self,file_path):
        self.__file_path = file_path
        self._lista_student_laborator=[]
        
    def __read_all_from_file_stud_prob(self):
        self._lista_student_laborator=[]
        with open(self.__file_path,'r') as f:
            lines=f.readlines()
            for line in lines:
                line=line.strip()
                if len(line)>0:
                    parts=line.split(',')
                    id_stud=int(parts[0])
                    nr_lab=int(parts[1])
                    nr_pb=int(parts[2])
                    nota=int(parts[3])
                    nume=str(parts[4])
                    stud_prob=StudentProblema(id_stud,nr_lab,nr_pb,nota,nume)
                    self._lista_student_laborator.append(stud_prob)
    
    
    def get_all_studenti_probleme(self):
        self.__read_all_from_file_stud_prob()
        return self._lista_student_laborator


    def __write_all_to_file_stud_prob(self, Lab_note):
        with open(self.__file_path,'a') as f:
            f.write(str(Lab_note.get_id_stud())+','+str(Lab_note.get_nr_lab())+','+str(Lab_note.get_nr_pb())+','+str(Lab_note.get_nota_stud())+','+str(Lab_note.get_nume_st())+'\n')
    
    
    def asign_lab_note(self,Lab_note):
        self.__read_all_from_file_stud_prob()
        self._lista_student_laborator.append(Lab_note)
        self.__write_all_to_file_stud_prob(Lab_note)

    def delete_stud_din_fisier(self):
        l=file_repo_studenti("Studenti.txt").get_all_students()
        with open(self.__file_path,'r') as f:
            lines=f.readlines()
        with open(self.__file_path,'w') as f:
            for line in lines:
                if len(line)>0:
                    parts=line.split(',')
                    for x in range(0,len(l)):
                        if int(parts[0])==l[x].get_id_student():
                            f.write(line)
    
    def delete_prob_din_fisier(self):
        l=file_repo_problema("Probleme.txt").get_all_problems()
        with open(self.__file_path,'r') as f:
            lines=f.readlines()
        with open(self.__file_path,'w') as f:
            for line in lines:
                if len(line)>0:
                    parts=line.split(',')
                    for x in range(0,len(l)):
                        if int(parts[1])==l[x].get_numar_lab() and int(parts[2])==l[x].get_numar_pb():
                            f.write(line)

    def sterge_asigm(self,id_stud,nr_lab,nr_pb):
        with open(self.__file_path,'r') as f:
            lines=f.readlines()
            b=len(lines)
            g=valideaza_student_problema()
            g.valideaza_id_si_nr(id_stud,nr_lab,nr_pb)
        nr=0
        de=""
        with open(self.__file_path,'w') as f:
            for line in lines:
                if len(line)>0:
                    parts=line.split(',')
                    if int(parts[0])!=id_stud or int(parts[1])!=nr_lab or int(parts[2])!=nr_pb:
                        f.write(line)
                        nr+=1
        if nr==b:
            de="Nu avem nici-o problema cu numarul de laborator si de problema selectat!\n"
        if len(de)>0:
            raise DeleteError(de)
    
        