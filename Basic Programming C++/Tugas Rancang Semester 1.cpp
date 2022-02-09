#include <iostream>
#include <string>
#include <windows.h>
using namespace std;

int pilihan, tinggi_polarumah, ukuranbendera, input_iditem;
int uanganda, totalharga = 0;
int input_jumlahitem;
int harga_item1 = 0, harga_item2 = 0, harga_item3 = 0, harga_item4 = 0, harga_item5 = 0;
int jumlah_item1 = 0, jumlah_item2 = 0, jumlah_item3 = 0, jumlah_item4 = 0, jumlah_item5 = 0;
char komponenrumah, belilagi;
char nama[] = "            Nama  : Adi Kurniawan";
char nim[] = "            NIM   : 672021172";
string namaitem[5] = {"Logitech Keyboard G213 Prodigy", "Logitech Mouse G304 Lightspeed", "Logitech Mousepad G240 Cloth", "Intel Core i3-10100 LGA1200 4c 8t", "AMD Ryzen 9 5950X AM4 16c 32t"};

void pembatasbaris(){
    cout << "\n--------------------------------------------------\n";
}

// Fungsi Program ke-2
void detail_item(int input_iditem){
    int hargaitem[5] = {545000, 509000, 269000, 2100000, 12530000};
    switch(input_iditem){
        case 1:
        cout << "Item yang dipilih : " << namaitem[0] << endl;
        cout << "Masukan jumlah pembelian itemnya : ";
        cin >> input_jumlahitem;
        jumlah_item1 += input_jumlahitem;
        harga_item1 = hargaitem[0] * jumlah_item1;
        break;
        case 2:
        cout << "Item yang dipilih : " << namaitem[1] << endl;
        cout << "Masukan jumlah pembelian itemnya : ";
        cin >> input_jumlahitem;
        jumlah_item2 += input_jumlahitem;
        harga_item2 = hargaitem[1] * jumlah_item2;
        break;
        case 3:
        cout << "Item yang dipilih : " << namaitem[2] << endl;
        cout << "Masukan jumlah pembelian itemnya : ";
        cin >> input_jumlahitem;
        jumlah_item3 += input_jumlahitem;
        harga_item3 = hargaitem[2] * jumlah_item3;
        break;
        case 4:
        cout << "Item yang dipilih : " << namaitem[3] << endl;
        cout << "Masukan jumlah pembelian itemnya : ";
        cin >> input_jumlahitem;
        jumlah_item4 += input_jumlahitem;
        harga_item4 = hargaitem[3] * jumlah_item4;
        break;
        case 5:
        cout << "Item yang dipilih : " << namaitem[4] << endl;
        cout << "Masukan jumlah pembelian itemnya : ";
        cin >> input_jumlahitem;
        jumlah_item5 += input_jumlahitem;
        harga_item5 = hargaitem[4] * jumlah_item5;
    }
}
// Fungsi Program ke-2
void pembayaran_item(){
    totalharga = harga_item1 + harga_item2 + harga_item3 + harga_item4 + harga_item5;
    cout << "Harga total belanjaan yang harus dibayar adalah : Rp. ";
    cout << totalharga;
    cout << "\nMasukan uang anda : ";
    cin >> uanganda;
    while(uanganda < totalharga){
        cout << "\nUang anda kurang untuk membayar belanjaan ini";
            cout << "\nMasukan uang anda kembali : ";
            cin >> uanganda;
    }
    if(uanganda > totalharga){
        cout << "\nKembalian anda : Rp. " << uanganda - totalharga;
        cout << "\nTerimakasih telah berbelanja di FTI Computer :)";
    } else{
        cout << "\nUang anda pas";
        cout << "\nTerimakasih telah berbelanja di FTI Computer :)";
    }
}
// Fungsi Program ke-2
void inputbelilagi(){
    cout << "Ingin membeli lagi ? (y/n) : ";
    cin >> belilagi;
}
// Fungsi Program ke-4
void animasi_teks(string teks){
    for(int x = 0; teks[x] != '\0'; x++){
        cout << teks[x] << "\xDB";
        for(auto y = 0; y < 30000000; y++);
        cout << "\b \b";
    }
}

