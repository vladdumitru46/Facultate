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

int main() {
	struct sockaddr_in server;
	int c;
	c = socket(AF_INET, SOCK_STREAM, 0);
	if (c < 0)
	{
		printf("Nu s-a putut creea clientul!\n");
		exit(0);
	}
	memset(&server, 0, sizeof(server));
	server.sin_port = htons(8888);
	server.sin_family = AF_INET;
	server.sin_addr.s_addr = inet_addr("192.168.1.128");

	if (connect(c, (struct sockaddr*)&server, sizeof(server)) < 0) {
		printf("Nu s-a putut conecta clientul la server!\n");
		exit(1);
	}

	uint16_t len;
	char cuvant[256];

	printf("Cititi cuvantul: ");
	scanf("%s", cuvant);
	len = strlen(cuvant);

	len = htons(len);
	send(c, &len, sizeof(len), 0);

	send(c, cuvant, sizeof(char) * strlen(cuvant), 0);

	uint16_t nr = 1;
	int nrr = 0;

	while (nr <= 5) {
		uint16_t ghiceala;
		printf("Ghiciti numarul: ");
		scanf("%hu", &ghiceala);
		ghiceala = htons(ghiceala);
		send(c, &ghiceala, sizeof(ghiceala), 0);

		char ok[3];
		memset(ok, 0, sizeof(ok));
		recv(c, ok, 2, 0);
		printf("ok=%s\n", ok);
		printf("Nr = %d\n", nr);


		if (ok[0] == 'd' && ok[1] == 'a') {
			nrr += nr;
			recv(c, &nr, sizeof(nr), 0);
			nr = ntohs(nr);
			printf("Numar ghiceli: %d", nrr);
			break;
		}

		else
		{
			if (nr == 5) {
				nrr += nr;
				char continui[2];
				printf("Doresti sa continui cu ghicelile? \n");
				scanf("%s", continui);
				send(c, continui, sizeof(char) * strlen(continui), 0);
				if (strcmp(continui, "da") == 0) {
					nr = 1;
				}
				else {
					int nrrr;
					recv(c, &nrrr, sizeof(nrrr), 0);
					nrrr = ntohs(nrrr);
					printf("Ai ghicit de: %hu ori", nrr);
					nr = 6;
				}
			}
		}
		nr += 1;
	}
}
