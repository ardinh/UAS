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
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author Raka Putra
 */
public class pembayaran extends javax.swing.JInternalFrame {

    int bulan, itung;
    String tagihan, pelanggan, nama, tahun, bulan1, pemakaian, tarif, beban, denda1, jumlah1, total1, admin1, beban2, status;
    float pemakaian1, tarif1, beban1, denda, bulanhari;

    /**
     * Creates new form pembayaran1
     */
    public pembayaran() {
        initComponents();
        this.setVisible(true);
        BasicInternalFrameUI bifUI = ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI());
        for (MouseListener listener : bifUI.getNorthPane().getMouseListeners()) {
            bifUI.getNorthPane().removeMouseListener(listener);
        }
        codePembayaran();
    }

    public void cekDenda() {
        Date tgl = new Date();
        SimpleDateFormat tgli = new SimpleDateFormat("MM");
        int month = Integer.parseInt(tgli.format(tgl.getTime()));
        int selisih = month - bulan;
        denda = selisih * 2000;
        denda1 = Float.toString(denda);
        if (nama.isEmpty()) {
            txt_denda.setText("");
            JOptionPane.showMessageDialog(rootPane, "Data yang anda cari tidak ada!");
        } else if (!nama.isEmpty()) {
            txt_denda.setText(denda1);
        }
    }

    public void convert() {
        String box = (String) combo_bulan.getSelectedItem();
        if (box == ("Januari")) {
            bulan = 1;
            bulanhari = 31;
        } else if (box == ("Februari")) {
            bulan = 2;
            bulanhari = 29;
        } else if (box == ("Maret")) {
            bulan = 3;
            bulanhari = 31;
        } else if (box == ("April")) {
            bulan = 4;
            bulanhari = 30;
        } else if (box == ("Mei")) {
            bulan = 5;
            bulanhari = 31;
        } else if (box == ("Juni")) {
            bulan = 6;
            bulanhari = 30;
        } else if (box == ("Juli")) {
            bulan = 7;
            bulanhari = 31;
        } else if (box == ("Agustus")) {
            bulan = 8;
            bulanhari = 31;
        } else if (box == ("September")) {
            bulan = 9;
            bulanhari = 30;
        } else if (box == ("Oktober")) {
            bulan = 10;
            bulanhari = 31;
        } else if (box == ("November")) {
            bulan = 11;
            bulanhari = 30;
        } else if (box == ("Desember")) {
            bulan = 12;
            bulanhari = 31;
        }
    }

    public void cekTagihan() {
        String box = (String) combo_bulan.getSelectedItem();
        String box1 = (String) combo_tahun.getSelectedItem();

        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql = "SELECT\n"
                    + "    `tbl_tagihan`.`id_tagihan`\n"
                    + "    , `tbl_pelanggan`.`id_pelanggan`\n"
                    + "    , `tbl_pelanggan`.`nama`\n"
                    + "    , `tbl_tagihan`.`tahunTagih`\n"
                    + "    , `tbl_tagihan`.`bulanTagih`\n"
                    + "    , `tbl_tagihan`.`pemakaian`\n"
                    + "    , `tbl_tagihan`.`status`\n"
                    + "    , `tbl_tarif`.`tarif_perKWH`\n"
                    + "    , `tbl_tarif`.`beban`\n"
                    + "FROM\n"
                    + "    `db_pembayaran_listrik`.`tbl_pelanggan`\n"
                    + "    INNER JOIN `db_pembayaran_listrik`.`tbl_tarif` \n"
                    + "        ON (`tbl_pelanggan`.`kode_tarif` = `tbl_tarif`.`kode_tarif`)\n"
                    + "    INNER JOIN `db_pembayaran_listrik`.`tbl_tagihan` \n"
                    + "        ON (`tbl_tagihan`.`id_pelanggan` = `tbl_pelanggan`.`id_pelanggan`)\n"
                    + "WHERE tbl_pelanggan.id_pelanggan='" + txt_pelanggan.getText() + "' AND bulanTagih='" + box + "' AND tahunTagih='" + box1 + "'        \n"
                    + "        ;";
            ////System.out.println(sql)
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                tagihan = rs.getString("id_tagihan");
                pelanggan = rs.getString("id_pelanggan");
                nama = rs.getString("nama");
                tahun = rs.getString("tahunTagih");
                bulan1 = rs.getString("bulanTagih");
                pemakaian = rs.getString("pemakaian");

                tarif = rs.getString("tarif_perKWH");

                beban = rs.getString("beban");
                status = rs.getString("status");

            }
            if (status.equals("belum lunas")) {
                pemakaian1 = Float.parseFloat(pemakaian);
                tarif1 = Float.parseFloat(tarif);
                beban1 = Float.parseFloat(beban);

                txt_idpelanggan.setText(pelanggan);
                txt_nama.setText(nama);
                txt_tagihan.setText(tagihan);

            }
            if (status.equals("lunas")) {
                nama = null;
                JOptionPane.showMessageDialog(rootPane, "Tagihan ini sudah dibayar!");
            }
            rs.close();
            ////System.out.println(id_peminjaman);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Data yang anda cari tidak ada!");
        }
    }

    public void total() {
        float admin = 2500;
        admin1 = Float.toString(admin);
        float jumlah = pemakaian1 * tarif1 * bulanhari;
        //System.out.println(pemakaian1);
        //System.out.println(tarif1);
        //System.out.println(bulanhari);
        jumlah1 = Float.toString(jumlah);
        float total = jumlah + beban1 + admin + denda;
        total1 = Float.toString(total);
        beban2 = Float.toString(beban1);
        if (nama.isEmpty()) {
            txt_jumlah.setText("");
            txt_total.setText("");
            txt_admin.setText("");
        } else if (!nama.isEmpty()) {
            txt_jumlah.setText(jumlah1);
            txt_total.setText(total1);
            txt_admin.setText(admin1);
            txt_beban.setText(beban2);
        }
        //System.out.println(txt_pelanggan.getText());
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

    public void reset() {
        txt_admin.setText("");
        txt_denda.setText("");
        txt_idpelanggan.setText("");
        txt_jumlah.setText("");
        txt_nama.setText("");
        txt_beban.setText("");
        txt_pelanggan.setText("");
        txt_tagihan.setText("");
        txt_total.setText("");
        combo_bulan.setSelectedItem("-Bulan-");
        combo_tahun.setSelectedItem("-Tahun-");
        pelanggan = null;
        nama = null;
        tagihan = null;
        denda1 = null;
        jumlah1 = null;
        total1 = null;
        admin1 = null;
        codePembayaran();
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
                    String pn1a = txt_idpelanggan.getText();
                    String pn2a = txt_nama.getText();
                    String pn3a = txt_tagihan.getText();
                    String pn4a = txt_jumlah.getText();
                    String pn5a = txt_denda.getText();
                    String pn6a = txt_admin.getText();
                    String pn7a = txt_total.getText();
                    String pn8a = login.id_petugas;
                    String pn9a = id_otomatis;
                    String pn10a = txt_beban.getText();
                    String pn11a = pemakaian;
                    ///////////////// Product names Get ///////////

                    g2d.setFont(new Font("Monospaced", Font.PLAIN, 9));
                    g2d.drawString("-----------------------------------------------------", 12, y);
                    y += yShift;
                    g2d.drawString("              Tanda Terima Pembayaran               ", 12, y);
                    y += yShift;
                    g2d.drawString("-----------------------------------------------------", 12, y);
                    y += headerRectHeight;
                    g2d.drawString("-----------------------------------------------------", 10, y);
                    y += yShift;
                    g2d.drawString("ID Pembayaran  : " + pn9a + "      ID Tagihan   : " + pn3a + "          ", 10, y);
                    y += yShift;
                    g2d.drawString("ID Pelanggan   : " + pn1a + "      ID Petugas   : " + pn8a + "          ", 10, y);
                    y += yShift;
                    g2d.drawString("Nama           : " + pn2a + "                                ", 10, y);
                    y += headerRectHeight;
                    g2d.drawString("Pemakaian      : " + pn11a + " perKWH                        ", 10, y);
                    y += yShift;
                    g2d.drawString("Jumlah Tagihan : Rp " + pn4a + "                             ", 10, y);
                    y += yShift;
                    g2d.drawString("Denda          : Rp " + pn5a + "                             ", 10, y);
                    y += yShift;
                    g2d.drawString("Beban          : Rp " + pn10a + "                            ", 10, y);
                    y += yShift;
                    g2d.drawString("Biaya Admin    : Rp " + pn6a + "                             ", 10, y);
                    y += yShift;
                    g2d.drawString("------------------------------------------------------", 10, y);
                    y += yShift;
                    g2d.drawString("                   Total : RP " + pn7a + "                  ", 10, y);
                    y += yShift;
                    g2d.drawString("------------------------------------------------------", 10, y);
                    y += yShift;
                    g2d.drawString("Smart Electricity Menyatakan Struk ini sebagai Pembayaran yang sah  ", 10, y);
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

    public static String id_otomatis;

    public void codePembayaran() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql = "SELECT * from tbl_pembayaran order by id_pembayaran desc";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                String kode = rs.getString("id_pembayaran").substring(3);
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
                id_otomatis = "PT-" + Nol + AN;
            } else {
                id_otomatis = "PT-0001";
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "masih ada kesalahan di " + e.getMessage());
        }

    }

    public void simpan() {
        try {
            String status = "lunas";
            Connection con = koneksi.connection();
            String sql = "insert into tbl_pembayaran(id_pembayaran,id_tagihan,id_petugas,jumlah_tagihan,biaya_denda,biaya_admin,total_bayar,status) values(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ////System.out.println(sql)
            ps.setString(1, id_otomatis);

            ps.setString(2, txt_tagihan.getText());
            ps.setString(3, login.id_petugas);
            ps.setString(4, txt_jumlah.getText());
            ps.setString(5, txt_denda.getText());
            ps.setString(6, txt_admin.getText());
            ps.setString(7, txt_total.getText());
            ps.setString(8, status);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            //System.out.println("Terjadi error");
        }
    }

    public void cetak() {
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(new BillPrintable(), getPageFormat(pj));
        try {
            pj.print();

        } catch (PrinterException ex) {
            ex.printStackTrace();
        }
    }

    public void ubah() {
        try {
            String status = "lunas";
            Connection con = koneksi.connection();
            String sql = "update tbl_tagihan set tbl_tagihan.status='" + status + "' where id_tagihan='" + txt_tagihan.getText() + "' ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
        }
    }

    public void ubah1() {
        try {
            String status = "lunas";
            Connection con = koneksi.connection();
            String sql1 = "update tbl_pelanggan set tbl_pelanggan.status='aktif' where id_pelanggan='" + txt_idpelanggan.getText() + "' ";
            PreparedStatement ps1 = con.prepareStatement(sql1);
            ps1.executeUpdate();
            ps1.close();
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
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

        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        txt_pelanggan = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btn_cari = new javax.swing.JButton();
        combo_tahun = new javax.swing.JComboBox<>();
        combo_bulan = new javax.swing.JComboBox<>();
        simpan_tagihan1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txt_idpelanggan = new javax.swing.JTextField();
        txt_nama = new javax.swing.JTextField();
        txt_tagihan = new javax.swing.JTextField();
        txt_jumlah = new javax.swing.JTextField();
        txt_denda = new javax.swing.JTextField();
        txt_admin = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_total = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btn_reset = new javax.swing.JButton();
        txt_beban = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();

        jCheckBox1.setText("jCheckBox1");

        setClosable(true);
        setPreferredSize(new java.awt.Dimension(999, 461));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Pelanggan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        txt_pelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_pelangganActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel1.setText("Masukkan ID Pelanggan");

        btn_cari.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_cari.setText("Cari");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });

        combo_tahun.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Tahun-", "2024", "2025", "2026", "2027" }));
        combo_tahun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_tahunActionPerformed(evt);
            }
        });

        combo_bulan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Bulan-", "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember" }));

        simpan_tagihan1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        simpan_tagihan1.setText("Laporan");
        simpan_tagihan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpan_tagihan1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txt_pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(combo_bulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(combo_tahun, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(simpan_tagihan1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo_tahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cari)
                    .addComponent(combo_bulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(simpan_tagihan1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Data Pembayaran", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel2.setText("ID Pelanggan");

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel4.setText("Nama");

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel5.setText("ID Tagihan");

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel6.setText("Jumlah Tagihan");

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel7.setText("Denda");

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel8.setText("Biaya Admin");

        jLabel9.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel9.setText("Total");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Bayar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txt_idpelanggan.setEditable(false);

        txt_nama.setEditable(false);

        txt_tagihan.setEditable(false);

        txt_jumlah.setEditable(false);

        txt_denda.setEditable(false);

        txt_admin.setEditable(false);

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel3.setText(":");

        jLabel10.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel10.setText(":");

        jLabel11.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel11.setText(":");

        jLabel12.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel12.setText(":");

        jLabel13.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel13.setText(":");

        jLabel14.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel14.setText(":");

        jLabel15.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel15.setText(":");

        txt_total.setEditable(false);
        txt_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totalActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel16.setText("Rp");

        jLabel17.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel17.setText("Rp");

        jLabel18.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel18.setText("Rp");

        jLabel19.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel19.setText("Rp");

        btn_reset.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_reset.setText("Reset");
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });

        txt_beban.setEditable(false);

        jLabel20.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel20.setText(":");

        jLabel21.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel21.setText("Beban");

        jLabel22.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel22.setText("Rp");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_reset, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6)
                                .addComponent(jLabel7)
                                .addComponent(jLabel8)
                                .addComponent(jLabel21))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addGap(83, 83, 83)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_admin, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_denda, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_tagihan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_idpelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_beban, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(246, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_idpelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_tagihan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txt_denda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txt_admin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel17)))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_beban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel15)
                    .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_reset, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_pelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_pelangganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_pelangganActionPerformed

    private void txt_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalActionPerformed

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        pelanggan = null;
        nama = null;
        tagihan = null;
        denda1 = null;
        jumlah1 = null;
        total1 = null;
        admin1 = null;
        countTagihan();
        if (itung == 1) {
            cekTagihan();
            convert();
            cekDenda();
            total();
        } else if (itung == 0) {
            JOptionPane.showMessageDialog(rootPane, "Maaf data yang anda cari tidak ada!");
            reset();
        } else {
            Object[] options = {"Ya", "Tidak"};
            int dialogBotton = JOptionPane.showOptionDialog(null, "Maaf ada tagihan yang belum dibayar ! Tagihan ini yang akan anda bayar ?", "", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (dialogBotton == JOptionPane.YES_OPTION) {
                cekTagihan();
                convert();
                cekDenda();
                total();
            }

        }

    }//GEN-LAST:event_btn_cariActionPerformed

    private void combo_tahunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_tahunActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_tahunActionPerformed

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        reset();
    }//GEN-LAST:event_btn_resetActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int x = JOptionPane.showConfirmDialog(rootPane, "Apakah anda yakin ?", "Mohon konfirmasi data", 0);
        if (x == JOptionPane.YES_OPTION) {
            simpan();
            ubah();
            countTagihan();
            if (itung == 0) {
                ubah1();
            }
            cetak();
            reset();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void simpan_tagihan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpan_tagihan1ActionPerformed
        report12 cb = new report12();
        cb.setVisible(true);
    }//GEN-LAST:event_simpan_tagihan1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_reset;
    private javax.swing.JComboBox<String> combo_bulan;
    private javax.swing.JComboBox<String> combo_tahun;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton simpan_tagihan1;
    private javax.swing.JTextField txt_admin;
    private javax.swing.JTextField txt_beban;
    private javax.swing.JTextField txt_denda;
    private javax.swing.JTextField txt_idpelanggan;
    private javax.swing.JTextField txt_jumlah;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_pelanggan;
    private javax.swing.JTextField txt_tagihan;
    private javax.swing.JTextField txt_total;
    // End of variables declaration//GEN-END:variables
}
