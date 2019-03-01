#include <iostream>
#include <algorithm>
using namespace std;

int cache[1001][10];

//배열 초기화
void Init_Arr() {
	for (int i = 1; i < 101; i++) {
		for (int j = 0; j < 10; j++) {
			if (i == 1)
				cache[i][j] = 1;
			else
				cache[i][j] = 0;
		}
	}
}

int Cal_Upper(int cnt) {
	for (int i = 2; i <= cnt; i++) {
		for (int j = 0; j <10; j++) {
			if (j == 0)                                      //0은 직전 숫자가 0일 때만 가능 
				cache[i][j] = (cache[i][j] + cache[i - 1][j]) % 10007;
			else                                             //나머지는 (Number-1)이 가능한 경우 + 직전 자리와 동일한 경우     
				cache[i][j] = (cache[i][j] + cache[i - 1][j] + cache[i][j - 1]) % 10007;
		}
	}

	//합 계
	int sum = 0;
	for (int i = 0; i<10; i++) {
		sum = (sum + cache[cnt][i]) % 10007;
	}
	return sum % 10007;
}

int main() {
	int count;
	cin >> count;

	Init_Arr();
	cout << Cal_Upper(count) << endl;
}