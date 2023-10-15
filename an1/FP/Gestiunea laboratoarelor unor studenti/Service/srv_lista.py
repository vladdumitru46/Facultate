from Creaza_student.student import student
from Creaza_problema.probleme import problema
from DTOS.dtos import StudentProblema, ProblemaStudent
from sortari.sort import BubleSort,ShellSort

class srv_student(object):
    
    def __init__(self,l_studenti,v_studenti):
        self.__l_studenti=l_studenti
        self.__v_studenti=v_studenti
        
            
    def get_nr_studenti(self):
        '''
        functie care retunreaza nr de studenti
        '''
        return len(self.__l_studenti)
    
    
    def get_all_stud(self):
        '''
        functie care retuneaza toti studentii
        '''
        return self.__l_studenti.get_all_students()
    
    
    def adauga_studenti(self, id_student, nume, grupa):
        '''
        functie car eadauga toti studentii in lista
        input:
        id stud= id-ul studentiilor
        nume=numele studeniilot
        grupa: grupa studentiilor
        output: lista de stiudenti daca este valida
        '''
        stud=student(id_student,nume,grupa)
        # self.__v_studenti.valideaza(stud)
        i=0
        # self.__l_studenti.adauga_student_lista(i,stud)
        self.__l_studenti.adauga_student_lista(i,stud)
        
        
    
    def cauta_student(self,id_stud):
        '''
        functie care cauta un student dupa id
        input: id_stud, id-ul nui student
        ouput: studentul cautat
        '''
        nr=0
        return self.__l_studenti.cauta_dupa_id(nr,id_stud)
    
    def sterge_student(self,id_stud):
        '''
        functie care sterge un student dupa id
        input: id_stud, if-ul unui student
        output: , daca studentul a fost sters
        '''
        self.__l_studenti.sterge_student_dupa_id_file(id_stud)
    
    def modifica_student_dupa_id(self,id_stud):
        '''
        functie care o pmodifica un student din lista dupa id
        input: id_stud, if-ul unui student
        ouput: , functia cerand sa introduci noile date ale studentului
        '''
        self.__l_studenti.modifica_student(id_stud)
    
    def aleatoriu(self,l,n):
        self.__l_studenti.aleatoriu_id(l,n)
 
    
class srv_problema(object):
    def __init__(self,l_problema,v_problema):
        self.__l_problema = l_problema
        self.__v_problema = v_problema

    
    def get_nr_probleme(self):
        '''
        functie care resturneaza nr de probleme
        '''
        return len(self.__l_problema)

    
    def get_all_prob(self):
        '''
        funstie care afiseaza toate problemele
        '''
        return self.__l_problema.get_all_problems()
    
    
    
    def adauga_probleme(self,nr_lab,nr_pb,descriere,dedline):
        ''' 
        functie care adauga problemele in lista
        input" 
        nr lab: nr de laboratir
        nr pb= numarul de problema
        descriere: descrierea problemei
        dedline" dedline-ul problemei
        '''
        prob=problema(nr_lab,nr_pb,descriere,dedline)
        self.__v_problema.validare_problema(prob)
        self.__l_problema.adauga_problema_in_lista( prob)
       
    
    def cauta_problema_dupa_nr_lab_si_nr_pb(self,nr_lab,nr_pb):
        '''
        functie care cauta o problema dupa nr_lab si nr_pb
        inut:   nr_lab, numarul de laborator
                nr_pb, numarul de problema
        ouput: problema cautata
        '''
        nr=0
        return self.__l_problema.cauta_problema(nr,nr_lab,nr_pb)

    def sterge_problema_dupa_nr_lab_si_nr_pb(self,nr_lab,nr_pb):
        '''
        functie care sterge o problema dupa nr_lab si nr_pb
        input:   nr_lab, numarul de laborator
                nr_pb, numarul de problema
        ouput: , daca studentul a fost sters
        '''
        self.__v_problema.valideaza_nr_lab_si_nr_pb(nr_lab,nr_pb)
        self.__l_problema.sterge_problema(nr_lab,nr_pb)
    
    def modifica_problema_dupa_nr_lab_si_nr_pb(self,nr_lab, nr_pb):
        '''
        functie care o problema din lista dupa nr_lab si nr_pb
        input:   nr_lab, numarul de laborator
                nr_pb, numarul de problema
        ouput: , functia cerand sa introduci noile date ale problemei
        '''
        self.__l_problema.modifica_problema(nr_lab,nr_pb)
    
