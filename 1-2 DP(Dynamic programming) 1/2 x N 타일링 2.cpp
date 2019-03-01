#include <iostream>
using namespace std;

int cache[1001] = { 1,1,0 };
int MyCal(int input) {

	if (input == 1)
		return 1;

	if (cache[input] != 0)
		return cache[input];

	cache[input] = (2 * MyCal(input - 2) + MyCal(input - 1)) % 10007;

	return cache[input];
}

int main() {
	int number;
	cin >> number;
	cout << MyCal(number) << endl;
}