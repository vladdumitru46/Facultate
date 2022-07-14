#pragma once
#include <QtWidgets/QMainWindow>
#include <qlistwidget.h>
#include <qwidget.h>
#include <qlineedit.h>
#include <qpushbutton.h>
#include "Service.h"
#include "RetetaGUI.h"
#include <QHBoxLayout>
#include <QTableWidget>
#include "Model_lista.h"

class GUIMedicament : public QWidget{

private:
    ServiceMedicament& service_medicament;
    Model_lista* model_de_lista;
    QHBoxLayout* main_layout = new QHBoxLayout;
    QListView* lista;
    //QListWidget* lista;
    QTableWidget* tabel_medicamente;
    QPushButton* buton_sorteaza_dupa_denumire;
    QPushButton* buton_sorteaza_dupa_producator;
    QPushButton* buton_sorteaza_dupa_substanta_activa_si_pret;
    QPushButton* filtreaza_dupa_pret;
    QPushButton* filtreaza_dupa_substanta_activa;
    QPushButton* undo;
    QPushButton* afiseaza_lista_intreaga;
    QLineEdit* text_Denumire;
    QLineEdit* text_Denumire_cautare;
    QLineEdit* text_Denumire_stergere;
    QLineEdit* text_Denumire_modificare;
    QLineEdit* text_Pret;
    QLineEdit* text_Producator;
    QLineEdit* text_Substanta_activa;
    QLineEdit* text_Pret_modificat;
    QLineEdit* text_Producator_modificat;
    QLineEdit* text_Substanta_activa_modificata;
    QLineEdit* filtrare_dupa_pret;
    QLineEdit* filtrare_dupa_substanta_activa;
    QPushButton* buton_adaugare;
    QPushButton* buton_cautare;
    QPushButton* buton_stergere;
    QPushButton* buton_modificare;
    QVBoxLayout* layout_buton = new QVBoxLayout;
    QWidget* buton_diametru = new QWidget;
    QWidget* buton_test=new QWidget;
    QVBoxLayout* layout_buton_test = new QVBoxLayout;

    void initializare_campuri_GUI();
    void conectam_semnalele_la_sloturi();
    void luam_lista_de_medicamente(const vector<Medicament> lista_de_medicamente);
    void adauga_un_nou_medicament();
    void cauta_un_medicament();
    void sterge_un_medicament();
    void modifica_un_medicament();
    void undo_medicament();
    void luam_tabelul_de_medicamente(const vector<Medicament> lista_de_medicamente);
public:
    GUIMedicament(ServiceMedicament& service_medicament) : service_medicament{ service_medicament } {
        initializare_campuri_GUI();
        model_de_lista = new Model_lista(service_medicament.get_toate_medicamentele_din_lista());
        lista->setModel(model_de_lista);
        conectam_semnalele_la_sloturi();
       // luam_lista_de_medicamente(service_medicament.get_toate_medicamentele_din_lista());
        luam_tabelul_de_medicamente(service_medicament.get_toate_medicamentele_din_lista());
        //adauga_butoane(service_medicament.get_toate_medicamentele_din_lista());
    };

};



class GUIMain: public QWidget {
private:
    GUIMedicament& gui_medicament;
    GUIReteta& gui_reteta;
    QHBoxLayout* main_page = new QHBoxLayout;
    QListWidget* lista_main;
    QPushButton* pagina_medicament;
    QPushButton* pagina_reteta;
    void initializare_campuri_main();
    void conectam_semalele_la_sloturi_main();
    void pagina_de_medciamente();
    void pagina_de_reteta();


public:
    GUIMain(GUIMedicament& gui_medicament, GUIReteta& gui_reteta) : gui_medicament{ gui_medicament }, gui_reteta{ gui_reteta } {
        initializare_campuri_main();
        conectam_semalele_la_sloturi_main();
    };

};