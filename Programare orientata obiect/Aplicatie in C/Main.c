#define _CRTDBG_MAP_ALLoC
#include "Teste.h"
#include "Run.h"
#include<crtdbg.h>

int main()
{
	run();
	run_teste();
	_CrtDumpMemoryLeaks();
	return 0;

}
