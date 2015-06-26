/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.unikom.prolan15.kelompok10.bimbel;

import id.ac.unikom.prolan15.kelompok10.bimbel.utility.KonekDb;
import id.ac.unikom.prolan15.kelompok10.bimbel.entity.Ruang;
import id.ac.unikom.prolan15.kelompok10.bimbel.tablemodel.TableModelRuang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author user
 */
public class InputRuang extends javax.swing.JDialog {

    /**
     * Creates new form NewJDialog
     */
    private Connection koneksi;
    private TableModelRuang tableModelKelas;
    
    private int rowHapus;
    private String kodeHapus;
    
    private String modeFormKelas;
    
    public InputRuang(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initConnection();
        initTableModel();
        refreshTable();
        disableFormKelas();   
    }

    public String getModeFormKelas() {
        return modeFormKelas;
    }

    public void setModeFormKelas(String modeFormKelas) {
        this.modeFormKelas = modeFormKelas;
    }
    
    private void initConnection() {
        koneksi = KonekDb.getKoneksi();
    }
    
    private void disableFormKelas() {
        LKodeKelas.setEnabled(false);
        LDayaTampung.setEnabled(false);
        LProyektor.setEnabled(false);
        TFKodeKelas.setEnabled(false);
        CBDayaTampung.setEnabled(false);
        RBYa.setEnabled(false);
        RBTidak.setEnabled(false);
        BSimpan.setEnabled(false);
        BBatal.setEnabled(false);
        BX.setEnabled(false);
        BCari.setEnabled(false);
    }
    
