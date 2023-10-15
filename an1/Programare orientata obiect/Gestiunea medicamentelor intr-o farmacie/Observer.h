#pragma once

#include <vector>
#include <algorithm>
using std::vector;


class Observer
{
public:
	virtual void update() = 0;
};


class Observable {
private:
	vector<Observer*> observers;
public:
	void adauga_observer(Observer* observer)
	{
		observers.push_back(observer);
	}
	void sterge_observer(Observer* observer)
	{
		observers.erase(std::remove(observers.begin(), observers.end(), observer), observers.end());
	}
	void notify_observer()
	{
		for (auto observer : observers)
		{
			observer->update();
		}
	}
};
