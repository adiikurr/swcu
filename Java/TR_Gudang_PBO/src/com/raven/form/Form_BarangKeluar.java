package com.raven.form;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import koneksi.koneksi;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import swing.EventCallBack;
import swing.EventTextField;
import table.TableCustom;

public class Form_BarangKeluar extends javax.swing.JPanel {

    public Form_BarangKeluar() {
        initComponents();
        aktif();
//        autoIdBM();
//        autoIdBM_DT();
        dataTable();
        dataTable2();
        dataTable3();
        tanggal();
        lebarKolom();
        lebarKolom2();
        lebarKolom3();
        txtIdBarangMasuk.requestFocus();
        txtIdDetailBarangMasuk.setVisible(false);
        Locale local = new Locale("id", "ID");
        Locale.setDefault(local);
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);  
        TableCustom.apply(jScrollPane4, TableCustom.TableType.MULTI_LINE);  
        TableCustom.apply(jScrollPane5, TableCustom.TableType.MULTI_LINE);  

        txt1.addEvent(new EventTextField() {
            @Override
            public void onPressed(EventCallBack call) {
                //  Test
                try {
                    for (int i = 1; i <= 100; i++) {
                        lb1.setText("Test " + i);
                        Thread.sleep(7);
                    }

                    String sqlPencarian = "select * from tb_detail_brg_masuk where id_bm like '%" + txt1.getText() + "%'"
                            + "or id_detail_bm like '%" + txt1.getText() + "%'"
                            + "or nama_part like '%" + txt1.getText() + "%'"
                            + "or kode_part like '%" + txt1.getText() + "%'"
                            + "or supplier like '%" + txt1.getText() + "%'";
                    pencarian(sqlPencarian);
                    lebarKolom();

                    call.done();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }

            @Override
            public void onCancel() {

            }
        });

        txt2.addEvent(new EventTextField() {
            @Override
            public void onPressed(EventCallBack call) {
                //  Test
                try {
                    for (int i = 1; i <= 100; i++) {
                        lb2.setText("Test " + i);
                        Thread.sleep(7);
                    }

                    String sqlPencarian2 = "select * from tb_barang where kode_part like '%" + txt2.getText() + "%' or nama_part like '%" + txt2.getText() + "%'";
                    pencarian2(sqlPencarian2);
                    lebarKolom2();

                    call.done();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }

            @Override
            public void onCancel() {

            }
        });

        txt3.addEvent(new EventTextField() {
            @Override
            public void onPressed(EventCallBack call) {
                //  Test
                try {
                    for (int i = 1; i <= 100; i++) {
                        lb3.setText("Test " + i);
                        Thread.sleep(7);
                    }

                    String sqlPencarian3 = "select * from tb_supplier where kode_supplier like '%" + txt3.getText() + "%' or nama_supplier like '%" + txt3.getText() + "%'";
                    pencarian3(sqlPencarian3);
                    lebarKolom3();

                    call.done();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }

            @Override
            public void onCancel() {

            }
        });
    }

    public final Connection conn = new koneksi().connect();
    private DefaultTableModel tabmode;
    private DefaultTableModel tabmode2;
    private DefaultTableModel tabmode3;

    JasperReport JasRep;
    JasperPrint JasPri;
    Map param = new HashMap();
    JasperDesign JasDes;

    // frame maks dan geser panel
    static boolean maximixed = true;
    int xMouse;
    int yMouse;

    private void aktif() {
        txtIdBarangMasuk.setEnabled(true);
        txtSupplier.setEnabled(true);
        txtKodePart.setEnabled(true);
        txtNamaPart.setEnabled(true);
        txtJumlahBarang.setEnabled(true);
        txtKeterangan.setEnabled(true);
    }

    protected void kosong() {
        txtIdBarangMasuk.setText(null);
        txtSupplier.setText(null);
        txtKodePart.setText(null);
        txtNamaPart.setText(null);
        txtJumlahBarang.setText(null);
        txtKeterangan.setText(null);
    }

    protected void kosong2() {
        txtKodePart.setText(null);
        txtNamaPart.setText(null);
        txtJumlahBarang.setText(null);
        txtKeterangan.setText(null);
    }

    public void noTable() {
        int Baris = tabmode.getRowCount();
        for (int a = 0; a < Baris; a++) {
            String nomor = String.valueOf(a + 1);
            tabmode.setValueAt(nomor + ".", a, 0);
        }
    }

    public void tanggal() {
        Date tgl = new Date();
        btnPilihTanggal.setDate(tgl);
    }

//    private void autoIdBM() {
//        try {
//            Connection con = new koneksi().connect();
//            java.sql.Statement stat = con.createStatement();
//            String sql = "select max(right (id_bm,6)) as no from tb_brg_masuk";
//            ResultSet res = stat.executeQuery(sql);
//            while (res.next()) {
//                if (res.first() == false) {
//                    txtIdBarangMasuk.setText("BM-000001");
//                } else {
//                    res.last();
//                    int aut_id = res.getInt(1) + 1;
//                    String no = String.valueOf(aut_id);
//                    int no_jual = no.length();
//                    // mengatur jumlah 0
//                    for (int j = 0; j < 6 - no_jual; j++) {
//                        no = "0" + no;
//                    }
//                    txtIdBarangMasuk.setText("BM-" + no);
//                }
//            }
//            res.close();
//            stat.close();
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan");
//        }
//    }
//
//    private void autoIdBM_DT() {
//        try {
//            Connection con = new koneksi().connect();
//            java.sql.Statement stat = con.createStatement();
//            String sql_dt = "select max(right (id_detail_bm,4)) as no from tb_detail_brg_masuk";
//            ResultSet res_dt = stat.executeQuery(sql_dt);
//            while (res_dt.next()) {
//                if (res_dt.first() == false) {
//                    txtIdDetailBarangMasuk.setText("DTBM-0001");
//                } else {
//                    res_dt.last();
//                    int aut_id_dt = res_dt.getInt(1) + 1;
//                    String no_dt = String.valueOf(aut_id_dt);
//                    int no_jual_dt = no_dt.length();
//                    // mengatur jumlah 0
//                    for (int j = 0; j < 4 - no_jual_dt; j++) {
//                        no_dt = "0" + no_dt;
//                    }
//                    txtIdDetailBarangMasuk.setText("DTBM-" + no_dt);
//                }
//            }
//            res_dt.close();
//            stat.close();
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan");
//        }
//    }
    public void dataTable() {
        Object[] Baris = {"No", "Tanggal", "ID Detail BM", "ID BM", "Supplier", "Kode Part", "Nama Part", "Qty", "Keterangan"};
        tabmode = new DefaultTableModel(null, Baris);
        tabelBarangMasuk.setModel(tabmode);
        String sql = "select * from tb_detail_brg_masuk order by tanggal asc";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                String tanggal = hasil.getString("tanggal");
                String id_detail_bm = hasil.getString("id_detail_bm");
                String id_bm = hasil.getString("id_bm");
                String supplier = hasil.getString("supplier");
                String kode_part = hasil.getString("kode_part");
                String nama_part = hasil.getString("nama_part");
                String jumlah = hasil.getString("jumlah");
                String keterangan = hasil.getString("keterangan");
                String[] data = {"", tanggal, id_detail_bm, id_bm, supplier, kode_part, nama_part, jumlah, keterangan};
                tabmode.addRow(data);
                noTable();
            }
        } catch (Exception e) {
        }
    }

