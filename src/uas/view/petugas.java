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
public class petugas extends javax.swing.JInternalFrame {

    public TableCellRenderer tengah = new tengahkan();
    private final DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    public String passwords, answers;

    /**
     * Creates new form petugas
     */
    public petugas() {
        initComponents();
        this.setVisible(true);
        BasicInternalFrameUI bifUI = ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI());
        for (MouseListener listener : bifUI.getNorthPane().getMouseListeners()) {
            bifUI.getNorthPane().removeMouseListener(listener);
        }

        table_petugas.getTableHeader().setReorderingAllowed(false);
        table_petugas.getTableHeader().setResizingAllowed(false);

        table_petugas.setModel(model);
        model.addColumn("ID Petugas");
        model.addColumn("Nama");
        model.addColumn("Alamat");
        model.addColumn("Email");
        model.addColumn("No HP");
        model.addColumn("Username");
        model.addColumn("Status");
        model.addColumn("Hint");

        tampil_petugas();
        codePetugas();
        table_petugas.getColumnModel().getColumn(0).setCellRenderer(tengah);
        table_petugas.getColumnModel().getColumn(1).setCellRenderer(tengah);
        table_petugas.getColumnModel().getColumn(2).setCellRenderer(tengah);
        table_petugas.getColumnModel().getColumn(3).setCellRenderer(tengah);
        table_petugas.getColumnModel().getColumn(4).setCellRenderer(tengah);
        table_petugas.getColumnModel().getColumn(5).setCellRenderer(tengah);
        table_petugas.getColumnModel().getColumn(6).setCellRenderer(tengah);
        table_petugas.getColumnModel().getColumn(7).setCellRenderer(tengah);

