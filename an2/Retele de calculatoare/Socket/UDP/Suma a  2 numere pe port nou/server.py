import random
import socket
import sys
import time

IP = sys.argv[1]
Port = sys.argv[2]
print(Port)
accept = False
list = []
listsum = []
lport = []
while True:
    s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    s.bind((IP, int(Port)))

    data, addr = s.recvfrom(4)
    print("S-a conectat clientul: ", addr)
    data = int.from_bytes(data, "little")
    l = socket.ntohs(data)
    print("l= ", l)
    data1, addr1 = s.recvfrom(l)
    numbers = data1.decode()
    print("numbers= ", numbers)

    sum = 0
    P = random.randint(1000, 9999)
    print("New port = ", P)
    s.sendto(P.to_bytes(2, "big"), addr)
    s.close()
    ss = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    ss.bind((IP, P))
    d, a = ss.recvfrom(2)
    print("D=", d.decode())
    if len(list) == 0:
        time.sleep(5)
        ss.sendto(str(addr).encode(), a)
        list.append(addr)
    else:
        time.sleep(5)
        ss.sendto(str(list[len(list) - 1]).encode(), a)
        list.append(addr)
    for n in numbers:
        if n != " " and n != '\n' and n != '\t':
            sum += int(n)

    if len(listsum) == 0:
        time.sleep(5)
        print("sum=", sum)
        ss.sendto(sum.to_bytes(2, "big"), a)
        listsum.append(sum)
    else:
        time.sleep(5)
        print("sum=", sum)
        ss.sendto(listsum[len(listsum) - 1].to_bytes(2, "big"), a)
        listsum.append(sum)
        lport.append(P)
    ss.close()
