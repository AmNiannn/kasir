package com.kasir.form;

import com.kasir.config.Koneksi;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.kasir.jdialog.DataMember;
import com.kasir.jdialog.DataProduk;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class TransaksiPenjualan extends javax.swing.JPanel {

    private int halamanSaatIni = 1;
    private int dataPerHalaman = 14;
    private int totalPages;
    private final Connection conn;
    private String userID;
    
    
    public TransaksiPenjualan(String userID) {
        initComponents();
        
        conn = Koneksi.getConnection();
        this.userID = userID;
        setTabelModel();
        setTabelModelDetail();
        setTabelModelSementara();
        loadData();
        loadDataSementara();
        paginationProduk();
        actionButton();
        setColumnWidth();
        setLayoutForm();
    }

    private void setColumnWidth() {
        TableColumnModel columnModel = tblData.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(40);
        columnModel.getColumn(0).setMaxWidth(40);
        columnModel.getColumn(0).setMinWidth(40);
        
        TableColumnModel columnModel2 = tblDataDetail.getColumnModel();
        columnModel2.getColumn(0).setPreferredWidth(40);
        columnModel2.getColumn(0).setMaxWidth(40);
        columnModel2.getColumn(0).setMinWidth(40);
        
        TableColumnModel columnModel3 = tblDataSementara.getColumnModel();
        columnModel3.getColumn(0).setPreferredWidth(40);
        columnModel3.getColumn(0).setMaxWidth(40);
        columnModel3.getColumn(0).setMinWidth(40);
    }

    private void setLayoutForm(){
        iconJudul.setIcon(new FlatSVGIcon("com/kasir/icon/peminjaman.svg", 1f));
        iconJudul2.setIcon(new FlatSVGIcon("com/kasir/icon/peminjaman.svg", 1f));
        iconJudul3.setIcon(new FlatSVGIcon("com/kasir/icon/peminjaman.svg", 1f));
        iconDashboard.setIcon(new FlatSVGIcon("com/kasir/icon/dashboard.svg", 1f));
        iconDashboard2.setIcon(new FlatSVGIcon("com/kasir/icon/dashboard.svg", 1f));
        lbGambar.setIcon(new FlatSVGIcon("com/kasir/icon/book_abu.svg", 5f));
        btnAdd.setIcon(new FlatSVGIcon("com/kasir/icon/add_white.svg", 1f));
        btnSave.setIcon(new FlatSVGIcon("com/kasir/icon/save_white.svg", 1f));
        btnCancelAdd.setIcon(new FlatSVGIcon("com/kasir/icon/cancel_white.svg", 1f));
        btnUbah.setIcon(new FlatSVGIcon("com/kasir/icon/save_white.svg", 1f));
        btnDelete.setIcon(new FlatSVGIcon("com/kasir/icon/delete_white.svg", 1f));
        btnCancelSementara.setIcon(new FlatSVGIcon("com/kasir/icon/cancel_white.svg", 1f));
        btnPrint.setIcon(new FlatSVGIcon("com/kasir/icon/print_white.svg", 1f));
        
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, 
                new FlatSVGIcon("com/kasir/icon/search.svg", 0.80f));
        
        txtJumlah.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
        
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Pencarian");
        txtID.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "ID Peminjaman");
        txtIdAnggota.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Pilih Member");
        txtNama.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nama Member");
        txtEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Email Member");
        txtTelepon.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Telepon Member");
        txtIdBuku.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Pilih Produk");
        txtJudul.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Judul Produk");
        txtPengarang.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Harga Produk");
        txtPenerbit.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Stok Produk");
        txtJumlah.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Jumlah Pembelian Produk");
        txtSubTotal.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Sub Total");
        txtBayar.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Jumlah Bayar");
        txtKembali.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Jumlah Kembalian");
        txtDiskon.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Jumlah Diskon");
        txtPersen.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "%");
    }
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.raven.datechooser.DateChooser();
        dateChooser2 = new com.raven.datechooser.DateChooser();
        panelMain = new javax.swing.JPanel();
        panelView = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        iconDashboard = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btn_last = new javax.swing.JButton();
        btn_next = new javax.swing.JButton();
        cbx_data = new javax.swing.JComboBox<>();
        btn_before = new javax.swing.JButton();
        btn_first = new javax.swing.JButton();
        lb_halaman = new javax.swing.JLabel();
        iconJudul = new javax.swing.JLabel();
        pnDetail = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        iconJudul3 = new javax.swing.JLabel();
        btnCloseDetail = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDataDetail = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        panelAdd = new javax.swing.JPanel();
        iconJudul2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        iconDashboard2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtTanggalPinjam = new javax.swing.JTextField();
        lbGambar = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnSetAnggota = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btnSetBuku = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnCancelAdd = new javax.swing.JButton();
        txtIdAnggota = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtTelepon = new javax.swing.JTextField();
        txtIdBuku = new javax.swing.JTextField();
        txtJudul = new javax.swing.JTextField();
        txtPengarang = new javax.swing.JTextField();
        txtPenerbit = new javax.swing.JTextField();
        txtJumlah = new javax.swing.JTextField();
        btnUbah = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnCancelSementara = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDataSementara = new javax.swing.JTable();
        txtSubTotal = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtDiskon = new javax.swing.JTextField();
        txtPersen = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtBayar = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtKembali = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        lbTotalHarga = new javax.swing.JLabel();
        txtTotalHarga = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();

        dateChooser1.setTextRefernce(txtTanggalPinjam);
        dateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateChooser1PropertyChange(evt);
            }
        });

        setLayout(new java.awt.CardLayout());

        panelMain.setBackground(new java.awt.Color(255, 255, 255));
        panelMain.setPreferredSize(new java.awt.Dimension(1133, 690));
        panelMain.setLayout(new java.awt.CardLayout());

        panelView.setBackground(new java.awt.Color(255, 255, 255));
        panelView.setPreferredSize(new java.awt.Dimension(1133, 690));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Data Transaksi Kasir");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Transaksi > Kasir");

        jPanel2.setBackground(new java.awt.Color(250, 250, 250));

        btn_last.setText("Last Page");

        btn_next.setText(">");

        cbx_data.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "14", "28", "54", "108" }));

        btn_before.setText("<");

        btn_first.setText("First Page");

        lb_halaman.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_halaman.setText("Halaman of Total Halaman");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_halaman, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_first)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_before, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbx_data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_next, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_last)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lb_halaman, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_first, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_before, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_data, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_next, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_last, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        iconJudul.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        iconJudul.setForeground(new java.awt.Color(102, 102, 102));

        pnDetail.setBackground(new java.awt.Color(250, 250, 250));

        jLabel22.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 102, 102));
        jLabel22.setText("Data Detail Transaksi");

        iconJudul3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        iconJudul3.setForeground(new java.awt.Color(102, 102, 102));

        btnCloseDetail.setForeground(new java.awt.Color(255, 255, 255));
        btnCloseDetail.setText("X");

        jScrollPane2.setViewportView(tblDataDetail);

        javax.swing.GroupLayout pnDetailLayout = new javax.swing.GroupLayout(pnDetail);
        pnDetail.setLayout(pnDetailLayout);
        pnDetailLayout.setHorizontalGroup(
            pnDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDetailLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(pnDetailLayout.createSequentialGroup()
                        .addComponent(iconJudul3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCloseDetail)))
                .addContainerGap())
        );
        pnDetailLayout.setVerticalGroup(
            pnDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDetailLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iconJudul3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCloseDetail))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnAdd.setText("TAMBAH");

        btnPrint.setText("PRINT");

        tblData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblData);

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelViewLayout = new javax.swing.GroupLayout(panelView);
        panelView.setLayout(panelViewLayout);
        panelViewLayout.setHorizontalGroup(
            panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelViewLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(pnDetail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelViewLayout.createSequentialGroup()
                        .addComponent(iconJudul, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelViewLayout.createSequentialGroup()
                                .addComponent(btnAdd)
                                .addGap(115, 115, 115)
                                .addComponent(btnPrint)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelViewLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 724, Short.MAX_VALUE)
                                .addComponent(iconDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)))))
                .addGap(20, 20, 20))
        );
        panelViewLayout.setVerticalGroup(
            panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelViewLayout.createSequentialGroup()
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelViewLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(iconJudul, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelViewLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(iconDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnDetail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelMain.add(panelView, "card2");

        panelAdd.setBackground(new java.awt.Color(255, 255, 255));
        panelAdd.setPreferredSize(new java.awt.Dimension(1133, 690));

        iconJudul2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        iconJudul2.setForeground(new java.awt.Color(102, 102, 102));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Kasir");

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Transaksi > Kasir");

        jLabel24.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(102, 102, 102));
        jLabel24.setText("ID");

        jLabel25.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(102, 102, 102));
        jLabel25.setText("Tanggal Transaksi");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addGap(18, 18, 18)
                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106)
                .addComponent(jLabel25)
                .addGap(18, 18, 18)
                .addComponent(txtTanggalPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTanggalPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        lbGambar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lbGambar.setForeground(new java.awt.Color(102, 102, 102));
        lbGambar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbGambar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Member");

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Nama");

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Email");

        jLabel15.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("Telepon");

        btnSetAnggota.setText("...");

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("Stok");

        jLabel17.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("Harga");

        jLabel18.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("Nama Produk");

        jLabel19.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 102));
        jLabel19.setText("Produk");

        btnSetBuku.setText("...");

        jLabel20.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 102));
        jLabel20.setText("Jumlah");

        jLabel21.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(102, 102, 102));
        jLabel21.setText("Sub Total");

        btnSave.setText("SIMPAN");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnCancelAdd.setText("BATAL");

        txtIdBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdBukuActionPerformed(evt);
            }
        });

        txtPenerbit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPenerbitActionPerformed(evt);
            }
        });

        btnUbah.setText("UBAH");

        btnDelete.setText("HAPUS");

        btnCancelSementara.setText("BATAL");

        tblDataSementara.setRowHeight(30);
        jScrollPane3.setViewportView(tblDataSementara);

        txtSubTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubTotalActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(102, 102, 102));
        jLabel23.setText("Diskon");

        txtPersen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPersenActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(102, 102, 102));
        jLabel28.setText("Bayar");

        txtBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBayarActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(102, 102, 102));
        jLabel29.setText("Kembali");

        jLabel30.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(102, 102, 102));
        jLabel30.setText("Total");

        jLabel31.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(102, 102, 102));
        jLabel31.setText("Rp.");

        lbTotalHarga.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        lbTotalHarga.setForeground(new java.awt.Color(102, 102, 102));

        jLabel32.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(102, 102, 102));
        jLabel32.setText("Total Harga");

        javax.swing.GroupLayout panelAddLayout = new javax.swing.GroupLayout(panelAdd);
        panelAdd.setLayout(panelAddLayout);
        panelAddLayout.setHorizontalGroup(
            panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAddLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAddLayout.createSequentialGroup()
                        .addComponent(btnSave)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelAdd)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAddLayout.createSequentialGroup()
                        .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelAddLayout.createSequentialGroup()
                                .addComponent(iconJudul2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(iconDashboard2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)))
                        .addGap(20, 20, 20))))
            .addGroup(panelAddLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelAddLayout.createSequentialGroup()
                        .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAddLayout.createSequentialGroup()
                                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelAddLayout.createSequentialGroup()
                                        .addComponent(txtIdAnggota, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnSetAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelAddLayout.createSequentialGroup()
                                        .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNama)
                                            .addComponent(txtEmail)
                                            .addComponent(txtTelepon))
                                        .addGap(56, 56, 56)))
                                .addGap(18, 18, 18))
                            .addGroup(panelAddLayout.createSequentialGroup()
                                .addComponent(btnUbah)
                                .addGap(18, 18, 18)
                                .addComponent(btnDelete)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancelSementara)
                                .addGap(131, 131, 131)))
                        .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAddLayout.createSequentialGroup()
                                .addComponent(txtIdBuku)
                                .addGap(18, 18, 18)
                                .addComponent(btnSetBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAddLayout.createSequentialGroup()
                                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtTotalHarga, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtJudul, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPengarang, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPenerbit, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSubTotal, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelAddLayout.createSequentialGroup()
                                        .addComponent(txtPersen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDiskon, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
                                    .addComponent(txtBayar, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(56, 56, 56)))
                        .addGap(15, 15, 15)
                        .addComponent(lbGambar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtJumlah)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29)
                            .addComponent(txtKembali)
                            .addComponent(jLabel30)
                            .addGroup(panelAddLayout.createSequentialGroup()
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbTotalHarga, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)))))
                .addGap(20, 20, 20))
        );
        panelAddLayout.setVerticalGroup(
            panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAddLayout.createSequentialGroup()
                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAddLayout.createSequentialGroup()
                        .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAddLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(iconDashboard2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelAddLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(iconJudul2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, 18)
                        .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAddLayout.createSequentialGroup()
                                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSetAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelAddLayout.createSequentialGroup()
                                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSetBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIdAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIdBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtJudul, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPengarang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelAddLayout.createSequentialGroup()
                            .addGap(301, 301, 301)
                            .addComponent(txtTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelAddLayout.createSequentialGroup()
                            .addGap(189, 189, 189)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelAddLayout.createSequentialGroup()
                            .addGap(301, 301, 301)
                            .addComponent(txtPenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAddLayout.createSequentialGroup()
                            .addGap(189, 189, 189)
                            .addComponent(lbGambar, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelAddLayout.createSequentialGroup()
                        .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAddLayout.createSequentialGroup()
                                .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPersen, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelAddLayout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDiskon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(txtTotalHarga))
                        .addGap(22, 22, 22)
                        .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelSementara, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(panelAddLayout.createSequentialGroup()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
        );

        panelMain.add(panelAdd, "card2");

        add(panelMain, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void dateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateChooser1PropertyChange

    }//GEN-LAST:event_dateChooser1PropertyChange

    private void txtSubTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSubTotalActionPerformed

    private void txtPenerbitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPenerbitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPenerbitActionPerformed

    private void txtBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBayarActionPerformed
pembayaran();
    }//GEN-LAST:event_txtBayarActionPerformed

    private void txtPersenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPersenActionPerformed
hitungPotongan();        
    }//GEN-LAST:event_txtPersenActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtIdBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdBukuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdBukuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancelAdd;
    private javax.swing.JButton btnCancelSementara;
    private javax.swing.JButton btnCloseDetail;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSetAnggota;
    private javax.swing.JButton btnSetBuku;
    private javax.swing.JButton btnUbah;
    private javax.swing.JButton btn_before;
    private javax.swing.JButton btn_first;
    private javax.swing.JButton btn_last;
    private javax.swing.JButton btn_next;
    private javax.swing.JComboBox<String> cbx_data;
    private com.raven.datechooser.DateChooser dateChooser1;
    private com.raven.datechooser.DateChooser dateChooser2;
    private javax.swing.JLabel iconDashboard;
    private javax.swing.JLabel iconDashboard2;
    private javax.swing.JLabel iconJudul;
    private javax.swing.JLabel iconJudul2;
    private javax.swing.JLabel iconJudul3;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbGambar;
    private javax.swing.JLabel lbTotalHarga;
    private javax.swing.JLabel lb_halaman;
    private javax.swing.JPanel panelAdd;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPanel panelView;
    private javax.swing.JPanel pnDetail;
    private javax.swing.JTable tblData;
    private javax.swing.JTable tblDataDetail;
    private javax.swing.JTable tblDataSementara;
    private javax.swing.JTextField txtBayar;
    private javax.swing.JTextField txtDiskon;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtIdAnggota;
    private javax.swing.JTextField txtIdBuku;
    private javax.swing.JTextField txtJudul;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtKembali;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtPenerbit;
    private javax.swing.JTextField txtPengarang;
    private javax.swing.JTextField txtPersen;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTanggalPinjam;
    private javax.swing.JTextField txtTelepon;
    private javax.swing.JTextField txtTotalHarga;
    // End of variables declaration//GEN-END:variables

    
    // Button Halaman 
    private void paginationProduk() {
        btn_first.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                halamanSaatIni = 1;
                loadData();
            }
            
        });
        
        btn_before.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (halamanSaatIni > 1)
                {
                    halamanSaatIni--;
                    loadData();
                }
            }
            
        });
        
        cbx_data.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dataPerHalaman = Integer.parseInt(cbx_data.getSelectedItem().toString());
                halamanSaatIni = 1;
                loadData();
            }
            
        });
        
        btn_next.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (halamanSaatIni < totalPages) {
                    halamanSaatIni++;
                    loadData();
                }
            }
            
        });
        
        btn_last.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                halamanSaatIni = totalPages;
                loadData();
            }
            
        });
