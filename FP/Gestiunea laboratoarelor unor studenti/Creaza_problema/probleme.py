class problema(object):
    def __init__(self,numar_lab,numar_pb,descriere,dedline):
        self.__numar_lab = numar_lab
        self.__numar_pb = numar_pb
        self.__descriere = descriere
        self.__dedline = dedline

    def get_numar_lab(self):
        return self.__numar_lab


    def get_numar_pb(self):
        return self.__numar_pb


    def get_descriere(self):
        return self.__descriere


    def get_dedline(self):
        return self.__dedline


    def set_numar_lab(self, value):
        self.__numar_lab = value


    def set_descriere(self, value):
        self.__descriere = value


    def set_dedline(self, value):
        self.__dedline = value


    def __eq__(self, other):
        if other.get_numar_lab()==self.__numar_lab and other.get_numar_pb()==self.__numar_pb:
            return 1

