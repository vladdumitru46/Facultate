import csv
import os
import random

from matplotlib import pyplot as plt

from liniarRegression import MyLinearUnivariateRegression


def loadData(fileName, inputVariabName1, inputVariabName2, outputVariabName):
    data = []
    dataNames = []
    with open(fileName) as csv_file:
        csv_reader = csv.reader(csv_file, delimiter=',')
        line_count = 0
        for row in csv_reader:
            if line_count == 0:
                dataNames = row
            else:
                data.append(row)
            line_count += 1
    selectedVariable = dataNames.index(inputVariabName1)
    inputs = [float(data[i][selectedVariable]) for i in range(len(data))]
    selectedVariable2 = dataNames.index(inputVariabName2)
    inputs2 = [float(data[i][selectedVariable2]) for i in range(len(data))]
    selectedOutput = dataNames.index(outputVariabName)
    outputs = [float(data[i][selectedOutput]) for i in range(len(data))]

    return inputs, inputs2, outputs


def plotDataHistogram(x, variableName):
    n, bins, patches = plt.hist(x, 10)
    plt.title('Histogram of ' + variableName)
    plt.show()


def train_and_validation(inputs, inputs2, outputs):
    indexes = [i for i in range(len(inputs))]
    trainSample = []
    i = 0
    while i < int(0.8 * len(inputs)):
        j = random.choice(indexes)
        if j not in trainSample:
            trainSample.append(j)
            i += 1

    indexes2 = [i for i in range(len(inputs))]
    trainSample2 = []
    i = 0
    while i < int(0.8 * len(inputs)):
        j = random.choice(indexes2)
        if j not in trainSample2:
            trainSample2.append(j)
            i += 1

    validationSample = [i for i in indexes if not i in trainSample]
    validationSample2 = [i for i in indexes2 if not i in trainSample2]

    print(len(trainSample), " ", len(trainSample2))
    trainInputs = [inputs[i] for i in trainSample]
    trainInputs2 = [inputs2[i] for i in trainSample2]
    trainOutputs = [outputs[i] for i in trainSample]

    validationInputs = [inputs[i] for i in validationSample]
    validationInputs2 = [inputs2[i] for i in validationSample2]
    validationOutputs = [outputs[i] for i in validationSample]

    plt.plot(trainInputs, trainOutputs, 'ro', label='training data')
    plt.plot(trainInputs2, trainOutputs, 'bo', label='training data 2')
    plt.plot(validationInputs, validationOutputs, 'g^', label='validation data')
    plt.plot(validationInputs2, validationOutputs, 'y^', label='validation data 2')
    plt.title('train and validation data')
    plt.xlabel('GDP capita')
    plt.ylabel('happiness')
    plt.legend()
    plt.show()

    return trainInputs, trainInputs2, trainOutputs, validationInputs, validationInputs2, validationOutputs


def plot(trainInputs, trainInputs2, trainOutputs, w0, w1):
    noOfPoints = 1000
    xref = []
    xref2 = []
    val = min(trainInputs)
    val2 = min(trainInputs2)
    step = (max(trainInputs) - min(trainInputs)) / noOfPoints
    step2 = (max(trainInputs2) - min(trainInputs2)) / noOfPoints
    for i in range(1, noOfPoints):
        xref.append(val)
        xref2.append(val2)
        val += step
        val2 += step2
    yref = [w0 + w1 * el for el in xref]
    yref2 = [w0 + w1 * el for el in xref2]

    plt.plot(trainInputs, trainOutputs, 'ro', label='training data')  # train data are plotted by red and circle sign
    plt.plot(xref, yref, 'b-', label='learnt model')  # model is plotted by a blue line
    plt.plot(xref2, yref2, 'g-', label='learnt model 2')  # model is plotted by a blue line
    plt.title('train data and the learnt model')
    plt.xlabel('GDP capita')
    plt.ylabel('happiness')
    plt.legend()
    plt.show()


def learning_model(trainInputs, trainInputs2, trainOutputs):
    xx = [el for el in trainInputs]
    xx2 = [el for el in trainInputs2]

    regressor = MyLinearUnivariateRegression()
    regressor.fit(xx, trainInputs)
    regressor.fit(xx2, trainInputs2)
    w0, w1 = regressor.intercept_, regressor.coef_
    return w0, w1, regressor


def calculate_metrics(computedValidationOutputs, validationOutputs):
    error = 0.0
    for t1, t2 in zip(computedValidationOutputs, validationOutputs):
        error += (t1 - t2) ** 2
    error = error / len(validationOutputs)
    print('prediction error (manual): ', error)


def plotData(x1, y1, z1, x2=None, y2=None, z2=None, x3=None, y3=None, z3=None, title=None):
    plt.plot(x1, y1, 'ro', label='train data')
    plt.plot(z1, y1, 'go', label='train data 2')
    if x2:
        plt.plot(x2, y2, 'b-', label='learnt model')
    if x3:
        plt.plot(x3, y3, 'g^', label='test data')
    if z2:
        plt.plot(z2, y2, 'y-', label='learnt model 2')
    if z3:
        plt.plot(z3, y3, 'k^', label='test data 2')
    plt.title(title)
    plt.legend()
    plt.show()


def execute2():
    crtDir = os.getcwd()
    filePath = 'v1.csv'
    print(filePath)
    inputs, inputs2, outputs = loadData(filePath, 'Economy..GDP.per.Capita.', "Freedom", 'Happiness.Score')

    plotDataHistogram(inputs, 'capita GDP')
    plotDataHistogram(inputs2, 'liberty')
    plotDataHistogram(outputs, 'Happiness score')

    plotData(inputs, outputs, inputs2, [], [], [], [], [], [], 'capita vs liberty vs happiness')

    trainInputs, trainInputs2, trainOutputs, validationInputs, validationInpits2, validationOutputs = train_and_validation(
        inputs, inputs2, outputs)

    w0, w1, regressor = learning_model(trainInputs, trainInputs2, trainOutputs)

    plot(trainInputs, trainInputs2, trainOutputs, w0, w1)

    computedValidationOutputs = regressor.predict([[x] for x in validationInputs])
    plotData([], [], [], validationInputs, computedValidationOutputs, validationInpits2, validationInputs,
             validationOutputs, validationInpits2,
             "predictions vs real test data")

    calculate_metrics(computedValidationOutputs, validationOutputs)
