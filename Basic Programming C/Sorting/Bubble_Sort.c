#include <stdio.h>

void bubble_sort(int data[], int length);

void main(){
    
    int data[] = {23, 67, 61, 5, 45, 69, 15, 27};

    bubble_sort(data, 8);
    printf("Sorting Data menggunakan Bubble : \n");
    for(int i = 0; i < 8; i++){
        printf("Data ke-%d = %d\n", i+1, data[i]);
    }
}

void bubble_sort(int data[], int length){
    for(int i = 0; i < 8; i++){
        for(int j = 0; j < 8-1; j++){
            if(data[j] > data[j + 1]){
                int temporary = data[j];
                data[j] = data[j + 1];
                data[j + 1] = temporary;
            }
        }
    }
}