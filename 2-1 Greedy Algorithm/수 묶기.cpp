#include <iostream>
#include <algorithm>
using namespace std;
int* arr;
long long calMax(int num) {
	long long result = 0;
	if (num == 1)
		return arr[0];
	//음수
	for (int i = 0; i < num -1 ;i++) {
		if (arr[i] < 0 && arr[i + 1] <= 0) {
			result += (arr[i] * arr[i + 1]);
			arr[i] = 0;
			arr[i + 1] = 0;
			i++;
		}
		else
			break;
	}

	//양수
	for (int j = num-1; j >0; j--) {
		if (arr[j] > 0 && arr[j-1] > 1) {
			result += (arr[j] * arr[j - 1]);
			arr[j] = 0;
			arr[j - 1] = 0;
			j--;
		}
		else
			break;
	}
	//남은 수들
	for (int i = 0; i < num; i++) {
		result += arr[i];
	}
	return result;
}
int main() {
	int num;
	cin >> num;
	arr = new int[num];
	for (int i = 0; i < num; i++) {
		int input;
		cin >> input;
		arr[i] = input;
	}
	sort(arr, arr + num);
	cout << calMax(num) << endl;
}