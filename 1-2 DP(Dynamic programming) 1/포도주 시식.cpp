#include <iostream>
#include <algorithm>
using namespace std;

int *wine;
int cache[10001][2] = { 0 };
int Cal_Max(int count) {
	int tmp_max = 0;
	cache[1][0] = 0;
	cache[1][1] = wine[0];
	cache[2][0] = cache[1][1];
	cache[2][1] = cache[1][1] + wine[1];
	for (int i = 3; i <= count; i++) {
		cache[i][0] = max(cache[i - 1][0], cache[i - 1][1]);
		cache[i][1] = (max(cache[i - 1][0], cache[i - 2][0] + wine[i - 2]) + wine[i - 1]);
	}
	return max(cache[count][0], cache[count][1]);
}
int main() {
	int count, wineNum;
	cin >> count;

	wine = new int[count];
	for (int i = 0; i < count; i++) {
		cin >> wineNum;
		wine[i] = wineNum;
	}

	cout << Cal_Max(count) << endl;
	return 0;
}