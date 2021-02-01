// 메모리 2000 KB, 시간 0 ms
#include <algorithm>
#include <iostream>
using namespace std;

int numseq[1001] = { 0 }; // 사용자 입력 수열 값
int dp[1001][2] = { 0 };
// dp를 두가지로 나눈다 0은 해당 위치에서 만들어지는 최대 수열 길이
// 1은 그 수열의 최대값 (현재 값) 저장
void partseq(int input)
{
	int maximum = 1;
	dp[1][0] = 1;
	dp[1][1] = numseq[1];
	dp[1][2] = numseq[1]; // 초기화

	for (int i = 2; i <= input; i++) // 사용자 인풋만큼 반복
	{
		for (int j = i - 1; j > 0; j--) // 이전 인덱스들만큼 비교한다
		{
			if (dp[j][2] < numseq[i] && dp[j][0] >= dp[i][0])
			{ // 앞쪽인덱스 중 수열 길이가 현재보다 길고 해당 값보다 작은 경우 
			  // 즉 수열이 만들어질 수 있는 경우 새로운 수열을 만들어낸다.
				dp[i][1] = dp[j][1]; // 최대값에 현재의 수를 넣어준다
				dp[i][0] = dp[j][0] + 1; // 길이는 이어붙이려는 수열의 길이 + 1
				maximum = max(dp[i][0], maximum); // 최대길이는 새로운 수열이 생길때마다 갱신
			}
		}
		if (dp[i][0] == 0) // 앞쪽과 전부 비교 이후 dp[i][0]이 0인 경우 (수열이 안만들어진 경우)
		{ // 길이가 1이고 최소, 최대값을 해당인덱스로하는 새로운 수열을 만들어냄
			dp[i][0] = 1;
			dp[i][1] = numseq[i];
		}
	}
	cout << maximum;
}
// 통상적인 메인함수
int main()
{
	int num;
	cin >> num;
	for (int i = 1; i <= num; i++)
	{
		cin >> numseq[i];
	}
	partseq(num);
	return 0;
}