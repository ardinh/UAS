/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uas.view;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
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
import static uas.view.pembayaran.id_otomatis;

/**
 *
 * @author Raka Putra
 */
public class sambunganbaru extends javax.swing.JInternalFrame {

    public static String id_pelanggan;
    public TableCellRenderer tengah = new tengahkan();
    private final DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    /**
     * Creates new form sambunganbaru
     */
    public sambunganbaru() {
        initComponents();
        this.setVisible(true);
        BasicInternalFrameUI bifUI = ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI());
        for (MouseListener listener : bifUI.getNorthPane().getMouseListeners()) {
            bifUI.getNorthPane().removeMouseListener(listener);
        }
        table_pelanggan.getTableHeader().setReorderingAllowed(false);
        table_pelanggan.getTableHeader().setResizingAllowed(false);

        table_pelanggan.setModel(model);
        model.addColumn("ID Pelanggan");
        model.addColumn("KTP");
        model.addColumn("Kode Tarif");
        model.addColumn("Nama");
        model.addColumn("Alamat");

        tampil_pelanggan();
        codeIP();
        tarif();
        table_pelanggan.getColumnModel().getColumn(0).setCellRenderer(tengah);
        table_pelanggan.getColumnModel().getColumn(1).setCellRenderer(tengah);
        table_pelanggan.getColumnModel().getColumn(2).setCellRenderer(tengah);
        table_pelanggan.getColumnModel().getColumn(3).setCellRenderer(tengah);
        table_pelanggan.getColumnModel().getColumn(4).setCellRenderer(tengah);
        ubahStatusTombolAksi(rowSelected);
    }

    public void tampil_pelanggan() {
        if (model.getDataVector() != null) {
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
        }
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();

            String sql = "Select * from tbl_pelanggan order by id_pelanggan";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object obj[] = new Object[5];
                obj[0] = rs.getString("id_pelanggan");
                obj[1] = rs.getString("no_ktp");
                obj[2] = rs.getString("kode_tarif");
                obj[3] = rs.getString("nama");
                obj[4] = rs.getString("alamat");

                model.addRow(obj);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            //System.out.println(ex);
        }
    }

    public PageFormat getPageFormat(PrinterJob pj) {

        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();

        double middleHeight = 12.0;
        double headerHeight = 7.0;
        double footerHeight = 7.0;
        double width = convert_CM_To_PPI(17);      //printer know only point per inch.default value is 72ppi
        double height = convert_CM_To_PPI(headerHeight + middleHeight + footerHeight);
        paper.setSize(width, height);
        paper.setImageableArea(
                0,
                15,
                width,
                height - convert_CM_To_PPI(1)
        );   //define boarder size    after that print area width is about 180 points

        pf.setOrientation(PageFormat.PORTRAIT);           //select orientation portrait or landscape but for this time portrait
        pf.setPaper(paper);

        return pf;
    }

    protected static double convert_CM_To_PPI(double cm) {
        return toPPI(cm * 0.393600787);
    }

    protected static double toPPI(double inch) {
        return inch * 72d;
    }

    public class BillPrintable implements Printable {

        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
                throws PrinterException {

            int result = NO_SUCH_PAGE;
            if (pageIndex == 0) {

                Graphics2D g2d = (Graphics2D) graphics;

                double width = pageFormat.getImageableWidth();

                g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

                ////////// code by alqama//////////////
                FontMetrics metrics = g2d.getFontMetrics(new Font("Arial", Font.BOLD, 7));
                //    int idLength=metrics.stringWidth("000000");
                //int idLength=metrics.stringWidth("00");
                int idLength = metrics.stringWidth("000");
                int amtLength = metrics.stringWidth("000000");
                int qtyLength = metrics.stringWidth("00000");
                int priceLength = metrics.stringWidth("000000");
                int prodLength = (int) width - idLength - amtLength - qtyLength - priceLength - 17;

                //    int idPosition=0;
                //    int productPosition=idPosition + idLength + 2;
                //    int pricePosition=productPosition + prodLength +10;
                //    int qtyPosition=pricePosition + priceLength + 2;
                //    int amtPosition=qtyPosition + qtyLength + 2;
                int productPosition = 0;
                int discountPosition = prodLength + 5;
                int pricePosition = discountPosition + idLength + 10;
                int qtyPosition = pricePosition + priceLength + 4;
                int amtPosition = qtyPosition + qtyLength;

                try {
                    /*Draw Header*/
                    int y = 20;
                    int yShift = 10;
                    int headerRectHeight = 15;
                    int headerRectHeighta = 40;

                    ///////////////// Product names Get ///////////
                    String pn1a = txt_pelanggan.getText();
                    String pn2a = txt_ktp.getText();
                    String pn3a = txt_nama.getText();
                    String pn4a = (String) combo_tarif.getSelectedItem();
                    String pn5a = txt_alamat.getText();
                    ///////////////// Product names Get ///////////

                    g2d.setFont(new Font("Monospaced", Font.PLAIN, 9));
                    g2d.drawString("-----------------------------------------------------", 12, y);
                    y += yShift;
                    g2d.drawString("              Tanda Bukti Pemasangan                 ", 12, y);
                    y += yShift;
                    g2d.drawString("-----------------------------------------------------", 12, y);
                    y += headerRectHeight;
                    g2d.drawString("-----------------------------------------------------", 10, y);
                    y += yShift;
                    g2d.drawString("ID Pelanggan   : " + pn1a + "            ", 10, y);
                    y += yShift;
                    g2d.drawString("KTP            : " + pn2a + "            ", 10, y);
                    y += yShift;
                    g2d.drawString("Nama           : " + pn3a + "            ", 10, y);
                    y += headerRectHeight;
                    g2d.drawString("Kode Tarif     : " + pn4a + "            ", 10, y);
                    y += yShift;
                    g2d.drawString("Alamat         : " + pn5a + "            ", 10, y);
                    y += yShift;
                    g2d.drawString("------------------------------------------------------", 10, y);
                    y += yShift;
                    g2d.drawString("Smart Electricity Menyatakan Struk ini sebagai Pemasangan yang sah  ", 10, y);
                    y += yShift;
                    g2d.drawString("                   Terima Kasih                       ", 10, y);
                    y += yShift;
                    g2d.drawString("******************************************************", 10, y);
                    y += yShift;
                    g2d.drawString("         Informasi Hubungi Call Center : 123          ", 10, y);
                    y += yShift;
                    g2d.drawString("******************************************************", 10, y);
                    y += yShift;

//            g2d.setFont(new Font("Monospaced",Font.BOLD,10));
//            g2d.drawString("Customer Shopping Invoice", 30,y);y+=yShift; 
                } catch (Exception r) {
                    r.printStackTrace();
                }

                result = PAGE_EXISTS;
            }
            return result;
        }
    }

    public void cetak() {
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(new sambunganbaru.BillPrintable(), getPageFormat(pj));
        try {
            pj.print();

        } catch (PrinterException ex) {
            ex.printStackTrace();
        }
    }

    public void codeIP() {
        int result = 0;

        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql = "SELECT * from tbl_pelanggan order by id_pelanggan desc";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                String kode = rs.getString("id_pelanggan").substring(3);
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
                txt_pelanggan.setText("SB-" + Nol + AN);
            } else {
                txt_pelanggan.setText("SB-0001");
            }
            rs.close();
            ////System.out.println(id_peminjaman);
        } catch (Exception e) {
            //System.out.println(e.getMessage());
        }

    }

    private void tarif() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql = "select * from tbl_tarif";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String kode_tarif = rs.getString("kode_tarif");
                combo_tarif.addItem(kode_tarif);
            }
        } catch (Exception e) {
            e.getMessage();
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

        btn_search = new javax.swing.JButton();
        txt_pelanggan = new javax.swing.JTextField();
        btn_simpan = new javax.swing.JButton();
        txt_nama = new javax.swing.JTextField();
        btn_edit = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btn_reset = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        combo_tarif = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_pelanggan = new javax.swing.JTable();
        txt_search = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btn_hapus = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        txt_alamat = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        txt_ktp = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(999, 461));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        btn_search.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_search.setText("Cari");
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });

        txt_pelanggan.setEditable(false);

        btn_simpan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_simpan.setText("Simpan");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

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

        btn_edit.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_edit.setText("Edit");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel3.setText("Nama");

        btn_reset.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_reset.setText("Reset");
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel4.setText("Alamat");

        combo_tarif.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        combo_tarif.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-pilih-" }));

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel6.setText("Kode Tarif");

        table_pelanggan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Pelanggan", "KTP", "Kode Tarif", "Nama", "Alamat"
            }
        ));
        table_pelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_pelangganMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_pelanggan);

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel2.setText("ID Pelanggan");

        btn_hapus.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        txt_alamat.setColumns(20);
        txt_alamat.setRows(5);
        jScrollPane5.setViewportView(txt_alamat);

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel5.setText("KTP");

        txt_ktp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ktpActionPerformed(evt);
            }
        });
        txt_ktp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_ktpKeyReleased(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel2))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txt_pelanggan, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_nama, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(combo_tarif, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 3, Short.MAX_VALUE))
                                        .addComponent(txt_ktp)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btn_edit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn_simpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btn_hapus)
                                        .addComponent(btn_reset))))
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 696, Short.MAX_VALUE)
                        .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_search)))
                .addContainerGap())
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
                            .addComponent(txt_pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_ktp))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_nama))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(combo_tarif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_reset, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        String nama = txt_nama.getText();
        String ktp = txt_ktp.getText();
        String alamat = txt_alamat.getText();
        try {
            if (nama.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Harap isi nama pelanggan!");
            } else if (ktp.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Harap isi ktp pelanggan!");
            } else if (alamat.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Harap isi alamat pelanggan!");
            } else {
                int x = JOptionPane.showConfirmDialog(rootPane, "Apakah data yang dimasukan sudah benar ?", "Mohon konfirmasi data", 0);
                if (x == JOptionPane.YES_OPTION) {
                    Connection con = koneksi.connection();
                    String sql = "insert into tbl_pelanggan(id_pelanggan,no_ktp,kode_tarif,nama,alamat,status) values(?,?,?,?,?,?)";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, txt_pelanggan.getText());
                    ps.setString(2, txt_ktp.getText());
                    ps.setString(3, (String) combo_tarif.getSelectedItem());
                    ps.setString(4, txt_nama.getText());
                    ps.setString(5, txt_alamat.getText());
                    ps.setString(6, "aktif");
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(rootPane, "Pelanggan dengan nomor pelanggan '" + txt_pelanggan.getText() + "' berhasil ditambahkan");
                    cetak();
                    ps.close();
                }
            }
        } catch (SQLException ex) {
            //System.out.println("Terjadi error "+ex);
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        String tarif = (String) combo_tarif.getSelectedItem();
        try {
            int x = JOptionPane.showConfirmDialog(rootPane, "Apakah data yang dimasukan sudah benar ?", "Mohon konfirmasi data", 0);
            if (x == JOptionPane.YES_OPTION) {
                Connection con = koneksi.connection();
                String sql = "update tbl_pelanggan set ktp='" + txt_ktp.getText() + "', kode_tarif ='" + tarif + "',nama='" + txt_nama.getText() + "',alamat='" + txt_alamat.getText() + "' where id_pelanggan='" + txt_pelanggan.getText() + "' ";
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

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        Object[] options = {"Ya", "Tidak"};
        int dialogBotton = JOptionPane.showOptionDialog(null, "Anda Yakin Ingin Menghapus?", "", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (dialogBotton == JOptionPane.YES_OPTION) {
            hapus();

        }
    }//GEN-LAST:event_btn_hapusActionPerformed
    public void refresh() {
        btn_reset.doClick();
    }
    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed

        txt_ktp.setText("");
        txt_nama.setText("");
        txt_alamat.setText("");
        combo_tarif.setSelectedItem("-pilih-");
        codeIP();
        tampil_pelanggan();
    }//GEN-LAST:event_btn_resetActionPerformed

    private void table_pelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_pelangganMouseClicked
        try {
            int row = table_pelanggan.rowAtPoint(evt.getPoint());
            txt_pelanggan.setText(String.valueOf(table_pelanggan.getValueAt(row, 0).toString()));
            txt_ktp.setText(String.valueOf(table_pelanggan.getValueAt(row, 1).toString()));
            combo_tarif.setSelectedItem(String.valueOf(table_pelanggan.getValueAt(row, 2).toString()));
            txt_nama.setText(String.valueOf(table_pelanggan.getValueAt(row, 3).toString()));
            txt_alamat.setText(String.valueOf(table_pelanggan.getValueAt(row, 4).toString()));
            rowSelected = true;
            ubahStatusTombolAksi(rowSelected);

        } catch (Exception e) {

        }
    }//GEN-LAST:event_table_pelangganMouseClicked

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String search = "like '%" + txt_search.getText() + "%'";
            String sql = "select * from tbl_pelanggan where id_pelanggan " + search + " or nama " + search + " or alamat " + search + " or kode_tarif " + search + " or ktp " + search + " order by id_pelanggan ";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object obj[] = new Object[5];
                obj[0] = rs.getString("id_pelanggan");
                obj[1] = rs.getString("ktp");
                obj[2] = rs.getString("kode_tarif");
                obj[3] = rs.getString("nama");
                obj[4] = rs.getString("alamat");
                model.addRow(obj);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            //System.out.println("ada error" + ex.getMessage());
        }
    }//GEN-LAST:event_btn_searchActionPerformed

    private void txt_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaActionPerformed

    private void txt_ktpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ktpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ktpActionPerformed

    private void txt_ktpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ktpKeyReleased
        String valid = "[\\p{Digit}&&[0123456789]]+";

        String x = txt_ktp.getText();
        if (!x.matches(valid)) {
            x = x.substring(0, 0);
            txt_ktp.setText(x);
        } else if (x.length() > 16) {
            x = x.substring(0, 16);
            txt_ktp.setText(x);
        }
    }//GEN-LAST:event_txt_ktpKeyReleased

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
    public void hapus() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();

            String sql = "delete from tbl_pelanggan where id_pelanggan='" + txt_pelanggan.getText() + "' ";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate(sql);
            ps.close();
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_search;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JComboBox<String> combo_tarif;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable table_pelanggan;
    private javax.swing.JTextArea txt_alamat;
    private javax.swing.JTextField txt_ktp;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_pelanggan;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
