#include <iostream>
#include <vector>
using namespace std;
int islandCount = 2;
int row, col;

void findIsland(int** arr, int x, int y) {
	if (x < 0 || x >= col || y < 0 || y >= row)
		return;

	if (arr[x][y] != 1) {
		return;
	}
	else if (arr[x][y] == 1) {
		arr[x][y] = islandCount;
		findIsland(arr, x, y + 1);
		findIsland(arr, x, y - 1);
		findIsland(arr, x + 1, y);
		findIsland(arr, x - 1, y);
		findIsland(arr, x + 1, y + 1);
		findIsland(arr, x - 1, y + 1);
		findIsland(arr, x + 1, y - 1);
		findIsland(arr, x - 1, y - 1);
	}

}
int main() {
	bool exit = false;

	while (!exit) {
		cin >> row >> col;
		if (row == 0 && col == 0)
			break;
		int **arr = new int*[col];
		for (int i = 0; i < col; i++) {
			arr[i] = new int[row];
		}

		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				int a;
				cin >> a;
				arr[i][j] = a;
			}
		}

		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				if (arr[i][j] == 1) {
					findIsland(arr, i, j);
					islandCount++;
				}
			}
		}

		for (int i = 0; i < col; i++)
			delete[] arr[i];
		delete[] arr;

		cout << islandCount - 2 << endl;
		islandCount = 2;
	}
	system("pause");
	return 0;
}