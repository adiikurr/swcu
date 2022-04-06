#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#define MAX_QUEUE 3       // Maximal Data

typedef struct 
{
    int data[MAX_QUEUE], head, tail;
} Queue; 

Queue antrian;

void Awal();
int Kosong();
int isFull();
void Masuk(int data);
int Keluar();
void Tampil();
void Hapus();

void main()
{
    int pilihan, data;
    Awal();
    do{
        system("cls");
        printf("Menu Queue FIFO\n");
        printf("1. Push Data\n");
        printf("2. Pop Data\n");
        printf("3. Lihat Isi Antrian\n");
        printf("4. Hapus Isi Antrian\n");
        printf("5. Keluar\n");
        printf("Masukan pilihanmu : "); scanf("%d", &pilihan);

        switch (pilihan)
        {
        case 1:
            printf("Data = "); scanf("%d", &data);
            Masuk(data);
            break;
        case 2:
            printf("Data yang keluar = %d", Keluar());
            break;
        case 3:
            Tampil();
            break;
        case 4:
            Hapus();
            break;
        default:
            if(pilihan != 5)
            {
                printf("Masukan pilihan dari 1 sampai 4 saja");
            }
        getch();
        }
    } while(pilihan != 5);
}

void Awal()
{
    antrian.head = antrian.tail = -1;
}

int Kosong()
{
    if(antrian.tail == -1)
        return 1;
    else
        return 0;
}

int isFull()
{
    if(antrian.tail == MAX_QUEUE-1)
        return 1;
    else
        return 0;
}

void Masuk(int data)
{
    if(Kosong() == 1)
    {
        antrian.head = antrian.tail = 0;
        antrian.data[antrian.tail] = data;
        printf("%d telah masuk", antrian.data[antrian.tail]);
    }
    else if(isFull() == 0)
    {
        antrian.tail++;
        antrian.data[antrian.tail] = data;
        printf("%d masuk", antrian.data[antrian.tail]);
    }
}

int Keluar()
{
    int d = antrian.data[antrian.head];

    for(int i = antrian.head; i <= antrian.tail-1; i++)
    {
        antrian.data[i] = antrian.data[i+1];
    }
    antrian.tail--;
    return d;
}

void Tampil()
{
    if(Kosong() == 0)
    {
        for(int i = antrian.head; i <= antrian.tail; i++)
        {
            printf("%d ", antrian.data[i]);
        }
    }
    else
        printf("Antrian Kosong");
}

void Hapus()
{
    Awal();
    printf("Data dalam antrian telah dihapus");
}