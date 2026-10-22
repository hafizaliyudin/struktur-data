import java.util.Arrays;

// Tugas Praktik 2 - Struktur Data
// Mengurutkan data dari TERBESAR ke TERKECIL
public class Tugas2 {

    public static void main(String[] args) {
        // Soal 1: Merge Sort
        int[] data1 = {38, 27, 43, 3, 9, 82, 10, 55};
        System.out.println("=== MERGE SORT (Terbesar -> Terkecil) ===");
        System.out.println("Data awal   : " + Arrays.toString(data1));
        mergeSort(data1, 0, data1.length - 1);
        System.out.println("Data urut   : " + Arrays.toString(data1));

        System.out.println();

        // Soal 2: Counting Sort
        int[] data2 = {4, 2, 9, 7, 2, 5, 9, 1};
        System.out.println("=== COUNTING SORT (Terbesar -> Terkecil) ===");
        System.out.println("Data awal   : " + Arrays.toString(data2));
        int[] hasil = countingSort(data2);
        System.out.println("Data urut   : " + Arrays.toString(hasil));
    }

    // ---------- MERGE SORT ----------
    static void mergeSort(int[] arr, int kiri, int kanan) {
        if (kiri < kanan) {
            int tengah = (kiri + kanan) / 2;
            mergeSort(arr, kiri, tengah);        // bagi kiri
            mergeSort(arr, tengah + 1, kanan);   // bagi kanan
            merge(arr, kiri, tengah, kanan);     // gabung
        }
    }

    static void merge(int[] arr, int kiri, int tengah, int kanan) {
        int[] L = Arrays.copyOfRange(arr, kiri, tengah + 1);
        int[] R = Arrays.copyOfRange(arr, tengah + 1, kanan + 1);
        int i = 0, j = 0, k = kiri;

        // ambil yang lebih BESAR dulu -> urut menurun
        while (i < L.length && j < R.length) {
            if (L[i] >= R[j]) arr[k++] = L[i++];
            else arr[k++] = R[j++];
        }
        while (i < L.length) arr[k++] = L[i++];
        while (j < R.length) arr[k++] = R[j++];
    }

    // ---------- COUNTING SORT ----------
    static int[] countingSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();

        // hitung frekuensi tiap nilai
        int[] count = new int[max + 1];
        for (int x : arr) count[x]++;

        // susun hasil dari nilai terbesar ke terkecil
        int[] output = new int[arr.length];
        int idx = 0;
        for (int nilai = max; nilai >= 0; nilai--) {
            while (count[nilai] > 0) {
                output[idx++] = nilai;
                count[nilai]--;
            }
        }
        return output;
    }
}
