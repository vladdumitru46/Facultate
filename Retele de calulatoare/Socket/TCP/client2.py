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
        accept = True
    cuvant = s.recv(10)
    cuvant = cuvant.decode()
    print("cuvantul adversarului: ", cuvant)
    cuvant2 = input("Intorduceti un cuvant care sa aiba primele 2 litere ultimele 2 litere ale cuvantului adversarului: ")
    s.send(cuvant2.encode())
    mesaj = s.recv(14)
    mesaj = mesaj.decode()
    if mesaj == "Jocul continua":
        print(mesaj)
        continue
    else:
        print(mesaj)
        break