    private void refreshTable() {
        tableKelas.clearSelection();
        BUbah.setEnabled(false);
        BHapus.setEnabled(false);
        TFKodeKelas.setText("Kode Ruang");
        try {                                         
            Statement state = koneksi.createStatement();
            
            String SELECT = "SELECT * FROM ruang";
            
            List<Ruang> list;
            try (ResultSet result = state.executeQuery(SELECT)) {
                list = new ArrayList<>();
                boolean adaData = false;
                while (result.next()) {
                    Ruang k = new Ruang();
                    k.setKodeKelas(result.getString("no"));
                    k.setDayaTampung(result.getInt("kapasitas"));
                    String infocus;
                    if (result.getInt("infocus") == 1) {
                        infocus = "YA";
                    } else {
                        infocus = "TIDAK";
                    }
                    String status;
                    // 1= tersedia
                    if (result.getInt("status") == 1) {
                        status = "TERSEDIA";
                    } else {
                        status = "KELAS PENUH";
                    }
                    k.setStatus(status);
                    k.setInfocus(infocus);
                    
                    list.add(k);
                    adaData = true;
                }
                if (!adaData) {
                    JOptionPane.showMessageDialog(this, "Data masih kosong", "TOK TOK", 1); 
                    TFKataKunci.setEnabled(false);
                    BCari.setEnabled(false);
                } else {
                    TFKataKunci.setEnabled(true);
                    BCari.setEnabled(true);
                }
                
            }
            tableModelKelas.setKelas(list);
        }   catch (SQLException ex) {
            Logger.getLogger(InputRuang.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Astagfirullah!");
        }
    }
    
    private void initTableModel() {
        tableModelKelas = new TableModelRuang();
        tableKelas.setModel(tableModelKelas);
        tableKelas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = tableKelas.getSelectedRow();
                
                if (row != -1) {
                    
                    Ruang p = tableModelKelas.getKelas(row);
                    kodeHapus = p.getKodeKelas();
                    rowHapus = row;
                    TFKodeKelas.setText(p.getKodeKelas());
                    CBDayaTampung.setSelectedItem(Integer.toString(p.getDayaTampung()));
                    //fieldDayaTampung.setText(Integer.toString(p.getDayaTampung()));
                    if (p.getInfocus().equals("YA") ) {
                        RBYa.setSelected(true);
                    } else {
                        RBTidak.setSelected(true);
                    }
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GBJk = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableKelas = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        LProyektor = new javax.swing.JLabel();
        RBYa = new javax.swing.JRadioButton();
        RBTidak = new javax.swing.JRadioButton();
        LKodeKelas = new javax.swing.JLabel();
        TFKodeKelas = new javax.swing.JTextField();
        LDayaTampung = new javax.swing.JLabel();
        BSimpan = new javax.swing.JButton();
        BBatal = new javax.swing.JButton();
        CBDayaTampung = new javax.swing.JComboBox();
        TFKataKunci = new javax.swing.JTextField();
        BCari = new javax.swing.JButton();
        BSegar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        BHapus = new javax.swing.JButton();
        BUbah = new javax.swing.JButton();
        BTambah = new javax.swing.JButton();
        BKembali = new javax.swing.JButton();
        BX = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Data Ruang");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        tableKelas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Kode Ruang", "Daya Tampung", "Infocus"
            }
        ));
        tableKelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableKelasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableKelas);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Kelas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        LProyektor.setText("Proyektor");

        GBJk.add(RBYa);
        RBYa.setText("YA");
        RBYa.setToolTipText("Ada Failitas Proyektor");

        GBJk.add(RBTidak);
        RBTidak.setSelected(true);
        RBTidak.setText("TIDAK");
        RBTidak.setToolTipText("Tidak ada Fasilitas Proyektor");

        LKodeKelas.setText("Kode Ruang");

        TFKodeKelas.setText("Kode Ruang");
        TFKodeKelas.setToolTipText("Kode Kelas");
        TFKodeKelas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TFKodeKelasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TFKodeKelasFocusLost(evt);
            }
        });
        TFKodeKelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TFKodeKelasMouseClicked(evt);
            }
        });
        TFKodeKelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFKodeKelasActionPerformed(evt);
            }
        });

        LDayaTampung.setText("Daya Tampung");

        BSimpan.setText("Simpan");
        BSimpan.setToolTipText("Simpan Data");
        BSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSimpanActionPerformed(evt);
            }
        });

        BBatal.setText("Batal");
        BBatal.setToolTipText("Batal menyimpan");
        BBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BBatalActionPerformed(evt);
            }
        });

        CBDayaTampung.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "5", "10", "20", "30" }));
        CBDayaTampung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBDayaTampungActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LDayaTampung)
                            .addComponent(LProyektor))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(RBYa, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(RBTidak))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(CBDayaTampung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(LKodeKelas)
                        .addGap(18, 18, 18)
                        .addComponent(TFKodeKelas)))
                .addGap(26, 26, 26)
                .addComponent(BSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LKodeKelas)
                            .addComponent(TFKodeKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LDayaTampung)
                            .addComponent(CBDayaTampung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(RBYa)
                            .addComponent(LProyektor)
                            .addComponent(RBTidak)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BSimpan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BBatal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TFKataKunci.setText("Masukan Kata Kunci");
        TFKataKunci.setToolTipText("Kata Kunci Pencarian");
        TFKataKunci.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TFKataKunciFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TFKataKunciFocusLost(evt);
            }
        });
        TFKataKunci.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TFKataKunciMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TFKataKunciMouseReleased(evt);
            }
        });
        TFKataKunci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFKataKunciActionPerformed(evt);
            }
        });
        TFKataKunci.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TFKataKunciKeyPressed(evt);
            }
        });

        BCari.setText("Cari");
        BCari.setToolTipText("Mulai Cari");
        BCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCariActionPerformed(evt);
            }
        });

        BSegar.setText("Segarkan");
        BSegar.setToolTipText("Segarkan Tabel Kelas");
        BSegar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSegarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        BHapus.setText("Hapus");
        BHapus.setToolTipText("Hapus Data Kelas yang dipilih");
        BHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BHapusActionPerformed(evt);
            }
        });

        BUbah.setText("Ubah");
        BUbah.setToolTipText("Ubah Data Kelas yang dipilih");
        BUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUbahActionPerformed(evt);
            }
        });

        BTambah.setText("Tambah");
        BTambah.setToolTipText("Tambah Data Baru");
        BTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTambahActionPerformed(evt);
            }
        });

        BKembali.setText("Kembali");
        BKembali.setToolTipText("Kembali Ke Menu Utama");
        BKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BKembaliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BKembali, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BHapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BUbah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BTambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        BX.setText("X");
        BX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BXActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TFKataKunci, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BX, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BCari)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BSegar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TFKataKunci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BCari)
                            .addComponent(BSegar)
                            .addComponent(BX)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleDescription("Simpan Ubah Hapus Cari Kelas");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTambahActionPerformed
        // TODO add your handling code here:
        tableKelas.clearSelection();
        BUbah.setEnabled(false);
        BHapus.setEnabled(false);
        TFKodeKelas.setText("Kode Ruang");
        LKodeKelas.setEnabled(true);
        LDayaTampung.setEnabled(true);
        LProyektor.setEnabled(true);
        TFKodeKelas.setEnabled(true);
        CBDayaTampung.setEnabled(true);
        RBYa.setEnabled(true);
        RBTidak.setEnabled(true);
        BBatal.setEnabled(true);
        BSimpan.setEnabled(true);
        setModeFormKelas("Tambah");       
    }//GEN-LAST:event_BTambahActionPerformed

    private void TFKodeKelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFKodeKelasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFKodeKelasActionPerformed

    private void BSegarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSegarActionPerformed
        refreshTable();
    }//GEN-LAST:event_BSegarActionPerformed

    private void BHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BHapusActionPerformed
        Ruang k = tableModelKelas.getKelas(rowHapus);
        kodeHapus = k.getKodeKelas();
        String msg = "Apakah anda yakin akan menghapus data ini?\n" + 
                "Kode Ruang : " + k.getKodeKelas() + "\n" +
                "Daya Tampung : " + k.getDayaTampung() + "\n" + 
                "Infocus : " + k.getInfocus();
        
        Object [] options = {"YA", "TIDAK"};
        
        int respon = JOptionPane.showOptionDialog(this, msg, "HAPUS", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (respon == JOptionPane.YES_OPTION) {
            try {                                       
            // TODO add your handling code here:
                Statement state = koneksi.createStatement();
                String SQL_DELETE = "DELETE FROM kelas WHERE id_kelas = ?";

                PreparedStatement statement = null;
                boolean success = false;
                try {
                    statement = koneksi.prepareStatement(SQL_DELETE);
                    statement.setString(1, kodeHapus);
                    int action = statement.executeUpdate();
                    success = action > 0;
                } catch (SQLException ex) {
                    System.out.println(ex);
                } catch (Exception e) {
                    System.out.println(e);
                }
                refreshTable();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        
        
    }//GEN-LAST:event_BHapusActionPerformed

    private void BUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUbahActionPerformed
        // TODO add your handling code here:
        LKodeKelas.setEnabled(false);
        LDayaTampung.setEnabled(true);
        LProyektor.setEnabled(true);
        TFKodeKelas.setEnabled(false);
        CBDayaTampung.setEnabled(true);
        RBYa.setEnabled(true);
        RBTidak.setEnabled(true);
        BBatal.setEnabled(true);
        BSimpan.setEnabled(true);
        setModeFormKelas("Ubah");
    }//GEN-LAST:event_BUbahActionPerformed

    private void BKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BKembaliActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_BKembaliActionPerformed

    private void BCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCariActionPerformed
        // TODO add your handling code here:
        tableKelas.clearSelection();
        BUbah.setEnabled(false);
        BHapus.setEnabled(false);
        TFKodeKelas.setText("Kode Ruang");
        
        String kataKunci = TFKataKunci.getText();
        if (kataKunci == null) {
            JOptionPane.showMessageDialog(this, "Silahkan isi kata kunci terlebih dahulu");
            return;
        }
        String SELECT = "SELECT * FROM kelas WHERE id_kelas LIKE ?";

        PreparedStatement statement = null;
        ResultSet result;
        try {    
            statement = koneksi.prepareStatement(SELECT);
            statement.setString(1, "%" + kataKunci + "%");
            
            result = statement.executeQuery();
            List<Ruang> list = new ArrayList<>();
            while (result.next()) {
                Ruang k = new Ruang();
                k.setKodeKelas(result.getString("id_kelas"));
                k.setDayaTampung(result.getInt("daya_tampung"));
                String infocus;
                if (result.getInt("infocus") == 1) {
                    infocus = "YA";
                } else {
                    infocus = "TIDAK";
                }
                k.setInfocus(infocus);
                list.add(k);
            }
            result.close();
            tableModelKelas.setKelas(list);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_BCariActionPerformed

    private void TFKataKunciMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TFKataKunciMouseClicked
        // TODO add your handling code here:
        if (TFKataKunci.getText().equals("Kata Kunci")){
            TFKataKunci.setText("");
        }
        tableKelas.clearSelection();
        BUbah.setEnabled(false);
        BHapus.setEnabled(false);
        TFKodeKelas.setText("Kode Ruang");
        
    }//GEN-LAST:event_TFKataKunciMouseClicked

    private void CBDayaTampungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBDayaTampungActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBDayaTampungActionPerformed

    private void TFKodeKelasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TFKodeKelasFocusGained
        // TODO add your handling code here:
        if (TFKodeKelas.getText().equals("Kode Ruang")){
            TFKodeKelas.setText("");
        }
    }//GEN-LAST:event_TFKodeKelasFocusGained

    private void TFKodeKelasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TFKodeKelasFocusLost
        // TODO add your handling code here:
        if (TFKodeKelas.getText().equals("")){
            TFKodeKelas.setText("Kode Ruang");
        }
    }//GEN-LAST:event_TFKodeKelasFocusLost

    private void TFKataKunciFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TFKataKunciFocusGained
        // TODO add your handling code here:
        if (TFKataKunci.getText().equals("Masukan Kata Kunci")){
            TFKataKunci.setText("");
            BCari.setEnabled(true);
        }
        tableKelas.clearSelection();
        BUbah.setEnabled(false);
        BHapus.setEnabled(false);
        TFKodeKelas.setText("Kode Ruang");
    }//GEN-LAST:event_TFKataKunciFocusGained

    private void TFKataKunciFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TFKataKunciFocusLost
        // TODO add your handling code here:
        if (TFKataKunci.getText().equals("")){
            TFKataKunci.setText("Masukan Kata Kunci");
            BCari.setEnabled(false);
        }
    }//GEN-LAST:event_TFKataKunciFocusLost

    private void BSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSimpanActionPerformed
        // TODO add your handling code here:
        //langkah 1
        //ambil data dari form
        String kodeKelas = TFKodeKelas.getText();
        int dayaTampung = Integer.parseInt((String) CBDayaTampung.getSelectedItem()); //int dayaTampung = Integer.parseInt(fieldDayaTampung.getText());
        int infocus = 0;
        if (RBYa.isSelected()){
            infocus = 1;
        }
        switch (getModeFormKelas()) {
            case "Tambah":
                //Langkah 2
                //Query insert untuk mengisikan data dari form ke table
                String INSERT = "INSERT INTO ruang VALUES(?,?,?,?)";
                boolean sukses = false;
                PreparedStatement stat = null;
                try {
                    //Langkah 3 
                    //replace setiap tanda tanya dengan data
                    stat = koneksi.prepareStatement(INSERT);
                    stat.setString(1, kodeKelas);
                    stat.setInt(2, dayaTampung);
                    stat.setInt(3, infocus);
                    stat.setInt(4, 1);
                    int jumlahDataSukses = stat.executeUpdate();
                    sukses = jumlahDataSukses > 0;
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                } finally {
                    try {
                         if (stat != null) {
                            stat.close();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(InputRuang.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    refreshTable();
                }
                //langkah 4
                if (sukses) {
                    //clear field
                    TFKodeKelas.setText("Kode Ruang");
                    CBDayaTampung.setSelectedIndex(0);//fieldDayaTampung.setText("");
                    GBJk.clearSelection();
                    tableKelas.clearSelection();
                    BUbah.setEnabled(false);
                    BHapus.setEnabled(false);
                    TFKodeKelas.setText("Kode Ruang");
                    JOptionPane.showMessageDialog(this, "Tambah data kelas sukses");
                    disableFormKelas();
                } else {
                    //JOptionPane.showMessageDialog(this, );
                }
                break;
            case "Ubah":
                //buat object kelas
                Ruang k = new Ruang();
                k.setKodeKelas(kodeKelas);
                k.setDayaTampung(dayaTampung);
                String proyektor = null;
                if (infocus == 1) {
                    proyektor = "YA";
                } else {
                    proyektor = "TIDAK";
                }
                k.setInfocus(proyektor);
                //Langkah 2 
                //Siapkan SQL untuk Update
                String UPDATE = "UPDATE kelas "
                        + "SET daya_tampung = ?, infocus = ? "
                        + "WHERE id_kelas = ?";
                //Langkah 3 
                //Gunaan prepared statement untuk menganti "?"
                PreparedStatement statement = null;
                boolean success = false;
                try {
                    statement = koneksi.prepareStatement(UPDATE);
                    statement.setInt(1, dayaTampung);
                    statement.setInt(2, infocus);
                    statement.setString(3, kodeKelas);
                    int action = statement.executeUpdate();
                    success = action > 0;
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                } finally {
                    try {
                        if (statement != null) {
                            statement.close();
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                //langkah 4 
                //muculkan notifikasi sukses
                if (success) {
                    // Bersihkan form
                    //fieldDayaTampung.setText("");
                    CBDayaTampung.setSelectedIndex(0);
                    TFKodeKelas.setText("");
                    GBJk.clearSelection();
                    // Hapus pegawai pada table
                    tableModelKelas.editKelas(rowHapus, k);
                    // Hapus selection di table
                    tableKelas.clearSelection();
                    BUbah.setEnabled(false);
                    BHapus.setEnabled(false);
                    TFKodeKelas.setText("Kode Ruang");
                    // Munculkan pesan
                    JOptionPane.showMessageDialog(this, "Ubah data pegawai sukses");
                    disableFormKelas();
                }
                break;
        }
    }//GEN-LAST:event_BSimpanActionPerformed

    private void TFKodeKelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TFKodeKelasMouseClicked
        // TODO add your handling code here:
        if (TFKodeKelas.getText().equals("Kode Ruang")){
            TFKodeKelas.setText("");
            BX.setEnabled(true);
        }
    }//GEN-LAST:event_TFKodeKelasMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        tableKelas.clearSelection();
        BUbah.setEnabled(false);
        BHapus.setEnabled(false);
        //TFKodeKelas.setText("Kode Ruang");
        
    }//GEN-LAST:event_formMouseClicked

    private void TFKataKunciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFKataKunciActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFKataKunciActionPerformed

    private void tableKelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableKelasMouseClicked
        // TODO add your handling code here:
        BUbah.setEnabled(true);
        BHapus.setEnabled(true);
    }//GEN-LAST:event_tableKelasMouseClicked

    private void BBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBatalActionPerformed
        // TODO add your handling code here:
        disableFormKelas();      
    }//GEN-LAST:event_BBatalActionPerformed

    private void TFKataKunciMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TFKataKunciMouseReleased
        // TODO add your handling code here:
        //TFKataKunci.setText("Kata Kunci");
        //refreshTable();
    }//GEN-LAST:event_TFKataKunciMouseReleased

    private void BXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BXActionPerformed
        TFKataKunci.setText("Masukan Kata Kunci");
        refreshTable();
        BX.setEnabled(false);
        BCari.setEnabled(false);
    }//GEN-LAST:event_BXActionPerformed

    private void TFKataKunciKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFKataKunciKeyPressed
        // TODO add your handling code here:
        BX.setEnabled(true);
        
    }//GEN-LAST:event_TFKataKunciKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InputRuang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InputRuang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InputRuang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InputRuang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InputRuang dialog = new InputRuang(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BBatal;
    private javax.swing.JButton BCari;
    private javax.swing.JButton BHapus;
    private javax.swing.JButton BKembali;
    private javax.swing.JButton BSegar;
    private javax.swing.JButton BSimpan;
    private javax.swing.JButton BTambah;
    private javax.swing.JButton BUbah;
    private javax.swing.JButton BX;
    private javax.swing.JComboBox CBDayaTampung;
    private javax.swing.ButtonGroup GBJk;
    private javax.swing.JLabel LDayaTampung;
    private javax.swing.JLabel LKodeKelas;
    private javax.swing.JLabel LProyektor;
    private javax.swing.JRadioButton RBTidak;
    private javax.swing.JRadioButton RBYa;
    private javax.swing.JTextField TFKataKunci;
    private javax.swing.JTextField TFKodeKelas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableKelas;
    // End of variables declaration//GEN-END:variables
   
}
