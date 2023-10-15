from solution import solution
from test import test

if __name__ == '__main__':
    test()
    l = []
    l2 = []
    for i in range(0, 2):
        a = int(input("introduceti un punct "))
        l.append(a)
    for i in range(0, 2):
        b = int(input("introduceti un punct "))
        l2.append(b)
    print(solution(l, l2))
