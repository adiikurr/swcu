// Nama : Adi Kurniawan
// NIM  : 672021172

#include <iostream>
#include <string>
using namespace std;

int main(){

    int jumlahkasus;
    string teks;
    cin >> jumlahkasus;

for(int x = 0; x <= jumlahkasus; x++){
    getline(cin, teks);
    for(int y = 0; y < teks.length(); y++){
			teks[y] = tolower(teks[y]);
    }   for(int i = 0; i < teks.length(); i++){
            if(teks[i] == ' '){
                teks[i-1] = toupper(teks[i -1]);
            } else if(i == teks.length() - 1){
                teks[i] = toupper(teks[i]);
                }
        }
        cout << teks << endl;
    }
    return 0;
}
