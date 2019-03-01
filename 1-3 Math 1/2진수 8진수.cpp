#include <iostream>
#include <string>
#include <cmath>
#include <algorithm>
using namespace std;
string Convert(string bin) {
	int length = bin.length();
	int two_num = 0;
	int temp = 0;
	string result = "";

	for (int i = length - 1; i >= 0; i--) {
		temp += (bin[i] - '0') * pow(2, two_num);
		two_num++;
		if (two_num % 3 == 0 || i == 0) {
			result += char(temp + '0');
			temp = 0;
			two_num = 0;
		}
	}

	reverse(result.begin(), result.end());
	return result;
}
int main()
{
	string input;
	cin >> input;
	cout << Convert(input) << endl;

	return 0;
}