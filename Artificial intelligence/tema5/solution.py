from cmath import sqrt


def read_data_from_file(file_path):
    import csv
    rows = []
    with open(file_path, 'r') as file:
        csv_reader = csv.reader(file)
        for row in csv_reader:
            rows.append(row)
    return rows


def calculate_regression(rows):
    vectorL1 = []
    vectorL2 = []
    for i in rows:
        vectorL1.append((abs(int(i[0]) - int(i[3]))) + (abs(int(i[1]) - int(i[4]))) + (abs(int(i[2]) - int(i[5]))))
        vectorL2.append(pow((abs(int(i[0]) - int(i[3]))), 2) + pow((abs(int(i[1]) - int(i[4]))), 2) + pow(
            (abs(int(i[2]) - int(i[5]))), 2))
    print("L1: ", (1 / len(rows)) * sum(vectorL1))
    print("L2: ", sqrt((1 / len(rows)) * sum(vectorL2)))


def load_data_for_regression(file_path):
    rows = read_data_from_file(file_path)
    calculate_regression(rows)


def calculate_classification(rows, pos1, pos2, pos3):
    acc = 0
    for i in rows:
        if i[0] == i[1]:
            acc += 1
    acc = acc / len(rows)
    tpD = 0
    tnD = 0
    fpD = 0
    fnD = 0
    tpT = 0
    tnT = 0
    fpT = 0
    fnT = 0
    tpR = 0
    tnR = 0
    fpR = 0
    fnR = 0
    for i in rows:
        if i[0] == pos1 and i[1] == pos1:
            tpD += 1
        if i[0] != pos1 and i[1] == pos1:
            fpD += 1
        if i[0] != pos1 and i[1] != pos1:
            tnD += 1
        if i[0] == pos1 and i[1] != pos1:
            fnD += 1

        if i[0] == pos2 and i[1] == pos2:
            tpT += 1
        if i[0] != pos2 and i[1] == pos2:
            fpT += 1
        if i[0] != pos2 and i[1] != pos2:
            tnT += 1
        if i[0] == pos2 and i[1] != pos2:
            fnT += 1

        if i[0] == pos3 and i[1] == pos3:
            tpR += 1
        if i[0] != pos3 and i[1] == pos3:
            fpR += 1
        if i[0] != pos3 and i[1] != pos3:
            tnR += 1
        if i[0] == pos3 and i[1] != pos3:
            fnR += 1

    tp = tpD + tpT + tpR
    tn = tnD + tnT + tnR
    fp = fpD + fpT + fpR
    fn = fnD + fnT + fnR

    print("acc: ", acc)
    precisionPosD = tpD / (tpD + fpD)
    precisionNegD = tnD / (tnD + fnD)
    recallPosD = tpD / (tpD + fnD)
    recallNegD = tnD / (tnD + fpD)
    print("precisionD: [", precisionPosD, ", ", precisionNegD, "]")
    print("recallD: [", recallPosD, ", ", recallNegD, "]")
    precisionPosT = tpT / (tpT + fpT)
    precisionNegT = tnT / (tnT + fnT)
    recallPosT = tpT / (tpT + fnT)
    recallNegT = tnT / (tnT + fpT)
    print("precisionT: [", precisionPosT, ", ", precisionNegT, "]")
    print("recallT: [", recallPosT, ", ", recallNegT, "]")
    precisionPosR = tpR / (tpR + fpR)
    precisionNegR = tnR / (tnR + fnR)
    recallPosR = tpR / (tpR + fnR)
    recallNegR = tnR / (tnR + fpR)
    print("precisionR: [", precisionPosR, ", ", precisionNegR, "]")
    print("recallR: [", recallPosR, ", ", recallNegR, "]")


def load_data_for_classification(file_path):
    rows = read_data_from_file(file_path)
    calculate_classification(rows, 'Daisy', 'Tulip', 'Rose')
