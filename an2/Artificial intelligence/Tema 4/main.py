from random import randint

from solution import load_network, load_network2

if __name__ == '__main__':
    print("net:")
    load_network('real/net/net.gml', 'real/net/results.txt')
    print("100-10000")
    load_network('real/neural networks/celegansneural.gml', 'real/neural networks/result.txt')
    print("les mise: ")
    load_network('real/lemis/les mizerables.gml', 'real/lemis/result.txt')
    # load_network('astro-ph.gml', 'result.txt')
