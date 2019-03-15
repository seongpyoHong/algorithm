#메모리 : 9816kb, 시간 : 80ms
#include <iostream>
#include <stdio.h>
#include <algorithm>
#include <string>
using namespace std;
struct Point
{
	string name;
	int index;
	int age;
};
bool compare(const Point &p1, const Point &p2) {
	if (p1.age< p2.age) {
		return true;
	}
	else if (p1.age == p2.age) {
		return p1.index < p2.index;
	}
	else {
		return false;
	}
}
int main() {
	int input_count;
	scanf_s("%d", &input_count);
	Point* arr = new Point[input_count + 1];
	for (int i = 0; i < input_count; i++) {
		int in_age;
		string in_name;
		scanf_s("%d", &in_age);
		cin >> in_name;
		arr[i].age = in_age;
		arr[i].name = in_name;
		arr[i].index = i;
	}

	stable_sort(arr, arr + input_count, compare);

	for (int i = 0; i < input_count; i++) {
		printf("%d %s\n", arr[i].age, arr[i].name.c_str());
	}

	system("pause");
	return 0;
}