/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.unikom.prolan15.kelompok10.bimbel.entity;

/**
 *
 * @author Ihsan
 */
public class MataPelajaran {
    //Variable / Atribut Entitas
    private String kodeMaPel;
    private String namaMaPel;
    private String katagori;
    private int sks;
    
    //Getter Setter

    public String getKodeMaPel() {
        return kodeMaPel;
    }

    public void setKodeMaPel(String kodeMaPel) {
        this.kodeMaPel = kodeMaPel;
    }

    public String getNamaMaPel() {
        return namaMaPel;
    }

    public void setNamaMaPel(String namaMaPel) {
        this.namaMaPel = namaMaPel;
    }

    public String getKatagori() {
        return katagori;
    }

    public void setKatagori(String katagori) {
        this.katagori = katagori;
    }

    public int getSks() {
        return sks;
    }

    public void setSks(int sks) {
        this.sks = sks;
    }
    
}
