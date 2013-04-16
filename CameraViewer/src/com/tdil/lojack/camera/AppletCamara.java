package com.tdil.lojack.camera;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.tdil.lojack.camera.models.PanasonicBLC131;
import com.tdil.lojack.camera.models.TPLinkSC4171G;

/**
 * Class <code>AppletCamara</code> is responsible of establishing a directo connection
 * to the camera and refresh its image at a regular interval.
 */
public class AppletCamara extends javax.swing.JApplet {

	private static final long serialVersionUID = -2966192681788304871L;

	private IPCamera camera = null;

	private PanelCamara panelCamara;

	private JButton jButtonDerecha;

	private JButton jButtonIzquierda;

	private JPanel jPanelHorizontal;

	private JButton jButtonAbajo;

	private JButton jButtonArriba;

	private JPanel jPanelVertical;

	private long refreshInterval;

	public AppletCamara() {
		initGUI();
	}

	/**
	 * Initializes the GUI. Auto-generated code - any changes you make will
	 * disappear.
	 */
	public void initGUI() {
		try {
			preInitGUI();
			jPanelVertical = new JPanel();
			jButtonArriba = new JButton();
			jButtonAbajo = new JButton();
			jPanelHorizontal = new JPanel();
			jButtonIzquierda = new JButton();
			jButtonDerecha = new JButton();

			GridBagLayout thisLayout = new GridBagLayout();
			this.getContentPane().setLayout(thisLayout);
			thisLayout.columnWidths = new int[] { 1, 1 };
			thisLayout.rowHeights = new int[] { 1, 1 };
			thisLayout.columnWeights = new double[] { 0.1, 0.1 };
			thisLayout.rowWeights = new double[] { 0.1, 0.1 };
			this.setSize(new java.awt.Dimension(350, 241));
			this.getContentPane().setBackground(
					new java.awt.Color(208, 176, 255));

			GridBagLayout jPanelVerticalLayout = new GridBagLayout();
			jPanelVertical.setLayout(jPanelVerticalLayout);
			jPanelVerticalLayout.columnWidths = new int[] { 1 };
			jPanelVerticalLayout.rowHeights = new int[] { 1, 1 };
			jPanelVerticalLayout.columnWeights = new double[] { 0.1 };
			jPanelVerticalLayout.rowWeights = new double[] { 0.1, 0.1 };
			jPanelVertical.setBackground(new java.awt.Color(208, 176, 255));
			this.getContentPane().add(
					jPanelVertical,
					new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, 10, 0,
							new Insets(0, 0, 5, 0), 0, 0));

			jButtonArriba.setIcon(new ImageIcon(AppletCamara.class
					.getResource("/com/tdil/lojack/camera/up.png")));
			jButtonArriba.setPressedIcon(new ImageIcon(AppletCamara.class
					.getResource("/com/tdil/lojack/camera/up_press.png")));
			jButtonArriba.setRolloverIcon(new ImageIcon(AppletCamara.class
					.getResource("/com/tdil/lojack/camera/up_rollover.png")));
			jButtonArriba.setToolTipText("Mover la cámara hacia arriba");
			jButtonArriba.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
			jPanelVertical.add(jButtonArriba, new GridBagConstraints(0, 0, 1,
					1, 0.0, 0.0, 10, 2, new Insets(0, 0, 5, 0), 0, 0));
			jButtonArriba.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jButtonArribaActionPerformed(evt);
				}
			});

			jButtonAbajo.setIcon(new ImageIcon(AppletCamara.class
					.getResource("/com/tdil/lojack/camera/down.png")));
			jButtonAbajo.setPressedIcon(new ImageIcon(AppletCamara.class
					.getResource("/com/tdil/lojack/camera/down_press.png")));
			jButtonAbajo.setRolloverIcon(new ImageIcon(AppletCamara.class
					.getResource("/com/tdil/lojack/camera/down_rollover.png")));
			jButtonAbajo.setToolTipText("Mover la cámara hacia abajo");
			jButtonAbajo.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
			jPanelVertical.add(jButtonAbajo, new GridBagConstraints(0, 1, 1, 1,
					0.0, 0.0, 10, 0, new Insets(5, 0, 0, 0), 0, 0));
			jButtonAbajo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jButtonAbajoActionPerformed(evt);
				}
			});

			GridBagLayout jPanelHorizontalLayout = new GridBagLayout();
			jPanelHorizontal.setLayout(jPanelHorizontalLayout);
			jPanelHorizontalLayout.columnWidths = new int[] { 1, 1 };
			jPanelHorizontalLayout.rowHeights = new int[] { 1 };
			jPanelHorizontalLayout.columnWeights = new double[] { 0.1, 0.1 };
			jPanelHorizontalLayout.rowWeights = new double[] { 0.1 };
			jPanelHorizontal.setBackground(new java.awt.Color(208, 176, 255));
			this.getContentPane().add(
					jPanelHorizontal,
					new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, 10, 0,
							new Insets(0, 0, 0, 5), 0, 0));

			jButtonIzquierda.setIcon(new ImageIcon(AppletCamara.class
					.getResource("/com/tdil/lojack/camera/left.png")));
			jButtonIzquierda.setPressedIcon(new ImageIcon(getClass()
					.getResource("left_press.png")));
			jButtonIzquierda.setRolloverIcon(new ImageIcon(getClass()
					.getResource("left_rollover.png")));
			jButtonIzquierda
					.setToolTipText("Mover la cámara hacia la izquierda");
			jButtonIzquierda.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
			jPanelHorizontal.add(jButtonIzquierda, new GridBagConstraints(0, 0,
					1, 1, 0.0, 0.0, 10, 0, new Insets(0, 0, 0, 5), 0, 0));
			jButtonIzquierda.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jButtonIzquierdaActionPerformed(evt);
				}
			});

			jButtonDerecha.setIcon(new ImageIcon(AppletCamara.class
					.getResource("/com/tdil/lojack/camera/right.png")));
			jButtonDerecha.setPressedIcon(new ImageIcon(getClass().getResource(
					"right_press.png")));
			jButtonDerecha.setRolloverIcon(new ImageIcon(getClass()
					.getResource("right_rollover.png")));
			jButtonDerecha.setToolTipText("Mover la cámara hacia la derecha");
			jButtonDerecha.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
			jPanelHorizontal.add(jButtonDerecha, new GridBagConstraints(1, 0,
					1, 1, 0.0, 0.0, 10, 0, new Insets(0, 5, 0, 0), 0, 0));
			jButtonDerecha.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jButtonDerechaActionPerformed(evt);
				}
			});

			postInitGUI();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** Add your pre-init code in here */
	public void preInitGUI() {
	}

	/** Add your post-init code in here */
	public void postInitGUI() {
		panelCamara = new PanelCamara();
		panelCamara.setBackground(new java.awt.Color(255, 255, 255));
		this.getContentPane().add(
				panelCamara,
				new GridBagConstraints(0, 0, 1, 1, 100.0, 100.0, 10, 1,
						new Insets(5, 5, 5, 5), 0, 0));
	}

	public void init() {
		super.init();
		String url = getParameter("url");
		String username = getParameter("username");
		String password = getParameter("password");
		refreshInterval = 1000;
		String refreshIntervalParam = getParameter("refresh");
		if (refreshIntervalParam != null) {
			refreshInterval = Long.parseLong(refreshIntervalParam);
		}

		String model = getParameter("model");
		if ("TPLinkSC4171G".equals(model)) {
			camera = new TPLinkSC4171G(url, username, password);
		}
		if ("PanasonicBLC131".equals(model)) {
			camera = new PanasonicBLC131(url, username, password);
		}

		Thread thread = new Thread() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Thread#run()
			 */
			public void run() {
				super.run();
				while (true) {
					ByteArrayInputStream in = null;
					try {
						in = camera.nextFrame();
						if (in != null) {
							Image imagen = ImageIO.read(in);
							panelCamara.setImage(imagen);
							panelCamara.repaint();
							sleep(refreshInterval);
						}
					} catch (IOException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						try {
							if (in != null) {
								in.close();
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
			}
		};
		thread.start();
	}

	/** Auto-generated event handler method */
	protected void jButtonHomeActionPerformed(ActionEvent evt) {
	}

	/** Auto-generated event handler method */
	protected void jButtonDerechaActionPerformed(ActionEvent evt) {
		camera.right();
	}

	/** Auto-generated event handler method */
	protected void jButtonIzquierdaActionPerformed(ActionEvent evt) {
		camera.left();
	}

	/** Auto-generated event handler method */
	protected void jButtonArribaActionPerformed(ActionEvent evt) {
		camera.up();
	}

	/** Auto-generated event handler method */
	protected void jButtonAbajoActionPerformed(ActionEvent evt) {
		camera.down();
	}

}
