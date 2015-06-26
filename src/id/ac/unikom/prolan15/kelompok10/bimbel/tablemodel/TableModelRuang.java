/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.unikom.prolan15.kelompok10.bimbel.tablemodel;

import id.ac.unikom.prolan15.kelompok10.bimbel.entity.Ruang;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class TableModelRuang extends AbstractTableModel{

    private List<Ruang> list = new ArrayList<>();
    
    public void setKelas (List<Ruang> list) {
        this.list = list;
        fireTableDataChanged();
    }
    
    public void addKelas (Ruang p) {
        list.add(p);
        fireTableDataChanged();
    }
    
    public void removeKelas (int row) {
        list.remove(row);
        fireTableDataChanged();
    }
    
    public Ruang getKelas (int row) {
        return list.get(row);
    }   
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0 :    
                return list.get(rowIndex).getKodeKelas();
            case 1 :
                return list.get(rowIndex).getDayaTampung();
            case 2 :
                return list.get(rowIndex).isInfocus();    
            case 3 :
                return list.get(rowIndex).getStatus();    
            default:
                return null;
                
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Kode Ruang";
            case 1:
                return "Daya Tampung";
            case 2:
                return "Infocus";
            case 3:
                return "Status";
            default:
                return null;
        } //To change body of genedsrated methods, choose Tools | Templates.
    }
    
    public void editKelas(int row, Ruang p) {
        list.set(row, p);
        fireTableDataChanged();
    }
}
