import os
import socket
import string
from random import random

ip = "172.30.115.200"
port = 8888

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

s.connect((ip, port))
accept = False
while True:
    if not accept:
        id_joc = s.recv(1)
        id_joc = id_joc.decode()
        print("Id_joc ", int(id_joc))
        litera = s.recv(1)
        litera = litera.decode()
        print("Litera de inceput", str(litera))
        cuvant = input("Scrieti un cuvant care incepe cu litera de mai sus: ")
        s.send(cuvant.encode())
        if cuvant[0] != litera:
            mesaj = s.recv(9)
            mesaj = mesaj.decode()
            print(mesaj)
            break
        accept = True

    mesaj = s.recv(14)
    mesaj = mesaj.decode()
    if mesaj == "Jocul continua":
        print(mesaj)
        cuvant2 = s.recv(10)
        cuvant2.decode()
        print("cuvantul adversarului: ", cuvant2)
        cuvant = input("Scrieti un cuvant care sa inceapa cu ultimele 2 litere ale cuvatului adversarului: ")
        s.send(cuvant.encode())
    else:
        print(mesaj)
        break
