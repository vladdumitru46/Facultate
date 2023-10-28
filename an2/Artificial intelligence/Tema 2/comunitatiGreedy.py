import copy
import os
from random import random, randint

import numpy as np
import networkx as nx
import matplotlib.pyplot as plt
import warnings

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
    # crtDir = os.getcwd()
    # filePath = os.path.join(crtDir, 'data', 'net.in')
    # network = readNet(filePath)

    # plot the network
    # plotNetwork(network)


# load a network


def greedyCommunitiesDetectionByTool(network):
    # Input: a graph
    # Output: list of comunity index (for every node)

    from networkx.algorithms import community

    A = np.matrix(network["matrix"])
    G = nx.from_numpy_matrix(A)
    communities_generator = community.girvan_newman(G)
    top_level_communities = next(communities_generator)
    sorted(map(sorted, top_level_communities))
    communities = [0 for node in range(network['number_of_nodes'])]
    index = 1
    for community in sorted(map(sorted, top_level_communities)):
        for node in community:
            communities[node] = index
        index += 1
    return communities


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
    modularity = 0
    matrix = getCommunity(community_assignment)
    # print(matrix)
    for i in matrix.values():
        numbers_of_edges_in_matrix = 0
        if len(i) > 1:
            for j in range(len(i) - 1):
                for k, f in network["edges"]:
                    if i[j] == k and i[j + 1] == f:
                        numbers_of_edges_in_matrix += 1
        suma = 0
        if len(i) > 1:
            for j in range(len(i) - 1):
                for nodes2 in graph2.nodes():
                    if i[j] == nodes2:
                        suma += grad(graph2, nodes2)
        modularity = modularity + ((numbers_of_edges_in_matrix / network["number_of_edges"]) - (
                (suma * suma) / (4 * network["number_of_edges"] * network["number_of_edges"])))
    return modularity


def greedyCommunitiesDetection(network, graph):
    # Input: a graph
    # Output: list of comunity index (for every node)

    # TODOS
    num_communities = len(network["nodes"])
    community_assignment = []
    current_modularity = 0
    for i in range(num_communities):
        community_assignment.append(i)
    for node in range(num_communities):
        current_community = community_assignment[node]
        current_modularity = calculate_modularity(community_assignment, network, graph)
        for comm in range(num_communities):
            community_assignment[node] = comm
            new_modularity = calculate_modularity(community_assignment, network, graph)
            if new_modularity > current_modularity:
                current_modularity = new_modularity
                current_community = comm
                node = 0
        community_assignment[node] = current_community
    print(current_modularity)
    return community_assignment


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
    lista = greedyCommunitiesDetection(network, graph)
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
    plotNetwork(network_copy, greedyCommunitiesDetection(network, graph))
    # # test_community_result = greedyCommunitiesDetectionByTool(network)
    # print(test_community_result)
    # plotNetwork(network_copy, greedyCommunitiesDetectionByTool(network))
