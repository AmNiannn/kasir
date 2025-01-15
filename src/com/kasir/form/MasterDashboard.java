package com.kasir.form;


import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.kasir.config.Koneksi;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javafx.scene.chart.NumberAxis;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;


public class MasterDashboard extends javax.swing.JPanel {
    
    private Connection conn;
    
    public MasterDashboard() {
        initComponents();
        conn = Koneksi.getConnection();
        loadData();
        setLayoutForm();
        getKasirDenganPenjualanTerbanyak();
        getProdukDenganPenjualanTerbanyak();
        totalBarangTerjualBulanIni() ;
        createTransactionBarChart();
    }
    

    
    private void setLayoutForm(){
        cardAnggota.putClientProperty( FlatClientProperties.STYLE, "arc: 20" );
        cardBuku.putClientProperty( FlatClientProperties.STYLE, "arc: 20" );
        cardPeminjaman.putClientProperty( FlatClientProperties.STYLE, "arc: 20" );
        cardPengembalian.putClientProperty( FlatClientProperties.STYLE, "arc: 20" );
        
        iconDashboard.setIcon(new FlatSVGIcon("com/kasir/icon/dashboard.svg", 1f));
        iconAnggota.setIcon(new FlatSVGIcon("com/kasir/icon/anggota_white.svg", 2f));
        iconBuku.setIcon(new FlatSVGIcon("com/kasir/icon/book_white.svg", 2f));
        iconPeminjaman.setIcon(new FlatSVGIcon("com/kasir/icon/stok.svg", 2f));
        iconPengembalian.setIcon(new FlatSVGIcon("com/kasir/icon/pengembalian_white.svg", 2f));
    }
private void createTransactionBarChart() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    // Query untuk menghitung jumlah transaksi per bulan dan tahun
    String sql = "SELECT " +
                 "MONTH(trs.Tanggal_Transaksi) AS Bulan, " +
                 "YEAR(trs.Tanggal_Transaksi) AS Tahun, " +
                 "COUNT(*) AS Total_Transaksi " +  // Menggunakan COUNT(*) untuk menghitung jumlah transaksi
                 "FROM transaksi trs " +
                 "WHERE trs.Tanggal_Transaksi IS NOT NULL " +
                 "GROUP BY YEAR(trs.Tanggal_Transaksi), MONTH(trs.Tanggal_Transaksi) " +
                 "ORDER BY Tahun ASC, Bulan ASC";

