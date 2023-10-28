#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <sstream>
using std::string;
using std::ifstream;
using std::cout;
using std::endl;
using std::vector;
using std::stringstream;

int main() {
    std::ifstream fisier("code.txt");

    if (!fisier.is_open()) {
        cout << "Eroare la deschiderea fișierului." << endl;
        return 1; // Încheie programul cu cod de eroare
    }

    string delimitatori = " ;+-/*<>";
    vector<string> cuvinte;

    string linie;
    while (std::getline(fisier, linie)) {
        stringstream flux(linie);
        string cuvant;

        while (getline(flux, cuvant, ' ')) {
            // Verifică dacă cuvantul nu este gol înainte de adăugare
            if (!cuvant.empty()) {
                cuvinte.push_back(cuvant);
            }
        }

        for (char delim : delimitatori) {
            vector<string> cuvinteNoi;
            cuvinte.swap(cuvinteNoi);
            for (const string& cuvantVechi : cuvinteNoi) {
                stringstream fluxNou(cuvantVechi);
                while (getline(fluxNou, cuvant, delim)) {
                    string textNou = cuvant;
                    char punctSiVirgula = ' ';
                    string paranteze = "";
                    int ok = 0;
                    for (int i = 0; i < textNou.size(); i++) {
                        paranteze = "";
                        if (textNou[i] == ' ' || textNou[i] == '\t') {
                            textNou.erase(i, 1);
                            i--;
                        }
                        else if (textNou[i] == ';' || textNou[i] == ')') {
                            punctSiVirgula = textNou[i];
                            textNou.erase(i, 1);
                            i--;
                        }
                        else {
                            if (textNou[i] == '(') {
                                if (textNou[i + 1] == ')') {
                                    paranteze.push_back(textNou[i]);
                                    paranteze.push_back(textNou[i + 1]);
                                    textNou.erase(i, 2);
                                }
                                else {
                                    punctSiVirgula = textNou[i];
                                    textNou.erase(i, 1);
                                    i--;
                                }

                                cout << punctSiVirgula<<endl;
                                ok = 1;
                            }
                        }
                    }
                    if (!textNou.empty()) {
                        if (!paranteze.empty()) {
                            cout << textNou << endl;
                        }
                        else 
                        if (punctSiVirgula == ' ') {
                            cout << textNou;
                        }

                        else {
                            cout << textNou << endl;
                        }
                    }
                    if (!paranteze.empty()) {
                        cout << paranteze << endl;
                    }
                    if (ok == 0) {
                        cout << punctSiVirgula << endl;
                    }
                    
                }
                // cout << delim << endl;
            }
        }
    }
    fisier.close();
    return 0; // Programul a fost executat cu succes
}
    
