#include <stdio.h>
char arr[2188][2188] = {};
void star(int a, int b, int n, char(*arr)[2188]);
int main() {
    int n = 0;
    
    scanf("%d", &n);
    star(0, 0, n, arr);
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            printf("%c", arr[i][j]?'*':' ');
        }
        printf("\n");
    }
    return 0;

}

void star(int a, int b, int n, char(*arr)[2188]) {


    if (n == 1) {

        arr[a][b] = 1;


    }


    else {
        for (int i = a; i < a + n; i = i + n / 3) {
            for (int j = b; j < b + n; j = j + n / 3) {
                if ((i >= a + (n / 3) && i <= a + n - 1 - (n / 3)) && (j >= b + (n / 3) && j <= b + n - 1 - (n / 3))) {}
                else { star(i, j, n / 3, arr); }


            }
        }
    }

}