void balikmain(){
    cout << "\nTekan enter untuk kembali ke menu utama : ";
    getchar();
    if(cin.get() == '\n'){
    system("CLS");
}

}
int main(){
    HANDLE h = GetStdHandle(STD_OUTPUT_HANDLE);
    int a, b, c, x, y, i;

    cout << "\n\n===================================\n";
    cout << "         TUGAS RANCANG DDP         \n";
    cout << "(1) Pola Rumah                     \n";
    cout << "(2) FTI Computer                   \n";
    cout << "(3) Bendera                        \n";
    cout << "(4) Author                         \n";
    cout << "(5) Exit Program                   \n";
    cout << "===================================\n";
    cout << "Masukan angka (1 , 2 , 3 , 4 atau 5) : ";
    cin >> pilihan;

switch (pilihan){
case 1:
        cout << "Tentukan tinggi dari cerobong asap, atap dan dinding : ";
        cin >> tinggi_polarumah;
        cout << "Masukan komponen rumahnya : ";
        cin >> komponenrumah;
// Cerobong Asap - Persegi - Putih
        for(a = 0; a < tinggi_polarumah; a++){
            for(b = 0; b < tinggi_polarumah; b++){
                cout << " ";
            } 
            for(c = 0; c < tinggi_polarumah; c++){
                cout << komponenrumah;
            }
            cout << "\n";
        }
// Atap - Segitiga sama kaki - Biru
        for(a = 1; a <= tinggi_polarumah; a++){
    SetConsoleTextAttribute(h, 1);
            for(b = tinggi_polarumah; b > a; b--){
                cout << " ";
            }
            for(c = 1; c < 2*a; c++){
                cout << komponenrumah;
            }
// Atap - Jajar Genjang  - Merah
    SetConsoleTextAttribute(h, 4);
            for(x = 0; x < 3*tinggi_polarumah; x++){
                cout << komponenrumah;
            }
            cout << "\n";
        }
// Dinding - Persegi Panjang - Kuning
            for(a = 0; a < tinggi_polarumah; a++){
    SetConsoleTextAttribute(h, 14);
                for(b = 1; b < 2*tinggi_polarumah; b++){
                    cout << komponenrumah;
                }
// Dinding - Persegi Panjang - Hijau
    SetConsoleTextAttribute(h, 2);
                for(x = 0; x < 3*tinggi_polarumah; x++){
                    cout << komponenrumah;
                }
                cout << "\n";
            }
    SetConsoleTextAttribute(h, 7);
balikmain();
return main();
case 2:
        cout << "\n=============================================================\n";
        cout << "|            Daftar dan Harga Barang FTI Computer           |\n";
        cout << "|-----------------------------------------------------------|\n";
        cout << "|  ID  |   Nama Item                       |  Harga Barang  |\n";
        cout << "|-----------------------------------------------------------|\n";
        cout << "|  1.  | Logitech Keyboard G213 Prodigy    | Rp. 545.000    |\n";
        cout << "|  2.  | Logitech Mouse G304 Lightspeed    | Rp. 509.000    |\n";
        cout << "|  3.  | Logitech Mousepad G240 Cloth      | Rp. 269.000    |\n";
        cout << "|  4.  | Intel Core i3-10100 LGA1200 4c 8t | Rp. 2.100.000  |\n";
        cout << "|  5.  | AMD Ryzen 9 5950X AM4 16c 32t     | Rp. 12.530.000 |\n";
        cout << "=============================================================\n";
    do{
        cout << "Masukan ID Item antara (1 , 2 , 3 , 4 atau 5) : ";
        cin >> input_iditem;

        switch(input_iditem){
            case 1:
                pembatasbaris();
                detail_item(input_iditem);
                inputbelilagi();
                pembatasbaris();
                break;
            case 2:
                pembatasbaris();
                detail_item(input_iditem);
                inputbelilagi();
                pembatasbaris();
                break;
            case 3:
                pembatasbaris();
                detail_item(input_iditem);
                inputbelilagi();
                pembatasbaris();
                break;
            case 4:
                pembatasbaris();
                detail_item(input_iditem);
                inputbelilagi();
                pembatasbaris();
                break;
            case 5:
                pembatasbaris();
                detail_item(input_iditem);
                inputbelilagi();
                pembatasbaris();
                break;
            default:
                cout << "\n-----Mohon masukan ID Item yang tersedia saja-----\n";
            }
    } while(belilagi == 'y');
    cout << "=========== Struk Belanja FTI Computer ===========\n";
    cout << "Daftar keranjang belanjaan anda : \n";
    if(jumlah_item1 != 0){
        cout << namaitem[0] << " dengan jumlah : " << jumlah_item1 << " item\n";
        } if(jumlah_item2 != 0){
            cout << namaitem[1] << " dengan jumlah : " << jumlah_item2 << " item\n";
        } if(jumlah_item3 != 0){
            cout << namaitem[2] << " dengan jumlah : " << jumlah_item3 << " item\n";
        } if(jumlah_item4 != 0){
            cout << namaitem[3] << " dengan jumlah : " << jumlah_item4 << " item\n";
        } if(jumlah_item5 != 0){
            cout << namaitem[4] << " dengan jumlah : " << jumlah_item5 << " item\n";
        }
    pembatasbaris();
    pembayaran_item();
    balikmain();
    break;
case 3:
    cout << "Masukan ukuran benderanya : ";
    cin >> ukuranbendera;

    for(i = 0; i < 3; i++){
        for(a = 1; a <= ukuranbendera; a++){
            for(b = 0; b < 3; b++){
                SetConsoleTextAttribute(h, 14);
                for(x = 0; x < a; x++){
                    cout << "x ";
                }
                SetConsoleTextAttribute(h, 4);
                for(y = ukuranbendera; y >= a; y--){
                    cout << "y ";
                }
            }
            cout << "\n";
        }
    }
    SetConsoleTextAttribute(h, 7);
balikmain();
return main();
case 4:
        cout << "\n\n\n---------- Tugas Rancang ini dibuat oleh : ----------\n\n";
        SetConsoleTextAttribute(h, 4);
        animasi_teks(nama);
        cout << "\n\n";
        SetConsoleTextAttribute(h, 14);
        animasi_teks(nim);
        SetConsoleTextAttribute(h, 7);
        cout << "\n-----------------------------------------------------";
balikmain();
return main();
case 5:
    exit(0);
default:
        cout << "Masukan angka diantara (1 , 2 , 3, 4 atau 5)";
return main();
    }
}