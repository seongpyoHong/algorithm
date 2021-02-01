#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <queue>
using namespace std;
struct point {
	int n;
	int m;
};
vector<point>* aroundArr;
string *input;
bool **check;
int **result;
int N_max;
int M_max;

void bfs(int startN, int startM) {
	point temp;
	check[startN][startM] = true;
	temp.n = startN;
	temp.m = startM;
	result[startN][startM] = 1;
	queue<point> q;
	q.push(temp);

	while (!q.empty()) {

		point current = q.front();
		int currentIndex = ((M_max + 1) * q.front().n) + q.front().m;
		q.pop();
		for (int i = 0; i < aroundArr[currentIndex].size(); i++) {
			point next = aroundArr[currentIndex][i];
			if (check[next.n][next.m] != true) {
				check[next.n][next.m] = true;
				result[next.n][next.m] = result[current.n][current.m] + 1;
				q.push(next);

				if (next.n == N_max && next.m == M_max)
					return;
			}
		}
	}
}

int main() {
	int N, M;
	cin >> N >> M;
	input = new string[N];
	N_max = N - 1;
	M_max = M - 1;
	for (int i = 0; i < N; i++)
		cin >> input[i];

	//동적할당
	check = new bool*[N];
	result = new int*[N];
	aroundArr = new vector<point>[N*M];
	for (int i = 0; i < N; i++) {
		check[i] = new bool[M];
		result[i] = new int[M];
	}


	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			check[i][j] = false;
			result[i][j] = 0;
			point temp;
			if (i + 1 < N && input[i + 1][j] == '1') {
				temp.n = i + 1;
				temp.m = j;
				aroundArr[(M * i) + j].push_back(temp);
			}
			if (i - 1 >= 0 && input[i - 1][j] == '1') {
				temp.n = i - 1;
				temp.m = j;
				aroundArr[(M * i) + j].push_back(temp);
			}
			if (j + 1 < M && input[i][j + 1] == '1') {
				temp.n = i;
				temp.m = j + 1;
				aroundArr[(M * i) + j].push_back(temp);
			}
			if (j - 1 >= 0 && input[i][j - 1] == '1') {
				temp.n = i;
				temp.m = j - 1;
				aroundArr[(M * i) + j].push_back(temp);
			}
		}
	}
	bfs(0, 0);
	cout << result[N - 1][M - 1] << endl;
	return 0;
}