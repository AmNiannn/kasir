package com.kasir.form;

import com.kasir.config.Koneksi;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class MasterBuku extends javax.swing.JPanel {

    private int halamanSaatIni = 1;
    private int dataPerHalaman = 14;
    private int totalPages;
    private final Connection conn;
    private String idKategori;
    private String idPenerbit;
    
    
    public MasterBuku() {
        initComponents();
        
        conn = Koneksi.getConnection();
        setTabelModel();
        loadData();
        paginationBuku();
        actionButton();
        setTableRenderer();
        setColumnWidth();
        setLayoutForm();
    }

    private void setColumnWidth() {
        TableColumnModel columnModel = tblData.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(40);
        columnModel.getColumn(0).setMaxWidth(40);
        columnModel.getColumn(0).setMinWidth(40);
    }
    
    private void setLayoutForm(){
        iconJudul.setIcon(new FlatSVGIcon("com/kasir/icon/book.svg", 1f));
        iconJudul2.setIcon(new FlatSVGIcon("com/kasir/icon/book.svg", 1f));
        iconDashboard.setIcon(new FlatSVGIcon("com/kasir/icon/dashboard.svg", 1f));
        iconDashboard2.setIcon(new FlatSVGIcon("com/kasir/icon/dashboard.svg", 1f));
        lbGambar.setIcon(new FlatSVGIcon("com/kasir/icon/book_abu.svg", 5f));
        btnAdd.setIcon(new FlatSVGIcon("com/kasir/icon/add_white.svg", 1f));
        btnDelete.setIcon(new FlatSVGIcon("com/kasir/icon/delete_white.svg", 1f));
        btnCancel.setIcon(new FlatSVGIcon("com/kasir/icon/cancel_white.svg", 1f));
        btnSave.setIcon(new FlatSVGIcon("com/kasir/icon/save_white.svg", 1f));
        btnCancel2.setIcon(new FlatSVGIcon("com/kasir/icon/cancel_white.svg", 1f));
        
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, 
                new FlatSVGIcon("com/kasir/icon/search.svg", 0.80f));
        
        txtID.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
        txtNama.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
        txtHBeli.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
        txtHJual.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
        txtStok.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
        
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Pencarian");
        txtID.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "ID Produk");
        txtNama.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Masukkan Nama Barang");
        txtHBeli.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Masukkan Harga Jual");
        txtHJual.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Masukkan Harga Beli");
        txtStok.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Masukkan Jumlah Stok Produk");
        txtImagePath.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Pilih Photo");
        
        cbxKategori.setForeground(new Color(153, 153, 153)); // Set initial color
        cbxKategori.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (cbxKategori.getSelectedItem().equals("Pilih Kategori")) {
                        cbxKategori.setForeground(new Color(153, 153, 153));
                    } else {
                        cbxKategori.setForeground(new Color(0, 0, 0));
                    }
                }
            }
        });

        cbxKategori.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (cbxKategori.getSelectedItem().equals("Pilih Kategori")) {
                    cbxKategori.setForeground(new Color(153, 153, 153));
                } else {
                    cbxKategori.setForeground(new Color(0, 0, 0));
                }
            }
        });
        
        cbxBrand.setForeground(new Color(153, 153, 153)); // Set initial color
        cbxBrand.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (cbxBrand.getSelectedItem().equals("Pilih Brand")) {
                        cbxBrand.setForeground(new Color(153, 153, 153));
                    } else {
                       cbxBrand.setForeground(new Color(0, 0, 0));
                    }
                }
            }
        });

        cbxBrand.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (cbxBrand.getSelectedItem().equals("Pilih Brand")) {
                    cbxKategori.setForeground(new Color(153, 153, 153));
                } else {
                    cbxBrand.setForeground(new Color(0, 0, 0));
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        btnCancel = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        panelAdd = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        menu = new javax.swing.JLabel();
        iconDashboard2 = new javax.swing.JLabel();
        iconJudul2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lbGambar = new javax.swing.JLabel();
        txtImagePath = new javax.swing.JTextField();
        btnBrowse = new javax.swing.JButton();
        btnCancel2 = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtHBeli = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtHJual = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cbxBrand = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        cbxKategori = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        txtStok = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        cbxSatuan = new javax.swing.JComboBox<>();

        setLayout(new java.awt.CardLayout());

        panelMain.setBackground(new java.awt.Color(255, 255, 255));
        panelMain.setPreferredSize(new java.awt.Dimension(1133, 690));
        panelMain.setLayout(new java.awt.CardLayout());

        panelView.setBackground(new java.awt.Color(255, 255, 255));
        panelView.setPreferredSize(new java.awt.Dimension(1133, 690));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Data Produk");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Master Data > Produk");

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

        btnAdd.setText("TAMBAH");

        btnDelete.setText("HAPUS");

        tblData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblData.setRowHeight(100);
        jScrollPane1.setViewportView(tblData);

        btnCancel.setText("BATAL");

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout panelViewLayout = new javax.swing.GroupLayout(panelView);
        panelView.setLayout(panelViewLayout);
        panelViewLayout.setHorizontalGroup(
            panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelViewLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelViewLayout.createSequentialGroup()
                        .addComponent(iconJudul, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 762, Short.MAX_VALUE)
                        .addComponent(iconDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelViewLayout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
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
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelMain.add(panelView, "card2");

        panelAdd.setBackground(new java.awt.Color(255, 255, 255));
        panelAdd.setPreferredSize(new java.awt.Dimension(1133, 690));

        jScrollPane2.setBorder(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        title.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        title.setForeground(new java.awt.Color(102, 102, 102));
        title.setText("Tambah Data Produk");

        menu.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        menu.setForeground(new java.awt.Color(102, 102, 102));
        menu.setText("Master Data >Produk");

        iconJudul2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        iconJudul2.setForeground(new java.awt.Color(102, 102, 102));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 913, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 619, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        lbGambar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lbGambar.setForeground(new java.awt.Color(102, 102, 102));
        lbGambar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbGambar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        btnBrowse.setText("...");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbGambar, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtImagePath, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbGambar, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtImagePath, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(227, Short.MAX_VALUE))
        );

        btnCancel2.setText("BATAL");

        btnSave.setText("SIMPAN");

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("ID");

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Nama Produk");

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Harga Jual");

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Harga Beli");

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Brand");

        cbxBrand.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxBrand.setForeground(new java.awt.Color(102, 102, 102));

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Kategori");

        cbxKategori.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxKategori.setForeground(new java.awt.Color(102, 102, 102));

        jLabel15.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("Stok");

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("Satuan");

        cbxSatuan.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxSatuan.setForeground(new java.awt.Color(102, 102, 102));
        cbxSatuan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Satuan -", "PCS", "Pack" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxSatuan, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtHJual)
                            .addComponent(txtHBeli)
                            .addComponent(txtNama)
                            .addComponent(txtID)
                            .addComponent(txtStok)
                            .addComponent(cbxKategori, 0, 913, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cbxBrand, 0, 913, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(iconJudul2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(title)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(iconDashboard2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menu))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSave)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iconDashboard2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iconJudul2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHBeli, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(txtHJual, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout panelAddLayout = new javax.swing.GroupLayout(panelAdd);
        panelAdd.setLayout(panelAddLayout);
        panelAddLayout.setHorizontalGroup(
            panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAddLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1121, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelAddLayout.setVerticalGroup(
            panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAddLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelMain.add(panelAdd, "card2");

        add(panelMain, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        searchData();
    }//GEN-LAST:event_txtSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCancel2;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btn_before;
    private javax.swing.JButton btn_first;
    private javax.swing.JButton btn_last;
    private javax.swing.JButton btn_next;
    private javax.swing.JComboBox<String> cbxBrand;
    private javax.swing.JComboBox<String> cbxKategori;
    private javax.swing.JComboBox<String> cbxSatuan;
    private javax.swing.JComboBox<String> cbx_data;
    private javax.swing.JLabel iconDashboard;
    private javax.swing.JLabel iconDashboard2;
    private javax.swing.JLabel iconJudul;
    private javax.swing.JLabel iconJudul2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbGambar;
    private javax.swing.JLabel lb_halaman;
    private javax.swing.JLabel menu;
    private javax.swing.JPanel panelAdd;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPanel panelView;
    private javax.swing.JTable tblData;
    private javax.swing.JLabel title;
    private javax.swing.JTextField txtHBeli;
    private javax.swing.JTextField txtHJual;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtImagePath;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtStok;
    // End of variables declaration//GEN-END:variables

    
    // Button Halaman 
    private void paginationBuku() {
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

                txtID.setText(setIDBuku());
                txtID.setEnabled(false);
                
                if(btnAdd.getText().equals("UBAH")){
                    dataTabel();
                    btnSave.setText("PERBARUI");
                }
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
                    }
            }
        });
        
        btnDelete.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteData();
            }
        });
        
        btnCancel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel();
                loadData();
            }
        });
        
        btnCancel2.addActionListener(new ActionListener(){
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
        
        tblData.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(btnAdd.getText().equals("TAMBAH")){
                    btnAdd.setText("UBAH");
                    btnDelete.setVisible(true);
                    btnCancel.setVisible(true);
                }
            }
        });
        
        btnBrowse.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                getImage();
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
            Logger.getLogger(MasterBuku.class.getName()).log(Level.SEVERE,null,e);
        }
        
        return totalData;
    }
    
    private void calculateTotalPages(){
        int totalData = getTotalData();
        totalPages = (int) Math.ceil((double) totalData / dataPerHalaman );
    }
    
    private void loadData() {
        getKategori();
        getPenerbit();
        calculateTotalPages();
        int totalData = getTotalData();
        lb_halaman.setText(String.valueOf("Halaman "+ halamanSaatIni + " dari Total Data " + totalData));
        
        int startIndex = (halamanSaatIni - 1) * dataPerHalaman;
        getData(startIndex, dataPerHalaman,(DefaultTableModel) tblData.getModel());
        btnDelete.setVisible(false);
        btnCancel.setVisible(false);
    }
    
    private void showPanel(){
        panelMain.removeAll();
        panelMain.add(new MasterBuku());
        panelMain.repaint();
        panelMain.revalidate();
    }
    
    private void resetForm() {
        txtID.setText("");
        txtNama.setText("");
        txtHBeli.setText("");
        txtHJual.setText("");
    }
    
   private void setTabelModel() {
    DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            // Mengatur semua sel agar tidak bisa diedit
            return false;
        }
    };

    tblData.setModel(model);
    
    model.addColumn("No");
    model.addColumn("ID Produk");
    model.addColumn("Nama Produk");
    model.addColumn("Harga Jual");
    model.addColumn("Harga Beli");
    model.addColumn("ID Kategori");
    model.addColumn("Nama Kategori");
    model.addColumn("ID Brand");
    model.addColumn("Brand");
    model.addColumn("Stok");
    model.addColumn("Satuan");
    model.addColumn("Gambar");
}

    public void getData(int startIndex, int entriesPage, DefaultTableModel model) {
        model.setRowCount(0);

        try {
            String sql = "SELECT pd.ID_Produk, pd.Nama_Produk, pd.Harga_Jual, pd.Harga_Beli, \n" +
                        "ktg.ID_Kategori, ktg.Nama_Kategori, \n" +
                        "br.ID_Brand, br.Nama_Brand,pd.Stok,pd.Satuan, pd.Gambar\n" +
                        "FROM produk pd \n" +
                        "INNER JOIN kategori ktg ON ktg.ID_Kategori = pd.Kategori\n" +
                        "INNER JOIN brand br ON br.ID_Brand = pd.Brand ORDER BY pd.ID_Produk ASC LIMIT ?,?";
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setInt(1, startIndex);
                st.setInt(2, entriesPage);
                ResultSet rs = st.executeQuery();

                int no = startIndex + 1;
                
                while (rs.next()) {
                    String idProduk       = rs.getString("ID_Produk");
                    String namaProduk    = rs.getString("Nama_Produk");
                    String hargaJual = rs.getString("Harga_Jual");
                    String hargaBeli  = rs.getString("Harga_Beli");
                    String idKategori   = rs.getString("ID_Kategori");
                    String namaKategori   = rs.getString("Nama_Kategori");
                    String idBrand   = rs.getString("ID_Brand");
                    String namaBrand   = rs.getString("Nama_Brand");
                    int stokProduk     = rs.getInt("Stok");
                    String Satuan   = rs.getString("Satuan");
                    
                    byte[] imageData = rs.getBytes("Gambar");
                    ImageIcon imageIcon = new ImageIcon(imageData);

                    Object[] rowData = {"   " + no++, idProduk, namaProduk, hargaJual, hargaBeli, idKategori,namaKategori, idBrand , namaBrand,stokProduk,Satuan, imageIcon};
                    model.addRow(rowData);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(MasterBuku.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void setTableRenderer(){
        
        class CustomRenderer extends DefaultTableCellRenderer{

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if(value instanceof ImageIcon){
                    ImageIcon icon = (ImageIcon) value;
                    Image originalImage = icon.getImage();
                    
                    int desireWidth = 70;
                    int desireHeight = 90;
                    
                    Image resizedImage =  originalImage.getScaledInstance(desireWidth, desireHeight, Image.SCALE_SMOOTH);
                    ImageIcon resizedIcon = new ImageIcon(resizedImage);
                    
                    JLabel label = new JLabel(resizedIcon);
                    label.setHorizontalAlignment(JLabel.CENTER);
                    
                    return label;
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        }
        
        tblData.getColumnModel().getColumn(10).setCellRenderer(new CustomRenderer());
    }
    
    class ImageRender extends DefaultTableCellRenderer{

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if(value instanceof ImageIcon){
                ImageIcon icon = (ImageIcon) value;
                JLabel label = new JLabel(icon);
                label.setHorizontalAlignment(JLabel.CENTER);
                return label;
            }
            
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
        
    }
    
    private String setIDBuku(){
        String urutan = null;
        Date now = new Date();
        SimpleDateFormat noFormat = new SimpleDateFormat("yyMM");
        String no = noFormat.format(now);
        
        String sql = "SELECT RIGHT(ID_Produk, 4) AS Nomor " +
                     "FROM produk " +
                     "WHERE ID_Produk LIKE 'PD" + no + "%' " +
                     "ORDER BY ID_Produk DESC " +
                     "LIMIT 1";
        
        try (PreparedStatement st = conn.prepareStatement(sql)){
                ResultSet rs = st.executeQuery();
                
            if (rs.next()) {
                int nomor = Integer.parseInt(rs.getString("Nomor")) + 1;
                urutan = "PD" + no + String.format("%04d", nomor);
            }else{
                urutan = "PD" + no + "0001";
            }
            
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(MasterBuku.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return urutan;
    }
    
    private void getKategori(){
        try {
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            model.addElement("Pilih Kategori");
            
            String sql = "SELECT ID_Kategori, Nama_Kategori FROM kategori";
            PreparedStatement st = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                String namaKategori = rs.getString("Nama_Kategori");
                model.addElement(namaKategori);
            }
            
            cbxKategori.setModel(model);
            cbxKategori.addActionListener(e -> {
                int selectIndex = cbxKategori.getSelectedIndex();
                
                if(selectIndex > 0){
                    try {
                        rs.absolute(selectIndex);
                        idKategori = rs.getString("ID_Kategori");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void getPenerbit(){
        try {
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            model.addElement("Pilih Brand");
            
            String sql = "SELECT ID_Brand, Nama_Brand FROM brand";
            PreparedStatement st = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                String namaKategori = rs.getString("Nama_Brand");
                model.addElement(namaKategori);
            }
            
            cbxBrand.setModel(model);
            cbxBrand.addActionListener(e -> {
                int selectIndex = cbxBrand.getSelectedIndex();
                
                if(selectIndex > 0){
                    try {
                        rs.absolute(selectIndex);
                        idPenerbit = rs.getString("ID_Brand");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void getImage(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Gambar","jpg","png","jpeg"));
        
        int result = fileChooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION){
            File selectedFile = fileChooser.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            txtImagePath.setText(path);
            
            try {
                byte[] img = Files.readAllBytes(selectedFile.toPath());
                ImageIcon imageIcon = new ImageIcon(img);
                
                int labelWidth = lbGambar.getWidth();
                int labelHeight = lbGambar.getHeight();
                
                int imageWidth = imageIcon.getIconWidth();
                int imageHeight = imageIcon.getIconHeight();
                
                double scaleX = (double) labelWidth / (double) imageWidth;
                double scaleY = (double) labelHeight / (double) imageHeight;
                double scale = Math.min(scaleX, scaleY);
                
                Image scaleImage = imageIcon.getImage().getScaledInstance(
                        (int) (scale * imageWidth), 
                        (int) (scale * imageHeight), 
                        Image.SCALE_SMOOTH);
                
                lbGambar.setIcon(new ImageIcon(scaleImage));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
       
    }

    private void insertData() {
        String idBuku = txtID.getText();
        String judulBuku = txtNama.getText();
        String pengarangBuku = txtHBeli.getText();
        String tahunTerbit = txtHJual.getText();
        String stokBuku = txtStok.getText();
        String satuanProduk = cbxSatuan.getSelectedItem().toString();
        String imagePath = txtImagePath.getText();
        
        if(idBuku.isEmpty() || judulBuku.isEmpty() || 
                pengarangBuku.isEmpty() || tahunTerbit.isEmpty() || cbxKategori.getSelectedItem().toString().equals("Pilih Kategori") ||
                cbxBrand.getSelectedItem().toString().equals("Pilih Brand") || stokBuku.isEmpty() || imagePath.isEmpty()){
            JOptionPane.showMessageDialog(this, "Semua kolom harus diisi !", "Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            String sql = "INSERT INTO produk (ID_Produk, Nama_Produk, Harga_Jual, Harga_Beli, Kategori, Brand, Stok,Satuan, Gambar) VALUES (?,?,?,?,?,?,?,?,?)";
            try(PreparedStatement st = conn.prepareStatement(sql)){
                st.setString(1, idBuku);
                st.setString(2, judulBuku);
                st.setString(3, pengarangBuku);
                st.setString(4, tahunTerbit);
                st.setString(5, idKategori);
                st.setString(6, idPenerbit);
                st.setString(7, stokBuku);
                st.setString(8, satuanProduk);
                
                File imageFile = new File(imagePath);
                FileInputStream fis = new FileInputStream(imageFile);
                st.setBinaryStream(9, fis, (int) imageFile.length());
                
                int rowInserted = st.executeUpdate();
                if(rowInserted > 0){
                    JOptionPane.showMessageDialog(this, "Data Berhasil Ditambahkan");
                    resetForm();
                    loadData();
                    showPanel();
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MasterBuku.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException e) {
            Logger.getLogger(MasterBuku.class.getName()).log(Level.SEVERE,null,e);
        }
    }

    private void dataTabel() {
        panelView.setVisible(false);
        panelAdd.setVisible(true);
        
        int row = tblData.getSelectedRow();
        title.setText("Perbarui Data Buku Perpustakaan");
        
        txtID.setEnabled(false);
        String id = tblData.getModel().getValueAt(row, 1).toString();
        
        txtID.setText(tblData.getValueAt(row, 1).toString());
        txtNama.setText(tblData.getValueAt(row, 2).toString());
        txtHBeli.setText(tblData.getValueAt(row, 3).toString());
        txtHJual.setText(tblData.getValueAt(row,4).toString());
        idKategori = tblData.getModel().getValueAt(row, 5).toString();
        idPenerbit = tblData.getModel().getValueAt(row, 7).toString();
        txtStok.setText(tblData.getModel().getValueAt(row, 9).toString());
        
        setImage(lbGambar, id);
        getKategoriID(idKategori);
        getPenerbitID(idPenerbit);
    }
    
    private void setImage(JLabel lb_gambar, String id){
        try {
            String sql = "SELECT * FROM buku WHERE ID_Buku = '"+ id +"'";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            if(rs.next()){
                byte[] img = rs.getBytes("Gambar");
                if(img != null){
                    ImageIcon imageIcon = new ImageIcon(new ImageIcon(img).getImage().
                            getScaledInstance(lb_gambar.getWidth(), lb_gambar.getHeight(), Image.SCALE_SMOOTH));
                    lb_gambar.setIcon(imageIcon);
                    System.out.println(imageIcon);
                }else{
                    ImageIcon defaultIcon = new ImageIcon(getClass().getResource("Image/icons8_book_100px.png"));
                    lb_gambar.setIcon(defaultIcon);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void getKategoriID(String id){
        try {
            String sql = "SELECT ID_Kategori, Nama_Kategori FROM kategori";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                String idKat = rs.getString("ID_Kategori");
                String namaKat = rs.getString("Nama_Kategori");
                
                if (id.equals(idKat)) {
                    cbxKategori.setSelectedItem(namaKat);
                }
            }
            
            cbxKategori.addItemListener(new ItemListener(){
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        String selectedKat = cbxKategori.getSelectedItem().toString();
                        updateKategoriID(selectedKat);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void updateKategoriID(String selectedKat){
        try {
            String sql = "SELECT ID_Kategori, Nama_Kategori FROM kategori WHERE Nama_Kategori = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, selectedKat);
            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
                String idKat = rs.getString("ID_Kategori");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void getPenerbitID(String id){
        try {
            String sql = "SELECT ID_Brand, Nama_Brand FROM brand";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                String idPnb = rs.getString("ID_Brand");
                String namaPnb = rs.getString("Nama_Brand");
                
                if (id.equals(idPnb)) {
                    cbxBrand.setSelectedItem(namaPnb);
                }
            }
            
            cbxBrand.addItemListener(new ItemListener(){
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        String selectedPenerbit = cbxBrand.getSelectedItem().toString();
                        updatePenerbitID(selectedPenerbit);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void updatePenerbitID(String selectedPenerbit){
        try {
            String sql = "SELECT ID_Brand, Nama_Brand FROM brand WHERE Nama_Brand = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, selectedPenerbit);
            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
                String idPnb = rs.getString("ID_Brand");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void updateData() {
        String idBuku = txtID.getText();
        String judulBuku = txtNama.getText();
        String pengarangBuku = txtHBeli.getText();
        String tahunTerbit = txtHJual.getText();
        String stokBuku = txtStok.getText();
        String satuanProduk = cbxSatuan.getSelectedItem().toString();
        String imagePath = txtImagePath.getText();
        
        if(idBuku.isEmpty() || judulBuku.isEmpty() || 
                pengarangBuku.isEmpty() || tahunTerbit.isEmpty() || cbxBrand.getSelectedItem().toString().equals("Pilih Kategori") ||
                cbxBrand.getSelectedItem().toString().equals("Pilih Penerbit") || stokBuku.isEmpty() ||txtImagePath.equals("Image Path")){
            JOptionPane.showMessageDialog(this, "Semua kolom harus dipilih dan diisi !", "Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            String sql = "UPDATE produk SET Nama_Produk=?, Harga_Jual=?,Harga_Beli=?, Kategori=?,Brand=?, Stok=?, Satuan=? ";
                    if(!imagePath.isEmpty()){
                        sql += ",Gambar=?";
                    }
                    sql += "WHERE ID_Produk=?";
                    
            try(PreparedStatement st = conn.prepareStatement(sql)){
                st.setString(1, judulBuku);
                st.setString(2, pengarangBuku);
                st.setString(3, tahunTerbit);
                st.setString(4, idKategori);
                st.setString(5, idPenerbit);
                st.setString(6, stokBuku);
                st.setString(7,  satuanProduk);
                
                
                if(!imagePath.isEmpty()){
                    File imageFile = new File(imagePath);
                    FileInputStream fis = new FileInputStream(imageFile);
                    st.setBinaryStream(8, fis, (int) imageFile.length());
                    st.setString(9, idBuku);
                }else{
                    st.setString(8, idBuku);
                }
                
                int rowUpdated = st.executeUpdate();
                if(rowUpdated > 0){
                    JOptionPane.showMessageDialog(this, "Data Berhasil Diperbarui");
                    resetForm();
                    loadData();
                    showPanel();
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MasterBuku.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException e) {
            Logger.getLogger(MasterBuku.class.getName()).log(Level.SEVERE,null,e);
        }
    }

    private void deleteData() {
        int selectedRow = tblData.getSelectedRow();
        int confirm = JOptionPane.showConfirmDialog(this, 
                "Apakah yakin ingin menghapus data ini ?",
                "Konfirmasi Hapus Data", 
                JOptionPane.YES_NO_OPTION);
        
        if(confirm == JOptionPane.YES_OPTION){
            String id = tblData.getValueAt(selectedRow, 1).toString();
            try {
                String sql =  "DELETE FROM produk WHERE ID_Produk=?";
                try(PreparedStatement st = conn.prepareStatement(sql)){
                    st.setString(1, id);
                    
                    int rowDeleted = st.executeUpdate();
                    if(rowDeleted > 0){
                        JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus");
                        
                    }else{
                        JOptionPane.showMessageDialog(this, "Data Gagal Dihapus");
                    }
                }
            } catch (SQLException e) {
                Logger.getLogger(MasterBuku.class.getName()).log(Level.SEVERE,null,e);
            }
        }
        resetForm();
        loadData();
        showPanel();
    }

    private void searchData() {
        String kataKunci = txtSearch.getText();
        
        DefaultTableModel model = (DefaultTableModel) tblData.getModel();
        model.setRowCount(0);
        
        try {
            String sql = "SELECT pd.ID_Produk, pd.Nama_Produk, pd.Harga_Jual, pd.Harga_Beli, \n" +
                        "ktg.ID_Kategori, ktg.Nama_Kategori, \n" +
                        "br.ID_Brand, br.Nama_Brand,pd.Stok,pd.Satuan, pd.Gambar\n" +
                        "FROM produk pd \n" +
                        "INNER JOIN kategori ktg ON ktg.ID_Kategori = pd.Kategori\n" +
                        "INNER JOIN brand br ON br.ID_Brand = pd.Brand\n" +
                        "WHERE pd.Nama_Produk LIKE ? OR ID_Produk LIKE ?";
            try (PreparedStatement st = conn.prepareStatement(sql)){
                st.setString(1, "%" + kataKunci + "%");
                st.setString(2, "%" + kataKunci + "%");
                ResultSet rs = st.executeQuery();
                
                int no = 1;
                
                while (rs.next()) {
                    String idProduk       = rs.getString("ID_Produk");
                    String namaProduk    = rs.getString("Nama_Produk");
                    String hargaJual = rs.getString("Harga_Jual");
                    String hargaBeli  = rs.getString("Harga_Beli");
                    String idKategori   = rs.getString("ID_Kategori");
                    String namaKategori   = rs.getString("Nama_Kategori");
                    String idBrand   = rs.getString("ID_Brand");
                    String namaBrand   = rs.getString("Nama_Brand");
                    int stokProduk     = rs.getInt("Stok");
                    String Satuan   = rs.getString("Satuan");
                    
                    byte[] imageData = rs.getBytes("Gambar");
                    ImageIcon imageIcon = new ImageIcon(imageData);

                    Object[] rowData = {"   " + no++, idProduk, namaProduk, hargaJual, hargaBeli, idKategori,namaKategori, idBrand , namaBrand,stokProduk,Satuan, imageIcon};
                    model.addRow(rowData);
                }
            } 
        } catch (SQLException e) {
            Logger.getLogger(MasterBuku.class.getName()).log(Level.SEVERE,null,e);
        }
    }

    
    
}
