#include <iostream>
using namespace std;
long long gcd(int *arr, int size) {

	long long  sum_gcd = 0;
	for (int i = 0; i < size; i++) {
		for (int j = i + 1; j < size; j++) {
			int a = arr[i];
			int b = arr[j];
			while (b != 0) {
				int c = a % b;
				a = b;
				b = c;
			}
			sum_gcd += a;
		}
	}
	return sum_gcd;
}


int main()
{
	int count;
	cin >> count;
	for (int i = 0; i<count; i++) {
		int input_count;
		cin >> input_count;
		int *input = new int[input_count];
		for (int j = 0; j < input_count; j++)
			cin >> input[j];
		cout << gcd(input, input_count) << endl;
	}

}