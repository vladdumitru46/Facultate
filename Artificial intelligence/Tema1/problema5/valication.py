def validation(collection_with_initial_numbers, size_of_collection):
    error_string = ""
    if size_of_collection <= 0:
        error_string += "Size of collection has to be bigger than 0\n"
    if len(collection_with_initial_numbers) != size_of_collection:
        error_string += "Collection has to be the size of given size!\n"
    min = size_of_collection + 1
    max = 0
    for numbers_from_collection in collection_with_initial_numbers:
        if numbers_from_collection > max:
            max = numbers_from_collection
        if numbers_from_collection < min:
            min = numbers_from_collection
    if min < 1 or max > size_of_collection - 1:
        error_string += "Numbers from collection have to be between [1, size_of_collection - 1]\n"
    dictionary_with_all_values_from_collection = {}
    for numbers_from_initial_collection in collection_with_initial_numbers:
        dictionary_with_all_values_from_collection[numbers_from_initial_collection] = 0
    for numbers_from_initial_collection in collection_with_initial_numbers:
        dictionary_with_all_values_from_collection[numbers_from_initial_collection] += 1
    lista = []
    for numbers_from_dictionary in dictionary_with_all_values_from_collection:
        if dictionary_with_all_values_from_collection[numbers_from_dictionary] > 1:
            lista.append(numbers_from_dictionary)
    if len(lista)!=1:
        error_string += "In collection has to be only one number that appers twice"

    if len(error_string) > 0:
        raise ValueError(error_string)
