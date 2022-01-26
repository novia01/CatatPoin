package com.mycompany.sistemcatatpoinkeaktifan;


public class Jabatan {
    private int IDJabatan;
    private String Organisasi;
    private String Tingkat;
    private String JenisJabatan;
    private String Keterangan;
    private String MasaJabatan;
    private int Poin;
    private String Status;
    
    
    public Jabatan(int IDJabatan, String Organisasi, String Tingkat, String JenisJabatan, String Keterangan, String MasaJabatan, int Poin, String Status){
        this.IDJabatan = IDJabatan;
        this.Organisasi = Organisasi;
        this.Tingkat = Tingkat;
        this.JenisJabatan = JenisJabatan;
        this.Keterangan = Keterangan;
        this.MasaJabatan = MasaJabatan;
        this.Poin = Poin;
        this.Status = Status;
    }

    Jabatan(int aInt, String string, String string0, String string1, String string2, String string3, int aInt0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Jabatan(int aInt, String string, String string0, String string1, String string2, String string3, int aInt0, String string4, String string5) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     public int getIDJabatan() {
        return IDJabatan;
    }

    public String getOrganisasi() {
        return Organisasi;
        
    }
    
    public String getTingkat() {
        return Tingkat;
        
    }
    
    public String getJenisJabatan() {
        return JenisJabatan;
    }   

    public String getKeterangan() {
        return Keterangan;
    }

    public String getMasaJabatan() {
        return MasaJabatan;
    }

    public int getPoin() {
        return Poin;
    }
    
    public String getStatus() {
        return Status;
    }
    
    
}
