# Tugas Praktik 2 – Struktur Data

> Salin isi file ini ke **Format Laporan Tugas Berpraktik Prodi SI.docx**.
> Sesuaikan nama, NIM, dan link video.

- **Nama**  : Muhamad Hafizh Aliyuddin
- **NIM**   : ..........
- **Link Video** : https://...........  (pastikan akses "Siapa saja yang memiliki link dapat melihat")

---

## Soal 1 – Merge Sort (Terbesar → Terkecil)

### Source Code
(tempel isi `MergeSortDescending.java`)

### Output Program
```
=== MERGE SORT (TERBESAR -> TERKECIL) ===
Data awal    : [38, 27, 43, 3, 9, 82, 10, 55]

Langkah 1: gabung [38] + [27] -> [38, 27]
Langkah 2: gabung [43] + [3] -> [43, 3]
Langkah 3: gabung [38, 27] + [43, 3] -> [43, 38, 27, 3]
Langkah 4: gabung [9] + [82] -> [82, 9]
Langkah 5: gabung [10] + [55] -> [55, 10]
Langkah 6: gabung [82, 9] + [55, 10] -> [82, 55, 10, 9]
Langkah 7: gabung [43, 38, 27, 3] + [82, 55, 10, 9] -> [82, 55, 43, 38, 27, 10, 9, 3]

Data terurut : [82, 55, 43, 38, 27, 10, 9, 3]
```

### Cara Kerja
Merge Sort memakai strategi **Divide and Conquer**:

1. **Divide** – array dibagi dua di titik tengah secara rekursif sampai tiap bagian tinggal 1 elemen (array 1 elemen pasti sudah terurut).
   ```
   [38, 27, 43, 3, 9, 82, 10, 55]
   [38, 27, 43, 3]         [9, 82, 10, 55]
   [38, 27]  [43, 3]       [9, 82]  [10, 55]
   [38] [27] [43] [3]      [9] [82] [10] [55]
   ```
2. **Conquer/Merge** – bagian-bagian kecil digabung kembali. Saat menggabung, dua elemen terdepan dibandingkan dan **yang lebih besar diambil lebih dulu** (`L[i] >= R[j]`). Inilah yang membuat hasilnya urut **menurun (descending)**. Kalau ingin ascending cukup diubah menjadi `<=`.

### Analisis Kinerja
| Aspek | Nilai | Penjelasan |
|---|---|---|
| Best case | O(n log n) | Array tetap dibagi dan digabung penuh walau sudah terurut |
| Average case | O(n log n) | |
| Worst case | O(n log n) | Tidak tergantung susunan data awal |
| Memori tambahan | O(n) | Butuh array sementara `L` dan `R` saat proses merge |
| Stable | Ya | Nilai sama mempertahankan urutan asal (karena `>=`) |
| In-place | Tidak | |

**Mengapa O(n log n)?** Array berukuran n dibagi dua terus sehingga terbentuk **log₂ n** tingkat (untuk n = 8 → 3 tingkat). Di setiap tingkat, proses merge menyentuh total **n** elemen. Jadi total kerja = n × log₂ n. Rumus rekurensinya: T(n) = 2T(n/2) + O(n) = O(n log n).

Pada program ini n = 8 → 3 tingkat pembagian dan 7 kali proses merge (n − 1), dengan jumlah perbandingan maksimal sekitar n log₂ n = 8 × 3 = 24.

**Menurut saya**, Merge Sort sangat baik untuk data berukuran besar karena kinerjanya **konsisten O(n log n)** di semua kasus (tidak seperti Quick Sort yang bisa jatuh ke O(n²)) dan bersifat stable. Kekurangannya adalah butuh memori tambahan sebesar n, sehingga kurang cocok bila memori sangat terbatas. Untuk data yang sangat kecil, algoritma sederhana seperti Insertion Sort bisa lebih cepat karena overhead rekursi Merge Sort.

---

## Soal 2 – Counting Sort (Terbesar → Terkecil)

### Source Code
(tempel isi `CountingSortDescending.java`)

### Output Program
```
=== COUNTING SORT (TERBESAR -> TERKECIL) ===
Data awal    : [4, 2, 9, 7, 2, 5, 9, 1]

Langkah 1 - min = 1, max = 9, rentang k = 9
Langkah 2 - frekuensi          : {1=1, 2=2, 3=0, 4=1, 5=1, 6=0, 7=1, 8=0, 9=2}
Langkah 3 - akumulasi (desc)   : {1=8, 2=7, 3=5, 4=5, 5=4, 6=3, 7=3, 8=2, 9=2}
Langkah 4 - hasil penempatan   : [9, 9, 7, 5, 4, 2, 2, 1]

Data terurut : [9, 9, 7, 5, 4, 2, 2, 1]
```

