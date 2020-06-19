/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Custom;

import com.github.lgooddatepicker.components.DatePicker;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author User
 */
public class ChonButton extends JButton {

    

    public ChonButton() {

        this.setText("Thêm");
        this.setBackground(new Color(3, 81, 145));
        this.setIcon(new ImageIcon(getClass().getResource("/DTO/Assets/Icons/ok_icon.png")));
        this.setForeground(Color.white);

//        btnChonKH.setBackground(new Color(3, 81, 145));
//        btnChonKH.setFont(new Font("Tahoma", 1, 14)); // NOI18N
//        btnChonKH.setForeground(new Color(255, 255, 255));
//        btnChonKH.setIcon(new ImageIcon(getClass().getResource("/com/qlchdt/assets/icons8_cancel_30px_1.png"))); // NOI18N
//        btnChonKH.setText("Chọn");
//
//        //btnChonKH.setMaximumSize(Dimension(150, 40));
//        // btnChonKH.setMinimumSize(new java.awt.Dimension(150, 40));
//        btnChonKH.setPreferredSize(new Dimension(150, 40));

    }

}
