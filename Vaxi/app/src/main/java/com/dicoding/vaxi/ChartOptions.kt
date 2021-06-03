package com.dicoding.vaxi

enum class Pilihan {
    VAKSINASI1, VAKSINASI2
}

enum class Periode(val jumlahHari:Int) {
    MINGGU (7),
    BULAN(30),
    MAKSIMUM(-1)

}