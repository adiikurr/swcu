// Nama : Adi Kurniawan
// NIM  : 672021172

#include <iostream>
using namespace std;

int main(){

int angka;
cout << "Masukan angka tingginya : ";  
cin >> angka;

for(int a = 1; a <= (angka); a++){              // Looping endline ke arah bawah sebanyak "angka"
    for(int y = 1; y <= (angka); y++){          // Looping membuat segitiga ke arah samping sebanyak "angka"
        cout << " ";                            // Membuat jarak diantara para segitiga
        if(y <= (angka-a)){                     // Membuat "Hollow di baris atas"               
            for(int z = 1; z <= angka; z++){    
                cout << " ";
                }
        } else{                                 // Membuat bentuk segitiga siku-siku normal
            for(int b = angka; b > a; b--){     
                cout << " ";
            } for(int c = 1; c <= a; c ++){
                cout << "*";
                }
        }
    } cout << "\n";
}


    for(int a = 1; a <= n; a++){
        for(int b = 1; b <= a; b++){
           cout << " ";
            for(int c = 1; c <= a; c++){
               cout << "*";
            } for(int d = n; d >= a; d--){
                cout << " ";
            }
        } cout << "\n"; 
    }
    return 0;
} 