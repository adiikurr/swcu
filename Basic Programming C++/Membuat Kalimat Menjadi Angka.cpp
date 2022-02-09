// Nama : Adi Kurniawan
// NIM  : 672021172

#include <iostream>
#include <string>
using namespace std;

int returnkata(char x)
{
    return (int)x - 64;
}

int main(){

    string kata;
    int jumlahkasus;
    cin >> jumlahkasus;

for(int a = 0; a <= jumlahkasus; a++){
    getline(cin,kata);
    for(int x = 0; x < kata.length(); x++){
        kata[x] = toupper(kata[x]);
    }
    for (unsigned int i = 0; i < kata.length(); i++){
        if(kata[i] == ' '){
            cout << "$";
        } else{
            cout << returnkata(kata[i]);
        }
    }
    cout << endl;
}
    return 0;
}