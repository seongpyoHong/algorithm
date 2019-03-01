//메모리 : 1988kb, 시간 : 4ms
#include <iostream>
#include <string>
using namespace std;
int main() {
	string input;
	while (getline(cin, input)) {
		int small_cnt = 0;
		int big_cnt = 0;
		int num_cnt = 0;
		int blank_cnt = 0;

		for (int i = 0; i < input.size(); i++) {
			if (input[i] == ' ')
				blank_cnt++;
			else if (input[i] - 'A' >= 0) {
				if (input[i] - 'a' >= 0)
					small_cnt++;
				else
					big_cnt++;
			}
			else
				num_cnt++;
		}
		cout << small_cnt << ' ' << big_cnt << ' ' << num_cnt << ' ' << blank_cnt << endl;
	}
}