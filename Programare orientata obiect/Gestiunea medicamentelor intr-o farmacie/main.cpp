#pragma once
#include <QtWidgets/QApplication>
#include <iostream>
#include "Teste.h"
#include "UI.h"
#include "Gui.h"


void run(int argc, char* argv[])
{
	QApplication aplicatie(argc, argv);
	Teste test;
	RepositoryFisier repo_medicament{ "Fisier_medicamente.txt" };
	RepoRetetaFisier reteta_de_medicamente{ repo_medicament, "Fisier_reteta.txt" };
	Validare_de_medicamente validare_medicament{};
	ServiceMedicament service_medicament{ repo_medicament, validare_medicament, reteta_de_medicamente };
	GUIMedicament gui_medicament{ service_medicament };
	Numarul_de_elemente_din_reteta numarul_de_medicamente(reteta_de_medicamente);
	Deseneaza_numarul_de_medicamente desen_numar_meddicamente(reteta_de_medicamente);
	GUIReteta gui_reteta{ service_medicament, numarul_de_medicamente, desen_numar_meddicamente };
	GUIMain gui_main{ gui_medicament, gui_reteta };
	test.run_teste();
	gui_main.show();
	aplicatie.exec();
}

int main(int argc, char* argv[])
{
	run(argc, argv);
	return 0;
}