btnPrint.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int row = tblData.getSelectedRow(); // Ambil baris yang dipilih di tabel
        if (row >= 0) { // Validasi apakah ada baris yang dipilih
            String idTransaksi = tblData.getValueAt(row, 1).toString(); // Ambil ID Transaksi dari kolom ke-1
            cetakStruk(idTransaksi); // Panggil method cetakLaporan dengan ID Transaksi
        } else {
            JOptionPane.showMessageDialog(null, "Pilih baris terlebih dahulu untuk mencetak laporan!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }
});

    }
    
    // Button Halaman 
    
    // Button Add, Save, Delete, Cancel, Search, TableMouseClick
    private void actionButton(){
        btnAdd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                panelMain.removeAll();
                panelMain.add(panelAdd);
                panelMain.repaint();
                panelMain.revalidate();

                txtID.setText(setIdPeminjaman());
                txtID.setEnabled(false);
                btnSave.setEnabled(false);
                btnUbah.setText("TAMBAH");
            }
        });
        
        btnSave.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(btnSave.getText().equals("TAMBAH"))
                    {
                        btnSave.setText("SIMPAN");
                    }
                else if(btnSave.getText().equals("SIMPAN"))
                    {
                        insertData();
                    }
                else if (btnSave.getText().equals("PERBARUI"))
                    {
                        updateData();
                        resetForm();
                        loadData();
                        showPanel();
                    }
            }
        });
        
        btnUbah.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(btnUbah.getText().equals("TAMBAH")){
                    insertDataSementara();
                    
                } else if(btnUbah.getText().equals("UBAH")){
                    updateData();
                }
            }
        });
        
        btnDelete.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteData();
            }
        });
        
        btnCancelSementara.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataSementara();
                resetFormProduk();
                btnUbah.setText("TAMBAH");
            }
        });
        
        btnCancelAdd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel();
                loadData();
            }
        });
        
        btnCloseDetail.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel();
                loadData();
            }
        });
        
        txtSearch.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e) {
                searchData();
            }
        });
        
        tblData.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        pnDetail.setVisible(true); // Menampilkan panel detail

        int row = tblData.getSelectedRow(); // Ambil baris yang dipilih
        if (row >= 0) { // Validasi jika ada baris yang dipilih
            String id = tblData.getValueAt(row, 1).toString(); // Ambil ID Transaksi dari kolom ke-1 (indeks 1)
            getDataDetail((DefaultTableModel) tblDataDetail.getModel(), id); // Tampilkan data detail
           
        } else {
            JOptionPane.showMessageDialog(null, "Pilih baris terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }
});

        
        tblDataSementara.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                btnUbah.setText("UBAH");
                btnDelete.setVisible(true);
                btnCancelSementara.setVisible(true);
                dataTabelSementara();
            }
        });
        
        btnSetAnggota.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setAnggota();
            }
        });
        
        btnSetBuku.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setProduk();
            }
        });
        
        txtJumlah.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnUbah.getText().equals("TAMBAH")) {
                    insertDataSementara();
                }else if(btnUbah.getText().equals("UBAH")){
                    updateData();
                }
            }
        });
         
    }
    // Button Add, Save, Delete, Cancel, Search, TableMouseClick
    
    // Fungsi lainnya
    private int getTotalData(){
        int totalData = 0;
        
        try {
            String sql = "SELECT COUNT(*) AS total FROM produk";
            try (PreparedStatement st = conn.prepareStatement(sql)){
                ResultSet rs = st.executeQuery();
                if(rs.next()){
                    totalData = rs.getInt("total");
                }
            } 
        } catch (Exception e) {
            Logger.getLogger(TransaksiPenjualan.class.getName()).log(Level.SEVERE,null,e);
        }
        
        return totalData;
    }
    
    private void calculateTotalPages(){
        int totalData = getTotalData();
        totalPages = (int) Math.ceil((double) totalData / dataPerHalaman );
    }
    
   private void loadData() {
    calculateTotalPages();
    int totalData = getTotalData();
    lb_halaman.setText("Halaman " + halamanSaatIni + " dari Total Data " + totalData);

    int startIndex = (halamanSaatIni - 1) * dataPerHalaman;

    // Panggil metode getData dengan userID yang sudah diatur sebelumnya
    getData(userID, startIndex, dataPerHalaman, (DefaultTableModel) tblData.getModel());

    btnDelete.setVisible(false);
    pnDetail.setVisible(false);
}

    
    private void loadDataSementara(){
        getDataSementara((DefaultTableModel) tblDataSementara.getModel());
        btnUbah.setText("TAMBAH");
        btnDelete.setVisible(false);
        btnCancelSementara.setVisible(false);
    }
    
    private void showPanel(){
        panelMain.removeAll();
        panelMain.add(new TransaksiPenjualan(userID));
        panelMain.repaint();
        panelMain.revalidate();
    }
    
    private void resetForm() {
        txtID.setText("");
        txtJudul.setText("");
        txtPengarang.setText("");
        
    }
    
    private void resetFormProduk(){
        txtIdBuku.setText("");
        txtJudul.setText("");
        txtPengarang.setText("");
        txtPenerbit.setText("");
        txtJumlah.setText("");
        lbGambar.setIcon(new FlatSVGIcon("com/kasir/icon/book_abu.svg", 5f));
    }
    
    private void aktifProduk(){
        btnSetBuku.setEnabled(true);
        txtIdBuku.setEnabled(true);
        txtJudul.setEnabled(true);
        txtPengarang.setEnabled(true);
        txtPenerbit.setEnabled(true);
    }
    
    private void nonAktifProduk(){
        btnSetBuku.setEnabled(false);
        txtIdBuku.setEnabled(false);
        txtJudul.setEnabled(false);
        txtPengarang.setEnabled(false);
        txtPenerbit.setEnabled(false);
    }
    
 private void setTabelModel() {
    // Buat model tabel kustom
    DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            // Semua sel tidak dapat diedit
            return false;
        }
    };

    // Tambahkan kolom ke model
    model.addColumn("No");
    model.addColumn("ID Transaksi");
    model.addColumn("Tanggal Transaksi");
    model.addColumn("Sub Total Harga");
    model.addColumn("Total Harga");
    model.addColumn("Bayar");
    model.addColumn("Diskon");
    model.addColumn("Kembalian");
    model.addColumn("Nama Member");
    model.addColumn("Nama Kasir");

    // Set model ke tabel
    tblData.setModel(model);
}


