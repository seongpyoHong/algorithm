//메모리 : 1988kb, 시간 : 224ms
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
	int testNum;
	cin >> testNum;
	for (int i = 0; i < testNum; i++) {
		int pointNum;
		cin >> pointNum;
		bool* check = new bool[pointNum + 1];
		fill(check, check + pointNum + 1, false);
		vector<int>* cache = new vector<int>[pointNum + 1];
		for (int j = 1; j <= pointNum; j++) {
			int a;
			cin >> a;
			cache[j].push_back(a);
			cache[a].push_back(j);
		}

		int partition = 1;
		dfs(1, cache, check);

		for (int index = 1; index <= pointNum; index++) {
			if (!check[index]) {
				dfs(index, cache, check);
				partition++;
			}
		}

		cout << partition << endl;
		delete[] check;
		delete[] cache;
	}
	system("pause");
}