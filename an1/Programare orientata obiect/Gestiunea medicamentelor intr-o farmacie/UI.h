#pragma once
#include "VectorDinamicTemplate.h"
#include "Service.h"
#include "Undo.h"

class ConsoleUI {

private:
	ServiceMedicament& service_medicament;

public:
	ConsoleUI(ServiceMedicament& service_medicament) noexcept :service_medicament { service_medicament }{};

	ConsoleUI(const ConsoleUI& constructor_de_copiere) = delete;

	void meniu();
	void meniu_medicament();
	void meniu_reteta();
	void afiseaza_toate_medicamentele(const vector<Medicament> toate_medicamentele);
	void run();
};