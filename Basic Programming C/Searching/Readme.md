# Searching Operation

Searching data sering juga disebut Table look-up atau Storage and Retrieval Information merupakan suatu teknik pencarian data dengan mengumpulkan sejumlah informasi dari sekumpulan data kemudian mencari kembali informasi yang diperlukan secepat mungkin.

### Dua Macam Metode Searching

1. [Sequential Searching](https://github.com/adiikurr/swcu/blob/main/Basic%20Programming%20C/Searching/Sequential_Searching.c)
  
  * Metode ini disebut dengan pencarian linear sebab prosesnya membandingkan data yang ada satu per satu secara berurutan dengan apa yang dicari sampai data tersebut ditemukan atau tidak.
  * Pada kasus terburuk, untuk N elemen data harus dilakukan pencarian sebanyak N kali pula.
  * Metode pencarian linear merupakan metode yang paling sederhana

2. [Binary Searching](https://github.com/adiikurr/swcu/blob/main/Basic%20Programming%20C/Searching/Binary_Searching1.c)
  
  * Metode ini bekerja dengan menelusuri semua elemen-elemen array dari awal sampai akhir, dimana data-data yang ada perlu diurutkan terlebih dahulu.
  * Metode ini menggunakan prinsip :
    - Mula-mula diambil posisi awal 0 dan posisi akhir = N-1, kemudian dicari posisi data tengah dengan rumus (posisi awal + posisi akhir)/2. Kemudian data yang dicari dibandingkan dengan data tengah. 
    - Jika lebih kecil, proses dilakukan kembali tetapi posisi akhir dianggap sama dengan posisi tengah –1. 
    - Jika lebih besar, proses dilakukan kembali tetapi posisi awal dianggap sama dengan posisi tengah +1.
    - Dan jika data sama, berarti ketemu.
