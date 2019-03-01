#include <iostream>
using namespace std;

int result = 100000;
int myCal(int x, int cnt) {

	if (cnt > result)
		return result;
	if (x == 1) {
		if (result>cnt)
			result = cnt;
		return result;
	}

	if (x % 3 == 0)
		myCal(x / 3, cnt + 1);
	if (x % 2 == 0)
		myCal(x / 2, cnt + 1);
	myCal(x - 1, cnt + 1);
}
int main() {
	int x;
	cin >> x;

	int count = 0;
	count = myCal(x, count);

	cout << count << endl;
}