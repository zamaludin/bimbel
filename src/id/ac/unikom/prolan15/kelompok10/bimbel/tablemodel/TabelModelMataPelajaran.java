/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.unikom.prolan15.kelompok10.bimbel.tablemodel;

import id.ac.unikom.prolan15.kelompok10.bimbel.entity.MataPelajaran;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ihsan
 */
public class TabelModelMataPelajaran extends AbstractTableModel{

    private List<MataPelajaran> list = new ArrayList<>();
    
    public void setMataPelajaran (List<MataPelajaran> list) {
        this.list = list;
        fireTableDataChanged();
    }
    
    public void addMataPelajaran (MataPelajaran p) {
        list.add(p);
        fireTableDataChanged();
    }
    
    public void removeMataPelajaran (int row) {
        list.remove(row);
        fireTableDataChanged();
    }
    
    public MataPelajaran getMatapelajaran (int row) {
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
                return list.get(rowIndex).getKodeMaPel();
            case 1 :
                return list.get(rowIndex).getNamaMaPel();
            case 2 :
                return list.get(rowIndex).getKatagori();    
            case 3 :
                return list.get(rowIndex).getSks();    
            default:
                return null;
                
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Kode";
            case 1:
                return "Nama";
            case 2:
                return "Katagori";
            case 3:
                return "Sks";
            default:
                return null;
        } //To change body of genedsrated methods, choose Tools | Templates.
    }
    
    public void editMataPelajaran(int row, MataPelajaran p) {
        list.set(row, p);
        fireTableDataChanged();
    }
    
}
