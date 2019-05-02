//2020kb, 4ms
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
	int size;
	cin >> size;
	vector<int> perm;
	for (int i = 0; i < size; i++) {
		int input;
		cin >> input;
		perm.push_back(input);
	}

	if (prev_permutation(perm.begin(), perm.end())) {
		for (int i = 0; i < size; i++)
			cout << perm[i] << " ";
	}
	else
		cout << -1 << endl;

	return 0;
}