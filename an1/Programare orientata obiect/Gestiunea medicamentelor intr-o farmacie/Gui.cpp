#include "Gui.h"
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QFormLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QMessageBox>
#include <Qt>

void GUIMedicament::initializare_campuri_GUI()
{
	setLayout(main_layout);
	//adaug lista si sub am trei butoane de sortare: Sorteaza dupa pret, Sorteaza dupa producator, Sorteaza dupa substanta activa si pret
	QWidget* widget_dreapta = new QWidget;
	QVBoxLayout* forma_buronului = new QVBoxLayout;
	widget_dreapta->setLayout(forma_buronului);
	lista = new QListView;
	//lista = new QListWidget;
	forma_buronului->addWidget(lista);

	int numar_linii = 40;
	int numar_coloane = 4;
	tabel_medicamente = new QTableWidget{ numar_linii, numar_coloane, this };
	QStringList denumire_header = QStringList();
	denumire_header.append("Denumire");
	denumire_header.append("Pret");
	denumire_header.append("Producator");
	denumire_header.append("Substanta Activa");
	tabel_medicamente->setHorizontalHeaderLabels(denumire_header);
	forma_buronului->addWidget(tabel_medicamente);
	//primul buton
	QWidget* widget_buton_drepata = new QWidget;
	QHBoxLayout* layout_buton_drepata = new QHBoxLayout;
	widget_buton_drepata->setLayout(layout_buton_drepata);
	buton_sorteaza_dupa_denumire = new QPushButton("Sorteaza dupa denumire");
	layout_buton_drepata->addWidget(buton_sorteaza_dupa_denumire);

	//al doilea buton
	buton_sorteaza_dupa_producator = new QPushButton("Sorteaza dupa producator");
	layout_buton_drepata->addWidget(buton_sorteaza_dupa_producator);

	//al treilea buton
	buton_sorteaza_dupa_substanta_activa_si_pret = new QPushButton("Sorteaza dupa substanta activa si pret");
	layout_buton_drepata->addWidget(buton_sorteaza_dupa_substanta_activa_si_pret);

	forma_buronului->addWidget(widget_buton_drepata);
	main_layout->addWidget(widget_dreapta);

	QWidget* widget_buton_dreapta_filtrare = new QWidget;
	QFormLayout* layout_buton_dreapta_filtare = new QFormLayout;
	widget_buton_dreapta_filtrare->setLayout(layout_buton_dreapta_filtare);
	filtrare_dupa_pret = new QLineEdit;
	layout_buton_dreapta_filtare->addRow(new QLabel("Pretul dupa care vrei sa filtrezi: "), filtrare_dupa_pret);
	filtreaza_dupa_pret = new QPushButton("Filtreaza dupa pret");
	layout_buton_dreapta_filtare->addWidget(filtreaza_dupa_pret);

	filtrare_dupa_substanta_activa = new QLineEdit;
	layout_buton_dreapta_filtare->addRow(new QLabel("Substanta activa dup acare vrei sa filtrezi: "), filtrare_dupa_substanta_activa);
	filtreaza_dupa_substanta_activa = new QPushButton("Filtreaza dupa substanta activa");
	layout_buton_dreapta_filtare->addWidget(filtreaza_dupa_substanta_activa);

	afiseaza_lista_intreaga = new QPushButton("Afiseaza toata lista de medicamente");
	layout_buton_dreapta_filtare->addWidget(afiseaza_lista_intreaga);
	forma_buronului->addWidget(widget_buton_dreapta_filtrare);
	main_layout->addWidget(widget_dreapta);

	//fac un form pentru detalii
	QWidget* widget_detalii = new QWidget;
	QFormLayout* from_detalii = new QFormLayout;
	widget_detalii->setLayout(from_detalii);
	text_Denumire = new QLineEdit;
	from_detalii->addRow(new QLabel("Denumire: "), text_Denumire);
	text_Pret = new QLineEdit;
	from_detalii->addRow(new QLabel("Pret: "), text_Pret);
	text_Producator = new QLineEdit;
	from_detalii->addRow(new QLabel("Producator: "), text_Producator);
	text_Substanta_activa = new QLineEdit;
	from_detalii->addRow(new QLabel("Substanta activa: "), text_Substanta_activa);

	/*
	*/
	//buton adauga medicament
	buton_adaugare = new QPushButton("Adauga medicament");
	from_detalii->addWidget(buton_adaugare);

	//buton cauta medicament
	text_Denumire_cautare = new QLineEdit;
	from_detalii->addRow(new QLabel("Denumirea medicamentului pe care vrei sa il cauti: "), text_Denumire_cautare);
	buton_cautare = new QPushButton("Cauta medicament");
	from_detalii->addWidget(buton_cautare);

	//buton stergere medicament
	text_Denumire_stergere = new QLineEdit;
	from_detalii->addRow(new QLabel("Denumirea medicamentului pe care vrei sa il stergi: "), text_Denumire_stergere);
	buton_stergere = new QPushButton("Sterge medicament");
	from_detalii->addWidget(buton_stergere);

	//buton modifica medicament
	text_Denumire_modificare = new QLineEdit;
	from_detalii->addRow(new QLabel("Denumirea medicamentului pe care vrei sa il modifici: "), text_Denumire_modificare);
	text_Pret_modificat = new QLineEdit;
	from_detalii->addRow(new QLabel("Pretul nou al medicamentului: "), text_Pret_modificat);
	text_Producator_modificat = new QLineEdit;
	from_detalii->addRow(new QLabel("Noul producator al medicamentului: "), text_Producator_modificat);
	text_Substanta_activa_modificata = new QLineEdit;
	from_detalii->addRow(new QLabel("Sunstanta activa noua a medicamentului: "), text_Substanta_activa_modificata);
	buton_modificare = new QPushButton("Modifica medicament");
	from_detalii->addWidget(buton_modificare);

	//buton undo
	undo = new QPushButton("Undo");
	from_detalii->addWidget(undo);

	
	main_layout->addWidget(widget_detalii);

	buton_diametru->setLayout(layout_buton);
	main_layout->addWidget(buton_diametru);

	buton_test->setLayout(layout_buton_test);
	main_layout->addWidget(buton_test);
}

