#include <iostream>
using namespace std;

int MyCal(int input) {
	long long result = 1;
	for (int i = input; i>0; i--) {
		result *= i;
	}
	return result;
}
int main() {
	int input;
	cin >> input;

	cout << MyCal(input) << endl;
	return 0;
}