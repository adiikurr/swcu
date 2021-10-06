#include <iostream>
#include <string>
using namespace std;

int main(){

string nama = "Adi Kurniawan", asal = "Tangerang", hobi = "Coding", makananfavorit = "Indomie";
int umur = 20, nilai;
float tinggi = 175.5;

    cout << "Ini adalah program dari soal pertama, \n \n";
    cout << "Perkenalkan nama saya " << nama << ", umur saya saat ini " << umur << " tahun";
    cout << " dan saya berasal dari kota " << asal << ", Hobby saya saat ini adalah " << hobi;
    cout << ", tinggi saya " << tinggi << " cm";
    cout << ", dan makanan favorit saya adalah " << makananfavorit << endl;

    cout << "\nIni adalah program dari soal kedua, \n \n";
    cout << "Masukan angka nilainya : ";
    cin >> nilai;
    if(nilai >80){
        cout << "Kamu dapat nilai A";
    }   else if(nilai > 75){
            cout << "Kamu dapat nilai AB";
    }       else if(nilai > 70){
                cout << "Kamu dapat nilai B";
    }           else if(nilai > 65){
                  cout << "Kamu dapat nilai BC";
    }               else if(nilai >= 60){
                     cout << "Kamu dapat nilai C";
    } else{
        cout << "Kamu dapat nilai E" << endl;
    }
    return 0;
}