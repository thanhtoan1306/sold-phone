/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.view;

/**
 *
 * @author W7x64Pro
 */
public class ThongKe extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    public ThongKe() {
        initComponents();
        this.jTabbedPane1.setTabLayoutPolicy(jTabbedPane1.SCROLL_TAB_LAYOUT);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new ThongKeDoanhThu();
        jPanel2 = new ThongKeKho();
        jPanel3 = new ThongKeSanPham()
        ;
        jPanel4 = new ThongKeNhanVien();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1200, 900));
        setLayout(new java.awt.BorderLayout());

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setToolTipText("");
        jTabbedPane1.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jTabbedPane1.setOpaque(true);
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(800, 600));

        jPanel1.setInheritsPopupMenu(true);
        jPanel1.setLayout(new java.awt.CardLayout());
        jTabbedPane1.addTab("Doanh Thu", jPanel1);

        jPanel2.setInheritsPopupMenu(true);
        jPanel2.setLayout(new java.awt.CardLayout());
        jTabbedPane1.addTab("Kho", jPanel2);

        jPanel3.setInheritsPopupMenu(true);
        jPanel3.setLayout(new java.awt.CardLayout());
        jTabbedPane1.addTab("Sản Phẩm", jPanel3);

        jPanel4.setInheritsPopupMenu(true);
        jPanel4.setLayout(new java.awt.CardLayout());
        jTabbedPane1.addTab("Nhân Viên", jPanel4);

        add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        jPanel5.setBackground(new java.awt.Color(176, 196, 229));
        jPanel5.setMaximumSize(new java.awt.Dimension(1200, 50));
        jPanel5.setMinimumSize(new java.awt.Dimension(1200, 50));
        jPanel5.setPreferredSize(new java.awt.Dimension(1200, 50));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("THỐNG KÊ");
        jLabel1.setMaximumSize(new java.awt.Dimension(300, 58));
        jLabel1.setMinimumSize(new java.awt.Dimension(300, 58));
        jLabel1.setPreferredSize(new java.awt.Dimension(300, 58));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1200, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 58, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        add(jPanel5, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
