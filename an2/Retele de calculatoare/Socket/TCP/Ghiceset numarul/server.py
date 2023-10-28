import socket
from random import randint

ip = "192.168.1.128"
port = 8888

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((ip, port))

s.listen(1)

acept = False
nr = 1
while True:
    if not acept:
        conn, addr = s.accept()
        print("S-a conectat clientul cu adresa: ", addr)
        lcuv = conn.recv(2)
        lcuv = int.from_bytes(lcuv, 'little')
        l = socket.ntohs(lcuv)
        cuvant = conn.recv(l)
        cuvant = cuvant.decode()
        print("L = ", l)
        print("cuv = ", cuvant)
        s = 0
        p1 = randint(0, l - 3)
        p2 = randint(p1, l - 2)
        p3 = randint(p2, l - 1)
        s += ord(cuvant[p1]) + ord(cuvant[p2]) + ord(cuvant[p3])
        print("s=", s)
        print("Sa inceapa jocul: ")

    ghiceala = conn.recv(2)
    ghiceala = int.from_bytes(ghiceala, 'little')
    ghici = socket.ntohs(ghiceala)
    print("ghici =", ghici)
    if ghici == s:
        ok = 'da'
        conn.send(ok.encode())
        print("am trimis: ", ok)
        conn.send(nr.to_bytes(2, "big"))
        break
    else:
        ok = 'nu'
        conn.send(ok.encode())
        print("am trimis: ", ok)
        if nr % 5 == 0:
            print("nr == 5")
            continui = conn.recv(2)
            continui = continui.decode()
            print("continui: ", continui)
            if continui == 'da':
                nr = 0
            else:
                conn.send(nr.to_bytes(2, "big"))
                print("nr = ", nr)
                break
    acept = True
    print("a fost ghiceala cu numarul: ", nr)
    nr += 1

