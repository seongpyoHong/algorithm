//메모리 :  2176kb , 시간 : 0ms
#include <iostream>
#include <string>
#include <cmath>
using namespace std;
int main() {
	string a, b, c, d;
	long long sum1 = 0;
	cin >> a >> b >> c >> d;

	int Int_a = 0;
	for (int i = 0; i < a.size(); i++) {
		Int_a += (int(a[i] - '0')*pow(10, a.size() - i - 1));
	}

	int Int_b = 0;
	for (int i = 0; i < b.size(); i++) {
		Int_b += (int(b[i] - '0')*pow(10, b.size() - i - 1));
	}

	int Int_c = 0;
	for (int i = 0; i < c.size(); i++) {
		Int_c += (int(c[i] - '0')*pow(10, c.size() - i - 1));
	}

	int Int_d = 0;
	for (int i = 0; i < d.size(); i++) {
		Int_d += (int(d[i] - '0')*pow(10, d.size() - i - 1));
	}

	sum1 += Int_b;
	sum1 += Int_d;
	sum1 += Int_a * pow(10, b.size());
	sum1 += Int_c * pow(10, d.size());

	cout << sum1 << endl;
	system("pause");
}