#include <stdio.h>

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
        printf("%d ", arr[i]);
    }
    printf("\n");
}

void main(){
    int data[] = {23, 67, 61, 5, 45, 69, 15, 27};
    int size = sizeof(data) / sizeof(data[0]);
    selectionSort(data, size);

    printf("Data setelah diurutkan dari kecil ke besar (Ascending) : \n");
    cetakArray(data, size);
}