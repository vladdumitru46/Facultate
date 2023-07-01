#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_Lab10_11oop.h"

class Lab10_11oop : public QMainWindow
{
    Q_OBJECT

public:
    Lab10_11oop(QWidget *parent = Q_NULLPTR);

private:
    Ui::Lab10_11oopClass ui;
};
