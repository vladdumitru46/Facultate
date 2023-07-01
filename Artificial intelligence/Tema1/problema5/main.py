import valication
from solution import solution
from test import test

if __name__ == '__main__':
    test()
    size_of_collection = int(input("introduceti lungimea multimii: "))
    collection = []
    for i in range(0, size_of_collection):
        number_from_collection = int(input("intrdouceti numere in colectie: "))
        collection.append(number_from_collection)
    try:
        valication.validation(collection, size_of_collection)
        print(solution(collection))
    except ValueError as ve:
        print(ve)


