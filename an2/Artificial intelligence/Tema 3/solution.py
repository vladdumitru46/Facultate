import copy
import os
from bisect import bisect_left
from random import random, randint

import numpy as np
import networkx as nx
import matplotlib.pyplot as plt
import warnings

from Tools.scripts.combinerefs import combine
from networkx.algorithms import community

warnings.simplefilter('ignore')


def readNet(fileName):
    f = open(fileName, "r")
    net = {}
    n = int(f.readline())
    net['noNodes'] = n
    mat = []
    for i in range(n):
        mat.append([])
        line = f.readline()
        elems = line.split(" ")
        for j in range(n):
            mat[-1].append(int(elems[j]))
    net["mat"] = mat
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
    net["noEdges"] = noEdges
    net["degrees"] = degrees
    f.close()
    return net


def plotNetwork(network, communities=[1, 1, 1, 1, 1, 1]):
    np.random.seed(123)  # to freeze the graph's view (networks uses a random view)
    A = np.matrix(network["matrix"])
    G = nx.from_numpy_matrix(A)
    pos = nx.spring_layout(G)  # compute graph layout
    plt.figure(figsize=(4, 4))  # image is 8 x 8 inches
    nx.draw_networkx_nodes(G, pos, node_size=50, cmap="RdYlBu", node_color=communities)
    nx.draw_networkx_edges(G, pos, alpha=0.3)
    plt.show()


# load a network

def grad(graf, nod):
    grad = 0
    for i in graf[nod]:
        grad += 1
    return grad


def getCommunity(community_assignment):
    matrix = {}
    nr = 0
    for node in range(len(community_assignment)):
        m = []
        for n in range(len(community_assignment)):
            if community_assignment[node] == community_assignment[n]:
                m.append(n)
        nr += 1
        matrix[nr] = m
    unique_values = set(tuple(v) for v in matrix.values())
    new_dict = {i + 1: list(v) for i, v in enumerate(unique_values)}
    return new_dict


def calculate_modularity(community_assignment, network, graph2):
    # modularity = 0
    # matrix = getCommunity(community_assignment)
    # for i in matrix.values():
    #     numbers_of_edges_in_matrix = 0
    #     if len(i) > 1:
    #         for j in range(len(i) - 1):
    #             for k, f in network["edges"]:
    #                 if i[j] == k and i[j + 1] == f:
    #                     numbers_of_edges_in_matrix += 1
    #     suma = 0
    #     if len(i) > 1:
    #         for j in range(len(i) - 1):
    #             for nodes2 in graph2.nodes():
    #                 if i[j] == nodes2:
    #                     suma += grad(graph2, nodes2)
    #     modularity = modularity + ((numbers_of_edges_in_matrix / network["number_of_edges"]) - (
    #             (suma * suma) / (4 * network["number_of_edges"] * network["number_of_edges"])))
    # return modularity

    noNodes = network['number_of_nodes']
    mat = network['matrix']
    # degrees = network['degrees']
    noEdges = network['number_of_edges']
    M = 2 * noEdges
    Q = 0.0
    for i in range(0, len(community_assignment) - 1):
        for j in range(0, len(community_assignment) - 1):
            if community_assignment[i] == community_assignment[j]:
                Q += (mat[i][j] - grad(graph2, i) * grad(graph2, j) / M)
    return Q * 1 / M


def mean(lista):
    s = 0
    for i in lista:
        s += i
    return s / len(lista)


def combineModularities(param, param1):
    new_individ = []
    # print("len p: ", len(param))
    # print("len p1: ", len(param1))
    for l in range(0, (len(param))):
        j = randint(0, 1)
        if j == 0:
            new_individ.append(param[l])
        else:
            new_individ.append(param1[l])
    return new_individ


def calculate_fitnes(list_of_modularityes):
    total_fitness = sum(list_of_modularityes)
    relative_fitness = []
    for fit in list_of_modularityes:
        relative_fitness.append(fit / total_fitness)
    cumulative_prob = []
    for i in range(len(relative_fitness)):
        cumulative_prob.append(sum(relative_fitness[:i + 1]))
    return cumulative_prob


def ruleta(cumulative_prob, list_of_modularities, dict, network, graph):
    pos1 = 0
    r1 = random()
    while pos1 < len(cumulative_prob) and cumulative_prob[pos1] < r1:
        pos1 += 1
    pos2 = pos1
    r2 = random()
    while pos2 < len(cumulative_prob) and cumulative_prob[pos2] < r2:
        pos2 += 1
    ni = combineModularities(dict[pos1], dict[pos2])
    if list_of_modularities[pos1] >= list_of_modularities[pos2]:
        dict[pos2] = ni
        list_of_modularities[pos2] = calculate_modularity(ni, network, graph)
    else:
        dict[pos1] = ni
        list_of_modularities[pos1] = calculate_modularity(ni, network, graph)
    return dict, list_of_modularities


def maxim(list_of_modularityes, dict):
    maxim = -1
    lmax = -1
    for l in range(len(list_of_modularityes)):
        if list_of_modularityes[l] > maxim:
            maxim = list_of_modularityes[l]
            lmax = l
    # print(maxim)
    return dict[lmax]


def communitiesDetection(network, graph):
    num_communities = len(network["nodes"])
    dict = {}
    nr = -1
    for j in range(0, 100):
        nr += 1
        community_assignment = []
        for i in range(num_communities):
            # r = randint(1, randint(3, num_communities))
            r = randint(1, 2)
            community_assignment.append(r)
        dict[nr] = community_assignment
    i = 100
    copy_i = i
    list_of_modularityes = []
    for j in dict.values():
        list_of_modularityes.append(calculate_modularity(j, network, graph))
    while i > 0:
        cumulative_prob = calculate_fitnes(list_of_modularityes)
        dict, list_of_modularityes = ruleta(cumulative_prob, list_of_modularityes, dict, network, graph)
        d = maxim(list_of_modularityes, dict)
        print(copy_i - i, ": ", calculate_modularity(d, network, graph))
        i = i - 1
    list_of_modularityes = []
    for j in dict:
        list_of_modularityes.append(calculate_modularity(dict[j], network, graph))
    return maxim(list_of_modularityes, dict)


def get_matrix_of_graph(graph):
    no_nodes = len(graph.nodes())
    matrix = np.zeros((no_nodes, no_nodes))
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


def loadNetwork(filePath):
    graph = nx.read_gml(filePath, label="id")
    dictionary = {}
    network = convert_gml_to_dictionary_for_network(graph)
    network_copy = copy.deepcopy(network)
    lista = communitiesDetection(network, graph)
    unique_list = list(set(lista))
    print(len(unique_list))
    for l in unique_list:
        lis = []
        for li in range(len(lista)):
            if lista[li] == l:
                lis.append(li)
        dictionary[l] = lis
    for l in dictionary:
        print(dictionary[l], '\n')
    plotNetwork(network_copy, lista)
