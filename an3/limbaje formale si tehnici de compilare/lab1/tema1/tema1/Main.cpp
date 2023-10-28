#include<iostream>
#include <string>
#include <cmath>
using std::cin;
using std::cout;

void main() {
	//suma a n numere
	int n;
	cout << "n: ";
	cin >> n;
	double suma = 0.0;
	for (int i = 0; i < n; i = i+1) {
		double numar;
		cout<< "Introduceti numarul " << i + 1 << ": ";
		cin >> numar;
		suma += numar;
	}
	cout << "suma : " << suma << "  a and b:";

	//cmmdc a 2 numere naturale
	int a, b;
	cin >> a;
	cin >> b;
	while (b != 0) {
		int temp = b;
		b = a % b;
		a = temp;
	}
	cout << "cmmdc: "<< a << "  radious: ";

	//perimetrul si aria cercului de o raza data
	int radious;
	cin >> radious;
	double pi = 3.14;
	double p = 2 * pi * radious;
	double ar = pi * radious * radious;

	cout << "perimeter: "<< p<< " array: "<< ar;

	
	//eroare 1:
	 int x = 10
	 cout << x;
	//eroare 2:
	 int s=10;
	cout s;
	

	//eroare care nu e de liombaj c++ dar e de limbajul meu:
	int x = 2;
	x++;
	
	//eroare 2:
	int ca =2;
	if(ca>3){
		cout<<"    da";
	}
	else if(ca<3 && ca>1){
		cout<<"   dada";
	}
	else{
		cout<<"   nu";
	}
}