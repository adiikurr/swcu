// Nama : Adi Kurniawan
// NIM  : 672021172

#include <iostream>
#include <string>
using namespace std;

int main(){

	int jumlahkasus;
	string teks;

	cin >> jumlahkasus;
	for(int i = 0; i <= jumlahkasus; i++){
		getline(cin,teks);
		for(int x = 0; x < teks.length(); x++){
			teks[x] = toupper(teks[x]);
		}
		cout << teks << endl;
	}
    return 0;
}