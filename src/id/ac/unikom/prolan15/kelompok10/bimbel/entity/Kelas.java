/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.unikom.prolan15.kelompok10.bimbel.entity;

/**
 *
 * @author user
 */
public class Kelas {
    //Variable / Atribut Entitas
    private String kode;
    private String kodeMataPelajaran;
    private int noruang;
    private String paket;
    
    //Getter Setter

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getKodeMataPelajaran() {
        return kodeMataPelajaran;
    }

    public void setKodeMataPelajaran(String kodeMataPelajaran) {
        this.kodeMataPelajaran = kodeMataPelajaran;
    }

    public int getNoruang() {
        return noruang;
    }

    public void setNoruang(int noruang) {
        this.noruang = noruang;
    }

    public String getPaket() {
        return paket;
    }

    public void setPaket(String paket) {
        this.paket = paket;
    }
     
}
