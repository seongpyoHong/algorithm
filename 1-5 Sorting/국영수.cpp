#메모리 : 11380kb, 시간 : 136ms
#include <iostream>
#include <stdio.h>
#include <algorithm>
#include <string>
using namespace std;
struct Point
{
	string name;
	int kor;
	int eng;
	int math;
};
bool compare(const Point &p1, const Point &p2) {
	if (p1.kor> p2.kor) {
		return true;
	}
	else if (p1.kor == p2.kor) {
		if (p1.eng < p2.eng)
			return true;
		else if (p1.eng == p2.eng) {
			if (p1.math > p2.math)
				return true;
			else if (p1.math == p2.math) {
				return p1.name < p2.name;
			}
			else
				return false;
		}
		else
			return false;
	}
	else {
		return false;
	}
}
int main() {
	int input_count;
	scanf("%d", &input_count);
	Point* arr = new Point[input_count + 1];
	for (int i = 0; i < input_count; i++) {
		int in_kor, in_math, in_eng;
		string in_name;
		cin >> in_name;
		scanf("%d", &in_kor);
		scanf("%d", &in_eng);
		scanf("%d", &in_math);
		arr[i].kor = in_kor;
		arr[i].math = in_math;
		arr[i].eng = in_eng;
		arr[i].name = in_name;
	}

	stable_sort(arr, arr + input_count, compare);

	for (int i = 0; i < input_count; i++) {
		printf("%s \n", arr[i].name.c_str());
	}

	return 0;
}