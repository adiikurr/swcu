// Nama : Adi Kurniawan
// NIM  : 672021172

#include <iostream>
#include <string>
using namespace std;

int main(){
    string teks;
    int jumlahkasus, jumlahvokal;

    cin >> jumlahkasus;
    for(int x = 0; x < jumlahkasus; x++){
        cin >> teks;
        jumlahvokal = 0;
            for(int y = 0; y < teks.length(); y++){
                if(teks[y] == 'a' || teks[y] == 'i' || teks[y] == 'u' || teks[y] == 'e' || teks[y] == 'o' || teks[y] == 'A' || teks[y] == 'U' || teks[y] == 'I' || teks[y] == 'E' || teks[y] == 'O'){
                    jumlahvokal++;
                } 
            } cout << jumlahvokal << endl;
    }
    return 0;
}