package com.kasir.jdialog;

import com.kasir.config.Koneksi;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Component;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class DataProduk extends javax.swing.JDialog {

    private int halamanSaatIni = 1;
    private int dataPerHalaman = 14;
    private int totalPages;
    private Connection conn;
    
    private String idBuku;
    private String judulBuku;
    private String pengarangBuku;
    private String penerbitBuku;

    public String getIdBuku() {
        return idBuku;
    }

    public String getJudulBuku() {
        return judulBuku;
    }

    public String getPengarangBuku() {
        return pengarangBuku;
    }

    public String getPenerbitBuku() {
        return penerbitBuku;
    }

    
    
    public DataProduk(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Pencarian");
        conn = Koneksi.getConnection();
        setTabelModel();
        loadData();
        paginationAnggota();
        actionButton();
        setTableRenderer();
        setColumnWidth();
        setLayoutForm();
    }

    private void setLayoutForm(){
        iconJudul.setIcon(new FlatSVGIcon("com/kasir/icon/book.svg", 1f));
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, ""
                + "Pencarian");
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, 
                new FlatSVGIcon("com/kasir/icon/search.svg", 0.80f));
    }

    private void setColumnWidth() {
        TableColumnModel columnModel = tblData.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(40);
        columnModel.getColumn(0).setMaxWidth(40);
        columnModel.getColumn(0).setMinWidth(40);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        iconJudul = new javax.swing.JLabel();
        lbJudul = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        pnHalaman = new javax.swing.JPanel();
        btn_last = new javax.swing.JButton();
        btn_next = new javax.swing.JButton();
        cbx_data = new javax.swing.JComboBox<>();
        btn_before = new javax.swing.JButton();
        btn_first = new javax.swing.JButton();
        lb_halaman = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        iconJudul.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        iconJudul.setForeground(new java.awt.Color(102, 102, 102));

        lbJudul.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lbJudul.setForeground(new java.awt.Color(102, 102, 102));
        lbJudul.setText("Data Produk");

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        tblData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblData.setRowHeight(30);
        jScrollPane1.setViewportView(tblData);

        pnHalaman.setBackground(new java.awt.Color(250, 250, 250));

        btn_last.setText("Last Page");

        btn_next.setText(">");

        cbx_data.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "14", "28", "54", "108" }));

        btn_before.setText("<");

        btn_first.setText("First Page");

        lb_halaman.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_halaman.setText("Halaman of Total Halaman");

        javax.swing.GroupLayout pnHalamanLayout = new javax.swing.GroupLayout(pnHalaman);
        pnHalaman.setLayout(pnHalamanLayout);
        pnHalamanLayout.setHorizontalGroup(
            pnHalamanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnHalamanLayout.createSequentialGroup()
                .addContainerGap(321, Short.MAX_VALUE)
                .addGroup(pnHalamanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_halaman, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnHalamanLayout.createSequentialGroup()
                        .addComponent(btn_first)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_before, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbx_data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_next, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_last)))
                .addContainerGap(322, Short.MAX_VALUE))
        );
        pnHalamanLayout.setVerticalGroup(
            pnHalamanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnHalamanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lb_halaman)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnHalamanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_first, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_before, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_data, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_next, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_last, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(iconJudul, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbJudul))
                    .addComponent(txtSearch)
                    .addComponent(pnHalaman, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbJudul, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iconJudul, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(pnHalaman, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DataProduk dialog = new DataProduk(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_before;
    private javax.swing.JButton btn_first;
    private javax.swing.JButton btn_last;
    private javax.swing.JButton btn_next;
    private javax.swing.JComboBox<String> cbx_data;
    private javax.swing.JLabel iconJudul;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbJudul;
    private javax.swing.JLabel lb_halaman;
    private javax.swing.JPanel pnHalaman;
    private javax.swing.JTable tblData;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

    private void paginationAnggota() {
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
    
 private void actionButton() {
    // Menambahkan KeyListener untuk mendeteksi tombol Enter pada txtSearch
    txtSearch.addKeyListener(new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            // Cek jika tombol yang ditekan adalah Enter
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                // Ambil ID produk dari txtSearch
                String idProduk = txtSearch.getText().trim();
                
                // Cari ID produk di tabel
                cariDanPilihData(idProduk);
            } else {
                // Panggil searchData() saat mengetik
                searchData();
            }
        }
    });

    // Menambahkan mouse listener untuk memilih data dari tabel
    tblData.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            pilihData();  // Memanggil fungsi pilihData() saat klik tabel
        }
    });
}

 private void cariDanPilihData(String idProduk) {
    // Cari baris berdasarkan ID produk
    DefaultTableModel model = (DefaultTableModel) tblData.getModel();
    int rowCount = model.getRowCount();
    boolean found = false;

    for (int i = 0; i < rowCount; i++) {
        // Ambil ID produk dari kolom yang sesuai (misalnya, kolom ke-1)
        String idInTable = model.getValueAt(i, 1).toString();
        
        // Jika ID produk yang dimasukkan ditemukan, pilih data tersebut
        if (idInTable.equals(idProduk)) {
            tblData.setRowSelectionInterval(i, i);  // Pilih baris yang sesuai
            pilihData();  // Panggil pilihData()
            found = true;
            break;
        }
    }
}




    
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
            Logger.getLogger(DataProduk.class.getName()).log(Level.SEVERE,null,e);
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
        lb_halaman.setText(String.valueOf("Halaman "+ halamanSaatIni + " dari Total Data " + totalData));
        
        int startIndex = (halamanSaatIni - 1) * dataPerHalaman;
        getData(startIndex, dataPerHalaman,(DefaultTableModel) tblData.getModel());
    }
    
    private void setTabelModel() {
        DefaultTableModel model = (DefaultTableModel) tblData.getModel();
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
            Logger.getLogger(DataProduk.class.getName()).log(Level.SEVERE, null, e);
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
    
private void searchData() {
    String kataKunci = txtSearch.getText().trim();

    DefaultTableModel model = (DefaultTableModel) tblData.getModel();
    model.setRowCount(0); // Menghapus data tabel sebelum menambahkannya lagi

    try {
        // Menggunakan query yang sama dengan getData, hanya menambahkan kondisi pencarian berdasarkan kataKunci
        String sql = "SELECT pd.ID_Produk, pd.Nama_Produk, pd.Harga_Jual, pd.Harga_Beli, " +
                     "ktg.ID_Kategori, ktg.Nama_Kategori, " +
                     "br.ID_Brand, br.Nama_Brand, pd.Stok, pd.Satuan, pd.Gambar " +
                     "FROM produk pd " +
                     "INNER JOIN kategori ktg ON ktg.ID_Kategori = pd.Kategori " +
                     "INNER JOIN brand br ON br.ID_Brand = pd.Brand " +
                     "WHERE pd.ID_Produk LIKE ? " +  // Kondisi pencarian berdasarkan ID Produk
                     "ORDER BY pd.ID_Produk ASC";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, "%" + kataKunci + "%");  // Mencari dengan kata kunci

            ResultSet rs = st.executeQuery();

            int no = 1; // Menambahkan nomor urut
            while (rs.next()) {
                String idProduk = rs.getString("ID_Produk");
                String namaProduk = rs.getString("Nama_Produk");
                String hargaJual = rs.getString("Harga_Jual");
                String hargaBeli = rs.getString("Harga_Beli");
                String idKategori = rs.getString("ID_Kategori");
                String namaKategori = rs.getString("Nama_Kategori");
                String idBrand = rs.getString("ID_Brand");
                String namaBrand = rs.getString("Nama_Brand");
                int stokProduk = rs.getInt("Stok");
                String satuan = rs.getString("Satuan");

                byte[] imageData = rs.getBytes("Gambar");
                ImageIcon imageIcon = new ImageIcon(imageData);

                Object[] rowData = { "   " + no++, idProduk, namaProduk, hargaJual, hargaBeli, idKategori, namaKategori, idBrand, namaBrand, stokProduk, satuan, imageIcon };
                model.addRow(rowData); // Menambahkan data hasil pencarian ke tabel
            }
        }
    } catch (SQLException e) {
        Logger.getLogger(DataProduk.class.getName()).log(Level.SEVERE, null, e);
    }
}



    
private void pilihData() {
    int row = tblData.getSelectedRow();

    // Memastikan bahwa row yang dipilih valid
    if (row != -1) {
        idBuku        = tblData.getValueAt(row, 1).toString();
        judulBuku     = tblData.getValueAt(row, 2).toString();
        pengarangBuku = tblData.getValueAt(row, 3).toString();
        penerbitBuku  = tblData.getValueAt(row, 9).toString();
        dispose(); // Menutup form setelah memilih data
    }
}

}
