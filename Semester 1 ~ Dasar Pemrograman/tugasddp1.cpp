#include <iostream>
#include <string>
using namespace std;

int main(){

string apel;
int m = 0, h = 0, mh = 0;
char tanya;

cout << "Program Menghitung Apel\n";
do{
cout << "Masukan warna apelnya : ";
cin >> apel;
    if(apel == "merah"){
    m++;
    cout << "Masukan apel lagi? (Y/N) ";
    cin >> tanya;
    } else if(apel == "hijau"){
        h++;
        cout << "Masukan apel lagi? (Y/N) ";
        cin >> tanya;
        } else if(apel == "merahhijau"){
            mh++;
            cout << "Masukan apel lagi? (Y/N) ";
            cin >> tanya;
            }
} while(tanya == 'y');

    cout << "\nDetail jumlah apel\n";
    cout << "Merah          : " << m << " buah" << endl;
    cout << "Hijau          : " << h << " buah" << endl;
    cout << "Merah Hijau    : " << mh << " buah" << endl;
    
    return 0;
}