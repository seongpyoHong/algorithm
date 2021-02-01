//메모리 :2772kb, 시간 :24ms
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main()
{
	int n;
	scanf("%d", &n);
	//2차원 벡터 생성
	vector <pair<int, int>> a(n);

	//입력받으면서 초기의 인덱스 값 저장
	for (int i = 0; i<n; i++)
	{
		scanf("%d", &a[i].first);
		a[i].second = i;
	}

	//정렬
	sort(a.begin(), a.end());
	//가장 많이 움직인 횟수를 저장할 변수
	int ans = 0;

	//ans를 최대값으로 계속해서 갱신
	for (int i = 0; i<n; i++)
	{
		if (ans < a[i].second - i)
			ans = a[i].second - i;
	}

	//움직인 횟수 +1 = 버블이 실행된 횟수
	printf("%d", ans + 1);
	return 0;
}