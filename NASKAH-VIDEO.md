# Naskah Video – Tugas Praktik 2 STSI4209 (±8 menit)

> **[LAYAR]** = yang ditampilkan. Teks biasa = yang diucapkan.
> Sebelum rekam: refresh `stok.html` & `tracking.html` supaya data kembali ke awal, dan zoom VS Code (Ctrl + =).

---

## 1. Pembukaan (00:00 – 00:30)

**[LAYAR]** `index.html` di browser

Assalamualaikum. Saya **[NAMA]**, NIM **[NIM]**. Pada video ini saya menjelaskan Tugas Praktik 2, yaitu aplikasi pemesanan bahan ajar SITTA menggunakan **Vue.js**, dengan dua halaman: **Stok Bahan Ajar** dan **Tracking DO**.

---

## 2. Struktur Proyek (00:30 – 01:30)

**[LAYAR]** Explorer VS Code

Strukturnya mengikuti soal: `index.html` untuk navigasi, `stok.html` dan `tracking.html` sebagai dua halaman utama, `css/style.css`, serta `stok-app.js` dan `tracking-app.js` untuk logika Vue. Saya menambahkan `dataBahanAjar.js` berisi dummy data, karena data ini dipakai oleh kedua halaman.

**[LAYAR: stok.html baris 266]**
Vue 2 dimuat lewat CDN dengan tag `<script>`, sesuai materi dan dummy data yang memakai `new Vue({ el: '#app' })`. Karena soal tidak meminta template component, saya tidak memakai `npm create vue`. Setiap file aplikasi disusun dengan urutan `data`, `computed`, `watch`, dan `methods`.

---

## 3. Halaman Stok – Binding & Conditional (01:30 – 03:00)

**[LAYAR: stok.html baris 125–148]**
Tabel dibuat dengan **`v-for`** dan `:key="item.kode"` karena kode bersifat unik. Data ditampilkan dengan **mustache** untuk judul dan harga, **`v-text`** untuk kategori, dan **`v-html`** untuk catatan, karena isinya mengandung tag HTML seperti `<em>`.

**`v-bind`** dipakai untuk binding satu arah, misalnya `:class` untuk mewarnai baris yang stoknya kosong. **`v-model`** dipakai untuk binding dua arah di semua filter dan form.

**[LAYAR: baris 144–146]**
Status memakai **`v-if`**, **`v-else-if`**, dan **`v-else`**: Kosong berwarna merah jika stok 0, Menipis berwarna oranye jika stok di bawah safety, dan Aman berwarna hijau untuk selain itu. Kondisi kosong dicek paling awal, karena stok 0 juga lebih kecil dari safety.

Untuk notifikasi saya memakai **`v-show`**, karena elemennya sering muncul dan hilang. `v-show` hanya menyembunyikan dengan CSS, sedangkan `v-if` benar-benar menghapus elemen dari halaman.

---

## 4. Computed, Methods & Demo Filter (03:00 – 04:30)

**[LAYAR: stok-app.js baris 50–85]**
Filter dibuat dengan **computed berantai**: `stokPerDaerah` → `stokPerKategori` → `stokTerfilter` → `stokTampil`. Computed di-**cache**, jadi hanya dihitung ulang jika data yang dipakainya berubah. Misalnya ketika saya hanya mengganti sort, proses filter tidak dijalankan ulang. Ini menjawab poin "filter tidak perlu recompute".

**Methods** dipakai untuk aksi atau fungsi yang butuh parameter, seperti `statusStok(item)`, `formatRupiah`, dan `resetFilter`.

**[LAYAR: demo browser]**
Saya pilih UT-Daerah Jakarta, lalu filter kategori baru muncul. Ini **dependent options** dengan `v-if`. Kemudian saya centang "perlu re-order", urutkan berdasarkan harga, lalu klik Reset.

---

## 5. Watchers (04:30 – 05:15)

**[LAYAR: stok-app.js baris 144–179]**
Contoh watcher yang saya pakai:
- **`filter.upbjj`**: ketika daerah diganti, filter kategori di-reset agar tidak tersisa kategori dari daerah sebelumnya.
- **`filter.reorder`**: ketika filter re-order aktif, data otomatis diurutkan dari stok paling sedikit.
- **`form.kode`**: input otomatis diubah menjadi huruf besar.
- **`stok`** dengan `deep: true`: mencatat waktu terakhir data diubah.

Di halaman tracking ada empat watcher lagi, sehingga totalnya sembilan.

---

## 6. Form & Validasi Stok (05:15 – 06:00)

**[LAYAR: klik "+ Tambah Bahan Ajar" → Simpan kosong → isi benar → Simpan]**
Validasi dibuat di computed `formErrors`. Kode harus 4 huruf dan 4 angka serta tidak boleh duplikat, format rak seperti R1-A3, dan harga serta stok harus berupa angka yang valid. Input angka memakai `v-model.number`. Pesan error baru muncul setelah tombol simpan ditekan. Form yang sama juga dipakai untuk **Edit**, dengan kode dibuat read-only.

---

## 7. Halaman Tracking DO (06:00 – 07:30)

**[LAYAR: tracking.html, ketik 123456789 di kolom cari]**
Pencarian memakai `v-model` dan watcher `kataKunci`. Jika yang diketik persis nomor DO atau NIM, detailnya langsung tampil, lengkap dengan timeline perjalanan.

**[LAYAR: tracking-app.js baris 57]**
Nomor DO dibuat oleh computed `nomorDOBaru`: "DO", tahun berjalan, lalu nomor urut tiga digit. Nomornya otomatis bertambah setiap ada DO baru.

**[LAYAR: isi form sambil dijelaskan]**
- Watcher **`form.nim`** hanya menerima angka, maksimal 9 digit.
- Ekspedisi diambil dari `pengirimanList`. Di soal tertulis `upbjjList`, tetapi data ekspedisinya sebenarnya ada di `pengirimanList`.
- Setelah paket dipilih, detail isi paket muncul di bawah `<select>`, dan watcher **`form.paket`** mengisi total harga otomatis.
- Tanggal kirim terisi hari ini menggunakan `Date`.

**[LAYAR: tracking-app.js baris 197 → klik Buat DO]**
DO baru disimpan dengan **`this.$set`**, karena di Vue 2 penambahan properti baru ke objek tidak reaktif tanpa `$set`. Hasilnya, DO baru langsung muncul di daftar.

---

## 8. Penutup (07:30 – 08:00)

Jadi pada tugas ini saya menerapkan struktur proyek Vue yang terpisah, mustache, `v-text`, `v-html`, `v-for`, `v-bind`, `v-model`, conditional `v-if` dan `v-show`, computed dan methods, sembilan watcher, serta form dengan validasi. Terima kasih. Wassalamualaikum.

---

*Hapus file ini sebelum mengumpulkan tugas.*
