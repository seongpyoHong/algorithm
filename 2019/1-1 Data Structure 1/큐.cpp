//메모리 : 1988kb, 시간: 328ms

#include <iostream>
#include <string>
using namespace std;
class myQueue {
private:
	int *arr;
	int m_front;
	int m_back;
	int m_size;
public:
	myQueue() {}
	myQueue(int x) {
		arr = new int[x];
		m_front = 0;
		m_back = -1;
		m_size = 0;
	}
	void push(int x) {
		arr[++m_back] = x;
		m_size++;
	}
	int pop() {
		if (empty() == 0) {
			int tmp = front();
			m_front++;
			m_size--;
			return tmp;
		}
		else {
			m_front = 0;
			m_back = -1;
			return -1;
		}
	}
	int size() {
		return m_size;
	}
	int empty() {
		if (m_size == 0)
			return 1;
		else
			return 0;
	}
	int front() {
		if (empty() == 0)
			return arr[m_front];
		else
			return -1;
	}
	int back() {
		if (empty() == 0)
			return arr[m_back];
		else
			return -1;
	}
};

void printInt(int x) {
	cout << x << endl;
}

int main() {
	int cnt;
	cin >> cnt;
	myQueue q(cnt);
	for (int i = 0; i < cnt; i++) {
		string input;
		cin >> input;
		if (input == "push") {
			int num;
			cin >> num;
			q.push(num);
		}
		else if (input == "pop") {
			printInt(q.pop());
		}
		else if (input == "size") {
			printInt(q.size());
		}
		else if (input == "front") {
			printInt(q.front());
		}
		else if (input == "back") {
			printInt(q.back());
		}
		else if (input == "empty") {
			printInt(q.empty());
		}
	}
}