    public void dataTable2() {
        Object[] Baris = {"Kode Part", "Nama Part"};
        tabmode2 = new DefaultTableModel(null, Baris);
        tabelBarang.setModel(tabmode2);
        String sql = "select * from tb_barang order by kode_part asc";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                String kode_part = hasil.getString("kode_part");
                String nama_part = hasil.getString("nama_part");
                String[] data = {kode_part, nama_part};
                tabmode2.addRow(data);
            }
        } catch (Exception e) {
        }
    }

    public void dataTable3() {
        Object[] Baris = {"Kode Supplier", "Nama Supplier"};
        tabmode3 = new DefaultTableModel(null, Baris);
        tabelSupplier.setModel(tabmode3);
        String sql = "select * from tb_supplier order by kode_supplier asc";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                String kode_supplier = hasil.getString("kode_supplier");
                String nama_supplier = hasil.getString("nama_supplier");
                String[] data = {kode_supplier, nama_supplier};
                tabmode3.addRow(data);
            }
        } catch (Exception e) {
        }
    }

    public void lebarKolom() {
        TableColumn column;
        tabelBarangMasuk.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        column = tabelBarangMasuk.getColumnModel().getColumn(0);
        column.setPreferredWidth(40);
        column = tabelBarangMasuk.getColumnModel().getColumn(1);
        column.setPreferredWidth(100);
        column = tabelBarangMasuk.getColumnModel().getColumn(2);
        column.setPreferredWidth(100);
        column = tabelBarangMasuk.getColumnModel().getColumn(3);
        column.setPreferredWidth(100);
        column = tabelBarangMasuk.getColumnModel().getColumn(4);
        column.setPreferredWidth(200);
        column = tabelBarangMasuk.getColumnModel().getColumn(5);
        column.setPreferredWidth(100);
        column = tabelBarangMasuk.getColumnModel().getColumn(6);
        column.setPreferredWidth(250);
        column = tabelBarangMasuk.getColumnModel().getColumn(7);
        column.setPreferredWidth(70);
        column = tabelBarangMasuk.getColumnModel().getColumn(8);
        column.setPreferredWidth(300);
    }

    public void lebarKolom2() {
        TableColumn column2;
        tabelBarang.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        column2 = tabelBarang.getColumnModel().getColumn(0);
        column2.setPreferredWidth(80);
        column2 = tabelBarang.getColumnModel().getColumn(1);
        column2.setPreferredWidth(215);
    }

    public void lebarKolom3() {
        TableColumn column3;
        tabelSupplier.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        column3 = tabelSupplier.getColumnModel().getColumn(0);
        column3.setPreferredWidth(90);
        column3 = tabelSupplier.getColumnModel().getColumn(1);
        column3.setPreferredWidth(205);
    }

    public void pencarian(String sql) {
        Object[] Baris = {"No", "Tanggal", "ID Detail BM", "ID BM", "Supplier", "Kode Part", "Nama Part", "Qty", "Keterangan"};
        tabmode = new DefaultTableModel(null, Baris);
        tabelBarangMasuk.setModel(tabmode);
        int brs = tabelBarangMasuk.getRowCount();
        for (int i = 0; 1 < brs; i++) {
            tabmode.removeRow(1);
        }
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                String tanggal = hasil.getString("tanggal");
                String id_detail_bm = hasil.getString("id_detail_bm");
                String id_bm = hasil.getString("id_bm");
                String supplier = hasil.getString("supplier");
                String kode_part = hasil.getString("kode_part");
                String nama_part = hasil.getString("nama_part");
                String jumlah = hasil.getString("jumlah");
                String keterangan = hasil.getString("keterangan");
                String[] data = {"", tanggal, id_detail_bm, id_bm, supplier, kode_part, nama_part, jumlah, keterangan};
                tabmode.addRow(data);
                noTable();
            }
        } catch (Exception e) {

        }
    }

    public void pencarian2(String sql) {
        Object[] Baris2 = {"Kode Part", "Nama Part"};
        tabmode2 = new DefaultTableModel(null, Baris2);
        tabelBarang.setModel(tabmode2);
        int brs = tabelBarang.getRowCount();
        for (int i = 0; 1 < brs; i++) {
            tabmode2.removeRow(1);
        }
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                String kode_part = hasil.getString("kode_part");
                String nama_part = hasil.getString("nama_part");
                String[] data = {kode_part, nama_part};
                tabmode2.addRow(data);
            }
        } catch (Exception e) {

        }
    }

    public void pencarian3(String sql) {
        Object[] Baris = {"Kode Supplier", "Nama Supplier"};
        tabmode3 = new DefaultTableModel(null, Baris);
        tabelSupplier.setModel(tabmode3);
        int brs = tabelSupplier.getRowCount();
        for (int i = 0; 1 < brs; i++) {
            tabmode3.removeRow(1);
        }
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                String kode_supplier = hasil.getString("kode_supplier");
                String nama_supplier = hasil.getString("nama_supplier");
                String[] data = {kode_supplier, nama_supplier};
                tabmode3.addRow(data);
            }
        } catch (Exception e) {

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelShadow1 = new com.raven.swing.PanelShadow();
        btnTambah = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnBersih = new javax.swing.JButton();
        btnCetak = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelBarangMasuk = new javax.swing.JTable();
        txt1 = new swing.TextFieldAnimation();
        jLabel1 = new javax.swing.JLabel();
        lbTanggal = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        labelKodePart = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        labelNamaPart = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        labelKategori = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnPilihTanggal = new com.toedter.calendar.JDateChooser();
        txtIdBarangMasuk = new javax.swing.JTextField();
        txtSupplier = new javax.swing.JTextField();
        txtKodePart = new javax.swing.JTextField();
        txtNamaPart = new javax.swing.JTextField();
        labelKeterangan1 = new javax.swing.JLabel();
        txtJumlahBarang = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtKeterangan = new javax.swing.JTextArea();
        labelKategori1 = new javax.swing.JLabel();
        labelNamaBarang = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabelSupplier = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelBarang = new javax.swing.JTable();
        txtIdDetailBarangMasuk = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        lb1 = new javax.swing.JLabel();
        txt2 = new swing.TextFieldAnimation();
        lb2 = new javax.swing.JLabel();
        txt3 = new swing.TextFieldAnimation();
        lb3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(242, 246, 253));
        setPreferredSize(new java.awt.Dimension(1280, 720));

        btnTambah.setBackground(new java.awt.Color(204, 204, 204));
        btnTambah.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnTambah.setText("Tambah Item");
        btnTambah.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 207, 186), 3, true));
        btnTambah.setContentAreaFilled(false);
        btnTambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTambah.setOpaque(true);
        btnTambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTambahMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTambahMouseExited(evt);
            }
        });
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnSimpan.setBackground(new java.awt.Color(204, 204, 204));
        btnSimpan.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnSimpan.setText("Simpan");
        btnSimpan.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 207, 186), 3, true));
        btnSimpan.setContentAreaFilled(false);
        btnSimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSimpan.setOpaque(true);
        btnSimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSimpanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSimpanMouseExited(evt);
            }
        });
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnUbah.setBackground(new java.awt.Color(204, 204, 204));
        btnUbah.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnUbah.setText("Ubah");
        btnUbah.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 207, 186), 3, true));
        btnUbah.setContentAreaFilled(false);
        btnUbah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUbah.setOpaque(true);
        btnUbah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnUbahMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnUbahMouseExited(evt);
            }
        });
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        btnHapus.setBackground(new java.awt.Color(204, 204, 204));
        btnHapus.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 207, 186), 3, true));
        btnHapus.setContentAreaFilled(false);
        btnHapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHapus.setOpaque(true);
        btnHapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHapusMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHapusMouseExited(evt);
            }
        });
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnBersih.setBackground(new java.awt.Color(204, 204, 204));
        btnBersih.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnBersih.setText("Bersih");
        btnBersih.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 207, 186), 3, true));
        btnBersih.setContentAreaFilled(false);
        btnBersih.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBersih.setOpaque(true);
        btnBersih.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBersihMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBersihMouseExited(evt);
            }
        });
        btnBersih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBersihActionPerformed(evt);
            }
        });

        btnCetak.setBackground(new java.awt.Color(204, 204, 204));
        btnCetak.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnCetak.setText("Print");
        btnCetak.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 207, 186), 3, true));
        btnCetak.setContentAreaFilled(false);
        btnCetak.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCetak.setOpaque(true);
        btnCetak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCetakMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCetakMouseExited(evt);
            }
        });
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });

        tabelBarangMasuk.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabelBarangMasuk.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        tabelBarangMasuk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabelBarangMasuk.setRowHeight(30);
        tabelBarangMasuk.setRowMargin(2);
        tabelBarangMasuk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelBarangMasukMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelBarangMasuk);

        txt1.setAnimationColor(new java.awt.Color(176, 152, 142));
        txt1.setHintText("Cari Data ...");
        txt1.setSelectionColor(new java.awt.Color(176, 152, 142));
        txt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelShadow1Layout.createSequentialGroup()
                        .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnBersih, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnCetak, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1199, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBersih, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCetak, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(176, 152, 142));
        jLabel1.setText("Form Barang Keluar");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        lbTanggal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbTanggal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbTanggal.setText("Tanggal");
        lbTanggal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText(":");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        labelKodePart.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelKodePart.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelKodePart.setText("Kode Gudang");
        labelKodePart.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText(":");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        labelNamaPart.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelNamaPart.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelNamaPart.setText("Nama Gudang");
        labelNamaPart.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText(":");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        labelKategori.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelKategori.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelKategori.setText("No. Telepon");
        labelKategori.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText(":");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText(":");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        btnPilihTanggal.setDateFormatString("dd-MM-yyyy");
        btnPilihTanggal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnPilihTanggal.setMaximumSize(new java.awt.Dimension(2147400000, 2147400000));
        btnPilihTanggal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPilihTanggalMouseClicked(evt);
            }
        });
        btnPilihTanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPilihTanggalKeyPressed(evt);
            }
        });

        txtIdBarangMasuk.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtIdBarangMasuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdBarangMasukKeyPressed(evt);
            }
        });

        txtSupplier.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSupplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSupplierKeyPressed(evt);
            }
        });

        txtKodePart.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtKodePart.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtKodePartKeyPressed(evt);
            }
        });

        txtNamaPart.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNamaPart.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNamaPartKeyPressed(evt);
            }
        });

        labelKeterangan1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelKeterangan1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelKeterangan1.setText("Keterangan");
        labelKeterangan1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txtJumlahBarang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtJumlahBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtJumlahBarangKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtJumlahBarangKeyTyped(evt);
            }
        });

        txtKeterangan.setColumns(20);
        txtKeterangan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtKeterangan.setRows(5);
        jScrollPane2.setViewportView(txtKeterangan);

        labelKategori1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelKategori1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelKategori1.setText("Qty");
        labelKategori1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        labelNamaBarang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelNamaBarang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelNamaBarang.setText("Kode Part");
        labelNamaBarang.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText(":");
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        tabelSupplier.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabelSupplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        tabelSupplier.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabelSupplier.setRowHeight(30);
        tabelSupplier.setRowMargin(2);
        tabelSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelSupplierMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tabelSupplier);

        tabelBarang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabelBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        tabelBarang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabelBarang.setRowHeight(30);
        tabelBarang.setRowMargin(2);
        tabelBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelBarangMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabelBarang);

        txtIdDetailBarangMasuk.setEditable(false);
        txtIdDetailBarangMasuk.setBackground(new java.awt.Color(242, 246, 253));
        txtIdDetailBarangMasuk.setBorder(null);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText(":");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        lb1.setForeground(new java.awt.Color(242, 246, 253));
        lb1.setText("Test");

        txt2.setAnimationColor(new java.awt.Color(176, 152, 142));
        txt2.setHintText("Cari Barang ...");
        txt2.setSelectionColor(new java.awt.Color(176, 152, 142));
        txt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt2ActionPerformed(evt);
            }
        });

        lb2.setForeground(new java.awt.Color(242, 246, 253));
        lb2.setText("Test");

        txt3.setAnimationColor(new java.awt.Color(176, 152, 142));
        txt3.setHintText("Cari Supplier ...");
        txt3.setSelectionColor(new java.awt.Color(176, 152, 142));
        txt3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt3ActionPerformed(evt);
            }
        });

        lb3.setForeground(new java.awt.Color(242, 246, 253));
        lb3.setText("Test");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelKategori1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelKeterangan1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtJumlahBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelKategori, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                    .addComponent(labelKodePart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelNamaPart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                    .addComponent(labelNamaBarang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtIdBarangMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtKodePart, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnPilihTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNamaPart, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabel1)))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(txtIdDetailBarangMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lb1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lb2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lb3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(txt2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                                    .addComponent(txt3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lb1)
                    .addComponent(lb2)
                    .addComponent(lb3)
                    .addComponent(txtIdDetailBarangMasuk))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnPilihTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtIdBarangMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelKodePart, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelNamaPart, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtKodePart, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txt3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNamaPart, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelKategori1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtJumlahBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(labelKeterangan1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    private void btnPilihTanggalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPilihTanggalMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPilihTanggalMouseClicked

    private void btnPilihTanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPilihTanggalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPilihTanggalKeyPressed

    private void txtIdBarangMasukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdBarangMasukKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtSupplier.requestFocus();
        }
    }//GEN-LAST:event_txtIdBarangMasukKeyPressed

    private void txtSupplierKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSupplierKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtKodePart.requestFocus();
        }
    }//GEN-LAST:event_txtSupplierKeyPressed

    private void txtKodePartKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKodePartKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtNamaPart.requestFocus();
        }
    }//GEN-LAST:event_txtKodePartKeyPressed

    private void txtNamaPartKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaPartKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtJumlahBarang.requestFocus();
        }
    }//GEN-LAST:event_txtNamaPartKeyPressed

    private void txtJumlahBarangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumlahBarangKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtKeterangan.requestFocus();
        }
    }//GEN-LAST:event_txtJumlahBarangKeyPressed

    private void txtJumlahBarangKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumlahBarangKeyTyped
        char enter = evt.getKeyChar();
        if (!(Character.isDigit(enter))) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Masukan Hanya Angka 0-9", "Input Qty", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txtJumlahBarangKeyTyped

    private void tabelSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelSupplierMouseClicked
        int bar = tabelSupplier.getSelectedRow();
        String a = tabmode3.getValueAt(bar, 0).toString();
        String b = tabmode3.getValueAt(bar, 1).toString();
        txtSupplier.setText(b);
        txtKodePart.requestFocus();
    }//GEN-LAST:event_tabelSupplierMouseClicked

    private void tabelBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBarangMouseClicked
        int bar = tabelBarang.getSelectedRow();
        String a = tabmode2.getValueAt(bar, 0).toString();
        String b = tabmode2.getValueAt(bar, 1).toString();
        txtKodePart.setText(a);
        txtNamaPart.setText(b);
        txtJumlahBarang.requestFocus();
    }//GEN-LAST:event_tabelBarangMouseClicked

    private void btnTambahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTambahMouseEntered
        btnTambah.setBackground(new Color(224,207,186));
        btnTambah.setForeground(Color.black);
    }//GEN-LAST:event_btnTambahMouseEntered

    private void btnTambahMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTambahMouseExited
        btnTambah.setBackground(new Color(204, 204, 204));
        btnTambah.setForeground(Color.black);
    }//GEN-LAST:event_btnTambahMouseExited

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        if (txtIdBarangMasuk.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "ID BM tidak boleh kosong");
            txtIdBarangMasuk.requestFocus();
        } else if (txtSupplier.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Supplier tidak boleh kosong");
            txtSupplier.requestFocus();
        } else if (txtKodePart.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Kode Part tidak boleh kosong");
            txtKodePart.requestFocus();
        } else if (txtNamaPart.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Nama Part tidak boleh kosong");
            txtNamaPart.requestFocus();
        } else if (txtJumlahBarang.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Qty tidak boleh kosong");
            txtJumlahBarang.requestFocus();
        } else {
            String sql = "insert into tb_detail_brg_masuk values (?,?,?,?,?,?,?,?)";
            String tampilan = "dd-MM-yyyy";
            SimpleDateFormat fm = new SimpleDateFormat(tampilan);
            String tanggal = String.valueOf(fm.format(btnPilihTanggal.getDate()));
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, tanggal.toString());
                stat.setString(2, txtIdDetailBarangMasuk.getText());
                stat.setString(3, txtIdBarangMasuk.getText());
                stat.setString(4, txtSupplier.getText());
                stat.setString(5, txtKodePart.getText());
                stat.setString(6, txtNamaPart.getText());
                stat.setString(7, txtJumlahBarang.getText());
                stat.setString(8, txtKeterangan.getText());
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
                //            String refresh = "select * from tb_barang";
                //autoIdBM();
