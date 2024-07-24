/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uas.view;

import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import uas.Palette.tengahkan;

/**
 *
 * @author Raka Putra
 */
public class tagihan extends javax.swing.JInternalFrame {

    public TableCellRenderer tengah = new tengahkan();
    private final DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    /**
     * Creates new form tagihan1
     */
    public static String id_tagihan;
    public int itung, cek;

    public tagihan() {
        initComponents();
        this.setVisible(true);
        BasicInternalFrameUI bifUI = ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI());
        for (MouseListener listener : bifUI.getNorthPane().getMouseListeners()) {
            bifUI.getNorthPane().removeMouseListener(listener);
        }

        table_tagihan.getTableHeader().setReorderingAllowed(false);
        table_tagihan.getTableHeader().setResizingAllowed(false);

        table_tagihan.setModel(model);
        model.addColumn("ID Tagihan");
        model.addColumn("ID Pelanggan");
        model.addColumn("Tahun");
        model.addColumn("Bulan");
        model.addColumn("Pemakaian");
        model.addColumn("Status");
        tampil_tagihan();
        codeIT();
        table_tagihan.getColumnModel().getColumn(0).setCellRenderer(tengah);
        table_tagihan.getColumnModel().getColumn(1).setCellRenderer(tengah);
        table_tagihan.getColumnModel().getColumn(2).setCellRenderer(tengah);
        table_tagihan.getColumnModel().getColumn(3).setCellRenderer(tengah);
        table_tagihan.getColumnModel().getColumn(4).setCellRenderer(tengah);
        table_tagihan.getColumnModel().getColumn(5).setCellRenderer(tengah);
        ubahStatusTombolAksi(rowSelected);
    }

    public void countTagihan() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql = "SELECT\n"
                    + "    COUNT(*)\n"
                    + "FROM\n"
                    + "    `db_pembayaran_listrik`.`tbl_tagihan` WHERE id_pelanggan='" + txt_pelanggan.getText() + "' AND tbl_tagihan.`status`=\"belum lunas\"";
//            //System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                itung = rs.getInt("COUNT(*)");
                //System.out.println(itung);
            }

            rs.close();
            ////System.out.println(id_peminjaman);
        } catch (Exception e) {
            //System.out.println("terjadi error" + e.getMessage());
        }
    }

    public void cekstatus() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql = "SELECT COUNT(*)FROM `db_pembayaran_listrik`.`tbl_pelanggan` WHERE id_pelanggan ='" + txt_pelanggan.getText() + "' AND tbl_pelanggan.`status`='nonaktif';";
