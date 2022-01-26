/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistemcatatpoinkeaktifan;

/**
 *
 * @author acer
 */
public class Kemahasiswaan {
    private int IDKegiatan;
    private String JenisKegiatan;
    private int Poin;
    private String Sifat;
    private String Status;

    public Kemahasiswaan(int IDKegiatan, String JenisKegiatan, int Poin, String Sifat, String Status) {
        this.IDKegiatan = IDKegiatan;
        this.JenisKegiatan = JenisKegiatan;
        this.Poin = Poin;
        this.Sifat = Sifat;
        this.Status = Status;
    }

    Kemahasiswaan(int aInt, String string, int aInt0, String string0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIDKegiatan() {
        return IDKegiatan;
    }

    public String getJenisKegiatan() {
        return JenisKegiatan;
    }

    public int getPoin() {
        return Poin;
    }

    public String getSifat() {
        return Sifat;
    }
    
    public String getStatus() {
        return Status;
    }

    
         
}
