#include "RetetaGUI.h"


void GUIReteta::initializare_campuri_reteta()
{
	setLayout(main_layout);
	QWidget* widget_lista = new QWidget;
	QVBoxLayout* layout_buton_lista = new QVBoxLayout;
	widget_lista->setLayout(layout_buton_lista);
	lista_reteta = new QListWidget;
	layout_buton_lista->addWidget(lista_reteta);

	QWidget* widget_butoane = new QWidget;
	QFormLayout* layout_widget_butoane = new QFormLayout;
	widget_butoane->setLayout(layout_widget_butoane);

	denumire_medicament_pentru_reteta = new QLineEdit;
	layout_widget_butoane->addRow(new QLabel("Denumirea medicamentului pe care vrei sa il adaugi in reteta: "), denumire_medicament_pentru_reteta);
	buton_adauga_in_reteta = new QPushButton("Adauga medicamente in reteta");
	layout_widget_butoane->addWidget(buton_adauga_in_reteta);

	buton_goleste_reteta = new QPushButton("Goleste reteta de medicamente");
	layout_buton_lista->addWidget(buton_goleste_reteta);
	buton_close = new QPushButton("Inchide fereastra de reteta");
	layout_buton_lista->addWidget(buton_close);

	numarul_de_elemente_pe_care_le_adaug_random_in_reteta = new QLineEdit;
	layout_widget_butoane->addRow(new QLabel("Nuamrul de mediamente pe care vrei sa le adaugi random in reteta"), numarul_de_elemente_pe_care_le_adaug_random_in_reteta);
	buton_adauga_random_in_reteta = new QPushButton("Adauga medicamente random in reteta");
	layout_widget_butoane->addWidget(buton_adauga_random_in_reteta);
	buton_numar_elemente_din_playlist = new QPushButton("Numarul de elemente din reteta");
	buton_numar_elemente_din_playlist->setStyleSheet("background-color: yellow");
	layout_widget_butoane->addWidget(buton_numar_elemente_din_playlist);
	buton_desen_medicamente = new QPushButton("Desenul numarului de medicamente");
	buton_desen_medicamente->setStyleSheet("background-color: red");
	layout_widget_butoane->addWidget(buton_desen_medicamente);
	main_layout->addWidget(widget_lista);
	main_layout->addWidget(widget_butoane);

}

void GUIReteta::conectare_butoane_reteta()
{
	QObject::connect(buton_adauga_in_reteta, &QPushButton::clicked, this, &GUIReteta::adauga_mediamente_in_reteta);
	QObject::connect(buton_goleste_reteta, &QPushButton::clicked, this, &GUIReteta::goleste_reteta);
	QObject::connect(buton_adauga_random_in_reteta, &QPushButton::clicked, this, &GUIReteta::adauga_medicamente_random_in_reteta);
	QObject::connect(buton_numar_elemente_din_playlist, &QPushButton::clicked, [&]() {
		numarul_de_elemente.show();
	});
	QObject::connect(buton_desen_medicamente, &QPushButton::clicked, [&]() {
		desen_numar_elemente.show();
	});
	QObject::connect(buton_close, &QPushButton::clicked, this, &GUIReteta::close);
}

void GUIReteta::luam_lista_de_mediamente_din_reteta(vector<Medicament> lista_de_reteta)
{
	lista_reteta->clear();
	for (const Medicament& medicament_din_reteta : service_medicament_reteta.get_toate_medicamentele_din_reteta())
	{
		lista_reteta->addItem(QString::fromStdString("Denumire: " + medicament_din_reteta.get_denumire() + ", Pret: " + std::to_string(medicament_din_reteta.get_pret()) + ", Producator: " + medicament_din_reteta.get_producator() + ", Substanta activa: " + medicament_din_reteta.get_substanta_activa()));
	}
}

void GUIReteta::adauga_mediamente_in_reteta()
{
	try {
		service_medicament_reteta.adauga_medicament_in_reteta(denumire_medicament_pentru_reteta->text().toStdString());
		luam_lista_de_mediamente_din_reteta(service_medicament_reteta.get_toate_medicamentele_din_reteta());
	}
	catch (Exceptie_de_adaugare& exceptie) {
		QMessageBox::warning(this, "Warning!", QString::fromStdString(exceptie.get_mesaje_de_erori()));
	}
}

void GUIReteta::goleste_reteta()
{
	try {
		service_medicament_reteta.golseste_reteta_de_medicamente();
		luam_lista_de_mediamente_din_reteta(service_medicament_reteta.get_toate_medicamentele_din_reteta());
	}
	catch (Exceptie_de_stergere& exceptie) {
		QMessageBox::warning(this, "Warning!", QString::fromStdString(exceptie.get_mesaje_de_erori()));
	}
}

void GUIReteta::adauga_medicamente_random_in_reteta()
{
	service_medicament_reteta.adauga_medicamente_random_in_cos(numarul_de_elemente_pe_care_le_adaug_random_in_reteta->text().toDouble());
	luam_lista_de_mediamente_din_reteta(service_medicament_reteta.get_toate_medicamentele_din_reteta());
}