//                autoIdBM_DT();
                kosong2();
                dataTable();
                lebarKolom();
                txtIdBarangMasuk.setEnabled(false);
                txtSupplier.setEnabled(false);
                txtKodePart.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Disimpan" + e);
            }
        }
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnSimpanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseEntered
        btnSimpan.setBackground(new Color(224,207,186));
        btnSimpan.setForeground(Color.black);
    }//GEN-LAST:event_btnSimpanMouseEntered

    private void btnSimpanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseExited
        btnSimpan.setBackground(new Color(204, 204, 204));
        btnSimpan.setForeground(Color.black);
    }//GEN-LAST:event_btnSimpanMouseExited

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        if (txtIdBarangMasuk.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "ID BM tidak boleh kosong");
            txtIdBarangMasuk.requestFocus();
        } else if (txtSupplier.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Supplier tidak boleh kosong");
            txtSupplier.requestFocus();
        } else {
            String sql = "insert into tb_brg_masuk values (?,?,?,?)";
            String tampilan = "dd-MM-yyyy";
            SimpleDateFormat fm = new SimpleDateFormat(tampilan);
            String tanggal = String.valueOf(fm.format(btnPilihTanggal.getDate()));
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, tanggal.toString());
                stat.setString(2, txtIdBarangMasuk.getText());
                stat.setString(3, txtSupplier.getText());
                stat.setString(4, txtKeterangan.getText());
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
                //            String refresh = "select * from tb_barang";
                kosong();
