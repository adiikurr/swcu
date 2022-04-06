#include <stdio.h>
#include <stdlib.h>
#include <conio.h>

void main()
{   
    char nama[20], matkul[20], nim[10];
    int nilai, tinggilove;

    system("cls");
    printf("------TUGAS ASD------\n");
    printf("----Adi Kurniawan----\n");
    printf("======MENU UTAMA======\n");
    printf("1. Cek Kelulusan Matkul\n");
    printf("2. Cinta\n");
    printf("3. Keluar\n\n");
    printf("Masukkan Pilihanmu : ");

    int pilihan;
    scanf("%d", &pilihan);
    fflush(stdin);

    switch (pilihan)
    {
    case 1:
    system("cls");
        printf("KELULUSAN MATKUL\n");
        printf("Masukan Nama    : ");
        scanf("%[^\n]", &nama);
        printf("Masukan NIM     : ");
        scanf("%s", &nim);
        printf("Masukan Matkul  : ");
        scanf("%s", &matkul);
        printf("Masukan Nilai   : ");
        scanf(" %d", &nilai);

        if(nilai <= 100 && nilai >= 85){
            printf("\nSelamat, %s kamu lulus mata kuliah %s dengan nilai A", nama, matkul);
        }
        else if(nilai <= 84 && nilai >= 80){
            printf("\nSelamat, %s kamu lulus mata kuliah %s dengan nilai AB", nama, matkul);
        }
        else if(nilai <= 79 && nilai >= 75){
            printf("\nSelamat, %s kamu lulus mata kuliah %s dengan nilai B", nama, matkul);
        }
        else if(nilai <= 74 && nilai >= 70){
            printf("\nSelamat, %s kamu lulus mata kuliah %s dengan nilai BC", nama, matkul);
        }
        else if(nilai <= 69 && nilai >= 65){
            printf("\nSelamat, %s kamu lulus mata kuliah %s dengan nilai C", nama, matkul);
        }
        else if(nilai <= 64 && nilai >= 60){
            printf("\nMaaf, %s kamu tidak lulus mata kuliah %s dengan nilai CD", nama, matkul);
        }
        else if(nilai <= 59 && nilai >= 55){
            printf("\nMaaf, %s kamu tidak lulus mata kuliah %s dengan nilai D", nama, matkul);
        }
        else {
            printf("\nMaaf, %s kamu tidak lulus mata kuliah %s dengan nilai E", nama, matkul);
        }
        printf("\n\nTekan apa saja untuk kembali ke menu utama ");
        getch();
        system("cls");
        return main();
    break;
    case 2:
    system("cls");
        printf("=====CINTA=====\n");
        printf("Seberapa besar cintamu? ");
        scanf("%d",&tinggilove);
        
        for(int x = 1; x <= tinggilove+1; x++){
            for(int y = 1; y <= (tinggilove+1)-x; y++){
                printf("  ");
            } printf("-");
            if (x == 1) {
                printf("");
                } 
            else if (x == 2) {
                for(int z = 1; z < tinggilove; z++){
                    printf(" ");
                } 
            } else if (x == tinggilove+1 || x == tinggilove && tinggilove > 2) {
                for(int z = 1; z <= tinggilove; z++){
                    printf("  ");
                }
            } else {
                for(int z = 1; z <= tinggilove+x; z++){
                    printf(" ");
                }
            }
            printf("-\n");
        }
        for(int x = 1; x <= tinggilove; x++){
            for(int y = 1; y <= x; y++){
                printf("  ");
            } printf("-");
            if(x == 1 && tinggilove > 2){
                for(int z = 1; z <= tinggilove; z++){
                    printf("  ");
                } 
            } else if(x == tinggilove-1){
                for(int z = 1; z <= x; z++){
                    printf(" ");
                }
            } else if(x == tinggilove){
                printf("");
                }
            else {
                for(int z = tinggilove; z+tinggilove >= x; z--){
                    printf(" ");
                }
            }
            printf("-\n");
        }

       if(tinggilove == 5){
           printf("\nCintamu sempurna semoga ga disia-siain sm doi yaa!");
       } else if(tinggilove == 4){
           printf("\nTerkadang kita memang harus membatasi rasa cinta, tapi kalau dirasa nyaman ya dipertahankan!");
       } else if(tinggilove == 3){
           printf("\nTerkadang kita memang harus membatasi rasa cinta, takut juga kalo dihantuing hikshikshiks :')");
       } else if(tinggilove <= 2){
           printf("\nKalo ga mau diseriusin, udahin aja deh ya!");
       } else if(tinggilove > 5){
           printf("\nYakin secinta itu sama dia? Tapi dia udah ada yang punya :')");
       }
       printf("\n\nTekan apa saja untuk kembali ke menu utama ");
       getch();
       system("cls");
       return main();
    break;
    case 3:
        exit(0);
        break;
    default:
        printf("\nMENU YANG ANDA PILIH TIDAK ADA");
        printf("\n\nTekan apa saja untuk kembali ke menu utama ");
        getch();
        system("cls");
        return main();
    }
}