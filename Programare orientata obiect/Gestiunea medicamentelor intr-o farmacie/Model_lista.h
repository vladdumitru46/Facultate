#pragma once

#include "Medicament.h"
#include <QWidget>
#include <QAbstractListModel>
#include <vector>
using std::vector;

class Model_lista : public QAbstractListModel
{
private:
	vector<Medicament> vector_de_medciamente;
public:
	Model_lista(const vector<Medicament> vector_de_medicamente) : vector_de_medciamente{ vector_de_medicamente } {};
	int rowCount(const QModelIndex& parinte = QModelIndex()) const override
	{
		parinte.row();
		return int(vector_de_medciamente.size());
	}
	QVariant data(const QModelIndex& index, int rol = Qt::DisplayRole) const override
	{
		if (rol == Qt::DisplayRole)
		{
			qDebug() << "get row:" << index.row();
			auto denumire = vector_de_medciamente[index.row()].get_denumire();
			auto pret = vector_de_medciamente[index.row()].get_pret();
			auto producator = vector_de_medciamente[index.row()].get_producator();
			auto substanta_activa = vector_de_medciamente[index.row()].get_substanta_activa();
			return "Denumire: " + QString::fromStdString(denumire) + ", Pret: " + QString::fromStdString(std::to_string(pret)) + ", Producator" + QString::fromStdString(producator) + ", Substanta activa: " + QString::fromStdString(substanta_activa);
		}
		if (rol == Qt::UserRole)
		{
			auto producator = vector_de_medciamente[index.row()].get_producator();
			return QString::fromStdString(producator);
		}
		if (rol == Qt::BackgroundRole)
		{
			if (vector_de_medciamente[index.row()].get_denumire()[0] == 'C')
			{
				return QColor(Qt::blue);
			}
		}
		return QVariant{};
	}
	void set_medicament(const vector<Medicament>& vector_de_medicamente)
	{
		this->vector_de_medciamente = vector_de_medicamente;
		auto stanga = createIndex(0, 4);
		auto dreapta = createIndex(rowCount(), 4);
		emit dataChanged(stanga, dreapta);
		emit layoutChanged();
	}

};
