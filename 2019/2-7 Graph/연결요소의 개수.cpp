//메모리 : 6344kb, 시간 : 240ms
#include <iostream>
#include <vector>
#include <stack>
#include <queue>
#include <algorithm>
using namespace std;

void dfs(int start, vector<int>* cache, bool* check) {
	check[start] = true;
	for (int i = 0; i < cache[start].size(); i++) {
		int next = cache[start][i];
		if (!check[next])
			dfs(next, cache, check);
	}
}

int main() {
	int pointNum;
	int edgeNum;
	int partition = 1;
	cin >> pointNum >> edgeNum;

	bool* check = new bool[pointNum + 1];
	fill(check, check + pointNum + 1, false);

	vector<int>* cache = new vector<int>[pointNum + 1];
	for (int i = 1; i <= edgeNum; i++) {
		int a, b;
		cin >> a >> b;
		cache[a].push_back(b);
		cache[b].push_back(a);
	}

	dfs(1, cache, check);

	for (int i = 2; i <= pointNum; i++) {
		if (!check[i]) {
			dfs(i, cache, check);
			partition++;
		}
	}

	cout << partition << endl;
	system("pause");
	return 0;
}