class srv_student_problema(object):
    
    def __init__(self,l_stud_prob,v_stud_prob,repo_student, repo_lab):
        self.__l_stud_prob = l_stud_prob
        self.__v_stud_prob = v_stud_prob
        self.__repo_student = repo_student
        self.__repo_lab = repo_lab


    def get_all_studneti_problema(self):
        '''
        functie care returneaza toate asigmenturile din fisier
        input: nimic
        output: lista de asigmentrui din fisier
        '''
        student_problema_dto=self.__l_stud_prob.get_all_studenti_probleme()
        prob_stud={}
        for stud_prob_dto in student_problema_dto:
            stud=self.__repo_student.cauta_dupa_id(stud_prob_dto.get_id_stud())
            stud_prob_dto.set_stud(stud)
            if stud.get_id_student() not in prob_stud:
                prob_stud[stud.get_id_student()]=[]
            prob_stud[stud.get_id_student()].append(stud_prob_dto)
            #prob_stud[stud.get_id_student()].append(stud_prob_dto.get_nota_stud())
        rezultat=[]
        for sp in prob_stud:   
            id_stud=sp
            stud=self.__repo_student.cauta_dupa_id(id_stud)
            prob=prob_stud[sp]
            s_p_dto=ProblemaStudent(stud.get_nume(),prob,"")
            rezultat.append(s_p_dto)
        return rezultat
    
    
    def adauga_stud_prob(self,id_stud,nr_lab,nr_pb,nota,nume):
        '''
        functie care adauga un nou asigment in fisierul de asigmenturi
        input:  id_stud: id-ul unui student
                nr_lab, numarul de laborator
                nr_pb, numarul de problema
                nota, nota de la asigment
        output: , asigemntul doar se adauga in fisier, fara a se afisa nimic
        '''
        stud_prob=StudentProblema(id_stud,nr_lab,nr_pb,nota,nume)
        self.__l_stud_prob.asign_lab_note(stud_prob)
        self.__v_stud_prob.validare_stud_prob(stud_prob)
    
    def sterge_asigm(self,id_stud,nr_lab,nr_pb):
        self.__l_stud_prob.sterge_asigm(id_stud,nr_lab,nr_pb)
        
        
    
    def nume(self,stud):
        return stud.get_id_stud() 
    
    def verif(self,l,id):
        for x in l:
            if x.get_id_stud()==id:
                return True
            
            
    # def creare_lista(self):
    #     l=[]
    #     l_stud_prob=self.__l_stud_prob.get_all_studenti_probleme()
    #     l_stud=self.__repo_student.get_all_students()
    #     for x in l_stud:
    #         for y in l_stud_prob:
    #             if self.verif()
        
    
 
    def sort_by_nume_and_nota(self,file_path):
        '''
        Functie care sorteaza toti studentii alfabetic, si dupa note
        input: file_path, fisierul in care sunt salvati elevii si asigenturile lor
        output: , functia sortand studnetii alfabetic si dupa note
        '''
        l_stud_prob= self.__l_stud_prob.get_all_studenti_probleme()
        # for a in range(0,len(l_stud_prob)):
        #     stud = self.__repo_student.cauta_dupa_id(l_stud_prob[a].get_id_stud())
        #     nume = stud.get_nume()
        #     for b in range(a+1,len(l_stud_prob)):
        #         stud1 = self.__repo_student.cauta_dupa_id(l_stud_prob[b].get_id_stud())
        #         nume1 = stud1.get_nume()
        #         if nume > nume1:
        #             aux = l_stud_prob[a]
        #             l_stud_prob[a] = l_stud_prob[b]
        #             l_stud_prob[b] = aux
        # for x in range(0,len(l_stud_prob)):
        #     id_stud = l_stud_prob[x].get_id_stud()
        #     for y in range(x+1,len(l_stud_prob)):
        #         if id_stud == l_stud_prob[y].get_id_stud():
        #             a = l_stud_prob[x].get_nota_stud()
        #             b = l_stud_prob[y].get_nota_stud()
        #             if a > b:
        #                 aux = l_stud_prob[x]
        #                 l_stud_prob[x] = l_stud_prob[y]
        #                 l_stud_prob[y] = aux   
        b=BubleSort()      
        b.sort(l_stud_prob,key=lambda x:x.get_nume_st(),cmp=lambda x:x.get_nota_stud())
        with open(file_path,'w') as f:
            f.write("")
        with open(file_path, 'w') as f:  
            for i in l_stud_prob:
                self.adauga_stud_prob(i.get_id_stud(), i.get_nr_lab(), i.get_nr_pb(), i.get_nota_stud(),i.get_nume_st())



    def medie(self, id_stud):
        '''
        functie care calculeaza media unui student
        '''
        l_stud_prob=self.__l_stud_prob.get_all_studenti_probleme()
        s=0
        nr=0
        for st in l_stud_prob:
            if id_stud==st.get_id_stud():
                s+=st.get_nota_stud()
                nr+=1
        return float(s/nr)
    
    
    def medie_sub_5(self): 
        ''' 
        functie care afiseaza toti studentii care au media mai mica decat 5
        input: nimic
        output: studentul si media sa
        '''
        a=[]
        arr=[]
        l_stud_prob=self.__l_stud_prob.get_all_studenti_probleme()   
        for stud in l_stud_prob:
            id_stud=stud.get_id_stud()
            ok=1
            for id_s in arr:
                if id_stud==id_s:
                    ok=0
            if ok==1:
                if self.medie(stud.get_id_stud())<5:
                    studentul=self.__repo_student.cauta_dupa_id(stud.get_id_stud())
                    a.append(studentul)
                arr.append(id_stud)
        return a


    def nr_note(self, nr_lab, nr_pb):
        '''
        functie care calculeaza numarul de note ontinute de catre studenti la o anumita problema
        input:  nr_lab, numarul de laborator
                nr_pb, numarul de problema
        outpu: nr, numarul de note de la o problema
        '''     
        l_pb_stud=self.__l_stud_prob.get_all_studenti_probleme()
        nr=0
        for prob in range(len(l_pb_stud)):
            if l_pb_stud[prob].get_nr_lab()==nr_lab and l_pb_stud[prob].get_nr_pb()==nr_pb:
                nr+=1
        return nr
        
    def top3nrnotelab(self):
        '''
        functie care afla primele 3 probleme cu cele mai multe note
        input: nimic
        output: primele 3 probleme cu cele mai multe note
        '''
        a=[]
        max1=0
        max2=0
        max3=0
        pb1=[]
        pb2=[]
        pb3=[]
        l_pb_stud=self.__l_stud_prob.get_all_studenti_probleme()
        for pb in range(len(l_pb_stud)):
            if self.nr_note(l_pb_stud[pb].get_nr_lab(),l_pb_stud[pb].get_nr_pb())>max1:
                max3=max2
                max2=max1 
                max1=self.nr_note(l_pb_stud[pb].get_nr_lab(),l_pb_stud[pb].get_nr_pb())
                pb1=self.__repo_lab.cauta_problema(l_pb_stud[pb].get_nr_lab(),l_pb_stud[pb].get_nr_pb())        
            elif self.nr_note(l_pb_stud[pb].get_nr_lab(),l_pb_stud[pb].get_nr_pb())>max2:
                if pb1.get_numar_lab()!=l_pb_stud[pb].get_nr_lab() or pb1.get_numar_pb()!=l_pb_stud[pb].get_nr_pb():
                    max2=self.nr_note(l_pb_stud[pb].get_nr_lab(),l_pb_stud[pb].get_nr_pb())
                    pb2=self.__repo_lab.cauta_problema(l_pb_stud[pb].get_nr_lab(),l_pb_stud[pb].get_nr_pb())
        for pb in range(len(l_pb_stud)):
            if self.nr_note(l_pb_stud[pb].get_nr_lab(),l_pb_stud[pb].get_nr_pb())>max3 and self.nr_note(l_pb_stud[pb].get_nr_lab(),l_pb_stud[pb].get_nr_pb())<=max2:
                if pb2.get_numar_lab()!=l_pb_stud[pb].get_nr_lab() or pb2.get_numar_pb()!=l_pb_stud[pb].get_nr_pb():
                        max3=self.nr_note(l_pb_stud[pb].get_nr_lab(),l_pb_stud[pb].get_nr_pb())
                        pb3=self.__repo_lab.cauta_problema(l_pb_stud[pb].get_nr_lab(),l_pb_stud[pb].get_nr_pb())
        a.append(pb1)
        a.append(pb2)
        a.append(pb3)
        return a

    def delete_stud_din_stud_prob(self):
        self.__l_stud_prob.delete_stud_din_fisier()
        
    def delete_problema_din_stud_prob(self):
        self.__l_stud_prob.delete_prob_din_fisier()
        