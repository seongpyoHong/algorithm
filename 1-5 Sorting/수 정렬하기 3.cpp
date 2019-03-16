// 메모리 1988 KB, 시간 2456 ms
#include <stdio.h>
#include <iostream>
using namespace std;

int main()
{
	int arr[10001] = { 0 };
	int count, index;
	scanf_s("%d", &count);
	for (int i = 1; i <= count; i++)
	{
		scanf_s("%d", &index);
		arr[index]++;
	}
	for (int i = 1; i <= 10000; i++)
	{
		if (arr[i] != 0)
		{
			for (int j = 0; j < arr[i]; j++)
				printf("%d\n", i);
		}
	}
	return 0;
}