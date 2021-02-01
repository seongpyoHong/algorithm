//메모리 : 9196 , 시간: 412ms
#include <stdio.h>
#include <algorithm>
#include <vector>
using namespace std;

int main() {
	int input_count;
	scanf_s("%d", &input_count);
	vector<int> arr;
	for (int i = 0; i < input_count; i++) {
		int input;
		scanf_s("%d", &input);
		arr.push_back(input);
	}
	stable_sort(arr.begin(), arr.end());
	for (int i = 0; i < input_count; i++) {
		printf("%d\n", arr[i]);
	}
	return 0;
}