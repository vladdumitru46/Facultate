#pragma once
#include "Service.h"
#include "Repository.h"

#include <QtWidgets/QMainWindow>
#include <qlistwidget.h>
#include <qwidget.h>
#include <qlineedit.h>
#include <qpushbutton.h>
#include <QHBoxLayout>
#include <QTableWidget>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QFormLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QMessageBox>
#include <Qt>
#include <QTextBlock>
#include <string>
#include <QPainter>

class Numarul_de_elemente_din_reteta : public QWidget, public Observer{

private:
    QLineEdit* numarul_de_elemente_din_reteta;
    RepoReteta& reteta_medicamente;
public:
    Numarul_de_elemente_din_reteta(RepoReteta& reteta_medicamente): reteta_medicamente{ reteta_medicamente } {
        numarul_de_elemente_din_reteta = new QLineEdit;
        QHBoxLayout* layout_numarul_de_elemente_din_reteta = new QHBoxLayout;
        setLayout(layout_numarul_de_elemente_din_reteta);
        layout_numarul_de_elemente_din_reteta->addWidget(numarul_de_elemente_din_reteta);
        reteta_medicamente.adauga_observer(this);
        update();
    };
    void update() override {
        numarul_de_elemente_din_reteta->setText(std::to_string(reteta_medicamente.get_toate_medicamentele_din_lista().size()).c_str());
    }

    ~Numarul_de_elemente_din_reteta() {
        reteta_medicamente.sterge_observer(this);
    }

};

class Deseneaza_numarul_de_medicamente : public QWidget, public Observer {
private:
    RepoReteta& repo_reteta;
protected:
    void paintEvent(QPaintEvent* ev) override {
        ev->accept();
        int x = 10, y = 10, z = 10;
        QPainter desen{ this };
        desen.setPen(Qt::blue);
        for (const auto& medciament : repo_reteta.get_toate_medicamentele_din_lista())
        {
            desen.drawRect(x, y, z, medciament.get_pret() * 20);
            x += 40;
        }
    }
public:
    Deseneaza_numarul_de_medicamente(RepoReteta& repo_reteta) : repo_reteta{ repo_reteta } {
        update();
    }
    void update() override
    {
        this->repaint();
    }
};

class GUIReteta : public QWidget {
private:
    ServiceMedicament& service_medicament_reteta;
    Numarul_de_elemente_din_reteta& numarul_de_elemente;
    Deseneaza_numarul_de_medicamente& desen_numar_elemente;
    QHBoxLayout* main_layout = new QHBoxLayout;
    QListWidget* lista_reteta;
    QPushButton* buton_adauga_in_reteta;
    QPushButton* buton_goleste_reteta;
    QPushButton* buton_adauga_random_in_reteta;
    QLineEdit* denumire_medicament_pentru_reteta;
    QLineEdit* numarul_de_elemente_pe_care_le_adaug_random_in_reteta;
    QPushButton* buton_numar_elemente_din_playlist;
    QPushButton* buton_desen_medicamente;
    QPushButton* buton_close;

    void initializare_campuri_reteta();
    void conectare_butoane_reteta();
    void luam_lista_de_mediamente_din_reteta(vector<Medicament> lista_de_reteta);
    void adauga_mediamente_in_reteta();
    void goleste_reteta();
    void adauga_medicamente_random_in_reteta();
public:
    GUIReteta(ServiceMedicament& service_medicament_reteta, Numarul_de_elemente_din_reteta& numarul_de_elemene, Deseneaza_numarul_de_medicamente& desen_numar_medicaemnte) : service_medicament_reteta{ service_medicament_reteta }, numarul_de_elemente{ numarul_de_elemene }, desen_numar_elemente{ desen_numar_medicaemnte } {
        initializare_campuri_reteta();
        conectare_butoane_reteta();
        luam_lista_de_mediamente_din_reteta(service_medicament_reteta.get_toate_medicamentele_din_reteta());
    };
};