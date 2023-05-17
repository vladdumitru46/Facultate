import csv
import warnings
from math import exp
from sklearn.preprocessing import StandardScaler

warnings.simplefilter('ignore')
import matplotlib.pyplot as plt
import numpy as np


def plotDataHistogram(x, variableName):
    n, bins, patches = plt.hist(x, 10)
    plt.title('Histogram of ' + variableName)
    plt.show()


def plot3_data(x1Train, x2Train, yTrain, x1Model=None, x2Model=None, yModel=None, x1Test=None, x2Test=None, yTest=None,
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


def loadDataForMultiClassProblem(filename):
    inputs = []
    outputs = []
    featureNames = []

    with open(filename) as file:
        reader = csv.reader(file, delimiter=',')
        lineCount = 0
        for row in reader:
            if lineCount == 0:
                featureNames = row
            else:
                floatInputs = []
                for x in row[:len(row) - 1]:
                    floatInputs.append(float(x))
                inputs.append(floatInputs)
                outputs.append(row[len(row) - 1])
            lineCount += 1

    outputNames = []
    setOutputs = set(outputs)
    for x in setOutputs:
        outputNames.append(x)

    return inputs, outputs, outputNames, featureNames


def plotDistributionData(outputNames, inputs, outputs):
    numberOfData = len(inputs)
    fig = plt.figure()
    ax = fig.add_subplot(1, 1, 1, projection='3d')
    colors = ["black", "blue", "orange"]
    i = -1
    for label in outputNames:
        i += 1
        sepalLength = [inputs[i][0] for i in range(numberOfData) if outputs[i] == label]
        sepalWidth = [inputs[i][1] for i in range(numberOfData) if outputs[i] == label]
        petalLength = [inputs[i][2] for i in range(numberOfData) if outputs[i] == label]
        petalWidth = [inputs[i][3] for i in range(numberOfData) if outputs[i] == label]

        petalWidthExtended = []

        for p in petalWidth:
            if p < 0:
                petalWidthExtended.append(p * -25)
            else:
                petalWidthExtended.append(p * 25)

        ax.scatter(sepalLength, sepalWidth, petalLength, c=colors[i], s=petalWidthExtended, label=label)

    plt.legend()
    plt.show()


def sigmoid(x):
    return 1 / (1 + exp(-x))


def convertLabelsInto1And0(trainOutputs, label):
    trainOutputsBinary = [0] * len(trainOutputs)
    for i in range(len(trainOutputs)):
        if trainOutputs[i] == label:
            trainOutputsBinary[i] = 1
    return trainOutputsBinary


def myGradientDescent(trainInputs, trainOutputs, labels, noEpochs=3000, learningRate=0.08,
                      batchSize=8):
    coefficients = []
    lenOfBatch = len(trainInputs) // batchSize
    for label in labels:
        w0 = w1 = w2 = w3 = w4 = 0
        trainOutputsBinary = convertLabelsInto1And0(trainOutputs, label)
        for i in range(noEpochs):
            for j in range(lenOfBatch):
                costW0 = costW1 = costW2 = costW3 = costW4 = 0
                for trainInput, lab in zip(trainInputs[j * batchSize:(j + 1) * batchSize],
                                           trainOutputsBinary[j * batchSize:(j + 1) * batchSize]):
                    scalar = sigmoid(
                        w0 + w1 * trainInput[0] + w2 * trainInput[1] + w3 * trainInput[2] + w4 * trainInput[3])
                    costW0 = costW0 + 2 * (scalar - lab) * scalar * (
                            1 - scalar)
                    costW1 = costW1 + 2 * trainInput[0] * (
                            scalar - lab) * scalar * (1 - scalar)
                    costW2 = costW2 + 2 * trainInput[1] * (
                            scalar - lab) * scalar * (1 - scalar)
                    costW3 = costW3 + 2 * trainInput[2] * (
                            scalar - lab) * scalar * (1 - scalar)
                    costW4 = costW4 + 2 * trainInput[3] * (
                            scalar - lab) * scalar * (1 - scalar)
                w0 = w0 - learningRate * (1 / batchSize) * costW0
                w1 = w1 - learningRate * (1 / batchSize) * costW1
                w2 = w2 - learningRate * (1 / batchSize) * costW2
                w3 = w3 - learningRate * (1 / batchSize) * costW3
                w4 = w4 - learningRate * (1 / batchSize) * costW4
        coefficients.extend([w0, w1, w2, w3, w4])

    return coefficients


def prediction(testInputs, coefficients, labels):
    computedTestOutputs = []
    for sepalLength, sepalWidth, petalLength, petalWidth in testInputs:
        dictionary = {"Iris-setosa": 0, "Iris-versicolor": 0, "Iris-virginica": 0}
        for i in range(len(labels)):
            if i == 0:
                w0, w1, w2, w3, w4 = coefficients[:5]
                dictionary[labels[i]] = sigmoid(
                    w0 + w1 * sepalLength + w2 * sepalWidth + w3 * petalLength + w4 * petalWidth)
            elif i == 1:
                w0, w1, w2, w3, w4 = coefficients[5:10]
                dictionary[labels[i]] = sigmoid(
                    w0 + w1 * sepalLength + w2 * sepalWidth + w3 * petalLength + w4 * petalWidth)
            elif i == 2:
                w0, w1, w2, w3, w4 = coefficients[10:15]
                dictionary[labels[i]] = sigmoid(
                    w0 + w1 * sepalLength + w2 * sepalWidth + w3 * petalLength + w4 * petalWidth)
        maxProbability = -1
        predictedClass = ''
        for c, probability in zip(dictionary, dictionary.values()):
            if probability > maxProbability:
                maxProbability = probability
                predictedClass = c
        computedTestOutputs.append(predictedClass)

    return computedTestOutputs


def normalisation(trainData, testData):
    scaler = StandardScaler()
    if not isinstance(trainData[0], list):
        trainData = [[d] for d in trainData]
        testData = [[d] for d in testData]

        scaler.fit(trainData)
        normalisedTrainData = scaler.transform(trainData)
        normalisedTestData = scaler.transform(testData)

        normalisedTrainData = [el[0] for el in normalisedTrainData]
        normalisedTestData = [el[0] for el in normalisedTestData]
    else:
        scaler.fit(trainData)
        normalisedTrainData = scaler.transform(trainData)
        normalisedTestData = scaler.transform(testData)
    return normalisedTrainData, normalisedTestData


def train_and_test(inputs, outputs):
    np.random.seed(500)
    indexes = [i for i in range(len(inputs))]
    trainSample = np.random.choice(indexes, int(0.8 * len(inputs)), replace=False)
    testSample = [i for i in indexes if not i in trainSample]

    trainInputs = [inputs[i] for i in trainSample]
    trainOutputs = [outputs[i] for i in trainSample]
    testInputs = [inputs[i] for i in testSample]
    testOutputs = [outputs[i] for i in testSample]

    trainInputs, testInputs = normalisation(trainInputs, testInputs)

    return trainInputs, testInputs, trainOutputs, testOutputs


def runMyCode():
    inputs, outputs, outputNames, featureNames = loadDataForMultiClassProblem('flori.csv')
    trainInputs, testInputs, trainOutputs, testOutputs = train_and_test(inputs, outputs)
    labels = ["Iris-setosa", "Iris-versicolor", "Iris-virginica"]
    coefficients = myGradientDescent(trainInputs, trainOutputs, labels)
    computedTestOutputs = prediction(testInputs, coefficients, labels)

    error = 0.0
    for t1, t2 in zip(computedTestOutputs, testOutputs):
        if t1 != t2:
            error += 1
    error = error / len(testOutputs)
    print("classification error (manual): ", 1 - error)

    from sklearn.metrics import accuracy_score
    error = 1 - accuracy_score(testOutputs, computedTestOutputs)
    print("classification error (tool): ", 1 - error)
