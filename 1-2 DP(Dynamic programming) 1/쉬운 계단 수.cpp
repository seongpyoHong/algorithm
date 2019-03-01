#include <iostream>
#include <algorithm>
using namespace std;

int cache[101][10];

//배열 초기화
void Init_Arr() {
	for (int i = 1; i < 101; i++) {
		for (int j = 0; j < 10; j++) {
			if (i == 1 && j != 0)
				cache[i][j] = 1;
			else
				cache[i][j] = 0;
		}
	}
}

//계단수 계산
int Cal_Stair(int cnt) {

	for (int i = 2; i <= cnt; i++) {
		for (int j = 0; j <10; j++) {
			if (j + 1 < 10)
				cache[i][j] = (cache[i][j] + cache[i - 1][j + 1]) % 1000000000;
			if (j - 1 >= 0)
				cache[i][j] = (cache[i][j] + cache[i - 1][j - 1]) % 1000000000;
		}
	}

	//합 계산
	int sum = 0;
	for (int i = 0; i<10; i++)
		sum = (sum + cache[cnt][i]) % 1000000000;
	return sum % 1000000000;
}

int main() {
	int count;
	cin >> count;
	Init_Arr();
	cout << Cal_Stair(count) << endl;
}