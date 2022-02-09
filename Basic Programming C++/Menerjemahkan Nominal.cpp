// Nama : Adi Kurniawan
// NIM  : 672021172

#include <iostream>
#include <string>
using namespace std;

int main(){

string teksnominal[] = {" ", "Satu", "Dua", "Tiga", "Empat", "Lima", "Enam", "Tujuh", "Delapan", "Sembilan", "Sepuluh", "Sebelas", "Dua Belas", "Tiga Belas", "Empat Belas", "Lima Belas", "Enam Belas", "Tujuh Belas", "Delapan Belas", "Sembilan Belas", "Dua Puluh", "Tiga Puluh", "Empat Puluh", "Lima Puluh", "Enam Puluh", "Tujuh Puluh", "Delapan Puluh", "Sembilan Puluh", "Seratus", "Seribu"};
int nominal, nominalbagiankiri;
cout << "Masukan nominalnya : ";
cin >> nominal;

if(nominal >= 0 && nominal <= 10000){
    if(nominal >= 1000 && nominal < 2000){
        nominalbagiankiri = nominal / 1000;
        cout << teksnominal[28 + nominalbagiankiri] << " ";
        nominal -= nominalbagiankiri * 1000;
    }
    if(nominal > 2000){
        nominalbagiankiri = nominal / 1000;
        cout << teksnominal[nominalbagiankiri] << " Ribu ";
        nominal -= nominalbagiankiri * 1000;
    }
    if(nominal >= 100 && nominal < 200){
        nominalbagiankiri = nominal / 100;
        cout << teksnominal[27 + nominalbagiankiri] << " ";
        nominal -= nominalbagiankiri * 100;
    }
    if(nominal > 200){
        nominalbagiankiri = nominal / 100;
        cout << teksnominal[nominalbagiankiri] << " Ratus ";
        nominal -= nominalbagiankiri * 100;
    }
    if(nominal > 20){
        nominalbagiankiri = nominal / 10;
        cout << teksnominal[18 + nominalbagiankiri] << " ";
        nominal -= nominalbagiankiri * 10;
    }
    cout << teksnominal[nominal];
} else {
    cout << "Mohon masukan nominal antara 0 - 10000";
}
 return 0;
}