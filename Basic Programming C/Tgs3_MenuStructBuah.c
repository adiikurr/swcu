#include <stdlib.h>
#include <stdio.h>
#include <conio.h>
#include <ctype.h>
#include <string.h>

int i, j, length = 5;

void swapArray(int *a, int *b);
void selectionSort(int pilihanSort);
void displaySortArray();

struct Buah
{
    char nama[10][10];
    int harga[10];
}; struct Buah b;

void main(){

    int pilihanMenu, pilihanSort, pilihanSearch, cariHarga, tanda;
    char cariNama[10];

    label:
    system("cls");
    printf("===Menu Struct Buah===\n");
    printf("1. Tambahkan Data Buah\n");
    printf("2. Tampilkan Data Buah\n");
    printf("3. Sorting Data\n");
    printf("4. Searching Data\n");
    printf("5. Exit Program\n"); 
    printf("Masukan pilihan menu (1 - 5) : "); scanf("%d", &pilihanMenu);
    fflush(stdin);

    switch(pilihanMenu)
    {
    case 1:
        system("cls");
        printf("Masukan nama buah beserta harganya:\n");
        for(i = 0; i < length; i++){
            printf("Masukan nama buah ke-%d : ", i+1); scanf("%s", &b.nama[i]);
            
            for(j = 0; j < strlen(b.nama[i]); j++)
            {
                b.nama[i][j] = tolower(b.nama[i][j]);
            }
            printf("Masukan harga buah %s : ", b.nama[i]); scanf("%d", &b.harga[i]);
        }
        printf("\n\nTekan apa saja untuk kembali ke menu utama ");
        getch();
        goto label;
        break;
    case 2:
        system("cls");
        printf("=====Data buah beserta harganya=====\n");
        displaySortArray();

        printf("\n\nTekan apa saja untuk kembali ke menu utama ");
        getch();
        goto label;
        break;
    case 3:
        system("cls");
        printf("Sorting data berdasarkan?\n"); 
        printf("1. Nama Buah\n");
        printf("2. Harga Buah\n");
        printf("Masukan pilihan : "); scanf("%d", &pilihanSort); fflush(stdin);
        system("cls");

        switch (pilihanSort)
        {
        case 1:
            selectionSort(pilihanSort);
            printf("Daftar buah setelah di sorting berdasarkan nama secara Ascending\n");
            displaySortArray();
            break;
        case 2:
            selectionSort(pilihanSort);
            printf("Daftar buah setelah di sorting berdasarkan harga secara Ascending\n");
            displaySortArray();
            break;
        default:
            printf("Masukan pilihan antara 1 atau 2 saja");
            getch();
        }
        printf("\n\nTekan apa saja untuk kembali ke menu utama ");
        getch();
        goto label;
        break;
    case 4:
        tanda = 0;
        system("cls");
        printf("Searching data berdasarkan?\n"); 
        printf("1. Nama Buah\n");
        printf("2. Harga Buah\n");
        printf("Masukan pilihan : "); scanf("%d", &pilihanSearch); fflush(stdin);
        
        system("cls");
        switch(pilihanSearch)
        {
            case 1:
                printf("Masukan Nama Buah yang ingin dicari : "); scanf("%s", &cariNama);
                // Lowercase nama yang ingin dicari
                for(i = 0; i < strlen(cariNama); i++){
                    cariNama[i] = tolower(cariNama[i]);
                }
                    for(i = 0; i < length; i++){
                        if(strcmp(b.nama[i], cariNama) == 0){
                            printf("%s yang kamu cari ketemu seharga %d per buahnya\n", cariNama, b.harga[i]);
                            tanda = 1;
                            break;
                        }
                    }
                    if(tanda != 1){
                        printf("Nama buah yang dicari tidak ada");
                    }
                break;
            case 2:
                printf("Masukan Harga Buah yang ingin dicari : "); scanf("%d", &cariHarga);
                    for(i = 0; i < length; i++){
                        if(b.harga[i] == cariHarga){
                            printf("Harga %d per buah yang kamu cari ketemu, Nama buahnya %s\n", cariHarga, b.nama[i]);
                            tanda = 1;
                            break;
                        }
                    }
                    if(tanda != 1){
                        printf("Harga buah yang dicari tidak ada");
                    }
                break;
            default:
                printf("Masukan pilihan antara 1 atau 2 saja"); 
                getch();
        }
        printf("\n\nTekan apa saja untuk kembali ke menu utama ");
        getch();
        goto label;
        break;
    case 5:
        exit(0);
        break;
    default:
        printf("Mohon masukan pilihan menu 1 sampai 5 saja");
        getch();
        goto label;
    }
}

void swapArray(int *a, int *b)
{
    int temp = *a;
    *a = *b;
    *b = temp;
}

void selectionSort(int pilihanSort)
{
    char temporary[10];
    for(i = 0; i < length - 1; i++)
    {
        // Sorting berdasarkan nama secara Ascending
        if(pilihanSort == 1){
            int swapped = i;
            for(j = i+1; j < length; j++){
                if(strcmpi(b.nama[i], b.nama[j]) > 0)
                {
                    swapped = j;
                    strcpy(temporary, b.nama[i]);
                    strcpy(b.nama[i], b.nama[j]);
                    strcpy(b.nama[j], temporary);
                }
            }
            swapArray(&b.harga[swapped], &b.harga[i]);
        }
        // Sorting berdasarkan harga secara Ascending
        else if(pilihanSort == 2){
            int swapped = i;
            for(j = i+1; j < length; j++){
                if(b.harga[swapped] >= b.harga[j])
                {
                    swapped = j;
                    strcpy(temporary, b.nama[i]);
                    strcpy(b.nama[i], b.nama[j]);
                    strcpy(b.nama[j], temporary);
                }
            }
            swapArray(&b.harga[swapped], &b.harga[i]);
        }
    }
}

void displaySortArray()
{
    for(int i = 0; i < length; i++){
        printf("List ke - %i : Nama buah = %s seharga %d perbuah\n", i+1, b.nama[i], b.harga[i]);
    }
}