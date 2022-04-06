#include <stdio.h>
#include <conio.h>
#include <stdlib.h>

void nodeAwal();
void nodeAkhir();
void nodeSembarang();
void showNode();
void hapusData();

struct node{
    int data;
    struct node* next;
};
struct node *head;
int item;

int main()
{
    int pilihan = 0;
    label:
    system("cls");
    printf("===== Menu Latihan Linked List =====\n");
    printf("1. Input data di Node awal\n2. Input data di Node akhir\n3. Input data di Node sembarang\n4. Lihat data dalam Linked List\n5. Hapus Data\n6. Exit Program\n\n");
    printf("Masukan Pilihan : "); scanf("%d", &pilihan);

    switch(pilihan)
    {
        case 1:
            nodeAwal();

            printf("\n\nTekan apa saja untuk kembali ke menu utama ");
            getch();
            goto label;
            break;
        case 2:
            nodeAkhir();

            printf("\n\nTekan apa saja untuk kembali ke menu utama ");
            getch();
            goto label;
            break;
        case 3:
            nodeSembarang();
            
            printf("\n\nTekan apa saja untuk kembali ke menu utama ");
            getch();
            goto label;
            break;
        case 4:
            showNode();
            
            printf("\n\nTekan apa saja untuk kembali ke menu utama ");
            getch();
            goto label;
            break;
        case 5:
            hapusData();
            
            printf("\n\nTekan apa saja untuk kembali ke menu utama ");
            getch();
            goto label;
            break;
        case 6:
            exit(0);
            break;
        default:
            printf("Silahkan masukkan pilihanmu.....");
    }
}

void nodeAwal(){

    struct node *pointer;
    pointer = (struct node *) malloc(sizeof(struct node *));
    if(pointer == NULL){
        printf("\n OVERFLOW");
    }
    else {
        printf("Masukkan Data : "); scanf("%d", &item);
        pointer->data = item;
        pointer->next = head;
        head = pointer;
        printf("\nData berhasil disimpan di Node awal!");
    }
}

void nodeAkhir(){
    struct node *pointer, *temp;
    pointer = (struct node *) malloc(sizeof(struct node *));
     if(pointer == NULL)
    {
        printf("\n OVERFLOW");
    }
    else
    {
        printf("Masukkan Data: "); scanf("%d", &item);
        pointer->data = item;
        if(head == NULL)
        {
            pointer->next = NULL;
            head = pointer;
            printf("Berhasil menyimpan dalam NODE!!!");
        }
        else
        {
            temp = head;
            while(temp -> next != NULL)
            {
                temp = temp->next;
            }
            temp->next = pointer;
            pointer->next = NULL;
            printf("Data berhasil disimpan di Node akhir!");
        }
    }
}

void nodeSembarang(){
    int lokasi;
    struct node *pointer, *temp;
    pointer = (struct node *)malloc(sizeof(struct node));
    if(pointer == NULL)
    {
        printf("\n OVERFLOW");
    }
    else
    {
        printf("Masukkan Data: "); scanf("%d", &item);
        pointer->data = item;
        printf("Mau simpan di lokasi mana? "); scanf("%d", &lokasi);
        temp = head;
        for(int i = 0; i < lokasi; i++)
        {
            temp = temp->next;
            if(temp == NULL)
            {
                printf("\n Tidak dapat tersimpan!");
                return;
            }
        }
            pointer->next = temp->next;
            temp->next = pointer;
            printf("Data berhasil disimpan di Node");
    }

}

void showNode(){
    struct node *pointer;
    pointer = head;
    if(pointer == NULL)
    {
        printf("Tidak ada data!!");
    }
    else
    {
        printf("Cetak data...\n");
        while(pointer!=NULL)
        {
            printf("%d\n", pointer->data);
            pointer = pointer-> next;
        }
    }
}

void hapusData(){
    struct node *ptr;  
    if(head == NULL)  
    {  
        printf("\nList kosong bro!\n");  
    }  
    else   
    {  
        ptr = head;  
        head = ptr->next;  
        free(ptr);  
        printf("\nNode awal berhasil dihapus!\n");  
    }
}