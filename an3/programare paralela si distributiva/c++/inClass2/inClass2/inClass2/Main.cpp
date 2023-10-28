#include "Test0.h"
#include "Test0_mutex.h"
#include <iostream>
#include <thread>
#include<functional>
#include <vector>
using namespace std;

const int n = 20;
vector<double> x(n);
vector<double> y(n);
vector<double> z(n);
double* a = new double[n];
double* b = new double[n];
double* c = new double[n];

void sumS(double a[], double b[], double c[], int start, int finish, function<double(double, double)> f) {
	int i;
	for (i = start; i < finish; i++) {
		c[i] = f(a[i], b[i]);
	}
}


void sumP(double a[], double b[], double c[], const int p, function<double(double, double)> f) {
	int cat = n / p;
	int rest = n % p;
	int start = 0;
	thread* threads = new thread[p];
	for (int i = 0; i < p; i++) {
		int finish = start + cat;
		if (rest < 0) {
			finish++;
			rest--;
		}

		threads[i] = std::thread(sumS, a, b, c, start, finish, f);
		start = finish;
	}
	for (int i = 0; i < p; i++) {
		threads[i].join();
	}
}
void sumSVector(vector<double> a, vector<double> b, vector<double> c, int start, int finish, function<double(double, double)> f) {
	int i;
	for (i = start; i < finish; i++) {
		c[i] = f(a[i], b[i]);
	}
}
void sumPVector(vector<double> a, vector<double> b, vector<double> c, const int p, function<double(double, double)> f) {
	int cat = n / p;
	int rest = n % p;
	int start = 0;
	thread* threads = new thread[p];
	for (int i = 0; i < p; i++) {
		int finish = start + cat;
		if (rest < 0) {
			finish++;
			rest--;
		}

		threads[i] = thread(sumSVector, a, b, c, start, finish, f);
		start = finish;
	}
	for (int i = 0; i < p; i++) {
		threads[i].join();
	}
}
int main() {
	//if(test0()==0) std::cout<<"Test 0 passed";
	for (int i = 0; i < n; i++) {
		a[i] = i;
		b[i] = -i;
		x[i] = i;
		y[i] = -i;
	}

	auto t_start = chrono::steady_clock::now();
	sumS(a, b, c, 0, n, [=](double x, double y) {return pow(x, 3) + pow(y, 3); });
	auto t_final = chrono::steady_clock::now();
	auto diff = t_final - t_start;
	cout << "secv " << chrono::duration <double, milli>(diff).count() << " ms" << endl;


	t_start = chrono::steady_clock::now();
	sumP(a, b, c, 4, [=](double x, double y) {return pow(x, 3) + pow(y, 3); });
	t_final = chrono::steady_clock::now();
	diff = t_final - t_start;
	cout << "paralel " << chrono::duration <double, milli>(diff).count() << " ms" << endl;


	t_start = chrono::steady_clock::now();
	sumPVector(x, y, z, 4, [=](double x, double y) {return pow(x, 3) + pow(y, 3); });
	t_final = chrono::steady_clock::now();
	diff = t_final - t_start;
	cout << "paralel v " << chrono::duration <double, milli>(diff).count() << " ms" << endl;


	return 0;
}