### Cara Kerja
Counting Sort **tidak membandingkan antar elemen**, melainkan **menghitung** kemunculan tiap nilai.

1. **Cari min & max** → rentang nilai k = max − min + 1 (di sini 9 − 1 + 1 = 9).
2. **Hitung frekuensi** tiap nilai ke array `count`. Contoh: angka 9 muncul 2 kali, angka 2 muncul 2 kali.
3. **Akumulasi dari nilai terbesar ke terkecil** (kunci descending). Setelah langkah ini, `count[v]` = banyaknya elemen yang **≥ v**, yang sekaligus menjadi posisi terakhir v di array output. Contoh: `9=2` berarti angka 9 menempati posisi 1–2, `7=3` berarti angka 7 di posisi 3.
   (Untuk ascending, akumulasi dilakukan dari kecil ke besar.)
4. **Tempatkan elemen** ke array output dengan membaca input dari belakang, lalu kurangi `count` setiap kali dipakai. Membaca dari belakang menjaga sifat **stable**.

### Analisis Kinerja
| Aspek | Nilai | Penjelasan |
|---|---|---|
| Best / Average / Worst | O(n + k) | n = jumlah data, k = rentang nilai |
| Memori tambahan | O(n + k) | Array `count` (k) dan array `output` (n) |
| Stable | Ya | Karena penempatan dibaca dari belakang |
| Berbasis perbandingan | Tidak | Sehingga bisa lebih cepat dari batas O(n log n) |

**Rincian:** mencari min/max = n, menghitung frekuensi = n, akumulasi = k, penempatan = n. Total ≈ 3n + k → **O(n + k)**. Pada program ini n = 8 dan k = 9, jadi sekitar 33 operasi dasar.

**Menurut saya**, Counting Sort adalah algoritma yang **sangat cepat (linear)** asalkan datanya berupa **bilangan bulat dengan rentang nilai kecil** (misal nilai ujian 0–100, umur, dsb.). Namun jika rentang nilainya sangat besar dibanding jumlah data (contoh: data [1, 1.000.000]), array `count` menjadi sangat besar sehingga boros memori dan waktu. Counting Sort juga tidak bisa langsung dipakai untuk data pecahan/desimal atau string.

---

## Perbandingan Singkat

| | Merge Sort | Counting Sort |
|---|---|---|
| Kompleksitas waktu | O(n log n) | O(n + k) |
| Memori tambahan | O(n) | O(n + k) |
| Stable | Ya | Ya |
| Jenis data | Apa saja yang bisa dibandingkan | Bilangan bulat, rentang kecil |
| Cocok untuk | Data umum berukuran besar | Data integer dengan rentang sempit |

## Kesimpulan
Kedua algoritma berhasil mengurutkan data dari terbesar ke terkecil. Merge Sort unggul dalam **keumuman dan konsistensi** (selalu O(n log n)), sedangkan Counting Sort unggul dalam **kecepatan** (O(n + k)) tetapi hanya efisien jika rentang nilai data kecil.

---

## Lampiran – Naskah Singkat Video (maks. 15 menit, wajah terlihat)

1. **Pembukaan (±1 menit)** – Salam, perkenalan nama & NIM, sebut tugas yang dibahas.
2. **Merge Sort (±5 menit)**
   - Tunjukkan nilai a1–a8 di kode.
   - Jelaskan `mergeSort()` (membagi) dan `merge()` (menggabung).
   - Tekankan baris `if (L[i] >= R[j])` sebagai kunci descending.
   - Jalankan program, jelaskan Langkah 1–7 di output.
   - Jelaskan analisis O(n log n).
3. **Counting Sort (±5 menit)**
   - Tunjukkan nilai a1–a8 (ada angka kembar 2 dan 9).
   - Jelaskan 4 langkah: min/max → frekuensi → akumulasi dari besar ke kecil → penempatan.
   - Jalankan program, cocokkan output tiap langkah.
   - Jelaskan analisis O(n + k).
4. **Perbandingan & Penutup (±2 menit)** – Bandingkan kedua algoritma, kesimpulan, salam penutup.
