import os
import socket
import string
import random

ip = "172.30.115.200"
port = 8888

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

s.bind((ip, port))

s.listen(2)

while True:
    conn, addr = s.accept()
    conn1, addr1 = s.accept()
    nr = 0
    n = 1
    clienti = []
    if os.fork() == 0:
        accept = False
        if not accept:
            client = addr
            client2 = addr1
            print("s-a conectat clientul: ", client)
            print("s-a conectat clientul: ", client2)
            conn.send(str(n).encode())
            conn1.send(str(n).encode())
            nr += 1
            clienti.append(client)
            clienti.append(client2)

            nr = 0
            n += 1
            litera = random.choice(string.ascii_lowercase)
            conn.send(litera.encode())
            print("litera: ", str(litera))
            cuvant = conn.recv(10)
            cuvant = cuvant.decode()
            if cuvant[0] != str(litera):
                print("Joc terminat")
                mesaj_victorie = "Ai castigat!"
                conn1.send(mesaj_victorie.encode())
                mesaj_infrangere = "Ai pierdut"
                conn.send(mesaj_infrangere.encode())
                break
            accept = True
            conn1.send(cuvant.encode())
            cuvant2 = conn1.recv(10)
            cuvant2 = cuvant2.decode()

        if cuvant2[0] != cuvant[len(cuvant) - 2] and cuvant2[1] != cuvant[len(cuvant) - 1]:
            print("Joc terminat")
            mesaj_victorie = "Ai castigat!"
            conn.send(mesaj_victorie.encode())
            mesaj_infrangere = "Ai pierdut"
            conn1.send(mesaj_infrangere.encode())
            break
        else:
            mesaj = "Jocul continua"
            conn.send(mesaj.encode())
            conn1.send(mesaj.encode())
            conn.send(cuvant2.encode())
            cuvant = conn.recv(10)
            cuvant = cuvant.decode()
        if cuvant[0] != cuvant2[len(cuvant2) - 2] and cuvant[1] != cuvant2[len(cuvant2) - 1]:
            print("Joc terminat")
            mesaj_victorie = "Ai castigat!"
            conn1.send(mesaj_victorie.encode())
            mesaj_infrangere = "Ai pierdut"
            conn.send(mesaj_infrangere.encode())
            break
        else:
            mesaj = "Jocul continua"
            conn.send(mesaj.encode())
            conn1.send(mesaj.encode())
            conn1.send(cuvant.encode())
            cuvant2 = conn1.recv(10)
            cuvant2 = cuvant.decode()

conn.close()
conn1.close()
