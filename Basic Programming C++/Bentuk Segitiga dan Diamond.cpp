/*  
    Nama : Adi Kurniawan
    NIM : 672021172
*/
#include <iostream>
using namespace std;

int main(){

    int n, a, b, c;
    cout << "Masukan angkanya (Disarankan 4): ";
    cin >> n;

cout << "\n===== Program pertama =====" << endl;
    for(a = 1; a < n; a++){
        for(b = 1; b <= a; b++){
            cout << a;
        } cout << "\n";
    }
cout << "\n";
cout << "===== Program kedua =====" << endl;
    for(a = 1; a <= n; a++){
        for(b = n; b > a; b--){
            cout << " ";
        } for(c = 1; c < 2*a; c++){
            cout << c;
        } cout << "\n";
    } for(a = 1; a <= n-1; a++){
        for(b = 1; b <= a; b++){
            cout << " ";
        } for(c = 1; c < 2*n-(2*a); c++){
            cout << c;
        } cout << "\n";
    }
cout << "\n";
cout << "===== Program ketiga =====" << endl;
    for(a = 1; a <= n; a++){
        for(b = 1; b <= n-a; b++){
            cout << "  ";
        } for(c = 1; c <= a; c++){
            cout << " " << c << "  ";
        } cout << "\n";
    } for(a = 1; a <= n-1; a++){
        for(b = 1; b <= a; b++){
            cout << "  ";
        } for(c = 1; c <= n-a; c++){
            cout << " " << c << "  "; 
        }cout << "\n";
    }
return 0;
}
