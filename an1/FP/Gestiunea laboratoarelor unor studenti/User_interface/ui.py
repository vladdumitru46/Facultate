from Service.srv_lista import srv_student
from Exceptii.exceptii import ValidationError, ShearchingError, DeleteError, AddError
from Repository.repo import file_repo_studenti,file_repo_problema, repo_student_problema
from Creaza_student.student import student
from Creaza_problema.probleme import problema
from DTOS.dtos import StudentProblema


class Consola(object):

    
    def __init__(self, srv,srv_p,srv_sp):
        self.__srv=srv
        self.__srv_p = srv_p
        self.__srv_sp=srv_sp
    
    def __meniu(self):
        print("Aplicatie care gestineaza lucariile de laborator ale eleviilor")
        
        print("1. Daca doriti sa gestionati lista de elevi(adauga un student, cauta un student, sterge un student, modifica un student, genereaza studenti random, afiseaza lista de studenti)")
        print("2. Daca doriti sa gestionati lista de probleme(adauga o porblema, cauta o probkema, sterge o problema, modifica o problema, afiseaza lista de probleme)")
        print("3. Daca doriti sa adaugati un asigment unui elev")
        print("4. Daca doriti sa adaugati o nata la asigmentul elevului")
        print("5. Daca doriti sa sorati alfabetic studentii")
        print("6. Daca doriti sa vedeti studentii cu media notelor de la laborator sub 5")
        print("7. Daca doriti sa vedeti primele 3 probleme cu cele mai multe note")
        print("8. Daca doriti sa afisati lista studentiilor cu asigemnturile date si nota oferita la acestea")
        print("Pentru a executa orice comanda din lista de mai sus, va rugam scrieti numarul comenzii")
        print("Daca doriti sa iesiti din aplicatie, scrieti 'exit'")
    
    
    
    def __ui_adauga_student(self,l):
        try:
            id_student=int(input("Introduce id-ul studentului:"))
        except ValueError:
            print("Id numeric invalid!")
            return 
        nume=input("Introduceti numele studentului:")
        try:
            grupa=int(input("Introduceti grupa studentului:"))
        except ValueError:
            print("Grupa invalida!")
            return
        self.__srv.adauga_studenti(id_student, nume, grupa)
        ok=1
        for x in l:
            if x.get_id_student()==id_student:
                ok=0
        if ok==1:
            l.append(student(id_student,nume,grupa))
        else:
            raise AddError("Id deja existent!\n")
        print("Student adaugat cu succes")
    
    
    def __ui_print_stud(self,l):
        x=0
        while x<len(l):
            print("Id:", l[x].get_id_student(), "Nume:", l[x].get_nume()," grup:", l[x].get_grup())
            x+=1
    
    
    
    def __ui_adauga_problema(self,l):
        try:
            nr_lab=int(input("Introduceti numarul laboratorului:"))
        except ValueError:
            print("Numar de laborator invalid!\n")
            return        
        try:
            nr_pb=int(input("Introduceti numarul problemei:"))
        except ValueError:
            print("Numar de problema invalida!\n")
            return 
        descriere=input("Introduceti descrierea problemei:")
        dedline=input("Introduceti dedline-ul problemei(zi/luna/an):")
        self.__srv_p.adauga_probleme(nr_lab,nr_pb,descriere,dedline)
        print("Problema adaugata cu succes")
        ok=1
        for x in l:
            if x.get_numar_lab()==nr_lab and x.get_numar_pb()==nr_pb:
                ok=0
        if ok==1:
            l.append(problema(nr_lab,nr_pb,descriere,dedline))
        else:
            raise AddError("Id deja existent!\n")
    
    
    
    def __ui_print_prob(self,m):
        for x in m:
            print("numarul de laborator:",x.get_numar_lab(), "numarul problemei", x.get_numar_pb())
            print("descriere:",x.get_descriere())
            print("Dedline:",x.get_dedline(),"\n")
            
    
    
    def __asign_lab(self,l,m,n):
        ae=""
        try:
            id_stud=int(input("Introduceti id-ul studentului caruia doriti sa ii dati o problema de laborator:"))
        except ValueError:
            print("Id de student invalid")
            return
        try:
            nr_lab=int(input("Introduceti numarul de laborator:"))
        except ValueError:
            print("Numar de laborator invalid")
            return
        try: 
            nr_pb=int(input("Introduceti numarul problemei:"))
        except ValueError:
            print("Numar de problema invalid")
            return
        nota=1
        ok=0
        for x in l:
            if x.get_id_student()==id_stud:
                ok=1
        okk=0
        if ok==1:
            for x in m:
                if x.get_numar_lab()==nr_lab and x.get_numar_pb()==nr_pb:
                    okk=1
        else:
            ae="Studentul nu exista!\n"
        okkk=1
        if okk==1:
            for x in n:
                if x.get_id_stud()==id_stud and x.get_nr_lab()==nr_lab and x.get_nr_pb()==nr_pb:
                    okkk=0
        else:
            ae="Problema nu exista!\n"
        if okkk==1:
            self.__srv_sp.adauga_stud_prob(id_stud,nr_lab,nr_pb,nota)
            n.append(StudentProblema(id_stud,nr_lab,nr_pb,nota))
        else:
            ae="Studentul a primit deja problema!\n"
        if len(ae)>0:
            raise AddError(ae)
        
    def __ui_print_stud_prob(self):
        stud_prob=self.__srv_sp.get_all_studneti_problema()
        for date_stud in stud_prob:
            print(date_stud)
            
    
    def __meniu_student(self):
        print("Optinuiile pentru lista de studenti sunt:")
        print("1. Adaugarea unui nou student in lista")
        print("2. Cautarea unui student in lista")
        print("3. Stergerea unui student din lista")
        print("4. Modificarea unui student din lista")
        print("5. Afisarea listei de studenti")
        print("6. Daca doriti sa generati un numar de studenti random")
        print("7. Iesirea din meniul pentru optiuni de studenti")
        print("Pentru a executa orice comanda din lista de mai sus, va rugam scrieti numarul comenzii")
    
    
    def __meniu_probleme(self):
        print("Optinuiile pentru lista de probleme sunt:")
        print("1. Adaugarea unuei noua probleme in lista")
        print("2. Cautarea unuei probleme in lista")
        print("3. Stergerea unuei probleme din lista")
        print("4. Modificarea unuei probleme din lista")
        print("5. Afisarea listei de probleme")
        print("6. Iesirea din meniul pentru optiuni de probleme")
        print("Pentru a executa orice comanda din lista de mai sus, va rugam scrieti numarul comenzii")    
      
      
        
    def run(self):
        a=file_repo_studenti("Studenti.txt")
        b=file_repo_problema("Probleme.txt")
        c=repo_student_problema("StudentiProbleme.txt")
        l=a.get_all_students()
        m=b.get_all_problems()
        n=c.get_all_studenti_probleme()
        while True:
            self.__meniu()
            cmd=input(">>>")
            if cmd=="exit":
                return 
            elif cmd=="":
                continue
            elif cmd=='1':
                self.__meniu_student()
                while True:
                    command=input(">>>")
                    if command=='1':
                        try:
                            self.__ui_adauga_student(l)
                        except ValidationError as ve:
                            print(ve)
                        except ShearchingError as se:
                            print(se)
                        except AddError as ae:
                            print(ae)
                    elif command=='2':
                        try:
                            id_stud=int(input("Introduceti id-ul studentului pe care doriti sa il cautati:"))
                            stud=int
                            stud=self.__srv.cauta_student(id_stud)
                            print("Studentul cu id-ul cerut de catre dumneavoatra este:", stud.get_nume()," si este din grupa:",stud.get_grup())
                        except ShearchingError as se:
                            print(se)
                        except ValidationError as ve:
                            print(ve)
                    elif command=='3':
                        try:
                            id_stud=int(input("Introduceti id-ul studentului pe care doriti sa il stergeti:"))
                            try:
                                self.__srv.sterge_student(id_stud)
                                print("Studentul a fost sters cu succes din lista")
                            except DeleteError as de:
                                print(de)
                        except ValidationError as ve:
                            print(ve)
                    elif command=='4':
                        try:
                            id_stud=int(input("Introduceti id-ul studentului pe care doriti sa il modificati:"))
                            self.__srv.modifica_student_dupa_id(id_stud)
                            nume=input("Introduceti numele studentului:")
                            try:
                                grupa=int(input("Introduceti grupa studentului:"))
                            except ValueError:
                                print("Grupa invalida!")
                                return
                            self.__srv.adauga_studenti(id_stud,nume,grupa)
                        except DeleteError as ue:
                            print(ue)
                    elif command=='5':
                        self.__ui_print_stud(l)
                    elif command=='6':
                        n=int(input("introduceti numarul de elemente:"))
                        stud=self.__srv.aleatoriu(l,n)
                    elif command=='7':
                        break
                    elif command=="":
                        continue
                    else:
                        print("Comanda invalida!") 
            elif cmd=='2':
                self.__meniu_probleme()
                while True:
                    command=input(">>>")
                    if command=="":
                        continue
                    elif command=='1':
                        try:
                            self.__ui_adauga_problema(m)
                        except AddError as se:
                            print(se)
                        except ValidationError as ve:
                            print(ve)
                    elif command=='2':
                        try:
                            nr_lab=int(input("Introduceti numarul laboratorului:"))
                            nr_pb=int(input("Introducei numarul problemei:"))
                            pb=self.__srv_p.cauta_problema_dupa_nr_lab_si_nr_pb(nr_lab, nr_pb)
                            print("Problema cu numarul de laborator",pb.get_numar_lab(),", si cu numarul", pb.get_numar_pb(),"are descrierea:",pb.get_descriere(),", si are dedline-ul pana in:", pb.get_dedline(),"\n")
                        except ShearchingError as se:
                            print(se)
                        except ValidationError as ve:
                            print(ve)
                    elif command=='3':
                        try:
                            nr_lab=int(input("Introduceti numarul laboratorului:"))
                            nr_pb=int(input("Introducei numarul problemei:"))
                            self.__srv_p.sterge_problema_dupa_nr_lab_si_nr_pb(nr_lab, nr_pb)
                            print("Problema a fost stearsa cu succes!\n")
                        except DeleteError as de:
                            print(de)
                        except ValidationError as ve:
                            print(ve)
                    elif command=='4':
                        try:
                            nr_lab=int(input("Introduceti numarul laboratorului:"))
                            nr_pb=int(input("Introducei numarul problemei:"))
                            self.__srv_p.modifica_problema_dupa_nr_lab_si_nr_pb(nr_lab, nr_pb)
                            descriere=input("Introduceti descrierea problemei:")
                            dedline=input("Introduceti dedline-ul problemei(zi/luna/an):")
                            self.__srv_p.adauga_probleme(nr_lab,nr_pb,descriere,dedline)
                        except DeleteError as ue:
                            print(ue)
                        except ValidationError as ve:
                            print(ve)
                    elif command=='5':
                        self.__ui_print_prob(m)
                    elif command=='6':
                        break
                    else:
                        print("Comanda invalida!")
            elif cmd=='3':
                try:
                    self.__asign_lab(l,m,n)
                except AddError as ae:
                    print(ae)
            elif cmd=='4':
                    try:
                        id_stud=int(input("Introduceti id-ul studentului caruia doriti sa ii dati o problema de laborator"))
                        nr_lab=int(input("Introduceti numarul de laborator:"))
                        nr_pb=int(input("Introduceti numarul problemei:"))
                        self.__srv_sp.sterge_asigm(id_stud,nr_lab,nr_pb)
                        nota=int(input("Introduceti ce nota doriti:"))
                        self.__srv_sp.adauga_stud_prob(id_stud,nr_lab,nr_pb,nota)
                    except DeleteError as de:
                        print(de)
                    except ValueError as ve:
                        print (ve)                    
            elif cmd=='5':
                self.__srv_sp.sort_by_nume_and_nota("StudentiProbleme.txt")
            elif cmd=='6':
                b=self.__srv_sp.medie_sub_5()
                for x in range(len(b)): 
                    print(b[x].get_nume()," ", self.__srv_sp.medie(b[x].get_id_student())) 
            elif cmd=='7':
                a=self.__srv_sp.top3nrnotelab()
                for i in range(len(a)):
                    print(i+1,". Numarul de laborator:",a[i].get_numar_lab(),"numarul problemei:",a[i].get_numar_pb(),"descriere:", a[i].get_descriere(),"are",self.__srv_sp.nr_note(a[i].get_numar_lab(),a[i].get_numar_pb()),"note")
        
            elif cmd=='8':
                self.__srv_sp.delete_stud_din_stud_prob()
                self.__srv_sp.delete_problema_din_stud_prob()
                self.__ui_print_stud_prob()
            else:
                print("Comanda invalida!")
            print('\n')


