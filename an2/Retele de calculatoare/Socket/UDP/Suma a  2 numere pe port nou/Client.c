#include <stdio.h> 
#include<sys/socket.h>
#include <netinet/in.h>
#include <string.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <string.h>
#include <stdlib.h>


int main(int argc, char* argv[]) {
	//FILE* file;
	uint16_t p;
	//int ACCEPT;
	//file = fopen("Text.txt", "r");
	//fscanf(file, "%hu", &p);
	//fscanf(file, "%d", &ACCEPT);
	//printf("ACCEPT=%d/n", ACCEPT);
	//printf("PORT=%hu\n", p);
	//printf("ACCEPT=%d\n", ACCEPT);

	struct sockaddr_in client;
	int c, len = sizeof(client);
	char numere[512];

	c = socket(AF_INET, SOCK_DGRAM, 0);

	memset((char*)&client, 0, sizeof(client));
	client.sin_family = AF_INET;
	client.sin_port = htons(atoi(argv[2]));
	client.sin_addr.s_addr = inet_addr(argv[1]);



	printf("Cititi sirul de numere:");
	fgets(numere, sizeof(numere), stdin);

	int l = strlen(numere);
	l = htons(l);
	sendto(c, &l, sizeof(l), 0, (struct sockaddr*)&client, sizeof(client));
	sendto(c, numere, sizeof(char) * l, 0, (struct sockaddr*)&client, sizeof(client));


	recvfrom(c, &p, sizeof(p), 0, (struct sockaddr*)&client, &len);
	p = ntohs(p);
	printf("Port: %hu\n", p);
	close(c);
	
	struct sockaddr_in cl;
	int cc, lenn = sizeof(cl);
	cc = socket(AF_INET, SOCK_DGRAM, 0);
	printf("Socket nou!\n");

	memset((char*)&cl, 0, sizeof(cl));
	cl.sin_family = AF_INET;
	cl.sin_port = htons(p);
	cl.sin_addr.s_addr = inet_addr(argv[1]);

	printf("IP: %s, Port: %d\n", argv[1], p);
	char da[2] = "da";
	sendto(cc, da, sizeof(char) * 2, 0, (struct sockaddr*)&cl, lenn);

	char ip[500];
	//lenn = sizeof(client);
	recvfrom(cc, ip, sizeof(ip), 0, (struct sockaddr*)&cl, &lenn);
	printf("IP: %s\n", ip);

	uint16_t suma = 0;
	recvfrom(cc, &suma, sizeof(suma), 0, (struct sockaddr*)&cl, &lenn);
	suma = ntohs(suma);
	printf("Suma= %d\n", suma);
	close(cc);
}