public class TipeData {
//  field
  private String nim;
  private String nama;
  private String alamat;
  private int semester;
  private int sks;
  private double ipk;

  // construktor
  // jadi, Setiap instance class TipeData wajib isi parameter construktornya dan valuenya di simpan di field class Tipe Data
  public TipeData(String nim, String nama, String alamat, int semester, int sks, double ipk) {
    this.nim = nim;
    this.nama = nama;
    this.alamat = alamat;
    this.semester = semester;
    this.sks = sks;
    this.ipk = ipk;
  }

  //  getter
  public String getNim() {
    return nim;
  }

  public String getNama() {
    return nama;
  }

  public String getAlamat() {
    return alamat;
  }

  public int getSemester() {
    return semester;
  }

  public int getSks() {
    return sks;
  }


  public double getIpk() {
    return ipk;
  }


  // mengoverride method toString agar nanti saat di print kelasnya akan tampil persis seperti apa yang di return
  // note : tidak wajib di override, kalau tidak di override nanti muncul hashcode dari kelasnya.
  @Override
  public String toString() {
    return "TipeData{" +
            "nim='" + nim + '\'' +
            ", nama='" + nama + '\'' +
            ", alamat='" + alamat + '\'' +
            ", semester=" + semester +
            ", sks=" + sks +
            ", ipk=" + ipk +
            '}';
  }
}
