import csv
import os
import warnings
from math import sqrt

from sklearn import linear_model

from MyBGDRegressionMultiVariate import MyBGDRegressionMultiVariate

warnings.simplefilter('ignore')
import matplotlib.pyplot as plt
import numpy as np


def plotDataHistogram(x, variableName):
    n, bins, patches = plt.hist(x, 10)
    plt.title('Histogram of ' + variableName)
    plt.show()


def plot3Ddata(x1Train, x2Train, yTrain, x1Model=None, x2Model=None, yModel=None, x1Test=None, x2Test=None, yTest=None,
               title=None):
    ax = plt.axes(projection='3d')
    if x1Train:
        ax.scatter(x1Train, x2Train, yTrain, c='r', marker='o', label='train data')
    if x1Model:
        ax.scatter(x1Model, x2Model, yModel, c='b', marker='_', label='learnt model')
    if x1Test:
        ax.scatter(x1Test, x2Test, yTest, c='g', marker='^', label='test data')
    plt.title(title)
    ax.set_xlabel("capita")
    ax.set_ylabel("freedom")
    ax.set_zlabel("happiness")
    plt.legend()
    plt.show()


def loadDataMoreInputs(fileName, inputVariabNames, outputVariabName):
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
    selectedVariable1 = dataNames.index(inputVariabNames[0])
    selectedVariable2 = dataNames.index(inputVariabNames[1])
    inputs = [[float(data[i][selectedVariable1]), float(data[i][selectedVariable2])] for i in range(len(data))]
    selectedOutput = dataNames.index(outputVariabName)
    outputs = [float(data[i][selectedOutput]) for i in range(len(data))]

    return inputs, outputs


def normalisationInput(trainData, testData, mean, mean2, standardDeviation, standardDeviation2):
    normalisedTrainData = []
    normalisedTestData = []
    for i in trainData:
        normalisedTrainData.append([(i[0] - mean) / standardDeviation, (i[1] - mean2) / standardDeviation2])
    for i in testData:
        normalisedTestData.append([(i[0] - mean) / standardDeviation, (i[1] - mean2) / standardDeviation2])
    return normalisedTrainData, normalisedTestData


def normalisation(trainData, testData, mean, standardDeviation):
    normalisedTrainData = []
    normalisedTestData = []
    for i in trainData:
        normalisedTrainData.append((i - mean) / standardDeviation)
    for i in testData:
        normalisedTestData.append((i - mean) / standardDeviation)
    return normalisedTrainData, normalisedTestData


def calculateMean(train_data):
    s = sum(train_data)
    return s / len(train_data)


def calculateStandardDeviationInput(trainData, mean, mean2):
    s1 = 0
    s2 = 0
    for i in trainData:
        s1 += (i[0] - mean) ** 2
        s2 += (i[1] - mean2) ** 2
    return (s1 / len(trainData[0])) ** 0.5, (s2 / len(trainData[1])) ** 0.5


def calculateStandardDeviationOutput(trainData, mean):
    s1 = 0
    for i in trainData:
        s1 += abs(i - mean) ** 2
    return sqrt(s1 / len(trainData))


def run_my_code():
    crtDir = os.getcwd()
    filePath = os.path.join(crtDir, 'data', 'world-happiness-report-2017.csv')

    inputs, outputs = loadDataMoreInputs('world-happiness-report-2017.csv', ['Economy..GDP.per.Capita.', 'Freedom'],
                                         'Happiness.Score')

    feature1 = [ex[0] for ex in inputs]
    feature2 = [ex[1] for ex in inputs]

    # plot the data histograms
    plotDataHistogram(feature1, 'capita GDP')
    plotDataHistogram(feature2, 'freedom')
    plotDataHistogram(outputs, 'Happiness score')

    # check the liniarity (to check that a linear relationship exists between the dependent variable (y = happiness) and the independent variables (x1 = capita, x2 = freedom).)
    plot3Ddata(feature1, feature2, outputs, [], [], [], [], [], [], 'capita vs freedom vs happiness')

    np.random.seed(5)
    indexes = [i for i in range(len(inputs))]
    trainSample = np.random.choice(indexes, int(0.8 * len(inputs)), replace=False)
    testSample = [i for i in indexes if not i in trainSample]

    trainInputs = [inputs[i] for i in trainSample]
    trainOutputs = [outputs[i] for i in trainSample]
    testInputs = [inputs[i] for i in testSample]
    testOutputs = [outputs[i] for i in testSample]

    meanInput1 = calculateMean(trainInputs[0])
    meanInput2 = calculateMean(trainInputs[1])
    meanOutput = calculateMean(trainOutputs)

    standardDeviationInput1, standardDeviationInput2 = calculateStandardDeviationInput(trainInputs, meanInput1,
                                                                                       meanInput2)
    standardDeviationOutput = calculateStandardDeviationOutput(trainOutputs, meanOutput)

    trainInputs, testInputs = normalisationInput(trainInputs, testInputs, meanInput1, meanInput2,
                                                 standardDeviationInput1, standardDeviationInput2)
    trainOutputs, testOutputs = normalisation(trainOutputs, testOutputs, meanOutput, standardDeviationOutput)

    feature1train = [ex[0] for ex in trainInputs]
    feature2train = [ex[1] for ex in trainInputs]

    feature1test = [ex[0] for ex in testInputs]
    feature2test = [ex[1] for ex in testInputs]

    plot3Ddata(feature1train, feature2train, trainOutputs, [], [], [], feature1test, feature2test, testOutputs,
               "train and test data (after normalisation)")

    regressor = MyBGDRegressionMultiVariate()

    regressor.fit(feature1train, feature2train, trainOutputs)

    w0, w1, w2 = regressor.w0, regressor.w1, regressor.w2
    print('the learnt model: f(x) = ', w0, ' + ', w1, ' * x1 + ', w2, ' * x2')

    noOfPoints = 50
    xref1 = []
    val = min(feature1)
    step1 = (max(feature1) - min(feature1)) / noOfPoints
    for _ in range(1, noOfPoints):
        for _ in range(1, noOfPoints):
            xref1.append(val)
        val += step1

    xref2 = []
    val = min(feature2)
    step2 = (max(feature2) - min(feature2)) / noOfPoints
    for _ in range(1, noOfPoints):
        aux = val
        for _ in range(1, noOfPoints):
            xref2.append(aux)
            aux += step2
    yref = [w0 + w1 * el1 + w2 * el2 for el1, el2 in zip(xref1, xref2)]
    plot3Ddata(feature1train, feature2train, trainOutputs, xref1, xref2, yref, [], [], [],
               'train data and the learnt model')
    computedTestOutputs = regressor.predict(testInputs)

    plot3Ddata([], [], [], feature1test, feature2test, computedTestOutputs, feature1test, feature2test, testOutputs,
               'predictions vs real test data')

    error = 0.0
    for t1, t2 in zip(computedTestOutputs, testOutputs):
        error += (t1 - t2) ** 2
    error = error / len(testOutputs)
    print('prediction error (manual): ', error)

    from sklearn.metrics import mean_squared_error
    error = mean_squared_error(testOutputs, computedTestOutputs)
    print('prediction error (tool):   ', error)
