#include <stdlib.h>
#include <stdio.h>

void main(){

    int n, cari, data[15], jumlah = 0;

    printf("Mau berapa data yang disimpan? : ");
    scanf("%d", &n);
    printf("Masukan %d integer(s)\n", n);
    
    for(int i = 0; i < n; i++){
        scanf("%d", &data[i]);
    }
    printf("Angka yang mau dicari :");
    scanf("%d", &cari);

    for(int i = 0; i < n; i++){
        if(data[i] == cari){
            printf("Angka %d Ketemu, dan tersimpan di lokasi %d\n", cari, i+1);
            jumlah++;
        }
    }
    if (jumlah == 0){
        printf("Angka %d tidak ditemukan", cari);
    } else {
        printf("Angka %d Ketemu, dan tampil sebanyak %d\n", cari, jumlah);
    }
}