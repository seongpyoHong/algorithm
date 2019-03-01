//메모리 : 3940kb , 시간 : 252ms
#include <iostream>
#include <algorithm>
using namespace std;
int cache[100001][3] = { 0 };
int arr[2][100000] = { 0 };
void InitArr(int sticknum) {
	for (int i = 0; i < 2; i++) {
		for (int j = 0; j < sticknum; j++) {
			int input;
			cin >> input;
			arr[i][j] = input;
		}
	}
}
int Cal_Max(int count) {

	cache[1][0] = 0;
	cache[1][1] = arr[0][0];
	cache[1][2] = arr[1][0];

	for (int i = 2; i <= count; i++) {
		int tmp_max = max(cache[i - 1][0], max(cache[i - 1][1], cache[i - 1][2]));
		cache[i][0] = tmp_max;
		cache[i][1] = max(cache[i - 1][0], cache[i - 1][2]) + arr[0][i - 1];
		cache[i][2] = max(cache[i - 1][0], cache[i - 1][1]) + arr[1][i - 1];
	}
	return max(cache[count][0], max(cache[count][1], cache[count][2]));
}
int main() {
	int testNum;
	cin >> testNum;
	for (int i = 0; i < testNum; i++) {
		int stickNum;
		cin >> stickNum;
		InitArr(stickNum);
		cout << Cal_Max(stickNum) << endl;
	}
}