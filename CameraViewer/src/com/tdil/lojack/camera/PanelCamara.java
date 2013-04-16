package com.tdil.lojack.camera;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;


/**
 *
 * Proyecto: appletCamara
 */
public class PanelCamara extends JPanel {
   
   private Image imagen;
   
   public void paintComponent(Graphics g) {
     super.paintComponent(g);
     if (imagen != null) {
      Graphics2D graphics2D = (Graphics2D) g.create(0, 0, getWidth(), getHeight());
      graphics2D.drawImage(imagen, 0, 0, getWidth(), getHeight(), null);
     }
   }
   
   
   /**
    * @return Devuelve el atributo &quot;imagen&quot.
    */
   public Image getImagen() {
      return imagen;
   }
   /**
    * @param imagen El valor a establecer en el atributo &quot;imagen&quot.
    */
   public void setImagen(Image imagen) {
      this.imagen = imagen;
   }
}
