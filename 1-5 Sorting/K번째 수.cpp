//메모리 : 40244 , 시간: 1644ms
#include <stdio.h>
#include <algorithm>
using namespace std;

int main() {
	int input_count, select_index;
	scanf("%d", &input_count);
	scanf("%d", &select_index);

	int *arr = new int[input_count];
	for (int i = 0; i < input_count; i++) {
		int input;
		scanf("%d", &input);
		arr[i] = input;
	}
	stable_sort(arr, arr + input_count);
	for (int i = 0; i < select_index; i++) {
		if (i == select_index - 1)
			printf("%d\n", arr[i]);
	}
	return 0;
}