#pragma once
#include "Medicament.h"
#include <QAbstractTableModel>
#include <vector>
#include <qdebug.h>
#include<vector>
#include <qbrush.h>
using std::vector;

class Model_tabel:public QAbstractTableModel {
private: 
	vector<Medicament> vector_medicament;
public:
	Model_tabel(const vector<Medicament> vector_medicament) : vector_medicament{ vector_medicament } {};
	int rowCount(const QModelIndex& parent = QModelIndex()) const override {
		parent.row();
		return int(vector_medicament.size());
	}
	int columnCount(const QModelIndex& parent = QModelIndex()) const override {
		parent.row();
		return 4;
	}
	QVariant data(const QModelIndex& index, int role = Qt::DisplayRole) const override {
		qDebug() << "row:" << index.row() << " col:" << index.column() << " role" << role;
		if (role == Qt::ForegroundRole) {
			Medicament medicament = vector_medicament[index.row()];
			if (medicament.get_pret() > 0) {
				return QBrush{ Qt::red };
			}
		}
		if (role == Qt::DisplayRole) {

			Medicament medicament = vector_medicament[index.row()];
			if (index.column() == 0) {
				return QString::fromStdString(medicament.get_denumire());
			}
			else if (index.column() == 1) {
				return QString::fromStdString(medicament.get_producator());
			}
			else if (index.column() == 2) {
				return QString::number(medicament.get_pret());
			}
		}

		return QVariant{};
	}

	void setData(const vector<Medicament>& medicament) {
		this->vector_medicament = medicament;
		auto topLeft = createIndex(0, 0);
		auto bottomR = createIndex(rowCount(), columnCount());
		emit dataChanged(topLeft, bottomR);
		emit layoutChanged();
	}
};

