// Nama : Adi Kurniawan
// NIM  : 672021172

#include <iostream>
#include <algorithm>
using namespace std;

int selisih_minimum(int z[], int n){
    sort(z, z+n);
    int min_selisih = INT_MAX;
    for (int i = 0; i < n-1; i++)
      if(z[i+1] - z[i] < min_selisih)
        min_selisih = z[i+1] - z[i];
    return min_selisih;
}

int main() {

    int jumlah_kuda, n;
    cout << "Masukan jumlah kudanya : ";
    cin >> jumlah_kuda;

    int kekuatan_kuda[jumlah_kuda];

    for(int x = 0; x < jumlah_kuda; x++){
    cin >> kekuatan_kuda[x];
    }

    n = sizeof(kekuatan_kuda)/sizeof(kekuatan_kuda[0]);
    cout << selisih_minimum(kekuatan_kuda, n);

    return 0;
}