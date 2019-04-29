//198kb , 924ms
#include <iostream>
#include <string.h>
using namespace std;
int cnt, S, n;
char input[6];

int main() {
	scanf("%d", &cnt);
	while (cnt--) {
		scanf("%s", input);
		if (input[1] == 'd') {
			scanf("%d", &n);
			n--;
			S = (S | (1 << n));
		}
		else if (input[1] == 'e') {
			scanf("%d", &n);
			n--;
			S = (S & ~(1 << n));
		}
		else if (input[1] == 'h') {
			scanf("%d", &n);
			n--;
			int check = (S & (1 << n));
			if (check) {
				printf("%d\n", 1);
			}
			else {
				printf("%d\n", 0);
			}
		}
		else if (input[1] == 'o') {
			scanf("%d", &n); n--;
			S = (S ^ (1 << n));
		}
		else if (input[1] == 'l') {
			S = (1 << 20) - 1;
		}
		else if (input[1] == 'm') {
			S = 0;
		}
	}
	return 0;
}