//                autoIdBM();
//                autoIdBM_DT();
                dataTable();
                lebarKolom();
                txtIdBarangMasuk.setEnabled(true);
                txtSupplier.setEnabled(true);
                txtIdBarangMasuk.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Disimpan" + e);
            }
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnUbahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUbahMouseEntered
        btnUbah.setBackground(new Color(224,207,186));
        btnUbah.setForeground(Color.black);
    }//GEN-LAST:event_btnUbahMouseEntered

    private void btnUbahMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUbahMouseExited
        btnUbah.setBackground(new Color(204, 204, 204));
        btnUbah.setForeground(Color.black);
    }//GEN-LAST:event_btnUbahMouseExited

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        String sql = "update tb_detail_brg_masuk set tanggal=?,id_detail_bm=?,id_bm=?,supplier=?,kode_part=?,nama_part=?,jumlah=?,keterangan=? where id_detail_bm='" + txtIdDetailBarangMasuk.getText() + "'";
        String tampilan = "dd-MM-yyyy";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tanggal = String.valueOf(fm.format(btnPilihTanggal.getDate()));
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, tanggal.toString());
            stat.setString(2, txtIdDetailBarangMasuk.getText());
            stat.setString(3, txtIdBarangMasuk.getText());
            stat.setString(4, txtSupplier.getText());
            stat.setString(5, txtKodePart.getText());
            stat.setString(6, txtNamaPart.getText());
            stat.setString(7, txtJumlahBarang.getText());
            stat.setString(8, txtKeterangan.getText());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            //            String refresh = "select * from tb_barang";
            kosong();
