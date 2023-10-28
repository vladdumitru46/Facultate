#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <sstream>
#include <thread>
using namespace std;


int k = 0, n = 0, m = 0;
int c[5][5];
int f[10000][10000];
int v[10000][10000];

int** cDinamic;
int** fDinamic;
int** vDinamic;



std::vector<std::string> split(const std::string& str, char delimiter) {
    std::vector<std::string> tokens;
    std::stringstream ss(str);
    std::string token;

    while (std::getline(ss, token, delimiter)) {
        tokens.push_back(token);
    }

    return tokens;
}


void sumDinamic() {
    for (int i = 1; i < n - 1; i++) {
        for (int j = 1; j < m - 1; j++) {
            vDinamic[i][j] = fDinamic[i][j] * cDinamic[1][1] + fDinamic[i - 1][j] * cDinamic[0][1] + fDinamic[i][j - 1] * cDinamic[1][0] + fDinamic[i - 1][j - 1] * cDinamic[0][0] + fDinamic[i + 1][j] * cDinamic[2][1] + fDinamic[i][j + 1] * cDinamic[1][2] + fDinamic[i + 1][j + 1] * cDinamic[2][2];
        }
    }
}


void sumPDinamic(int p) {
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

        threads[i] = std::thread(sumDinamic);
        start = finish;
    }
    for (int i = 0; i < p; i++) {
        threads[i].join();
    }
}

void sumPDinamicColumns(int p) {
    int cat = m / p;
    int rest = m % p;
    int start = 0;
    thread* threads = new thread[p];
    for (int i = 0; i < p; i++) {
        int finish = start + cat;
        if (rest < 0) {
            finish++;
            rest--;
        }

        threads[i] = std::thread(sumDinamic);
        start = finish;
    }
    for (int i = 0; i < p; i++) {
        threads[i].join();
    }
}
void sum() {
    //broadMatrix();
    if (k == 3) {
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (k == 3) {
                    v[i][j] = f[i][j] * c[1][1] + f[i - 1][j] * c[0][1] + f[i][j - 1] * c[1][0] + f[i - 1][j - 1] * c[0][0] + f[i + 1][j] * c[2][1] + f[i][j + 1] * c[1][2] + f[i + 1][j + 1] * c[2][2];
                }
            }
        }
    }
    else if (k == 5) {
        for (int i = 2; i < n - 2; i++) {
            for (int j = 2; j < m - 2; j++) {
                v[i][j] = f[i][j] * c[2][2] + f[i - 2][j] * c[0][2] + f[i - 1][j] * c[1][2] +
                    f[i + 1][j] * c[3][2] + f[i + 2][j] * c[4][2] + f[i][j - 2] * c[2][0] +
                    f[i][j - 1] * c[2][1] + f[i][j + 1] * c[2][3] + f[i][j + 2] * c[2][4] +
                    f[i - 2][j - 2] * c[0][0] + f[i - 2][j - 1] * c[0][1] + f[i - 1][j - 2] * c[1][0] +
                    f[i - 1][j - 1] * c[1][1] + f[i - 1][j + 1] * c[1][3] + f[i - 1][j + 2] * c[1][4] +
                    f[i + 1][j - 2] * c[3][0] + f[i + 1][j - 1] * c[3][1] + f[i + 1][j + 1] * c[3][3] +
                    f[i + 1][j + 2] * c[3][4] + f[i + 2][j - 2] * c[4][0] + f[i + 2][j - 1] * c[4][1] +
                    f[i + 2][j + 1] * c[4][3] + f[i + 2][j + 2] * c[4][4];
            }
        }
    }
}

void broadMatrix() {
    int kernelSize = n;
    int offset = kernelSize / 2;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            int sum = 0;

            for (int u = -offset; u <= offset; u++) {
                for (int v = -offset; v <= offset; v++) {
                    int ni = i + u;
                    int nj = j + v;

                    if (ni < 0) {
                        ni = 0;
                    }
                    if (ni >= n) {
                        ni = n - 1;
                    }
                    if (nj < 0) {
                        nj = 0;
                    }
                    if (nj >= m) {
                        nj = m - 1;
                    }

                    sum += f[ni][nj] * c[u + offset][v + offset];
                }
            }

            v[i][j] = sum;
        }
    }
}


void sumP(int p) {
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

        threads[i] = std::thread(sum);
        start = finish;
    }
    for (int i = 0; i < p; i++) {
        threads[i].join();
    }
}

void sumPColumns(int p) {
    int cat = m / p;
    int rest = m % p;
    int start = 0;
    thread* threads = new thread[p];
    for (int i = 0; i < p; i++) {
        int finish = start + cat;
        if (rest < 0) {
            finish++;
            rest--;
        }

        threads[i] = std::thread(sum);
        start = finish;
    }
    for (int i = 0; i < p; i++) {
        threads[i].join();
    }
}


int main(int argc, char* argv[]) {
    string fileName = "C:\\Users\\vladb\\Desktop\\Facultate\\an3\\programare paralela si distributiva\\c++\\tema1\\date1.txt";
    std::ifstream file(fileName);
    std::string line;


    long p = 3;
   p = stoi(argv[1]);


    while (std::getline(file, line)) {

        if (line.empty()) {
            continue;
        }
        if (line.find("k:5") == 0) {
            vector<string> list = split(line, ':');
            k = std::stoi(list[1]);
            for (int i = 0; i < k; i++) {
                std::getline(file, line);
                list = split(line, ' ');
                for (int j = 0; j < k; j++) {
                    c[i][j] = std::stoi(list[j]);
                }
            }
        }
        else if (line == "n: 1000 m: 10") {
            std::vector<std::string> list = split(line, ' ');
            n = std::stoi(list[1]);
            m = std::stoi(list[3]);

            for (int i = 0; i < n; i++) {
                std::getline(file, line);
                list = split(line, ' ');
                for (int j = 0; j < m; j++) {
                    f[i][j] = std::stoi(list[j]);
                }
            }
        }

    }
    fDinamic = new int* [n];
    vDinamic = new int* [n];
    cDinamic = new int* [k];
    for (int i = 0; i < n; i++) {
        fDinamic[i] = new int[m];
        vDinamic[i] = new int[m];
    }

    for (int i = 0; i < k; i++) {
        cDinamic[i] = new int[k];
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            fDinamic[i][j] = f[i][j];
            vDinamic[i][j] = v[i][j];
        }
    }
    for (int i = 0; i < k; i++) {
        for (int j = 0; j < k; j++) {
            cDinamic[i][j] = c[i][j];
        }
    }
    auto t_start = chrono::steady_clock::now();
    auto t_final = chrono::steady_clock::now();
    auto diff = t_final - t_start;

    
    
    t_start = chrono::steady_clock::now();
    sumPDinamicColumns(p);
    t_final = chrono::steady_clock::now();
    diff = t_final - t_start;
    cout << chrono::duration <double, milli>(diff).count()  << endl;

    t_start = chrono::steady_clock::now();
    sumPDinamic(p);
    t_final = chrono::steady_clock::now();
    diff = t_final - t_start;
    cout << chrono::duration <double, milli>(diff).count() << endl;
    t_start = chrono::steady_clock::now();
    sumP(p);
    t_final = chrono::steady_clock::now();
    diff = t_final - t_start;
    cout << chrono::duration <double, milli>(diff).count() << endl;
    t_start = chrono::steady_clock::now();
    sumPColumns(p);
    t_final = chrono::steady_clock::now();
    diff = t_final - t_start;
    cout << chrono::duration <double, milli>(diff).count()  << endl;
}