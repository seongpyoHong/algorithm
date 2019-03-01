//메모리 : 1988	kb, 시간 : 0ms
#include <iostream>
#include <string>
using namespace std;
int main() {
	int arr[26];
	for (int i = 0; i < 26; i++) {
		arr[i] = -1;
	}
	string input;
	cin >> input;

	for (int i = 0; i < input.size(); i++) {
		input[i] -= 'a';
		if (arr[int(input[i])] == -1)
			arr[int(input[i])] = i;
	}
	for (int j = 0; j < 26; j++)
	{
		cout << arr[j] << ' ';
	}
	cout << endl;
	system("pause");
}