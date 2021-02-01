#include <iostream>
#include <algorithm>
using namespace std;
int *time;

long long CalMinTime(int size)
{
    long long result = 0;
    for (int i = 0; i < size; i++)
    {
        result += (time[i] * (size - i));
    }
    return result;
}
int main()
{
    int num;
    cin >> num;
    time = new int[num];
    for (int i = 0; i < num; i++)
    {
        int input;
        cin >> input;
        time[i] = input;
    }

    sort(time, time + num);
    cout << CalMinTime(num) << endl;
}