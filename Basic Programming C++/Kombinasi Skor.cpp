// Nama : Adi Kurniawan
// NIM  : 672021172

#include <iostream>
#include <vector>
using namespace std;

vector<vector<int>> res;
vector<int> values;
int total = 0;

void fungsi_pembantu(vector<int>& current_poin, int current, int i){

    int old = current;

    if(i == values.size())
        return;

    int val = values[i];

    while(current<total){
        current += val;
        current_poin.push_back(val);
    }
    if(current == total){
        res.push_back(current_poin);
    }
    while (current > old) {
        current -= val;
        current_poin.pop_back();
        if (current >= 0) {
            fungsi_pembantu(current_poin, current, i+1);
        }
    }
}

int main() {       

    cout << "Masukan total poinnya : ";
    cin >> total;
    values = {2,3,5};
    vector<int> tag_poin;

    fungsi_pembantu(tag_poin, 0, 0);

    for (int i = 0; i < res.size(); i++) {
    int tengah = 0, luar = 0, dalam = 0;
        for (int j = 0; j < res[i].size(); j++) {
            if(res[i][j] == 5){
                tengah++;
            }
            else if(res[i][j] == 3){
                luar++;
            }
            else if(res[i][j] == 2){
                dalam++;
            }
        }
        cout << tengah << " " << luar << " " << dalam << endl;
        // Print Tengah 5 poin , Luar 3 poin, Dalam 2 poin
    }
    return 0;
}