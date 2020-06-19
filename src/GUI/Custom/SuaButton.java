/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Custom;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author User
 */
public class SuaButton extends JButton{
    
    public SuaButton () {
     this.setText("Sửa");
        this.setBackground(new Color(3, 81, 145));
        this.setIcon(new ImageIcon(getClass().getResource("/DTO/Assets/Icons/edit_icon.png")));
        this.setForeground(Color.white);
    }
    
}
