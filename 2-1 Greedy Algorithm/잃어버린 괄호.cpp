#include <iostream>
#include <string>
using namespace std;

int CalMin(string input)
{
    int result = 0;
    string temp = "";
    bool minus_flag = false;
    for (int i = 0; i <= input.size(); i++)
    {
        if (input[i] == '+' || input[i] == '-' || i == input.size())
        {
            if (minus_flag)
                result -= atoi(temp.c_str());
            else
                result += atoi(temp.c_str());
            if (input[i] == '-')
                minus_flag = true;

            temp = "";
        }
        else
            temp += input[i];
    }
    return result;
}

int main()
{
    string input;
    cin >> input;
    cout << CalMin(input) << endl;
    system("pause");
}