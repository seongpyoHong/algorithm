#include <iostream>
#include <cmath>
using namespace std;

//전역변수 선언
int result[25];

void convert(int count, int A, int B) {

	//10진법으로 변환한 값을 저장할 변수
	int sum = 0;

	//A진법 수를 10진법으로 변환
	for (int i = 0; i<count; i++) {
		sum += (result[i] * pow(A, i));
		result[i] = -1;
	}

	int index = 0;
	//10진법 수를 B진법으로 변환
	while (sum>0) {
		result[index] = sum % B;
		sum /= B;
		index++;
	}
	return;
}
int main() {
	int A, B, count;
	cin >> A >> B;
	cin >> count;

	//배열 초기화
	for (int i = 0; i<25; i++) {
		result[i] = -1;
	}

	//입력값 배열에 뒤에서 부터 삽입
	for (int i = count - 1; i >= 0; i--) {
		int number;
		cin >> number;
		result[i] = number;
	}

	//진법 변환 
	convert(count, A, B);

	//변환된 수의 자리수 계산
	int size = sizeof(result) / sizeof(int) + 1;

	//거꾸로 출력
	for (int i = size - 2; i >= 0; i--) {
		if (result[i] != -1)
			cout << result[i] << " ";
	}
	cout << endl;
	return 0;
}