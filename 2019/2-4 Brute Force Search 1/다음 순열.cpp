#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;
int* arr;

void swap(int* arr, int i, int j) {
	int temp = arr[i];
	arr[i] = arr[j];
	arr[j] = temp;
}

int checkDecre(int* arr, int index, int size) {
	for (int i = index; i < size - 1; i++) {
		if (arr[i] < arr[i + 1])
			return i;
	}
	return -1;
}

void nextPerm(int* arr, int size) {
	int idx_i = 0;
	int idx_j = 0;
	if (checkDecre(arr, 0, size) == -1) {
		cout << -1 << endl;
		return;
	}
	for (int i = 0; i < size; i++) {
		idx_i = max(idx_i, checkDecre(arr, i, size));
	}
	for (int i = idx_i + 1; i < size; i++) {
		if (arr[idx_i] < arr[i])
			idx_j = max(idx_j, i);
	}
	swap(arr, idx_i, idx_j);

	for (int i = idx_i + 1; i < size; i++) {
		if (i > size + idx_i - i)
			break;
		swap(arr, i, size - i + idx_i);
	}

	for (int i = 0; i < size; i++) {
		cout << arr[i] << " ";
	}
	cout << endl;
}

int main() {
	int size;
	cin >> size;
	arr = new int[size];
	for (int i = 0; i < size; i++) {
		cin >> arr[i];
	}
	nextPerm(arr, size);
	return 0;
}