//메모리 : 1992kb, 시간: 320ms
#include <iostream>
#include <string>
using namespace std;

class myStack
{
public:
	int *arr;
	int index;

	myStack(int a)
	{
		arr = new int[a];
		index = -1;
	}
	~myStack()
	{
	}
	void push(int x)
	{
		index++;
		if (index != 10000)
			arr[index] = x;
	}
	void pop()
	{
		if (index > -1)
		{
			cout << arr[index] << endl;
			index--;
		}
		else
			cout << -1 << endl;
	}
	void size()
	{
		cout << index + 1 << endl;
	}
	void empty()
	{
		if (index == -1)
			cout << 1 << endl;
		else
			cout << 0 << endl;
	}
	void top()
	{
		if (index == -1)
			cout << -1 << endl;
		else
			cout << arr[index] << endl;
	}
};

int main()
{
	int input;
	cin >> input;
	myStack A(input);
	for (int i = 0; i < input; i++)
	{
		string select;
		int number = 0;
		cin >> select;
		if (select == "push")
		{
			cin >> number;
			A.push(number);
		}

		else if (select == "pop")
			A.pop();

		else if (select == "top")
			A.top();

		else if (select == "empty")
			A.empty();

		else if (select == "size")
			A.size();
	}
}