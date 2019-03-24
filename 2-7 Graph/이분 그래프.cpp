//메모리 : 19928KB , 시간 : 676ms
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;

bool flag = true;

void dfs(int start, vector<int>* cache, int* check, int type) {
	type = -type;
	check[start] = type;

	for (int i = 0; i < cache[start].size(); i++) {
		int next = cache[start][i];
		if (check[next] == check[start]) {
			flag = false;
			return;
		}
		else if (check[next] == 0) {
			dfs(next, cache, check, type);
		}
	}
}

int main() {
	int testNum, pointNum, edgeNum;
	cin >> testNum;

	for (int i = 0; i < testNum; i++) {
		cin >> pointNum >> edgeNum;
		int* check = new int[pointNum + 1];
		fill(check, check + pointNum + 1, 0);
		vector<int>* cache = new vector<int>[pointNum + 1];

		for (int j = 0; j < edgeNum; j++) {
			int a, b;
			cin >> a >> b;
			cache[a].push_back(b);
			cache[b].push_back(a);
		}

		for (int i = 1; i <= pointNum; i++)
		{
			if (check[i] == 0)
				dfs(i, cache, check, 1);
		}

		if (flag)
			cout << "YES" << endl;
		else
			cout << "NO" << endl;

		flag = true;
	}
	system("pause");
	return 0;
}