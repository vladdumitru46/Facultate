import unittest
from Validare.validare import valideaza_student, valideaza_problema,\
    valideaza_student_problema
from Creaza_student.student import student
from Repository.repo import file_repo_studenti, file_repo_problema,\
    repo_student_problema
from Exceptii.exceptii import ValidationError, AddError, ShearchingError,\
    DeleteError
from Service.srv_lista import srv_student, srv_problema, srv_student_problema
from Creaza_problema.probleme import problema
from DTOS.dtos import StudentProblema



class TestCaseStudent(unittest.TestCase):
    def setUp(self):
        self.__validator=valideaza_student()
        self.__Student=student(23,"Gigi Becali",213)
        self.__alt_Student=student(24,"Gigi Becali",213)
        self.__Student_rau=student(0,"",-5)
        self.__l=file_repo_studenti("teste/test_student.txt")
        self.__srv=srv_student(self.__l,self.__validator)
        
    def test_creaza_student(self):
        self.assertEqual(self.__Student.get_id_student(),23)
        self.assertEqual(self.__Student.get_nume(),"Gigi Becali")
        self.assertEqual(self.__Student.get_grup(),213)
        alt_student=student(23,"",222)
        self.assertEqual(self.__Student,alt_student)
        
    
    def test_valideaza_student(self):
        #test valodeaza student id rau
        self.assertRaises(ValidationError, self.__validator.valideaza,student(-8,"YFGYF",213))
        #test valideaza student nume rau
        self.assertRaises(ValidationError, self.__validator.valideaza,student(8,"",213))
        #test valideaza student grup rau
        self.assertRaises(ValidationError, self.__validator.valideaza,student(23,"YFGYF",-286))
        #test valideaza student rau
        self.assertRaises(ValidationError, self.__validator.valideaza,self.__Student_rau)
    
    def test_repo_student(self):
        #Test adauga un student bun in lista 
        file_path = "teste/test_student.txt"
        with open(file_path, 'w') as f:
            f.write("")
        self.assertEqual(len(self.__l),0)
        self.__l.adauga_student_lista(self.__Student)
        self.assertEqual(len(self.__l),1)
        
        #test adauga un student deja existent in lista
        self.assertRaises(AddError,self.__l.adauga_student_lista,self.__Student)
        
        
        #test cauta un student dupa id
        st=self.__l.cauta_dupa_id(23)
        self.assertGreater(len(str(st)),0)
        
        #test cauta un student dupa un id inex
        self.assertRaises(ShearchingError,self.__l.cauta_dupa_id,99)
        
        #test cauta un student dupa un id invalid
        self.assertRaises(ValidationError,self.__l.cauta_dupa_id,-786)
        
        #Test get all studentu
        alll =self.__l.get_all_students()
        self.assertEqual(alll[0],self.__Student)
        self.assertTrue(alll[0].get_nume()==self.__Student.get_nume())
        self.assertTrue(alll[0].get_grup()==self.__Student.get_grup())
        
        #Test stergere de student
        self.__l.adauga_student_lista(self.__alt_Student)
        b=len(self.__l)
        self.__l.sterge_student_dupa_id_file(24)
        self.assertTrue(len(self.__l)==b-1)
        
        #Test stergere student id inexistent
        self.assertRaises(DeleteError,self.__l.sterge_student_dupa_id,8656454)
        #Test stergere student dupa un id invalid
        self.assertRaises(ValidationError,self.__l.sterge_student_dupa_id,-836723)
        
    def test_srv_student(self):
        #Test srv adauga student id rau
        self.assertRaises(ValidationError,self.__srv.adauga_studenti,-2,"IGS",213)
        #Test srv adauga student nume rau
        self.assertRaises(ValidationError,self.__srv.adauga_studenti,25,"",213)
        #Test srv adauga student grupa rea
        self.assertRaises(ValidationError,self.__srv.adauga_studenti,26,"gfwdf",-213)
        #Test srv aduga student rau
        self.assertRaises(ValidationError,self.__srv.adauga_studenti,-23,"",-213)

            

