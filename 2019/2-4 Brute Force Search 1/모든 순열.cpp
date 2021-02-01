//1988kb, 24ms
#include <iostream>
#include <stdio.h>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
	int size;
	cin >> size;
	vector<int> perm;
	for (int i = 1; i <= size; i++) {
		perm.push_back(i);
		printf("%d ", i);
	}
	printf("\n");
	while (next_permutation(perm.begin(), perm.end())) {
		for (int i = 0; i < size; i++)
			printf("%d ", perm[i]);
		printf("\n");
	}
	system("pause");
	return 0;
}