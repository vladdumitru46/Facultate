import csv
import os
import warnings
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


# step 1

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

    # normalise the features
    trainInputs, testInputs = normalisation(trainInputs, testInputs)
    return trainInputs, testInputs, trainOutputs, testOutputs


def run():
    inputs, outputs, outputNames, featureNames = loadDataForMultiClassProblem('flori.csv')
    trainInputs, testInputs, trainOutputs, testOutputs = train_and_test(inputs, outputs)

    from sklearn import linear_model
    classifier = linear_model.LogisticRegression()
    classifier.fit(trainInputs, trainOutputs)

    computedTestOutputs = classifier.predict(testInputs)
    error = 0.0
    for t1, t2 in zip(computedTestOutputs, testOutputs):
        if t1 != t2:
            error += 1
    error = error / len(testOutputs)
    print("classification error (manual): ", 1 - error)

    from sklearn.metrics import accuracy_score
    error = 1 - accuracy_score(testOutputs, computedTestOutputs)
    print("classification error (tool): ", 1 - error)