//            autoIdBM();
//            autoIdBM_DT();
            dataTable();
            lebarKolom();
            txtIdBarangMasuk.requestFocus();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah" + e);
        }
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnHapusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusMouseEntered
        btnHapus.setBackground(Color.red);
        btnHapus.setForeground(Color.white);
    }//GEN-LAST:event_btnHapusMouseEntered

    private void btnHapusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusMouseExited
        btnHapus.setBackground(new Color(204, 204, 204));
        btnHapus.setForeground(Color.black);
    }//GEN-LAST:event_btnHapusMouseExited

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, " Apakah Anda Yakin Ingin "
                + "Menghapus Data", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            String sql = "delete from tb_detail_brg_masuk where id_bm='" + txtIdBarangMasuk.getText() + "'";
            String sql2 = "delete from tb_brg_masuk where id_bm='" + txtIdBarangMasuk.getText() + "'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                PreparedStatement stat2 = conn.prepareStatement(sql2);
                stat2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                kosong();
//                autoIdBM();
//                autoIdBM_DT();
                dataTable();
                lebarKolom();
                txtIdBarangMasuk.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus" + e);
            }
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnBersihMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBersihMouseEntered
        btnBersih.setBackground(new Color(224,207,186));
        btnBersih.setForeground(Color.black);
    }//GEN-LAST:event_btnBersihMouseEntered

    private void btnBersihMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBersihMouseExited
        btnBersih.setBackground(new Color(204, 204, 204));
        btnBersih.setForeground(Color.black);
    }//GEN-LAST:event_btnBersihMouseExited

    private void btnBersihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBersihActionPerformed
