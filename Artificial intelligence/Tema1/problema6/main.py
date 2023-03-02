from solution import solution
from test import test

if __name__ == '__main__':
    test()
    size = int(input("Introduce lungimea multimii: "))
    collection = []
    for i in range(0, size):
        number = int(input("introduce numere in multime: "))
        collection.append(number)
    print(solution(collection))
