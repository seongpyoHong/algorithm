#include <iostream>
#include <cmath>
#include <cstdio>

using namespace std;

int MyCal(int input) {
	int cnt_two = 0;
	int cnt_five = 0;
	int result = 0;
	for (int i = input; i > 0; i--) {
		int temp = i;
		if (temp % 5 == 0 || temp % 2 == 0) {
			while (1) {
				if (temp % 5 == 0) {
					cnt_five++;
					temp /= 5;
				}
				if (temp % 2 == 0) {
					cnt_two++;
					temp /= 2;
				}

				if (temp % 5 != 0 && temp % 2 != 0)
					break;
			}
		}
	}
	return cnt_five;
}
int main() {
	int input;
	cin >> input;
	cout << MyCal(input) << endl;

	system("pause");
}