//        autoIdBM();
//        autoIdBM_DT();
        tanggal();
        txtIdBarangMasuk.requestFocus();
        txtSupplier.setText(null);
        txtKodePart.setText(null);
        txtNamaPart.setText(null);
        txtJumlahBarang.setText(null);
        txtKeterangan.setText(null);
    }//GEN-LAST:event_btnBersihActionPerformed

    private void btnCetakMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCetakMouseEntered
        btnCetak.setBackground(new Color(0, 0, 204));
        btnCetak.setForeground(Color.white);
    }//GEN-LAST:event_btnCetakMouseEntered

    private void btnCetakMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCetakMouseExited
        btnCetak.setBackground(new Color(204, 204, 204));
        btnCetak.setForeground(Color.black);
    }//GEN-LAST:event_btnCetakMouseExited

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
        try {
            Connection konn = new koneksi().connect();
            File file = new File("src/laporan/transaksiBM.jrxml");
            JasDes = JRXmlLoader.load(file);
            param.put("id_bm", txtIdBarangMasuk.getText());
            JasRep = JasperCompileManager.compileReport(JasDes);
            JasPri = JasperFillManager.fillReport(JasRep, param, konn);
            //JasperViewer.viewReport(JasPri, false);
            JasperViewer jasperViewer = new JasperViewer(JasPri, false);
            jasperViewer.setExtendedState(jasperViewer.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
            jasperViewer.setVisible(true);
            //jasperViewer.setAlwaysOnTop(maximixed);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal membuka Laporan", "Cetak laporan", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCetakActionPerformed

    private void tabelBarangMasukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBarangMasukMouseClicked
        int bar = tabelBarangMasuk.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String d = tabmode.getValueAt(bar, 3).toString();
        String e = tabmode.getValueAt(bar, 4).toString();
        String f = tabmode.getValueAt(bar, 5).toString();
        String g = tabmode.getValueAt(bar, 6).toString();
        String h = tabmode.getValueAt(bar, 7).toString();
        String i = tabmode.getValueAt(bar, 8).toString();

        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        Date dateValue = null;
        try {
            dateValue = date.parse((String) tabelBarangMasuk.getValueAt(bar, 1));
        } catch (ParseException ex) {
            Logger.getLogger(Form_BarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
        }

        btnPilihTanggal.setDate(dateValue);
        txtIdDetailBarangMasuk.setText(c);
        txtIdBarangMasuk.setText(d);
        txtSupplier.setText(e);
        txtKodePart.setText(f);
        txtNamaPart.setText(g);
        txtJumlahBarang.setText(h);
        txtKeterangan.setText(i);
        aktif();
    }//GEN-LAST:event_tabelBarangMasukMouseClicked

    private void txt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt1ActionPerformed

    private void txt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt2ActionPerformed

    private void txt3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBersih;
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnHapus;
    private com.toedter.calendar.JDateChooser btnPilihTanggal;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUbah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel labelKategori;
    private javax.swing.JLabel labelKategori1;
    private javax.swing.JLabel labelKeterangan1;
    private javax.swing.JLabel labelKodePart;
    private javax.swing.JLabel labelNamaBarang;
    private javax.swing.JLabel labelNamaPart;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lb3;
    private javax.swing.JLabel lbTanggal;
    private com.raven.swing.PanelShadow panelShadow1;
    private javax.swing.JTable tabelBarang;
    private javax.swing.JTable tabelBarangMasuk;
    private javax.swing.JTable tabelSupplier;
    private swing.TextFieldAnimation txt1;
    private swing.TextFieldAnimation txt2;
    private swing.TextFieldAnimation txt3;
    private javax.swing.JTextField txtIdBarangMasuk;
    private javax.swing.JTextField txtIdDetailBarangMasuk;
    private javax.swing.JTextField txtJumlahBarang;
    private javax.swing.JTextArea txtKeterangan;
    private javax.swing.JTextField txtKodePart;
    private javax.swing.JTextField txtNamaPart;
    private javax.swing.JTextField txtSupplier;
    // End of variables declaration//GEN-END:variables
}
