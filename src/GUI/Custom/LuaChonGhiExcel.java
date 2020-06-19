/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Custom;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class LuaChonGhiExcel extends JOptionPane {

    JComboBox<String> cb = new JComboBox<>(new String[]{"Ghi đè", "Bỏ qua", "Ghi đè tất cả", "Bỏ qua tất cả"});
    JPanel pl = new JPanel();

    public LuaChonGhiExcel(MyTable mtb, String defaultSelect) {
        initComponents();
        cb.setBorder(BorderFactory.createTitledBorder("Hành động:"));
        cb.setSelectedItem(defaultSelect);
        mtb.resizeColumnWidth();
        
        pl.setLayout(new BorderLayout());
        pl.add(mtb, BorderLayout.CENTER);
        pl.add(cb, BorderLayout.SOUTH);
        
        this.showMessageDialog(null, pl, "Trùng mã", QUESTION_MESSAGE);
    }

    public String getAnswer() {
        return cb.getSelectedItem().toString();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setMaximumSize(new java.awt.Dimension(400, 200));
        setMinimumSize(new java.awt.Dimension(400, 200));
        setPreferredSize(new java.awt.Dimension(400, 200));
    }// </editor-fold>//GEN-END:initComponents

    private void cbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
