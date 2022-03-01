#include <stdio.h>

void insertion_sort(int x[], int length);

void main(){
    
    int data[] = {23, 67, 61, 5, 45, 69, 15, 27};

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