//            //System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                cek = rs.getInt("COUNT(*)");
                //System.out.println(itung);
            }

            rs.close();
            ////System.out.println(id_peminjaman);
        } catch (Exception e) {
            //System.out.println("terjadi error" + e.getMessage());
        }
    }

    public void codeIT() {
        int result = 0;

        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql = "SELECT * from tbl_tagihan order by id_tagihan desc";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                String kode = rs.getString("id_tagihan").substring(3);
                String AN = "" + (Integer.parseInt(kode) + 1);
                String Nol = "";

                if (AN.length() == 1) {
                    Nol = "000";
                } else if (AN.length() == 2) {
                    Nol = "00";
                } else if (AN.length() == 3) {
                    Nol = "0";
                } else if (AN.length() == 4) {
                    Nol = "";
                }
                txt_tagihan.setText("T-" + Nol + AN);
            } else {
                txt_tagihan.setText("T-0001");
            }
            rs.close();
            ////System.out.println(id_peminjaman);
        } catch (Exception e) {
            //System.out.println(e.getMessage());
        }

    }

    public void ubahstatus() {
        try {

            Connection con = koneksi.connection();
            String sql = "update tbl_pelanggan set tbl_pelanggan.status='nonaktif' where id_pelanggan='" + txt_pelanggan.getText() + "' ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
        }
    }

    public void tampil_tagihan() {
        if (model.getDataVector() != null) {
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
        }
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();

            String sql = "Select * from tbl_tagihan order by id_tagihan";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object obj[] = new Object[6];
                obj[0] = rs.getString("id_tagihan");
                obj[1] = rs.getString("id_pelanggan");
                obj[2] = rs.getString("tahunTagih");
                obj[3] = rs.getString("bulanTagih");
                obj[4] = rs.getString("pemakaian");
                obj[5] = rs.getString("status");

                model.addRow(obj);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            //System.out.println(ex);
        }
    }

    private boolean rowSelected = false;

    private void ubahStatusTombolAksi(boolean state) {
        edit_tagihan.setEnabled(state);
        hapus_tagihan.setEnabled(state);
    }

    public void hapus() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();

            String sql = "delete from tbl_tagihan where id_tagihan='" + txt_tagihan.getText() + "' ";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate(sql);
            ps.close();
            tampil_tagihan();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void simpan() {
        String pelanggan = txt_pelanggan.getText();
        String tahun = txt_tahun.getText();
        String pemakaian = txt_pemakaian.getText();

        try {
            if (pelanggan.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Harap isi id pelanggan!");
            } else if (tahun.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Harap isi tahun tagihan!");
            } else if (pemakaian.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Harap isi pemakaian tagihan!");
            } else {
                int x = JOptionPane.showConfirmDialog(rootPane, "Apakah data yang dimasukan sudah benar ?", "Mohon konfirmasi data", 0);
                if (x == JOptionPane.YES_OPTION) {
                    Connection con = koneksi.connection();
                    String sql = "insert into tbl_tagihan(id_tagihan,id_pelanggan,tahunTagih,bulanTagih,pemakaian,status)values(?,?,?,?,?,?)";
                    ////System.out.println(sql)
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, txt_tagihan.getText());
                    ps.setString(2, txt_pelanggan.getText());
                    ps.setString(3, txt_tahun.getText());
                    ps.setString(4, (String) combo_bulan.getSelectedItem());
                    ps.setString(5, txt_pemakaian.getText());
                    ps.setString(6, "belum lunas");
                    ps.executeUpdate();
                    ps.close();

                }
            }
        } catch (SQLException ex) {
            //System.out.println(ex);
        } finally {
            tampil_tagihan();
            codeIT();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        edit_tagihan = new javax.swing.JButton();
        txt_pemakaian = new javax.swing.JTextField();
        hapus_tagihan = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_tagihan = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        combo_bulan = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_tagihan = new javax.swing.JTable();
        txt_search = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        txt_pelanggan = new javax.swing.JTextField();
        txt_tahun = new javax.swing.JTextField();
        simpan_tagihan = new javax.swing.JButton();
        reset_tagihan = new javax.swing.JButton();
        simpan_tagihan1 = new javax.swing.JButton();

        setClosable(true);
        setPreferredSize(new java.awt.Dimension(812, 450));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        edit_tagihan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        edit_tagihan.setText("Edit");
        edit_tagihan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_tagihanActionPerformed(evt);
            }
        });

        txt_pemakaian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_pemakaianActionPerformed(evt);
            }
        });
        txt_pemakaian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_pemakaianKeyReleased(evt);
            }
        });

        hapus_tagihan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        hapus_tagihan.setText("Hapus");
        hapus_tagihan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapus_tagihanActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel2.setText("ID Tagihan");

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel3.setText("ID Pelanggan");

        txt_tagihan.setEditable(false);

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel4.setText("Tahun Tagih");

        combo_bulan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Bulan-", "Januari ", "Februari ", "Maret", "April ", "Mei ", "Juni ", "Juli", "Agustus", "September", "Oktober", "November", "Desember" }));

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel5.setText("Bulan Tagih");

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel6.setText("Pemakaian");

        table_tagihan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Tagihan", "ID Pelanggan", "Tahun", "Bulan", "Pemakaian", "Status"
            }
        ));
        table_tagihan.setPreferredSize(new java.awt.Dimension(999, 461));
        table_tagihan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_tagihanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_tagihan);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Cari");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txt_tahun.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_tahunKeyReleased(evt);
            }
        });

        simpan_tagihan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        simpan_tagihan.setText("Simpan");
        simpan_tagihan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpan_tagihanActionPerformed(evt);
            }
        });

        reset_tagihan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        reset_tagihan.setText("Reset");
        reset_tagihan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_tagihanActionPerformed(evt);
            }
        });

        simpan_tagihan1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        simpan_tagihan1.setText("Laporan");
        simpan_tagihan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpan_tagihan1ActionPerformed(evt);
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
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_pemakaian, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_tagihan, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_tahun, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                    .addComponent(combo_bulan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(edit_tagihan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(simpan_tagihan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(simpan_tagihan1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(hapus_tagihan, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(reset_tagihan, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_tagihan))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_pelanggan))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_tahun))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(combo_bulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_pemakaian))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(104, 104, 104)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(simpan_tagihan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(hapus_tagihan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(edit_tagihan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(reset_tagihan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(simpan_tagihan1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void simpan_tagihanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpan_tagihanActionPerformed
        cekstatus();
        if (cek == 0) {
            countTagihan();
            if (itung == 3) {
                JOptionPane.showMessageDialog(rootPane, "Maaf Pelanggan dengan ID " + txt_pelanggan.getText() + " akan mengalami pemutusan segera bayar tunggakan yang ada!");
                simpan();
            } else if (itung < 3) {
                simpan();
            } else if (itung == 4) {
                JOptionPane.showMessageDialog(rootPane, "Maaf Pelanggan dengan ID " + txt_pelanggan.getText() + " sudah mengalami pemutusan , untuk pemasangan kembali silahkan hubungi petugas berwenang !");
                ubahstatus();
            }
        } else if (cek == 1) {
            JOptionPane.showMessageDialog(rootPane, "Maaf Pelanggan dengan ID " + txt_pelanggan.getText() + " sudah mengalami pemutusan , untuk pemasangan kembali silahkan hubungi petugas berwenang !");
        }

    }//GEN-LAST:event_simpan_tagihanActionPerformed

    private void edit_tagihanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_tagihanActionPerformed
        // TODO add your handling code here:
        String bulan = (String) combo_bulan.getSelectedItem();
        try {
            int x = JOptionPane.showConfirmDialog(rootPane, "Apakah data yang dimasukan sudah benar ?", "Mohon konfirmasi data", 0);
            if (x == JOptionPane.YES_OPTION) {
                Connection con = koneksi.connection();
                String sql = "update tbl_tagihan set id_pelanggan='" + txt_pelanggan.getText() + "',tahunTagih='" + txt_tahun.getText() + "',bulanTagih='" + bulan + "',pemakaian='" + txt_pemakaian.getText() + "' where id_tagihan='" + txt_tagihan.getText() + "' ";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.executeUpdate();
                ps.close();
            }
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
        } finally {
            tampil_tagihan();
            codeIT();
        }
    }//GEN-LAST:event_edit_tagihanActionPerformed

    private void table_tagihanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_tagihanMouseClicked
        // TODO add your handling code here:
        try {
            int row = table_tagihan.rowAtPoint(evt.getPoint());
            txt_tagihan.setText(String.valueOf(table_tagihan.getValueAt(row, 0).toString()));
            txt_pelanggan.setText(String.valueOf(table_tagihan.getValueAt(row, 1).toString()));
            txt_tahun.setText(String.valueOf(table_tagihan.getValueAt(row, 2).toString()));
            combo_bulan.setSelectedItem(String.valueOf(table_tagihan.getValueAt(row, 3).toString()));
            txt_pemakaian.setText(String.valueOf(table_tagihan.getValueAt(row, 4).toString()));

            rowSelected = true;
            ubahStatusTombolAksi(rowSelected);
        } catch (Exception e) {

        }
    }//GEN-LAST:event_table_tagihanMouseClicked

    private void hapus_tagihanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapus_tagihanActionPerformed
        Object[] options = {"Ya", "Tidak"};
        int dialogBotton = JOptionPane.showOptionDialog(null, "Anda Yakin Ingin Menghapus?", "", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (dialogBotton == JOptionPane.YES_OPTION) {
            hapus();
            codeIT();
        }
    }//GEN-LAST:event_hapus_tagihanActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String search = "like '%" + txt_search.getText() + "%'";
            String sql = "select * from tbl_tagihan where id_tagihan " + search + " or id_pelanggan " + search + " or tahunTagih " + search + " or bulanTagih " + search + " or pemakaian " + search + " or status " + search + " order by id_tagihan ";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object obj[] = new Object[6];
                obj[0] = rs.getString("id_tagihan");
                obj[1] = rs.getString("id_pelanggan");
                obj[2] = rs.getString("tahunTagih");
                obj[3] = rs.getString("bulanTagih");
                obj[4] = rs.getString("pemakaian");
                obj[5] = rs.getString("status");
                model.addRow(obj);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            //System.out.println("ada error" + ex.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void reset_tagihanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_tagihanActionPerformed
        txt_pelanggan.setText("");
        txt_pemakaian.setText("");

        txt_tahun.setText("");
        combo_bulan.setSelectedItem("-Bulan-");
        codeIT();
        tampil_tagihan();
    }//GEN-LAST:event_reset_tagihanActionPerformed

    private void txt_tahunKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tahunKeyReleased
        String valid = "[\\p{Digit}&&[0123456789]]+";

        String x = txt_tahun.getText();
        if (!x.matches(valid)) {
            x = x.substring(0, 0);
            txt_tahun.setText(x);
        } else if (x.length() > 12) {
            x = x.substring(0, 12);
            txt_tahun.setText(x);
        }
    }//GEN-LAST:event_txt_tahunKeyReleased

    private void txt_pemakaianKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_pemakaianKeyReleased
        String valid = "[0123456789.]+";

        String x = txt_pemakaian.getText();
        if (!x.matches(valid)) {
            x = x.substring(0, 0);
            txt_pemakaian.setText(x);
        } else if (x.length() > 12) {
            x = x.substring(0, 12);
            txt_pemakaian.setText(x);
        }
    }//GEN-LAST:event_txt_pemakaianKeyReleased
    public void refresh() {
        reset_tagihan.doClick();
    }
    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        rowSelected = false;
        ubahStatusTombolAksi(rowSelected);
        refresh();
    }//GEN-LAST:event_formMouseClicked

    private void txt_pemakaianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_pemakaianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_pemakaianActionPerformed

    private void simpan_tagihan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpan_tagihan1ActionPerformed
        report cb = new report();
        cb.setVisible(true);
    }//GEN-LAST:event_simpan_tagihan1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> combo_bulan;
    private javax.swing.JButton edit_tagihan;
    private javax.swing.JButton hapus_tagihan;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton reset_tagihan;
    private javax.swing.JButton simpan_tagihan;
    private javax.swing.JButton simpan_tagihan1;
    private javax.swing.JTable table_tagihan;
    private javax.swing.JTextField txt_pelanggan;
    private javax.swing.JTextField txt_pemakaian;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_tagihan;
    private javax.swing.JTextField txt_tahun;
    // End of variables declaration//GEN-END:variables
}
