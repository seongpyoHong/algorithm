//스택  메모리 :2236kb , 시간: 4ms
#include <iostream>
#include <string>
#include <stack>
using namespace std;
int main()
{
	string input;
	cin >> input;
	stack<int> st;

	int index = -1;
	int sum = 0;
	for (int i = 0; i < input.size(); i++)
	{

		if (input[i] == '(' && input[i + 1] == ')')
		{
			sum += st.size();
		}

		else if (input[i] == '(' && input[i + 1] != ')')
			st.push(1);

		else if (input[i] == ')' && input[i - 1] != '(')
		{
			sum += 1;
			st.pop();
		}
	}
	cout << sum << endl;
}