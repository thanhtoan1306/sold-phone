/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.view.custom;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author User
 */
public class HuyButton extends JButton{

    public HuyButton() {

        this.setText("Hủy");
        this.setBackground(new Color(3, 81, 145));
        this.setIcon(new ImageIcon(getClass().getResource("/com/qlchdt/assets/icons8_cancel_30px_1.png"))); // NOI18N
        this.setForeground(Color.white);

    }
}
