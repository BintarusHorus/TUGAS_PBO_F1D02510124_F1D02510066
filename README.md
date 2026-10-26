# Tugas Diskusi PBO - Abstract Class, Inheritance, & Polymorphism

**Anggota Kelompok:**
1. Muhammad Bintang Arifin (F1D02510124)
2. Lalu Hajiril Akbar (F1D02510066)

## Deskripsi Kasus
Program ini mensimulasikan sistem transaksi **Rental Kendaraan** yang menyewakan tiga jenis kendaraan: **Mobil**, **Motor**, dan **Bus**. Setiap jenis kendaraan memiliki spesifikasi dan aturan perhitungan biaya tambahan serta diskon yang berbeda-beda.

## Penerapan Materi pada Kode

1. **Abstract Class & Abstract Method (`Kendaraan`)**
   * `abstract class Kendaraan` menjadi kerangka dasar yang menyimpan atribut umum (`merk`, `platNomor`, dan `hargaPerHari`) serta method `infoDasar()`.
   * Memiliki 2 *abstract method* yaitu `tampilSpesifikasi()` dan `hitungBiaya(int lamaHari)` yang wajib di-*override* oleh semua class anaknya karena detail spesifikasi dan rumus perhitungan biayanya berbeda-beda.

2. **Inheritance / Pewarisan (`Mobil`, `Motor`, `Bus`)**
   * Ketiga class anak menggunakan kata kunci `extends Kendaraan` untuk mewarisi atribut dan method dari `Kendaraan`, serta memanggil constructor induk menggunakan `super(...)`.
   * Masing-masing class anak memiliki atribut unik: `Mobil` memiliki `jumlahKursi` dan `pakaiSupir`, `Motor` memiliki `tipeMotor` dan `jmlHelm`, sedangkan `Bus` memiliki `kapasitas` dan `fasilitasAC`.

3. **Polymorphism**
   * Diterapkan pada `Main.java` dengan membuat array bertipe class induk `Kendaraan[] daftarSewa = new Kendaraan[3];` yang diisi dengan objek `Mobil`, `Motor`, dan `Bus`.
   * Saat dilakukan perulangan (`for`), pemanggilan `daftarSewa[i].tampilSpesifikasi()` dan `daftarSewa[i].hitungBiaya(...)` secara otomatis mengeksekusi method milik masing-masing class anak (*Dynamic Method Dispatch / Method Overriding*).
