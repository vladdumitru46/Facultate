#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <map>
#include <regex>
#include <set>
#include <sstream>
#include "lexicograficTable.h";
using std::cout;
using namespace std;


int cautaCuvantInSet(const string& cuvant, const set<pair<string, int>>& perechiCuvantCod) {
    for (const auto& pereche : perechiCuvantCod) {
        if (pereche.first == cuvant) {
            return pereche.second;
        }
    }
    return -1;
}

int main() {
    std::ifstream fisier("Text.txt");

    if (!fisier.is_open()) {
        cout << "Eroare la deschiderea fisierului." << endl;
        return 1;
    }

    string delimitatori = " ;+-/*<>";
    vector<string> cuv;
    vector<string> cuvinte;

    string linie;

    set<pair<string, int>> perechiCuvantCod;
    string cuvant;


    map<string, int> coduriAtom;

    coduriAtom["#include"] = 2;
    coduriAtom["void"] = 3;
    coduriAtom["iostream"] = 4;
    coduriAtom["main"] = 5;
    coduriAtom["using"] = 6;
    coduriAtom["namespace"] = 7;
    coduriAtom["std"] = 8;
    coduriAtom["("] = 9;
    coduriAtom[")"] = 10;
    coduriAtom["{"] = 11;
    coduriAtom["}"] = 12;
    coduriAtom["["] = 13;
    coduriAtom["]"] = 14;
    coduriAtom["int"] = 15;
    coduriAtom["for"] = 16;
    coduriAtom[";"] = 17;
    coduriAtom[","] = 18;
    coduriAtom["cin"] = 19;
    coduriAtom[">>"] = 20;
    coduriAtom["cout"] = 21;
    coduriAtom["<<"] = 22;
    coduriAtom["="] = 23;
    coduriAtom["+="] = 24;
    coduriAtom["<"] = 25;
    coduriAtom[">"] = 26;
    coduriAtom["<="] = 27;
    coduriAtom[">="] = 28;
    coduriAtom["-"] = 29;
    coduriAtom["+"] = 30;
    coduriAtom["*"] = 31;
    coduriAtom["()"] = 32;
    coduriAtom["/"] = 33;
    coduriAtom["||"] = 34;
    coduriAtom["&&"] = 35;
    coduriAtom["if"] = 36;
    coduriAtom["else"] = 37;
    coduriAtom["while"] = 38;
    coduriAtom[">>"] = 39;

    while (getline(fisier, linie)) {
        stringstream flux(linie);
        string cuvant;

        while (getline(flux, cuvant, ' ')) {
            if (!cuvant.empty()) {
                cuv.push_back(cuvant);
            }
        }

        for (char delim : delimitatori) {
            vector<string> cuvinteNoi;
            cuv.swap(cuvinteNoi);
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

                                //cout << punctSiVirgula << endl;
                                string myString(1, punctSiVirgula);
                                cuvinte.push_back(myString);
                                ok = 1;
                            }
                        }
                    }
                    if (!textNou.empty()) {
                        if (!paranteze.empty()) {
                            //cout << textNou << endl;
                            cuvinte.push_back(textNou);
                        }
                        else
                            if (punctSiVirgula == ' ') {
                                //cout << textNou;
                                cuvinte.push_back(textNou);
                            }

                            else {
                                //cout << textNou << endl;
                                cuvinte.push_back(textNou);
                            }
                    }
                    if (!paranteze.empty()) {
                       // cout << paranteze << endl;
                        cuvinte.push_back(paranteze);
                    }
                    if (ok == 0) {
                        //cout << punctSiVirgula << endl;
                        string myString(1, punctSiVirgula);
                        cuvinte.push_back(myString);
                    }

                }
            }
        }
    }
    fisier.close();

    vector<string> cuvintee;
    for (auto cuvi : cuvinte) {
        if (cuvi!=" ") {
            cuvintee.push_back(cuvi);
        }
    }


    regex regexLitere("[a-zA-Z]+");
    regex regexNumar(R"((\d+)(\.\d+)?)");
    for (auto cuvi : cuvintee) {
        if (coduriAtom.find(cuvi) != coduriAtom.end()) {
            perechiCuvantCod.insert(make_pair(cuvi, coduriAtom[cuvi]));
        }
        else {
            if (cuvi != "for" && regex_match(cuvi, regexLitere) && cuvi.size() <= 8) {
                perechiCuvantCod.insert(make_pair(cuvi, 0));
            }
            else {
                if (regex_match(cuvi, regexNumar)) {
                    perechiCuvantCod.insert(make_pair(cuvi, 1));
                }
                else {
                    cerr << "Eroare: Atomul " + cuvi + " nu exista in limbaj." << endl;
                }
            }
        }
    }

    LexicographicTable lexiConst;
    LexicographicTable lexiIdent;

    int i = 0;
    int j = 0;
    for (auto per : perechiCuvantCod) {
        if (per.second == 0) {
            lexiIdent.insert(per.first, i);
            i++;
        }
        else if (per.second == 1) {
            lexiConst.insert(per.first, j++);
        }
    }
    
    cout << "Tabel de simboluri pentru constante" << '\n';
    lexiConst.display();
    cout << "Tabel de simboluri pentru identificatori" << '\n';
    lexiIdent.display();


    cout << "Forma interna a programului sursa" << '\n';
    for (const string& cuvant : cuvintee) {
        int cod = cautaCuvantInSet(cuvant, perechiCuvantCod);
        if (cod == -1) {
            cout << "Atomul: " << cuvant << " nu a fost gasit." << endl;
        }
        else {
            cout << cuvant << " are Cod atom: " << cod;

            if (cod == 0 ){
                int codTS = lexiIdent.search(cuvant);
                cout << " si Cod TS: " << codTS << '\n';
            }
            else if(cod == 1){
                int codTS = lexiConst.search(cuvant);
                cout << " si Cod TS: " << codTS << '\n';
            }
            else {
                cout << " si Cod TS: " << "-" << '\n';
            }
        }
    }

    return 0;
}

