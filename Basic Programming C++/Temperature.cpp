// Nama : Adi Kurniawan
// NIM  : 672021172

#include <iostream>
#include <cstdlib>
using namespace std;

int main(){

    int jumlahtemperatur, positif[100], selisih, x, y = 0;
    cin >> jumlahtemperatur;
    int temperatur[jumlahtemperatur];
    
// Mengubah semua inputan integer ke positif integer array
    for(x = 0; x < jumlahtemperatur; x++){
        cin >> temperatur[x];
            positif[x] = abs(temperatur[x] - 0);
    }

    for(x = 0; x < jumlahtemperatur; x++){
        if(x == 0 && temperatur[0] > 0){
            selisih = abs(positif[x] - 0);
        }
        else if(x == 0 && temperatur[0] < 0){
            selisih = positif[x];
        }
        else {
            if(temperatur[x] >= -273 && temperatur[x] <= 5526){
                if(abs(temperatur[x] - 0) == abs(selisih - 0)){
                    y = positif[x];
                }
                else if(positif[x] < selisih){
                    selisih = positif[x];
                    y = temperatur[x];
                    }
            }
            else if(temperatur[x] == 0){
                cout << 0;
            }
        }
    }
cout << y;
return 0;
}