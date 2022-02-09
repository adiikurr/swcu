// Nama : Adi Kurniawan
// NIM  : 672021172

#include <iostream>
#include <cstring>
using namespace std;

int main(){

char kata[100];
int jumlahkasus;

cin >> jumlahkasus;
for(int a = 0; a < jumlahkasus; a++){
    int banding = 0;
    cin >> kata;
    int y = strlen(kata);
        for(int x = 0; x < y; x++){
            if(kata[x] != kata[y-x-1]){
                banding = 1;
                break;
            }
        } if(banding){
            cout << "NO\n";
            } else{
                cout << "YES\n";
            }
}
    return 0;
}