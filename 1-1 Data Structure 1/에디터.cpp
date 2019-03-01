//메모리 20844kb , 시간:152ms

#include <iostream>
#include <list>
#include <string>
using namespace std;
int main()
{
	string input;
	int cnt;
	cin >> input;
	cin >> cnt;

	list <char>word;
	for (int index = 0; index < input.size(); index++)
	{
		word.push_back(input[index]);
	}

	list<char>::iterator it = word.end();

	char select;
	for (int i = 0; i < cnt; i++)
	{
		cin >> select;
		if (select == 'L' && it != word.begin())
		{
			it--;
		}
		else if (select == 'D' && it != word.end())
		{
			it++;
		}
		else if (select == 'B' && it != word.begin())
		{
			it--;
			it = word.erase(it);
		}
		else if (select == 'P')
		{
			char insert;
			cin >> insert;
			word.insert(it, insert);
		}
	}

	it = word.begin();
	for (int i = 0; i < word.size(); i++)
	{
		cout << *it;
		it++;
	}
}