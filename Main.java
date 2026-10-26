// Tugas Diskusi PBO: Abstract Class, Inheritance, dan Polymorphism// Studi Kasus: Sistem Rental Kendaraan (Mobil, Motor, dan Bus)
// ==========================================
// 1. ABSTRACT CLASS (Class Induk)
// ==========================================
// Menggunakan abstract class karena Kendaraan masih bersifat umum.
abstract class Kendaraan {
    String merk;
    String platNomor;
    int hargaPerHari;

    // Constructor class induk
    public Kendaraan(String merk, String platNomor, int hargaPerHari) {
        this.merk = merk;
        this.platNomor = platNomor;
        this.hargaPerHari = hargaPerHari;
    }

    // Method biasa (konkret) yang langsung diwariskan ke semua class anak
    public void infoDasar() {
        System.out.println("Merk Kendaraan  : " + merk);
        System.out.println("Plat Nomor      : " + platNomor);
        System.out.println("Harga Sewa/Hari : Rp" + hargaPerHari);
    }

    // ABSTRACT METHOD
    // Wajib di-override (diisi) oleh setiap class anak karena detail tiap kendaraan berbeda
    public abstract void tampilSpesifikasi();
    public abstract int hitungBiaya(int lamaHari);
}

// ==========================================
// 2. INHERITANCE (Class Anak 1 - Mobil)
// ==========================================
class Mobil extends Kendaraan {
    int jumlahKursi;
    boolean pakaiSupir;

    public Mobil(String merk, String platNomor, int hargaPerHari, int jumlahKursi, boolean pakaiSupir) {
        // Memanggil constructor milik class Kendaraan
        super(merk, platNomor, hargaPerHari);
        this.jumlahKursi = jumlahKursi;
        this.pakaiSupir = pakaiSupir;
    }

    @Override
    public void tampilSpesifikasi() {
        System.out.println("Jenis Kendaraan : Mobil Penumpang");
        System.out.println("Jumlah Kursi    : " + jumlahKursi + " Kursi");
        if (pakaiSupir == true) {
            System.out.println("Layanan Supir   : Ya (+Rp100.000/hari)");
        } else {
            System.out.println("Layanan Supir   : Tidak (Lepas Kunci)");
        }
    }

    @Override
    public int hitungBiaya(int lamaHari) {
        int total = hargaPerHari * lamaHari;

        // Tambahan biaya jika menggunakan supir
        if (pakaiSupir == true) {
            total = total + (100000 * lamaHari);
        }

        // Diskon Rp50.000 jika sewa mobil lebih dari 3 hari
        if (lamaHari > 3) {
            System.out.println("Promo           : Diskon sewa > 3 hari (-Rp50.000)");
            total = total - 50000;
        }
        return total;
    }
}

// ==========================================
// 2. INHERITANCE (Class Anak 2 - Motor)
// ==========================================
class Motor extends Kendaraan {
    String tipeMotor;
    int jmlHelm;

    public Motor(String merk, String platNomor, int hargaPerHari, String tipeMotor, int jmlHelm) {
        super(merk, platNomor, hargaPerHari);
        this.tipeMotor = tipeMotor;
        this.jmlHelm = jmlHelm;
    }

    @Override
    public void tampilSpesifikasi() {
        System.out.println("Jenis Kendaraan : Sepeda Motor (" + tipeMotor + ")");
        System.out.println("Jumlah Helm     : " + jmlHelm + " Buah");
    }

    @Override
    public int hitungBiaya(int lamaHari) {
        int total = hargaPerHari * lamaHari;

        // Helm pertama gratis, helm tambahan dikenakan biaya Rp10.000 per helm
        if (jmlHelm > 1) {
            int tambahanHelm = (jmlHelm - 1) * 10000;
            System.out.println("Biaya Tambahan  : Sewa helm ekstra (+Rp" + tambahanHelm + ")");
            total = total + tambahanHelm;
        }
        return total;
    }
}

// ==========================================
// 2. INHERITANCE (Class Anak 3 - Bus)
// ==========================================
class Bus extends Kendaraan {
    int kapasitas;
    boolean fasilitasAC;

    public Bus(String merk, String platNomor, int hargaPerHari, int kapasitas, boolean fasilitasAC) {
        super(merk, platNomor, hargaPerHari);
        this.kapasitas = kapasitas;
        this.fasilitasAC = fasilitasAC;
    }

    @Override
    public void tampilSpesifikasi() {
        System.out.println("Jenis Kendaraan : Bus Pariwisata");
        System.out.println("Kapasitas       : " + kapasitas + " Penumpang");
        if (fasilitasAC == true) {
            System.out.println("Fasilitas AC    : Tersedia (+Rp200.000/hari)");
        } else {
            System.out.println("Fasilitas AC    : Tidak Ada (Bus Ekonomi)");
        }
    }

    @Override
    public int hitungBiaya(int lamaHari) {
        int total = hargaPerHari * lamaHari;

        // Biaya tambahan untuk perawatan AC per hari
        if (fasilitasAC == true) {
            total = total + (200000 * lamaHari);
        }
        return total;
    }
}

// ==========================================
// CLASS UTAMA (Main Program)
// ==========================================
public class Main {
    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("     SISTEM RENTAL KENDARAAN MAJU JAYA    ");
        System.out.println("==========================================");

        // 3. POLYMORPHISM
        // Membuat array dengan tipe class induk (Kendaraan), 
        // tapi diisi dengan objek-objek dari class anak (Mobil, Motor, Bus)
        Kendaraan[] daftarSewa = new Kendaraan[3];

        daftarSewa[0] = new Mobil("Toyota Innova", "DR 1234 AB", 350000, 7, true);
        daftarSewa[1] = new Motor("Honda PCX 160", "DR 5678 XY", 90000, "Matic", 2);
        daftarSewa[2] = new Bus("Mercedes-Benz OH", "DR 9999 ZZ", 1200000, 40, true);

        // Array untuk menyimpan lama hari sewa masing-masing pelanggan
        int[] durasiSewa = {4, 2, 3};
        String[] namaPenyewa = {"Budi Santoso", "Siti Aminah", "Rombongan Kampus"};

        int totalPendapatanRental = 0;

        // Menampilkan data transaksi menggunakan perulangan
        for (int i = 0; i < daftarSewa.length; i++) {
            System.out.println("\n--- Data Transaksi ke-" + (i + 1) + " ---");
            System.out.println("Nama Penyewa    : " + namaPenyewa[i]);
            System.out.println("Lama Sewa       : " + durasiSewa[i] + " Hari");
            
            // Memanggil method dari class induk
            daftarSewa[i].infoDasar();
            
            // Polymorphism bekerja di sini:
            // Method tampilSpesifikasi() dan hitungBiaya() akan otomatis menyesuaikan 
            // dengan jenis objek aslinya (apakah Mobil, Motor, atau Bus)
            daftarSewa[i].tampilSpesifikasi();
            int biaya = daftarSewa[i].hitungBiaya(durasiSewa[i]);
            
            System.out.println("Total Bayar     : Rp" + biaya);
            System.out.println("------------------------------------------");

            totalPendapatanRental = totalPendapatanRental + biaya;
        }

        System.out.println("\nTotal Seluruh Pendapatan Rental: Rp" + totalPendapatanRental);
    }
}