class TestCaseProblema(unittest.TestCase):
    def setUp(self):
        self.__validator_de_probleme=valideaza_problema()
        self.__m=file_repo_problema("teste/test_problema.txt")
        self.__srv_p=srv_problema(self.__m,self.__validator_de_probleme)
        
        
    def test_creaza_problema(self):
        Problema=problema(1,1,"JDNJD","23/2/2021")
        self.assertTrue(Problema.get_numar_lab()==1)
        self.assertTrue(Problema.get_numar_pb()==1)
        self.assertTrue(Problema.get_descriere()=="JDNJD")
        self.assertTrue(Problema.get_dedline()=="23/2/2021")
        alta_problema=Problema=problema(1,1,"JDsgufusJD","23/2/2022")
        self.assertEqual(Problema,alta_problema)
    
    def test_valideaza_problema(self):    
        #test validare problema nr lab invalid
        self.assertRaises(ValidationError,self.__validator_de_probleme.validare_problema,problema(-1,1,"wgfdy","23/1/2021"))
        #test valideaza problema nr pb invalida
        self.assertRaises(ValidationError,self.__validator_de_probleme.validare_problema,problema(1,-1,"wgfdy","23/1/2021"))
        #test valideaza descriere invalida
        self.assertRaises(ValidationError,self.__validator_de_probleme.validare_problema,problema(1,1,"","23/1/2021"))
        #test valideaza dedline invalid
        self.assertRaises(ValidationError,self.__validator_de_probleme.validare_problema,problema(1,1,"wgfdy",""))
        #test valideaza problema invalida
        self.assertRaises(ValidationError,self.__validator_de_probleme.validare_problema,problema(-1,-1,"",""))
    

        
    def test_repo_problema(self):
        file_path = "teste/test_problema.txt"
        with open(file_path, 'w') as f:
            f.write("")
        #test adauga problema
        self.assertTrue(len(self.__m)==0)
        self.__m.adauga_problema_in_lista(problema(1,1,"hdbfahs","23/1/2021"))
        self.assertTrue(len(self.__m)==1)    
        #test adauga problema deja existenta
        self.assertRaises(AddError, self.__m.adauga_problema_in_lista,problema(1,1,"suidgfysg","22/3/2021"))
        
        
        #test cauta problema
        pb=self.__m.cauta_problema(1, 1)
        self.assertTrue(len(str(pb))>0)
        #test cauta problema inexistenta
        self.assertRaises(ShearchingError,self.__m.cauta_problema,3453, 1313)
        #test cauta problema invalida
        self.assertRaises(ValidationError,self.__m.cauta_problema,-24234,-343)
        
        
        #test get all problems
        prob=problema(1,1,"hdbfahs","23/1/2021")
        alll=self.__m.get_all_problems()
        self.assertEqual(alll[0],prob)
        self.assertEqual(alll[0].get_descriere(),prob.get_descriere())
        self.assertEqual(alll[0].get_dedline(),prob.get_dedline())
        
        #test stergere problema
        prob_sterg=problema(1,2,"hdbfahs","23/1/2021")
        self.__m.adauga_problema_in_lista(prob_sterg)
        b=len(self.__m)
        self.__m.sterge_problema_file(1,2)
        self.assertEqual(len(self.__m),b-1)
        
        #test stegrere prob inex
        self.assertRaises(DeleteError,self.__m.sterge_problema_file,374634,136564)
        #test stergere problema invalida
        self.assertRaises(ValidationError,self.__m.sterge_problema_file,-341, -4734)
        
        
    def test_srv_problema(self):
        file_path = "teste/test_problema.txt"
        with open(file_path, 'w') as f:
            f.write("")
        #test srv adauga problema
        self.assertEqual(len(self.__srv_p.get_all_prob()),0)
        self.__srv_p.adauga_probleme(1,1,"shjdvgdf","23/2/2021")
        self.assertEqual(len(self.__srv_p.get_all_prob()),1)
       

        #test srv adauga probl nr lab invalid
        self.assertRaises(ValidationError,self.__srv_p.adauga_probleme,-1,1,"sudfud","23/1/2021")
        #test srv adauga nr pb invalid
        self.assertRaises(ValidationError,self.__srv_p.adauga_probleme,1,-1,"sudfud","23/1/2021")
        #test srv adauga descriere invalida
        self.assertRaises(ValidationError,self.__srv_p.adauga_probleme,2,1,"","23/1/2021")
        #test srv adauga dedline invalid
        self.assertRaises(ValidationError,self.__srv_p.adauga_probleme,2,2,"sudfud","")
        #test srv adauga prob invalida
        self.assertRaises(ValidationError,self.__srv_p.adauga_probleme,-1,-1,"","")




