#include <conio.h>
#include <stdio.h>
#include <stdlib.h>

struct Node{
  int data;
  struct Node *kiri;
  struct Node *kanan;
};

void tambah(struct Node **root, int dataku)
{
    if ((*root) == NULL) 
    {
        struct Node *baru;
        baru = (struct Node*)malloc(sizeof(struct Node));
        baru->data = dataku;
        baru->kiri = NULL;
        baru->kanan = NULL;
        (*root) = baru;
        (*root)->kiri = NULL;
        (*root)->kanan = NULL;
        printf("Data bertambah!");
    }
    // jika data yang akan dimasukkan lebih kecil daripada elemen root, maka akan diletakkan di node sebelah kiri
    else if (dataku < (*root)->data)
        tambah(&(*root)->kiri, dataku);
    // jika data yang akan dimasukkan lebih besar daripada elemen root, maka akan diletakkan di node sebelah kanan
    else if (dataku > (*root)->data)
        tambah(&(*root)->kanan, dataku);
    // jika saat dicek data yang akan dimasukkan memiliki nilai yang sama dengan data pada root
    else if (dataku == (*root)->data)
        printf("Data sudah ada!");
}

void preOrder(struct Node *root) 
{
    if (root != NULL)
    {
        printf("%d ", root->data);
        preOrder(root->kiri);
        preOrder(root->kanan);
    }
}

void inOrder(struct Node *root) 
{
    if (root != NULL)
    {
        inOrder(root->kiri);
        printf("%d ", root->data);
        inOrder(root->kanan);
    }
}

void postOrder(struct Node *root) 
{
    if (root != NULL) 
    {
        postOrder(root->kiri);
        postOrder(root->kanan);
        printf("%d ", root->data);
    }
}

void clearTree(struct Node **root) 
{
    free(*(root));
    *(root) = NULL;
    printf("Berhasil hapus semua data!");
}

int main() 
{
    int pil, data;
    struct Node *tree;
    tree = NULL;

    do {
        system("cls"); // bersihkan layar
        printf("\tPROGRAM TREE Bahasa C");
        printf("\n\t=====================");
        printf("\nMENU");
        printf("\n----\n");
        printf("1. Input Data\n");
        printf("2. Lihat pre-order\n");
        printf("3. Lihat in-order\n");
        printf("4. Lihat post-order\n");
        printf("5. Hapus data tree\n");
        printf("6. Keluar\n");
        printf("Pilihan kamu? = "); scanf("%d", &pil);
        
        switch (pil) 
        {
        case 1:
            printf("\nINPUT : ");
            printf("\n-------");
            printf("\nData baru : ");
            scanf("%d", &data);
            tambah(&tree, data);
            break;
        case 2:
            printf("\nOUTPUT PRE ORDER : ");
            printf("\n------------------\n");
            if (tree)
                // panggil fungsi untuk mencetak data secara preOrder
                preOrder(tree);
            else
                printf("Masih kosong!");
            break;
        case 3:
            printf("\nOUTPUT IN ORDER : ");
            printf("\n-----------------\n");
            if (tree)
                // panggil fungsi untuk mencetak data secara preOrder
                inOrder(tree);
            else
                printf("Masih kosong!");
            break;
        case 4:
            printf("\nOUTPUT POST ORDER : ");
            printf("\n------------------\n");
            if (tree)
                // panggil fungsi untuk mencetak data secara postOrder
                postOrder(tree);
            else
                printf("Masih kosong!");
            break;
        case 5:
            if (tree)
                clearTree(&tree);
            else
                printf("Data kosong!");
        }
        getch();
    } while (pil != 6);
    free(tree);
}