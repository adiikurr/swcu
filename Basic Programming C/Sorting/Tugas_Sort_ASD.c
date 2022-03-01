#include <stdio.h>

// Nama : Adi Kurniawan
// NIM  : 672021172
// NIM Genap (Tugas Selection & Insertion Sort secara Ascending)

void insertion_sort(int x[], int length);
void swap(int *a, int *b);
void selectionSort (int arr[], int size);
void cetakArray(int arr[], int size);

void main(){

    int data[] = {23, 67, 89, 5, 45, 20, 15, 27};
    int size = sizeof(data) / sizeof(data[0]);

        selectionSort(data, size);
        printf("Sorting Data dengan Selection (Ascending) : \n");
        cetakArray(data, size);

        printf("Sorting Data dengan Insertion (Ascending) : \n");
        insertion_sort(data, 8);
        for(int i = 0; i < 8; i++){
            printf("Data ke-%d = %d\n", i+1, data[i]);
        }
}

void insertion_sort(int x[], int length){
    for(int i = 1; i < length; i++){
        int kunci = x[i];
        int j = i - 1;

        while(j >= 0 && x[j] > kunci){
            x[j + 1] = x[j];
            j = j - 1;
        }
        x[j + 1] = kunci;
    }
}

void swap(int *a, int *b){
    int temporary = *a;
    *a = *b;
    *b = temporary;
}

void selectionSort (int arr[], int size){
    for(int step = 0; step < size-1; step++){
        int min_idx = step;

        for(int i = step+1; i < size; i++){
            if(arr[i] < arr[min_idx]){
                min_idx = i;
            }
        }
        swap(&arr[min_idx], &arr[step]);
    }
}

void cetakArray(int arr[], int size){
    for(int i = 0; i < size; ++i){
        printf("Data ke-%d = %d\n", i+1, arr[i]);
    }
    printf("\n");
}