void GUIMedicament::conectam_semnalele_la_sloturi()
{
	
	// cand se emite semnalul clicked, de pe buton, reinacarc lista, si sortez dupa denumire
	QObject::connect(buton_sorteaza_dupa_denumire, &QPushButton::clicked, [&]() {
		luam_lista_de_medicamente(service_medicament.sorteaza_lista_dupa_denumirea_medicamentului());
		luam_tabelul_de_medicamente(service_medicament.sorteaza_lista_dupa_denumirea_medicamentului());
		});
	// cand se emite semnalul clicked, de pe buton, reinacarc lista, si sortez dupa productaor
	QObject::connect(buton_sorteaza_dupa_producator, &QPushButton::clicked, [&]() {
		luam_lista_de_medicamente(service_medicament.sorteaza_lista_dupa_producatorul_medicamentului());
		luam_tabelul_de_medicamente(service_medicament.sorteaza_lista_dupa_producatorul_medicamentului());
		});
	// cand se emite semnalul clicked, de pe buton, reinacarc lista, si sortez dupa substanta activa si pret
	QObject::connect(buton_sorteaza_dupa_substanta_activa_si_pret, &QPushButton::clicked, [&]() {
		luam_lista_de_medicamente(service_medicament.sorteaza_lista_dupa_substanta_activa_si_pretul_medicamentului());
		luam_tabelul_de_medicamente(service_medicament.sorteaza_lista_dupa_substanta_activa_si_pretul_medicamentului());
		});

	//buton filtrare pret
	QObject::connect(filtreaza_dupa_pret, &QPushButton::clicked, [&]() {
		vector<Medicament> vector_filtrat = service_medicament.filtreaza_lista_dupa_pret(filtrare_dupa_pret->text().toDouble());
		luam_lista_de_medicamente(vector_filtrat);
		luam_tabelul_de_medicamente(vector_filtrat);
			});
	QObject::connect(filtreaza_dupa_substanta_activa, &QPushButton::clicked, [&]() {
		luam_lista_de_medicamente(service_medicament.filtreaza_lista_dupa_substanta_activa(filtrare_dupa_substanta_activa->text().toStdString()));
		luam_tabelul_de_medicamente(service_medicament.filtreaza_lista_dupa_substanta_activa(filtrare_dupa_substanta_activa->text().toStdString()));
		});

	QObject::connect(afiseaza_lista_intreaga, &QPushButton::clicked, [&]() {
		luam_lista_de_medicamente(service_medicament.get_toate_medicamentele_din_lista());
		luam_tabelul_de_medicamente(service_medicament.get_toate_medicamentele_din_lista());
		});

	QObject::connect(lista->selectionModel(), &QItemSelectionModel::selectionChanged, [&]() {
		if (lista->selectionModel()->selectedIndexes().isEmpty())
		{
			text_Denumire->setText("");
			text_Pret->setText("");
			text_Producator->setText("");
			text_Substanta_activa->setText("");
		}
		else {
			auto obiectul_selectat = lista->selectionModel()->selectedIndexes().at(0);
			auto denumire = obiectul_selectat.data(Qt::DisplayRole).toString();
			auto producator = obiectul_selectat.data(Qt::UserRole).toString();
			auto substanta_activa = obiectul_selectat.data(Qt::UserRole).toString();
			text_Denumire->setText(denumire);
			text_Producator->setText(producator);
			text_Substanta_activa->setText(substanta_activa);
			auto medicament = service_medicament.service_cauta_un_medicament_in_lista_de_medicamente(denumire.toStdString());
			text_Pret->setText(QString::number(medicament.get_pret()));
		}
		});

	//butonul pentru adaugarea unui medicament nou
	QObject::connect(buton_adaugare, &QPushButton::clicked, this, &GUIMedicament::adauga_un_nou_medicament);

	//butonul pentru cautarea unui medicament
	QObject::connect(buton_cautare, &QPushButton::clicked, this, &GUIMedicament::cauta_un_medicament);

	//butonul pentru stergerea unui medocament
	QObject::connect(buton_stergere, &QPushButton::clicked, this, &GUIMedicament::sterge_un_medicament);

	//butonul pentru modificarea unui medicament
	QObject::connect(buton_modificare, &QPushButton::clicked, this, &GUIMedicament::modifica_un_medicament);

	//butonu; pentru undo
	QObject::connect(undo, &QPushButton::clicked, this, &GUIMedicament::undo_medicament);

}


