#include <iostream>
#include <string>
#include <cmath>
using namespace std;
int main()
{
	string input;
	int zin;
	cin >> input >> zin;
	int sum = 0;

	for (int i = 0; i<input.length(); i++) {
		if ('0' <= input[i] && input[i] <= '9') {
			sum += (input[i] - '0') *pow(zin, input.length() - i - 1);
		}
		else if ('A' <= input[i] && input[i] <= 'Z') {
			sum += (input[i] - 'A' + 10) * pow(zin, input.length() - i - 1);
		}
	}
	cout << sum << endl;
}