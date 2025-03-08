import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Database db = new Database();

    while (true) {
      tampilMenu();
      System.out.print("pilih : ");
      char pilih = sc.next().charAt(0);
      pilih = Character.toUpperCase(pilih);
      switch (pilih) {
        case 'C':
          db.tambah();
          break;
        case 'R':
          db.view();
          break;
        case 'U' :
          db.view();
          System.out.print("masukkan nim : ");
          String nim = sc.next();
          db.update(nim);
          break;
        case 'D' :
          db.view();
          System.out.print("masukkan nim : ");
          String nimDel = sc.next();
          db.delete(nimDel);
          break;
      }

    }
  }

  public static void tampilMenu() {
    System.out.println();
    System.out.println("+=====================+");
    System.out.println("|     SIMPLE CRUD     |");
    System.out.println("+---------------------+");
    System.out.println("|      [C] CREATE     |");
    System.out.println("|       [R] READ      |");
    System.out.println("|      [U] UPDATE     |");
    System.out.println("|      [D] DELETE     |");
    System.out.println("+---------------------+");
  }
}
