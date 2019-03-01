#include <iostream>
using namespace std;

int cache[12] = { 0,1,2,4,0 };
int MyCal(int input) {

	if (cache[input] != 0)
		return cache[input];

	cache[input] = MyCal(input - 3) + MyCal(input - 2) + MyCal(input - 1);
	return cache[input];
}

int main() {
	int count;
	cin >> count;
	for (int i = 0; i < count; i++) {
		int number = 0;
		cin >> number;
		cout << MyCal(number) << endl;
	}
}