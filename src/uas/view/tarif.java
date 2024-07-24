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
public class tarif extends javax.swing.JInternalFrame {

    public TableCellRenderer tengah = new tengahkan();
    private final DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    /**
     * Creates new form tarif
     */
    public tarif() {
        initComponents();
        this.setVisible(true);
        BasicInternalFrameUI bifUI = ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI());
        for (MouseListener listener : bifUI.getNorthPane().getMouseListeners()) {
            bifUI.getNorthPane().removeMouseListener(listener);
        }

        table_tarif.getTableHeader().setReorderingAllowed(false);
        table_tarif.getTableHeader().setResizingAllowed(false);

        table_tarif.setModel(model);
        model.addColumn("Kode");
        model.addColumn("Daya");
        model.addColumn("Tarif perKwH");
        model.addColumn("Beban");
        tampil_tarif();

        table_tarif.getColumnModel().getColumn(0).setCellRenderer(tengah);
        table_tarif.getColumnModel().getColumn(1).setCellRenderer(tengah);
        table_tarif.getColumnModel().getColumn(2).setCellRenderer(tengah);
        table_tarif.getColumnModel().getColumn(3).setCellRenderer(tengah);

        ubahStatusTombolAksi(rowSelected);
    }
    private boolean rowSelected = false;

    private void ubahStatusTombolAksi(boolean state) {
        btn_edit.setEnabled(state);
        btn_hapus.setEnabled(state);
    }

    public void tampil_tarif() {
        if (model.getDataVector() != null) {
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
        }
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();

            String sql = "Select * from tbl_tarif order by kode_tarif";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object obj[] = new Object[4];
                obj[0] = rs.getString("kode_tarif");
                obj[1] = rs.getString("daya");
                obj[2] = rs.getString("tarif_perKWH");
                obj[3] = rs.getString("beban");

                model.addRow(obj);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            //System.out.println(ex);
        }
    }

    public void hapus() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();

            String sql = "delete from tbl_tarif where kode_tarif='" + txt_kode.getText() + "' ";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate(sql);
            ps.close();
            tampil_tarif();
        } catch (Exception e) {
            e.printStackTrace();
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

        jLabel2 = new javax.swing.JLabel();
        txt_kode = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_tarif = new javax.swing.JTextField();
        txt_beban = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_tarif = new javax.swing.JTable();
        txt_search = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_reset = new javax.swing.JButton();
        combo_daya = new javax.swing.JComboBox<>();

        setClosable(true);
        setPreferredSize(new java.awt.Dimension(999, 461));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel2.setText("Kode");

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel3.setText("Daya");

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel4.setText("Tarif perKwH");

        txt_tarif.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_tarifKeyReleased(evt);
            }
        });

        txt_beban.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_bebanKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel6.setText("Beban");

        table_tarif.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Kode", "Daya", "Tarif perKwH", "Baban"
            }
        ));
        table_tarif.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_tarifMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                table_tarifMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(table_tarif);

        btn_search.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_search.setText("Cari");
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });

        btn_simpan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_simpan.setText("Simpan");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        btn_edit.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_edit.setText("Edit");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        btn_hapus.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_reset.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_reset.setText("Reset");
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });

        combo_daya.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-pilih-", "900", "1300", "2200", "3500", "5500", "6600" }));
        combo_daya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_dayaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_beban, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                            .addComponent(txt_kode, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                            .addComponent(txt_tarif, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                            .addComponent(combo_daya, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_edit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_simpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_hapus, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_reset, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_search))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE))
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_kode, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(combo_daya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_tarif))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_beban))
                        .addGap(139, 139, 139)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_reset, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        Object[] options = {"Ya", "Tidak"};
        int dialogBotton = JOptionPane.showOptionDialog(null, "Anda Yakin Ingin Menghapus?", "", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (dialogBotton == JOptionPane.YES_OPTION) {
            hapus();

        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        txt_beban.setText("");
        combo_daya.setSelectedItem("-pilih-");
        txt_kode.setText("");
        txt_tarif.setText("");
        tampil_tarif();

    }//GEN-LAST:event_btn_resetActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        String kode = txt_kode.getText();
        String daya = (String) combo_daya.getSelectedItem();
        String tarif = txt_tarif.getText();
        String beban = txt_beban.getText();
        try {
            if (kode.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Harap isi kode tarif!");
            } else if (daya.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Harap isi daya!");
            } else if (tarif.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Harap isi tarif!");
            } else if (beban.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Harap isi beban!");
            } else {
                int x = JOptionPane.showConfirmDialog(rootPane, "Apakah data yang dimasukan sudah benar ?", "Mohon konfirmasi data", 0);
                if (x == JOptionPane.YES_OPTION) {
                    Connection con = koneksi.connection();
                    String sql = "insert into tbl_tarif(kode_tarif,daya,tarif_perKWH,beban) values(?,?,?,?)";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, txt_kode.getText());
                    ps.setString(2, (String) combo_daya.getSelectedItem());
                    ps.setString(3, txt_tarif.getText());
                    ps.setString(4, txt_beban.getText());

                    ps.executeUpdate();
                    ps.close();
                }
            }
        } catch (SQLException ex) {
            //System.out.println("Terjadi error");
        } finally {
            refresh();

        }
    }//GEN-LAST:event_btn_simpanActionPerformed
    public void refresh() {
        btn_reset.doClick();
    }

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        try {
            int x = JOptionPane.showConfirmDialog(rootPane, "Apakah data yang dimasukan sudah benar ?", "Mohon konfirmasi data", 0);
            if (x == JOptionPane.YES_OPTION) {
                Connection con = koneksi.connection();
                String sql = "update tbl_tarif set daya='" + (String) combo_daya.getSelectedItem() + "',tarif_perKWH='" + txt_tarif.getText() + "',beban='" + txt_beban.getText() + "' where kode_tarif='" + txt_kode.getText() + "' ";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.executeUpdate();
                ps.close();
            }
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
        } finally {
            refresh();

        }
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String search = "like '%" + txt_search.getText() + "%'";
            String sql = "select * from tbl_tarif where kode_tarif " + search + " or daya " + search + " or tarif_perKWH " + search + " or beban " + search + " order by kode_tarif ";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object obj[] = new Object[4];
                obj[0] = rs.getString("kode_tarif");
                obj[1] = rs.getString("daya");
                obj[2] = rs.getString("tarif_perKWH");
                obj[3] = rs.getString("beban");
                model.addRow(obj);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            //System.out.println("ada error" + ex.getMessage());
        }
    }//GEN-LAST:event_btn_searchActionPerformed

    private void table_tarifMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_tarifMouseClicked
        // TODO add your handling code here:
        try {
            int row = table_tarif.rowAtPoint(evt.getPoint());
            txt_kode.setText(String.valueOf(table_tarif.getValueAt(row, 0).toString()));
            combo_daya.setSelectedItem(String.valueOf(table_tarif.getValueAt(row, 1).toString()));
            txt_tarif.setText(String.valueOf(table_tarif.getValueAt(row, 2).toString()));
            txt_beban.setText(String.valueOf(table_tarif.getValueAt(row, 3).toString()));
            rowSelected = true;
            ubahStatusTombolAksi(rowSelected);

        } catch (Exception e) {

        }
    }//GEN-LAST:event_table_tarifMouseClicked

    private void table_tarifMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_tarifMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_table_tarifMouseEntered

    private void txt_tarifKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tarifKeyReleased
        String valid = "[\\p{Digit}&&[0123456789]]+";

        String x = txt_tarif.getText();
        if (!x.matches(valid)) {
            x = x.substring(0, 0);
            txt_tarif.setText(x);
        } else if (x.length() > 11) {
            x = x.substring(0, 11);
            txt_tarif.setText(x);
        }
    }//GEN-LAST:event_txt_tarifKeyReleased

    private void txt_bebanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_bebanKeyReleased
        String valid = "[\\p{Digit}&&[0123456789]]+";

        String x = txt_beban.getText();
        if (!x.matches(valid)) {
            x = x.substring(0, 0);
            txt_beban.setText(x);
        } else if (x.length() > 11) {
            x = x.substring(0, 11);
            txt_beban.setText(x);
        }
    }//GEN-LAST:event_txt_bebanKeyReleased

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        rowSelected = false;
        ubahStatusTombolAksi(rowSelected);
        refresh();
    }//GEN-LAST:event_formMouseClicked

    private void combo_dayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_dayaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_dayaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_search;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JComboBox<String> combo_daya;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_tarif;
    private javax.swing.JTextField txt_beban;
    private javax.swing.JTextField txt_kode;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_tarif;
    // End of variables declaration//GEN-END:variables

}