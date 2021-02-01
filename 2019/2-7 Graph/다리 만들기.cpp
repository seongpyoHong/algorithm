#include <iostream>
#include <vector>
#include <queue>
#include <utility>
#include <algorithm>
using namespace std;

bool **visit;
int **map;
int sectorNum = 2;

void clearVisit(int n) {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (visit[i][j])
				visit[i][j] = false;
		}
	}
}

int bfs(int n, int secNum) {
	queue<pair<int, int> > q;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (map[i][j] == secNum) {
				visit[i][j] = true;
				pair<int, int > temp(i, j);
				q.push(temp);
			}
		}
	}

	int distance = 0;

	while (!q.empty()) {
		int q_size = q.size();
		for (int i = 0; i <q_size; i++) {
			pair<int, int> current = q.front();
			q.pop();

			if (current.first - 1 >= 0) {
				if (map[current.first - 1][current.second] != 0 && map[current.first - 1][current.second] != secNum) {
					return distance;
				}
				else if (!visit[current.first - 1][current.second]) {
					visit[current.first - 1][current.second] = true;
					pair <int, int > next(current.first - 1, current.second);
					q.push(next);
				}
			}

			if (current.first + 1 <n) {
				if (map[current.first + 1][current.second] != 0 && map[current.first + 1][current.second] != secNum) {
					return distance;
				}
				else if (!visit[current.first + 1][current.second]) {
					visit[current.first + 1][current.second] = true;
					pair <int, int > next(current.first + 1, current.second);
					q.push(next);
				}
			}

			if (current.second - 1 >= 0) {
				if (map[current.first][current.second - 1] != 0 && map[current.first][current.second - 1] != secNum) {
					return distance;
				}
				else if (!visit[current.first][current.second - 1]) {
					visit[current.first][current.second - 1] = true;
					pair <int, int > next(current.first, current.second - 1);
					q.push(next);
				}
			}

			if (current.second + 1 <n) {
				if (map[current.first][current.second + 1] != 0 && map[current.first][current.second + 1] != secNum) {
					return distance;
				}
				else if (!visit[current.first][current.second + 1]) {
					visit[current.first][current.second + 1] = true;
					pair <int, int > next(current.first, current.second + 1);
					q.push(next);
				}
			}
		}
		distance++;
	}
}

void sector(int x, int y, int n) {
	if (x < 0 || y < 0 || x >= n || y >= n)
		return;
	if (map[x][y] == 0 || visit[x][y])
		return;
	else {
		visit[x][y] = true;
		map[x][y] = sectorNum;
		sector(x - 1, y, n);
		sector(x, y + 1, n);
		sector(x + 1, y, n);
		sector(x, y - 1, n);
	}
}

int main() {
	int N;
	cin >> N;
	//동적 할당
	map = new int*[N];
	visit = new bool*[N];
	for (int i = 0; i < N; i++) {
		map[i] = new int[N];
		visit[i] = new bool[N];
	}

	//입력
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> map[i][j];
			visit[i][j] = false;
		}
	}

	//섬 구분
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (map[i][j] == 1) {
				sector(i, j, N);
				sectorNum++;
			}
		}
	}


	//BFS 가장 작은값 검색
	int result = N * N;
	for (int i = 2; i <sectorNum; i++) {
		clearVisit(N);
		result = min(result, bfs(N, i));
	}

	cout << result << endl;
	system("pause");
	return 0;
}