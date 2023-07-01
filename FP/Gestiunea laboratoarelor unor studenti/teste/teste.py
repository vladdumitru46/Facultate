from Creaza_student.student import student 
from Validare.validare import valideaza_student,valideaza_problema 
from Exceptii.exceptii import ValidationError, ShearchingError,DeleteError
from Repository.repo import repo_student, repo_problema, file_repo_studenti,file_repo_problema
from Service.srv_lista import srv_student, srv_problema
from Creaza_problema.probleme import problema


class teste(object):

    
    def __test_creaza_student(self):
        id_student=23
        nume="Gigi Becali"
        grup=213
        Student=student(id_student,nume,grup)
        assert(Student.get_id_student()==id_student)
        assert(nume==Student.get_nume())
        assert(grup==Student.get_grup())
        alt_nume="Ligi Becali"
        alt_grup=217
        alt_student=student(id_student,alt_nume,alt_grup)
        assert(Student==alt_student)
        assert(Student.__eq__(alt_student))    
        assert(str(Student)=="Id:23, nume:Gigi Becali, grupa:213")
        assert(Student.__str__()=="Id:23, nume:Gigi Becali, grupa:213")
        
        

    def __test_valideaza_student_bun(self):
        Student = student(23, "Gigi Becali", 213)
        valid = valideaza_student()
        valid.valideaza(Student)
        return valid


    def __test_valideaza_student_id_rau(self, valid):
        Student_id_rau = student(-23, "Gigi Becali", 213)
        try:
            valid.valideaza(Student_id_rau)
            assert False
        except ValidationError as ve:
            assert str(ve) == "Id invalid!\n"


    def __test_valideaza_student_nume_rau(self, valid):
        Student_nume_rau = student(23, "", 213)
        try:
            valid.valideaza(Student_nume_rau)
            assert False
        except ValidationError as ve:
            assert str(ve) == "Nume invalid!\n"


    def __test_valideaza_student_grup_rau(self, valid):
        Student_grup_rau = student(23, "Gigi Becali", -11)
        try:
            valid.valideaza(Student_grup_rau)
            assert False
        except ValidationError as ve:
            assert str(ve) == "Grup invalid!\n"


    def __test_valideaza_student_rau(self, valid):
        Student_rau = student(-23, "", 0)
        try:
            valid.valideaza(Student_rau)
            assert False
        except ValidationError as ve:
            assert str(ve) == "Id invalid!\nNume invalid!\nGrup invalid!\n"

    def __test_valideaza_student(self):
        valid = self.__test_valideaza_student_bun()
        self.__test_valideaza_student_id_rau(valid)
        self.__test_valideaza_student_nume_rau(valid)
        self.__test_valideaza_student_grup_rau(valid)
        self.__test_valideaza_student_rau(valid)
            
            


    def __test_adauga_student_bun_in_lista(self):
        file_path = "teste/test_student.txt"
        with open(file_path, 'w') as f:
            f.write("")
        l = file_repo_studenti(file_path)
        assert len(l) == 0
        assert l.__len__() == 0
        id_student = 23
        nume = "Gigi Becali"
        grup = 213
        Student = student(id_student, nume, grup)
        l.adauga_student_lista(Student)
        assert len(l) == 1
        return l, id_student, Student


    def __test_adauga_student_deja_existent_in_lista(self, l, id_student):
        nume_nou = "LALA"
        grup_nou = 2199
        Student_nou = student(id_student, nume_nou, grup_nou)
        try:
            l.adauga_student_lista(Student_nou)
            assert False
        except ShearchingError as se:
            assert str(se) == "Id deja existent!\n"
            


    def __test_cauta_dupa_id_stud_bun(self, l):
        id_stud = 23
        stud = l.cauta_dupa_id(id_stud)
        assert len(str(stud)) > 0


    def __test_cauta_dupa_id_stud_rau(self, l):
        id_inexistent = 88
        try:
            l.cauta_dupa_id(id_inexistent)
            assert False
        except ShearchingError as se:
            assert str(se) == "Id inexistent!\n"


    def __test_stergere_student_id_bun(self, l, id_student, Student):
        id_student = 25
        nume = "ALAL"
        grup = 213
        Student = student(id_student, nume, grup)
        l.adauga_student_lista(Student)
        b = len(l)
        l.sterge_student_dupa_id_file(id_student)
        assert len(l) == b - 1
        return Student


    def __test_stergere_student_id_inex(self, l):
        id_stud_inex = 6458624353
        try:
            l.sterge_student_dupa_id_file(id_stud_inex)
            assert False
        except DeleteError as dr:
            assert str(dr) == "Nu avem nici-un student cu id-ul selectat!\n"


    def __test_stergere_student_id_rau(self, l):
        id_stud_rau = -27
        try:
            l.sterge_student_dupa_id_file(id_stud_rau)
            assert False
        except ValidationError as ve:
            assert str(ve) == "Id invalid!\n"


    def __test_get_all_studenti(self, l, Student):
        a = l.get_all_students()
        assert len(a) == 1
        assert a[0] == Student
        assert a[0].get_nume() == Student.get_nume()
        assert a[0].get_grup() == Student.get_grup()

    def __test_repository_student(self):
        l, id_student, Student = self.__test_adauga_student_bun_in_lista()
        self.__test_adauga_student_deja_existent_in_lista(l, id_student)
        
        
        self.__test_cauta_dupa_id_stud_bun(l)
        self.__test_cauta_dupa_id_stud_rau(l)
        
        self.__test_get_all_studenti(l, Student)   
        
        Student = self.__test_stergere_student_id_bun(l, id_student, Student)
        self.__test_stergere_student_id_inex(l)
        self.__test_stergere_student_id_rau(l)

        
         
    
    
    def __test_srv_adauga_in_lista_student_bun(self):
        l = repo_student()
        v = valideaza_student()
        srv = srv_student(l, v)
        id_student = 23
        nume = "GIGI BECAli"
        grupa = 123
        assert srv.get_nr_studenti() == 0
        srv.adauga_studenti(id_student, nume, grupa)
        assert srv.get_nr_studenti() == 1
        return srv, id_student

    def __test_srv_adauga_in_lista_student_id_deja_existent(self, srv, id_student):
        alt_nume = "GIGI BECAli"
        alta_grupa = 123
        try:
            srv.adauga_studenti(id_student, alt_nume, alta_grupa)
            assert False
        except ShearchingError as se:
            assert str(se) == "Id deja existent!\n"
    
    def __test_srv_adauga_in_lista_student_rau(self, srv):
        id_rau = -1919
        nume_rau = ""
        grupa_rea = -182
        try:
            srv.adauga_studenti(id_rau, nume_rau, grupa_rea)
            assert False
        except ValidationError as ve:
            assert str(ve) == "Id invalid!\nNume invalid!\nGrup invalid!\n"

    def __test_srv_adauga_in_lista(self):
        srv, id_student = self.__test_srv_adauga_in_lista_student_bun()
        self.__test_srv_adauga_in_lista_student_id_deja_existent(srv, id_student)
        self.__test_srv_adauga_in_lista_student_rau(srv)
        
        

            
    def __test_creaza_problema(self):
        numar_lab=1
        numar_pb=1
        descriere="problema cu sir de caractere"
        dedline="23/3/2021"
        prob=problema(numar_lab, numar_pb, descriere, dedline)
        assert(prob.get_numar_lab()==numar_lab)
        assert(prob.get_numar_pb()==numar_pb)
        assert(prob.get_descriere()==descriere)
        assert(prob.get_dedline()==dedline)
        alta_descriere="problema cu sir"
        alt_dedline="23/4/2021"
        alta_prob=problema(numar_lab, numar_pb, alta_descriere, alt_dedline)
        assert(prob==alta_prob)
        assert(prob.__eq__(alta_prob))
        
    
    
    
    def __test_valideaza_problema(self):
        prob=problema(1,1,"Problema","23/3/2021")
        valid=valideaza_problema()
        valid.validare_problema(prob)
        
        prob_nr_lab_rau=problema(0,1,"Problema","23/3/2021")
        try:
            valid.validare_problema(prob_nr_lab_rau)
            assert False
        except ValidationError as ve:
            assert(str(ve)=="Numar de laborator invalid!\n")
        
        prob_nr_lab_rau=problema(0,-5,"","")
        try:
            valid.validare_problema(prob_nr_lab_rau)
            assert False
        except ValidationError as ve:
            assert(str(ve)=="Numar de laborator invalid!\nNumar de problema invalida!\nDescriere invalida!\nDedline invalid!\n")
        
    
    
    

    def __test_repo_problema_buna(self):
        file_path="teste/test_problema.txt"
        with open(file_path,'w') as f:
            f.write("")
        l = file_repo_problema(file_path) 
        nr_lab = 1
        nr_pb = 1
        descriere = "Problema de mate"
        dedline = "23/2/2021"
        prob = problema(nr_lab, nr_pb, descriere, dedline)
        
        assert len(l) == 0
        assert l.__len__() == 0
        l.adauga_problema_in_lista(prob)
        assert len(l) == 1
        return nr_lab, nr_pb, l, prob


    def __test_repo_problema_nr_lab_si_nr_pb_identice(self, nr_lab, nr_pb, l):
        descriere_noua = "Problema de mate"
        dedline_nou = "23/2/2021"
        alta_prob = problema(nr_lab, nr_pb, descriere_noua, dedline_nou)
        try:
            l.adauga_problema_in_lista(alta_prob)
            assert False
        except ShearchingError as se:
            assert str(se) == "Problema deja existenta!\n"


    def __test_repo_problema_nr_lab_si_nr_pb_inex(self, l):
        nr_lab_inex = 10000
        nr_pb_inex = 100000
        try:
            l.cauta_problema(nr_lab_inex, nr_pb_inex)
            assert False
        except ShearchingError as se:
            assert str(se) == "Nu avem nici-o probmea cu acest numar de laborator si de problema!\n"


    def __test_repo_problema_all(self, l, prob):
        all = l.get_all_problems()
        assert len(all) == 1
        assert all[0].get_numar_lab() == prob.get_numar_lab()
        assert all[0].get_numar_pb() == prob.get_numar_pb()
        assert all[0].get_descriere() == prob.get_descriere()
        assert all[0].get_dedline() == prob.get_dedline()

    def __test_repo_problema(self):
        nr_lab, nr_pb, l, prob = self.__test_repo_problema_buna()
        self.__test_repo_problema_nr_lab_si_nr_pb_identice(nr_lab, nr_pb, l)
        self.__test_repo_problema_nr_lab_si_nr_pb_inex(l)    
        self.__test_repo_problema_all(l, prob)
            
            
    def __test_srv_probleme(self):
        l=repo_problema()
        v=valideaza_problema()
        srv=srv_problema(l,v)
        nr_lab=1
        nr_pb=1
        descriere="problema"
        dedline="23/2/2021"
        assert(srv.get_nr_probleme()==0)
        srv.adauga_probleme(nr_lab,nr_pb,descriere,dedline)
        assert(srv.get_nr_probleme()==1)
        
        alta_descriere="papa"
        alt_dedline="22/1/2022"
        try:
            srv.adauga_probleme(nr_lab,nr_pb,alta_descriere,alt_dedline)
            assert False
        except ShearchingError as se:
            assert(str(se)=="Problema deja existenta!\n")
            
        nr_lab_rau=-23
        nr_pb_rea=-237647236
        descriere_rea=""
        dedline_rau=""
        try:
            srv.adauga_probleme(nr_lab_rau,nr_pb_rea,descriere_rea,dedline_rau)
            assert False
        except ValidationError as ve:
            assert(str(ve)=="Numar de laborator invalid!\nNumar de problema invalida!\nDescriere invalida!\nDedline invalid!\n")\
            
            

    def __test_stergere_problema_buna(self):
        file_path="teste/test_problema.txt"
        with open(file_path,'w') as f:
            f.write("")
        a=file_repo_problema(file_path)
        nr_lab = 1
        nr_pb = 1
        descriere = "problema"
        dedline = "23/2/19"
        prob=problema(nr_lab,nr_pb,descriere,dedline)
        a.adauga_problema_in_lista(prob)
        a.sterge_problema_file(nr_lab, nr_pb)
        assert(len(a)==0)
        return a


    def __test_stergere_probl_inex(self, a):
        nr_lab_inex = 786785325623
        nr_pb_inex = 726767637
        try:
            a.sterge_problema(nr_lab_inex, nr_pb_inex)
            assert False
        except DeleteError as de:
            assert str(de) == "Nu avem nici-o problema cu numarul de laborator si de problema selectat!\n"


    def __test_stergere_problema_invalida(self, a):
        nr_lab_invalid = -786785325623
        nr_pb_invalid = -726767637
        try:
            a.sterge_problema(nr_lab_invalid, nr_pb_invalid)
            assert False
        except ValidationError as ve:
            assert str(ve) == "Numar de laborator invalid!\nNumar de problema invalida!\n"

    def __test_sterge_problema(self):
        a = self.__test_stergere_problema_buna()
        self.__test_stergere_probl_inex(a)
        self.__test_stergere_problema_invalida(a)
    
    
    def __test_valideaza_studenti_pachete(self):
        pass
    
    
    def run_teste(self):
        self.__test_creaza_student()
        self.__test_valideaza_student()
        self.__test_repository_student()
        self.__test_srv_adauga_in_lista()
        self.__test_creaza_problema()
        self.__test_valideaza_problema()
        self.__test_repo_problema()
        self.__test_srv_probleme()
        self.__test_sterge_problema()
        self.__test_valideaza_studenti_pachete()
