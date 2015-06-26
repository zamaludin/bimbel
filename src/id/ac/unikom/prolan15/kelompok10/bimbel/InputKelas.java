/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.unikom.prolan15.kelompok10.bimbel;

import id.ac.unikom.prolan15.kelompok10.bimbel.utility.KonekDb;
import id.ac.unikom.prolan15.kelompok10.bimbel.entity.Kelas;
import id.ac.unikom.prolan15.kelompok10.bimbel.tablemodel.TableModelKelas;
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
public class InputKelas extends javax.swing.JDialog {

    /**
     * Creates new form Kelas
     */
    private Connection koneksi;
    private TableModelKelas tabelModelKelas;
    
    private int rowHapus;
    private String kodeHapus;
    
    private String modeFormKelas;
    public InputKelas(java.awt.Frame parent, boolean modal) {
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
        LMataPelajaran.setEnabled(false);
        TFKodeKelas.setEnabled(false);
        CBPaket.setEnabled(false);
        BSimpan.setEnabled(false);
        BBatal.setEnabled(false);
        BX.setEnabled(false);
        BCari.setEnabled(false);
    }
    
    private void refreshTable() {
        TKelas.clearSelection();
        BUbah.setEnabled(false);
        BHapus.setEnabled(false);
        TFKodeKelas.setText("Kode Ruang");
        try {                                         
            Statement state = koneksi.createStatement();
            
            String SELECT = "SELECT * FROM ruang";
            
            List<Kelas> list;
            try (ResultSet result = state.executeQuery(SELECT)) {
                list = new ArrayList<>();
                boolean adaData = false;
                while (result.next()) {
                    Kelas k = new Kelas();
                    //k.setKode(result.getString("no"));
                    //k.setKodeMataPelajaran(result.getString("no"));
                    //k.getNoruang(result.getInt("kapasitas"));
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
                   // k.setStatus(status);
                    //k.setInfocus(infocus);
                    
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
            tabelModelKelas.setKelas(list);
        }   catch (SQLException ex) {
            Logger.getLogger(InputRuang.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Astagfirullah!");
        }
    }
    
    private void initTableModel() {
        tabelModelKelas = new TableModelKelas();
        TKelas.setModel(tabelModelKelas);
        TKelas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = TKelas.getSelectedRow();
                
                if (row != -1) {
                    
                    Kelas p = tabelModelKelas.getKelas(row);
                    kodeHapus = p.getKode();
                    rowHapus = row;
                    TFKodeKelas.setText(p.getKode());
                    //CBDayaTampung.setSelectedItem(Integer.toString(p.getDayaTampung()));
                    //fieldDayaTampung.setText(Integer.toString(p.getDayaTampung()));
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

        jPanel1 = new javax.swing.JPanel();
        LMataPelajaran = new javax.swing.JLabel();
        BSimpan = new javax.swing.JButton();
        BBatal = new javax.swing.JButton();
        CBPaket = new javax.swing.JComboBox();
        TFKodeMaPel = new javax.swing.JTextField();
        BPilihMapel = new javax.swing.JButton();
        LRuangan = new javax.swing.JLabel();
        TFNoRuang = new javax.swing.JTextField();
        BPilihRuang = new javax.swing.JButton();
        LPaket = new javax.swing.JLabel();
        LKodeKelas = new javax.swing.JLabel();
        TFKodeKelas = new javax.swing.JTextField();
        PModeForm = new javax.swing.JPanel();
        BHapus = new javax.swing.JButton();
        BUbah = new javax.swing.JButton();
        BTambah = new javax.swing.JButton();
        BKembali = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TKelas = new javax.swing.JTable();
        TFKataKunci = new javax.swing.JTextField();
        BX = new javax.swing.JButton();
        BCari = new javax.swing.JButton();
        BSegar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Kelas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        LMataPelajaran.setText("Mata Pelajaran");

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

        CBPaket.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Paket 1", "Paket 2", "Paket 3" }));
        CBPaket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBPaketActionPerformed(evt);
            }
        });

        TFKodeMaPel.setText("Matapelajaran");

        BPilihMapel.setText("Pilih");

        LRuangan.setText("Ruangan");

        TFNoRuang.setText("No Ruangan");

        BPilihRuang.setText("Pilih");

        LPaket.setText("Paket");

        LKodeKelas.setText("Kode");

        TFKodeKelas.setText("Kode Kelas");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LMataPelajaran)
                    .addComponent(LRuangan)
                    .addComponent(LPaket)
                    .addComponent(LKodeKelas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TFKodeKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TFKodeMaPel, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TFNoRuang, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBPaket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BPilihMapel)
                    .addComponent(BPilihRuang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(BSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LKodeKelas)
                            .addComponent(TFKodeKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LMataPelajaran)
                            .addComponent(TFKodeMaPel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BPilihMapel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LRuangan)
                            .addComponent(TFNoRuang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BPilihRuang))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LPaket)
                            .addComponent(CBPaket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        PModeForm.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

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

        javax.swing.GroupLayout PModeFormLayout = new javax.swing.GroupLayout(PModeForm);
        PModeForm.setLayout(PModeFormLayout);
        PModeFormLayout.setHorizontalGroup(
            PModeFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PModeFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PModeFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BKembali, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BHapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BUbah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BTambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PModeFormLayout.setVerticalGroup(
            PModeFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PModeFormLayout.createSequentialGroup()
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

        TKelas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Kode", "Mata Pelajaran", "Ruangan", "Paket"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TKelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TKelasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TKelas);

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

        BX.setText("X");
        BX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BXActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TFKataKunci)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BX, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BCari)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BSegar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PModeForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PModeForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFKataKunci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BCari)
                    .addComponent(BSegar)
                    .addComponent(BX))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSimpanActionPerformed
        // TODO add your handling code here:
        //langkah 1
        //ambil data dari form
        String kodeMaPel = TFKodeMaPel.getText();
        int noRuang = Integer.parseInt(TFNoRuang.getText());        
        Object paket = CBPaket.getSelectedItem(); //int dayaTampung = Integer.parseInt(fieldDayaTampung.getText());

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
                //stat.setString(1, kodeKelas);
                //stat.setInt(2, dayaTampung);
                //stat.setInt(3, infocus);
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
                TFKodeKelas.setText("Kode Kelas");
                CBPaket.setSelectedIndex(0);//fieldDayaTampung.setText("");
                //GBJk.clearSelection();
                TKelas.clearSelection();
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
            Kelas k = new Kelas();
            //k.setKodeKelas(kodeKelas);
            //k.setDayaTampung(dayaTampung);
            //k.setInfocus(proyektor);
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
                //statement.setInt(1, dayaTampung);
                //statement.setInt(2, infocus);
                //statement.setString(3, kodeKelas);
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
                CBPaket.setSelectedIndex(0);
                TFKodeKelas.setText("");
                //GBJk.clearSelection();
                // Hapus pegawai pada table
                //tableModelKelas.editKelas(rowHapus, k);
                // Hapus selection di table
                TKelas.clearSelection();
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

    private void BBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBatalActionPerformed
        // TODO add your handling code here:
        disableFormKelas();
    }//GEN-LAST:event_BBatalActionPerformed

    private void CBPaketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBPaketActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBPaketActionPerformed

    private void BHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BHapusActionPerformed
        //Kelas k = tableModelKelas.getKelas(rowHapus);
        //kodeHapus = k.getKodeKelas();
        String msg = "Apakah anda yakin akan menghapus data ini?\n";// +
        //"Kode Ruang : " + k.getKodeKelas() + "\n" +
        //"Daya Tampung : " + k.getDayaTampung() + "\n" +
        //"Infocus : " + k.getInfocus();

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
        LMataPelajaran.setEnabled(true);
        //LProyektor.setEnabled(true);
        TFKodeKelas.setEnabled(false);
        CBPaket.setEnabled(true);
        //RBYa.setEnabled(true);
        //RBTidak.setEnabled(true);
        BBatal.setEnabled(true);
        BSimpan.setEnabled(true);
        setModeFormKelas("Ubah");
    }//GEN-LAST:event_BUbahActionPerformed

    private void BTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTambahActionPerformed
        // TODO add your handling code here:
        TKelas.clearSelection();
        BUbah.setEnabled(false);
        BHapus.setEnabled(false);
        TFKodeKelas.setText("Kode Ruang");
        LKodeKelas.setEnabled(true);
        LMataPelajaran.setEnabled(true);
        //LProyektor.setEnabled(true);
        TFKodeKelas.setEnabled(true);
        CBPaket.setEnabled(true);
        //RBYa.setEnabled(true);
        //RBTidak.setEnabled(true);
        BBatal.setEnabled(true);
        BSimpan.setEnabled(true);
        setModeFormKelas("Tambah");
    }//GEN-LAST:event_BTambahActionPerformed

    private void BKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BKembaliActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_BKembaliActionPerformed

    private void TKelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TKelasMouseClicked
        // TODO add your handling code here:
        BUbah.setEnabled(true);
        BHapus.setEnabled(true);
    }//GEN-LAST:event_TKelasMouseClicked

    private void TFKataKunciFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TFKataKunciFocusGained
        // TODO add your handling code here:
        if (TFKataKunci.getText().equals("Masukan Kata Kunci")){
            TFKataKunci.setText("");
            BCari.setEnabled(true);
        }
        TKelas.clearSelection();
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

    private void TFKataKunciMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TFKataKunciMouseClicked
        // TODO add your handling code here:
        if (TFKataKunci.getText().equals("Kata Kunci")){
            TFKataKunci.setText("");
        }
        TKelas.clearSelection();
        BUbah.setEnabled(false);
        BHapus.setEnabled(false);
        TFKodeKelas.setText("Kode Ruang");

    }//GEN-LAST:event_TFKataKunciMouseClicked

    private void TFKataKunciMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TFKataKunciMouseReleased
        // TODO add your handling code here:
        //TFKataKunci.setText("Kata Kunci");
        //refreshTable();
    }//GEN-LAST:event_TFKataKunciMouseReleased

    private void TFKataKunciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFKataKunciActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFKataKunciActionPerformed

    private void TFKataKunciKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFKataKunciKeyPressed
        // TODO add your handling code here:
        BX.setEnabled(true);

    }//GEN-LAST:event_TFKataKunciKeyPressed

    private void BXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BXActionPerformed
        TFKataKunci.setText("Masukan Kata Kunci");
        refreshTable();
        BX.setEnabled(false);
        BCari.setEnabled(false);
    }//GEN-LAST:event_BXActionPerformed

    private void BCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCariActionPerformed
        // TODO add your handling code here:
        TKelas.clearSelection();
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
            List<Kelas> list = new ArrayList<>();
            while (result.next()) {
                Kelas k = new Kelas();
                //k.setKodeKelas(result.getString("id_kelas"));
                //k.setDayaTampung(result.getInt("daya_tampung"));
                String infocus;
                if (result.getInt("infocus") == 1) {
                    infocus = "YA";
                } else {
                    infocus = "TIDAK";
                }
                //k.setInfocus(infocus);
                list.add(k);
            }
            result.close();
            //tableModelKelas.setKelas(list);
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

    private void BSegarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSegarActionPerformed
        refreshTable();
    }//GEN-LAST:event_BSegarActionPerformed

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
            java.util.logging.Logger.getLogger(InputKelas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InputKelas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InputKelas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InputKelas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InputKelas dialog = new InputKelas(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton BPilihMapel;
    private javax.swing.JButton BPilihRuang;
    private javax.swing.JButton BSegar;
    private javax.swing.JButton BSimpan;
    private javax.swing.JButton BTambah;
    private javax.swing.JButton BUbah;
    private javax.swing.JButton BX;
    private javax.swing.JComboBox CBPaket;
    private javax.swing.JLabel LKodeKelas;
    private javax.swing.JLabel LMataPelajaran;
    private javax.swing.JLabel LPaket;
    private javax.swing.JLabel LRuangan;
    private javax.swing.JPanel PModeForm;
    private javax.swing.JTextField TFKataKunci;
    private javax.swing.JTextField TFKodeKelas;
    private javax.swing.JTextField TFKodeMaPel;
    private javax.swing.JTextField TFNoRuang;
    private javax.swing.JTable TKelas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
