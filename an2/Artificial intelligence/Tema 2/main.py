
from comunitatiGreedy import readNet, plotNetwork, greedyCommunitiesDetectionByTool, loadNetwork

if __name__ == '__main__':
    print("dolphins:")
    loadNetwork('real/dolphins/dolphins.gml')
    print("karate:")
    loadNetwork('real/karate/karate.gml')
    print("kerbs: ")
    loadNetwork('real/krebs/krebs.gml')
    print("football:")
    loadNetwork('real/football/football.gml')
    print("net:")
    loadNetwork('real/net/net.gml')

    print("sinonime adiacente: ")
    loadNetwork('real/adjnoun/sinonime adiacente.gml')
    print("les miserables: ")
    loadNetwork('real/lemis/les mizerables.gml')
    print("hep-th: ")
    loadNetwork('real/polblogs/hep-th.gml')
    print("power: ")
    loadNetwork('real/power/polbooks.gml')
    print("neural networks: ")
    loadNetwork('real/neural networks/celegansneural.gml')
    print("netsience: ")
    loadNetwork('real/as-22/netscience.gml')




