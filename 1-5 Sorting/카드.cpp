// 메모리 5120 KB, 시간 80 ms
#include <stdio.h>
#include <iostream>
#include <algorithm>
using namespace std;
struct Card
{
	long long id = -1;
	int cnt = 0;
};
bool compare(const Card &p1, const Card &p2) {
	if (p1.cnt> p2.cnt) {
		return true;
	}
	else if (p1.cnt == p2.cnt) {
		return p1.id < p2.id;
	}
	else {
		return false;
	}
}
int main()
{
	int count;
	long long index;
	scanf("%d", &count);
	Card* arr = new Card[count];

	for (int i = 0; i < count; i++)
	{
		bool chk = false;
		int j;
		scanf("%lld", &index);
		for (j = 0; j < count; j++) {
			if (arr[j].id == -1) {
				break;
			}
			else if (arr[j].id == index) {
				arr[j].cnt++;
				chk = true;
				break;
			}
		}
		if (!chk) {
			arr[j].id = index;
			arr[j].cnt++;
		}
	}
	stable_sort(arr, arr + count, compare);

	cout << arr[0].id << endl;
	return 0;
}