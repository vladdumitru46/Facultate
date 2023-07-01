#pragma once
#include <vector>
#include <string>
using std::string;
using std::vector;
class Exceptii {
private:
	string mesaje_de_erori;
public:
	Exceptii(string mesaj_de_erori) : mesaje_de_erori{ mesaj_de_erori } {};
	virtual string get_mesaje_de_erori()
	{
		return this->mesaje_de_erori;
	}
	virtual ~Exceptii() = default;
};

class Exceptie_de_adaugare : public Exceptii {
public:
	Exceptie_de_adaugare(string mesaje_de_erori) : Exceptii(mesaje_de_erori) {};
};

class Exceptie_de_stergere : public Exceptii {
public:
	Exceptie_de_stergere(string mesaje_de_erori) : Exceptii(mesaje_de_erori) {};
};

class Exceptie_de_cautare : public Exceptii {
public:
	Exceptie_de_cautare(string mesaje_de_erori) : Exceptii(mesaje_de_erori) {};
};

class Exceptie_de_modificare : public Exceptii {
public:
	Exceptie_de_modificare(string mesaje_de_erori) : Exceptii(mesaje_de_erori) {};
};

class Exceptie_undo : public Exceptii {
public: 
	Exceptie_undo(string mesaj_de_erori) : Exceptii(mesaj_de_erori) {};
};