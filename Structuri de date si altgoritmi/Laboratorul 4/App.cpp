#include <iostream>
#include <vector>
#include <exception>
using std::exception;
using std::vector;
vector<int> f(int a)
{
    if (a < 0)
        throw exception("Illegal argument");
    vector<int> rez;
    for (int i = 1; i <= a; i++)
    {
        if (a % i == 0)
        {
            rez.push_back(i);
        }
    }
    return rez;
}

int main()
{
    vector<int> v = f(7);
    for (auto el : v)
        std::cout << el << " ";
    return 0;
}
