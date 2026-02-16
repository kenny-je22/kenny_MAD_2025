public class manusia {
    private String nama;
    private int umur;

    public String getNama(){
        return nama;

    }
    public void setNama(String nama){
        this.nama = nama;

    }
    public int getUmur(){
        return umur;

    }
    public void setUmur(int umur){
        this.umur = umur;

    }
    void info(){
        System.out.println("nama" + nama + " " + "umur:" + umur);

    }
}
class Main1{
    public static void main(String[]args) {
        manusia kenny = new manusia();
        kenny.setNama("kenny");
        kenny.setUmur(24);
        kenny.info();
    }
}