void GUIMedicament::adauga_un_nou_medicament()
{	
	try {
		service_medicament.service_adauga_medicament_in_lista(text_Denumire->text().toStdString(), text_Pret->text().toDouble(), text_Producator->text().toStdString(), text_Substanta_activa->text().toStdString());
		for (const Medicament& medicament : service_medicament.get_toate_medicamentele_din_lista())
		{
			if (service_medicament.numarul_medicamentuelor_cu_acelsi_producator(medicament.get_producator())==1)
			{
				auto buton_nou = new QPushButton(text_Producator->text());
				layout_buton_test->addWidget(buton_nou);
				QObject::connect(buton_nou, &QPushButton::clicked, [this, buton_nou, medicament]() {
					QMessageBox::information(nullptr, "Numarul de medicamente: ", QString::number(service_medicament.numarul_medicamentuelor_cu_acelsi_producator(medicament.get_producator())));
				});
			}
		}
		luam_lista_de_medicamente(service_medicament.get_toate_medicamentele_din_lista());
		luam_tabelul_de_medicamente(service_medicament.get_toate_medicamentele_din_lista());
	}
	catch (Exceptie_de_adaugare& exceptie) {
		QMessageBox::warning(this, "Warning!", QString::fromStdString(exceptie.get_mesaje_de_erori()));
	}
	catch (Validare& exceptie) {
		QMessageBox::warning(this, "Warning!", QString::fromStdString(exceptie.get_stringul_de_erori()));
	}
}

void GUIMedicament::cauta_un_medicament()
{
	try {
		Medicament medicament_cautat = service_medicament.service_cauta_un_medicament_in_lista_de_medicamente(text_Denumire_cautare->text().toStdString());
		//luam_lista_de_medicamente(service_medicament.get_toate_medicamentele_din_lista());
		QMessageBox* mesaj = new QMessageBox;
		mesaj->information(this, "Medicamentul cautat: ", QString::fromStdString("Pret: "+std::to_string(medicament_cautat.get_pret()) + ", Producator: " + medicament_cautat.get_producator()+ ", Substanta activa:"+ medicament_cautat.get_substanta_activa()));
	}
	catch (Exceptie_de_cautare& exceptie) {
		QMessageBox::warning(this, "Warning!", QString::fromStdString(exceptie.get_mesaje_de_erori()));
	}
}

void GUIMedicament::sterge_un_medicament()
{
	try {
		service_medicament.service_setrge_medicament_din_lista(text_Denumire_stergere->text().toStdString());
		luam_lista_de_medicamente(service_medicament.get_toate_medicamentele_din_lista());
		luam_tabelul_de_medicamente(service_medicament.get_toate_medicamentele_din_lista());
	}
	catch (Exceptie_de_stergere& exceptie) {
		QMessageBox::warning(this, "Warning!", QString::fromStdString(exceptie.get_mesaje_de_erori()));
	}
	catch (Exceptie_de_cautare& exceptie) {
		QMessageBox::warning(this, "Warning!", QString::fromStdString(exceptie.get_mesaje_de_erori()));
	}
}

