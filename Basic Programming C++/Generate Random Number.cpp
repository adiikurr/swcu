// Adi Kurniawan - 672021172

#include <iostream>
#include <ctime>
using namespace std;

int main(){

srand (time(0)); 
int rndm = rand()%101, jwbn;
// Angka random yang digenerate dari 0 - 99

cout << "Tebaklah angka yang digenerate oleh komputer\n";

while(jwbn != rndm){
    cout << "Masukan jawaban kamu : ";
    cin >> jwbn;
        if(jwbn < rndm){
            cout << "Jawaban kamu lebih kecil dari angkanya" << endl;
        }else if(jwbn > rndm){
                cout << "Jawaban kamu lebih besar dari angkanya" << endl;
            }else{
                cout << "Selamat! Jawaban kamu sudah tepat! Angkanya adalah " << rndm << endl;
            }
    } return 0;
}
