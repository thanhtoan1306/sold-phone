/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlchdt.view.DinhDangCp;

import com.github.lgooddatepicker.components.DatePicker;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author User
 */
public class DateButton extends JButton {

    public DateButton(DatePicker dp) {
        
        ImageIcon dPickerIcon = new ImageIcon(getClass().getResource("/com/qlchdt/assets/calendar.png"));
        JButton datePickerButton = dp.getComponentToggleCalendarButton();
        datePickerButton.setBackground(Color.white);
        datePickerButton.setText(null);
        datePickerButton.setIcon(dPickerIcon);
        
    }

}
