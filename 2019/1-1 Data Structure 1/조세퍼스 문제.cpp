// 메모리 : 1988KB, 시간 :92ms

#include <iostream>
#include <queue>
using namespace std;
int main() {
	queue<int> q;
	int N, M;
	cin >> N >> M;
	for (int i = 1; i <= N; i++) {
		q.push(i);
	}
	cout << '<';
	int cnt = 0;
	while (q.size() != 1) {
		cnt++;
		if (cnt == M) {
			cout << q.front() << ", ";
			q.pop();
			cnt = 0;
		}
		else {
			q.push(q.front());
			q.pop();
		}
	}
	cout << q.front() << '>' << endl;
}