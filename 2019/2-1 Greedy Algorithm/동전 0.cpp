//1988kb, 0ms
#include <iostream>
using namespace std;

void coinCnt(long long& total_price,int* coin,long long & cnt,int coin_num) {
	if (total_price == 0)
		return;
	for (int i = 0; i < coin_num; i++) {
		if (coin[i] > total_price) {
			int ofCnt = total_price / coin[i-1];
			cnt += ofCnt;
			total_price -= (coin[i-1] * ofCnt);
			coinCnt(total_price, coin, cnt, coin_num);
			break;
		}
		else if (i == coin_num - 1) {
			int ofCnt = total_price / coin[i];
			cnt += ofCnt;
			total_price -= (coin[i] * ofCnt);
			coinCnt(total_price, coin, cnt, coin_num);
			break;
		}
	}
}

int main() {
	int coin_num;
	long long total;
	cin >> coin_num >> total;
	int* coin = new int[coin_num];
	for (int i = 0; i < coin_num; i++) {
		cin >> coin[i];
	}
	long long cnt = 0;
	coinCnt(total, coin, cnt,coin_num);
	cout << cnt << endl;
	system("pause");
}