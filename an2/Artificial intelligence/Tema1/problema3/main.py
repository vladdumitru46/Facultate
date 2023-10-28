from solution import solution
from test import test

if __name__ == '__main__':
    test()
    lungime = int(input("introduce lungimea vectorilor"))
    l1 = []
    l2 = []
    for i in range(0, lungime):
        a = input("introduce element: ")
        l1.append(a)
    for i in range(0, lungime):
        a = input("introduce element: ")
        l2.append(a)
    print(solution(l1, l2))
