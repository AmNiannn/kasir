package com.kasir.form;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.kasir.config.Koneksi;
import com.kasir.main.MenuUtama;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class FormLogin extends javax.swing.JFrame {

    private Connection conn;
    
    public FormLogin() {
        initComponents();
        conn = Koneksi.getConnection();
        
        setLayoutForm();
    }
    
    private void setLayoutForm(){
        setIconImage(new ImageIcon(getClass().getResource("/com/kasir/icon/Logo.png")).getImage());
        
        txtUsername.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
        txtPassword.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
        
        txtUsername.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, 
                new FlatSVGIcon("com/kasir/icon/username.svg", 0.80f));
        txtPassword.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, 
                new FlatSVGIcon("com/kasir/icon/password.svg", 0.80f));
        
        txtPassword.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true;showCapsLock:true");
        txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, ""
                + "Masukkan username");
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, ""
                + "Masukkan password");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        password = new javax.swing.JLabel();
        username1 = new javax.swing.JLabel();
        welcome = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        txtUsername = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        jPanel1.setBackground(new java.awt.Color(1, 166, 204));

        jPanel3.setBackground(new java.awt.Color(63, 9, 79));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/kasir/icon/Night.png"))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        password.setForeground(new java.awt.Color(63, 9, 79));
        password.setText("Password");
        jPanel2.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, -1));

        username1.setForeground(new java.awt.Color(63, 9, 79));
        username1.setText("Username");
        jPanel2.add(username1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        welcome.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        welcome.setForeground(new java.awt.Color(63, 9, 79));
        welcome.setText("Welcome to");
        jPanel2.add(welcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 120, -1));

        btnLogin.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(204, 204, 204));
        btnLogin.setText("LOGIN");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        jPanel2.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 100, 30));

        txtPassword.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
        });
        jPanel2.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 230, 30));

        txtUsername.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jPanel2.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 230, 30));

        jLabel6.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(63, 9, 79));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("login");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 70, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(63, 9, 79));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("USER");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 70, 50));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(63, 9, 79));
        jLabel9.setText("Simple and efficient transaction");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(63, 9, 79));
        jLabel4.setText("Simple Cashier");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(63, 9, 79));
        jLabel8.setText("Keep me logged in");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, 20));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(63, 9, 79));
        jLabel12.setText("Forgot your password?");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, -1, 20));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            prosesLogin();
        }
    }//GEN-LAST:event_txtPasswordKeyPressed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        prosesLogin();
    }//GEN-LAST:event_btnLoginActionPerformed

    public static void main(String args[]) {
        FlatLaf.registerCustomDefaultsSource("com.kasir.theme");
        FlatLightLaf.setup();
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel password;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    private javax.swing.JLabel username1;
    private javax.swing.JLabel welcome;
    // End of variables declaration//GEN-END:variables

    public String getMd5java(String message){
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(message.getBytes("UTF-8"));
            
            StringBuilder sb = new StringBuilder( 2 * hash.length);
            for(byte b : hash){
                sb.append(String.format("%02x", b & 0xff));
            }
            digest = sb.toString();
            
        } catch (Exception e) {
            Logger.getLogger(FormLogin.class.getName()).log(Level.SEVERE,null,e);
        }
        
        return digest;
    }
    
    private boolean validasiInput(){
        boolean valid = false;
        if(txtUsername.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Username tidak boleh kosong");
        }else if(txtPassword.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Password tidak boleh kosong");
        }else{
            valid = true;
        }
        return valid;
    }
    
    private Map<String, String> checkLogin(String username, String password){
        Map<String, String> result = new HashMap<>();
        
        if(conn != null){
            try {
               String sql = "SELECT * FROM user WHERE Username=? AND Password=?"; 
               PreparedStatement st = conn.prepareStatement(sql);
               st.setString(1, username);
               st.setString(2, password);

               ResultSet rs = st.executeQuery();
               if(rs.next()){
                   result.put("ID_User", rs.getString("ID_User"));
                   result.put("Nama_User", rs.getString("Nama_User"));
                   result.put("Level", rs.getString("Level"));
                   return result;
               }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    private void prosesLogin() {
    if (validasiInput()) {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        // Hash password menggunakan MD5
        String hashedPassword = getMd5java(password);

        // Periksa login dengan password yang sudah di-hash
        Map<String, String> loginResult = checkLogin(username, hashedPassword);

        if (loginResult != null) {
            String userID = loginResult.get("ID_User");
            String namaUser = loginResult.get("Nama_User");
            String levelUser = loginResult.get("Level");

            // Buka Menu Utama
            MenuUtama mn = new MenuUtama(userID, namaUser, levelUser);
            mn.setVisible(true);
            mn.revalidate();

            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Username dan Password Salah",
                    "Pesan", JOptionPane.INFORMATION_MESSAGE);

        }
    }
}

    
    
}
