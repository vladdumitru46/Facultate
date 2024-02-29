class student(object):
    #Clasa care creaza un student
    
    def __init__(self,id_student,nume,grup):
        #functie care creaza un student
        '''
        input: id_student= id-ul unui student
               nume= numele unui stundet
               grup= grupa unui student
        '''
        self.__id_student=id_student
        self.__nume=nume
        self.__grup=grup

    def get_id_student(self):
        return self.__id_student


    def get_nume(self):
        return self.__nume


    def get_grup(self):
        return self.__grup


    def set_nume(self, value):
        self.__nume = value


    def __eq__(self, other):
        return self.__id_student==other.__id_student
    
    
    def __str__(self):
        return "Id:"+str(self.__id_student)+", nume:"+self.__nume+", grupa:"+str(self.__grup)

