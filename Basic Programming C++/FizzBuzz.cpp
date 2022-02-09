// Nama : Adi Kurniawan
// NIM  : 672021172

#include <iostream>
#include <cstring>
using namespace std;

int main(){

int jumlahkasus;
cout << "Masukan jumlah kasus (N) yang ingin diproses : ";
cin >> jumlahkasus;
char fb_output[jumlahkasus][10]; //FizzBuzz Output

for(int x = 0; x <= jumlahkasus; x++){
    cin.getline(fb_output[x], sizeof fb_output[x]);
}
for(int x = 0; x <= jumlahkasus; x++){
    if(strcmp(fb_output[x], "Fizz") == 0){
    cout << "\n" << x << " ";
    } 
    else if(strcmp(fb_output[x], "Buzz") == 0){
    cout << x;
    break;
    }
}
 return 0;
}