public void getData(String userID, int startIndex, int entriesPage, DefaultTableModel model) {
    model.setRowCount(0);

    try {
        String sql = "SELECT trs.ID_Transaksi, trs.Tanggal_Transaksi, trs.SubTotalHarga, trs.Total_Harga, trs.Bayar, trs.Diskon, trs.Kembali, mbr.Nama_Member, usr.Nama_User " +
                     "FROM transaksi trs " +
                     "INNER JOIN member mbr ON mbr.ID_Member = trs.ID_Member " +
                     "INNER JOIN user usr ON usr.ID_User = trs.ID_User " +
                     "WHERE trs.ID_User = ? " +
                     "ORDER BY trs.ID_Transaksi ASC LIMIT ?, ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, userID); // Filter berdasarkan ID kasir
            st.setInt(2, startIndex); // Parameter untuk pagination
            st.setInt(3, entriesPage); // Parameter untuk pagination
            
            ResultSet rs = st.executeQuery();

            int no = startIndex + 1;
            while (rs.next()) {
                String idTransaksi = rs.getString("ID_Transaksi");
                String tanggalTransaksi = rs.getString("Tanggal_Transaksi");
                int subTotalHarga = rs.getInt("SubTotalHarga");
                int totalHarga = rs.getInt("Total_Harga");
                int jumlahBayar = rs.getInt("Bayar");
                String jumlahDiskon = rs.getString("Diskon");
                String jumlahKembali = rs.getString("Kembali");
                String namaMember = rs.getString("Nama_Member");
                String namaKasir = rs.getString("Nama_User");
                
                Object[] rowData = {"   " + no++, idTransaksi, tanggalTransaksi, subTotalHarga, totalHarga, jumlahBayar, jumlahDiskon, jumlahKembali, namaMember, namaKasir};
                model.addRow(rowData);
            }
        }
    } catch (SQLException e) {
        Logger.getLogger(TransaksiPenjualan.class.getName()).log(Level.SEVERE, null, e);
    }
}

    
  private void setTabelModelDetail() {
    // Buat model tabel kustom
    DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            // Semua sel tidak dapat diedit
            return false;
        }
    };

    // Tambahkan kolom ke model
    model.addColumn("No");
    model.addColumn("ID Transaksi");
    model.addColumn("ID Produk");
    model.addColumn("Nama Produk");
    model.addColumn("Harga");
    model.addColumn("Jumlah");
    model.addColumn("Sub Total");

    // Set model ke tabel
    tblDataDetail.setModel(model);
}

    
    public void getDataDetail(DefaultTableModel model, String id) {
        model.setRowCount(0);

        try {
            String sql = "SELECT tr.ID_Transaksi, det.ID_Produk, pd.Nama_Produk, pd.Harga_Jual, det.Jumlah, det.Sub_Total " +
             "FROM detail_transaksi det " +
             "INNER JOIN transaksi tr ON tr.ID_Transaksi = det.ID_Transaksi " +
             "INNER JOIN produk pd ON pd.ID_Produk = det.ID_Produk " +
             "WHERE tr.ID_Transaksi = '" + id + "'";

            try (PreparedStatement st = conn.prepareStatement(sql)) {
                ResultSet rs = st.executeQuery();

                int no = 1;
                
                while (rs.next()) {
                    String idTransaksi =   (rs.getString ("ID_Transaksi"));
                    String idProduk   =   (rs.getString    ("ID_Produk"));
                    String namaProduk =   (rs.getString ("Nama_produk"));
                    String hargaJual =    (rs.getString    ("Harga_Jual"));
                    String jumlah     =  (rs.getString   ("Jumlah"));
                    int subTotal =      (rs.getInt   ("Sub_Total"));
                    
                    Object[] rowData = {"   " + no++, idTransaksi, idProduk, namaProduk, hargaJual, jumlah, subTotal};
                    model.addRow(rowData);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(TransaksiPenjualan.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private void setTabelModelSementara() {
        DefaultTableModel model = (DefaultTableModel) tblDataSementara.getModel();
        model.addColumn("No");
        model.addColumn("ID");
        model.addColumn("Nama");
        model.addColumn("Harga");
        model.addColumn("Stok");
        model.addColumn("Jumlah");
        model.addColumn("Sub Total");
    }

    public void getDataSementara(DefaultTableModel model) {
        model.setRowCount(0);

        try {
            String sql = "SELECT * FROM sementara";
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                ResultSet rs = st.executeQuery();

                int no = 1;
                
                while (rs.next()) {
                    String idProduk       = rs.getString("ID_Produk");
                    String namaProduk     = rs.getString("Nama_Produk");
                    String hargaProduk    = rs.getString("Harga");
                    String stokProduk     = rs.getString("Stok");
                    String jumlahProduk   = rs.getString("Jumlah");
                    int subTotal       = rs.getInt("Sub_Total");
                    
                    Object[] rowData = {"   " + no++, idProduk, namaProduk, hargaProduk, stokProduk, jumlahProduk, subTotal};
                    model.addRow(rowData);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(TransaksiPenjualan.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private String setIdPeminjaman(){
        String urutan = null;
        Date now = new Date();
        SimpleDateFormat noFormat = new SimpleDateFormat("yyMM");
        String no = noFormat.format(now);
        
        String sql = "SELECT RIGHT(ID_Transaksi, 4) AS Nomor " +
                     "FROM transaksi " +
                     "WHERE ID_Transaksi LIKE 'TK" + no + "%' " +
                     "ORDER BY ID_Transaksi DESC " +
                     "LIMIT 1";
        
        try (PreparedStatement st = conn.prepareStatement(sql)){
                ResultSet rs = st.executeQuery();
                
            if (rs.next()) {
                int nomor = Integer.parseInt(rs.getString("Nomor")) + 1;
                urutan = "TK" + no + String.format("%04d", nomor);
            }else{
                urutan = "TK" + no + "0001";
            }
            
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(TransaksiPenjualan.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return urutan;
    }
    
    private void setAnggota(){
        boolean closable = true;
        DataMember anggota = new DataMember(null, closable);
        anggota.setVisible(true);
        
        txtIdAnggota.setText(anggota.getIdAnggota());
        txtNama.setText(anggota.getNamaAnggota());
        txtEmail.setText(anggota.getEmailAnggota());
        txtTelepon.setText(anggota.getTeleponAnggota());
        
        txtIdAnggota.setEnabled(false);
        txtNama.setEnabled(false);
        txtEmail.setEnabled(false);
        txtTelepon.setEnabled(false);
    }
    
    private void setProduk(){
        boolean closable = true;
        DataProduk buku = new DataProduk(null, closable);
        buku.setVisible(true);
        
        String id = buku.getIdBuku();
        txtIdBuku.setText(buku.getIdBuku());
        txtJudul.setText(buku.getJudulBuku());
        txtPengarang.setText(buku.getPengarangBuku());
        txtPenerbit.setText(buku.getPenerbitBuku());
        setImage(lbGambar, id);
        
        txtIdBuku.setEnabled(false);
        txtJudul.setEnabled(false);
        txtPengarang.setEnabled(false);
        txtPenerbit.setEnabled(false);
    }
    
    private boolean cekStok(String idBuku, String jumlahPinjamBuku) {
    try {
        int jumlahPinjam = Integer.parseInt(jumlahPinjamBuku);

        String sql = "SELECT Stok FROM produk WHERE ID_Produk=?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, idBuku);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                int stokTersedia = rs.getInt("Stok");
                return jumlahPinjam <= stokTersedia;
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
}

private void insertDataSementara() {
    String idProduk = txtIdBuku.getText();
    String namaProduk = txtJudul.getText();
    double hargaProduk;
    int stokProduk;
    int jumlahProduk;

    try {
        hargaProduk = Double.parseDouble(txtPengarang.getText());
        stokProduk = Integer.parseInt(txtPenerbit.getText());
        jumlahProduk = Integer.parseInt(txtJumlah.getText());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Kolom harga, stok, dan jumlah harus berupa angka!", "Validasi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (idProduk.isEmpty() || namaProduk.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Semua kolom harus diisi!", "Validasi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (!cekStok(idProduk, String.valueOf(jumlahProduk))) {
        JOptionPane.showMessageDialog(this, "Stok Produk Tidak Tersedia!", "Peringatan", JOptionPane.ERROR_MESSAGE);
        return;
    }

    double subTotal = hargaProduk * jumlahProduk;

    try {
        conn.setAutoCommit(false); // Memulai transaksi

        // Masukkan data ke tabel sementara
        String sqlInsert = "INSERT INTO sementara (ID_Produk, Nama_Produk, Harga, Stok, Jumlah, Sub_Total) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sqlInsert)) {
            st.setString(1, idProduk);
            st.setString(2, namaProduk);
            st.setDouble(3, hargaProduk);
            st.setInt(4, stokProduk);
            st.setInt(5, jumlahProduk);
            st.setDouble(6, subTotal);

            st.executeUpdate();
        }

        // Update stok di tabel produk
        String sqlUpdate = "UPDATE produk SET Stok = Stok - ? WHERE ID_Produk = ?";
        try (PreparedStatement st = conn.prepareStatement(sqlUpdate)) {
            st.setInt(1, jumlahProduk);
            st.setString(2, idProduk);

            st.executeUpdate();
        }

        conn.commit(); // Commit transaksi

        txtSubTotal.setText(String.valueOf(getSubTotal()));
        loadDataSementara();

        if (JOptionPane.showConfirmDialog(this, "Mau Tambah Produk?",
                "Konfirmasi", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            resetFormProduk();
            btnSetBuku.requestFocus();
        } else {
            resetFormProduk();
            btnSave.requestFocus();
            btnSave.setEnabled(true);
        }
    } catch (SQLException e) {
        try {
            conn.rollback(); // Rollback jika ada error
        } catch (SQLException rollbackEx) {
            rollbackEx.printStackTrace();
        }
        Logger.getLogger(TransaksiPenjualan.class.getName()).log(Level.SEVERE, null, e);
    } finally {
        try {
            conn.setAutoCommit(true); // Kembali ke auto-commit
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}


private double getSubTotal() {
    double total = 0;
    String sql = "SELECT SUM(Sub_Total) AS Total FROM sementara";

    try (PreparedStatement st = conn.prepareStatement(sql);
         ResultSet rs = st.executeQuery()) {
        if (rs.next()) {
            total = rs.getDouble("Total");
        }
    } catch (SQLException e) {
        Logger.getLogger(TransaksiPenjualan.class.getName()).log(Level.SEVERE, null, e);
    }
    return total;
}

    
 private void insertData() {
    String idTransaksi = txtID.getText();
    String tanggalTransaksi = txtTanggalPinjam.getText();
    String totalHargaText = lbTotalHarga.getText().replaceAll("[^\\d.]", "");  // Hapus karakter selain angka dan titik
    String bayarText = txtBayar.getText().replaceAll("[^\\d.]", "");
    String diskonText = txtDiskon.getText().replaceAll("[^\\d]", ""); // Hapus karakter selain angka
    String kembaliText = txtKembali.getText().replaceAll("[^\\d.]", "");
    String member = txtIdAnggota.getText();
    String subTotalHarga = txtSubTotal.getText().replaceAll("[^\\d.]", "");

    // Validasi input kosong
    if (idTransaksi.isEmpty() || tanggalTransaksi.isEmpty() || totalHargaText.isEmpty() 
            || bayarText.isEmpty() || diskonText.isEmpty() || kembaliText.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Semua kolom harus diisi!", "Validasi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Validasi format input
    if (!totalHargaText.matches("^[0-9]+(?:\\.[0-9]+)?$") || 
        !bayarText.matches("^[0-9]+(?:\\.[0-9]+)?$") ||
        !diskonText.matches("^[0-9]+$") ||
        !subTotalHarga.matches("^[0-9]+(?:\\.[0-9]+)?$") ||
        !kembaliText.matches("^[0-9]+(?:\\.[0-9]+)?$")) {
        JOptionPane.showMessageDialog(this, "Input jumlah atau angka tidak valid!", "Error", JOptionPane.ERROR_MESSAGE);
        return; // Berhenti jika format input salah
    }

    try {
        // Parsing tipe data
        double totalHarga = Double.parseDouble(totalHargaText);
        double bayar = Double.parseDouble(bayarText);
        int diskon = Integer.parseInt(diskonText);
        double kembali = Double.parseDouble(kembaliText);
        double subtotalHarga = Double.parseDouble(subTotalHarga);

        // SQL query untuk insert
        String sql = "INSERT INTO transaksi (ID_Transaksi, Tanggal_Transaksi,subTotalHarga ,Total_Harga, Bayar, Diskon, Kembali, ID_User, ID_Member) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, idTransaksi);
            st.setString(2, tanggalTransaksi);
            st.setDouble(3,subtotalHarga);
            st.setDouble(4, totalHarga);
            st.setDouble(5, bayar);
            st.setInt(6, diskon);
            st.setDouble(7, kembali);
            st.setString(8, userID); // Pastikan userID memiliki nilai
            st.setString(9, member);

            // Eksekusi query
            int rowInserted = st.executeUpdate();
            if (rowInserted > 0) {
                JOptionPane.showMessageDialog(this, "Data Berhasil Ditambahkan");
            }

            // Panggil fungsi lain setelah data berhasil disimpan
            insertDataDetail();
            deleteDataSementara();
            resetForm();
            loadData();
            showPanel();
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Input jumlah atau angka tidak valid!", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException e) {
        Logger.getLogger(TransaksiPenjualan.class.getName()).log(Level.SEVERE, null, e);
    }
}







    private void insertDataDetail() {
        String idPeminjaman     = txtID.getText();
        
        try {
            String sql = "INSERT INTO detail_transaksi (ID_Transaksi, ID_Produk, Jumlah, Sub_Total) SELECT '"+idPeminjaman+"', ID_Produk, Jumlah, Sub_Total from sementara";                              
            try(PreparedStatement st = conn.prepareStatement(sql)){
               st.executeUpdate();
            }
        } catch (SQLException e) {
            Logger.getLogger(TransaksiPenjualan.class.getName()).log(Level.SEVERE,null,e);
        }
    }
    
    private void dataTabelSementara() {
    int row = tblDataSementara.getSelectedRow();
    if (row != -1) {
        nonAktifProduk();

        String id = tblDataSementara.getValueAt(row, 1).toString();
        txtIdBuku.setText(tblDataSementara.getValueAt(row, 1).toString());
        txtJudul.setText(tblDataSementara.getValueAt(row, 2).toString());
        txtPengarang.setText(tblDataSementara.getValueAt(row, 3).toString());
        txtPenerbit.setText(tblDataSementara.getValueAt(row, 4).toString());
        txtJumlah.setText(tblDataSementara.getValueAt(row, 5).toString()); // Set nilai Jumlah

        setImage(lbGambar, id);
        txtJumlah.requestFocus();
    }
}
    
    private void setImage(JLabel lb_gambar, String id){
        try {
            String sql = "SELECT * FROM produk WHERE ID_Produk = '"+ id +"'";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            if(rs.next()){
                byte[] img = rs.getBytes("Gambar");
                if(img != null){
                    ImageIcon imageIcon = new ImageIcon(new ImageIcon(img).getImage().
                            getScaledInstance(lb_gambar.getWidth(), lb_gambar.getHeight(), Image.SCALE_SMOOTH));
                    lb_gambar.setIcon(imageIcon);
                }else{
                    ImageIcon defaultIcon = new ImageIcon(getClass().getResource("Image/icons8_book_100px.png"));
                    lb_gambar.setIcon(defaultIcon);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void updateData() {
    String idBuku = txtIdBuku.getText();
    String jumlahPinjam = txtJumlah.getText();

    if (idBuku.isEmpty() || jumlahPinjam.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Semua kolom harus dipilih dan diisi!", "Validasi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        // Dapatkan harga produk untuk menghitung Sub_Total
        String sqlHarga = "SELECT Harga FROM sementara WHERE ID_Produk=?";
        double hargaProduk = 0;

        try (PreparedStatement stHarga = conn.prepareStatement(sqlHarga)) {
            stHarga.setString(1, idBuku);
            ResultSet rs = stHarga.executeQuery();
            if (rs.next()) {
                hargaProduk = rs.getDouble("Harga");
            } else {
                JOptionPane.showMessageDialog(this, "Data produk tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Hitung Sub_Total baru
        int jumlahBaru = Integer.parseInt(jumlahPinjam);
        double subTotalBaru = hargaProduk * jumlahBaru;

        // Update Jumlah dan Sub_Total
        String sqlUpdate = "UPDATE sementara SET Jumlah=?, Sub_Total=? WHERE ID_Produk=?";
        try (PreparedStatement stUpdate = conn.prepareStatement(sqlUpdate)) {
            stUpdate.setInt(1, jumlahBaru);
            stUpdate.setDouble(2, subTotalBaru);
            stUpdate.setString(3, idBuku);
            int rowUpdated = stUpdate.executeUpdate();

            if (rowUpdated > 0) {
                double totalPinjam = getSubTotal(); // Hitung ulang total keseluruhan
                txtSubTotal.setText(String.valueOf(totalPinjam)); // Perbarui lbtotal

                JOptionPane.showMessageDialog(this, "Data Berhasil Diperbarui");
                resetFormProduk();
                loadDataSementara();
                aktifProduk();
                btnSave.setEnabled(true);
            }
        }
    } catch (SQLException e) {
        Logger.getLogger(TransaksiPenjualan.class.getName()).log(Level.SEVERE, null, e);
    }
}

    private void deleteData() {
        int selectedRow = tblDataSementara.getSelectedRow();
        int confirm = JOptionPane.showConfirmDialog(this, 
                "Apakah yakin ingin menghapus data ini ?",
                "Konfirmasi Hapus Data", 
                JOptionPane.YES_NO_OPTION);
        
        if(confirm == JOptionPane.YES_OPTION){
            String id = tblDataSementara.getValueAt(selectedRow, 1).toString();
            try {
                String sql =  "DELETE FROM sementara WHERE ID_Produk=?";
                try(PreparedStatement st = conn.prepareStatement(sql)){
                    st.setString(1, id);
                    int rowDeleted = st.executeUpdate();
                    
                    double totalPinjam = getSubTotal();
                    if(totalPinjam > 0){
                        txtSubTotal.setText(String.valueOf(totalPinjam));
                    }else{
                        txtSubTotal.setText("Total");
                    }
                    
                    if(rowDeleted > 0){
                        JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus");
                    }else{
                        JOptionPane.showMessageDialog(this, "Data Gagal Dihapus");
                    }
                    
                    btnUbah.setText("TAMBAH");
                    aktifProduk();
                }
            } catch (SQLException e) {
                Logger.getLogger(TransaksiPenjualan.class.getName()).log(Level.SEVERE,null,e);
            }
        }
        resetFormProduk();
        loadDataSementara();
    }

    private void deleteDataSementara(){
        try {
            String sql =  "DELETE FROM sementara";
                try(PreparedStatement st = conn.prepareStatement(sql)){
                    st.executeUpdate();
                }   
        } catch (SQLException e) {
            Logger.getLogger(TransaksiPenjualan.class.getName()).log(Level.SEVERE,null,e);
        }
    }
    
    private void searchData() {
        String kataKunci = txtSearch.getText();
        
        DefaultTableModel model = (DefaultTableModel) tblData.getModel();
        model.setRowCount(0);
        
        try {
            String sql = "SELECT trs.ID_Transaksi, trs.Tanggal_Transaksi, trs.Total_Harga, trs.Bayar,trs.Diskon,trs.Kembali, mbr.Nama_Member, usr.Nama_User\n" +
                            "FROM transaksi trs\n" +
                            "INNER JOIN member mbr ON mbr.ID_Member = trs.ID_Member\n" +
                            "INNER JOIN user usr ON usr.ID_User = trs.ID_User\n" +
                            "WHERE trs.ID_Transaksi LIKE ? OR mbr.Nama_Member LIKE ?";
            try (PreparedStatement st = conn.prepareStatement(sql)){
                st.setString(1, "%" + kataKunci + "%");
                st.setString(2, "%" + kataKunci + "%");
                ResultSet rs = st.executeQuery();
                
                int no = 1;
                
                while (rs.next()) {
                    String idTransaksi     = rs.getString("ID_Transaksi");
                    String tanggalTransaksi    = rs.getString("Tanggal_Transaksi");
                    String totalHarga   = rs.getString("Total_Harga");
                    String jumlahBayar      = rs.getString("Bayar");
                    String jumlahDiskon      = rs.getString("Diskon");
                    String jumlahKembali         = rs.getString("Kembali");
                    String namaMember         = rs.getString("Nama_Member");
                    String namaKasir        = rs.getString("Nama_User");
                    
                    Object[] rowData = {"   " + no++, idTransaksi, tanggalTransaksi,totalHarga, jumlahBayar,jumlahDiskon, jumlahKembali,namaMember,namaKasir };
                    model.addRow(rowData);
                }
            } 
        } catch (SQLException e) {
            Logger.getLogger(TransaksiPenjualan.class.getName()).log(Level.SEVERE,null,e);
        }
    }
    private void hitungPotongan() {
    try {
        String subtotalStr = txtSubTotal.getText().replaceAll("[^\\d.]", ""); // Hapus karakter selain angka dan titik
        String diskonStr = txtPersen.getText().replaceAll("[^\\d]", ""); // Hapus karakter selain angka

        // Validasi input subtotal dan diskon
        if (!subtotalStr.matches("^[0-9]+(?:\\.[0-9]+)?$") || !diskonStr.matches("^[0-9]+$")) {
            JOptionPane.showMessageDialog(this, "Masukan angka yang valid untuk diskon");
            return;  // Hentikan jika format salah
        }

        double subtotal = Double.parseDouble(subtotalStr);
        int diskon = Integer.parseInt(diskonStr);
        
        // Menghitung hasil diskon dan total harga
        double hasilDiskon = subtotal * (diskon / 100.0);
        double total = subtotal - hasilDiskon;
        
        // Format hasil menggunakan DecimalFormat
        DecimalFormat df = new DecimalFormat("#.##"); // Format tanpa nol tambahan
        String formattedDiskon = df.format(hasilDiskon);
        String formattedTotal = df.format(total);
        
        // Menampilkan hasil
        txtDiskon.setText(formattedDiskon);
        txtTotalHarga.setText(formattedTotal);
        
        lbTotalHarga.setText(formattedTotal);
        txtBayar.requestFocus();

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Masukan angka yang valid untuk diskon");
    }
}

        
private void pembayaran() {
    try {
        // Mengambil nilai total dan bayar sebagai double
        String totalStr = txtTotalHarga.getText().replaceAll("[^\\d.]", "");  // Hapus karakter selain angka dan titik
        String bayarStr = txtBayar.getText().replaceAll("[^\\d.]", "");

        // Pastikan input total dan bayar valid
        if (!totalStr.matches("^[0-9]+(?:\\.[0-9]+)?$") || !bayarStr.matches("^[0-9]+(?:\\.[0-9]+)?$")) {
            JOptionPane.showMessageDialog(this, "Masukan nominal pembayaran yang valid");
            return;  // Jika format tidak valid, hentikan eksekusi
        }

        double total = Double.parseDouble(totalStr);
        double bayar = Double.parseDouble(bayarStr);
        
        // Menghitung kembali
        double kembali = bayar - total;
        
        // Menampilkan hasil kembalian (tetap tipe double, hanya diformat ke string saat ditampilkan)
        txtKembali.setText(String.format("%.0f", kembali));  // Format dengan 2 angka desimal

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Masukan nominal pembayaran yang valid");
    }
}

private void cetakStruk(String idTransaksi) {
    try {
        String reportPath = "src/com/kasir/reports/report1.jasper";

        // Masukkan ID Transaksi ke dalam parameter untuk laporan
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("ID_Transaksi", idTransaksi);

        // Cetak laporan dengan parameter dan koneksi
        JasperPrint print = JasperFillManager.fillReport(reportPath, parameters, conn);
        JasperViewer viewer = new JasperViewer(print, false);
        viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
        viewer.setVisible(true);
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Gagal mencetak laporan!\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}





}
