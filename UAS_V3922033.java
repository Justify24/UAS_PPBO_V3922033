import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Kelas Barang untuk merepresentasikan data barang
class Barang {
    private String nama;
    private int jumlah;

    // Konstruktor untuk menginisialisasi data barang
    public Barang(String nama, int jumlah) {
        this.nama = nama;
        this.jumlah = jumlah;
    }

    // Getter untuk mendapatkan nama barang
    public String getNama() {
        return nama;
    }

    // Getter untuk mendapatkan jumlah barang
    public int getJumlah() {
        return jumlah;
    }

    // Metode untuk mengupdate jumlah barang
    public void updateJumlah(int jumlah) {
        this.jumlah += jumlah;
    }
}

// Class BarangElektronik sebagai turunan dari class Barang
class BarangElektronik extends Barang {
    private String merek;

    // Konstruktor untuk menginisialisasi data barang elektronik
    public BarangElektronik(String nama, int jumlah, String merek) {
        super(nama, jumlah);
        this.merek = merek;
    }

    // Getter untuk mendapatkan merek barang elektronik
    public String getMerek() {
        return merek;
    }
}

// Kelas InventoryManager untuk mengelola inventaris barang
class InventoryManager {
    private List<Barang> inventaris;

    // Konstruktor untuk membuat inventaris barang baru
    public InventoryManager() {
        inventaris = new ArrayList<>();
    }

    // Metode untuk menambahkan barang ke inventaris
    public void tambahBarang(Barang barang) {
        if (barang instanceof BarangElektronik) {
            BarangElektronik barangElektronik = (BarangElektronik) barang;
            if (barangElektronik.getMerek().isEmpty()) {
                System.out.println("Merek barang elektronik harus dicantumkan.");
                return;
            }
        }
        inventaris.add(barang);
        System.out.println("Barang berhasil ditambahkan ke inventaris.");
    }

    // Metode untuk mencari barang berdasarkan nama
    public void cariBarang(String nama) {
        boolean ditemukan = false;
        for (Barang barang : inventaris) {
            if (barang.getNama().equalsIgnoreCase(nama)) {
                System.out.println("Nama Barang: " + barang.getNama());
                System.out.println("Jumlah Barang: " + barang.getJumlah());
                if (barang instanceof BarangElektronik) {
                    BarangElektronik barangElektronik = (BarangElektronik) barang;
                    System.out.println("Merek Barang Elektronik: " + barangElektronik.getMerek());
                }
                ditemukan = true;
                break;
            }
        }
        if (!ditemukan) {
            System.out.println("Barang tidak ditemukan di inventaris.");
        }
    }

    // Metode untuk mengupdate jumlah barang dalam inventaris
    public void updateJumlahBarang(String nama, int jumlah) {
        boolean ditemukan = false;
        for (Barang barang : inventaris) {
            if (barang.getNama().equalsIgnoreCase(nama)) {
                barang.updateJumlah(jumlah);
                System.out.println("Jumlah barang berhasil diperbarui.");
                ditemukan = true;
                break;
            }
        }
        if (!ditemukan) {
            System.out.println("Barang tidak ditemukan di inventaris.");
        }
    }
}

// Kelas utama yang berisi metode main untuk menjalankan program
public class UAS_V3922033 {
    public static void main(String[] args) {
        InventoryManager inventoryManager = new InventoryManager();
        
        inventoryManager.tambahBarang(new Barang("Buku Tulis", 10));
        inventoryManager.tambahBarang(new Barang("Pensil", 20));
        inventoryManager.tambahBarang(new BarangElektronik("Laptop", 5, "Asus"));
        inventoryManager.tambahBarang(new BarangElektronik("PC", 5, "Razer"));
        inventoryManager.tambahBarang(new BarangElektronik("Mouse", 5, "Logitech"));

        
        
        Scanner scanner = new Scanner(System.in);
        int pilihan = 0;

        do {
            System.out.println("\n===== Aplikasi Manajemen Inventaris =====");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Cari Barang");
            System.out.println("3. Update Jumlah Barang");
            System.out.println("4. Keluar");
            System.out.print("Pilih opsi (1-4): ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan nama barang: ");
                    String namaBarang = scanner.nextLine();
                    System.out.print("Masukkan jumlah barang: ");
                    int jumlahBarang = scanner.nextInt();
                    scanner.nextLine();
                    if (namaBarang.equalsIgnoreCase("Barang Elektronik")) {
                        System.out.print("Masukkan merek barang elektronik: ");
                        String merek = scanner.nextLine();
                        inventoryManager.tambahBarang(new BarangElektronik(namaBarang, jumlahBarang, merek));
                    } else {
                        inventoryManager.tambahBarang(new Barang(namaBarang, jumlahBarang));
                    }
                    break;
                case 2:
                    System.out.print("Masukkan nama barang yang ingin dicari: ");
                    String cariBarang = scanner.nextLine();
                    inventoryManager.cariBarang(cariBarang);
                    break;
                case 3:
                    System.out.print("Masukkan nama barang yang ingin diupdate: ");
                    String updateNama = scanner.nextLine();
                    System.out.print("Masukkan perubahan jumlah barang (+/-): ");
                    int updateJumlah = scanner.nextInt();
                    scanner.nextLine();
                    inventoryManager.updateJumlahBarang(updateNama, updateJumlah);
                    break;
                case 4:
                    System.out.println("Terima kasih telah menggunakan aplikasi Manajemen Inventaris.");
                    break;
                default:
                    System.out.println("Opsi yang Anda pilih tidak valid.");
                    break;
            }
        } while (pilihan != 4);
    }
}
