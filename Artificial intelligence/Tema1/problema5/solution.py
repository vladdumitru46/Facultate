"""
functia cauta intr-o multime data numarul care se repeta de 2 ori
:param collection_in_witch_is_the_initial_numbers - list, multimea in care caut numarul care se repeta de 2 ori
:returns numbers_from_dictionary - int, numarul care se repeta de 2 ori in multime
"""

def solution(collection_in_witch_is_the_initial_numbers):
    dictionary_with_all_values_from_collection = {}
    for numbers_from_initial_collection in collection_in_witch_is_the_initial_numbers:
        dictionary_with_all_values_from_collection[numbers_from_initial_collection] = 0
    for numbers_from_initial_collection in collection_in_witch_is_the_initial_numbers:
        dictionary_with_all_values_from_collection[numbers_from_initial_collection] += 1
    for numbers_from_dictionary in dictionary_with_all_values_from_collection:
        if dictionary_with_all_values_from_collection[numbers_from_dictionary] > 1:
            return numbers_from_dictionary


