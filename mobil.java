public class mobil {
    private String nama;
    private String platno;
    private int tahunkeluar;

    public String getNama(){
        return nama;
    }
    public void setNama(String nama){
        this.nama = nama;
    }
    public String getPlatno(){
        return platno;
    }
    public void setPlatno(String platno){
        this.platno = platno;
    }
    public int  getTahunkeluar(){
        return tahunkeluar;
    }
    public void setTahunkeluar(int tahunkeluar){
        this.tahunkeluar = tahunkeluar;
    }
    void info(){
        System.out.println( "nama mobil:"+ nama+ ""+"plat nomor"+ platno+ ""+ "tahun keluar" + tahunkeluar);

    }
    }

    class Main2{
        public static void main(String[]args){
            mobil honda = new mobil();
            honda.setNama("Honda");
            honda.setPlatno("B2234HD");
            honda.setTahunkeluar(2001);
            honda.info();}

         public static void main2(String[]args){
            mobil honda = new mobil();
            honda.setNama("Honda");
            honda.setPlatno("B2234HD");
            honda.setTahunkeluar(2001);
            honda.info();}

         public static void main3(String[]args){
            mobil honda = new mobil();
            honda.setNama("Honda");
            honda.setPlatno("B2234HD");
            honda.setTahunkeluar(2001);
            honda.info();

           
        }
    }

    
