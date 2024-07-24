/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uas.view;

import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.Timer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import static uas.view.report.hasil1;

/**
 *
 * @author Raka Putra
 */
public class home extends javax.swing.JFrame {

    login hm;
    backup_restore br;
    public int cek;
    public static String hasil3, hasil4, hasil7, hasil8, hasil9, hasil1lunas, hasil2lunas, hasil3lunas, hasil4lunas, hasil5lunas, hasil6lunas, hasil7lunas, hasil8lunas, hasil9lunas, hasil10lunas, hasil11lunas, hasil12lunas, hasil1tidak, hasil2tidak, hasil3tidak, hasil4tidak, hasil5tidak, hasil6tidak, hasil7tidak, hasil8tidak, hasil9tidak, hasil10tidak, hasil11tidak, hasil12tidak;

    /**
     * Creates new form home
     */
    public home() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        TanggalOtomatis();

        JamOtomatis();
        if (login.status.equals("petugas pembayaran")) {
            btn_tagihan.setVisible(false);
            btn_tarif.setVisible(false);
            btn_petugas.setVisible(false);
            btn_sambunganbaru.setVisible(false);
            btn_riwayat.setVisible(false);
        }
        if (login.status.equals("petugas tagihan")) {
            btn_pembayaran.setVisible(false);
            btn_petugas.setVisible(false);
            btn_sambunganbaru.setVisible(false);
        }
        if (login.status.equals("petugas pendaftaran")) {
            btn_pembayaran.setVisible(false);
            btn_tagihan.setVisible(false);
            btn_tarif.setVisible(false);
            jPanel2.setVisible(false);
        }
    }

    public void itungPembayaranJanuari() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql = "SELECT COUNT(*) as hasil FROM tbl_tagihan WHERE tbl_tagihan.status='lunas' AND bulanTagih='Januari' AND tahunTagih='" + combo_bulan.getSelectedItem() + "'";
            ////System.out.println(sql)
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                hasil1lunas = rs.getString("hasil");
            }
            rs.close();

        } catch (Exception e) {
            hasil1lunas = "0";
        }
    }

    public void itungPembayaranJanuari1() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql1 = "SELECT COUNT(*) as hasil FROM tbl_tagihan WHERE tbl_tagihan.status='belum lunas' AND bulanTagih='Januari' AND tahunTagih='" + combo_bulan.getSelectedItem() + "'";
            //System.out.println(sql1);
            ResultSet ra = st.executeQuery(sql1);
            while (ra.next()) {
                hasil1tidak = ra.getString("hasil");
            }
            ra.close();
        } catch (Exception e) {
            hasil1tidak = "0";
        }
    }

    public void itungPembayaranFebruari() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql = "SELECT COUNT(*) as hasil FROM tbl_tagihan WHERE tbl_tagihan.status='lunas' AND bulanTagih='Februari' AND tahunTagih='" + combo_bulan.getSelectedItem() + "'";
            ////System.out.println(sql)
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                hasil2lunas = rs.getString("hasil");
            }
            rs.close();
        } catch (Exception e) {
            hasil2lunas = "0";
        }
    }

    public void itungPembayaranFebruari1() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql1 = "SELECT COUNT(*) as hasil FROM tbl_tagihan WHERE tbl_tagihan.status='belum lunas' AND bulanTagih='Februari' AND tahunTagih='" + combo_bulan.getSelectedItem() + "'";
            //System.out.println(sql1);
            ResultSet ra = st.executeQuery(sql1);
            while (ra.next()) {
                hasil2tidak = ra.getString("hasil");
            }
            ra.close();
        } catch (Exception e) {
            hasil2tidak = "0";
        }
    }

    public void itungPembayaranMaret() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql = "SELECT COUNT(*) as hasil FROM tbl_tagihan WHERE tbl_tagihan.status='lunas' AND bulanTagih='Maret' AND tahunTagih='" + combo_bulan.getSelectedItem() + "'";
            ////System.out.println(sql)
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                hasil3lunas = rs.getString("hasil");
            }
            rs.close();
        } catch (Exception e) {
            hasil3lunas = "0";
        }
    }

    public void itungPembayaranMaret1() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql1 = "SELECT COUNT(*) as hasil FROM tbl_tagihan WHERE tbl_tagihan.status='belum lunas' AND bulanTagih='Maret' AND tahunTagih='" + combo_bulan.getSelectedItem() + "'";
            //System.out.println(sql1);
            ResultSet ra = st.executeQuery(sql1);
            while (ra.next()) {
                hasil3tidak = ra.getString("hasil");
            }
            ra.close();
        } catch (Exception e) {
            hasil3tidak = "0";
        }
    }

    public void itungPembayaranApril() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql = "SELECT COUNT(*) as hasil FROM tbl_tagihan WHERE tbl_tagihan.status='lunas' AND bulanTagih='April' AND tahunTagih='" + combo_bulan.getSelectedItem() + "'";
            ////System.out.println(sql)
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                hasil4lunas = rs.getString("hasil");
            }
            rs.close();
        } catch (Exception e) {
            hasil4lunas = "0";
        }
    }

    public void itungPembayaranApril1() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql1 = "SELECT COUNT(*) as hasil FROM tbl_tagihan WHERE tbl_tagihan.status='belum lunas' AND bulanTagih='April' AND tahunTagih='" + combo_bulan.getSelectedItem() + "'";
            //System.out.println(sql1);
            ResultSet ra = st.executeQuery(sql1);
            while (ra.next()) {
                hasil4tidak = ra.getString("hasil");
            }
            ra.close();
        } catch (Exception e) {
            hasil4tidak = "0";
        }
    }

    public void itungPembayaranMei() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql = "SELECT COUNT(*) as hasil FROM tbl_tagihan WHERE tbl_tagihan.status='lunas' AND bulanTagih='Mei' AND tahunTagih='" + combo_bulan.getSelectedItem() + "'";
            ////System.out.println(sql)
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                hasil5lunas = rs.getString("hasil");
            }
            rs.close();
        } catch (Exception e) {
            hasil5lunas = "0";
        }
    }

    public void itungPembayaranMei1() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql1 = "SELECT COUNT(*) as hasil FROM tbl_tagihan WHERE tbl_tagihan.status='belum lunas' AND bulanTagih='Mei' AND tahunTagih='" + combo_bulan.getSelectedItem() + "'";
            //System.out.println(sql1);
            ResultSet ra = st.executeQuery(sql1);
            while (ra.next()) {
                hasil5tidak = ra.getString("hasil");
            }
            ra.close();
        } catch (Exception e) {
            hasil5tidak = "0";
        }
    }

    public void itungPembayaranJuni() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql = "SELECT COUNT(*) as hasil FROM tbl_tagihan WHERE tbl_tagihan.status='lunas' AND bulanTagih='Juni' AND tahunTagih='" + combo_bulan.getSelectedItem() + "'";
            ////System.out.println(sql)
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                hasil6lunas = rs.getString("hasil");
            }
            rs.close();
        } catch (Exception e) {
            hasil6lunas = "0";
        }
    }

    public void itungPembayaranJuni1() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql1 = "SELECT COUNT(*) as hasil FROM tbl_tagihan WHERE tbl_tagihan.status='belum lunas' AND bulanTagih='Juni' AND tahunTagih='" + combo_bulan.getSelectedItem() + "'";
            //System.out.println(sql1);
            ResultSet ra = st.executeQuery(sql1);
            while (ra.next()) {
                hasil6tidak = ra.getString("hasil");
            }
            ra.close();
        } catch (Exception e) {
            hasil6tidak = "0";
        }
    }

    public void itungPembayaranJuli() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql = "SELECT COUNT(*) as hasil FROM tbl_tagihan WHERE tbl_tagihan.status='lunas' AND bulanTagih='Juli' AND tahunTagih='" + combo_bulan.getSelectedItem() + "'";
            ////System.out.println(sql)
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                hasil7lunas = rs.getString("hasil");
            }
            rs.close();
        } catch (Exception e) {
            hasil7lunas = "0";
        }
    }

    public void itungPembayaranJuli1() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql1 = "SELECT COUNT(*) as hasil FROM tbl_tagihan WHERE tbl_tagihan.status='belum lunas' AND bulanTagih='Juli' AND tahunTagih='" + combo_bulan.getSelectedItem() + "'";
            //System.out.println(sql1);
            ResultSet ra = st.executeQuery(sql1);
            while (ra.next()) {
                hasil7tidak = ra.getString("hasil");
            }
            ra.close();
        } catch (Exception e) {
            hasil7tidak = "0";
        }
    }

    public void itungPembayaranAgustus() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql = "SELECT COUNT(*) as hasil FROM tbl_tagihan WHERE tbl_tagihan.status='lunas' AND bulanTagih='Agustus' AND tahunTagih='" + combo_bulan.getSelectedItem() + "'";
            ////System.out.println(sql)
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                hasil8lunas = rs.getString("hasil");
            }
            rs.close();
        } catch (Exception e) {
            hasil8lunas = "0";
        }
    }

    public void itungPembayaranAgustus1() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql1 = "SELECT COUNT(*) as hasil FROM tbl_tagihan WHERE tbl_tagihan.status='belum lunas' AND bulanTagih='Agustus' AND tahunTagih='" + combo_bulan.getSelectedItem() + "'";
            //System.out.println(sql1);
            ResultSet ra = st.executeQuery(sql1);
            while (ra.next()) {
                hasil8tidak = ra.getString("hasil");
            }
            ra.close();
        } catch (Exception e) {
            hasil8tidak = "0";
        }
    }

    public void itungPembayaranSeptember() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql = "SELECT COUNT(*) as hasil FROM tbl_tagihan WHERE tbl_tagihan.status='lunas' AND bulanTagih='September' AND tahunTagih='" + combo_bulan.getSelectedItem() + "'";
            ////System.out.println(sql)
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                hasil9lunas = rs.getString("hasil");
            }
            rs.close();
        } catch (Exception e) {
            hasil9lunas = "0";
        }
    }

    public void itungPembayaranSeptember1() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql1 = "SELECT COUNT(*) as hasil FROM tbl_tagihan WHERE tbl_tagihan.status='belum lunas' AND bulanTagih='September' AND tahunTagih='" + combo_bulan.getSelectedItem() + "'";
            //System.out.println(sql1);
            ResultSet ra = st.executeQuery(sql1);
            while (ra.next()) {
                hasil9tidak = ra.getString("hasil");
            }
            ra.close();
        } catch (Exception e) {
            hasil9tidak = "0";
        }
    }

    public void itungPembayaranOktober() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql = "SELECT COUNT(*) as hasil FROM tbl_tagihan WHERE tbl_tagihan.status='lunas' AND bulanTagih='Oktober' AND tahunTagih='" + combo_bulan.getSelectedItem() + "'";
            ////System.out.println(sql)
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                hasil10lunas = rs.getString("hasil");
            }
            rs.close();
        } catch (Exception e) {
            hasil10lunas = "0";
        }
    }

    public void itungPembayaranOktober1() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql1 = "SELECT COUNT(*) as hasil FROM tbl_tagihan WHERE tbl_tagihan.status='belum lunas' AND bulanTagih='Oktober' AND tahunTagih='" + combo_bulan.getSelectedItem() + "'";
            //System.out.println(sql1);
            ResultSet ra = st.executeQuery(sql1);
            while (ra.next()) {
                hasil10tidak = ra.getString("hasil");
            }
            ra.close();
        } catch (Exception e) {
            hasil10tidak = "0";
        }
    }

    public void itungPembayaranNovember() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql = "SELECT COUNT(*) as hasil FROM tbl_tagihan WHERE tbl_tagihan.status='lunas' AND bulanTagih='November' AND tahunTagih='" + combo_bulan.getSelectedItem() + "'";
            ////System.out.println(sql)
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                hasil11lunas = rs.getString("hasil");
            }
            rs.close();
        } catch (Exception e) {
            hasil11lunas = "0";
        }
    }

    public void itungPembayaranNovember1() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql1 = "SELECT COUNT(*) as hasil FROM tbl_tagihan WHERE tbl_tagihan.status='belum lunas' AND bulanTagih='November' AND tahunTagih='" + combo_bulan.getSelectedItem() + "'";
            //System.out.println(sql1);
            ResultSet ra = st.executeQuery(sql1);
            while (ra.next()) {
                hasil11tidak = ra.getString("hasil");
            }
            ra.close();
        } catch (Exception e) {
            hasil11tidak = "0";
        }
    }

    public void itungPembayaranDesember() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql = "SELECT COUNT(*) as hasil FROM tbl_tagihan WHERE tbl_tagihan.status='lunas' AND bulanTagih='Desember' AND tahunTagih='" + combo_bulan.getSelectedItem() + "'";
            ////System.out.println(sql)
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                hasil12lunas = rs.getString("hasil");
            }
            rs.close();
        } catch (Exception e) {
            hasil12lunas = "0";
        }
    }

    public void itungPembayaranDesember1() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql1 = "SELECT COUNT(*) as hasil FROM tbl_tagihan WHERE tbl_tagihan.status='belum lunas' AND bulanTagih='Desember' AND tahunTagih='" + combo_bulan.getSelectedItem() + "'";
            //System.out.println(sql1);
            ResultSet ra = st.executeQuery(sql1);
            while (ra.next()) {
                hasil12tidak = ra.getString("hasil");
            }
            ra.close();
        } catch (Exception e) {
            hasil12tidak = "0";
        }
    }

    public void itungdaya1() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql = "SELECT\n"
                    + "    COUNT(`tbl_pelanggan`.`kode_tarif`) AS hasil\n"
                    + "    \n"
                    + "FROM\n"
                    + "    `db_pembayaran_listrik`.`tbl_pelanggan`\n"
                    + "    INNER JOIN `db_pembayaran_listrik`.`tbl_tarif` \n"
                    + "        ON (`tbl_pelanggan`.`kode_tarif` = `tbl_tarif`.`kode_tarif`) WHERE tbl_tarif.daya='450';";
            ////System.out.println(sql)
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                hasil7 = rs.getString("hasil");
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Data yang anda cari tidak ada!");
        }
    }

    public void itungdaya2() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql = "SELECT\n"
                    + "    COUNT(`tbl_pelanggan`.`kode_tarif`) AS hasil\n"
                    + "    \n"
                    + "FROM\n"
                    + "    `db_pembayaran_listrik`.`tbl_pelanggan`\n"
                    + "    INNER JOIN `db_pembayaran_listrik`.`tbl_tarif` \n"
                    + "        ON (`tbl_pelanggan`.`kode_tarif` = `tbl_tarif`.`kode_tarif`) WHERE tbl_tarif.daya='900';";
            ////System.out.println(sql)
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                hasil8 = rs.getString("hasil");
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Data yang anda cari tidak ada!");
        }
    }

    public void itungdaya3() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql = "SELECT\n"
                    + "    COUNT(`tbl_pelanggan`.`kode_tarif`) AS hasil\n"
                    + "    \n"
                    + "FROM\n"
                    + "    `db_pembayaran_listrik`.`tbl_pelanggan`\n"
                    + "    INNER JOIN `db_pembayaran_listrik`.`tbl_tarif` \n"
                    + "        ON (`tbl_pelanggan`.`kode_tarif` = `tbl_tarif`.`kode_tarif`) WHERE tbl_tarif.daya='1300';";
            ////System.out.println(sql)
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                hasil9 = rs.getString("hasil");
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Data yang anda cari tidak ada!");
        }
    }

    public void itungaktif() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql = "SELECT COUNT(*) as hasil FROM tbl_pelanggan WHERE tbl_pelanggan.status='aktif'";
            ////System.out.println(sql)
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                hasil3 = rs.getString("hasil");
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Data yang anda cari tidak ada!");
        }
    }

    public void itungnonaktif() {
        try {
            Connection con = koneksi.connection();
            Statement st = con.createStatement();
            String sql = "SELECT COUNT(*) as hasil FROM tbl_pelanggan WHERE tbl_pelanggan.status='nonaktif'";
            ////System.out.println(sql)
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                hasil4 = rs.getString("hasil");
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Data yang anda cari tidak ada!");
        }
    }

    public void TanggalOtomatis() {
        Date tgl = new Date();
        SimpleDateFormat tgli = new SimpleDateFormat("EEEE,dd MMMM yyyy");
        tanggal.setText(tgli.format(tgl.getTime()));
    }

    public void JamOtomatis() {
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String nol_jam = "", nol_menit = "", nol_detik = "";

                Date datetime = new Date();
                int nilai_jam = datetime.getHours();
                int nilai_menit = datetime.getMinutes();
                int nilai_detik = datetime.getSeconds();

                if (nilai_jam <= 9) {
                    nol_jam = "0";
                }
                if (nilai_menit <= 9) {
                    nol_menit = "0";
                }
                if (nilai_detik <= 9) {
                    nol_detik = "0";
                }

                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);

                waktu.setText(jam + ":" + menit + ":" + detik + " ");
            }
        };
        new Timer(1000, taskPerformer).start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_pembayaran = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Desktop = new uas.Palette.PanelBackground2();
        btn_tagihan = new javax.swing.JButton();
        btn_tarif = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btn_sambunganbaru = new javax.swing.JButton();
        btn_petugas = new javax.swing.JButton();
        btn_help = new javax.swing.JButton();
        btn_logout = new javax.swing.JButton();
        waktu = new javax.swing.JLabel();
        tanggal = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btn_daya = new javax.swing.JButton();
        btn_pelanggan = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        combo_bulan = new javax.swing.JComboBox<>();
        btn_lihat = new javax.swing.JButton();
        btn_br = new javax.swing.JButton();
        btn_riwayat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        btn_pembayaran.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btn_pembayaran.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uas/Images/pembayaran.png"))); // NOI18N
        btn_pembayaran.setText("Pembayaran");
        btn_pembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pembayaranActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel1.setText("Smart Electricity");

        Desktop.setPreferredSize(new java.awt.Dimension(999, 461));

        javax.swing.GroupLayout DesktopLayout = new javax.swing.GroupLayout(Desktop);
        Desktop.setLayout(DesktopLayout);
        DesktopLayout.setHorizontalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        DesktopLayout.setVerticalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 538, Short.MAX_VALUE)
        );

        btn_tagihan.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btn_tagihan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uas/Images/AddTagihan.png"))); // NOI18N
        btn_tagihan.setText("Tagihan");
        btn_tagihan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tagihanActionPerformed(evt);
            }
        });

        btn_tarif.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btn_tarif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uas/Images/AddTarif.png"))); // NOI18N
        btn_tarif.setText("Tarif");
        btn_tarif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tarifActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uas/Images/1517754379785.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("We Exist Because We Care");

        btn_sambunganbaru.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btn_sambunganbaru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uas/Images/AddPelanggan.png"))); // NOI18N
        btn_sambunganbaru.setText("Sambungan Baru");
        btn_sambunganbaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sambunganbaruActionPerformed(evt);
            }
        });

        btn_petugas.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btn_petugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uas/Images/AddUser.png"))); // NOI18N
        btn_petugas.setText("Petugas");
        btn_petugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_petugasActionPerformed(evt);
            }
        });

        btn_help.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btn_help.setText("Help/About Us");
        btn_help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_helpActionPerformed(evt);
            }
        });

        btn_logout.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btn_logout.setText("Logout");
        btn_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_logoutActionPerformed(evt);
            }
        });

        waktu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        waktu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uas/Images/jam.png"))); // NOI18N

        tanggal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tanggal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uas/Images/tanggal.png"))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Grafik Pelanggan"));

        btn_daya.setText("Berdasarkan Daya");
        btn_daya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dayaActionPerformed(evt);
            }
        });

        btn_pelanggan.setText("Berdasarkan Status Pelanggan");
        btn_pelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pelangganActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_daya, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_pelanggan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_daya)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_pelanggan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Grafik Pembayaran"));

        combo_bulan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilih Tahun-", "2024", "2025", "2026", "2027" }));

        btn_lihat.setText("Lihat");
        btn_lihat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lihatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(combo_bulan, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(btn_lihat, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(combo_bulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_lihat))
        );

        btn_br.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btn_br.setText("Backup & Restore");
        btn_br.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_brActionPerformed(evt);
            }
        });

        btn_riwayat.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btn_riwayat.setText("Riwayat");
        btn_riwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_riwayatActionPerformed(evt);
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
                                .addComponent(btn_help, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_petugas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_tarif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_tagihan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_pembayaran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_sambunganbaru, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(btn_br, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 1142, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btn_riwayat, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(288, 606, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(waktu, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                            .addComponent(tanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(36, 36, 36))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(waktu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tanggal)
                                .addGap(8, 8, 8))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Desktop, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_riwayat, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_pembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_tagihan, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_tarif, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_sambunganbaru, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_petugas, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_help, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_br, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_tagihanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tagihanActionPerformed
        // TODO add your handling code here:
        try {
            tagihan coba = new tagihan();
            Desktop.removeAll();
            Desktop.repaint();
            Desktop.add(coba);
            Desktop.moveToFront(coba);
            coba.setSize(Desktop.getWidth(), Desktop.getHeight());
            coba.setLocation(0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_tagihanActionPerformed

    private void btn_tarifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tarifActionPerformed
        // TODO add your handling code here:
        try {
            tarif coba = new tarif();
            Desktop.removeAll();
            Desktop.repaint();
            Desktop.add(coba);
            Desktop.moveToFront(coba);
            coba.setSize(Desktop.getWidth(), Desktop.getHeight());
            coba.setLocation(0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_tarifActionPerformed

    private void btn_pembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pembayaranActionPerformed
        // TODO add your handling code here:
        try {
            pembayaran coba = new pembayaran();
            Desktop.removeAll();
            Desktop.repaint();
            Desktop.add(coba);
            Desktop.moveToFront(coba);
            coba.setSize(Desktop.getWidth(), Desktop.getHeight());
            coba.setLocation(0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_pembayaranActionPerformed

    private void btn_sambunganbaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sambunganbaruActionPerformed
        // TODO add your handling code here:
        try {
            sambunganbaru coba = new sambunganbaru();
            Desktop.removeAll();
            Desktop.repaint();
            Desktop.add(coba);
            Desktop.moveToFront(coba);
            coba.setSize(Desktop.getWidth(), Desktop.getHeight());
            coba.setLocation(0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_sambunganbaruActionPerformed

    private void btn_petugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_petugasActionPerformed
        // TODO add your handling code here:
        try {
            petugas coba = new petugas();
            Desktop.removeAll();
            Desktop.repaint();
            Desktop.add(coba);
            Desktop.moveToFront(coba);
            coba.setSize(Desktop.getWidth(), Desktop.getHeight());
            coba.setLocation(0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_petugasActionPerformed

    private void btn_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_logoutActionPerformed
        Object[] options = {"Ya", "Tidak"};
        int dialogBotton = JOptionPane.showOptionDialog(null, "Anda Yakin Ingin Logout?", "", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (dialogBotton == JOptionPane.YES_OPTION) {
            this.dispose();
            hm = new login();
            hm.setVisible(true);
        }
    }//GEN-LAST:event_btn_logoutActionPerformed

    private void btn_helpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_helpActionPerformed
        JOptionPane.showMessageDialog(rootPane, "Mohon Maaf , Fitur Ini Masih Dalam Pengembangan");
    }//GEN-LAST:event_btn_helpActionPerformed

    private void btn_pelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pelangganActionPerformed
        itungaktif();
        itungnonaktif();
        int hasil5 = Integer.parseInt(home.hasil3);
        int hasil6 = Integer.parseInt(home.hasil4);
        //System.out.println(hasil5);
        //System.out.println(hasil6);
        DefaultPieDataset objDataset = new DefaultPieDataset();
        objDataset.setValue("Aktif", new Integer(hasil5));
        objDataset.setValue("NonAktif", new Integer(hasil6));
        JFreeChart chart = ChartFactory.createPieChart("Grafik Pelanggan Berdasarkan status", objDataset, rootPaneCheckingEnabled, rootPaneCheckingEnabled, Locale.ITALY);
        ChartFrame frame = new ChartFrame("Grafik Pelanggan Berdasarkan status", chart);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }//GEN-LAST:event_btn_pelangganActionPerformed

    private void btn_dayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dayaActionPerformed
        itungdaya1();
        itungdaya2();
        itungdaya3();
        int hasil5 = Integer.parseInt(home.hasil7);
        int hasil6 = Integer.parseInt(home.hasil8);
        int hasil10 = Integer.parseInt(home.hasil9);
        //System.out.println(hasil5);
        //System.out.println(hasil6);
        DefaultPieDataset objDataset = new DefaultPieDataset();
        objDataset.setValue("450 V", new Integer(hasil5));
        objDataset.setValue("900 V", new Integer(hasil6));
        objDataset.setValue("1300 V", new Integer(hasil10));
        JFreeChart chart = ChartFactory.createPieChart("Grafik Pelanggan Berdasarkan Daya", objDataset, rootPaneCheckingEnabled, rootPaneCheckingEnabled, Locale.ITALY);
        ChartFrame frame = new ChartFrame("Grafik Pelanggan Berdasarkan Daya", chart);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }//GEN-LAST:event_btn_dayaActionPerformed

    private void btn_lihatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lihatActionPerformed
        itungPembayaranJanuari();
        itungPembayaranFebruari();
        itungPembayaranMaret();
        itungPembayaranApril();
        itungPembayaranMei();
        itungPembayaranJuni();
        itungPembayaranJuli();
        itungPembayaranAgustus();
        itungPembayaranSeptember();
        itungPembayaranOktober();
        itungPembayaranNovember();
        itungPembayaranDesember();
        itungPembayaranJanuari1();
        itungPembayaranFebruari1();
        itungPembayaranMaret1();
        itungPembayaranApril1();
        itungPembayaranMei1();
        itungPembayaranJuni1();
        itungPembayaranJuli1();
        itungPembayaranAgustus1();
        itungPembayaranSeptember1();
        itungPembayaranOktober1();
        itungPembayaranNovember1();
        itungPembayaranDesember1();
        int lunas1 = Integer.parseInt(home.hasil1lunas);
        int lunas2 = Integer.parseInt(home.hasil2lunas);
        int lunas3 = Integer.parseInt(home.hasil3lunas);
        int lunas4 = Integer.parseInt(home.hasil4lunas);
        int lunas5 = Integer.parseInt(home.hasil5lunas);
        int lunas6 = Integer.parseInt(home.hasil6lunas);
        int lunas7 = Integer.parseInt(home.hasil7lunas);
        int lunas8 = Integer.parseInt(home.hasil8lunas);
        int lunas9 = Integer.parseInt(home.hasil9lunas);
        int lunas10 = Integer.parseInt(home.hasil10lunas);
        int lunas11 = Integer.parseInt(home.hasil11lunas);
        int lunas12 = Integer.parseInt(home.hasil12lunas);
        int tidak1 = Integer.parseInt(home.hasil1tidak);
        int tidak2 = Integer.parseInt(home.hasil2tidak);
        int tidak3 = Integer.parseInt(home.hasil3tidak);
        int tidak4 = Integer.parseInt(home.hasil4tidak);
        int tidak5 = Integer.parseInt(home.hasil5tidak);
        int tidak6 = Integer.parseInt(home.hasil6tidak);
        int tidak7 = Integer.parseInt(home.hasil7tidak);
        int tidak8 = Integer.parseInt(home.hasil8tidak);
        int tidak9 = Integer.parseInt(home.hasil9tidak);
        int tidak10 = Integer.parseInt(home.hasil10tidak);
        int tidak11 = Integer.parseInt(home.hasil11tidak);
        int tidak12 = Integer.parseInt(home.hasil12tidak);
        String series1 = "Lunas";
        String series2 = "Belum Lunas";
        String category1 = "Januari";
        String category2 = "Februari";
        String category3 = "Maret";
        String category4 = "April";
        String category5 = "Mei";
        String category6 = "Juni";
        String category7 = "Juli";
        String category8 = "Agustus";
        String category9 = "September";
        String category10 = "Oktober";
        String category11 = "November";
        String category12 = "Desember";
        DefaultCategoryDataset objDataset = new DefaultCategoryDataset();
        objDataset.addValue(lunas1, series1, category1);
        objDataset.addValue(tidak1, series2, category1);
        objDataset.addValue(lunas2, series1, category2);
        objDataset.addValue(tidak2, series2, category2);
        objDataset.addValue(lunas3, series1, category3);
        objDataset.addValue(tidak3, series2, category3);
        objDataset.addValue(lunas4, series1, category4);
        objDataset.addValue(tidak4, series2, category4);
        objDataset.addValue(lunas5, series1, category5);
        objDataset.addValue(tidak5, series2, category5);
        objDataset.addValue(lunas6, series1, category7);
        objDataset.addValue(tidak6, series2, category7);
        objDataset.addValue(lunas8, series1, category8);
        objDataset.addValue(tidak8, series2, category8);
        objDataset.addValue(lunas9, series1, category9);
        objDataset.addValue(tidak9, series2, category9);
        objDataset.addValue(lunas10, series1, category10);
        objDataset.addValue(tidak10, series2, category10);
        objDataset.addValue(lunas11, series1, category11);
        objDataset.addValue(tidak11, series2, category11);
        objDataset.addValue(lunas12, series1, category12);
        objDataset.addValue(tidak12, series2, category12);
        JFreeChart chart = ChartFactory.createBarChart("Grafik Pembayaran Tagihan Tahun " + combo_bulan.getSelectedItem() + "", "Bulan", "Pelanggan", objDataset, PlotOrientation.VERTICAL, true, true, true);
        ChartFrame frame = new ChartFrame("Grafik Pelanggan Berdasarkan Pembayaran Tagihan", chart);
        frame.setSize(1200, 400);
        frame.setVisible(true);
    }//GEN-LAST:event_btn_lihatActionPerformed

    private void btn_brActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_brActionPerformed
        br = new backup_restore();
        br.setVisible(true);
    }//GEN-LAST:event_btn_brActionPerformed

    private void btn_riwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_riwayatActionPerformed
        // TODO add your handling code here:
        if (login.status.equals("petugas tagihan")) {
            riwayat_tagihan rt = new riwayat_tagihan();
            rt.setVisible(true);
        }
        if (login.status.equals("petugas pendaftaran")) {
            riwayat_pendaftaran rp = new riwayat_pendaftaran();
            rp.setVisible(true);
        }
    }//GEN-LAST:event_btn_riwayatActionPerformed

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
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane Desktop;
    private javax.swing.JButton btn_br;
    private javax.swing.JButton btn_daya;
    private javax.swing.JButton btn_help;
    private javax.swing.JButton btn_lihat;
    private javax.swing.JButton btn_logout;
    private javax.swing.JButton btn_pelanggan;
    private javax.swing.JButton btn_pembayaran;
    private javax.swing.JButton btn_petugas;
    private javax.swing.JButton btn_riwayat;
    private javax.swing.JButton btn_sambunganbaru;
    private javax.swing.JButton btn_tagihan;
    private javax.swing.JButton btn_tarif;
    private javax.swing.JComboBox<String> combo_bulan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel tanggal;
    private javax.swing.JLabel waktu;
    // End of variables declaration//GEN-END:variables
}
