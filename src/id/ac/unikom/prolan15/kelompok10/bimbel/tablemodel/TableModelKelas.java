/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.unikom.prolan15.kelompok10.bimbel.tablemodel;

import id.ac.unikom.prolan15.kelompok10.bimbel.entity.Kelas;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class TableModelKelas extends AbstractTableModel{

    private List<Kelas> list = new ArrayList<>();
    
    public void setKelas (List<Kelas> list) {
        this.list = list;
        fireTableDataChanged();
    }
    
    public void addKelas (Kelas p) {
        list.add(p);
        fireTableDataChanged();
    }
    
    public void removeKelas (int row) {
        list.remove(row);
        fireTableDataChanged();
    }
    
    public Kelas getKelas (int row) {
        return list.get(row);
    }   
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0 :    
                return list.get(rowIndex).getKode();
            case 1 :
                return list.get(rowIndex).getKodeMataPelajaran();
            case 2 :
                return list.get(rowIndex).getNoruang();    
            case 3 :
                return list.get(rowIndex).getPaket(); 
            default:
                return null;
                
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Kode Kelas";
            case 1:
                return "Kode Mata Peljaran";
            case 2:
                return "No Ruangan";
            case 3:
                return "Paket";
            default:
                return null;
        } //To change body of genedsrated methods, choose Tools | Templates.
    }
    
    public void editKelas(int row, Kelas p) {
        list.set(row, p);
        fireTableDataChanged();
    }
}