class TestCaseStudProb(unittest.TestCase):
    def setUp(self):
        self.__valid_stud_prob=valideaza_student_problema()
        self.__n=repo_student_problema("teste/test_studenti_probleme.txt")
        self.__srv_sp=srv_student_problema(self.__n,self.__valid_stud_prob,file_repo_studenti("teste/test_student.txt"),file_repo_problema("teste/test_problema.txt"))
    
    
    def test_creaza_stud_prob(self):
        stud_prob=StudentProblema(23,1,1,10)
        self.assertEqual(stud_prob.get_id_stud(),23)
        self.assertEqual(stud_prob.get_nr_lab(),1)
        self.assertEqual(stud_prob.get_nr_pb(), 1)
        self.assertEqual(stud_prob.get_nota_stud(),10)
    
    
    def test_valideaza_stud_prob(self):
        #test valideaza asigmnetul id stud rau
        self.assertRaises(ValidationError,self.__valid_stud_prob.validare_stud_prob, StudentProblema(-1,1,1,10) )
        #est valideaza asigm nr lab rau
        self.assertRaises(ValidationError,self.__valid_stud_prob.validare_stud_prob, StudentProblema(1,-1,1,10) )
        #test valideaza asigm nr pb rea
        self.assertRaises(ValidationError,self.__valid_stud_prob.validare_stud_prob, StudentProblema(1,1,-1,10) )
        #test valideaza asigm nota rea
        self.assertRaises(ValidationError,self.__valid_stud_prob.validare_stud_prob, StudentProblema(1,1,1,-10) )
        #test valideaza asigm rau
        self.assertRaises(ValidationError,self.__valid_stud_prob.validare_stud_prob, StudentProblema(-1,-1,-1,-10) )
    
    def test_repo_stud_prob(self):
        with open("teste/test_studenti_probleme.txt",'w') as f:
            f.write("")
        #test asign lab la stud
        self.assertTrue(len(self.__n.get_all_studenti_probleme())==0)
        stud_prob=StudentProblema(23,1,1,10)
        self.__n.asign_lab_note(stud_prob)
        self.assertTrue(len(self.__n.get_all_studenti_probleme())==1)
        
        #test get all asigm
        alll=self.__n.get_all_studenti_probleme()
        self.assertTrue(len(alll)==1)
        self.assertTrue(alll[0].get_id_stud()==stud_prob.get_id_stud())
        self.assertTrue(alll[0].get_nr_lab()==stud_prob.get_nr_lab())
        self.assertTrue(alll[0].get_nr_pb()==stud_prob.get_nr_pb())
        self.assertTrue(alll[0].get_nota_stud()==stud_prob.get_nota_stud())


        #test sterge asigm
        stud_prob=StudentProblema(24,1,1,10)
        b=len(self.__n.get_all_studenti_probleme())
        self.__n.sterge_asigm(23, 1, 1)
        self.assertTrue(len(self.__n.get_all_studenti_probleme())==b-1)
        #test sterge asigm inex
        self.assertRaises(DeleteError,self.__n.sterge_asigm,33,33,33)
        #test sterge asigm invalid
        self.assertRaises(ValidationError, self.__n.sterge_asigm,-23,-1,-1)
    
    def test_srv_stud_prob(self):
        with open("teste/test_studenti_probleme.txt",'w') as f:
            f.write("")
            
        #test srv adauga asigm bun
        self.assertTrue(len(self.__srv_sp.get_all_studneti_problema())==0)
        self.__srv_sp.adauga_stud_prob(23,1,1,10)
        self.assertTrue(len(self.__srv_sp.get_all_studneti_problema())==1)
        #test srv adauga asigmnetul id stud rau
        self.assertRaises(ValidationError,self.__srv_sp.adauga_stud_prob,-1,1,1,10 )
        #est srv adauga asigm nr lab rau
        self.assertRaises(ValidationError,self.__srv_sp.adauga_stud_prob,1,-1,1,10 )
        #test srv adauga asigm nr pb rea
        self.assertRaises(ValidationError,self.__srv_sp.adauga_stud_prob,1,1,-1,10) 
        #test srv adauga asigm nota rea
        self.assertRaises(ValidationError,self.__srv_sp.adauga_stud_prob, 2,1,1,-10) 
        #test srv adauga asigm rau
        self.assertRaises(ValidationError,self.__srv_sp.adauga_stud_prob,-1,-1,-1,-10) 
    
    
    def test_medie_sub_5(self):
        with open("teste/test_studenti_probleme.txt",'w') as f:
            f.write("") 
        self.__srv_sp.adauga_stud_prob(23,1,1,1)
        self.__srv_sp.adauga_stud_prob(23,1,1,2)
        self.__srv_sp.adauga_stud_prob(24,1,1,10)
        self.__srv_sp.adauga_stud_prob(24,1,1,9)   
        a=self.__srv_sp.medie_sub_5()
        self.assertTrue(len(a)==1)
     
        
        
        
        
        
        
        
        
