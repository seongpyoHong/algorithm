#include <iostream>
#include <algorithm>
using namespace std;
int main() {
	int M, N, K;
	int team_cnt = 0;
	bool flag = false;
	cin >> N >> M >> K;

	int init_K = K;
	
	if (N < 2 * M) {
		K -= (M - (N / 2));
		team_cnt = N / 2;
		if (K <= 0) {
			cout << team_cnt << endl;
			flag = true;
		}
	}
	
	else if (N > 2 * M) {
		K -= (N - (2 * M));
		team_cnt = M;
		if (K <= 0) {
			cout << team_cnt << endl;
			flag = true;
		}
	}
	if(!flag){
		team_cnt = 0;
		while (1) {
			if (N + M - init_K < 3 || N < 2 || M < 1)
				break;
			N -= 2;
			M -= 1;
			team_cnt++;
		}
		cout << team_cnt << endl;
	}
}