//메모리 : 1988kb, 시간 : 4ms

#include <iostream>
#include <string>
using namespace std;
int main()
{
	int cnt;
	cin >> cnt;

	for (int i = 0; i < cnt; i++)
	{
		string input;
		cin >> input;

		bool check = true;
		int r_num = 0;
		int l_num = 0;
		for (int j = 0; j < input.size(); j++)
		{
			if (input[j] == '(')
				r_num++;
			else if (input[j] == ')')
				l_num++;

			if (r_num < l_num)
			{
				cout << "NO" << endl;
				check = false;
				break;
			}
		}

		if (check && r_num != l_num)
			cout << "NO" << endl;
		else if (check)
			cout << "YES" << endl;
	}
}