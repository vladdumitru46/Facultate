#pragma once
#include "Repository.h"
#include "Medicament.h"


class ActiuneUndo {
public: 
	virtual void doUndo() = 0;
	virtual ~ActiuneUndo() = default;
};



class UndoAdauga : public ActiuneUndo {
private:
	Repository& reteta_de_medicamente;
	Medicament medicament_pentru_undo;
public:
	UndoAdauga(Repository& reteta_de_medicamente, Medicament& medicament_de_undo) : reteta_de_medicamente{ reteta_de_medicamente }, medicament_pentru_undo{ medicament_de_undo }{};

	void doUndo() override {
		reteta_de_medicamente.sterge_medicament_din_lista(medicament_pentru_undo.get_denumire());
	}
};



class UndoSterge : public ActiuneUndo {
private:
	Repository& reteta_de_medicamente;
	Medicament medicament_undo;
public:
	UndoSterge(Repository& reteta_de_medicamente, Medicament& medicament_de_undo) : reteta_de_medicamente{ reteta_de_medicamente }, medicament_undo{ medicament_de_undo }{};
	void doUndo()override {
		reteta_de_medicamente.adauga_medicament_in_lista(medicament_undo);
	}
};



class UndoModifica : public ActiuneUndo {
private:
	Repository& reteta_de_medicamente;
	Medicament medicament_undo;
public:
	UndoModifica(Repository& reteta_de_medicamente, Medicament& medicament_undo) : reteta_de_medicamente{ reteta_de_medicamente }, medicament_undo{ medicament_undo }{};
	void doUndo()override {
		reteta_de_medicamente.sterge_medicament_din_lista(medicament_undo.get_denumire());
		reteta_de_medicamente.adauga_medicament_in_lista(medicament_undo);
	}
};