void GUIMedicament::modifica_un_medicament()
{
	try {
		service_medicament.service_modifica_medicament_din_lista(text_Denumire_modificare->text().toStdString(), text_Producator_modificat->text().toStdString(), text_Pret_modificat->text().toDouble(), text_Substanta_activa_modificata->text().toStdString());
		luam_lista_de_medicamente(service_medicament.get_toate_medicamentele_din_lista());
		luam_tabelul_de_medicamente(service_medicament.get_toate_medicamentele_din_lista());
	}
	catch (Exceptie_de_modificare& exceptie) {
		QMessageBox::warning(this, "Warning!", QString::fromStdString(exceptie.get_mesaje_de_erori()));
	}
	catch (Exceptie_de_cautare& exceptie) {
		QMessageBox::warning(this, "Warning!", QString::fromStdString(exceptie.get_mesaje_de_erori()));
	}
}

void GUIMedicament::undo_medicament()
{
	try {
		service_medicament.undo();
		luam_lista_de_medicamente(service_medicament.get_toate_medicamentele_din_lista());
		luam_tabelul_de_medicamente(service_medicament.get_toate_medicamentele_din_lista());
	}
	catch (Exceptie_undo& exceptie) {
		QMessageBox::warning(this, "Warning!", QString::fromStdString(exceptie.get_mesaje_de_erori()));
	}
}

void GUIMedicament::luam_tabelul_de_medicamente(const vector<Medicament> lista_de_medicamente)
{
	//tabel_medicamente->clear();
	int numar_linie = 0;
	for (auto& medicamente : lista_de_medicamente)
	{
		int numar_coloana = 0;
		tabel_medicamente->setItem(numar_linie, numar_coloana, new QTableWidgetItem(medicamente.get_denumire().c_str()));
		numar_coloana++;
		tabel_medicamente->setItem(numar_linie, numar_coloana, new QTableWidgetItem(QString::fromStdString(std::to_string( medicamente.get_pret()))));
		numar_coloana++;
		tabel_medicamente->setItem(numar_linie, numar_coloana, new QTableWidgetItem(medicamente.get_producator().c_str()));
		numar_coloana++;
		tabel_medicamente->setItem(numar_linie, numar_coloana, new QTableWidgetItem(medicamente.get_substanta_activa().c_str()));
		numar_coloana++;
		numar_linie++;
	}
}

void GUIMedicament::luam_lista_de_medicamente(vector<Medicament> lista_de_medicamente)
{
	//lista->clear();
	//for (auto& medicamente : lista_de_medicamente)
	//{
		//lista->addItem(QString::fromStdString("Denumire: " + medicamente.get_denumire() + ", Pret: " + std::to_string(medicamente.get_pret()) + ", Producator: " + medicamente.get_producator() + ", Substanta activa: " + medicamente.get_substanta_activa()));
	model_de_lista->set_medicament(lista_de_medicamente);
	//}
}






void GUIMain::initializare_campuri_main()
{
	setLayout(main_page);
	QWidget* widget_centru_main = new QWidget;
	QVBoxLayout* forma_butoanelor = new QVBoxLayout;
	widget_centru_main->setLayout(forma_butoanelor);
	QLabel* farmacie_text = new QLabel("Farmacie");
	forma_butoanelor->addWidget(farmacie_text, Qt::AlignHCenter);
	QFont font_text = farmacie_text->font();
	font_text.setPixelSize(30);
	farmacie_text->setFont(font_text);
	QLabel* text = new QLabel("Va rugam sa selectati una dintre cele doua obtiuni");
	QLabel* text_2 = new QLabel("Prima obitiune va deshide o noua fereastra in care veti putea adauga / sterge / cauta / modifica / filtra / sorta medicamente");
	QLabel* text_3 = new QLabel("Cea dea doua obiune va decshide o noua fereasta in care va veti putea creea porpia reteta de medicamente");
	forma_butoanelor->addWidget(text);
	forma_butoanelor->addWidget(text_2);
	forma_butoanelor->addWidget(text_3);


	QWidget* widget_centru = new QWidget;
	QVBoxLayout* forma_butoanelor_aplicatiei = new QVBoxLayout;
	widget_centru->setLayout(forma_butoanelor_aplicatiei);
	pagina_medicament = new QPushButton("Lista de medicamente");
	forma_butoanelor_aplicatiei->addWidget(pagina_medicament);
	pagina_reteta = new QPushButton("Reteta de medicamente");
	forma_butoanelor_aplicatiei->addWidget(pagina_reteta);

	forma_butoanelor->addWidget(widget_centru);
	main_page->addWidget(widget_centru_main);
}

void GUIMain::conectam_semalele_la_sloturi_main()
{
	QObject::connect(pagina_medicament, &QPushButton::clicked, this, &GUIMain::pagina_de_medciamente);
	QObject::connect(pagina_reteta, &QPushButton::clicked, this, &GUIMain::pagina_de_reteta);
}

void GUIMain::pagina_de_medciamente()
{
	gui_medicament.show();
}

void GUIMain::pagina_de_reteta()
{
	gui_reteta.show();
}

