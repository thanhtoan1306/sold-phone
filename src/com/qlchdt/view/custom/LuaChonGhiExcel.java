/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.view.custom;

import com.qlchdt.service.format.MyTable;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;

/**
 *
 * @author User
 */
public class LuaChonGhiExcel extends JOptionPane {

    public LuaChonGhiExcel(MyTable mtb, String defaultSelect) {
        initComponents();                
        mtb.resizeColumnWidth();
        pl.add(mtb, BorderLayout.CENTER);
        
        this.showMessageDialog(null, pl, "Hiện dữ liệu đang trùng", QUESTION_MESSAGE);
    }


        
    public String getAnswer() {
        return cb.getSelectedItem().toString();
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pl = new javax.swing.JPanel();
        cb = new javax.swing.JComboBox<>();

        setMaximumSize(new java.awt.Dimension(400, 200));
        setMinimumSize(new java.awt.Dimension(400, 200));
        setPreferredSize(new java.awt.Dimension(400, 200));
        setLayout(new java.awt.BorderLayout());

        pl.setMaximumSize(new java.awt.Dimension(400, 200));
        pl.setMinimumSize(new java.awt.Dimension(400, 200));
        pl.setPreferredSize(new java.awt.Dimension(400, 200));
        pl.setLayout(new java.awt.BorderLayout());

        cb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ghi đè \t", "Bỏ quả", "Ghi đè tất cả", "Bỏ qua tất cả", " " }));
        cb.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Phương thức", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        cb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbActionPerformed(evt);
            }
        });
        pl.add(cb, java.awt.BorderLayout.PAGE_END);

        add(pl, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void cbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cb;
    private javax.swing.JPanel pl;
    // End of variables declaration//GEN-END:variables
}
