package com.tdil.lojack.camera;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * 
 */
public class PanelCamara extends JPanel {

	private static final long serialVersionUID = 961553442273139701L;

	private Image image;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null) {
			Graphics2D graphics2D = (Graphics2D) g.create(0, 0, getWidth(),
					getHeight());
			graphics2D.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		}
	}

	/**
	 * @return Devuelve el atributo &quot;imagen&quot.
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @param imagen
	 *            El valor a establecer en el atributo &quot;imagen&quot.
	 */
	public void setImage(Image imagen) {
		this.image = imagen;
	}
}