        ubahStatusTombolAksi(rowSelected);
    }
    public static String id_otomatis;

    public void codePetugas() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql = "SELECT * from tbl_petugas order by id_petugas desc";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                String kode = rs.getString("id_petugas").substring(3);
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
                txt_petugas.setText("P-" + Nol + AN);
            } else {
                txt_petugas.setText("P-0001");
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "masih ada kesalahan di " + e.getMessage());
        }

    }

    public void tampil_petugas() {
        if (model.getDataVector() != null) {
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();

        }
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();

            String sql = "Select * from tbl_petugas order by id_petugas";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object obj[] = new Object[8];
                obj[0] = rs.getString("id_petugas");
                obj[1] = rs.getString("nama");
                obj[2] = rs.getString("alamat");
                obj[3] = rs.getString("email");
                obj[4] = rs.getString("no_hp");
                obj[5] = rs.getString("username");
                obj[6] = rs.getString("status");
                obj[7] = rs.getString("hint");

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
        btn_edit.setEnabled(state);
        btn_hapus.setEnabled(state);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_edit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_petugas = new javax.swing.JTable();
        btn_hapus = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_search = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txt_nama = new javax.swing.JTextField();
        txt_petugas = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btn_simpan = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_alamat = new javax.swing.JTextArea();
        txt_hp = new javax.swing.JTextField();
        txt_username = new javax.swing.JTextField();
        btn_reset = new javax.swing.JButton();
        txt_password = new javax.swing.JPasswordField();
        combo_status = new javax.swing.JComboBox<>();
        combo_hint = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_answer = new javax.swing.JPasswordField();

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        btn_edit.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_edit.setText("Edit");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        table_petugas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Petugas", "Nama", "Alamat", "Email", "No HP", "Username", "Status", "Hint"
            }
        ));
        table_petugas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_petugasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_petugas);

        btn_hapus.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel7.setText("Status");

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel2.setText("ID Petugas");

        btn_search.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_search.setText("Cari");
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel3.setText("Nama");

        txt_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namaActionPerformed(evt);
            }
        });
        txt_nama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_namaKeyReleased(evt);
            }
        });

        txt_petugas.setEditable(false);

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel4.setText("Alamat");

        btn_simpan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_simpan.setText("Simpan");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel5.setText("Email");

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel6.setText("No Hp");

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel8.setText("Username");

        jLabel9.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel9.setText("Password");

        txt_alamat.setColumns(20);
        txt_alamat.setRows(5);
        jScrollPane2.setViewportView(txt_alamat);

        txt_hp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_hpKeyReleased(evt);
            }
        });

        btn_reset.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_reset.setText("Reset");
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });

        combo_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilih-", "petugas pendaftaran", "petugas tagihan", "petugas pembayaran" }));

        combo_hint.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilih-", "Apa nama peliharaan kamu", "Siapa nama ayah kamu", "Tanggal paling bersejarah", "Nickname game online kamu" }));
        combo_hint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_hintActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel10.setText("Hint");

        jLabel11.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel11.setText("Jawaban");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_petugas, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(combo_status, 0, 166, Short.MAX_VALUE)
                                                .addComponent(txt_password)
                                                .addComponent(txt_username)
                                                .addComponent(txt_hp)
                                                .addComponent(txt_email))
                                            .addComponent(combo_hint, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_answer, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 719, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_search))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_simpan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_edit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_hapus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_reset)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_petugas))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_nama))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(9, 9, 9)
                                .addComponent(jLabel6)
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(txt_hp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(combo_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(combo_hint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txt_answer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_reset, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        Object[] options = {"Ya", "Tidak"};
        int dialogBotton = JOptionPane.showOptionDialog(null, "Anda Yakin Ingin Menghapus?", "", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (dialogBotton == JOptionPane.YES_OPTION) {
            hapus();

        }
    }//GEN-LAST:event_btn_hapusActionPerformed
    public void hapus() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();

            String sql = "delete from tbl_petugas where id_petugas='" + txt_petugas.getText() + "' ";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate(sql);
            ps.close();

            refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refresh() {
        btn_reset.doClick();
    }
    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed

        txt_alamat.setText("");
        txt_email.setText("");
        txt_hp.setText("");
        txt_nama.setText("");
        combo_hint.setSelectedItem("-Pilih-");
        txt_petugas.setText("");
        txt_password.setText("");
        txt_answer.setText("");
        txt_search.setText("");
        combo_status.setSelectedItem("-Pilih-");
        txt_username.setText("");
        codePetugas();
        tampil_petugas();
    }//GEN-LAST:event_btn_resetActionPerformed

    private void table_petugasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_petugasMouseClicked
        try {
            int row = table_petugas.rowAtPoint(evt.getPoint());
            String ambil = String.valueOf(table_petugas.getValueAt(row, 0).toString());
            Connection con = koneksi.connection();
            Statement st = con.createStatement();

            String sql = "Select * from tbl_petugas where id_petugas='" + ambil + "'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                passwords = rs.getString("password");
                answers = rs.getString("answer");

            }
            rs.close();
            st.close();

            txt_password.setText(passwords);
            txt_answer.setText(answers);
            txt_petugas.setText(String.valueOf(table_petugas.getValueAt(row, 0).toString()));
            txt_nama.setText(String.valueOf(table_petugas.getValueAt(row, 1).toString()));
            txt_alamat.setText(String.valueOf(table_petugas.getValueAt(row, 2).toString()));
            txt_email.setText(String.valueOf(table_petugas.getValueAt(row, 3).toString()));
            txt_hp.setText(String.valueOf(table_petugas.getValueAt(row, 4).toString()));
            txt_username.setText(String.valueOf(table_petugas.getValueAt(row, 5).toString()));

            combo_status.setSelectedItem(String.valueOf(table_petugas.getValueAt(row, 6).toString()));
            combo_hint.setSelectedItem(String.valueOf(table_petugas.getValueAt(row, 7).toString()));

            rowSelected = true;
            ubahStatusTombolAksi(rowSelected);

        } catch (Exception e) {

        }
    }//GEN-LAST:event_table_petugasMouseClicked

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        String nama = txt_nama.getText();
        String alamat = txt_alamat.getText();
        String email = txt_email.getText();
        String hp = txt_hp.getText();
        String username = txt_username.getText();
        String password = txt_password.getText();
        String hint = (String) combo_status.getSelectedItem();
        String answer = txt_answer.getText();
        try {
            if (nama.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Harap isi nama belakang petugas!");
            } else if (alamat.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Harap isi alamat petugas!");
            } else if (email.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Harap isi email petugas!");
            } else if (hp.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Harap isi No hp petugas!");
            } else if (!email.contains("@") || !email.contains(".") || email.length() < 4) {
                JOptionPane.showMessageDialog(rootPane, "Harap isi email petugas yang valid\nContoh : xxxx@gmail.com!");
            } else if (username.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Harap isi username petugas!");
            } else if (password.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Harap isi password petugas!");
            } else if (hint.isEmpty() || answer.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Harap isi hint dan jawaban !");
            } else {
                int x = JOptionPane.showConfirmDialog(rootPane, "Apakah data yang dimasukan sudah benar ?", "Mohon konfirmasi data", 0);
                if (x == JOptionPane.YES_OPTION) {
                    Connection con = koneksi.connection();
                    String sql = "insert into tbl_petugas(id_petugas,nama,alamat,email,no_hp,username,password,status,hint,answer) values(?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ////System.out.println(sql)
                    ps.setString(1, txt_petugas.getText());

                    ps.setString(2, txt_nama.getText());
                    ps.setString(3, txt_alamat.getText());
                    ps.setString(4, txt_email.getText());
                    ps.setString(5, txt_hp.getText());
                    ps.setString(6, txt_username.getText());
                    ps.setString(7, txt_password.getText());
                    ps.setString(8, (String) combo_status.getSelectedItem());
                    ps.setString(9, (String) combo_hint.getSelectedItem());
                    ps.setString(10, txt_answer.getText());
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

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        String status = (String) combo_status.getSelectedItem();
        String hint = (String) combo_hint.getSelectedItem();
        try {
            int x = JOptionPane.showConfirmDialog(rootPane, "Apakah data yang dimasukan sudah benar ?", "Mohon konfirmasi data", 0);
            if (x == JOptionPane.YES_OPTION) {
                Connection con = koneksi.connection();
                String sql = "update tbl_petugas set nama='" + txt_nama.getText() + "',alamat='" + txt_alamat.getText() + "',email='" + txt_email.getText() + "',no_hp='" + txt_hp.getText() + "',username='" + txt_username.getText() + "',password='" + txt_password.getText() + "',status='" + status + "',answer='" + txt_answer.getText() + "',hint='" + hint + "' where id_petugas='" + txt_petugas.getText() + "' ";
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
            String sql = "select * from tbl_petugas where id_petugas " + search + " or nama " + search + " or alamat " + search + " or email " + search + " or no_hp " + search + " or status " + search + " order by id_petugas ";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object obj[] = new Object[8];
                obj[0] = rs.getString("id_petugas");
                obj[1] = rs.getString("nama");
                obj[2] = rs.getString("alamat");
                obj[3] = rs.getString("email");
                obj[4] = rs.getString("no_hp");
                obj[5] = rs.getString("username");
                obj[6] = rs.getString("password");
                obj[7] = rs.getString("status");
                model.addRow(obj);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            //System.out.println("ada error" + ex.getMessage());
        }
    }//GEN-LAST:event_btn_searchActionPerformed

    private void txt_hpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_hpKeyReleased
        String valid = "[\\p{Digit}&&[0123456789]]+";

        String x = txt_hp.getText();
        if (!x.matches(valid)) {
            x = x.substring(0, 0);
            txt_hp.setText(x);
        } else if (x.length() > 12) {
            x = x.substring(0, 12);
            txt_hp.setText(x);
        }
    }//GEN-LAST:event_txt_hpKeyReleased

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        rowSelected = false;
        ubahStatusTombolAksi(rowSelected);
        refresh();
    }//GEN-LAST:event_formMouseClicked

    private void txt_namaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_namaKeyReleased
        String valid = "[a-zA-Z]*";

        String x = txt_nama.getText();
        if (!x.matches(valid)) {
            x = x.substring(0, 0);
            txt_nama.setText(x);
        }
    }//GEN-LAST:event_txt_namaKeyReleased

    private void combo_hintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_hintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_hintActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_search;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JComboBox<String> combo_hint;
    private javax.swing.JComboBox<String> combo_status;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable table_petugas;
    private javax.swing.JTextArea txt_alamat;
    private javax.swing.JPasswordField txt_answer;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_hp;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_petugas;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
