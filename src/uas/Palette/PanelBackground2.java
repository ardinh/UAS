/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uas.Palette;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
/**
 *
 * @author Administrator
 */
public class PanelBackground2 extends JDesktopPane {
    
 
    @Override
    protected void paintComponent(Graphics g) {
        
        Graphics2D graphics = (Graphics2D) g.create();
       
        
         
 
        Image img = new ImageIcon(getClass().getResource("../Images/1517752558867.png")).getImage();
 
        int imgX = img.getWidth(null);
        int imgY = img.getHeight(null);
        graphics.drawImage(img, (getWidth() - imgX) / 2, (getHeight() - imgY) / 2, imgX, imgY, null);
 
 
        graphics.dispose();
    }
}