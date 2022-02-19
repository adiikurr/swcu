#include <stdlib.h>
#include <stdio.h>
#include <conio.h>
#include <string.h>

void main(){

    /* 
    Username    : 672021172adi
    Password    : adikur12
    */

    char username[21], password[9], Kodedosen[7][50];
    system("cls");
    printf("=====Login Page=====\n");
    printf("\nMasukan Username    : ");
    scanf("%s", username);

if(strcmp(username, "672021172adi") == 0){
    printf("\nMasukan Password    : ");
    scanf("%s", password);
if(strcmp(password, "adikur12") == 0){
    label:
    system("cls");
    printf("=====Menu Akademik Sederhana=====\n");
    printf("1. Daftar Mata Kuliah\n");
    printf("2. Registrasi Mata Kuliah\n");
    printf("3. Kartu Studi (KST)\n");
    printf("4. Exit Program\n");
    printf("Masukan pilihan menu (1 - 4) : ");
    
    int menu, clone;
    scanf("%d", &menu);
    fflush(stdin);

    switch (menu) {
    case 1:
        system("cls");
        printf("=====Daftar Mata Kuliah S1 AiTi=====\n\n");
        printf("IN001A | IMK          |  Awanda Ardaneswari\n");
        printf("IN002B | ASD          |  Yeremia Alfa S\n");
        printf("IN003B | Tekjar       |  Riko Yudhistira\n");
        printf("IN010J | AI           |  Ineke A. Pakereng\n");
        printf("IN011H | Grafkom      |  Andrew Julius S\n");
        printf("IN012G | RPL          |  Septovan\n");
        printf("IN013C | Etprof       |  Vita Tantriyati\n");
        printf("IN050D | Komputasi    |  Yessica N\n");
        printf("IN070A | MobileAppDev |  Pratyaksa Ocsa\n");
        printf("IN051E | PBP          |  Evangs Mailoa\n");

        printf("\n\nTekan apa saja untuk kembali ke menu utama ");
        getch();
        goto label;
        break;
    case 2:
    do {
        system("cls");
        clone = 0;
        printf("=====Registrasi Mata Kuliah=====\n\n");
        printf("Silahkan pilih 5 dari 10 Matkul yang tersedia\n\n");
        for(int i = 0; i < 5; i++){
            printf("Masukan kode dosen yang ingin dipilih : ");
            scanf("%s", &Kodedosen[i]);
            for(int j = 0; j < i; j++){
                if(strcmp(Kodedosen[j], Kodedosen[i]) == 0){
                    clone++;
                }
            }
        }
        if(clone != 0){
            printf("\n\nMohon untuk tidak mengambil matakuliah yang sama secara bersamaan..");
            getch();
        }
    } while(clone != 0);
        fflush(stdin);
        printf("\n\nRegistrasi Mata Kuliah BERHASIL");
        printf("\nSilahkan cek Kartu Studi anda di menu ketiga");
        getch();
        goto label;
        break;
    case 3:
        system("cls");
        printf("===== Kartu Studi =====\n\n");
        printf("NIM     : 672021172\n");
        printf("Nama    : Adi Kurniawan\n\n");
        printf("Data Mata Kuliah terpilih :\n\n");
        
        for(int i = 0; i < 5; i++){
            if(strcmp(Kodedosen[i], "IN001A") == 0){
                printf("%d. IN001A | IMK          |  Awanda Ardaneswari\n", i+1);
            }
            else if(strcmp(Kodedosen[i], "IN002B") == 0){
                printf("%d. IN002B | ASD          |  Yeremia Alfa S\n", i+1);
            }
            else if(strcmp(Kodedosen[i], "IN003C") == 0){
                printf("%d. IN003B | Tekjar       |  Riko Yudhistira\n", i+1);
            }
            else if(strcmp(Kodedosen[i], "IN010J") == 0){
                printf("%d. IN010J | AI           |  Ineke A. Pakereng\n", i+1);
            }
            else if(strcmp(Kodedosen[i], "IN011H") == 0){
                printf("%d. IN011H | Grafkom      |  Andrew Julius S\n", i+1);
            }
            else if(strcmp(Kodedosen[i], "IN012G") == 0){
                printf("%d. IN012G | RPL          |  Septovan\n", i+1);
            }
            else if(strcmp(Kodedosen[i], "IN013C") == 0){
                printf("%d. IN013C | Etprof       |  Vita Tantriyati\n", i+1);
            }
            else if(strcmp(Kodedosen[i], "IN050D") == 0){
                printf("%d. IN050D | Komputasi    |  Yessica N\n", i+1);
            }
            else if(strcmp(Kodedosen[i], "IN070A") == 0){
                printf("%d. IN070A | MobileAppDev |  Pratyaksa Ocsa\n", i+1);
            }
            else if(strcmp(Kodedosen[i], "IN051E") == 0){
                printf("%d. IN051E | PBP          |  Evangs Mailoa\n", i+1);
            }
        }
        printf("\n\nTekan apa saja untuk kembali ke menu utama ");
        getch();
        goto label;
        break;
    case 4:
        exit(0);
        break;
    default:
        printf("Mohon masukan pilihan menu 1 sampai 4 saja");
        getch();
        goto label;
        break;
    }
} else {
    printf("Password salah, mohon coba lagi..");
    }

} else {
    printf("Username salah, mohon coba lagi..");
    }
}