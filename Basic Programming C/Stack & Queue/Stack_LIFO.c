#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <string.h>
#define MAX_STACK 5     // Maximal Data

typedef struct
{
    int top;
    char data[5][5];
} Stack;

Stack tumpukan;

void Awal();
int isFull();
int isEmpty();
void Push(char d[5]);
void Pop();
void TampilStack();
void Bersihkan();

int main()
{
    int pilihan;
    Awal();
    char data[5];
    do
    {
        system("cls");
        printf("Menu Stack LIFO\n");
        printf("1. Push\n");
        printf("2. Pop\n");
        printf("3. Lihat isi array stack\n");
        printf("4. Hapus isi array stack\n");
        printf("5. Keluar\n");
        printf("Masukan pilihanmu : "); scanf("%d", &pilihan);

        switch (pilihan)
        {
        case 1:
            if (isFull() != 1)
            {
                printf("Data yang ingin diinputkan = "); scanf("%s", data);
                Push(data);
            }
            else
                printf("Stack sudah penuh!\n");
            break;

        case 2:
            if (isEmpty() != 1)
                Pop();
            else
                printf("Stack masih kosong!\n");
            break;
        case 3:
            if (isEmpty() != 1)
                TampilStack();
            else
                printf("\nMasih kosong! \n");
            break;
        case 4:
            Bersihkan();
            printf("Data sudah dihapus!\n");
            break;
        default:
            if(pilihan != 5)
            {
                printf("Masukan pilihan dari 1 sampai 4 saja");
            }
        }
        getch();
    } while (pilihan != 5);
}

void Awal()
{
    tumpukan.top = -1;
}

int isFull()
{
    if (tumpukan.top == MAX_STACK - 1)
        return 1;
    else
        return 0;
}

int isEmpty()
{
    if (tumpukan.top == -1)
        return 1;
    else
        return 0;
}

void Push(char d[5])
{
    tumpukan.top++;
    strcpy(tumpukan.data[tumpukan.top], d);
}

void Pop()
{
    printf("Data yang diambil = %s\n", tumpukan.data[tumpukan.top]);
    tumpukan.top--;
}

void TampilStack()
{
    for (int i = tumpukan.top; i >= 0; i--)
    {
        printf("Data: %s\n", tumpukan.data[i]);
    }
}

void Bersihkan()
{
    tumpukan.top = -1;
}