    // Mempersiapkan dan menjalankan query
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        try (ResultSet rs = stmt.executeQuery()) {
            // Mengecek apakah hasil query tidak kosong
            if (!rs.isBeforeFirst()) {
            }

            // Mengambil data dan menambahkannya ke dataset
            while (rs.next()) {
                int bulan = rs.getInt("Bulan");
                int tahun = rs.getInt("Tahun");
                int totalTransaksi = rs.getInt("Total_Transaksi");  // Mengambil jumlah transaksi

                // Menyusun label untuk sumbu X (Bulan-Tahun)
                String labelBulan = tahun + "-" + bulan;
                dataset.addValue(totalTransaksi, "Total Transaksi", labelBulan);

            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    // Membuat grafik batang
    JFreeChart chart = ChartFactory.createBarChart(
            "Total Transaksi per Bulan", // Judul grafik
            "Bulan",                    // Label sumbu X
            "Total Transaksi",          // Label sumbu Y
            dataset                     // Dataset
    );

    // Mengatur warna bar grafik
    CategoryPlot plot = (CategoryPlot) chart.getPlot();
    plot.getRenderer().setSeriesPaint(0, new java.awt.Color(12, 161, 198));  // Mengubah warna bar ke #0ca1c6

    // Menambahkan grafik ke dalam PanelGrafik
    ChartPanel chartPanel = new ChartPanel(chart);
    chartPanel.setPreferredSize(new java.awt.Dimension(800, 600)); // Ukuran grafik
    chartPanel.setMouseWheelEnabled(true);

    // Memastikan PanelGrafik menggunakan layout yang benar
    PanelGrafik.setLayout(new BorderLayout());  // Menggunakan BorderLayout untuk menambahkan chart di tengah
    PanelGrafik.removeAll();  // Menghapus komponen yang ada sebelumnya
    PanelGrafik.add(chartPanel, BorderLayout.CENTER);  // Menambahkan grafik ke posisi CENTER

    // Memperbarui dan merender ulang panel
    PanelGrafik.setPreferredSize(new Dimension(800, 600));  // Pastikan PanelGrafik cukup besar untuk menampung grafik
    PanelGrafik.revalidate();  // Memperbarui layout
    PanelGrafik.repaint();     // Merender ulang panel

}








    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        iconDashboard = new javax.swing.JLabel();
        cardAnggota = new javax.swing.JPanel();
        iconAnggota = new javax.swing.JLabel();
        lbJumlahAnggota = new javax.swing.JLabel();
        lb_anggota = new javax.swing.JLabel();
        lbNamaKasir = new javax.swing.JLabel();
        cardBuku = new javax.swing.JPanel();
        lbJumlahBuku = new javax.swing.JLabel();
        lb_anggota1 = new javax.swing.JLabel();
        iconBuku = new javax.swing.JLabel();
        lbNamaProduk = new javax.swing.JLabel();
        cardPeminjaman = new javax.swing.JPanel();
        lbJumlahPeminjaman = new javax.swing.JLabel();
        lb_anggota5 = new javax.swing.JLabel();
        iconPeminjaman = new javax.swing.JLabel();
        cardPengembalian = new javax.swing.JPanel();
        lbJumlahTransaksi = new javax.swing.JLabel();
        lb_anggota3 = new javax.swing.JLabel();
        iconPengembalian = new javax.swing.JLabel();
        lbNamaBulan = new javax.swing.JLabel();
        PanelGrafik = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1133, 690));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(62, 0, 75));
        jLabel1.setText("Grafik Penjualan");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(62, 0, 75));
        jLabel2.setText("Master Data > Dashboard");

        cardAnggota.setBackground(new java.awt.Color(1, 166, 204));

        iconAnggota.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        iconAnggota.setForeground(new java.awt.Color(0, 102, 153));

        lbJumlahAnggota.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        lbJumlahAnggota.setForeground(new java.awt.Color(255, 255, 255));
        lbJumlahAnggota.setText("999");

        lb_anggota.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lb_anggota.setForeground(new java.awt.Color(255, 255, 255));
        lb_anggota.setText("Kasir Terbaik");

        lbNamaKasir.setBackground(new java.awt.Color(255, 255, 255));
        lbNamaKasir.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lbNamaKasir.setForeground(new java.awt.Color(255, 255, 255));
        lbNamaKasir.setText("Nama Kasir");

        javax.swing.GroupLayout cardAnggotaLayout = new javax.swing.GroupLayout(cardAnggota);
        cardAnggota.setLayout(cardAnggotaLayout);
        cardAnggotaLayout.setHorizontalGroup(
            cardAnggotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardAnggotaLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(cardAnggotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardAnggotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lb_anggota, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbNamaKasir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(cardAnggotaLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lbJumlahAnggota)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(iconAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        cardAnggotaLayout.setVerticalGroup(
            cardAnggotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardAnggotaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(cardAnggotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iconAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(cardAnggotaLayout.createSequentialGroup()
                        .addComponent(lb_anggota)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbNamaKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(lbJumlahAnggota)))
                .addGap(16, 16, 16))
        );

        cardBuku.setBackground(new java.awt.Color(1, 166, 204));

        lbJumlahBuku.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        lbJumlahBuku.setForeground(new java.awt.Color(255, 255, 255));
        lbJumlahBuku.setText("999");

        lb_anggota1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lb_anggota1.setForeground(new java.awt.Color(255, 255, 255));
        lb_anggota1.setText("PRODUK TERLARIS");

        iconBuku.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        iconBuku.setForeground(new java.awt.Color(0, 102, 153));

        lbNamaProduk.setBackground(new java.awt.Color(255, 255, 255));
        lbNamaProduk.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lbNamaProduk.setForeground(new java.awt.Color(255, 255, 255));
        lbNamaProduk.setText("Nama Produk");

        javax.swing.GroupLayout cardBukuLayout = new javax.swing.GroupLayout(cardBuku);
        cardBuku.setLayout(cardBukuLayout);
        cardBukuLayout.setHorizontalGroup(
            cardBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardBukuLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(cardBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardBukuLayout.createSequentialGroup()
                        .addGroup(cardBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbJumlahBuku)
                            .addComponent(lb_anggota1))
                        .addGap(0, 66, Short.MAX_VALUE))
                    .addComponent(lbNamaProduk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(iconBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        cardBukuLayout.setVerticalGroup(
            cardBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardBukuLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(cardBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iconBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(cardBukuLayout.createSequentialGroup()
                        .addComponent(lb_anggota1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbJumlahBuku)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cardPeminjaman.setBackground(new java.awt.Color(1, 166, 204));

        lbJumlahPeminjaman.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        lbJumlahPeminjaman.setForeground(new java.awt.Color(255, 255, 255));
        lbJumlahPeminjaman.setText("999");

        lb_anggota5.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lb_anggota5.setForeground(new java.awt.Color(255, 255, 255));
        lb_anggota5.setText("STOK BARANG");

        iconPeminjaman.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        iconPeminjaman.setForeground(new java.awt.Color(0, 102, 153));

        javax.swing.GroupLayout cardPeminjamanLayout = new javax.swing.GroupLayout(cardPeminjaman);
        cardPeminjaman.setLayout(cardPeminjamanLayout);
        cardPeminjamanLayout.setHorizontalGroup(
            cardPeminjamanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardPeminjamanLayout.createSequentialGroup()
                .addGroup(cardPeminjamanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardPeminjamanLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(lb_anggota5))
                    .addGroup(cardPeminjamanLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbJumlahPeminjaman)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(iconPeminjaman, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        cardPeminjamanLayout.setVerticalGroup(
            cardPeminjamanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardPeminjamanLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(cardPeminjamanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardPeminjamanLayout.createSequentialGroup()
                        .addComponent(iconPeminjaman, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(cardPeminjamanLayout.createSequentialGroup()
                        .addComponent(lb_anggota5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbJumlahPeminjaman)
                        .addGap(16, 16, 16))))
        );

        cardPengembalian.setBackground(new java.awt.Color(1, 166, 204));

        lbJumlahTransaksi.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        lbJumlahTransaksi.setForeground(new java.awt.Color(255, 255, 255));
        lbJumlahTransaksi.setText("999");

        lb_anggota3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lb_anggota3.setForeground(new java.awt.Color(255, 255, 255));
        lb_anggota3.setText("PRODUK TERJUAL");

        iconPengembalian.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        iconPengembalian.setForeground(new java.awt.Color(0, 102, 153));

        lbNamaBulan.setBackground(new java.awt.Color(255, 255, 255));
        lbNamaBulan.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lbNamaBulan.setForeground(new java.awt.Color(255, 255, 255));
        lbNamaBulan.setText("Nama Bulan");

        javax.swing.GroupLayout cardPengembalianLayout = new javax.swing.GroupLayout(cardPengembalian);
        cardPengembalian.setLayout(cardPengembalianLayout);
        cardPengembalianLayout.setHorizontalGroup(
            cardPengembalianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardPengembalianLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(cardPengembalianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardPengembalianLayout.createSequentialGroup()
                        .addGroup(cardPengembalianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbJumlahTransaksi)
                            .addComponent(lb_anggota3))
                        .addGap(0, 44, Short.MAX_VALUE))
                    .addComponent(lbNamaBulan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(iconPengembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        cardPengembalianLayout.setVerticalGroup(
            cardPengembalianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardPengembalianLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lb_anggota3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cardPengembalianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardPengembalianLayout.createSequentialGroup()
                        .addComponent(lbNamaBulan, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbJumlahTransaksi))
                    .addComponent(iconPengembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelGrafikLayout = new javax.swing.GroupLayout(PanelGrafik);
        PanelGrafik.setLayout(PanelGrafikLayout);
        PanelGrafikLayout.setHorizontalGroup(
            PanelGrafikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PanelGrafikLayout.setVerticalGroup(
            PanelGrafikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 285, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(339, 901, Short.MAX_VALUE)
                        .addComponent(iconDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PanelGrafik, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cardAnggota, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(48, 48, 48)
                                .addComponent(cardBuku, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(48, 48, 48)
                                .addComponent(cardPeminjaman, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(50, 50, 50)
                                .addComponent(cardPengembalian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 950, Short.MAX_VALUE)))))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iconDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cardPengembalian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cardAnggota, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cardBuku, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cardPeminjaman, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(51, 51, 51)
                .addComponent(PanelGrafik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        add(jPanel1, "card2");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelGrafik;
    private javax.swing.JPanel cardAnggota;
    private javax.swing.JPanel cardBuku;
    private javax.swing.JPanel cardPeminjaman;
    private javax.swing.JPanel cardPengembalian;
    private javax.swing.JLabel iconAnggota;
    private javax.swing.JLabel iconBuku;
    private javax.swing.JLabel iconDashboard;
    private javax.swing.JLabel iconPeminjaman;
    private javax.swing.JLabel iconPengembalian;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbJumlahAnggota;
    private javax.swing.JLabel lbJumlahBuku;
    private javax.swing.JLabel lbJumlahPeminjaman;
    private javax.swing.JLabel lbJumlahTransaksi;
    private javax.swing.JLabel lbNamaBulan;
    private javax.swing.JLabel lbNamaKasir;
    private javax.swing.JLabel lbNamaProduk;
    private javax.swing.JLabel lb_anggota;
    private javax.swing.JLabel lb_anggota1;
    private javax.swing.JLabel lb_anggota3;
    private javax.swing.JLabel lb_anggota5;
    // End of variables declaration//GEN-END:variables

    // Metode untuk mengambil Kasir dengan penjualan terbanyak dan total barang yang dijual
private void getKasirDenganPenjualanTerbanyak() {
    try {
        // Query untuk mengambil nama kasir dan total barang yang dijual
        String sql = "SELECT usr.Nama_User AS Kasir, SUM(det.Jumlah) AS Total_Barang " +
                     "FROM detail_transaksi det " +
                     "INNER JOIN transaksi tr ON tr.ID_Transaksi = det.ID_Transaksi " +
                     "INNER JOIN user usr ON usr.ID_User = tr.ID_User " +
                     "GROUP BY usr.Nama_User " +
                     "ORDER BY Total_Barang DESC LIMIT 1"; // Kasir dengan penjualan terbanyak
        
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
                String namaKasir = rs.getString("Kasir");  // Nama Kasir dengan penjualan terbanyak
                int totalBarang = rs.getInt("Total_Barang");  // Total barang yang dijual oleh kasir tersebut
                
                // Set label untuk nama kasir dan total barang yang dijual
                lbNamaKasir.setText(namaKasir);
                lbJumlahAnggota.setText(String.valueOf(totalBarang));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    
   private void getProdukDenganPenjualanTerbanyak() {
    try {
        // Query untuk mengambil nama produk dan total jumlah terjual
        String sql = "SELECT pd.Nama_Produk, SUM(det.Jumlah) AS Jumlah_Terjual " +
                     "FROM detail_transaksi det " +
                     "INNER JOIN transaksi tr ON tr.ID_Transaksi = det.ID_Transaksi " +
                     "INNER JOIN produk pd ON pd.ID_Produk = det.ID_Produk " +
                     "GROUP BY pd.Nama_Produk " +
                     "ORDER BY Jumlah_Terjual DESC LIMIT 1";  // Produk dengan jumlah terjual terbanyak

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
                String namaProduk = rs.getString("Nama_Produk");  // Nama produk dengan jumlah terjual terbanyak
                int jumlahTerjual = rs.getInt("Jumlah_Terjual");  // Jumlah produk yang terjual
                
                // Set label untuk nama produk dan jumlah terjual
                lbNamaProduk.setText(namaProduk);
                lbJumlahBuku.setText("" + jumlahTerjual);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    
    private int jumlahPeminjaman(){
        int totalPeminjaman = 0;
        
        try {
            String sql = "SELECT SUM(Stok) AS total FROM produk;"; // stok produk
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            if(rs.next()){
                totalPeminjaman = rs.getInt("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalPeminjaman;
    }
    
private int totalBarangTerjualBulanIni() {
    int totalBarangTerjual = 0;

    try {
        String sql = "SELECT SUM(det.Jumlah) AS total_terjual " +
                     "FROM detail_transaksi det " +
                     "INNER JOIN transaksi tr ON det.ID_Transaksi = tr.ID_Transaksi " +
                     "WHERE MONTH(tr.Tanggal_Transaksi) = MONTH(CURDATE()) " +
                     "AND YEAR(tr.Tanggal_Transaksi) = YEAR(CURDATE())";
        PreparedStatement st = conn.prepareStatement(sql);
        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            totalBarangTerjual = rs.getInt("total_terjual");

            // Mendapatkan nama bulan sekarang
            java.time.Month currentMonth = java.time.LocalDate.now().getMonth();
            String namaBulan = currentMonth.getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.getDefault());

            // Set ke label lbNamaBulan
            lbNamaBulan.setText("" + namaBulan);

            // Set ke label lbJumlahTransaksi
            lbJumlahTransaksi.setText("" + totalBarangTerjual);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return totalBarangTerjual;
}
    
    private void loadData() {
        lbJumlahPeminjaman.setText(String.valueOf(jumlahPeminjaman()));
    }
}
