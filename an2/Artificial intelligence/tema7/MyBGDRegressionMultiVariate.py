import random


class MyBGDRegressionMultiVariate:
    def __init__(self):
        self.w0 = 0.0
        self.w1 = 0.0
        self.w2 = 0.0

    # simple stochastic GD
    def fit(self, x, x2, y, learningRate=0.001, noEpochs=1000):
        for epoch in range(noEpochs):
            sw0 = 0.0
            sw1 = 0.0
            sw2 = 0.0
            for i in range(len(x)):
                sw1 += x[i] * (self.w1 * x[i] + self.w2 * x2[i] + self.w0 - y[i])
                sw2 += x2[i] * (self.w1 * x[i] + self.w2 * x2[i] + self.w0 - y[i])
                sw0 += self.w1 * x[i] + self.w2 * x2[i] + self.w0 - y[i]

            self.w0 = self.w0 - learningRate * (1 / len(x)) * sw0
            self.w1 = self.w1 - learningRate * (1 / len(x)) * sw1
            self.w2 = self.w2 - learningRate * (1 / len(x)) * sw2

    def eval(self, xi):
        yi = self.w1
        for j in range(len(xi)):
            yi += self.w1 * xi[j]
        return yi

    def predict(self, x):
        yComputed = [self.eval(xi) for xi in x]
        return yComputed
