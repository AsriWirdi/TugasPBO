import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Database {
  //field
  public ArrayList<TipeData> data = new ArrayList<>();
  Scanner sc = new Scanner(System.in);
  private Path path = Path.of("src/database.csv");
  TipeData mhs = new TipeData(null, null, null, 0, 0, 0.0);

  // construktor
  public Database() {
    start();
  }

  public void start() {
    try {
      List<String> lines = Files.readAllLines(path);
      for (int i = 1; i < lines.size(); i++) {
        String line = lines.get(i);
        String[] element = line.split(";");
        String nim = element[0];
        String nama = element[1];
        String alamat = element[2];
        int semester = Integer.parseInt(element[3]);
        int sks = Integer.parseInt(element[4]);
        double ipk = Double.parseDouble(element[5]);
        mhs = new TipeData(nim, nama, alamat, semester, sks, ipk);
        data.add(mhs);
      }

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

//  public boolean cek(String nim) {
//    boolean status = true;
//    if (!data.isEmpty()) {
//      for (int i = 0; i < data.size(); i++) {
//        if (data.get(i).getNim().equalsIgnoreCase(nim)) {
//          status = false;
//          break;
//        }
//      }
//    }
//    return status;
//  }

  public int searchIndex(String nim) {
    int index = -1;
    if (!data.isEmpty()) {
      for (int i = 0; i < data.size(); i++) {
        if (data.get(i).getNim().equalsIgnoreCase(nim)) {
          index = i;
        }
      }
    }
    return index;
  }

  public void save() {

    StringBuilder sb = new StringBuilder();

    if (!data.isEmpty()) {
      sb.append("NIM;NAMA;ALAMAT;SEMESTER;SKS;IPK\n");

      for (int i = 0; i < data.size(); i++) {
        mhs = data.get(i);
        String line = mhs.getNim() + ";" + mhs.getNama() + ";" + mhs.getAlamat() + ";" + mhs.getSemester() + ";" + mhs.getSks() + ";" + mhs.getIpk() + "\n";
        sb.append(line);

      }
      try {
        Files.writeString(path, sb);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }


  public void tambah() {
    Scanner sc = new Scanner(System.in);
    System.out.print("masukkan nim baru : ");
    String nim = sc.nextLine();

    boolean status = true; // nilai awal true
      for (int i = 0; i < data.size(); i++) {
        if (data.get(i).getNim().equalsIgnoreCase(nim)) {
          status = false; // program berhenti karena nimnya sudah ada sebelumnya di database
        }
      }

      if (status == true) {

        System.out.print("masukkan nama : ");
        String nama = sc.nextLine();
        System.out.print("masukkan alamat : ");
        String alamat = sc.nextLine();
        System.out.print("masukkan semester : ");
        int semester = sc.nextInt();
        System.out.print("masukkan sks : ");
        int sks = sc.nextInt();
        System.out.print("masukkan ipk : ");
        double ipk = sc.nextDouble();

        System.out.printf("Apakah anda yakin ingin menambahkan (%s,%s) (Y/N)? : ", nim, nama);
        char yn = sc.next().charAt(0);
        yn = Character.toUpperCase(yn);
        if (yn == 'Y') {
          mhs = new TipeData(nim, nama, alamat, semester, sks, ipk);
          data.add(mhs);
          save();
          System.out.println("Data berhasil disimpan");
        }
      } else{
        System.err.printf("! : nim %s telah ada di database\n",nim);
      }

  }

    public void delete(String nim){
      boolean status = false; // program berhenti ketika nimnya tidak di temukan
      if (!data.isEmpty()) {
        for (int i = 0; i < data.size(); i++) {
          if (data.get(i).getNim().equalsIgnoreCase(nim)) {
            status = true; // true karena menemukan nim yang sama
          }

        }

        if (status == true) {
          System.out.printf("Apakah anda yakin ingin menghapus %s (Y/N)? : ", nim);
          char yn = sc.next().charAt(0);
          yn = Character.toUpperCase(yn);
          if (yn == 'Y') {
            data.remove(searchIndex(nim));
            save();
            System.out.println("Data berhasil disimpan");
          }
        }else {
          System.err.println("! : nim yang di masukkan tidak cocok");
        }
      }
    }


    public void update (String nim){
      boolean status = false; // program berhenti ketika nimnya tidak di temukan
      if (!data.isEmpty()) {
        for (int i = 0; i < data.size(); i++) {
          if (data.get(i).getNim().equalsIgnoreCase(nim)) {
            status = true; // true karena menemukan nim yang sama
          }
        }
      }

      if (status == true) {
        Scanner sc = new Scanner(System.in);
        System.out.print("masukkan nim baru : ");
        String nimBaru = sc.nextLine();
        System.out.print("masukkan nama baru : ");
        String namaBaru = sc.nextLine();
        System.out.print("masukkan alamat baru : ");
        String alamatBaru = sc.nextLine();
        System.out.print("masukkan semester : ");
        int semesterBaru = sc.nextInt();
        System.out.print("masukkan sks : ");
        int sksBaru = sc.nextInt();
        System.out.print("masukkan ipk : ");
        double ipkBaru = sc.nextDouble();
        System.out.printf("Apakah anda yakin ingin mengupdate %s (Y/N)? : ", nim);
        char yn = sc.next().charAt(0);
        yn = Character.toUpperCase(yn);
        if (yn == 'Y') {
          mhs = new TipeData(nimBaru, namaBaru, alamatBaru, semesterBaru, sksBaru, ipkBaru);
          data.set(searchIndex(nim), mhs);
          save();
          System.out.println("Data berhasil disimpan");
        } else {
          System.out.println("Dibatalkan");
        }

      } else {
        System.err.println("! : nim yang di masukkan tidak cocok");
      }
    }

    public void view () {
      System.out.println("==================================================================================");
      System.out.printf("| %-8.8S |", "NIM");
      System.out.printf(" %-20.20S |", "NAMA");
      System.out.printf(" %-20.20S |", "ALAMAT");
      System.out.printf(" %-8.8S |", "SEMESTER");
      System.out.printf(" %-3.3S |", "SKS");
      System.out.printf(" %-4.4S |%n", "IPK");
      System.out.println("----------------------------------------------------------------------------------");

      // looping baca data
      for (TipeData mhs : data) {
        System.out.printf("| %-8S |", mhs.getNim());
        System.out.printf(" %-20.20S |", mhs.getNama());
        System.out.printf(" %-20.20S |", mhs.getAlamat());
        System.out.printf(" %-8.8S |", mhs.getSemester());
        System.out.printf(" %-3.3S |", mhs.getSks());
        System.out.printf(" %-4.4S |", mhs.getIpk());
        System.out.println();
      }
      System.out.println();
    }

  }
