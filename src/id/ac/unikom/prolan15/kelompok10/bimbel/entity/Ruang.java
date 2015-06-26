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
public class Ruang {
    //Variable / Atribut Entitas
    private String kodeKelas;
    private int dayaTampung;
    private String infocus;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String isInfocus() {
        return infocus;
    }
    //Getter Setter
    public void setInfocus(String infocus) {
        this.infocus = infocus;
    }

    public String getInfocus() {
        return infocus;
    }

    public String getKodeKelas() {
        return kodeKelas;
    }

    public void setKodeKelas(String kodeKelas) {
        this.kodeKelas = kodeKelas;
    }

    public int getDayaTampung() {
        return dayaTampung;
    }

    public void setDayaTampung(int dayaTampung) {
        this.dayaTampung = dayaTampung;
    }   
}
