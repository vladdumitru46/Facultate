from math import exp

import numpy as np


class MLPClassifier:
    def __init__(self, input_size, hidden_size, output_size):
        self.input_size = input_size
        self.hidden_size = hidden_size
        self.output_size = output_size
        self.W1 = np.random.randn(self.input_size, self.hidden_size)
        self.b1 = np.zeros((1, self.hidden_size))
        self.W2 = np.random.randn(self.hidden_size, self.output_size)
        self.b2 = np.zeros((1, self.output_size))

    @staticmethod
    def sigmoid(x):
        return 1 / (1 + exp(-x))

    def sigmoidDerivative(self, x):
        return self.sigmoid(x) * (1 - self.sigmoid(x))

    @staticmethod
    def convertLabelsInto0And1(labels):
        num_classes = len(np.unique(labels))
        labelWith1And0 = np.zeros((len(labels), num_classes))
        for i, label in enumerate(labels):
            labelWith1And0[i, label] = 1
        return labelWith1And0

    def forward_propagation(self, trainInputs):
        self.hidden_layer_input = np.dot(trainInputs, self.W1) + self.b1
        self.hidden_layer_output = np.vectorize(self.sigmoid)(self.hidden_layer_input)
        self.output_layer_input = np.dot(self.hidden_layer_output, self.W2) + self.b2
        self.output_layer_output = np.vectorize(self.sigmoid)(self.output_layer_input)
        return self.output_layer_output

    def back_propagation(self, trainInputs, trainOutputs, output):
        gradient_output = output - trainOutputs
        gradient_hidden = np.dot(gradient_output, self.W2.T) * np.vectorize(self.sigmoidDerivative)(
            self.hidden_layer_input)

        gradient_W1 = np.dot(trainInputs.T, gradient_hidden)
        gradient_W2 = np.dot(self.hidden_layer_output.T, gradient_output)
        gradient_b1 = np.sum(gradient_hidden, axis=0)
        gradient_b2 = np.sum(gradient_output, axis=0)
        return gradient_W1, gradient_W2, gradient_b1, gradient_b2

    @staticmethod
    def calculate_accuracy(output, trainOutputs):
        predicted_labels = np.argmax(output, axis=1)
        true_labels = np.argmax(trainOutputs, axis=1)
        correct_output = np.sum(predicted_labels == true_labels)
        return correct_output/len(trainOutputs)

    @staticmethod
    def calculate_loss(output, trainOutputs):
        return np.mean((output - trainOutputs) ** 2)

    def fit(self, trainInputs, trainOutputs, learningRate=0.1, noEpochs=100):
        y = self.convertLabelsInto0And1(trainOutputs)
        for epoch in range(noEpochs):
            output = self.forward_propagation(trainInputs)
            gradient_W1, gradient_W2, gradient_b1, gradient_b2 = self.back_propagation(trainInputs, y, output)
            self.W1 -= learningRate * gradient_W1
            self.W2 -= learningRate * gradient_W2
            self.b1 -= learningRate * gradient_b1
            self.b2 -= learningRate * gradient_b2
            loss = self.calculate_loss(output, y)
            accuracy = self.calculate_accuracy(output, y)
            print("Iteration: ", epoch + 1, "loss: ", loss, " accuracy: ", accuracy)
