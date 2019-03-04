#include <iostream>
#include <cmath>
#include <cstdio>

#define SIZE 1000000
using namespace std;


int cache[SIZE] = { 0 };

void isPrime() {
	for (int i = 2; i*i < SIZE; i++) {
		if (!cache[i]) {
			for (int j = i * i; j < SIZE; j += i)
				cache[j] = 1;
		}
	}
	cache[1] = 1;
}

int main() {

	isPrime();
	int input;
	while (1)
	{
		scanf("%d", &n);
		if (input == 0)
			break;

		bool chk = false;
		for (int i = 3; i <= n; i += 2)
			if (!cache[i] && !cache[input - i])
			{
				printf("%d = %d + %d\n", input, i, input - i);
				chk = true;
				break;
			}
		if (!chk)
			printf("Goldbach's conjecture is wrong.\n");
	}
	return 0;
}