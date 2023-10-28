from solution import load_data_for_regression, load_data_for_classification

if __name__ == '__main__':
    print("problema 1:")
    load_data_for_regression('sport.csv')
    print("\nproblema2:")
    load_data_for_classification('flowers.csv')
