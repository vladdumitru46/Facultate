import copy
import os
from bisect import bisect_left
from itertools import permutations
from random import random, randint

import numpy as np
import networkx as nx
import matplotlib.pyplot as plt
import warnings

from random import randint, seed
from Tools.scripts.combinerefs import combine
from networkx.algorithms import community

warnings.simplefilter('ignore')


def readNet(fileName):
    f = open(fileName, "r")
    net = {}
    n = 100000
    net['number_of_nodes'] = n
    mat = []
    for i in range(n):
        mat.append([])
        line = f.readline()
        elems = line.split("    ")
        for j in range(n):
            mat.append(elems)
    net["matrix"] = mat
    degrees = []
    noEdges = 0
    for i in range(n):
        d = 0
        for j in range(n):
            if (mat[i][j] == 1):
                d += 1
            if (j > i):
                noEdges += mat[i][j]
        degrees.append(d)
    net["number_of_edges"] = noEdges
    f.close()
    return net


def plot_network(network, communities=[1, 1, 1, 1, 1, 1]):
    np.random.seed(123)  # to freeze the graph's view (networks uses a random view)
    A = np.matrix(network["matrix"])
    G = nx.from_numpy_matrix(A)
    pos = nx.spring_layout(G)  # compute graph layout
    plt.figure(figsize=(4, 4))  # image is 8 x 8 inches
    nx.draw_networkx_nodes(G, pos, node_size=50, cmap="RdYlBu", node_color=communities)
    nx.draw_networkx_edges(G, pos, alpha=0.3)
    plt.show()


def calculate_number_of_edges(perm, network):
    number_of_edges = 0
    for i in range(0, len(perm) - 1):
        for k, f in network["edges"]:
            if perm[i] == k and perm[i + 1] == f or perm[i] == f and perm[i + 1] == k:
                number_of_edges += 1
    return number_of_edges


def calculate_fitness(list_of_modularityes):
    total_fitness = sum(list_of_modularityes)
    relative_fitness = []
    for fit in list_of_modularityes:
        relative_fitness.append(fit / total_fitness)
    cumulative_prob = []
    for i in range(len(relative_fitness)):
        cumulative_prob.append(sum(relative_fitness[:i + 1]))
    return cumulative_prob


def combineModularities(param, param1):
    new_individ = []
    for l in range(0, (len(param))):
        j = randint(0, 1)
        if j == 0:
            new_individ.append(param[l])
        else:
            new_individ.append(param1[l])
    return new_individ


def mutation(new_individ):
    p1 = randint(0, len(new_individ)-1)
    p2 = randint(0, len(new_individ)-1)
    new_individ[p1], new_individ[p2] = new_individ[p2], new_individ[p1]
    return new_individ


def ruleta(cumulative_prob, list_of_modularities, dict, network):
    pos1 = 0
    r1 = random()
    while pos1 < len(cumulative_prob) and cumulative_prob[pos1] < r1:
        pos1 += 1
    pos2 = pos1
    r2 = random()
    while pos2 < len(cumulative_prob) and cumulative_prob[pos2] < r2:
        pos2 += 1
    nii = combineModularities(dict[pos1], dict[pos2])
    ni = mutation(nii)
    if list_of_modularities[pos1] >= list_of_modularities[pos2]:
        dict[pos2] = ni
        list_of_modularities[pos2] = calculate_number_of_edges(ni, network)
    else:
        dict[pos1] = ni
        list_of_modularities[pos1] =  calculate_number_of_edges(ni, network)
    return dict, list_of_modularities


def maxim(list_of_modularityes, dict):
    maxim = -1
    lmax = -1
    for l in range(len(list_of_modularityes)):
        if list_of_modularityes[l] > maxim:
            maxim = list_of_modularityes[l]
            lmax = l
    # print(maxim)
    return dict[lmax], maxim


def generate_a_random_permutation(n, network, write):
    nr = 0
    maxi = 0
    l = []
    m = 100
    j = 0
    dict = {}
    while j < 100:
        perm = [i for i in range(n)]
        pos1 = randint(0, n - 1)
        pos2 = randint(0, n - 1)
        perm[pos1], perm[pos2] = perm[pos2], perm[pos1]
        perm.append(perm[0])
        dict[nr] = perm
        nr += 1
        j += 1
    fitness = []
    for perm in dict.values():
        fitness.append(calculate_number_of_edges(perm, network))
    j = 0
    while j < m:
        cumuliative_prop = calculate_fitness(fitness)
        dict, fitness = ruleta(cumuliative_prop, fitness, dict, network)
        perm, f = maxim(fitness, dict)
        # if fitness == n:
        if f >= maxi and perm not in l:
            # print("FOHD")
            maxi = f
            print(perm, ": ", (f * 100) // n, "%")
            s = str(perm)
            k = ": " + str(((f * 100) // n) )+ "%" + '\n'
            st = s + k
            fi = open(write, "a")
            fi.write(st)
            fi.close()
            l.append(perm)
            # nr += 1
        j += 1


def get_matrix_of_graph(graph):
    no_nodes = len(graph.nodes())
    matrix = np.zeros((no_nodes, no_nodes), dtype='uint8')
    for i, j in graph.edges():
        matrix[i][j] = matrix[j][i] = 1
    return matrix


def convert_gml_to_dictionary_for_network(graph):
    network = {
        "nodes": graph.nodes(),
        "number_of_nodes": len(graph.nodes()),
        "edges": graph.edges(),
        "number_of_edges": len(graph.edges()),
        "matrix": get_matrix_of_graph(graph)
    }
    return network


def load_network(file_path, write):
    graph = nx.read_gml(file_path, label="id")
    dictionary = {}
    network = convert_gml_to_dictionary_for_network(graph)
    # plot_network(network)
    generate_a_random_permutation(network["number_of_nodes"], network, write)


def load_network2(file_path):
    network = readNet(file_path)
    # plot_network(network)
    generate_a_random_permutation(network["number_of_nodes"], network)
