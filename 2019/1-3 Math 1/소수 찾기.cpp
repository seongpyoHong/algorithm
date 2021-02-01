#include <iostream>
using namespace std;
int gCount = 0;
bool Cal_Primary(int num) {
	int flag = false;
	for (int i = num; i>0; i--) {
		int mod = num % i;
		if (mod == 0)
			gCount++;
	}

	if (gCount == 2)
		flag = true;

	gCount = 0;
	return flag;
}

int main() {
	int count;
	cin >> count;

	int result = 0;
	for (int i = 0; i<count; i++) {
		int number;
		cin >> number;
		if (Cal_Primary(number))
			result++;
	}
	cout << result << endl;
	return 0;
}