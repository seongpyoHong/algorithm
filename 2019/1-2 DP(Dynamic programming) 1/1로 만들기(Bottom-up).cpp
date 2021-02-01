#include <iostream>
using namespace std;

int cache[1000001] = { 0 };

int myCal(int x) {
	for (int i = 1; i < x; i++) {
		if (i + 1 <= x) {
			if (cache[i + 1] == 0 || cache[i + 1] > cache[i])
				cache[i + 1] = cache[i] + 1;
		}
		if (i * 3 <= x) {
			if (cache[i * 3] == 0 || cache[i * 3] > cache[i])
				cache[i * 3] = cache[i] + 1;
		}
		if (i * 2 <= x) {
			if (cache[i * 2] == 0 || cache[i * 2] > cache[i])
				cache[i * 2] = cache[i] + 1;
		}
	}
	return cache[x];
}

int main() {
	int input;
	cin >> input;
	cout << myCal(input) << endl;
}