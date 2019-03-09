//메모리 : 41052kb , 시간 : 32ms
#include <iostream>
#include <cmath>
#include <cstdio>

#define SIZE 10000000
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
	int a;
	cin >> a;
	for (int index = 2; index < SIZE;) {
		if (a%index == 0 && cache[index] == 0) {
			printf("%d\n", index);
			a /= index;
		}
		else
			index++;

		if (a == 1) {
			break;
		}
	}
	return 0;
}